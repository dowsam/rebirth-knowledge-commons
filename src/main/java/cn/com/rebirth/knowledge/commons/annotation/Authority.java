/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons Authority.java 2012-8-23 11:56:19 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Interface Authority.
 *
 * @author l.xue.nong
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Authority {

	/**
	 * Value.
	 *
	 * @return the string[]
	 */
	String[] value() default {};

	/**
	 * Names.
	 *
	 * @return the string[]
	 */
	String[] names() default {};
}
