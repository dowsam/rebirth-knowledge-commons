/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons PassThruAuthenticationFilterExt.java 2012-9-18 15:20:52 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.security;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter;

/**
 * The Class PassThruAuthenticationFilterExt.
 *
 * @author l.xue.nong
 */
public class PassThruAuthenticationFilterExt extends PassThruAuthenticationFilter {
	/** The redirect to login. */
	private final RedirectToLogin redirectToLogin;

	/**
	 * Instantiates a new form authentication filter ext.
	 *
	 * @param redirectToLogin the redirect to login
	 */
	public PassThruAuthenticationFilterExt(RedirectToLogin redirectToLogin) {
		super();
		this.redirectToLogin = redirectToLogin;
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.web.filter.AccessControlFilter#redirectToLogin(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	@Override
	protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
		super.redirectToLogin(request, response);
		redirectToLogin.redirectToLogin(this, request, response);
	}

}
