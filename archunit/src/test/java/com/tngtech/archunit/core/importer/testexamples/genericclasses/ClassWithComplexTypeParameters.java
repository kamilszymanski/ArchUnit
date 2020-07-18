package com.tngtech.archunit.core.importer.testexamples.genericclasses;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("unused")
public class ClassWithComplexTypeParameters<
        A extends List<?> & Serializable & Comparable<A>,
        B extends A,
        C extends Map<
                Map.Entry<A, Map.Entry<String, B>>,
                Map<? extends String,
                        Map<? extends Serializable, List<List<? extends Set<? super Iterable<? super Map<B, ?>>>>>>>>,
        SELF extends ClassWithComplexTypeParameters<A, B, C, SELF, D>,
        D> {
}
