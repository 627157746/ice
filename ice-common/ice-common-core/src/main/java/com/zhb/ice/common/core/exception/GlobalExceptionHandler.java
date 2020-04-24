/*
 *
 *  *  Copyright (c) 2019-2020, 冷冷 (wangiegie@gmail.com).
 *  *  <p>
 *  *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *  <p>
 *  * https://www.gnu.org/licenses/lgpl.html
 *  *  <p>
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.zhb.ice.common.core.exception;

import cn.hutool.core.collection.CollUtil;
import com.zhb.ice.common.core.constant.Status;
import com.zhb.ice.common.core.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.exceptions.*;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static com.zhb.ice.common.core.constant.SecurityConstants.ACCOUNT_LOCK;
import static com.zhb.ice.common.core.constant.SecurityConstants.INVALID_REFRESH_TOKEN;

/**
 * @Author zhb
 * @Description TODO 全局异常处理
 * @Date 2020/4/17 11:40
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public R exception(Exception e) {
        System.out.println(e.getClass());
        log.error("全局异常信息 ex={}", e.getMessage());
        return R.ofError();
    }

    @ExceptionHandler(InvalidGrantException.class)
    public R exception(InvalidGrantException e) {
        if (e.getMessage().equals(ACCOUNT_LOCK)) {
            return R.ofStatus(Status.ACCOUNT_LOCK);
        }
        if (e.getMessage().startsWith(INVALID_REFRESH_TOKEN)) {
            return R.ofStatus(Status.INVALID_REFRESH_TOKEN);
        }
        return R.ofStatus(Status.USERNAME_PASSWORD_ERROR);
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public R exception(InternalAuthenticationServiceException e) {
        if (e.getMessage() != null && e.getMessage().equals(Status.ERROR.getMsg())) {
            return R.ofStatus(Status.ERROR);
        }
        return R.ofStatus(Status.USERNAME_PASSWORD_ERROR);
    }

    @ExceptionHandler({UnsupportedGrantTypeException.class,
            InvalidScopeException.class,
            InvalidRequestException.class,
            InvalidClientException.class})
    public R exception() {
        return R.ofStatus(Status.INVALID_CLIENT_PARAM);
    }

    @ExceptionHandler(BaseException.class)
    public R exception(BaseException e) {
        return R.ofException(e);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public R exception(AccessDeniedException e) {
        return R.ofStatus(Status.ACCESS_DENIED);
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    public R exception(NoHandlerFoundException e) {
        return R.ofStatus(Status.REQUEST_NOT_FOUND);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public R exception(HttpRequestMethodNotSupportedException e) {
        return R.ofStatus(Status.HTTP_BAD_METHOD);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public R exception(ConstraintViolationException e) {
        return R.of(Status.BAD_REQUEST.getCode(), CollUtil.getFirst(e.getConstraintViolations())
                .getMessage(), null);
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public R exception(MethodArgumentTypeMismatchException e) {
        return R.ofStatus(Status.PARAM_NOT_MATCH);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public R exception(HttpMessageNotReadableException e) {
        return R.ofStatus(Status.PARAM_NOT_MATCH);
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public R exception(MissingServletRequestParameterException e) {
        return R.of(Status.PARAM_NOT_NULL.getCode(), e.getParameterName() + Status.PARAM_NOT_NULL.getMsg(), null);
    }

    @ExceptionHandler({BindException.class})
    public R exception(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        return R.ofMsg(fieldErrors.get(0).getDefaultMessage());
    }

}
