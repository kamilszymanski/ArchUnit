package com.tngtech.archunit.core.importer.testexamples.genericclasses;

import java.io.File;
import java.io.Serializable;

@SuppressWarnings("unused")
public class ClassWithMultipleTypeParametersWithGenericClassOrInterfaceBoundsAssignedToConcreteTypes<
        A extends ClassParameterWithSingleTypeParameter<File>,
        B extends InterfaceParameterWithSingleTypeParameter<Serializable>,
        C extends InterfaceParameterWithSingleTypeParameter<String>> {
}
