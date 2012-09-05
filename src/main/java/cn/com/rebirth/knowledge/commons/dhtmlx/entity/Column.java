/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons Column.java 2012-8-3 21:06:52 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx.entity;

import cn.com.rebirth.commons.search.annotation.AbstractSearchProperty;
import cn.com.rebirth.knowledge.commons.dhtmlx.ColumnDataSets;

/**
 * The Class Column.
 *
 * @author l.xue.nong
 */
public class Column {

	/** The id. */
	private String id;

	/** The header. */
	private String header;

	/** The width. */
	private String width = "0";

	/** The align. */
	private String align = "left";

	/** The frozen. */
	private boolean frozen = false;

	/** The visible. */
	private boolean visible = true;

	/** The sortable. */
	private boolean sortable = true;

	/** The color. */
	private String color = "";

	/** The format. */
	private String format = "";

	/** The query expression. */
	private String queryExpression;

	/** The type. */
	private String type = "ro";

	/** The exportable. */
	private boolean exportable = true;

	/** The is key. */
	private boolean isKey = false;

	/** The parent. */
	private Object parent;

	/** The column data sets. */
	private ColumnDataSets columnDataSets;

	/** The property. */
	private AbstractSearchProperty property;

	/** The group. */
	private boolean group = false;

	/**
	 * Checks if is key.
	 *
	 * @return true, if is key
	 */
	public boolean isKey() {
		return isKey;
	}

	/**
	 * Sets the key.
	 *
	 * @param isKey the new key
	 */
	public void setKey(boolean isKey) {
		this.isKey = isKey;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
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
	 * Gets the width.
	 *
	 * @return the width
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * Sets the width.
	 *
	 * @param width the new width
	 */
	public void setWidth(String width) {
		this.width = width;
	}

	/**
	 * Gets the align.
	 *
	 * @return the align
	 */
	public String getAlign() {
		return align;
	}

	/**
	 * Checks if is exportable.
	 *
	 * @return true, if is exportable
	 */
	public boolean isExportable() {
		return exportable;
	}

	/**
	 * Sets the exportable.
	 *
	 * @param exportable the new exportable
	 */
	public void setExportable(boolean exportable) {
		this.exportable = exportable;
	}

	/**
	 * Sets the align.
	 *
	 * @param align the new align
	 */
	public void setAlign(String align) {
		this.align = align;
	}

	/**
	 * Checks if is frozen.
	 *
	 * @return true, if is frozen
	 */
	public boolean isFrozen() {
		return frozen;
	}

	/**
	 * Sets the frozen.
	 *
	 * @param frozen the new frozen
	 */
	public void setFrozen(boolean frozen) {
		this.frozen = frozen;
	}

	/**
	 * Checks if is visible.
	 *
	 * @return true, if is visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * Sets the visible.
	 *
	 * @param visible the new visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * Checks if is sortable.
	 *
	 * @return true, if is sortable
	 */
	public boolean isSortable() {
		return sortable;
	}

	/**
	 * Sets the sortable.
	 *
	 * @param sortable the new sortable
	 */
	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Sets the color.
	 *
	 * @param color the new color
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Gets the format.
	 *
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * Sets the format.
	 *
	 * @param format the new format
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * Gets the query expression.
	 *
	 * @return the query expression
	 */
	public String getQueryExpression() {
		return queryExpression;
	}

	/**
	 * Sets the query expression.
	 *
	 * @param queryExpression the new query expression
	 */
	public void setQueryExpression(String queryExpression) {
		this.queryExpression = queryExpression;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the parent.
	 *
	 * @return the parent
	 */
	public Object getParent() {
		return parent;
	}

	/**
	 * Sets the parent.
	 *
	 * @param parent the new parent
	 */
	public void setParent(Object parent) {
		this.parent = parent;
	}

	/**
	 * Gets the column data sets.
	 *
	 * @return the column data sets
	 */
	public ColumnDataSets getColumnDataSets() {
		return columnDataSets;
	}

	/**
	 * Sets the column data sets.
	 *
	 * @param columnDataSets the new column data sets
	 */
	public void setColumnDataSets(ColumnDataSets columnDataSets) {
		this.columnDataSets = columnDataSets;
	}

	/**
	 * Gets the property.
	 *
	 * @return the property
	 */
	public AbstractSearchProperty getProperty() {
		return property;
	}

	/**
	 * Sets the property.
	 *
	 * @param property the new property
	 */
	public void setProperty(AbstractSearchProperty property) {
		this.property = property;
	}

	/**
	 * Checks if is group.
	 *
	 * @return true, if is group
	 */
	public boolean isGroup() {
		return group;
	}

	/**
	 * Sets the group.
	 *
	 * @param group the new group
	 */
	public void setGroup(boolean group) {
		this.group = group;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Column other = (Column) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}