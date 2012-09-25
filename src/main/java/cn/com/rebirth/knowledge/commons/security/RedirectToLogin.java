/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons RedirectToLogin.java 2012-9-18 15:15:16 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.security;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.AuthenticationFilter;

/**
 * The Interface RedirectToLogin.
 *
 * @author l.xue.nong
 */
public interface RedirectToLogin {

	/**
	 * Redirect to login.
	 *
	 * @param authenticationFilter the authentication filter
	 * @param request the request
	 * @param response the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void redirectToLogin(AuthenticationFilter authenticationFilter, ServletRequest request,
			ServletResponse response) throws IOException;
}
