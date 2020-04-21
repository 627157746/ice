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

package com.zhb.ice.system.api.feign;

import com.zhb.ice.common.core.util.R;
import com.zhb.ice.system.api.dto.UserInfo;
import com.zhb.ice.system.api.feign.factory.RemoteUserServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@FeignClient(contextId = "remoteUserService", value = "ice-system-service", fallbackFactory = RemoteUserServiceFallbackFactory.class)
public interface RemoteUserService {

    @GetMapping("/users/info/{username}")
    R<UserInfo> info(@PathVariable(value = "username") String username);

}
