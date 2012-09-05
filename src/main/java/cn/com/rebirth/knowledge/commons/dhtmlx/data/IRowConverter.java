/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons IRowConverter.java 2012-8-6 14:33:14 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx.data;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.com.rebirth.commons.search.annotation.AbstractSearchProperty;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlColumn;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.Dhtmlx;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlxBaseType;

import com.google.common.collect.Lists;

/**
 * The Interface IRowConverter.
 *
 * @author l.xue.nong
 */
public interface IRowConverter {

	/**
	 * Converter.
	 *
	 * @param param the param
	 * @param properties the properties
	 * @param dhtmlColumns the dhtml columns
	 * @return the collection
	 */
	public Collection<Object> converter(List<Object> param, List<AbstractSearchProperty> properties,
			List<DhtmlColumn> dhtmlColumns, Dhtmlx dhtmlx);

	/**
	 * The Class DefaultRowConverter.
	 *
	 * @author l.xue.nong
	 */
	public static class DefaultRowConverter implements IRowConverter {

		/* (non-Javadoc)
		 * @see cn.com.rebirth.knowledge.commons.dhtmlx.data.IRowConverter#converter(java.util.List, java.util.List, java.util.List)
		 */
		@Override
		public Collection<Object> converter(List<Object> param, List<AbstractSearchProperty> properties,
				List<DhtmlColumn> dhtmlColumns, Dhtmlx dhtmlx) {
			Object columnData = null;
			List<Object> returnData = Lists.newArrayList();
			if (dhtmlx.createCheckAllColumn()) {
				returnData.add("0");
			}
			for (int i = 0; i < dhtmlColumns.size(); i++) {
				columnData = param.get(i);
				DhtmlColumn dhtmlColumn = dhtmlColumns.get(i);
				if (null != columnData) {
					if (DhtmlxBaseType.LINK.equals(dhtmlColumn.coulumnType())) {
						returnData.add(substringBefore(columnData.toString(), "^"));
					} else {
						returnData.add(columnData);
					}
				} else {
					returnData.add(columnData);
				}
			}
			return returnData;
		}

		private String substringBefore(String rawString, String match) {
			if (StringUtils.isEmpty(rawString)) {
				return rawString;
			}
			int index = rawString.indexOf(match);
			if (index != -1) {
				rawString = rawString.substring(0, index);
			}
			return rawString;
		}
	}

}
