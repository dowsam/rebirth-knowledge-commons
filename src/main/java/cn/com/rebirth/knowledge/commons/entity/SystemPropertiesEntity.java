/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons SystemPropertiesEntity.java 2012-8-16 13:26:12 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.entity;

import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlColumn;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.Dhtmlx;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlxBaseType;
import cn.com.rebirth.knowledge.commons.dhtmlx.entity.AbstractDhtmlxBaseEntity;

/**
 * The Class SystemPropertiesEntity.
 *
 * @author l.xue.nong
 */
@Dhtmlx(createCheckAllColumn = false, isDataProcessor = true, isPage = false)
public class SystemPropertiesEntity extends AbstractDhtmlxBaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3172003330157501477L;

	/** The attribute name. */
	private String attributeName;

	/** The attribute value. */
	private String attributeValue;

	/**
	 * Gets the attribute name.
	 *
	 * @return the attribute name
	 */
	@DhtmlColumn(columnIndex = 0, headerName = "属性名", coulumnType = DhtmlxBaseType.ED, initWidth = 150)
	public String getAttributeName() {
		return attributeName;
	}

	/**
	 * Sets the attribute name.
	 *
	 * @param attributeName the new attribute name
	 */
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	/**
	 * Gets the attribute value.
	 *
	 * @return the attribute value
	 */
	@DhtmlColumn(columnIndex = 0, headerName = "属性值", coulumnType = DhtmlxBaseType.ED, initWidth = 150)
	public String getAttributeValue() {
		return attributeValue;
	}

	/**
	 * Sets the attribute value.
	 *
	 * @param attributeValue the new attribute value
	 */
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	/* (non-Javadoc)
	 * @see cn.com.rebirth.knowledge.commons.entity.AbstractBaseEntity#isChildTrem()
	 */
	@Override
	public boolean isChildTrem() {
		return false;
	}

}
