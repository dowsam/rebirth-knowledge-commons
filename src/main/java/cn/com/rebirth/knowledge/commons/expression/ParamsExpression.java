/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons ParamsExpression.java 2012-9-3 11:14:54 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.expression;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import cn.com.rebirth.commons.exception.RebirthIllegalArgumentException;
import cn.com.rebirth.commons.utils.ConvertUtils;

/**
 * The Class ParamsExpression.
 *
 * @author l.xue.nong
 */
public class ParamsExpression {

	/** The property name. */
	private String propertyName;// 字段名

	/** The property value. */
	private Object propertyValue;// 字段值

	/** The match type. */
	private MatchType matchType;// 条件

	/** The property type. */
	private Class<?> propertyType;// 字段值的类型

	/** select in 存储的集合对象 传递的参数值的格式:(1,2,3);. */
	private Collection<Object> inCollection;

	/** select between 查询:[lo,hi]. */
	private Object lo;

	/** The hi. */
	private Object hi;

	/**
	 * Instantiates a new params expression.
	 */
	ParamsExpression() {

	}

	/**
	 * Instantiates a new params expression.
	 *
	 * @param matchType the match type
	 * @param propertyType the property type
	 * @param propertyName the property name
	 * @param propertyValue the property value
	 */
	public ParamsExpression(final MatchType matchType, final Class<?> propertyType, final String propertyName,
			final Object propertyValue) {
		this.matchType = matchType;
		this.propertyType = propertyType;
		if (MatchType.IN.equals(this.matchType)) {
			bulidPropertyCollection(propertyValue);
		} else if (MatchType.NOTIN.equals(this.matchType)) {
			bulidPropertyCollection(propertyValue);
		} else if (MatchType.BETWEEN.equals(this.matchType)) {
			lo = bulidPropertyBetween(propertyValue, 0);
			hi = bulidPropertyBetween(propertyValue, 1);
		} else if (MatchType.NOTBETWEEN.equals(this.matchType)) {
			lo = bulidPropertyBetween(propertyValue, 0);
			hi = bulidPropertyBetween(propertyValue, 1);
		} else {
			this.propertyValue = ConvertUtils.convertObjectToObject(propertyValue, propertyType);
		}
		this.propertyName = propertyName;
	}

	/**
	 * Instantiates a new params expression.
	 *
	 * @param filterName the filter name
	 * @param value the value
	 */
	public ParamsExpression(final String filterName, final Object value) {
		String matchTypeStr = StringUtils.substringBefore(filterName, "_");
		String matchTypeCode = StringUtils.substring(matchTypeStr, 0, matchTypeStr.length() - 1);
		String propertyTypeCode = StringUtils.substring(matchTypeStr, matchTypeStr.length() - 1, matchTypeStr.length());
		try {
			matchType = Enum.valueOf(MatchType.class, matchTypeCode);
		} catch (RuntimeException e) {
			throw new IllegalArgumentException("filter名称" + filterName + "没有按规则编写,无法得到属性比较类型.", e);
		}

		try {
			propertyType = Enum.valueOf(PropertyType.class, propertyTypeCode).getValue();
		} catch (RuntimeException e) {
			throw new IllegalArgumentException("filter名称" + filterName + "没有按规则编写,无法得到属性值类型.", e);
		}

		if (MatchType.IN.equals(this.matchType)) {
			bulidPropertyCollection(value);
		} else if (MatchType.NOTIN.equals(this.matchType)) {
			bulidPropertyCollection(value);
		} else if (MatchType.BETWEEN.equals(this.matchType)) {
			lo = bulidPropertyBetween(value, 0);
			hi = bulidPropertyBetween(value, 1);
		} else if (MatchType.NOTBETWEEN.equals(this.matchType)) {
			lo = bulidPropertyBetween(value, 0);
			hi = bulidPropertyBetween(value, 1);
		} else {
			this.propertyValue = ConvertUtils.convertObjectToObject(value, propertyType);
		}
		this.propertyName = StringUtils.substringAfter(filterName, "_");
	}

	/**
	 * Bulid property between.
	 *
	 * @param value the value
	 * @param i the i
	 * @return the object
	 */
	private Object bulidPropertyBetween(Object value, int i) {
		String v = (String) value;
		if (v.startsWith("[") && v.endsWith("]")) {
			v = v.replace("[", "").replace("]", "").trim();
			String[] tem = v.split(",");
			if (tem.length >= 2) {
				return ConvertUtils.convertObjectToObject(tem[i], propertyType);
			} else if (tem.length <= 1) {
				return ConvertUtils.convertObjectToObject(tem[0], propertyType);
			}
		}
		return null;
	}

	/**
	 * Bulid property collection.
	 *
	 * @param convertValue the convert value
	 */
	private void bulidPropertyCollection(Object convertValue) {
		String value = (String) convertValue;
		inCollection = new ArrayList<Object>();
		if (value.startsWith("(") && value.endsWith(")")) {
			value = value.replace("(", "").replace(")", "").trim();
			String[] tem = value.split(",");
			for (String string : tem) {
				inCollection.add(ConvertUtils.convertObjectToObject(string.trim(), propertyType));
			}
		}
	}

	/**
	 * LIKES_name.
	 *
	 * @param filterName the filter name
	 * @param value the value
	 * @param matchType the match type
	 */
	public ParamsExpression(final String filterName, final Object value, final MatchType matchType) {
		String matchTypeStr = StringUtils.substringBefore(filterName, "_");
		String propertyTypeCode = StringUtils.substring(matchTypeStr, matchTypeStr.length() - 1, matchTypeStr.length());
		this.matchType = matchType;
		this.propertyType = Enum.valueOf(PropertyType.class, propertyTypeCode).getValue();

		if (MatchType.IN.equals(this.matchType)) {
			bulidPropertyCollection(value);
		} else if (MatchType.NOTIN.equals(this.matchType)) {
			bulidPropertyCollection(value);
		} else if (MatchType.BETWEEN.equals(this.matchType)) {
			lo = bulidPropertyBetween(value, 0);
			hi = bulidPropertyBetween(value, 1);
		} else if (MatchType.NOTBETWEEN.equals(this.matchType)) {
			lo = bulidPropertyBetween(value, 0);
			hi = bulidPropertyBetween(value, 1);
		} else {
			this.propertyValue = ConvertUtils.convertObjectToObject(value, propertyType);
		}
		this.propertyName = StringUtils.substringAfter(filterName, "_");
	}

	/** 多值与分号隔开. */
	public static final String SEPARATOR = ";";

	/** 多属性与_OR_分开. */
	public static final String OR_SEPARATOR = "_OR_";

	/** 多属性并_AND_分开. */
	public static final String AND_SEPARATOR = "_AND_";

	/**
	 * 属性比较类型.
	 *
	 * @author l.xue.nong
	 */
	public enum MatchType {

		/** The eq. */
		EQ,
		/** The noteq. */
		NOTEQ,
		/** The like. */
		LIKE,
		/** The likestart. */
		LIKESTART,
		/** The likeend. */
		LIKEEND,
		/** The likeanywhere. */
		LIKEANYWHERE,
		/** The likeexact. */
		LIKEEXACT,
		/** The notlike. */
		NOTLIKE,
		/** The lt. */
		LT,
		/** The gt. */
		GT,
		/** The le. */
		LE,
		/** The ge. */
		GE,
		/** The isnull. */
		ISNULL,
		/** The isnotnull. */
		ISNOTNULL,
		/** The in. */
		IN,
		/** The notin. */
		NOTIN,
		/** The between. */
		BETWEEN,
		/** The notbetween. */
		NOTBETWEEN;
	}

	/**
	 * 属性数据类型.
	 *
	 * @author l.xue.nong
	 */
	public enum PropertyType {

		/** The s. */
		S(String.class),
		/** The i. */
		I(Integer.class),
		/** The i. */
		i(int.class),
		/** The l. */
		L(Long.class),
		/** The l. */
		l(long.class),
		/** The n. */
		N(Double.class),
		/** The n. */
		n(double.class),
		/** The d. */
		D(Date.class),
		/** The b. */
		B(Boolean.class),
		/** The b. */
		b(boolean.class);

		/** The clazz. */
		private Class<?> clazz;

		/**
		 * Instantiates a new property type.
		 *
		 * @param clazz the clazz
		 */
		PropertyType(Class<?> clazz) {
			this.clazz = clazz;
		}

		/**
		 * Gets the value.
		 *
		 * @return the value
		 */
		public Class<?> getValue() {
			return clazz;
		}

		/**
		 * Resolve.
		 *
		 * @param clazz the clazz
		 * @return the property type
		 */
		public static PropertyType resolve(Class<?> clazz) {
			PropertyType[] propertyTypes = PropertyType.class.getEnumConstants();
			for (PropertyType propertyType : propertyTypes) {

				if (propertyType.getValue().equals(clazz)) {
					return propertyType;
				}
			}
			throw new RebirthIllegalArgumentException("Not find class enum:" + clazz);
		}
	}

	/**
	 * Gets the property name.
	 *
	 * @return the property name
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * Sets the property name.
	 *
	 * @param propertyName the new property name
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * Gets the property value.
	 *
	 * @return the property value
	 */
	public Object getPropertyValue() {
		return propertyValue;
	}

	/**
	 * Sets the property value.
	 *
	 * @param propertyValue the new property value
	 */
	public void setPropertyValue(Object propertyValue) {
		this.propertyValue = propertyValue;
	}

	/**
	 * Gets the match type.
	 *
	 * @return the match type
	 */
	public MatchType getMatchType() {
		return matchType;
	}

	/**
	 * Sets the match type.
	 *
	 * @param matchType the new match type
	 */
	public void setMatchType(MatchType matchType) {
		this.matchType = matchType;
	}

	/**
	 * Gets the property type.
	 *
	 * @return the property type
	 */
	public Class<?> getPropertyType() {
		return propertyType;
	}

	/**
	 * Sets the property type.
	 *
	 * @param propertyType the new property type
	 */
	public void setPropertyType(Class<?> propertyType) {
		this.propertyType = propertyType;
	}

	/**
	 * Gets the in collection.
	 *
	 * @return the in collection
	 */
	public Collection<Object> getInCollection() {
		return inCollection;
	}

	/**
	 * Sets the in collection.
	 *
	 * @param inCollection the new in collection
	 */
	public void setInCollection(Collection<Object> inCollection) {
		this.inCollection = inCollection;
	}

	/**
	 * Gets the lo.
	 *
	 * @return the lo
	 */
	public Object getLo() {
		return lo;
	}

	/**
	 * Sets the lo.
	 *
	 * @param lo the new lo
	 */
	public void setLo(Object lo) {
		this.lo = lo;
	}

	/**
	 * Gets the hi.
	 *
	 * @return the hi
	 */
	public Object getHi() {
		return hi;
	}

	/**
	 * Sets the hi.
	 *
	 * @param hi the new hi
	 */
	public void setHi(Object hi) {
		this.hi = hi;
	}
}
