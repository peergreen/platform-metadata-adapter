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
 * Exception if lifecycle callback cannot be done
 * @author Florent Benoit
 */
public class LifeCycleCallbackException extends Exception {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 3231453329776463841L;

    public LifeCycleCallbackException(String message) {
        super(message);
    }

    public LifeCycleCallbackException(String message, Throwable e) {
        super(message, e);
    }
}
