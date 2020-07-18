package com.tngtech.archunit.core.importer.testexamples.genericclasses;

import java.io.File;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.util.Map;

@SuppressWarnings("unused")
public class ClassWithMultipleTypeParametersBoundByTypesWithDifferentBounds<A extends Map<? extends Serializable, ? super File>, B extends Reference<? super String> & Map<?, ?>> {
}
