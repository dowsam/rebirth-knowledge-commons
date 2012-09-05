/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons AopAllianceAnnotationsAuthorizingMethodInterceptorExt.java 2012-8-23 9:51:53 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.security;

import org.apache.shiro.spring.security.interceptor.AopAllianceAnnotationsAuthorizingMethodInterceptor;

/**
 * The Class AopAllianceAnnotationsAuthorizingMethodInterceptorExt.
 *
 * @author l.xue.nong
 */
public class AopAllianceAnnotationsAuthorizingMethodInterceptorExt extends
		AopAllianceAnnotationsAuthorizingMethodInterceptor {

	/**
	 * Instantiates a new aop alliance annotations authorizing method interceptor ext.
	 */
	public AopAllianceAnnotationsAuthorizingMethodInterceptorExt() {
		super();
		getMethodInterceptors().add(new PermissionExtAnnotationMethodInterceptor());
	}

}
