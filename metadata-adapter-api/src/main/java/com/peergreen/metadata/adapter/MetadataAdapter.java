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

import java.util.Map;

import org.ow2.util.scan.api.metadata.IClassMetadata;
import org.ow2.util.scan.api.metadata.ICollectionClassMetadata;

import com.peergreen.deployment.Artifact;

/**
 * Metadata adapter that handle the injection and life cycle callbacl for a given class or for a given set of classes
 * @author Florent Benoit
 */
public interface MetadataAdapter {

    /**
     * Gets annotated classes fom a given set of metadata
     * @param metadata
     */
    Map<String, AnnotatedClass> adapt(Artifact artifact, ICollectionClassMetadata collectionMetadata);

    /**
     * Gets annotated classes fom a given metadata
     * @param metadata
     */
    AnnotatedClass adapt(Artifact artifact, IClassMetadata metadata);
}
