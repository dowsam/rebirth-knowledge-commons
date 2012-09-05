/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons GridFactory.java 2012-8-3 22:17:22 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;

import cn.com.rebirth.commons.utils.SpringContextHolder;
import cn.com.rebirth.knowledge.commons.dhtmlx.builder.DhtmlxGridBuilder;
import cn.com.rebirth.knowledge.commons.dhtmlx.builder.IGridBuilder;

/**
 * A factory for creating Grid objects.
 */
public class GridFactory {

	/** The Constant cachedGridBuilder. */
	private static final Map<String, IGridBuilder> cachedGridBuilder = new ConcurrentHashMap<String, IGridBuilder>();

	/**
	 * Gets the grid builder.
	 *
	 * @param gridTypeName the grid type name
	 * @return the grid builder
	 */
	public static IGridBuilder getGridBuilder(String gridTypeName) {
		if (StringUtils.isEmpty(gridTypeName)) {
			gridTypeName = Configuration.getInstance().getDefaultGrid().name();
		}
		if (cachedGridBuilder.containsKey(gridTypeName)) {
			return cachedGridBuilder.get(gridTypeName);
		}

		String gridBuilderName = Configuration.getInstance().getBuilinGrid(gridTypeName).getBuilder();
		IGridBuilder gridBuilder = null;
		try {
			gridBuilder = SpringContextHolder.getBean(gridBuilderName);
		} catch (Exception e) {
			//nothing
		}

		if (null == gridBuilder) {
			gridBuilder = new DhtmlxGridBuilder();
		}

		cachedGridBuilder.put(gridBuilderName, gridBuilder);
		return gridBuilder;
	}
}
