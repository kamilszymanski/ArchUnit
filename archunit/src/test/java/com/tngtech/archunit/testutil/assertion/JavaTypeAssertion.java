package com.tngtech.archunit.testutil.assertion;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.collect.FluentIterable;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.core.domain.JavaType;
import com.tngtech.archunit.core.domain.JavaTypeVariable;
import org.assertj.core.api.AbstractObjectAssert;
import org.objectweb.asm.Type;

import static com.google.common.base.Preconditions.checkArgument;
import static com.tngtech.archunit.base.Guava.toGuava;
import static com.tngtech.archunit.core.domain.properties.HasName.Predicates.name;
import static com.tngtech.archunit.testutil.TestUtils.namesOf;
import static com.tngtech.archunit.testutil.assertion.JavaAnnotationAssertion.propertiesOf;
import static com.tngtech.archunit.testutil.assertion.JavaAnnotationAssertion.runtimePropertiesOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.guava.api.Assertions.assertThat;

public class JavaTypeAssertion extends AbstractObjectAssert<JavaTypeAssertion, JavaType> {
    private static final Pattern ARRAY_PATTERN = Pattern.compile("(\\[+)(.*)");

    public JavaTypeAssertion(JavaType javaType) {
        super(javaType, JavaTypeAssertion.class);
    }

    public void matches(java.lang.reflect.Type type) {
        checkArgument(type instanceof Class<?>, "Only %s implemented so far, please extend", Class.class.getName());
        matches((Class<?>) type);
    }

    public void matches(Class<?> clazz) {
        JavaClass javaClass = actualClass();

        assertThat(javaClass.getName()).as(partialAssertionDescriptionPrefix() + "Name of " + javaClass)
                .isEqualTo(clazz.getName());
        assertThat(javaClass.getSimpleName()).as(partialAssertionDescriptionPrefix() + "Simple name of " + javaClass)
                .isEqualTo(ensureArrayName(clazz.getSimpleName()));
        assertThat(javaClass.getPackage().getName()).as(partialAssertionDescriptionPrefix() + "Package of " + javaClass)
                .isEqualTo(getExpectedPackageName(clazz));
        assertThat(javaClass.getPackageName()).as(partialAssertionDescriptionPrefix() + "Package name of " + javaClass)
                .isEqualTo(getExpectedPackageName(clazz));
        assertThat(javaClass.getModifiers()).as(partialAssertionDescriptionPrefix() + "Modifiers of " + javaClass)
                .isEqualTo(JavaModifier.getModifiersForClass(clazz.getModifiers()));
        assertThat(javaClass.isArray()).as(partialAssertionDescriptionPrefix() + javaClass + " is array").isEqualTo(clazz.isArray());
        assertThat(runtimePropertiesOf(javaClass.getAnnotations())).as(partialAssertionDescriptionPrefix() + "Annotations of " + javaClass)
                .isEqualTo(propertiesOf(clazz.getAnnotations()));

        if (clazz.isArray()) {
            new JavaTypeAssertion(javaClass.getComponentType())
                    .as("%sComponent type of %s: ", partialAssertionDescriptionPrefix(), javaClass.getSimpleName())
                    .matches(clazz.getComponentType());
        }
    }

    private String partialAssertionDescriptionPrefix() {
        return !Strings.isNullOrEmpty(descriptionText()) ? descriptionText() + ": " : "";
    }

    public JavaTypeAssertion hasTypeParameters(String... names) {
        assertThat(namesOf(actualClass().getTypeParameters())).as("names of type parameters").containsExactly(names);
        return this;
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent") // checked via AssertJ
    public JavaTypeVariableOfClassAssertion hasTypeParameter(String name) {
        List<JavaTypeVariable> typeVariables = actualClass().getTypeParameters();

        Optional<JavaTypeVariable> variable = FluentIterable.from(typeVariables).firstMatch(toGuava(name(name)));
        assertThat(variable).as("Type variable with name '%s'", name).isPresent();

        return new JavaTypeVariableOfClassAssertion(variable.get(), this);
    }

    public JavaTypeVariableOfClassAssertion hasOnlyTypeParameter(String name) {
        assertThat(actualClass().getTypeParameters()).as("Type parameters").hasSize(1);
        return hasTypeParameter(name);
    }

    private JavaClass actualClass() {
        return actual instanceof JavaClass ? (JavaClass) actual : actual.toErasure();
    }

    private String ensureArrayName(String name) {
        String suffix = "";
        Matcher matcher = ARRAY_PATTERN.matcher(name);
        if (matcher.matches()) {
            name = Type.getType(matcher.group(2)).getClassName();
            suffix = Strings.repeat("[]", matcher.group(1).length());
        }
        return name + suffix;
    }

    public static String getExpectedPackageName(Class<?> clazz) {
        if (!clazz.isArray()) {
            return clazz.getPackage() != null ? clazz.getPackage().getName() : "";
        }
        return getExpectedPackageName(clazz.getComponentType());
    }
}
