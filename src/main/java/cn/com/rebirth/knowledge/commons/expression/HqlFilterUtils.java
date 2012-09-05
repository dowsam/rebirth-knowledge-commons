/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons HqlFilterUtils.java 2012-9-3 11:16:53 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.expression;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import cn.com.rebirth.commons.utils.EncodeUtils;
import cn.com.rebirth.knowledge.commons.expression.ParamsExpression.MatchType;
import cn.com.rebirth.knowledge.commons.expression.ParamsExpression.PropertyType;

/**
 * The Class HqlFilterUtils.
 *
 * @author l.xue.nong
 */
public class HqlFilterUtils {

	/** The Constant logger. */
	private transient static final Logger logger = LoggerFactory.getLogger(HqlFilterUtils.class);

	/**
	 * Creates the params.
	 *
	 * @param <T> the generic type
	 * @param request the request
	 * @param entityClass the entity class
	 * @return the hql object
	 */
	public static <T> HqlObject createParams(final HttpServletRequest request, final Class<T> entityClass) {
		return createParams(request, "filter_", entityClass);
	}

	/**
	 * Creates the params.
	 *
	 * @param <T> the generic type
	 * @param request the request
	 * @param entityClass the entity class
	 * @param alias the alias
	 * @return the hql object
	 */
	public static <T> HqlObject createParams(final HttpServletRequest request, final Class<T> entityClass, String alias) {
		return createParams(request, "filter_", entityClass, alias);
	}

	/**
	 * Creates the params.
	 *
	 * @param <T> the generic type
	 * @param request the request
	 * @param filterPrefix the filter prefix
	 * @param entityClass the entity class
	 * @return the hql object
	 */
	public static <T> HqlObject createParams(final HttpServletRequest request, final String filterPrefix,
			final Class<T> entityClass) {
		return createParams(buildFiterProperty(request, filterPrefix), entityClass);
	}

	/**
	 * Creates the params.
	 *
	 * @param <T> the generic type
	 * @param request the request
	 * @param filterPrefix the filter prefix
	 * @param entityClass the entity class
	 * @param alias the alias
	 * @return the hql object
	 */
	public static <T> HqlObject createParams(final HttpServletRequest request, final String filterPrefix,
			final Class<T> entityClass, String alias) {
		return createParams(buildFiterProperty(request, filterPrefix), entityClass, alias);
	}

	/**
	 * Creates the params.
	 *
	 * @param <T> the generic type
	 * @param map the map
	 * @param entityClass the entity class
	 * @return the hql object
	 */
	public static <T> HqlObject createParams(Map<String, List<ParamsExpression>> map, Class<T> entityClass) {
		String hqlTop = "from " + entityClass.getSimpleName();
		return createParams(hqlTop, map, entityClass, "");
	}

	/**
	 * Creates the params.
	 *
	 * @param <T> the generic type
	 * @param map the map
	 * @param entityClass the entity class
	 * @param alias the alias
	 * @return the hql object
	 */
	public static <T> HqlObject createParams(Map<String, List<ParamsExpression>> map, Class<T> entityClass, String alias) {

		String hqlTop;
		//对象别名
		if (alias == null || "".equals(alias)) {
			hqlTop = "from " + entityClass.getSimpleName();
		} else {
			hqlTop = "select " + alias + " from " + entityClass.getSimpleName() + " as " + alias;
		}
		return createParams(hqlTop, map, entityClass, alias);
	}

	/**
	 * Creates the params.
	 *
	 * @param <T> the generic type
	 * @param hqlTop the hql top
	 * @param map the map
	 * @param entityClass the entity class
	 * @param alias the alias
	 * @return the hql object
	 */
	public static <T> HqlObject createParams(String hqlTop, Map<String, List<ParamsExpression>> map,
			Class<T> entityClass, String alias) {
		Assert.notNull(hqlTop, "查询语句不能为null");
		HqlObject hqlObject = new HqlObject();
		hqlObject.setAlias(alias);
		if ("".equals(hqlTop.trim())) {
			throw new IllegalArgumentException("查询语句不能为空");
		}
		//对象别名
		if (alias == null || "".equals(alias)) {
			alias = "";
		} else {
			alias = alias + ".";
		}
		StringBuffer stringBuffer = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		stringBuffer.append(hqlTop);
		if (!(null == map || map.isEmpty())) {
			stringBuffer.append(" where ");
			for (Map.Entry<String, List<ParamsExpression>> entry : map.entrySet()) {
				List<ParamsExpression> list = entry.getValue();
				if (!(list.size() > 1)) {
					stringBuffer.append("(").append(alias + list.get(0).getPropertyName());
					buildPropertyType(stringBuffer, list.get(0), values);
					stringBuffer.append(")").append(" and ");
				} else {
					stringBuffer.append("(");

					for (ParamsExpression paramsExpression : list) {
						stringBuffer.append(alias + paramsExpression.getPropertyName());
						buildPropertyType(stringBuffer, paramsExpression, values);

						stringBuffer.append(" or ");
					}
					if (stringBuffer.lastIndexOf(" or ") > -1) {
						stringBuffer.delete(stringBuffer.lastIndexOf(" or "), stringBuffer.length());
					}
					stringBuffer.append(")").append(" and ");
				}
			}
			stringBuffer.delete(stringBuffer.lastIndexOf(" and "), stringBuffer.length());
		}

		hqlObject.setQuery(stringBuffer.toString());
		hqlObject.setValues(values);
		hqlObject.setEntityClass(entityClass);
		logger.info("hqlTop=" + hqlTop);
		logger.info("alias=" + alias);
		logger.info("hqlObject.getQuery()=" + hqlObject.getQuery());
		logger.info("hqlObject.getValues().toString()=" + hqlObject.getValues().toString());
		logger.info(hqlObject.getEntityClass().getSimpleName());
		return hqlObject;
	}

	/**
	 * Creates the where condition.
	 *
	 * @param <T> the generic type
	 * @param p the p
	 * @param request the request
	 * @param entityClass the entity class
	 * @return the hql object
	 */
	public static <T> HqlObject createWhereCondition(String p, final HttpServletRequest request, Class<T> entityClass) {
		Map<String, List<ParamsExpression>> map = buildFiterProperty(request, "filter_");
		return (createWhereCondition(p, map, entityClass));
	}

	/**
	 * Creates the where condition.
	 *
	 * @param <T> the generic type
	 * @param p the p
	 * @param map the map
	 * @param entityClass the entity class
	 * @return the hql object
	 */
	public static <T> HqlObject createWhereCondition(String p, Map<String, List<ParamsExpression>> map,
			Class<T> entityClass) {
		StringBuffer stringBuffer = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		if (!(null == map || map.isEmpty())) {
			for (Map.Entry<String, List<ParamsExpression>> entry : map.entrySet()) {
				List<ParamsExpression> list = entry.getValue();
				if (!(list.size() > 1)) {
					stringBuffer.append("(").append(p + "." + list.get(0).getPropertyName());
					buildPropertyType(stringBuffer, list.get(0), values);
					stringBuffer.append(")").append(" and ");
				} else {
					stringBuffer.append("(");

					for (ParamsExpression paramsExpression : list) {
						stringBuffer.append(p + "." + paramsExpression.getPropertyName());
						buildPropertyType(stringBuffer, paramsExpression, values);

						stringBuffer.append(" or ");
					}
					if (stringBuffer.lastIndexOf(" or ") > -1) {
						stringBuffer.delete(stringBuffer.lastIndexOf(" or "), stringBuffer.length());
					}
					stringBuffer.append(")").append(" and ");
				}
			}
			stringBuffer.delete(stringBuffer.lastIndexOf(" and "), stringBuffer.length());
		}
		HqlObject hqlObject = new HqlObject();
		hqlObject.setQuery(stringBuffer.toString());
		hqlObject.setValues(values);
		hqlObject.setEntityClass(entityClass);
		logger.info(hqlObject.getQuery());
		logger.info(hqlObject.getValues().toString());
		logger.info(hqlObject.getEntityClass().getSimpleName());
		return hqlObject;
	}

	/**
	 * Builds the property type.
	 *
	 * @param stringBuffer the string buffer
	 * @param paramsExpression the params expression
	 * @param values the values
	 */
	private static void buildPropertyType(StringBuffer stringBuffer, ParamsExpression paramsExpression,
			List<Object> values) {
		if (MatchType.EQ.equals(paramsExpression.getMatchType())) {
			stringBuffer.append("=?");
			values.add(paramsExpression.getPropertyValue());
		} else if (MatchType.LIKE.equals(paramsExpression.getMatchType())) {
			stringBuffer.append(" like ?");
			values.add("%" + paramsExpression.getPropertyValue() + "%");
		} else if (MatchType.LIKESTART.equals(paramsExpression.getMatchType())) {
			stringBuffer.append(" like ?");
			values.add(paramsExpression.getPropertyValue() + "%");
		} else if (MatchType.LIKEEND.equals(paramsExpression.getMatchType())) {
			stringBuffer.append(" like ?");
			values.add("%" + paramsExpression.getPropertyValue());
		} else if (MatchType.LIKEANYWHERE.equals(paramsExpression.getMatchType())) {
			stringBuffer.append(" like ?");
			values.add("%" + paramsExpression.getPropertyValue() + "%");
		} else if (MatchType.LIKEEXACT.equals(paramsExpression.getMatchType())) {
			stringBuffer.append(" like ?");
			values.add(paramsExpression.getPropertyValue());
		} else if (MatchType.LT.equals(paramsExpression.getMatchType())) {
			stringBuffer.append("<?");
			values.add(paramsExpression.getPropertyValue());
		} else if (MatchType.GT.equals(paramsExpression.getMatchType())) {
			stringBuffer.append(">?");
			values.add(paramsExpression.getPropertyValue());
		} else if (MatchType.LE.equals(paramsExpression.getMatchType())) {
			stringBuffer.append("<=?");
			values.add(paramsExpression.getPropertyValue());
		} else if (MatchType.GE.equals(paramsExpression.getMatchType())) {
			stringBuffer.append(">=?");
			values.add(paramsExpression.getPropertyValue());
		} else if (MatchType.ISNULL.equals(paramsExpression.getMatchType())) {
			//stringBuffer.append(" is ?");
			//values.add(paramsExpression.getPropertyValue());
			stringBuffer.append(" is null");
		} else if (MatchType.ISNOTNULL.equals(paramsExpression.getMatchType())) {
			//stringBuffer.append(" is not ?");
			//values.add(paramsExpression.getPropertyValue());
			stringBuffer.append(" is not null");
		} else if (MatchType.IN.equals(paramsExpression.getMatchType())) {
			stringBuffer.append(" in (?)");
			values.add(paramsExpression.getPropertyValue());
		} else if (MatchType.NOTIN.equals(paramsExpression.getMatchType())) {
			stringBuffer.append(" not in (?)");
			values.add(paramsExpression.getPropertyValue());
		} else if (MatchType.BETWEEN.equals(paramsExpression.getMatchType())) {
			stringBuffer.append("between ? and ?");
			values.add(paramsExpression.getLo());
			values.add(paramsExpression.getHi());
		} else if (MatchType.NOTBETWEEN.equals(paramsExpression.getMatchType())) {
			stringBuffer.append(" not between ? and ?");
			values.add(paramsExpression.getLo());
			values.add(paramsExpression.getHi());
		}
	}

	/**
	 * Builds the fiter property.
	 *
	 * @param request the request
	 * @param filterPrefix the filter prefix
	 * @return the map
	 */
	private static Map<String, List<ParamsExpression>> buildFiterProperty(HttpServletRequest request,
			String filterPrefix) {
		// new Map存入列表
		Map<String, List<ParamsExpression>> map = new HashMap<String, List<ParamsExpression>>();

		// 从request中获取含属性前缀名的参数,构造去除前缀名后的参数Map.
		Map<String, String[]> filterParamMap = getParametersStartingWith(request, filterPrefix);

		// 分析Map,构造ParamsExpression列表
		for (Map.Entry<String, String[]> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String[] values = entry.getValue();

			for (String string2 : values) {
				String value = EncodeUtils.urlEncode(string2);

				// 解析后的参数,格式.LIKES_name
				String matchTypeStr = StringUtils.substringBefore(filterName, "_");// LIKES
				String matchTypeCode = StringUtils.substring(matchTypeStr, 0, matchTypeStr.length() - 1);// LIKE
				String propertyTypeCode = StringUtils.substring(matchTypeStr, matchTypeStr.length() - 1,
						matchTypeStr.length());// S
				String propertyNameStr = StringUtils.substringAfter(filterName, "_");// name
				MatchType matchType = null;
				try {
					matchType = Enum.valueOf(MatchType.class, matchTypeCode);
				} catch (RuntimeException e) {
					throw new IllegalArgumentException("filter名称" + filterName + "没有按规则编写,无法得到属性比较类型.", e);
				}

				Class<?> propertyType = null;

				try {
					propertyType = Enum.valueOf(PropertyType.class, propertyTypeCode).getValue();
				} catch (RuntimeException e) {
					throw new IllegalArgumentException("filter名称" + filterName + "没有按规则编写,无法得到属性值类型.", e);
				}

				List<ParamsExpression> list = map.get(filterName);
				if (list == null) {
					list = new ArrayList<ParamsExpression>();
				}

				// 如果value值为空,则忽略此filter.
				if (value == null || StringUtils.isNotBlank(value)) {
					String[] tem = value.trim().split(ParamsExpression.SEPARATOR);
					for (String string : tem) {
						if (string.trim().startsWith("*") && string.trim().endsWith("*")) {
							list.add(new ParamsExpression(MatchType.LIKEANYWHERE, propertyType, propertyNameStr.trim(),
									string.replace("*", "").trim()));
						} else if (string.trim().startsWith("*")) {
							list.add(new ParamsExpression(MatchType.LIKEEND, propertyType, propertyNameStr.trim(),
									string.replace("*", "").trim()));
						} else if (string.trim().endsWith("*")) {
							list.add(new ParamsExpression(MatchType.LIKESTART, propertyType, propertyNameStr.trim(),
									string.replace("*", "").trim()));
						} else {
							list.add(new ParamsExpression(matchType, propertyType, propertyNameStr.trim(), string
									.trim()));
						}
					}
					map.put(filterName, list);
				}
			}

		}
		return map;
	}

	/**
	 * Gets the parameters starting with.
	 *
	 * @param request the request
	 * @param filterPrefix the filter prefix
	 * @return the parameters starting with
	 */
	private static Map<String, String[]> getParametersStartingWith(HttpServletRequest request, String filterPrefix) {
		Assert.notNull(request, "Request must not be null");
		Enumeration<?> paramNames = request.getParameterNames();
		Map<String, String[]> params = new TreeMap<String, String[]>();
		if (filterPrefix == null) {
			filterPrefix = "";
		}
		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			if ("".equals(filterPrefix) || paramName.startsWith(filterPrefix)) {
				String unprefixed = paramName.substring(filterPrefix.length());
				String[] values = request.getParameterValues(paramName);
				if (values == null || values.length == 0) {
					// Do nothing, no values found at all.
				} else {
					params.put(unprefixed, values);
				}
			}
		}
		return params;
	}

}
