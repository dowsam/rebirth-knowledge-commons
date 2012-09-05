/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons AuthorizationAttributeSourceAdvisorExt.java 2012-8-23 9:47:41 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.security;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.springframework.core.annotation.AnnotationUtils;

import cn.com.rebirth.knowledge.commons.annotation.RequiresPermissionsExt;

/**
 * The Class AuthorizationAttributeSourceAdvisorExt.
 *
 * @author l.xue.nong
 */
@SuppressWarnings({ "unchecked" })
public class AuthorizationAttributeSourceAdvisorExt extends AuthorizationAttributeSourceAdvisor {
	
	/** The Constant AUTHZ_ANNOTATION_CLASSES. */
	private static final Class<? extends Annotation>[] AUTHZ_ANNOTATION_CLASSES = new Class[] {
			RequiresPermissions.class, RequiresRoles.class, RequiresUser.class, RequiresGuest.class,
			RequiresAuthentication.class, RequiresPermissionsExt.class };
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8635562957633707458L;

	/**
	 * Instantiates a new authorization attribute source advisor ext.
	 */
	public AuthorizationAttributeSourceAdvisorExt() {
		setAdvice(new AopAllianceAnnotationsAuthorizingMethodInterceptorExt());
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor#matches(java.lang.reflect.Method, java.lang.Class)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public boolean matches(Method method, Class targetClass) {
		Method m = method;

		if (isAuthzAnnotationPresent(m)) {
			return true;
		}

		//The 'method' parameter could be from an interface that doesn't have the annotation.
		//Check to see if the implementation has it.
		if (targetClass != null) {
			try {
				m = targetClass.getMethod(m.getName(), m.getParameterTypes());
				if (isAuthzAnnotationPresent(m)) {
					return true;
				}
			} catch (NoSuchMethodException ignored) {
				//default return value is false.  If we can't find the method, then obviously
				//there is no annotation, so just use the default return value.
			}
		}

		return false;
	}

	/**
	 * Checks if is authz annotation present.
	 *
	 * @param method the method
	 * @return true, if is authz annotation present
	 */
	protected boolean isAuthzAnnotationPresent(Method method) {
		for (Class<? extends Annotation> annClass : AUTHZ_ANNOTATION_CLASSES) {
			Annotation a = AnnotationUtils.findAnnotation(method, annClass);
			if (a != null) {
				return true;
			}
		}
		return false;
	}

}
