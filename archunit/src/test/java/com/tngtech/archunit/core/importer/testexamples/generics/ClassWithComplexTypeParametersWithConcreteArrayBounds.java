package com.tngtech.archunit.core.importer.testexamples.generics;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class ClassWithComplexTypeParametersWithConcreteArrayBounds<
        A extends List<Serializable[]>,
        B extends List<? extends Serializable[][]>,
        C extends Map<? super String[], Map<Map<? super String[][][], ?>, Serializable[][]>>
        > {
}
