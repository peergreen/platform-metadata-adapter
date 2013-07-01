/**
 * Copyright 2013 Peergreen S.A.S.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.peergreen.metadata.adapter.internal;

import org.osgi.framework.BundleContext;
import org.ow2.util.scan.api.metadata.IFieldMetadata;
import org.ow2.util.scan.api.metadata.IMetadata;
import org.ow2.util.scan.api.metadata.IMethodMetadata;

import com.peergreen.metadata.adapter.Binding;
import com.peergreen.metadata.adapter.InjectionContext;
import com.peergreen.metadata.adapter.InjectionProcessor;

@InjectionProcessor("javax.annotation.Resource")
public class ResourceBundleContextInjectionProcessor {

    private final BundleContext bundleContext;

    public ResourceBundleContextInjectionProcessor(BundleContext bundleContext) {
        this.bundleContext = bundleContext;
    }


    public Binding<?> handle(InjectionContext injectionContext) {
        IMetadata metadata = injectionContext.getMetadata();
        if (metadata instanceof IFieldMetadata) {
            IFieldMetadata fieldMetadata = (IFieldMetadata) metadata;
            if (BundleContext.class.getName().equals(fieldMetadata.getType())) {
                // matching interface of the field
                return injectionContext.createBinding(fieldMetadata.getMember().getName(), bundleContext);
            }
        } else if (metadata instanceof IMethodMetadata) {
            IMethodMetadata methodMetadata = (IMethodMetadata) metadata;
                String[] params = methodMetadata.getParametersClassName();
                if (params.length == 1 && BundleContext.class.getName().equals(params[0])) {
                    // matching interface of the field
                    return injectionContext.createBinding(methodMetadata.getMember().getName(), bundleContext);
                }

            }

        return null;

    }

}
