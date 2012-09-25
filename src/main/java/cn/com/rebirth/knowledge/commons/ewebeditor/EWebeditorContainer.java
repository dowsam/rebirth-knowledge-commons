/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons EWebeditorContainer.java 2012-9-18 11:38:58 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.ewebeditor;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.apache.commons.lang3.StringUtils;

import cn.com.rebirth.commons.AbstractContainer;
import cn.com.rebirth.commons.Container;
import cn.com.rebirth.commons.RebirthContainer;
import cn.com.rebirth.commons.io.FastByteArrayInputStream;
import cn.com.rebirth.commons.utils.CglibProxyUtils;
import cn.com.rebirth.commons.utils.CryptoUtils;
import cn.com.rebirth.commons.utils.DigestUtils;
import cn.com.rebirth.commons.utils.IpUtils;

/**
 * The Class EWebeditorContainer.
 *
 * @author l.xue.nong
 */
public class EWebeditorContainer extends AbstractContainer implements Container {

	/* (non-Javadoc)	
	 * @see cn.com.rebirth.commons.Container#start()
	 */
	@Override
	public void start() {
		List<String> list = new ArrayList<String>();
		String s_License = "2:6966:3:2:1::%s:%s";
		List<String> ips = IpUtils.getRealIps();
		for (String ip : ips) {
			String a = CryptoUtils.generateAesHexKey();
			try {
				a = DigestUtils.md5ToHex(new FastByteArrayInputStream(ip.getBytes()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			list.add(String.format(s_License, ip, a));
		}
		EWebeditorUtils eWebeditorUtils = new EWebeditorUtils();
		eWebeditorUtils.InitServer(CglibProxyUtils.getProxyInstance(PageContext.class, new MethodInterceptor() {

			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				String methodName = method.getName();
				if ("getServletContext".equalsIgnoreCase(methodName)) {
					return RebirthContainer.getInstance().getServletContext();
				}
				if ("getRequest".equalsIgnoreCase(methodName)) {
					return CglibProxyUtils.getProxyInstance(HttpServletRequest.class, new MethodInterceptor() {

						@Override
						public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
								throws Throwable {
							String methodName = method.getName();
							if ("getServletPath".equalsIgnoreCase(methodName)) {
								return "/ewebeditor/jsp/browse.jsp";
							}
							return null;
						}
					});
				}
				return null;
			}
		}));
		eWebeditorUtils.WriteConfig(StringUtils.join(list, ";"));
	}

	/* (non-Javadoc)
	 * @see cn.com.rebirth.commons.Container#stop()
	 */
	@Override
	public void stop() {

	}

}
