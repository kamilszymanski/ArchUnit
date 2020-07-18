package com.tngtech.archunit.core.importer.testexamples.genericclasses;

import java.io.File;
import java.io.Serializable;
import java.util.Map;

import com.tngtech.archunit.base.Function;

@SuppressWarnings("unused")
public class ClassWithTwoTypeParametersWithMultipleGenericClassAndInterfaceBoundsAssignedToConcreteTypes<
        A extends ClassParameterWithSingleTypeParameter<String> & InterfaceParameterWithSingleTypeParameter<Serializable>,
        B extends Map<String, Serializable> & Iterable<File> & Function<Integer, Long>> {
}
