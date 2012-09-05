/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons IGridBuilder.java 2012-8-3 21:31:03 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx.builder;

import cn.com.rebirth.knowledge.commons.dhtmlx.GridRequest;
import cn.com.rebirth.knowledge.commons.dhtmlx.GridResponse;
import cn.com.rebirth.knowledge.commons.dhtmlx.entity.Grid;

/**
 * The Interface IGridBuilder.
 *
 * @author l.xue.nong
 */
public interface IGridBuilder {

	/**
	 * Builds the.
	 *
	 * @param request the request
	 * @param response the response
	 * @param grid the grid
	 * @return the string
	 */
	String build(GridRequest request, GridResponse response, Grid grid);
}