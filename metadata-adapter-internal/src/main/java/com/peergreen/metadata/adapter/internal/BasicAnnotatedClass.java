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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.peergreen.metadata.adapter.AnnotatedClass;
import com.peergreen.metadata.adapter.AnnotatedMember;
import com.peergreen.metadata.adapter.LifeCycleCallbackException;
import com.peergreen.metadata.adapter.internal.executor.MethodExecutor;

public class BasicAnnotatedClass implements AnnotatedClass {

    private final String name;
    private final List<AnnotatedMember> entries;
    private final Map<String, MethodExecutor> lifecycleExecutors;

    public BasicAnnotatedClass(String name, List<AnnotatedMember> entries) {
        this.name = name;
        this.entries = entries;
        this.lifecycleExecutors = new HashMap<>();
    }


    @Override
    public String className() {
        return name;
    }

    @Override
    public List<AnnotatedMember> entries() {
        return entries;
    }


    @Override
    public boolean hasLifeCycleCallBack(String callbackName) {
        return lifecycleExecutors.containsKey(callbackName);
    }


    @Override
    public void callback(String callbackName, Object instance) throws LifeCycleCallbackException {
        MethodExecutor methodExecutor = lifecycleExecutors.get(callbackName);
        if (methodExecutor != null) {
            methodExecutor.execute(instance);
        }

    }

    /**
     * Adds the given callback on the class.
     * @param callbackName
     * @param methodExecutor
     */
    public void addCallback(String callbackName, MethodExecutor methodExecutor) {
        lifecycleExecutors.put(callbackName, methodExecutor);
    }

}
