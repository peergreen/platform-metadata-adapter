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

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@DummyAnnotation("annotatedOnClass")
public class MyAnnotatedClass {

    private String postConstructSetValue;
    private String preDestroySetValue;

    @DummyAnnotation("valueOnField1")
    private String field1;

    @DummyAnnotation("valueOnField2")
    private String field2;

    private String valMethod1;
    private String valMethod2;

    @DummyAnnotation("valueOnMethod1")
    protected void setVal1(String valMethod1) {
        this.valMethod1 = valMethod1;
    }

    @DummyAnnotation("valueOnMethod2")
    protected void setVal2(String valMethod2) {
        this.valMethod2 = valMethod2;
    }


    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public String getMethod1() {
        return valMethod1;
    }

    public String getMethod2() {
        return valMethod2;
    }

    @PostConstruct
    protected void myPostConstruct() {
        this.postConstructSetValue = "CALLED";
    }

    @PreDestroy
    protected void myPreDestroy() {
        this.preDestroySetValue = "CALLED";
    }

    public String getPostConstructSetValue() {
        return postConstructSetValue;
    }

    public String getPreDestroySetValue() {
        return preDestroySetValue;
    }

    public String getValMethod1() {
        return valMethod1;
    }

    public String getValMethod2() {
        return valMethod2;
    }

}
