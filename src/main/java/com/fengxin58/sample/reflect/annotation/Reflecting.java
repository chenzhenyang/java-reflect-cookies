package com.fengxin58.sample.reflect.annotation;

import java.io.InvalidClassException;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.nio.InvalidMarkException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Location
public class Reflecting<@Location A extends Comparable<String> & Serializable, @Location B>
		implements @Location Serializable {

	@Location
	private A a;

	@Location
	private B b;

	@Location
	private List<@Location ? extends Map<@Location String, String>> list;

	private List<@Location A> t;

	@Location
	private List<@Location ? super Map<String, String>> list2;

	private List<@Location String> emails;

	@Location
	public Reflecting() {	

	}

	@Location
	public Reflecting(@Location  A a, @Location B b)
			throws @Location InvalidClassException, InvalidMarkException {
		@Location
		String str = "test";
		this.a = a;
		this.b = b;
	}

	public <T1, T2 extends List<String>> void print(T1 x, T2[] ta) {
		System.out.println(x.getClass().getName());
	}

	/**
	 * 展示WildcardType的用法<br>
	 * 
	 * @param a
	 */
	public <T> void test(List<? extends Serializable> a) {
	}

	/**
	 * 展示ParameterizedType<br>
	 * 
	 * @param a
	 */
	public void parameterizedType(List<ArrayList<String>[]> a) {
	}

	/**
	 * 打印Class上的TypeVariable信息；<br>
	 */
	private static void displayTypeVariable(TypeVariable<?> typeVariable) {
		System.out.println("TypeVariable Name:" + typeVariable.getName());
		System.out.println("TypeVariable GenericDeclaration:" + typeVariable.getGenericDeclaration());
		Type[] types = typeVariable.getBounds();
		for (int j = 0; j < types.length; j++) {
			Type type = types[j];
			System.out.println(String.format("Bound%s:%s", j, type));
		}
	}

	private static void printClassTypeVariable() {
		Reflecting<?, ?> t = new Reflecting<>();
		Class<? extends Reflecting> tc = t.getClass();
		TypeVariable<?>[] tv = tc.getTypeParameters();
		for (int i = 0; i < tv.length; i++) {
			TypeVariable<?> typeVariable = tv[i];
			displayTypeVariable(typeVariable);
		}
	}

	private static void printConstructorTypeVariable() throws NoSuchMethodException, SecurityException {
		Constructor<Reflecting> constructor = Reflecting.class.getDeclaredConstructor();

	}

	private static void printMethodTypeVariable() {

	}

	private static void displayParameterizedType(ParameterizedType parameterizedType) {
		System.out.println(String.format("TypeName:%s", parameterizedType.getTypeName()));
		System.out.println(String.format("RawType:%s", parameterizedType.getRawType()));
		System.out.println(String.format("OwnerType:%s", parameterizedType.getOwnerType()));
		Type[] arguments = parameterizedType.getActualTypeArguments();
		for (int j = 0; j < arguments.length; j++) {
			Type t = arguments[j];
			System.out.println(String.format("ActualTypeArguments[%s]:%s", j, t));
		}
	}

	private static void printParameterizedType() {
		Map<Integer, String> maps = new HashMap<>();
		ParameterizedType parameterizedType = (ParameterizedType) maps.getClass().getGenericSuperclass();
		displayParameterizedType(parameterizedType);
	}

	private static void displayConstructor(Constructor constructor) {
		int modifiers = constructor.getModifiers();
		Annotation[] annotations = constructor.getAnnotations();
		for (int i = 0; i < annotations.length; i++) {
			Annotation annotation = annotations[i];
			System.out.println(annotation.toString());
		}
		System.out.print(String.format("%s %s", Modifier.toString(modifiers), constructor.getName()));

		System.out.print("(");
		Parameter[] paramaters = constructor.getParameters();
		for (int i = 0; i < paramaters.length; i++) {
			Parameter parameter = paramaters[i];

			Annotation[] parameterAnnotations = parameter.getAnnotations();
			for (int j = 0; j < parameterAnnotations.length; j++) {
				Annotation parameterAnnotation = parameterAnnotations[j];
				System.out.print(parameterAnnotation.toString() + " ");
			}

			System.out.print(parameter.getParameterizedType() + " ");
			System.out.print(parameter.getName() + " ");

			if (i < paramaters.length - 1) {
				System.out.print(" , ");
			}
		}
		System.out.print(")");

		Class<?>[] exceptions = constructor.getExceptionTypes();
		if (exceptions.length > 0) {
			System.out.print(" throws ");
		}
		for (int i = 0; i < exceptions.length; i++) {
			Class<?> clazz = exceptions[i];
			System.out.print(clazz);
			if (i < exceptions.length - 1) {
				System.out.print(",");
			}
		}
	}

	public static void displayAnnotatedParameterizedType() {

	}

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, NoSuchFieldException {
//		Constructor<?>[] constructors = Reflecting.class.getConstructors();
//		for (int i = 0; i < constructors.length; i++) {
//			Constructor<?> constructor = constructors[i];
//			displayConstructor(constructor);	
//		}

		Field emailAddressField = Reflecting.class.getDeclaredField("emails");
		// the field's type is both parameterized and annotated,
		// cast it to the right type representation
		AnnotatedParameterizedType annotatedParameterizedType = (AnnotatedParameterizedType) emailAddressField
				.getAnnotatedType();

		// get all type parameters
		AnnotatedType[] annotatedActualTypeArguments = annotatedParameterizedType.getAnnotatedActualTypeArguments();

		// the String parameter which contains the annotation
		AnnotatedType stringParameterType = annotatedActualTypeArguments[0];

		// The actual annotation
		Annotation emailAnnotation = stringParameterType.getAnnotations()[0];

		System.out.println(emailAnnotation); // @Email()

	}

}
