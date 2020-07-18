package com.tngtech.archunit.core.importer.testexamples.genericclasses;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class ClassWithComplexTypeParametersWithGenericArrayBounds<
        X extends Serializable,
        Y extends String,
        A extends List<X[]>,
        B extends List<? extends X[][]>,
        C extends Map<? super Y[], Map<Map<? super Y[][][], ?>, X[][]>>
        > {
}
