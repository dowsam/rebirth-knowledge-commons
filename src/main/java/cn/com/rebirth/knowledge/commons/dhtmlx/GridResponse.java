/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons GridResponse.java 2012-8-3 21:35:45 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletResponse;

/**
 * The Class GridResponse.
 *
 * @author l.xue.nong
 */
public class GridResponse {
	
	/** The http response. */
	protected HttpServletResponse httpResponse;
	
	/** The writer. */
	protected Writer writer;

	/**
	 * Instantiates a new grid response.
	 */
	public GridResponse() {

	}

	/**
	 * Instantiates a new grid response.
	 *
	 * @param resp the resp
	 */
	public GridResponse(HttpServletResponse resp) {
		this.setHttpResponse(resp);
	}

	/**
	 * Gets the http response.
	 *
	 * @return the http response
	 */
	public HttpServletResponse getHttpResponse() {
		return httpResponse;
	}

	/**
	 * Sets the http response.
	 *
	 * @param httpResponse the new http response
	 */
	public void setHttpResponse(HttpServletResponse httpResponse) {
		this.httpResponse = httpResponse;
	}

	/**
	 * Sets the writer.
	 *
	 * @param writer the new writer
	 */
	public void setWriter(Writer writer) {
		this.writer = writer;
	}

	/**
	 * Gets the writer.
	 *
	 * @return the writer
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Writer getWriter() throws IOException {
		if (null != writer) {
			return writer;
		} else {
			if (null != httpResponse) {
				return httpResponse.getWriter();
			} else {
				this.writer = new java.io.StringWriter();
			}
		}
		return writer;
	}
}