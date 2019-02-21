package com.fengxin58.sample.reflect.annotation;

import java.util.ArrayList;
import java.util.List;

/**
 * 看eclipse给的提示，这样的就是raw type!<br>
 * Multiple markers at this line<br>
 * - List is a raw type. References to generic type List<E> should be
 * parameterized<br>
 * - ArrayList is a raw type. References to generic type ArrayList<E> should be
 * <br>
 * 
 * @author Administrator
 *
 */
public class RawType {

	List list = new ArrayList();

}
