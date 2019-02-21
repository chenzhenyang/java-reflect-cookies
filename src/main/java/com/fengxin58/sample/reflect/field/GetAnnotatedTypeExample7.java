package com.fengxin58.sample.reflect.field;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedArrayType;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.AnnotatedTypeVariable;
import java.lang.reflect.Field;
import java.util.Arrays;

public class GetAnnotatedTypeExample7<T extends Number> {
	private @PrimeNumber T[] number;

	public static void main(String... args) throws NoSuchFieldException {
		System.out.println("Example:- int @PrimeNumber T[] number;");
		Field field = GetAnnotatedTypeExample7.class.getDeclaredField("number");
		AnnotatedType annotatedType = field.getAnnotatedType();
		printType(annotatedType);

		if (annotatedType instanceof AnnotatedArrayType) {
			System.out.println("-- casting to AnnotatedArrayType --");
			AnnotatedArrayType arrayType = ((AnnotatedArrayType) annotatedType);
			System.out.println("-- AnnotatedArrayType#.getAnnotatedGenericComponentType() --");
			AnnotatedType annotatedComponentType = arrayType.getAnnotatedGenericComponentType();
			printType(annotatedComponentType);

			if (annotatedComponentType instanceof AnnotatedTypeVariable) {
				System.out.println("-- casting to AnnotatedTypeVariable --");
				AnnotatedTypeVariable annotatedTypeVariable = (AnnotatedTypeVariable) annotatedComponentType;
				System.out.println("-- AnnotatedTypeVariable#getAnnotatedBounds() --");
				AnnotatedType[] annotatedBounds = annotatedTypeVariable.getAnnotatedBounds();
				for (AnnotatedType annotatedBound : annotatedBounds) {
					System.out.println("-- annotatedBound --");
					printType(annotatedBound);
				}
			}
		}
	}

	private static void printType(AnnotatedType annotatedType) {
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
