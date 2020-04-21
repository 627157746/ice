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

package com.zhb.ice.common.security.component;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lengleng
 * @date 2019/03/08
 */
@ConfigurationPropertiesScan
@ComponentScan("com.zhb.ice.common.security")
public class IceResourceServerAutoConfiguration {
//	@Bean
//	@Primary
//	@LoadBalanced
//	public RestTemplate lbRestTemplate() {
//		RestTemplate restTemplate = new RestTemplate();
//		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
//			@Override
//			@SneakyThrows
//			public void handleError(ClientHttpResponse response) {
//				if (response.getRawStatusCode() != HttpStatus.BAD_REQUEST.value()) {
//					super.handleError(response);
//				}
//			}
//		});
//		return restTemplate;
//	}
}
