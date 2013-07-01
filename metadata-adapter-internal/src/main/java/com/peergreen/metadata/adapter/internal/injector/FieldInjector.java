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
package com.peergreen.metadata.adapter.internal.injector;

import java.lang.reflect.Field;

import org.ow2.util.scan.api.metadata.IFieldMetadata;

import com.peergreen.metadata.adapter.InjectException;
import com.peergreen.metadata.adapter.Injector;

public class FieldInjector implements Injector {

    private final IFieldMetadata fieldMetadata;

    public FieldInjector(IFieldMetadata fieldMetadata) {
        this.fieldMetadata = fieldMetadata;
    }

    @Override
    public void inject(Object instance, Object... values) throws InjectException {
        // assert that we only have one value
        if (values.length != 1) {
            throw new InjectException("Invalid injection value : " + values + "' for instance '" + instance + "'.");
        }


        ClassLoader classLoader = instance.getClass().getClassLoader();

        // load the class of the field
        Class<?> fieldClass;
        try {
            fieldClass = classLoader.loadClass(fieldMetadata.getClassMetadata().getClassName());
        } catch (ClassNotFoundException e) {
            throw new InjectException("Cannot load class", e);
        }

        // get the field name
        Field field;
        try {
            field = fieldClass.getDeclaredField(fieldMetadata.getMember().getName());
        } catch (NoSuchFieldException | SecurityException e) {
            throw new InjectException("Cannot get field from the class", e);
        }

        field.setAccessible(true);

        // inject value
        Object value = values[0];

        // sets the value
        try {
            field.set(instance, value);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new InjectException("Cannot set instance in the field", e);
        }





    }

}
