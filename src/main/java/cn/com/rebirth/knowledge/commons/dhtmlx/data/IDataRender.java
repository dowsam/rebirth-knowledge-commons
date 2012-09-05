/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons IDataRender.java 2012-8-3 21:31:54 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx.data;

import cn.com.rebirth.commons.Page;
import cn.com.rebirth.commons.PageRequest;
import cn.com.rebirth.knowledge.commons.dhtmlx.GridRequest;
import cn.com.rebirth.knowledge.commons.dhtmlx.GridResponse;

/**
 * The Interface IDataRender.
 *
 * @author l.xue.nong
 */
public interface IDataRender {

	/**
	 * Render.
	 *
	 * @param dataSet the data set
	 * @param request the request
	 * @param response the response
	 */
	<T> Page<T> render(PageRequest pageRequest, GridRequest request, GridResponse response);
}