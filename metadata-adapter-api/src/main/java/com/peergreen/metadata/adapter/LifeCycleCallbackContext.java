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

import org.ow2.util.scan.api.metadata.IMethodMetadata;
import org.ow2.util.scan.api.metadata.structures.IMethod;


/**
 * Defines the life cycle callback context
 * @author Florent Benoit
 */
public interface LifeCycleCallbackContext extends MetadataContext {

    /**
     * @return the metadata containing the parsed annotations
     */
    IMethodMetadata getMetadata();

    /**
     * @return the method being analyzed
     */
    IMethod getMethod();

    /**
     * Add a callback for the current annotation
     */
    void addCallback();

}
