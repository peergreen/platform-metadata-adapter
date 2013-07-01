/**
 * Copyright 2013 Peergreen S.A.S. All rights reserved.
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.peergreen.metadata.adapter;

/**
 * Allows to manage injection for a specific type (like Field, Method)
 * @author Florent Benoit
 */
public interface Injector {

    /**
     * Inject the given values on the given instance.
     * @param instance the instance on which to get field/method or constructor or ...
     * @param values the given values
     * @throws InjectException if value cannot be injected
     */
    void inject(Object instance, Object... values) throws InjectException;
}
