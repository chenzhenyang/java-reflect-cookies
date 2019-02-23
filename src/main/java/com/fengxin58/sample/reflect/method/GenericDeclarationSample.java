package com.fengxin58.sample.reflect.method;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;

/**
 * 
 * 
 * @author Administrator
 *
 * @param <T1>
 * @param <T2>
 */
public class GenericDeclarationSample<T1,T2> {

	public  <T3,T4> GenericDeclarationSample(T3 t3, T4 t4) {
	}
	
	
	public <T5,T6> T6 test(T5 t5){
		return null;
	}
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		TypeVariable<Class<GenericDeclarationSample>>[] classTypeParameters = GenericDeclarationSample.class.getTypeParameters();
		
		Constructor<GenericDeclarationSample> contructor = GenericDeclarationSample.class.getConstructor(Object.class,Object.class);
		TypeVariable<Constructor<GenericDeclarationSample>>[] contructorTypeParameters = contructor.getTypeParameters();
		
		Method method = GenericDeclarationSample.class.getMethod("test", Object.class);
		TypeVariable<Method>[] methodTypeParameters = method.getTypeParameters();
	}
}
