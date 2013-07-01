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

import org.ow2.util.scan.api.metadata.IMethodMetadata;
import org.ow2.util.scan.api.metadata.structures.IAnnotation;
import org.ow2.util.scan.api.metadata.structures.IMethod;

import com.peergreen.deployment.Artifact;
import com.peergreen.metadata.adapter.LifeCycleCallbackContext;
import com.peergreen.metadata.adapter.internal.executor.MethodExecutor;

public class BasicLifeCycleContext implements LifeCycleCallbackContext {

    private Artifact artifact;

    private IMethod method;

    private IMethodMetadata metadata;

    private IAnnotation annotation;

    private final BasicAnnotatedClass annotatedClass;

    public BasicLifeCycleContext(BasicAnnotatedClass annotatedClass) {
        this.annotatedClass = annotatedClass;
    }

    @Override
    public Artifact getArtifact() {
        return artifact;
    }

    @Override
    public IMethod getMethod() {
        return method;
    }

    public void setMethod(IMethod method) {
        this.method = method;
    }

    @Override
    public IMethodMetadata getMetadata() {
        return metadata;
    }

    @Override
    public IAnnotation getAnnotation() {
        return annotation;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }


    public void setMetadata(IMethodMetadata metadata) {
        this.metadata = metadata;
    }

    public void setAnnotation(IAnnotation annotation) {
        this.annotation = annotation;
    }


    public void addCallback() {
        annotatedClass.addCallback(annotation.getClassName(), new MethodExecutor(metadata));
    }


}
