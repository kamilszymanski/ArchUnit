package com.tngtech.archunit.core.importer.testexamples.genericclasses;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unused")
public class ClassWithSingleTypeParameterBoundByTypeWithWildcardWithUpperInterfaceBound<T extends List<? extends Serializable>> {
}
