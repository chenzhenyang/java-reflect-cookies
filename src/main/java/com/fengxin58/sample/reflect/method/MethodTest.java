package com.fengxin58.sample.reflect.method;

import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

public class MethodTest {

	@Documented
	@Retention(RetentionPolicy.RUNTIME)
	@Target(value = { METHOD, PARAMETER, LOCAL_VARIABLE, TYPE_USE })
	@Repeatable(Schedules.class)
	private @interface Schedule {
		String value();
	}

	@Target(value = { METHOD, PARAMETER, LOCAL_VARIABLE, TYPE_USE })
	@Documented
	@Retention(RetentionPolicy.RUNTIME)
	private @interface Schedules {
		Schedule[] value();
	}

	@Schedule("before method")
	public @Schedule("before return type") String test(@Schedule("receiver parameters") MethodTest this,
			@Schedule("before method parameter1") String a1, @Schedule("before method parameter2") String a2)
			throws @Schedule("before throws exception") IndexOutOfBoundsException {
		System.out.println(this.toString());

		@Schedule("before local variable")
		String str = "str";

		return str;
	}

	public <E extends Exception,T1,T2,TR extends ArrayList<String>> TR testGeneric(MethodTest this, T1 a1, T2 a2) throws IndexOutOfBoundsException,E {
		return (TR) new ArrayList<String>();
	}

	private static void displayAnnotations(Annotation annotation) {
		System.out.println(annotation);
	}

	private static void displayAnnotatedType(AnnotatedType annotatedType) {
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
			displayAnnotatedType(annotatedOwnerType);
		}
	}

	private static void displayClass(Class<?> clazz) {
		System.out.println(clazz);
	}

	private static void displayParameter(Parameter p) {
		System.out.println(p);
	}

	private static void printMethodAllAnnotation() throws NoSuchMethodException, SecurityException {
		Method m = MethodTest.class.getMethod("test", String.class, String.class);

		// Annotation on Method
		Annotation[] annotations = m.getAnnotations();
		for (int i = 0; i < annotations.length; i++) {
			Annotation annotation = annotations[i];
			displayAnnotations(annotation);
		}

		// Annotated Method Receiver Parameters
		AnnotatedType annotatedReceiverType = m.getAnnotatedReceiverType();
		displayAnnotatedType(annotatedReceiverType);

		// Annotated Method Parameters
		Parameter[] parameters = m.getParameters();
		for (int i = 0; i < parameters.length; i++) {
			Parameter parameter = parameters[i];
			displayParameter(parameter);
		}

		AnnotatedType[] annotatedParameterTypes = m.getAnnotatedParameterTypes();
		for (int i = 0; i < annotatedParameterTypes.length; i++) {
			AnnotatedType annotatedType = annotatedParameterTypes[i];
			displayAnnotatedType(annotatedType);
		}

		// throws exceptions
		Class<?>[] exceptionTypes = m.getExceptionTypes();
		for (int i = 0; i < exceptionTypes.length; i++) {
			Class<?> class1 = exceptionTypes[i];
			displayClass(class1);
		}

		AnnotatedType[] et = m.getAnnotatedExceptionTypes();
		for (int i = 0; i < et.length; i++) {
			AnnotatedType annotatedType = et[i];
			displayAnnotatedType(annotatedType);
		}

		// AnnotatedReturnType
		System.out.println("--------------------------AnnotatedReturnType------------------------------");
		AnnotatedType annotatedReturnType = m.getAnnotatedReturnType();
		displayAnnotatedType(annotatedReturnType);

	}

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		// printMethodAllAnnotation();
		Method m = MethodTest.class.getMethod("testGeneric", Object.class,Object.class );
		Type[] genericParameterTypes = m.getGenericParameterTypes();
		for (int i = 0; i < genericParameterTypes.length; i++) {
			Type type = genericParameterTypes[i];
			System.out.println(type.getClass());
		}

		Type[] genericExceptionTypes = m.getGenericExceptionTypes();
		for (int i = 0; i < genericExceptionTypes.length; i++) {
			Type type = genericExceptionTypes[i];
			System.out.println(type);
		}

		Type genericReturnType = m.getGenericReturnType();
		System.out.println(genericReturnType);

	}

}
