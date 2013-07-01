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

import javax.annotation.PostConstruct;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;

import com.peergreen.metadata.adapter.HandlerLifeCycleCallbackProcessor;
import com.peergreen.metadata.adapter.LifeCycleCallbackContext;

@Component
@Instantiate
@Provides
public class PostConstructHandlerLifeCycleCallbackProcessor implements HandlerLifeCycleCallbackProcessor {

    private final PostConstructLifeCycleCallbackProcessor wrapped;

    public PostConstructHandlerLifeCycleCallbackProcessor() {
        this.wrapped = new PostConstructLifeCycleCallbackProcessor();
    }

    @Override
    public String getAnnotation() {
        return PostConstruct.class.getName();
    }

    @Override
    public void handle(LifeCycleCallbackContext lifeCycleCallbackContext) {
        wrapped.handle(lifeCycleCallbackContext);
    }

}
