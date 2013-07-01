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

import java.util.HashMap;
import java.util.Map;

import org.ow2.util.scan.api.configurator.IAnnotationConfigurator;
import org.ow2.util.scan.api.configurator.IClassConfigurator;
import org.ow2.util.scan.api.configurator.basic.AnnotationConfigurator;
import org.ow2.util.scan.api.configurator.basic.SessionConfigurator;
import org.ow2.util.scan.api.metadata.IClassMetadata;

public class MySessionConfigurator extends SessionConfigurator {

    private final Map<String, IClassMetadata> classes;

    private final IAnnotationConfigurator annotationConfigurator;

    public MySessionConfigurator() {
        super();
        this.classes = new HashMap<>();
        this.annotationConfigurator = new AnnotationConfigurator();
        annotationConfigurator.registerAnnotationVisitor(new MyDummyAnnotationVisitor());
        getAnnotationConfigurators().add(annotationConfigurator);
    }

    @Override
    public IClassConfigurator createClassConfigurator(IClassMetadata classMetadata) {
        addClassMetadata(classMetadata);
        return super.createClassConfigurator(classMetadata);
    }


    /**
     * Add annotation metadata for a given class.
     * @param classMetadata metadata of a class.
     */
    public void addClassMetadata(final IClassMetadata classMetadata) {
        String key = classMetadata.getClassName();
        // already exists ?
        if (classes.containsKey(key)) {
            throw new IllegalStateException("addClassMetadata : key already exists");
        }
        classes.put(key, classMetadata);
    }


    public Map<String, IClassMetadata> getClasses() {
        return classes;
    }

}
