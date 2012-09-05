/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons AbstractDhtmlxConfig.java 2012-8-3 21:35:45 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx;

import java.io.IOException;
import java.util.Map;

import cn.com.rebirth.commons.utils.TemplateMatcher;
import cn.com.rebirth.core.template.TemplateEngine;
import cn.com.rebirth.core.template.TemplateEngineFactory;

/**
 * The Class AbstractDhtmlxConfig.
 *
 * @author l.xue.nong
 */
public abstract class AbstractDhtmlxConfig implements DhtmlxConfig {

	/** The template matcher. */
	protected TemplateMatcher templateMatcher;

	/**
	 * Instantiates a new abstract dhtmlx config.
	 */
	public AbstractDhtmlxConfig() {
		templateMatcher = new TemplateMatcher("${", "}");
	}

	/* (non-Javadoc)
	 * @see cn.com.rebirth.knowledge.commons.dhtmlx.DhtmlxConfig#getImagePath()
	 */
	@Override
	public String getImagePath() {
		return templateMatcher.replace(DhtmlxConfig.IMAGEPATH, variableResolver(new TemplateMatcher.VariableResolver() {
			@Override
			public String resolve(String variable) {
				String _v = System.getProperty(variable);
				if (_v == null)
					_v = System.getenv(variable);
				if (_v == null) {
					_v = variable;
				}
				return _v;
			}
		}));
	}

	/* (non-Javadoc)
	 * @see cn.com.rebirth.knowledge.commons.dhtmlx.DhtmlxConfig#getSkin()
	 */
	@Override
	public String getSkin() {
		return templateMatcher.replace(DhtmlxConfig.SKIN, variableResolver(new TemplateMatcher.VariableResolver() {

			@Override
			public String resolve(String variable) {
				String _v = System.getProperty(variable);
				if (_v == null)
					_v = System.getenv(variable);
				if (_v == null) {
					_v = DhtmlxConfig.DEFAULT_SKIN;
				}
				return _v;
			}
		}));
	}

	/**
	 * Variable resolver.
	 *
	 * @param variableResolver the variable resolver
	 * @return the template matcher. variable resolver
	 */
	protected TemplateMatcher.VariableResolver variableResolver(TemplateMatcher.VariableResolver variableResolver) {
		return variableResolver;
	}

	/* (non-Javadoc)
	 * @see cn.com.rebirth.knowledge.commons.dhtmlx.DhtmlxConfig#writer(java.io.Writer)
	 */
	@Override
	public String writer(String templateName) throws IOException {
		TemplateEngine engine = createTemplateEngine(templateName);
		return engine.renderFile(templateName, toParam());
	}

	/**
	 * Creates the template engine.
	 *
	 * @param templateName the template name
	 * @return the template engine
	 */
	protected TemplateEngine createTemplateEngine(String templateName) {
		return TemplateEngineFactory.createTemplateEngine(templateName);
	}

	/**
	 * To param.
	 *
	 * @return the map
	 */
	public abstract Map<String, Object> toParam();
}
