package com.tngtech.archunit.core.importer.testexamples.generics;

import java.util.List;

@SuppressWarnings("unused")
public class ClassWithSingleTypeParameterBoundByTypeWithWildcardWithLowerClassBound<T extends List<? super String>> {
}
