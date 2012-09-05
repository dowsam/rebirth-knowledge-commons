/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons Permission.java 2012-8-23 11:35:09 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.security;

import java.lang.annotation.Annotation;

import org.apache.shiro.aop.MethodInvocation;
import org.apache.shiro.authz.AuthorizationException;

/**
 * The Interface Permission.
 *
 * @author l.xue.nong
 */
public interface Permission {

	/**
	 * Assert authorized.
	 *
	 * @param a the a
	 * @throws AuthorizationException the authorization exception
	 */
	public abstract void assertAuthorized(Annotation a) throws AuthorizationException;

	/**
	 * Assert authorized.
	 *
	 * @param mi the mi
	 * @param a the a
	 * @throws AuthorizationException the authorization exception
	 */
	public abstract void assertAuthorized(MethodInvocation mi, Annotation a) throws AuthorizationException;
}
