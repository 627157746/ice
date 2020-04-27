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

package com.zhb.ice.auth.exception;

import com.zhb.ice.common.core.constant.Status;
import com.zhb.ice.common.core.exception.DefaultExceptionHandler;
import com.zhb.ice.common.core.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.exceptions.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import static com.zhb.ice.common.core.constant.SecurityConstants.ACCOUNT_LOCK;
import static com.zhb.ice.common.core.constant.SecurityConstants.INVALID_REFRESH_TOKEN;

/**
 * @Author zhb
 * @Description TODO 全局异常处理
 * @Date 2020/4/17 11:40
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler  extends DefaultExceptionHandler {

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

    @ExceptionHandler(AccessDeniedException.class)
    public R exception(AccessDeniedException e) {
        return R.ofStatus(Status.ACCESS_DENIED);
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    public R exception(NoHandlerFoundException e) {
        return R.ofStatus(Status.REQUEST_NOT_FOUND);
    }
}
