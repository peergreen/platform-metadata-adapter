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

import java.util.ArrayList;
import java.util.List;

import org.ow2.util.scan.api.metadata.structures.IMember;

import com.peergreen.metadata.adapter.AnnotatedMember;
import com.peergreen.metadata.adapter.Binding;
import com.peergreen.metadata.adapter.InjectException;
import com.peergreen.metadata.adapter.Injector;

public class BasicAnnotatedMember implements AnnotatedMember {

    private Injector injector;

    private List<Binding<?>> bindings;

    private IMember member;

    public BasicAnnotatedMember() {
        this.bindings = new ArrayList<>();
    }

    public IMember getMember() {
        return member;
    }

    public void setMember(IMember member) {
        this.member = member;
    }

    @Override
    public List<Binding<?>> getBindings() {
        return bindings;
    }

    public void setBindings(List<Binding<?>> bindings) {
        this.bindings = bindings;
    }

    @Override
    public boolean hasInjection() {
        return injector != null;
    }

    public void setInjector(Injector injector) {
        this.injector = injector;
    }


    @Override
    public void inject(Object instance) throws InjectException {
        Object[] values = new Object[bindings.size()];
        int i =0;
        for (Binding<?> binding : bindings) {
            values[i++] = binding.value();
        }
        inject(instance, values);

    }

    @Override
    public void inject(Object instance, Object... value) throws InjectException {
        if (injector != null) {
            injector.inject(instance, value);
        }
    }

}
