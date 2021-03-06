package com.fengxin58.sample.reflect.field;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedArrayType;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.util.Arrays;

public class GetAnnotatedTypeExample3 {
	
    private @PrimeNumber int[] anInt;

    public static void main(String... args) throws NoSuchFieldException {
        System.out.println("Example:- @PrimeNumber int[] anInt;");
        
        Field field = GetAnnotatedTypeExample3.class.getDeclaredField("anInt");
        
        AnnotatedType annotatedType = field.getAnnotatedType();
        
        printAnnotatedType(annotatedType);

        if (annotatedType instanceof AnnotatedArrayType) {
            System.out.println("-- casting to AnnotatedArrayType --");
            AnnotatedArrayType annotatedArrayType = (AnnotatedArrayType) annotatedType;
            System.out.println("-- AnnotatedArrayType#getAnnotatedGenericComponentType() --");
            AnnotatedType annotatedType2 = annotatedArrayType.getAnnotatedGenericComponentType();
            printAnnotatedType(annotatedType2);
        }
    }

    private static void printAnnotatedType(AnnotatedType annotatedType) {
        System.out.println("Type: " + annotatedType.getType().getTypeName());
        System.out.println("Annotations: " +
                Arrays.toString(annotatedType.getAnnotations()));
        System.out.println("Declared Annotations: " +
                Arrays.toString(annotatedType.getDeclaredAnnotations()));
        AnnotatedType annotatedOwnerType = annotatedType.getAnnotatedOwnerType();//Java 9
        System.out.println("Annotated owner type: " + annotatedOwnerType);
        System.out.println("AnnotatedType class: " + annotatedType.getClass().getName());
        System.out.println("AnnotatedType class implementing interfaces: " +
                Arrays.toString(annotatedType.getClass().getInterfaces()));
    }

    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    private @interface PrimeNumber {}
}