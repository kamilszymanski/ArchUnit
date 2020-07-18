package com.tngtech.archunit.core.importer.testexamples.genericclasses;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unused")
public class ClassWithSingleTypeParameterBoundByTypeWithWildcardWithLowerInterfaceBound<T extends List<? super Serializable>> {
}
