package com.fengxin58.sample.reflect.annotation;

import java.io.InvalidClassException;
import java.io.Serializable;
import java.nio.InvalidMarkException;
import java.util.List;
import java.util.Map;

@Location
public class AnnotationLocationDisplayer<@Location A extends Comparable<String> & Serializable, @Location B>
		implements @Location Serializable {

	private static final long serialVersionUID = 1L;

	@Location
	private @Location A a;
	@Location
	private @Location A[] as;
	@Location
	private @Location A @Location [] as2;
	@Location
	private @Location A @Location [] @Location [] ass1;
	@Location
	private @Location B b;
	@Location
	private List<@Location ? extends Map<@Location String, String>> list;
	private List<@Location A> t;
	@Location
	private List<@Location ? super Map<String, String>> list2;
	private List<@Location String> emails;
	@Location
	public AnnotationLocationDisplayer() {
	}

	@Location
	public AnnotationLocationDisplayer(@Location @Deprecated A a, @Location B b)
			throws @Location InvalidClassException, InvalidMarkException {
		@SuppressWarnings("unused")
		@Location
		String str = "test";
		this.a = a;
		this.b = b;
	}

	@Location("before method")
	public @Location("before return type") String test(
			@Location("receiver parameters") AnnotationLocationDisplayer<@Location("receiver parameters") A, @Location("receiver parameters") B> this,
			@Location("before method parameter1") String a1, @Location("before method parameter2") String a2)
			throws @Location("before throws exception") IndexOutOfBoundsException {
		@Location("before local variable")
		String str = "str";
		return str;
	}

}