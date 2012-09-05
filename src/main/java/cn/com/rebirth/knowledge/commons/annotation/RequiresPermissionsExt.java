/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons RequiresPermissionsExt.java 2012-8-23 9:41:44 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.shiro.authz.annotation.RequiresPermissions;

/**
 * The Interface RequiresPermissionsExt.
 *
 * @author l.xue.nong
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPermissionsExt {
	
	/**
	 * Requires permissions.
	 *
	 * @return the requires permissions
	 */
	RequiresPermissions requiresPermissions();

	/**
	 * Value.
	 *
	 * @return the string[]
	 */
	String[] value();
}
