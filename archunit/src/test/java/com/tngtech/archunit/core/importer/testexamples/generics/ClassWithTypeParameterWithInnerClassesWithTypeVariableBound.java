package com.tngtech.archunit.core.importer.testexamples.generics;

@SuppressWarnings("unused")
public class ClassWithTypeParameterWithInnerClassesWithTypeVariableBound<U extends T, T extends String> {
    @SuppressWarnings("InnerClassMayBeStatic")
    public class SomeInner {
        public class EvenMoreInnerDeclaringOwn<V extends U, MORE_INNER1, MORE_INNER2 extends U> {
            public class AndEvenMoreInner<MOST_INNER1 extends T, MOST_INNER2 extends MORE_INNER2> {
            }
        }
    }
}
