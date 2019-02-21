package com.fengxin58.sample.reflect.field;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PACKAGE;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.TYPE_PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.AnnotatedTypeVariable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GetAnnotatedTypeExample9<T extends Number, L extends List> {

	private Map<@AnnotationAnyWhere T, L> numbers;

	public static void main(String... args) throws NoSuchFieldException {
		System.out.println("Example:- Map<@PrimeNumber T, L> numbers;");
		Field field = GetAnnotatedTypeExample9.class.getDeclaredField("numbers");
		AnnotatedType annotatedType = field.getAnnotatedType();
		printAnnotatedType(annotatedType);
		if (annotatedType instanceof AnnotatedParameterizedType) {
			System.out.println("-- casting to AnnotatedParameterizedType --");
			
			AnnotatedParameterizedType parameterizedType = (AnnotatedParameterizedType) annotatedType;
			
			System.out.println("-- AnnotatedParameterizedType#getAnnotatedActualTypeArguments() --");
			AnnotatedType[] actualTypeArguments = parameterizedType.getAnnotatedActualTypeArguments();
			
			for (AnnotatedType actualTypeArgument : actualTypeArguments) {
				System.out.println("-- actualTypeArgument --");
				printAnnotatedType(actualTypeArgument);
				
				if (actualTypeArgument instanceof AnnotatedTypeVariable) {
					System.out.println("-- casting to AnnotatedTypeVariable --");
					
					AnnotatedTypeVariable annotatedTypeVariable = (AnnotatedTypeVariable) actualTypeArgument;
					
					System.out.println("-- AnnotatedTypeVariable#getAnnotatedBounds() --");
					
					AnnotatedType[] annotatedBounds = annotatedTypeVariable.getAnnotatedBounds();
					for (AnnotatedType annotatedBound : annotatedBounds) {
						System.out.println("-- annotatedBound --");
						printAnnotatedType(annotatedBound);
					}
				}
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

		if (annotatedOwnerType != null) {
			System.out.println("-- annotatedOwnerType --");
			printAnnotatedType(annotatedOwnerType);
		}
	}

	@Documented
	@Retention(RetentionPolicy.RUNTIME)
	@Target(value = { TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE,
			TYPE_PARAMETER, TYPE_USE })
	private @interface PrimeNumberL {
	}

	@Documented
	@Retention(RetentionPolicy.RUNTIME)
	@Target(value = { TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE,
			TYPE_PARAMETER, TYPE_USE })
	private @interface AnnotationAnyWhere {
	}
}