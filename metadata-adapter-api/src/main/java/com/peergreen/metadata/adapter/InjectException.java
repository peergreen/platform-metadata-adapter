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
 * Exception if injection cannot be done
 * @author Florent Benoit
 */
public class InjectException extends Exception {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -2869484335499180969L;

    public InjectException(String message) {
        super(message);
    }

    public InjectException(String message, Throwable e) {
        super(message, e);
    }
}
