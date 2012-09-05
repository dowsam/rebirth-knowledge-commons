/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons DefinitionSourceShiroFilterFactoryBeanExt.java 2012-8-24 14:16:51 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.security;

import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.apache.shiro.web.util.WebUtils;

import cn.com.rebirth.commons.utils.ReflectionUtils;
import cn.com.rebirth.core.security.DefinitionSourceShiroFilterFactoryBean;

/**
 * The Class DefinitionSourceShiroFilterFactoryBeanExt.
 *
 * @author l.xue.nong
 */
public class DefinitionSourceShiroFilterFactoryBeanExt extends DefinitionSourceShiroFilterFactoryBean {

	/* (non-Javadoc)
	 * @see org.apache.shiro.spring.web.ShiroFilterFactoryBean#createInstance()
	 */
	@Override
	protected AbstractShiroFilter createInstance() throws Exception {
		AbstractShiroFilter abstractShiroFilter = super.createInstance();
		PathMatchingFilterChainResolver pathMatchingFilterChainResolver = (PathMatchingFilterChainResolver) abstractShiroFilter
				.getFilterChainResolver();
		bulidOverFilters(pathMatchingFilterChainResolver.getFilterChainManager());
		PathMatchingFilterChainResolverExt chainResolverExt = new PathMatchingFilterChainResolverExt();
		chainResolverExt.setPathMatcher(new RebirthPatternMatcher());
		chainResolverExt.setFilterChainManager(pathMatchingFilterChainResolver.getFilterChainManager());
		abstractShiroFilter.setFilterChainResolver(chainResolverExt);
		return abstractShiroFilter;
	}

	/**
	 * Bulid over filters.
	 *
	 * @param filterChainManager the filter chain manager
	 */
	private void bulidOverFilters(FilterChainManager filterChainManager) {
		Map<String, Filter> map = filterChainManager.getFilters();
		if (map != null && !map.isEmpty()) {
			for (Map.Entry<String, Filter> entry : map.entrySet()) {
				String key = entry.getKey();
				Filter filter = entry.getValue();
				if (filter instanceof PathMatchingFilter) {
					filterChainManager.addFilter(key, new RebithAuthFilter((PathMatchingFilter) filter), false);
				}
			}
		}
	}

	/**
	 * The Class RebithAuthFilter.
	 *
	 * @author l.xue.nong
	 */
	private static class RebithAuthFilter extends PathMatchingFilter {

		/** The path matching filter. */
		private final PathMatchingFilter pathMatchingFilter;
		private RebirthPatternMatcher rebirthPatternMatcher = new RebirthPatternMatcher();

		/**
		 * Instantiates a new rebith auth filter.
		 *
		 * @param filter the filter
		 */
		public RebithAuthFilter(PathMatchingFilter filter) {
			super();
			this.pathMatchingFilter = filter;
		}

		/* (non-Javadoc)
		 * @see org.apache.shiro.web.filter.PathMatchingFilter#getPathWithinApplication(javax.servlet.ServletRequest)
		 */
		@Override
		protected String getPathWithinApplication(ServletRequest request) {
			return super.getPathWithinApplication(request) + "![" + WebUtils.toHttp(request).getMethod() + "]";
		}

		/* (non-Javadoc)
		 * @see org.apache.shiro.web.filter.PathMatchingFilter#pathsMatch(java.lang.String, java.lang.String)
		 */
		@Override
		protected boolean pathsMatch(String pattern, String path) {
			return rebirthPatternMatcher.matches(pattern, path);
		}

		/* (non-Javadoc)
		 * @see org.apache.shiro.web.filter.PathMatchingFilter#processPathConfig(java.lang.String, java.lang.String)
		 */
		@Override
		public Filter processPathConfig(String path, String config) {
			return super.processPathConfig(path, config);
		}

		/* (non-Javadoc)
		 * @see org.apache.shiro.web.filter.PathMatchingFilter#onPreHandle(javax.servlet.ServletRequest, javax.servlet.ServletResponse, java.lang.Object)
		 */
		@Override
		protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
				throws Exception {
			try {
				return (Boolean) ReflectionUtils.invokeMethod(pathMatchingFilter, "onPreHandle", new Class[] {
						ServletRequest.class, ServletResponse.class, Object.class }, new Object[] { request, response,
						mappedValue });
			} catch (Exception e) {
			}
			return super.onPreHandle(request, response, mappedValue);
		}

	}

	/**
	 * The Class PathMatchingFilterChainResolverExt.
	 *
	 * @author l.xue.nong
	 */
	private static class PathMatchingFilterChainResolverExt extends PathMatchingFilterChainResolver implements
			FilterChainResolver {

		/**
		 * Instantiates a new path matching filter chain resolver ext.
		 */
		public PathMatchingFilterChainResolverExt() {
			super();
		}

		/* (non-Javadoc)
		 * @see org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver#getPathWithinApplication(javax.servlet.ServletRequest)
		 */
		@Override
		protected String getPathWithinApplication(ServletRequest request) {
			return super.getPathWithinApplication(request) + "![" + WebUtils.toHttp(request).getMethod() + "]";
		}

	}

}
