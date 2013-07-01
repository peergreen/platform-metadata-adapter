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
import org.mockito.MockitoAnnotations;
import org.osgi.framework.BundleContext;
import org.ow2.util.scan.api.IScanner;
import org.ow2.util.scan.api.ScanException;
import org.ow2.util.scan.api.metadata.IClassMetadata;
import org.ow2.util.scan.api.metadata.IFieldMetadata;
import org.ow2.util.scan.api.metadata.IMethodMetadata;
import org.ow2.util.scan.impl.ASMScannerImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.peergreen.deployment.Artifact;
import com.peergreen.metadata.adapter.internal.BasicMetadataAdapter;
import com.peergreen.metadata.adapter.internal.ResourceBundleContextHandlerInjectionProcessor;

/**
 * Test if @Resource on a BundleContext is correctly handled
 * @author Florent Benoit
 */
public class TestBundleContextInjection {

    // instance of the class
    private BundleContextAnnotatedClass myInstance;

    private AnnotatedClass annotatedClass;

    private Map<String, Object> jndi;

    private IClassMetadata classMetadata;

    @Mock
    private Artifact artifact;

    @Mock
    private BundleContext bundleContext;


    @BeforeClass
    public void loadMetadata() throws ScanException {
        IScanner scanner = new ASMScannerImpl();

        URL url = TestBundleContextInjection.class.getResource("/".concat(BundleContextAnnotatedClass.class.getName().replace(".", "/").concat(".class")));
        MySessionConfigurator sessionConfigurator = new MySessionConfigurator();
        scanner.scanClass(url, sessionConfigurator);

        Map<String, IClassMetadata> classes = sessionConfigurator.getClasses();

        // Only one class
        assertEquals(classes.size(), 1);

        this.classMetadata = classes.get(BundleContextAnnotatedClass.class.getName());
        assertNotNull(classMetadata);
    }


    @BeforeClass(dependsOnMethods="loadMetadata")
    public void getInjection() throws InjectException {
        MockitoAnnotations.initMocks(this);
        this.jndi = new HashMap<>();
        this.myInstance = new BundleContextAnnotatedClass();
        BasicMetadataAdapter injectionService = new BasicMetadataAdapter();

        injectionService.bindInjectionProcessor(new ResourceBundleContextHandlerInjectionProcessor(bundleContext));

        this.annotatedClass  = injectionService.adapt(artifact, classMetadata);
        List<AnnotatedMember> annotatedMembers = this.annotatedClass.entries();
        for (AnnotatedMember annotatedMember : annotatedMembers) {
            if (annotatedMember.hasInjection()) {
                annotatedMember.inject(myInstance);
            }
            for (Binding<?> binding : annotatedMember.getBindings()) {
                this.jndi.put(binding.name(), binding.value());
            }
        }

    }

    @Test
    public void checkField() {
        List<IFieldMetadata> fields = this.classMetadata.searchFieldMetadata("bundleContextField");
        assertNotNull(fields);
        assertEquals(fields.size(), 1);
        BundleContext fieldInjected = myInstance.getInjectedByField();
        assertNotNull(fieldInjected);
        assertEquals(fieldInjected, bundleContext);

    }


    @Test
    public void checkMethod() {
        List<IMethodMetadata> methods = this.classMetadata.searchMethodMetadata("setBC");
        assertNotNull(methods);
        assertEquals(methods.size(), 1);
        BundleContext methodInjected = myInstance.getInjectedByMethod();
        assertNotNull(methodInjected);
        assertEquals(methodInjected, bundleContext);

    }

}
