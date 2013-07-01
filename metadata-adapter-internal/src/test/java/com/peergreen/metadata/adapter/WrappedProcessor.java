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


import com.peergreen.metadata.adapter.Binding;
import com.peergreen.metadata.adapter.HandlerInjectionProcessor;
import com.peergreen.metadata.adapter.InjectionContext;

public class WrappedProcessor implements HandlerInjectionProcessor {

    private final MyInjectionProcessor myInjectionProcessor;

    public WrappedProcessor(MyInjectionProcessor myInjectionProcessor) {
        this.myInjectionProcessor = myInjectionProcessor;
    }

    @Override
    public Binding<?> handle(InjectionContext injectionContext) {
        return myInjectionProcessor.handle(injectionContext);
    }

    @Override
    public String getAnnotation() {
        return DummyAnnotation.class.getName();
    }

}
