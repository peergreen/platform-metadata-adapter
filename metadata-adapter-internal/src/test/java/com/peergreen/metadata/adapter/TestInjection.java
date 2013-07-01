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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mockito.Mock;
import org.ow2.util.scan.api.IScanner;
import org.ow2.util.scan.api.ScanException;
import org.ow2.util.scan.api.metadata.IClassMetadata;
import org.ow2.util.scan.impl.ASMScannerImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.peergreen.deployment.Artifact;
import com.peergreen.metadata.adapter.internal.BasicMetadataAdapter;

public class TestInjection {

   private IClassMetadata classMetadata;

   // instance of the class
   private MyAnnotatedClass myAnnotatedInstance;

   @Mock
   private Artifact artifact;

   private AnnotatedClass annotatedClass;

   private Map<String, Object> jndi;


    @BeforeClass
    public void loadMetadata() throws ScanException {
        IScanner scanner = new ASMScannerImpl();

        URL url = TestInjection.class.getResource("/".concat(MyAnnotatedClass.class.getName().replace(".", "/").concat(".class")));
        MySessionConfigurator sessionConfigurator = new MySessionConfigurator();
        scanner.scanClass(url, sessionConfigurator);

        Map<String, IClassMetadata> classes = sessionConfigurator.getClasses();

        // Only one class
        assertEquals(classes.size(), 1);

        this.classMetadata = classes.get(MyAnnotatedClass.class.getName());
        assertNotNull(classMetadata);
    }

    @BeforeClass(dependsOnMethods="loadMetadata")
    public void getInjection() throws InjectException {
        this.jndi = new HashMap<>();
        this.myAnnotatedInstance = new MyAnnotatedClass();
        BasicMetadataAdapter metadataAdapter = new BasicMetadataAdapter();

        metadataAdapter.bindInjectionProcessor(new WrappedProcessor(new MyInjectionProcessor()));

        this.annotatedClass  = metadataAdapter.adapt(artifact, classMetadata);
        List<AnnotatedMember> annotatedMembers = this.annotatedClass.entries();
        for (AnnotatedMember annotatedMember : annotatedMembers) {
            if (annotatedMember.hasInjection()) {
                annotatedMember.inject(myAnnotatedInstance);
            }
            for (Binding<?> binding : annotatedMember.getBindings()) {
                this.jndi.put(binding.name(), binding.value());
            }
        }

    }

    @Test
    public void testClassBinding() {
        Object value = this.jndi.get(MyAnnotatedClass.class.getName());
        assertNotNull(value);
        assertEquals(value, "annotatedOnClass");
    }

    @Test
    public void testField1Binding() {
        Object value = this.jndi.get(MyAnnotatedClass.class.getName().concat("/field1"));
        assertNotNull(value);
        assertEquals(value, "valueOnField1");
    }

    @Test
    public void testField2Binding() {
        Object value = this.jndi.get(MyAnnotatedClass.class.getName().concat("/field2"));
        assertNotNull(value);
        assertEquals(value, "valueOnField2");
    }

    @Test
    public void testMethod1Binding() {
        Object value = this.jndi.get(MyAnnotatedClass.class.getName().concat("/setVal1"));
        assertNotNull(value);
        assertEquals(value, "valueOnMethod1");
    }

    @Test
    public void testMethod2Binding() {
        Object value = this.jndi.get(MyAnnotatedClass.class.getName().concat("/setVal2"));
        assertNotNull(value);
        assertEquals(value, "valueOnMethod2");
    }

    @Test
    public void testField1Injection() {
        assertEquals(myAnnotatedInstance.getField1(), "valueOnField1");
    }

    @Test
    public void testField2Injection() {
        assertEquals(myAnnotatedInstance.getField2(), "valueOnField2");
    }

    @Test
    public void testMethod1Injection() {
        assertEquals(myAnnotatedInstance.getMethod1(), "valueOnMethod1");
    }

    @Test
    public void testMethod2Injection() {
        assertEquals(myAnnotatedInstance.getMethod2(), "valueOnMethod2");
    }





}
