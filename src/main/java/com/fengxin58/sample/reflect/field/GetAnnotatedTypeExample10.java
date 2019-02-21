package com.fengxin58.sample.reflect.field;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.AnnotatedWildcardType;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Map;

/**
 * AnnotatedWildcardType <br>
 * 
 * @author Administrator
 *
 */
public class GetAnnotatedTypeExample10 {
	
    private @PrimeNumber Map<? extends BigInteger, ? super BigDecimal> numbers;

    public static void main(String... args) throws NoSuchFieldException {
        System.out.println("Example:- @PrimeNumber Map<? extends BigInteger, ? super BigDecimal> numbers;");
        Field field = GetAnnotatedTypeExample10.class.getDeclaredField("numbers");
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

                if (actualTypeArgument instanceof AnnotatedWildcardType) {
                    System.out.println("-- casting to AnnotatedWildcardType --");
                    AnnotatedWildcardType wildcardType = (AnnotatedWildcardType) actualTypeArgument;
                    System.out.println("-- AnnotatedWildcardType#getAnnotatedLowerBounds() --");
                    
                    AnnotatedType[] lowerBounds = wildcardType.getAnnotatedLowerBounds();
                    printAnnotatedType(lowerBounds);
                    
                    System.out.println("-- AnnotatedWildcardType#getAnnotatedUpperBounds() --");
                    AnnotatedType[] upperBounds = wildcardType.getAnnotatedUpperBounds();
                    printAnnotatedType(upperBounds);
                }
            }
        }
    }

    private static void printAnnotatedType(AnnotatedType... annotatedTypes) {
        for (AnnotatedType annotatedType : annotatedTypes) {
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

            if (annotatedOwnerType != null) {
                System.out.println("-- annotatedOwnerType --");
                printAnnotatedType(annotatedOwnerType);
            }
        }
    }

    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    private @interface PrimeNumber {}
} 
