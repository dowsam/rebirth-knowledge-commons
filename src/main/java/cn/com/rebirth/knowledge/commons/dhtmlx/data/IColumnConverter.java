/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons IColumnConverter.java 2012-8-6 13:59:32 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx.data;

import cn.com.rebirth.commons.search.annotation.AbstractSearchProperty;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlColumn;
import cn.com.rebirth.knowledge.commons.dhtmlx.entity.AbstractDhtmlxBaseEntity;

/**
 * The Interface IColumnConverter.
 *
 * @author l.xue.nong
 */
public interface IColumnConverter {

	/**
	 * Convert.
	 *
	 * @param property the property
	 * @param dhtmlColumn the dhtml column
	 * @param dhtmlxBaseEntity the dhtmlx base entity
	 * @param converterValue the converter value
	 * @return the object
	 */
	public Object convert(AbstractSearchProperty property, DhtmlColumn dhtmlColumn,
			AbstractDhtmlxBaseEntity dhtmlxBaseEntity, Object converterValue);

	/**
	 * The Class DefaultColumnConverter.
	 *
	 * @author l.xue.nong
	 */
	public static class DefaultColumnConverter implements IColumnConverter {

		/* (non-Javadoc)
		 * @see cn.com.rebirth.knowledge.commons.dhtmlx.data.IColumnConverter#convert(cn.com.rebirth.commons.search.annotation.AbstractSearchProperty, cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlColumn, cn.com.rebirth.knowledge.commons.dhtmlx.entity.AbstractDhtmlxBaseEntity)
		 */
		@Override
		public Object convert(AbstractSearchProperty property, DhtmlColumn dhtmlColumn,
				AbstractDhtmlxBaseEntity dhtmlxBaseEntity, Object converterValue) {
			return converterValue == null ? "" : converterValue;
		}

	}
}
