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

import javax.annotation.PreDestroy;

import org.ow2.util.scan.api.metadata.IMethodMetadata;

import com.peergreen.metadata.adapter.LifeCycleCallbackContext;
import com.peergreen.metadata.adapter.LifeCycleCallbackProcessor;

@LifeCycleCallbackProcessor("javax.annotation.PreDestroy")
public class PreDestroyLifeCycleCallbackProcessor {


    public void handle(LifeCycleCallbackContext lifeCycleCallbackContext) {
        IMethodMetadata metadata = lifeCycleCallbackContext.getMetadata();

        if (PreDestroy.class.getName().equals(lifeCycleCallbackContext.getAnnotation().getClassName())) {
            String[] params = metadata.getParametersClassName();
            //check that it is a no-param method
            if (params.length == 0) {
                // matching
                lifeCycleCallbackContext.addCallback();
            }
        }

    }

}
