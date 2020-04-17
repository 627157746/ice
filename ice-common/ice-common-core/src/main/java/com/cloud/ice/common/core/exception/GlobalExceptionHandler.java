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

package com.cloud.ice.common.core.exception;

import com.cloud.ice.common.core.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @Author zhb
 * @Description TODO 全局异常处理
 * @Date 2020/4/17 11:40
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public R exception(BaseException e) {
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        return R.ofException(e);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public R bodyValidExceptionHandler(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        log.warn(fieldErrors.get(0).getDefaultMessage());
        return R.ofMsg(fieldErrors.get(0).getDefaultMessage());
    }

}
