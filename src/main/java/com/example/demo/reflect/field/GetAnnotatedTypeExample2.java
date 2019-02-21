package com.example.demo.reflect.field;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.util.Arrays;

import com.example.demo.reflect.field.GetAnnotatedTypeExample2.PrimeNumber4;

public class GetAnnotatedTypeExample2<@PrimeNumber4 T> {

	@PrimeNumber1
	private @PrimeNumber2 String anInt = new @PrimeNumber3 String();

	public static void main(String... args) throws NoSuchFieldException {
		System.out.println("Example:- @PrimeNumber int anInt;");
		Field field = GetAnnotatedTypeExample2.class.getDeclaredField("anInt");
		System.out.println("Filed Annotations: " + Arrays.toString(field.getAnnotations()));
		AnnotatedType annotatedType = field.getAnnotatedType();
		printAnnotatedType(annotatedType);
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

	@Target({ ElementType.FIELD })
	@Retention(RetentionPolicy.RUNTIME)
	public @interface PrimeNumber1 {
	}

	@Target({ ElementType.TYPE_USE })
	@Retention(RetentionPolicy.RUNTIME)
	public @interface PrimeNumber2 {
	}

	@Target({ ElementType.TYPE_USE })
	@Retention(RetentionPolicy.RUNTIME)
	public @interface PrimeNumber3 {
	}

	@Target({ ElementType.TYPE_PARAMETER })
	@Retention(RetentionPolicy.RUNTIME)
	public @interface PrimeNumber4 {
	}
}