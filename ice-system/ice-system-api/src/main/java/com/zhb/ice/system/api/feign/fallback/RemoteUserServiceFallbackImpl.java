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

package com.zhb.ice.system.api.feign.fallback;

import com.zhb.ice.common.core.constant.Status;
import com.zhb.ice.common.core.util.R;
import com.zhb.ice.system.api.dto.SysSocialUserDTO;
import com.zhb.ice.system.api.dto.UserInfo;
import com.zhb.ice.system.api.entity.SysUser;
import com.zhb.ice.system.api.feign.RemoteUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/20 20:42
 */
@Slf4j
@Component
public class RemoteUserServiceFallbackImpl implements RemoteUserService {

    @Override
    public R register(SysSocialUserDTO sysUserDto, String from) {
        return R.ofStatus(Status.ERROR);
    }

    @Override
    public R<SysUser> getSysUserByOpenId(String openId, String from) {
        return R.ofStatus(Status.ERROR);
    }

    @Override
    public R<UserInfo> info(String username, String type, String from) {
        log.error("feign ice-system-service服务请求失败");
        return R.ofStatus(Status.ERROR);
    }

}
