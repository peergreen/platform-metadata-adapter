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
package com.peergreen.metadata.adapter;

import org.ow2.util.scan.api.metadata.IClassMetadata;
import org.ow2.util.scan.api.metadata.IFieldMetadata;
import org.ow2.util.scan.api.metadata.IMetadata;
import org.ow2.util.scan.api.metadata.IMethodMetadata;

@InjectionProcessor("com.peergreen.metadata.adapter.DummyAnnotation")
public class MyInjectionProcessor {

    Binding<String> handle(InjectionContext injectionContext) {
        String value = injectionContext.getMetadata().getProperty(DummyAnnotation.class.getName());

        // name is extracted from the metadata
        IMetadata metadata = injectionContext.getMetadata();
        String name = "";
        if (metadata instanceof IClassMetadata) {
            name = ((IClassMetadata) metadata).getClassName();
        } else if (metadata instanceof IFieldMetadata) {
            IFieldMetadata fieldMetadata = ((IFieldMetadata) metadata);
            name = fieldMetadata.getClassMetadata().getClassName().concat("/").concat(fieldMetadata.getMember().getName());
        } else if (metadata instanceof IMethodMetadata) {
            IMethodMetadata methodMetadata = ((IMethodMetadata) metadata);
            name = methodMetadata.getClassMetadata().getClassName().concat("/").concat(methodMetadata.getMember().getName());
        }

        return injectionContext.createBinding(name, value);
    }
}
