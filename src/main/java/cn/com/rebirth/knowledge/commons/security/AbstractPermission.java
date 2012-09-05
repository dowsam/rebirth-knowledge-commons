/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons AbstractPermission.java 2012-8-23 11:40:30 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.security;

import java.lang.annotation.Annotation;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.aop.AuthorizingAnnotationHandler;

/**
 * The Class AbstractPermission.
 *
 * @author l.xue.nong
 */
public abstract class AbstractPermission extends AuthorizingAnnotationHandler implements Permission {

	/**
	 * Instantiates a new abstract permission.
	 *
	 * @param annotationClass the annotation class
	 */
	public AbstractPermission(Class<? extends Annotation> annotationClass) {
		super(annotationClass);
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.authz.aop.AuthorizingAnnotationHandler#assertAuthorized(java.lang.annotation.Annotation)
	 */
	@Override
	public void assertAuthorized(Annotation a) throws AuthorizationException {
		throw new UnsupportedOperationException("not yet implement");
	}
}
