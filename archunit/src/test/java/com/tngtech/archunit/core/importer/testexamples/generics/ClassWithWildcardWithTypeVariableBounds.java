package com.tngtech.archunit.core.importer.testexamples.generics;

import java.util.List;

@SuppressWarnings("unused")
public class ClassWithWildcardWithTypeVariableBounds<T extends String, U extends List<? extends T>, V extends List<? super T>> {
    public class Inner<MORE_INNER extends List<? extends U>> {
        public class MoreInner<MOST_INNER1 extends List<? extends T>, MOST_INNER2 extends List<? super V>> {
        }
    }
}
