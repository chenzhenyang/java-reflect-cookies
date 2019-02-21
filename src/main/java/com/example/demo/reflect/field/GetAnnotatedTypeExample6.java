package com.example.demo.reflect.field;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.AnnotatedTypeVariable;
import java.lang.reflect.Field;
import java.util.Arrays;

public class GetAnnotatedTypeExample6<T extends Number> {
	
	private @PrimeNumber T number;

	public static void main(String... args) throws NoSuchFieldException {
		System.out.println("Example:- @PrimeNumber T number;");
		Field field = GetAnnotatedTypeExample6.class.getDeclaredField("number");
		AnnotatedType annotatedType = field.getAnnotatedType();
		printAnnotatedType(annotatedType);

		if (annotatedType instanceof AnnotatedTypeVariable) {
			System.out.println("-- casting to AnnotatedTypeVariable --");
			AnnotatedTypeVariable annotatedTypeVariable = (AnnotatedTypeVariable) annotatedType;
			AnnotatedType[] annotatedBounds = annotatedTypeVariable.getAnnotatedBounds();
			System.out.println("-- AnnotatedTypeVariable.getAnnotatedBounds() --");
			for (AnnotatedType annotatedBound : annotatedBounds) {
				System.out.println("-- annotatedBound --");
				printAnnotatedType(annotatedBound);
			}
		}
	}

	private static void printAnnotatedType(AnnotatedType annotatedType) {
		System.out.println("Type: " + annotatedType.getType().getTypeName());
		System.out.println("Annotations: " + Arrays.toString(annotatedType.getAnnotations()));
		System.out.println("Declared Annotations: " + Arrays.toString(annotatedType.getDeclaredAnnotations()));
		AnnotatedType annotatedOwnerType = annotatedType.getAnnotatedOwnerType();// Java 9
		System.out.println("Annotated owner type: " + annotatedOwnerType);
		System.out.println("AnnotatedType class: " + annotatedType.getClass().getName());
		System.out.println("AnnotatedType class implementing interfaces: "
				+ Arrays.toString(annotatedType.getClass().getInterfaces()));
	}

	@Target({ ElementType.TYPE_USE })
	@Retention(RetentionPolicy.RUNTIME)
	private @interface PrimeNumber {
	}
}