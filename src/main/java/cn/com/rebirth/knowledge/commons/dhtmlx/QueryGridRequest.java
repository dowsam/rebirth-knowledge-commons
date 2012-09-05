/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons QueryGridRequest.java 2012-8-3 21:35:45 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx;

import cn.com.rebirth.knowledge.commons.dhtmlx.utils.json.JSONObject;

/**
 * The Class QueryGridRequest.
 *
 * @author l.xue.nong
 */
public class QueryGridRequest extends GridRequest {

	/** The Constant PARAM_START_KEY. */
	private static final String PARAM_START_KEY = "start";

	/** The Constant PARAM_LIMIT_KEY. */
	private static final String PARAM_LIMIT_KEY = "limit";

	/** The Constant PARAM_TOTLE_KEY. */
	private static final String PARAM_TOTLE_KEY = "total";

	/** The Constant PARAM_SUM_SQLID_KEY. */
	public static final String PARAM_SUM_SQLID_KEY = "sumSqlId";

	/** The Constant PARAM_RECOUNT_KEY. */
	private static final String PARAM_RECOUNT_KEY = "recount";

	/** The start. */
	private int start = 0;

	/** The limit. */
	private int limit = 0;

	/** The total. */
	private int total = 0;

	/** The sum sql id. */
	private String sumSqlId;

	/** The recount. */
	private boolean recount = true;

	/**
	 * Instantiates a new query grid request.
	 *
	 * @param request the request
	 */
	public QueryGridRequest(GridRequest request) {
		this.setPostParameters(request.getPostParameters());
		this.setParameters(request.getParameters());
		this.setHttpRequest(request.getHttpRequest());
		this.setOrderBy(request.getOrderBy());
		this.setOtherParameters();

	}

	/**
	 * Sets the other parameters.
	 */
	private void setOtherParameters() {
		readRequredParam(PARAM_START_KEY, this, int.class, null, false);
		readRequredParam(PARAM_LIMIT_KEY, this, int.class, null, false);
		readRequredParam(PARAM_TOTLE_KEY, this, int.class, null, false);
		readRequredParam(PARAM_SUM_SQLID_KEY, this, String.class, null, false);
		readRequredParam(PARAM_RECOUNT_KEY, this, boolean.class, null, false);
		readeFromPostParameters();
		if (this.limit == 0) {
			this.limit = Configuration.getInstance().getPageSetting().getSize();
		}
	}

	/**
	 * Reade from post parameters.
	 */
	private void readeFromPostParameters() {
		JSONObject postParams = getPostParameters();

		// 获取分页起始信息
		this.start = postParams.optInt(PARAM_START_KEY, this.start);
		this.limit = postParams.optInt(PARAM_LIMIT_KEY, this.limit);

		// 获取现有数据总数信息
		this.total = postParams.optInt(PARAM_TOTLE_KEY, this.total);
	}

	/**
	 * Gets the start.
	 *
	 * @return the start
	 */
	public int getStart() {
		return start;
	}

	/**
	 * Sets the start.
	 *
	 * @param start the new start
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * Gets the limit.
	 *
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * Sets the limit.
	 *
	 * @param limit the new limit
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * Gets the end.
	 *
	 * @return the end
	 */
	public int getEnd() {
		return this.start + this.limit;
	}

	/**
	 * Gets the total.
	 *
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * Sets the total.
	 *
	 * @param total the new total
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * Gets the sum sql id.
	 *
	 * @return the sum sql id
	 */
	public String getSumSqlId() {
		return sumSqlId;
	}

	/**
	 * Sets the sum sql id.
	 *
	 * @param sumSqlId the new sum sql id
	 */
	public void setSumSqlId(String sumSqlId) {
		this.sumSqlId = sumSqlId;
	}

	/**
	 * Checks if is recount.
	 *
	 * @return true, if is recount
	 */
	public boolean isRecount() {
		return recount;
	}

	/**
	 * Sets the recount.
	 *
	 * @param recount the new recount
	 */
	public void setRecount(boolean recount) {
		this.recount = recount;
	}
}