package com.tngtech.archunit.core.importer.testexamples.generics;

@SuppressWarnings("unused")
public class ClassWithSingleTypeParameterWithGenericClassBoundAssignedToConcreteClass<T extends ClassParameterWithSingleTypeParameter<String>> {
}
