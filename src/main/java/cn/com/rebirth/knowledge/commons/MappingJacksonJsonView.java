/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-web-admin MappingJacksonJsonView.java 2012-7-19 14:03:18 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.view.AbstractView;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The Class MappingJacksonJsonView.
 *
 * @author l.xue.nong
 */
public class MappingJacksonJsonView extends AbstractView {

	/** The Constant DEFAULT_CONTENT_TYPE. */
	public static final String DEFAULT_CONTENT_TYPE = "application/json";

	/** The object mapper. */
	private ObjectMapper objectMapper = new ObjectMapper();

	/** The encoding. */
	private JsonEncoding encoding = JsonEncoding.UTF8;

	/** The prefix json. */
	private boolean prefixJson = false;

	/** The model keys. */
	private Set<String> modelKeys;

	/** The extract value from single key model. */
	private boolean extractValueFromSingleKeyModel = false;

	/** The disable caching. */
	private boolean disableCaching = true;

	/**
	 * Instantiates a new mapping jackson json view.
	 */
	public MappingJacksonJsonView() {
		setContentType(DEFAULT_CONTENT_TYPE);
		setExposePathVariables(false);
	}

	/**
	 * Sets the object mapper.
	 *
	 * @param objectMapper the new object mapper
	 */
	public void setObjectMapper(ObjectMapper objectMapper) {
		Assert.notNull(objectMapper, "'objectMapper' must not be null");
		this.objectMapper = objectMapper;
	}

	/**
	 * Sets the encoding.
	 *
	 * @param encoding the new encoding
	 */
	public void setEncoding(JsonEncoding encoding) {
		Assert.notNull(encoding, "'encoding' must not be null");
		this.encoding = encoding;
	}

	/**
	 * Sets the prefix json.
	 *
	 * @param prefixJson the new prefix json
	 */
	public void setPrefixJson(boolean prefixJson) {
		this.prefixJson = prefixJson;
	}

	/**
	 * Sets the model key.
	 *
	 * @param modelKey the new model key
	 */
	public void setModelKey(String modelKey) {
		this.modelKeys = Collections.singleton(modelKey);
	}

	/**
	 * Sets the model keys.
	 *
	 * @param modelKeys the new model keys
	 */
	public void setModelKeys(Set<String> modelKeys) {
		this.modelKeys = modelKeys;
	}

	/**
	 * Gets the model keys.
	 *
	 * @return the model keys
	 */
	public Set<String> getModelKeys() {
		return this.modelKeys;
	}

	/**
	 * Sets the rendered attributes.
	 *
	 * @param renderedAttributes the new rendered attributes
	 */
	@Deprecated
	public void setRenderedAttributes(Set<String> renderedAttributes) {
		this.modelKeys = renderedAttributes;
	}

	/**
	 * Gets the rendered attributes.
	 *
	 * @return the rendered attributes
	 */
	@Deprecated
	public Set<String> getRenderedAttributes() {
		return this.modelKeys;
	}

	/**
	 * Sets the extract value from single key model.
	 *
	 * @param extractValueFromSingleKeyModel the new extract value from single key model
	 */
	public void setExtractValueFromSingleKeyModel(boolean extractValueFromSingleKeyModel) {
		this.extractValueFromSingleKeyModel = extractValueFromSingleKeyModel;
	}

	/**
	 * Sets the disable caching.
	 *
	 * @param disableCaching the new disable caching
	 */
	public void setDisableCaching(boolean disableCaching) {
		this.disableCaching = disableCaching;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.view.AbstractView#prepareResponse(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void prepareResponse(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType(getContentType());
		response.setCharacterEncoding(this.encoding.getJavaName());
		if (this.disableCaching) {
			response.addHeader("Pragma", "no-cache");
			response.addHeader("Cache-Control", "no-cache, no-store, max-age=0");
			response.addDateHeader("Expires", 1L);
		}
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Object value = filterModel(model);
		JsonGenerator generator = this.objectMapper.getJsonFactory().createJsonGenerator(response.getOutputStream(),
				this.encoding);
		if (this.prefixJson) {
			generator.writeRaw("{} && ");
		}
		this.objectMapper.writeValue(generator, value);
	}

	/**
	 * Filter model.
	 *
	 * @param model the model
	 * @return the object
	 */
	protected Object filterModel(Map<String, Object> model) {
		Map<String, Object> result = new HashMap<String, Object>(model.size());
		Set<String> renderedAttributes = (!CollectionUtils.isEmpty(this.modelKeys) ? this.modelKeys : model.keySet());
		for (Map.Entry<String, Object> entry : model.entrySet()) {
			if (!(entry.getValue() instanceof BindingResult) && renderedAttributes.contains(entry.getKey())) {
				result.put(entry.getKey(), entry.getValue());
			}
		}
		return (this.extractValueFromSingleKeyModel && result.size() == 1 ? result.values().iterator().next() : result);
	}

}
