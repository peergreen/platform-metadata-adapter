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
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.net.URL;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.ow2.util.scan.api.IScanner;
import org.ow2.util.scan.api.ScanException;
import org.ow2.util.scan.api.metadata.IClassMetadata;
import org.ow2.util.scan.impl.ASMScannerImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.peergreen.deployment.Artifact;
import com.peergreen.metadata.adapter.internal.BasicMetadataAdapter;
import com.peergreen.metadata.adapter.internal.PostConstructHandlerLifeCycleCallbackProcessor;
import com.peergreen.metadata.adapter.internal.PreDestroyHandlerLifeCycleCallbackProcessor;

/**
 * Test if @PostConstruct on a given method is correctly handled
 * @author Florent Benoit
 */
public class TestLifeCycleCallback {

    // instance of the class
    private MyAnnotatedClass myInstance;

    private AnnotatedClass annotatedClass;

    private IClassMetadata classMetadata;

    @Mock
    private Artifact artifact;


    @BeforeClass
    public void loadMetadata() throws ScanException {
        IScanner scanner = new ASMScannerImpl();

        URL url = TestLifeCycleCallback.class.getResource("/".concat(MyAnnotatedClass.class.getName().replace(".", "/").concat(".class")));
        MySessionConfigurator sessionConfigurator = new MySessionConfigurator();
        scanner.scanClass(url, sessionConfigurator);

        Map<String, IClassMetadata> classes = sessionConfigurator.getClasses();

        // Only one class
        assertEquals(classes.size(), 1);

        this.classMetadata = classes.get(MyAnnotatedClass.class.getName());
        assertNotNull(classMetadata);
    }


    @BeforeClass(dependsOnMethods="loadMetadata")
    public void testProcessors() throws InjectException {
        MockitoAnnotations.initMocks(this);
        this.myInstance = new MyAnnotatedClass();
        BasicMetadataAdapter basicMetadataAdapter = new BasicMetadataAdapter();

        basicMetadataAdapter.bindLifeCycleProcessor(new PostConstructHandlerLifeCycleCallbackProcessor());
        basicMetadataAdapter.bindLifeCycleProcessor(new PreDestroyHandlerLifeCycleCallbackProcessor());

        this.annotatedClass = basicMetadataAdapter.adapt(artifact, classMetadata);

    }

    @Test
    public void checkPostConstruct() throws LifeCycleCallbackException {

        // It has post construct
        assertTrue(this.annotatedClass.hasLifeCycleCallBack(PostConstruct.class.getName()));

        // check value before
        String valPostConstruct = myInstance.getPostConstructSetValue();
        assertNull(valPostConstruct);

        // call
        this.annotatedClass.callback(PostConstruct.class.getName(), myInstance);

        valPostConstruct = myInstance.getPostConstructSetValue();
        assertNotNull(valPostConstruct);
        assertEquals(valPostConstruct, "CALLED");
    }

    @Test
    public void checkPreDestroy() throws LifeCycleCallbackException {

        // It has pre destroy
        assertTrue(this.annotatedClass.hasLifeCycleCallBack(PreDestroy.class.getName()));

        // check value before
        String valPreDestroy = myInstance.getPreDestroySetValue();
        assertNull(valPreDestroy);

        // call
        this.annotatedClass.callback(PreDestroy.class.getName(), myInstance);

        valPreDestroy = myInstance.getPreDestroySetValue();
        assertNotNull(valPreDestroy);
        assertEquals(valPreDestroy, "CALLED");


    }


}
