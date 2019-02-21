package com.example.demo.reflect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value=ElementType.TYPE_USE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {
	
	String regular();
	
}