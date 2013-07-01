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
package com.peergreen.metadata.adapter.internal.executor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.ow2.util.scan.api.metadata.IMethodMetadata;

import com.peergreen.metadata.adapter.LifeCycleCallbackException;

/**
 * Allows to execute the given method on the given object instance
 * @author Florent Benoit
 */
public class MethodExecutor {

    private final IMethodMetadata methodMetadata;

    public MethodExecutor(IMethodMetadata methodMetadata) {
        this.methodMetadata = methodMetadata;
    }

    public void execute(Object instance) throws LifeCycleCallbackException {

        ClassLoader classLoader = instance.getClass().getClassLoader();

        // load the class of the method
        Class<?> methodClass;
        try {
            methodClass = classLoader.loadClass(methodMetadata.getClassMetadata().getClassName());
        } catch (ClassNotFoundException e) {
            throw new LifeCycleCallbackException("Cannot load class of the injected method", e);
        }

        // get the method name
        Method method = methodMetadata.getReflectMethod(methodClass);
        method.setAccessible(true);

        // Call the method
        try {
            method.invoke(instance);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new LifeCycleCallbackException("Cannot call the method", e);
        }

    }

}
