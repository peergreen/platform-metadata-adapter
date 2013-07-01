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

import java.util.List;

/**
 * Defined an annotated class with annotated members (that can be either on class, field, methods...)
 * @author Florent Benoit
 */
public interface AnnotatedClass {

    /**
     * @return the name of the annotated class
     */
    String className();

    /**
     * @return all entries annotated on a class
     */
    List<AnnotatedMember> entries();

    /**
     * Returns true if this class has methods with the given callback name.
     * @param callbackName the name of the callback
     */
    boolean hasLifeCycleCallBack(String callbackName);

    /**
     * Executes the callback with the given name on the given object instance
     * @param callbackName the name of the callback
     * @param instance the instance of the class to use for calling callback
     */
    void callback(String callbackName, Object instance) throws LifeCycleCallbackException;
}
