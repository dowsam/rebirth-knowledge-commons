/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-web-admin SpringEhCacheRegionFactory.java 2012-7-20 11:53:41 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons;

import java.util.Properties;

import net.sf.ehcache.CacheManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.cache.CacheException;
import org.hibernate.cfg.Settings;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import cn.com.rebirth.commons.utils.SpringContextHolder;

/**
 * A factory for creating SpringEhCacheRegion objects.
 */
public class SpringEhCacheRegionFactory extends org.hibernate.cache.ehcache.EhCacheRegionFactory {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5527139437378451139L;

	@Override
	public void start(Settings settings, Properties properties) throws CacheException {
		CacheManager cacheManager = null;
		try {
			cacheManager = SpringContextHolder.getBean(CacheManager.class);
		} catch (NoSuchBeanDefinitionException e) {
		}
		if (cacheManager != null) {
			this.settings = settings;
			this.manager = cacheManager;
			this.mbeanRegistrationHelper.registerMBean(manager, properties);
		} else {
			super.start(settings, properties);
			SpringContextHolder.getBeanFactory().registerSingleton(
					StringUtils.uncapitalize(CacheManager.class.getSimpleName()), manager);
		}
		SpringContextHolder.getBean(CacheManager.class);
	}

}
