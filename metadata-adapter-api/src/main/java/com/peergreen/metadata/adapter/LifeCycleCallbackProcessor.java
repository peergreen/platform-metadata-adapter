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
 * Defines the life cycle callback processor
 * @author Florent Benoit
 */
public @interface LifeCycleCallbackProcessor {

    /**
     * Name of the annotation for which it needs to be called.
     * @return the annotation for which it needs to be called.
     */
    String value();
}
