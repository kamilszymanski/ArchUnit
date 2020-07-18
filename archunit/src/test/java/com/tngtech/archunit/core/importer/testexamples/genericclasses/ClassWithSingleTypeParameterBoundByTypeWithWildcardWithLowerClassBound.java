package com.tngtech.archunit.core.importer.testexamples.genericclasses;

import java.util.List;

@SuppressWarnings("unused")
public class ClassWithSingleTypeParameterBoundByTypeWithWildcardWithLowerClassBound<T extends List<? super String>> {
}
