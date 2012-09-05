/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons Group.java 2012-8-3 21:06:52 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class Group.
 *
 * @author l.xue.nong
 */
public class Group {

	/** The header. */
	private String header;

	/** The columns. */
	private List<Object> columns = new ArrayList<Object>();

	/**
	 * Gets the columns.
	 *
	 * @return the columns
	 */
	public List<Object> getColumns() {
		return columns;
	}

	/**
	 * Sets the columns.
	 *
	 * @param columns the new columns
	 */
	public void setColumns(List<Object> columns) {
		this.columns = columns;
	}

	/**
	 * Gets the header.
	 *
	 * @return the header
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * Sets the header.
	 *
	 * @param header the new header
	 */
	public void setHeader(String header) {
		this.header = header;
	}

	/**
	 * Adds the column.
	 *
	 * @param column the column
	 */
	public void addColumn(Column column) {
		this.getColumns().add(column);
	}
}