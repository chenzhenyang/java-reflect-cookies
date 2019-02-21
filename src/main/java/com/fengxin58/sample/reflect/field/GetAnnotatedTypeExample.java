package com.fengxin58.sample.reflect.field;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.util.Arrays;

public class GetAnnotatedTypeExample {

	private int anInt;

	public static void main(String... args) throws NoSuchFieldException {
		Field field = GetAnnotatedTypeExample.class.getDeclaredField("anInt");
		AnnotatedType annotatedType = field.getAnnotatedType();
		System.out.println(annotatedType);
		
		printAnnotatedType(annotatedType);
	}

	private static void printAnnotatedType(AnnotatedType annotatedType) {
		System.out.println("Type: " + annotatedType.getType().getTypeName());
		System.out.println("Annotations: " + Arrays.toString(annotatedType.getAnnotations()));
		System.out.println("Declared Annotations: " + Arrays.toString(annotatedType.getDeclaredAnnotations()));
		AnnotatedType annotatedOwnerType = annotatedType.getAnnotatedOwnerType();
		System.out.println("Annotated owner type: " + annotatedOwnerType);
		System.out.println("AnnotatedType class: " + annotatedType.getClass().getName());
		System.out.println("AnnotatedType class implementing interfaces: "
				+ Arrays.toString(annotatedType.getClass().getInterfaces()));
	}
}
