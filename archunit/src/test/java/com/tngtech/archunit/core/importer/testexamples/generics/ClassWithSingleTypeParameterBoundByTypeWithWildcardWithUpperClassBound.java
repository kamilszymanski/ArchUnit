package com.tngtech.archunit.core.importer.testexamples.generics;

import java.util.List;

@SuppressWarnings("unused")
public class ClassWithSingleTypeParameterBoundByTypeWithWildcardWithUpperClassBound<T extends List<? extends String>> {
}
