/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons PermissionExtAnnotationHandler.java 2012-8-23 9:58:44 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.security;

import java.lang.annotation.Annotation;

import org.apache.shiro.aop.MethodInvocation;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.subject.Subject;

import cn.com.rebirth.knowledge.commons.annotation.RequiresPermissionsExt;

/**
 * The Class PermissionExtAnnotationHandler.
 *
 * @author l.xue.nong
 */
public class PermissionExtAnnotationHandler extends AbstractPermission implements Permission {

	/**
	 * Instantiates a new permission ext annotation handler.
	 */
	public PermissionExtAnnotationHandler() {
		super(RequiresPermissionsExt.class);
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.authz.aop.AuthorizingAnnotationHandler#assertAuthorized(java.lang.annotation.Annotation)
	 */
	@Override
	public void assertAuthorized(Annotation a) throws AuthorizationException {
		if (!(a instanceof RequiresPermissionsExt))
			return;

		RequiresPermissionsExt rpAnnotation = (RequiresPermissionsExt) a;
		String[] perms = getAnnotationValue(a);
		Subject subject = getSubject();

		if (perms.length == 1) {
			subject.checkPermission(perms[0]);
			return;
		}
		if (Logical.AND.equals(rpAnnotation.requiresPermissions().logical())) {
			getSubject().checkPermissions(perms);
			return;
		}
		if (Logical.OR.equals(rpAnnotation.requiresPermissions().logical())) {
			// Avoid processing exceptions unnecessarily - "delay" throwing the exception by calling hasRole first
			boolean hasAtLeastOnePermission = false;
			for (String permission : perms)
				if (getSubject().isPermitted(permission))
					hasAtLeastOnePermission = true;
			// Cause the exception if none of the role match, note that the exception message will be a bit misleading
			if (!hasAtLeastOnePermission)
				getSubject().checkPermission(perms[0]);

		}
	}

	/**
	 * Gets the annotation value.
	 *
	 * @param a the a
	 * @return the annotation value
	 */
	protected String[] getAnnotationValue(Annotation a) {
		RequiresPermissionsExt rpAnnotation = (RequiresPermissionsExt) a;
		return rpAnnotation.requiresPermissions().value();
	}

	@Override
	public void assertAuthorized(MethodInvocation mi, Annotation a) throws AuthorizationException {
	}

}
