/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons JSONEncoder.java 2012-8-3 21:34:58 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx.utils.json;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The Class JSONEncoder.
 *
 * @author l.xue.nong
 */
@SuppressWarnings("all")
public final class JSONEncoder {

	/** The Constant NULL_STRING. */
	public static final String NULL_STRING = "null";

	/** The Constant EMPTY_OBJECT. */
	public static final String EMPTY_OBJECT = "{}";

	/** The trim space. */
	private boolean trimSpace = true;

	/** The ignore null. */
	private boolean ignoreNull = true;

	/** The ignore empty. */
	private boolean ignoreEmpty = true;

	/** The aliases. */
	private Map<String, String> aliases = new HashMap<String, String>();

	/** The ignores. */
	private Map<String, Object> ignores = new HashMap<String, Object>();

	/** The includes. */
	private Map<String, Object> includes = new HashMap<String, Object>();

	/**
	 * Instantiates a new jSON encoder.
	 */
	public JSONEncoder() {

	}

	/**
	 * Alias.
	 *
	 * @param alias the alias
	 * @param property the property
	 */
	public void alias(String alias, String property) {
		this.aliases.put("prop$" + property, alias);
	}

	/**
	 * Alias.
	 *
	 * @param alias the alias
	 * @param type the type
	 * @param property the property
	 */
	public void alias(String alias, Class<?> type, String property) {
		this.aliases.put(type.getName() + "$" + property, alias);
	}

	/**
	 * Alias.
	 *
	 * @param alias the alias
	 * @param type the type
	 */
	public void alias(String alias, Class<?> type) {
		this.aliases.put("type$" + type.getName(), alias);
	}

	/**
	 * Ignore.
	 *
	 * @param property the property
	 */
	public void ignore(String property) {
		this.ignores.put("prop$" + property, null);
	}

	/**
	 * Ignore.
	 *
	 * @param type the type
	 * @param property the property
	 */
	public void ignore(Class<?> type, String property) {
		this.ignores.put(type.getName() + "$" + property, null);
	}

	/**
	 * Ignore.
	 *
	 * @param type the type
	 */
	public void ignore(Class<?> type) {
		this.ignores.put("type$" + type.getName(), null);
	}

	/**
	 * Ignore.
	 *
	 * @param property the property
	 * @param value the value
	 */
	public void ignore(String property, Object value) {
		this.ignores.put("prop$" + property, value);
	}

	/**
	 * Ignore.
	 *
	 * @param type the type
	 * @param value the value
	 */
	public void ignore(Class<?> type, Object value) {
		this.ignores.put("type$" + type.getName(), value);
	}

	/**
	 * Ignore.
	 *
	 * @param parent the parent
	 * @param type the type
	 */
	public void ignore(Class<?> parent, Class<?> type) {
		this.ignores.put("parent$" + parent.getName() + ",type$" + type.getName(), null);
	}

	/**
	 * Ignore.
	 *
	 * @param type the type
	 * @param property the property
	 * @param value the value
	 */
	public void ignore(Class<?> type, String property, Object value) {
		this.ignores.put(type.getName() + "$" + property, value);
	}

	/**
	 * Include.
	 *
	 * @param property the property
	 */
	public void include(String property) {
		this.includes.put("prop$" + property, null);
	}

	/**
	 * Include.
	 *
	 * @param type the type
	 * @param property the property
	 */
	public void include(Class<?> type, String property) {
		this.includes.put(type.getName() + "$" + property, null);
	}

	/**
	 * Include.
	 *
	 * @param type the type
	 */
	public void include(Class<?> type) {
		this.includes.put("type$" + type.getName(), null);
	}

	/**
	 * Include.
	 *
	 * @param property the property
	 * @param value the value
	 */
	public void include(String property, Object value) {
		this.includes.put("prop$" + property, value);
	}

	/**
	 * Include.
	 *
	 * @param type the type
	 * @param value the value
	 */
	public void include(Class<?> type, Object value) {
		this.includes.put("type$" + type.getName(), value);
	}

	/**
	 * Include.
	 *
	 * @param parent the parent
	 * @param type the type
	 */
	public void include(Class<?> parent, Class<?> type) {
		this.includes.put("parent$" + parent.getName() + ",type$" + type.getName(), null);
	}

	/**
	 * Include.
	 *
	 * @param type the type
	 * @param property the property
	 * @param value the value
	 */
	public void include(Class<?> type, String property, Object value) {
		this.includes.put(type.getName() + "$" + property, value);
	}

	/**
	 * Checks if is trim space.
	 *
	 * @return true, if is trim space
	 */
	public boolean isTrimSpace() {
		return trimSpace;
	}

	/**
	 * Sets the trim space.
	 *
	 * @param trimSpace the new trim space
	 */
	public void setTrimSpace(boolean trimSpace) {
		this.trimSpace = trimSpace;
	}

	/**
	 * Checks if is ignore null.
	 *
	 * @return true, if is ignore null
	 */
	public boolean isIgnoreNull() {
		return ignoreNull;
	}

	/**
	 * Sets the ignore null.
	 *
	 * @param ignoreNull the new ignore null
	 */
	public void setIgnoreNull(boolean ignoreNull) {
		this.ignoreNull = ignoreNull;
	}

	/**
	 * Checks if is ignore empty.
	 *
	 * @return true, if is ignore empty
	 */
	public boolean isIgnoreEmpty() {
		return ignoreEmpty;
	}

	/**
	 * Sets the ignore empty.
	 *
	 * @param ignoreEmpty the new ignore empty
	 */
	public void setIgnoreEmpty(boolean ignoreEmpty) {
		this.ignoreEmpty = ignoreEmpty;
	}

	/**
	 * Gets the alias.
	 *
	 * @param objectType the object type
	 * @param propType the prop type
	 * @param prop the prop
	 * @return the alias
	 */
	protected String getAlias(Class<?> objectType, Class<?> propType, String prop) {
		String alias = aliases.get(objectType.getName() + "$" + prop);
		if (null == alias) {
			alias = aliases.get("prop$" + prop);
			if (null == alias) {
				alias = aliases.get("type$" + propType.getName());
			}
		}
		return alias;
	}

	/**
	 * Checks if is inlcude.
	 *
	 * @param objectType the object type
	 * @param propType the prop type
	 * @param propName the prop name
	 * @param propValue the prop value
	 * @return true, if is inlcude
	 */
	protected boolean isInlcude(Class<?> objectType, Class<?> propType, String propName, Object propValue) {
		return isMatch(includes, objectType, propType, propName, propValue);
	}

	/**
	 * Checks if is ignore.
	 *
	 * @param objectType the object type
	 * @param propType the prop type
	 * @param propName the prop name
	 * @param propValue the prop value
	 * @return true, if is ignore
	 */
	protected boolean isIgnore(Class<?> objectType, Class<?> propType, String propName, Object propValue) {
		boolean include = isInlcude(objectType, propType, propName, propValue);
		if (include) {
			return false;
		} else {
			return isMatch(ignores, objectType, propType, propName, propValue);
		}
	}

	/**
	 * Checks if is match.
	 *
	 * @param map the map
	 * @param objectType the object type
	 * @param propType the prop type
	 * @param propName the prop name
	 * @param propValue the prop value
	 * @return true, if is match
	 */
	protected boolean isMatch(Map<String, Object> map, Class<?> objectType, Class<?> propType, String propName,
			Object propValue) {
		String matchKey = objectType.getName() + "$" + propName;
		boolean isMatch = map.containsKey(matchKey);
		if (!isMatch) {
			matchKey = "prop$" + propName;
			isMatch = map.containsKey(matchKey);
			if (!isMatch) {
				matchKey = "parent$" + objectType.getName() + ",type$" + propType.getName();
				isMatch = map.containsKey(matchKey);
				if (!isMatch) {
					matchKey = "type$" + propType.getName();
					isMatch = map.containsKey(matchKey);
				}
			}
		}

		if (isMatch) {
			Object value = map.get(matchKey);
			if (null != value && !value.equals(propValue)) {
				isMatch = false;
			}
		}

		return isMatch;
	}

	/**
	 * Gets the prop name.
	 *
	 * @param objectType the object type
	 * @param propType the prop type
	 * @param prop the prop
	 * @return the prop name
	 */
	protected String getPropName(Class<?> objectType, Class<?> propType, String prop) {
		String alias = getAlias(objectType, propType, prop);
		return alias == null ? prop : alias;
	}

	/**
	 * Encode.
	 *
	 * @param object the object
	 * @return the string
	 */
	@SuppressWarnings("unchecked")
	public String encode(Object object) {
		if (null != object) {
			Class<?> objectType = object.getClass();
			if (object instanceof Collection) {
				return encodeColletion((Collection) object);
			} else if (object instanceof Map) {
				return encodeMap((Map) object);
			} else if (object.getClass().isArray()) {
				return encodeArray(object);
			} else if (isNumberType(objectType)) {
				return encodeNumber(object);
			} else if (isBooleanType(objectType)) {
				return encodeBoolean(object);
			} else if (isByteType(objectType)) {
				return encodeByte(object);
			} else if (isStringType(objectType)) {
				return encodeString(object);
			} else {
				JSONBuilder json = new JSONBuilder(trimSpace, ignoreNull);
				json.startObject();
				try {
					Class<?> clazz = object.getClass();
					PropertyDescriptor[] propDescs = Introspector.getBeanInfo(clazz, Introspector.USE_ALL_BEANINFO)
							.getPropertyDescriptors();
					for (PropertyDescriptor propDesc : propDescs) {
						Method getter = propDesc.getReadMethod();

						if (null != getter) {
							String propName = propDesc.getName();
							if (!propName.equalsIgnoreCase("class")) {
								Class<?> type = propDesc.getPropertyType();
								Object propValue = getter.invoke(object);

								if (!(ignoreNull && null == propValue)
										&& !isIgnore(object.getClass(), type, propName, propValue)) {
									String key = JSONUtil.quote(getPropName(clazz, type, propName));
									String value = encode(propValue);
									if (!isIgnoreEmpty() || !(value.equals("''") || value.equals("\"\""))) {
										json.setPropertyQuoted(key, value);
									}
								}
							}
						}
					}
				} catch (Exception e) {
					throw new RuntimeException(e.getMessage(), e);
				}
				json.endObject();
				return json.toString();
			}
		} else {
			return EMPTY_OBJECT;
		}
	}

	/**
	 * Encode array.
	 *
	 * @param array the array
	 * @return the string
	 */
	protected String encodeArray(Object array) {
		JSONBuilder json = new JSONBuilder(trimSpace, ignoreNull);
		json.startArray();
		int len = Array.getLength(array);
		for (int i = 0; i < len; i++) {
			Object o = Array.get(array, i);
			if (null != o) {
				json.setValueQuoted(encode(o));
			}
		}
		json.endArray();
		return json.toString();
	}

	/**
	 * Encode colletion.
	 *
	 * @param col the col
	 * @return the string
	 */
	protected String encodeColletion(Collection<Object> col) {
		JSONBuilder json = new JSONBuilder(trimSpace, ignoreNull);
		json.startArray();

		for (Object o : col) {
			if (null != o) {
				json.setValueQuoted(encode(o));
			}
		}
		json.endArray();
		return json.toString();
	}

	/**
	 * Encode map.
	 *
	 * @param map the map
	 * @return the string
	 */
	protected String encodeMap(Map<Object, Object> map) {
		Set<Object> keySet = map.keySet();

		JSONBuilder json = new JSONBuilder(trimSpace, ignoreNull);
		json.startObject();
		for (Object o : keySet) {
			Object key = o.toString();
			Object value = map.get(o);
			if (!(isIgnoreNull() && null == value)) {
				String prop = getPropName(map.getClass(), key.getClass(), key.toString());
				json.setPropertyQuoted(JSONUtil.quote(prop), encode(value));
			}
		}
		json.endObject();
		return json.toString();
	}

	/**
	 * Encode string.
	 *
	 * @param value the value
	 * @return the string
	 */
	protected String encodeString(Object value) {
		return null == value ? "''" : JSONUtil.quote((trimSpace ? value.toString().trim() : value.toString()));
	}

	/**
	 * Encode number.
	 *
	 * @param value the value
	 * @return the string
	 */
	protected String encodeNumber(Object value) {
		return null == value ? "0" : String.valueOf(value);
	}

	/**
	 * Encode boolean.
	 *
	 * @param value the value
	 * @return the string
	 */
	protected String encodeBoolean(Object value) {
		return null == value ? "false" : String.valueOf(value).toLowerCase();
	}

	/**
	 * Encode byte.
	 *
	 * @param value the value
	 * @return the string
	 */
	protected String encodeByte(Object value) {
		return "0x" + (null == value ? "0" : JSONUtil.toHexString(((Byte) value).byteValue()));
	}

	/**
	 * Checks if is number type.
	 *
	 * @param type the type
	 * @return true, if is number type
	 */
	static boolean isNumberType(Class<?> type) {
		return type == Integer.TYPE || type == Integer.class || type == Short.TYPE || type == Short.class
				|| type == Long.TYPE || type == Long.class || type == Float.TYPE || type == Float.class
				|| type == Double.TYPE || type == Double.class;

	}

	/**
	 * Checks if is boolean type.
	 *
	 * @param type the type
	 * @return true, if is boolean type
	 */
	static boolean isBooleanType(Class<?> type) {
		return type == Boolean.TYPE || type == Boolean.class;
	}

	/**
	 * Checks if is byte type.
	 *
	 * @param type the type
	 * @return true, if is byte type
	 */
	static boolean isByteType(Class<?> type) {
		return type == Byte.TYPE || type == Byte.class;
	}

	/**
	 * Checks if is string type.
	 *
	 * @param type the type
	 * @return true, if is string type
	 */
	static boolean isStringType(Class<?> type) {
		return type == String.class || type == Character.TYPE || type == Character.class;
	}
}
