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

import org.ow2.util.scan.api.metadata.IMetadata;
import org.ow2.util.scan.api.metadata.structures.IAnnotation;
import org.ow2.util.scan.api.metadata.structures.IMember;

import com.peergreen.deployment.Artifact;
import com.peergreen.metadata.adapter.Binding;
import com.peergreen.metadata.adapter.InjectionContext;

public class BasicInjectionPoint implements InjectionContext {

    private Artifact artifact;

    private IMember member;

    private IMetadata metadata;

    private IAnnotation annotation;


    @Override
    public Artifact getArtifact() {
        return artifact;
    }

    @Override
    public IMember getMember() {
        return member;
    }

    @Override
    public IMetadata getMetadata() {
        return metadata;
    }

    @Override
    public IAnnotation getAnnotation() {
        return annotation;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }

    public void setMember(IMember member) {
        this.member = member;
    }

    public void setMetadata(IMetadata metadata) {
        this.metadata = metadata;
    }

    public void setAnnotation(IAnnotation annotation) {
        this.annotation = annotation;
    }

    @Override
    public <T> Binding<T> createBinding(final String name, final T value) {
        return new Binding<T>() {

            @Override
            public String name() {
                return name;
            }

            @Override
            public T value() {
                return value;
            }

        };
    }

}
