/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons PermissionExtAnnotationMethodInterceptor.java 2012-8-23 9:54:12 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.security;

import org.apache.shiro.aop.AnnotationResolver;
import org.apache.shiro.aop.MethodInvocation;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.aop.AuthorizingAnnotationMethodInterceptor;

/**
 * The Class PermissionExtAnnotationMethodInterceptor.
 *
 * @author l.xue.nong
 */
public class PermissionExtAnnotationMethodInterceptor extends AuthorizingAnnotationMethodInterceptor {

	/**
	 * Instantiates a new permission ext annotation method interceptor.
	 */
	public PermissionExtAnnotationMethodInterceptor() {
		super(new PermissionExtAnnotationHandler());
	}

	/**
	 * Instantiates a new permission ext annotation method interceptor.
	 *
	 * @param resolver the resolver
	 * @since 1.1
	 */
	public PermissionExtAnnotationMethodInterceptor(AnnotationResolver resolver) {
		super(new PermissionExtAnnotationHandler(), resolver);
	}

	@Override
	public void assertAuthorized(MethodInvocation mi) throws AuthorizationException {
		// TODO Auto-generated method stub
		super.assertAuthorized(mi);
	}
	
	
	
	

}
