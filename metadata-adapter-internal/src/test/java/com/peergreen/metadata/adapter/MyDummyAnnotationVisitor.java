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

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import org.ow2.util.scan.api.IAnnotationVisitorContext;
import org.ow2.util.scan.api.annotation.VisitorTarget;
import org.ow2.util.scan.api.annotation.VisitorType;
import org.ow2.util.scan.api.visitor.DefaultAnnotationVisitor;

@VisitorTarget({TYPE, FIELD, METHOD})
@VisitorType("com.peergreen.metadata.adapter.DummyAnnotation")
public class MyDummyAnnotationVisitor extends DefaultAnnotationVisitor<String> {

    /**
     * Empty visit method.
     * @param name annotation name
     * @param value annotation value
     */
    @Override
    public void visit(final String name, final Object value, IAnnotationVisitorContext<String> annotationVisitorContext) {
        annotationVisitorContext.setProperty(DummyAnnotation.class.getName(), (String) value);
    }

    @Override
    public String buildInstance() {
        return null;
    }
}
