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
 * JNDI Binding for an annotation
 * @author Florent Benoit
 */
public interface Binding<T> {

    /**
     * @return name of the JNDI binding
     */
    String name();

    /**
     * @return value to use for this JNDI binding
     */
    T value();

}
