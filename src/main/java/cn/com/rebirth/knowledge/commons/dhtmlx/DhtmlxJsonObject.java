/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons DhtmlxJsonObject.java 2012-8-3 21:35:45 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx;

import java.io.Serializable;
import java.util.Collection;

/**
 * The Class DhtmlxJsonObject.
 *
 * @author l.xue.nong
 */
public class DhtmlxJsonObject implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 229133894403473439L;

	/** The id. */
	private Serializable id;//主键

	/** The data. */
	private Collection<Object> data;//数据

	/**
	 * Instantiates a new dhtmlx json object.
	 */
	public DhtmlxJsonObject() {
		super();
	}

	/**
	 * Instantiates a new dhtmlx json object.
	 *
	 * @param id the id
	 * @param data the data
	 */
	public DhtmlxJsonObject(Serializable id, Collection<Object> data) {
		super();
		this.id = id;
		this.data = data;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Serializable getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Serializable id) {
		this.id = id;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public Collection<Object> getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(Collection<Object> data) {
		this.data = data;
	}

}
