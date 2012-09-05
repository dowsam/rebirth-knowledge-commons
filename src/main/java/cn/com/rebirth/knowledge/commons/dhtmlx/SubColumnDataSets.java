/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons SubColumnDataSets.java 2012-8-20 14:25:55 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx;

import cn.com.rebirth.commons.search.annotation.AbstractSearchProperty;
import cn.com.rebirth.knowledge.commons.dhtmlx.entity.Grid;

/**
 * The Interface SubColumnDataSets.
 *
 * @author l.xue.nong
 */
public interface SubColumnDataSets {

	/**
	 * Before script.
	 *
	 * @param grid the grid
	 * @param stringBuilder the string builder
	 */
	void beforeScript(int columnIndex, AbstractSearchProperty property, Grid grid, StringBuilder stringBuilder);

	/**
	 * After script.
	 *
	 * @param grid the grid
	 * @param stringBuilder the string builder
	 */
	void afterScript(int columnIndex, AbstractSearchProperty property, Grid grid, StringBuilder stringBuilder);
}
