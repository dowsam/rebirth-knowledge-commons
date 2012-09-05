/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons CheckAllColumn.java 2012-8-3 21:06:52 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx.entity;

/**
 * The Class CheckAllColumn.
 *
 * @author l.xue.nong
 */
public class CheckAllColumn extends Column {

	/**
	 * Instantiates a new check all column.
	 */
	public CheckAllColumn() {
		this.setAlign("center");
		this.setHeader("{#master_page_checkbox}");
		this.setType("cb");
		this.setId("_checkAll");
		this.setFrozen(true);
		this.setWidth("5");
		this.setExportable(false);
	}
}