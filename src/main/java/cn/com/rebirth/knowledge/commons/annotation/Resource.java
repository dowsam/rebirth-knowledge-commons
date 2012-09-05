/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons Resource.java 2012-8-23 13:04:59 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.com.rebirth.knowledge.commons.entity.system.SysResourceEntity;

/**
 * The Interface Resource.
 *
 * @author l.xue.nong
 */
@Target({ ElementType.PACKAGE, ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Resource {

	/**
	 * Values.
	 *
	 * @return the string[]
	 */
	String[] values() default {};

	/**
	 * Names.
	 *
	 * @return the string[]
	 */
	String[] names() default {};

	/**
	 * Method.
	 *
	 * @return the string[]
	 */
	String[] method() default {};

	/**
	 * Params.
	 *
	 * @return the string[]
	 */
	String[] params() default {};

	/**
	 * Resource type.
	 *
	 * @return the string
	 */
	String resourceType() default SysResourceEntity.URL_TYPE;

	/**
	 * Show menu.
	 *
	 * @return true, if successful
	 */
	boolean showMenu() default true;

	/**
	 * Open menu.
	 *
	 * @return true, if successful
	 */
	boolean openMenu() default true;
}
