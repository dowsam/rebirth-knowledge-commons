/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons GridRequest.java 2012-8-3 21:35:45 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.rebirth.commons.utils.BeanUtils;
import cn.com.rebirth.knowledge.commons.dhtmlx.utils.json.JSONObject;

/**
 * The Class GridRequest.
 *
 * @author l.xue.nong
 */
public class GridRequest {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(GridRequest.class);

	/** The Constant PARAM_DATA_QUERIER_KEY. */
	public static final String PARAM_DATA_QUERIER_KEY = "dataQuerier";

	/** The Constant PARAM_GRID_TYPE_KEY. */
	public static final String PARAM_GRID_TYPE_KEY = "gridType";

	/** The Constant PARAM_DAO_NAME_KEY. */
	public static final String PARAM_DAO_NAME_KEY = "daoName";

	/** The Constant PARAM_FILTER_SET_KEY. */
	private static final String PARAM_FILTER_SET_KEY = "filterSet";

	/** The Constant PARAM_SQL_ID_KEY. */
	public static final String PARAM_SQL_ID_KEY = "sqlId";

	/** The Constant PARAM_COUNT_SQLID_KEY. */
	public static final String PARAM_COUNT_SQLID_KEY = "countSqlId";

	/** The Constant PARAM_SORT_KEY. */
	private static final String PARAM_SORT_KEY = "orderBy";

	/** The http request. */
	private HttpServletRequest httpRequest;

	/** The parameters. */
	private Map<String, Object> parameters = new HashMap<String, Object>();

	/** The post parameters. */
	private JSONObject postParameters;

	/** The data querier. */
	private String dataQuerier;

	/** The grid type. */
	private String gridType;

	/** The dao name. */
	private String daoName;

	/** The sql id. */
	private String sqlId;

	/** The count sql id. */
	private String countSqlId;

	/** The order by. */
	private String orderBy = null;

	/**
	 * Instantiates a new grid request.
	 */
	public GridRequest() {

	}

	/**
	 * Instantiates a new grid request.
	 *
	 * @param request the request
	 * @param loadRequestParams the load request params
	 */
	@SuppressWarnings("unchecked")
	public GridRequest(HttpServletRequest request, boolean loadRequestParams) {
		this.httpRequest = request;
		if (null != request && loadRequestParams) {
			Enumeration<String> e = request.getParameterNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String value = request.getParameter(key);
				logger.debug("load request params:{}={}", key, value);
				if (key.equalsIgnoreCase(PARAM_GRID_TYPE_KEY)) {
					this.gridType = value;
				} else if (key.equalsIgnoreCase(PARAM_DATA_QUERIER_KEY)) {
					this.dataQuerier = value;
				} else if (key.equalsIgnoreCase(PARAM_DAO_NAME_KEY)) {
					this.daoName = value;
				} else if (key.equalsIgnoreCase(PARAM_SQL_ID_KEY)) {
					this.sqlId = value;
				} else if (key.equalsIgnoreCase(PARAM_COUNT_SQLID_KEY)) {
					this.countSqlId = value;
				} else if (key.equalsIgnoreCase(PARAM_SORT_KEY)) {
					this.orderBy = value;
				}
				parameters.put(key, value);
			}
			// 读取post流参数
			postParameters = readJSONFormRequest(request);
			readeFromPostParameters();
		}
	}

	/**
	 * Adds the parameter.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public void addParameter(String key, String value) {
		parameters.put(key, value);
	}

	/**
	 * Sets the http request.
	 *
	 * @param httpRequest the new http request
	 */
	public void setHttpRequest(HttpServletRequest httpRequest) {
		this.httpRequest = httpRequest;
	}

	/**
	 * Gets the sql id.
	 *
	 * @return the sql id
	 */
	public String getSqlId() {
		return sqlId;
	}

	/**
	 * Gets the order by.
	 *
	 * @return the order by
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * Sets the order by.
	 *
	 * @param orderBy the new order by
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * Gets the count sql id.
	 *
	 * @return the count sql id
	 */
	public String getCountSqlId() {
		return countSqlId;
	}

	/**
	 * Sets the count sql id.
	 *
	 * @param countSqlId the new count sql id
	 */
	public void setCountSqlId(String countSqlId) {
		this.countSqlId = countSqlId;
	}

	/**
	 * Sets the sql id.
	 *
	 * @param sqlId the new sql id
	 */
	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}

	/**
	 * Sets the parameters.
	 *
	 * @param parameters the parameters
	 */
	protected void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	/**
	 * Gets the http request.
	 *
	 * @return the http request
	 */
	public HttpServletRequest getHttpRequest() {
		return httpRequest;
	}

	/**
	 * Gets the post parameters.
	 *
	 * @return the post parameters
	 */
	public JSONObject getPostParameters() {
		return postParameters;
	}

	/**
	 * Sets the post parameters.
	 *
	 * @param postParameters the new post parameters
	 */
	public void setPostParameters(JSONObject postParameters) {
		this.postParameters = postParameters;
	}

	/**
	 * Gets the parameter.
	 *
	 * @param key the key
	 * @return the parameter
	 */
	public Object getParameter(String key) {
		return parameters.get(key);
	}

	/**
	 * Checks if is param exists.
	 *
	 * @param key the key
	 * @return true, if is param exists
	 */
	public boolean isParamExists(String key) {
		return parameters.containsKey(key);
	}

	/**
	 * Gets the parameters.
	 *
	 * @return the parameters
	 */
	public Map<String, Object> getParameters() {
		return parameters;
	}

	/**
	 * Read json form request.
	 *
	 * @param request the request
	 * @return the jSON object
	 */
	private JSONObject readJSONFormRequest(HttpServletRequest request) {
		final int buf_size = 4096;
		JSONObject jsonObject = null;
		BufferedReader inputStream = null;

		try {
			inputStream = new BufferedReader(new InputStreamReader(request.getInputStream(), Configuration
					.getInstance().getCharacterEncoding()));
			CharArrayWriter data = new CharArrayWriter();
			char buf[] = new char[buf_size];
			int ret;
			while ((ret = inputStream.read(buf, 0, buf_size)) != -1) {
				data.write(buf, 0, ret);
			}
			if (data.size() == 0)
				return jsonObject;
			jsonObject = new JSONObject(data.toString());
		} catch (UnsupportedEncodingException e) {
			logger.error("read request post data error：don't support post encoding", e);
		} catch (IOException e) {
			logger.error("read request post data error：io exception", e);
		} catch (ParseException e) {
			logger.error("read request post data error：parse json error", e);
		} finally {
			IOUtils.closeQuietly(inputStream);
		}

		logger.debug("Received request post data:{}", jsonObject);
		return jsonObject;
	}

	/**
	 * Reade from post parameters.
	 */
	private void readeFromPostParameters() {
		// 获取排序信息
		JSONObject postParams = getPostParameters();
		if (null == postParams)
			return;
		JSONObject orderInfo = postParams.optJSONObject(PARAM_SORT_KEY);
		if (orderInfo != null && StringUtils.isNotEmpty(orderInfo.optString("fieldName"))) {
			this.orderBy = orderInfo.optString("fieldName") + " " + orderInfo.optString("orderMethod");
			this.addParameter(PARAM_SORT_KEY, this.orderBy);
		}
		// 读取查询条件信息
		JSONObject filterMap = postParams.optJSONObject(PARAM_FILTER_SET_KEY);
		if (filterMap != null) {
			for (Object key : filterMap.getParams().keySet().toArray()) {
				this.addParameter(String.valueOf(key), String.valueOf(filterMap.getParams().get(key)));
			}
		}
		// 读取导出相关的参数信息
		addExportParams(postParams, "fileName");
		addExportParams(postParams, "dataExporter");
		addExportParams(postParams, "rowConverter");
		addExportParams(postParams, "exportColumnIds");
		addExportParams(postParams, "exportColumnTypes");
		addExportParams(postParams, "exportColumnHeaders");
		addExportParams(postParams, "templateFileName");
		addExportParams(postParams, "exportColumnAligns");
	}

	/**
	 * Adds the export params.
	 *
	 * @param postParams the post params
	 * @param paramName the param name
	 */
	private void addExportParams(JSONObject postParams, String paramName) {
		this.addParameter(paramName, postParams.optString(paramName));
	}

	/**
	 * Gets the context path.
	 *
	 * @return the context path
	 */
	public String getContextPath() {
		if (null != getHttpRequest()) {
			String path = getHttpRequest().getContextPath();
			if (path.endsWith("/")) {
				path = path.substring(0, path.length() - 1);
			}
			return path;
		}
		return "";
	}

	/**
	 * Builds the params as query string.
	 *
	 * @return the string
	 */
	public String buildParamsAsQueryString() {
		Map<String, Object> requestParams = getParameters();
		StringBuilder urlParams = new StringBuilder();
		for (Object key : requestParams.keySet().toArray()) {
			urlParams.append("&").append(key).append("=").append(requestParams.get(key));
		}
		if (urlParams.toString().trim().length() == 0)
			return "";
		return "?" + urlParams.substring(1);
	}

	/**
	 * To int.
	 *
	 * @param rawValue the raw value
	 * @return the int
	 */
	protected int toInt(String rawValue) {
		if (StringUtils.isEmpty(rawValue)) {
			return 0;
		}
		return Integer.valueOf(rawValue);
	}

	/**
	 * To boolean.
	 *
	 * @param rawValue the raw value
	 * @return true, if successful
	 */
	protected boolean toBoolean(String rawValue) {
		if (null == rawValue) {
			return true;
		}
		return Boolean.valueOf(rawValue);
	}

	/**
	 * Read requred param.
	 *
	 * @param <T> the generic type
	 * @param paramName the param name
	 * @param request the request
	 * @param clazz the clazz
	 * @param defaultVavlue the default vavlue
	 * @param throwExceptionWhenNotFound the throw exception when not found
	 */
	protected <T> void readRequredParam(String paramName, GridRequest request, Class<T> clazz, T defaultVavlue,
			boolean throwExceptionWhenNotFound) {
		Object paramValue = parameters.get(paramName);
		if (null == paramValue || paramValue instanceof String && StringUtils.isEmpty((String) paramValue)) {
			paramValue = defaultVavlue;
		}
		if (null != paramValue) {
			if (String.class == clazz) {
				BeanUtils.setProperty(request, paramName, (String) paramValue);
				return;
			}
			if (Integer.class == clazz || int.class == clazz) {
				BeanUtils.setProperty(request, paramName, toInt((String) paramValue));
				return;
			}
			if (Boolean.class == clazz) {
				BeanUtils.setProperty(request, paramName, toBoolean((String) paramValue));
				return;
			}
		}
		if (throwExceptionWhenNotFound) {
			throwGridException(paramName);
		}
	}

	/**
	 * Throw grid exception.
	 *
	 * @param paramName the param name
	 */
	protected void throwGridException(String paramName) {
		throw new GridExceptoin(String.format("param:[%s] must be past by request.", paramName));
	}

	/**
	 * Gets the data querier.
	 *
	 * @return the data querier
	 */
	public String getDataQuerier() {
		return dataQuerier;
	}

	/**
	 * Sets the data querier.
	 *
	 * @param dataQuerier the new data querier
	 */
	public void setDataQuerier(String dataQuerier) {
		this.dataQuerier = dataQuerier;
	}

	/**
	 * Gets the grid type.
	 *
	 * @return the grid type
	 */
	public String getGridType() {
		return gridType;
	}

	/**
	 * Sets the grid type.
	 *
	 * @param gridType the new grid type
	 */
	public void setGridType(String gridType) {
		this.gridType = gridType;
	}

	/**
	 * Gets the dao name.
	 *
	 * @return the dao name
	 */
	public String getDaoName() {
		return daoName;
	}

	/**
	 * Sets the dao name.
	 *
	 * @param daoName the new dao name
	 */
	public void setDaoName(String daoName) {
		this.daoName = daoName;
	}
}