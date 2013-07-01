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
 * Exception if the injection cannot be done
 * @author Florent Benoit
 */
public interface HandlerInjectionProcessor {

    /**
     * Handle the given injection for the given injection context
     * @param injectionContext the injection context with parameters
     * @return the binding if processor can be applied
     */
    Binding<?> handle(InjectionContext injectionContext);

    /**
     * @return name of the annotation for which this processor wants to be notified
     */
    String getAnnotation();
}
