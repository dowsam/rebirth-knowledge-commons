/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons JSONObject.java 2012-8-3 21:35:05 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx.utils.json;

/*
 Copyright (c) 2002 JSON.org

 Permission is hereby granted, free of charge, to any person obtaining a copy 
 of this software and associated documentation files (the "Software"), to deal 
 in the Software without restriction, including without limitation the rights 
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
 copies of the Software, and to permit persons to whom the Software is 
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all 
 copies or substantial portions of the Software.

 The Software shall be used for Good, not Evil.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER 
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE 
 SOFTWARE.
 */

import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import cn.com.rebirth.commons.utils.DateUtils;

/**
 * The Class JSONObject.
 *
 * @author l.xue.nong
 */
@SuppressWarnings("all")
public class JSONObject {

	/**
	 * The Class Null.
	 *
	 * @author l.xue.nong
	 */
	private static final class Null {

		/**
		 * Instantiates a new null.
		 */
		private Null() {
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#clone()
		 */
		protected final Object clone() {
			return this;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		public boolean equals(Object object) {
			return object == null || object == this;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			return "null";
		}
	}

	/** The my hash map. */
	private Map myHashMap;

	/** The Constant NULL. */
	public static final Object NULL = new Null();

	/**
	 * Instantiates a new jSON object.
	 */
	public JSONObject() {
		myHashMap = new LinkedHashMap();
	}

	/**
	 * Instantiates a new jSON object.
	 *
	 * @param x the x
	 * @throws ParseException the parse exception
	 */
	public JSONObject(JSONTokener x) throws ParseException {
		this();
		char c;
		String key;
		if (x.next() == '%') {
			x.unescape();
		}
		x.back();
		if (x.nextClean() != '{') {
			throw x.syntaxError("A JSONObject must begin with '{'");
		}
		while (true) {
			c = x.nextClean();
			switch (c) {
			case 0:
				throw x.syntaxError("A JSONObject must end with '}'");
			case '}':
				return;
			default:
				x.back();
				key = x.nextValue().toString();
			}
			if (x.nextClean() != ':') {
				throw x.syntaxError("Expected a ':' after a key");
			}
			myHashMap.put(key, x.nextValue());
			switch (x.nextClean()) {
			case ',':
				if (x.nextClean() == '}') {
					return;
				}
				x.back();
				break;
			case '}':
				return;
			default:
				throw x.syntaxError("Expected a ',' or '}'");
			}
		}
	}

	/**
	 * Instantiates a new jSON object.
	 *
	 * @param string the string
	 * @throws ParseException the parse exception
	 */
	public JSONObject(String string) throws ParseException {
		this(new JSONTokener(string));
	}

	/**
	 * Instantiates a new jSON object.
	 *
	 * @param map the map
	 */
	public JSONObject(Map map) {
		//		myHashMap = new HashMap(map);
		this.myHashMap = map;
	}

	/**
	 * Accumulate.
	 *
	 * @param key the key
	 * @param value the value
	 * @return the jSON object
	 * @throws NullPointerException the null pointer exception
	 */
	public JSONObject accumulate(String key, Object value) throws NullPointerException {
		JSONArray a;
		Object o = opt(key);
		if (o == null) {
			put(key, value);
		} else if (o instanceof JSONArray) {
			a = (JSONArray) o;
			a.put(value);
		} else {
			a = new JSONArray();
			a.put(o);
			a.put(value);
			put(key, a);
		}
		return this;
	}

	/**
	 * Gets the.
	 *
	 * @param key the key
	 * @return the object
	 * @throws NoSuchElementException the no such element exception
	 */
	public Object get(String key) throws NoSuchElementException {
		Object o = opt(key);
		if (o == null) {
			throw new NoSuchElementException("JSONObject[" + quote(key) + "] not found.");
		}
		return o;
	}

	/**
	 * Gets the boolean.
	 *
	 * @param key the key
	 * @return the boolean
	 * @throws ClassCastException the class cast exception
	 * @throws NoSuchElementException the no such element exception
	 */
	public boolean getBoolean(String key) throws ClassCastException, NoSuchElementException {
		Object o = get(key);
		if (o == Boolean.FALSE || o.equals("false")) {
			return false;
		} else if (o == Boolean.TRUE || o.equals("true")) {
			return true;
		}
		throw new ClassCastException("JSONObject[" + quote(key) + "] is not a Boolean.");
	}

	/**
	 * Gets the double.
	 *
	 * @param key the key
	 * @return the double
	 * @throws NoSuchElementException the no such element exception
	 * @throws NumberFormatException the number format exception
	 */
	public double getDouble(String key) throws NoSuchElementException, NumberFormatException {
		Object o = get(key);
		if (o instanceof Number) {
			return ((Number) o).doubleValue();
		}
		if (o instanceof String) {
			return new Double((String) o).doubleValue();
		}
		throw new NumberFormatException("JSONObject[" + quote(key) + "] is not a number.");
	}

	/**
	 * Gets the hash map.
	 *
	 * @return the hash map
	 */
	Map getHashMap() {
		return myHashMap;
	}

	/**
	 * Gets the int.
	 *
	 * @param key the key
	 * @return the int
	 * @throws NoSuchElementException the no such element exception
	 * @throws NumberFormatException the number format exception
	 */
	public int getInt(String key) throws NoSuchElementException, NumberFormatException {
		Object o = get(key);
		if (o instanceof Number) {
			return ((Number) o).intValue();
		}
		return (int) getDouble(key);
	}

	/**
	 * Gets the jSON array.
	 *
	 * @param key the key
	 * @return the jSON array
	 * @throws NoSuchElementException the no such element exception
	 */
	public JSONArray getJSONArray(String key) throws NoSuchElementException {
		Object o = get(key);
		if (o instanceof JSONArray) {
			return (JSONArray) o;
		}
		throw new NoSuchElementException("JSONObject[" + quote(key) + "] is not a JSONArray.");
	}

	/**
	 * Gets the jSON object.
	 *
	 * @param key the key
	 * @return the jSON object
	 * @throws NoSuchElementException the no such element exception
	 */
	public JSONObject getJSONObject(String key) throws NoSuchElementException {
		Object o = get(key);
		if (o instanceof JSONObject) {
			return (JSONObject) o;
		}
		throw new NoSuchElementException("JSONObject[" + quote(key) + "] is not a JSONObject.");
	}

	/**
	 * Gets the string.
	 *
	 * @param key the key
	 * @return the string
	 * @throws NoSuchElementException the no such element exception
	 */
	public String getString(String key) throws NoSuchElementException {
		return get(key).toString();
	}

	/**
	 * Checks for.
	 *
	 * @param key the key
	 * @return true, if successful
	 */
	public boolean has(String key) {
		return myHashMap.containsKey(key);
	}

	/**
	 * Checks if is null.
	 *
	 * @param key the key
	 * @return true, if is null
	 */
	public boolean isNull(String key) {
		return JSONObject.NULL.equals(opt(key));
	}

	/**
	 * Keys.
	 *
	 * @return the iterator
	 */
	public Iterator keys() {
		return myHashMap.keySet().iterator();
	}

	/**
	 * Length.
	 *
	 * @return the int
	 */
	public int length() {
		return myHashMap.size();
	}

	/**
	 * Names.
	 *
	 * @return the jSON array
	 */
	public JSONArray names() {
		JSONArray ja = new JSONArray();
		Iterator keys = keys();
		while (keys.hasNext()) {
			ja.put(keys.next());
		}
		if (ja.length() == 0) {
			return null;
		}
		return ja;
	}

	/**
	 * Number to string.
	 *
	 * @param n the n
	 * @return the string
	 * @throws ArithmeticException the arithmetic exception
	 */
	static public String numberToString(Number n) throws ArithmeticException {
		if ((n instanceof Float && (((Float) n).isInfinite() || ((Float) n).isNaN()))
				|| (n instanceof Double && (((Double) n).isInfinite() || ((Double) n).isNaN()))) {
			throw new ArithmeticException("JSON can only serialize finite numbers.");
		}

		// Shave off trailing zeros and decimal point, if possible.

		String s = n.toString().toLowerCase();
		if (s.indexOf('e') < 0 && s.indexOf('.') > 0) {
			while (s.endsWith("0")) {
				s = s.substring(0, s.length() - 1);
			}
			if (s.endsWith(".")) {
				s = s.substring(0, s.length() - 1);
			}
		}
		return s;
	}

	/**
	 * Opt.
	 *
	 * @param key the key
	 * @return the object
	 * @throws NullPointerException the null pointer exception
	 */
	public Object opt(String key) throws NullPointerException {
		if (key == null) {
			throw new NullPointerException("Null key");
		}
		return myHashMap.get(key);
	}

	/**
	 * Gets the params.
	 *
	 * @return the params
	 */
	public Map<String, Object> getParams() {
		return myHashMap;
	}

	/**
	 * Opt boolean.
	 *
	 * @param key the key
	 * @return true, if successful
	 */
	public boolean optBoolean(String key) {
		return optBoolean(key, false);
	}

	/**
	 * Opt boolean.
	 *
	 * @param key the key
	 * @param defaultValue the default value
	 * @return true, if successful
	 */
	public boolean optBoolean(String key, boolean defaultValue) {
		Object o = opt(key);
		if (o != null) {
			if (o == Boolean.FALSE || o.equals("false")) {
				return false;
			} else if (o == Boolean.TRUE || o.equals("true")) {
				return true;
			}
		}
		return defaultValue;
	}

	/**
	 * Opt double.
	 *
	 * @param key the key
	 * @return the double
	 */
	public double optDouble(String key) {
		return optDouble(key, Double.NaN);
	}

	/**
	 * Opt double.
	 *
	 * @param key the key
	 * @param defaultValue the default value
	 * @return the double
	 */
	public double optDouble(String key, double defaultValue) {
		Object o = opt(key);
		if (o != null) {
			if (o instanceof Number) {
				return ((Number) o).doubleValue();
			}
			try {
				return new Double((String) o).doubleValue();
			} catch (Exception e) {
			}
		}
		return defaultValue;
	}

	/**
	 * Opt int.
	 *
	 * @param key the key
	 * @return the int
	 */
	public int optInt(String key) {
		return optInt(key, 0);
	}

	/**
	 * Opt int.
	 *
	 * @param key the key
	 * @param defaultValue the default value
	 * @return the int
	 */
	public int optInt(String key, int defaultValue) {
		Object o = opt(key);
		if (o != null) {
			if (o instanceof Number) {
				return ((Number) o).intValue();
			}
			try {
				return Integer.parseInt((String) o);
			} catch (Exception e) {
			}
		}
		return defaultValue;
	}

	/**
	 * Opt long.
	 *
	 * @param key the key
	 * @return the long
	 */
	public long optLong(String key) {
		return optLong(key, 0l);
	}

	/**
	 * Opt long.
	 *
	 * @param key the key
	 * @param defaultValue the default value
	 * @return the long
	 */
	public long optLong(String key, long defaultValue) {
		Object o = opt(key);
		if (o != null) {
			if (o instanceof Number) {
				return ((Number) o).longValue();
			}
			try {
				return Long.parseLong((String) o);
			} catch (Exception e) {
			}
		}
		return defaultValue;
	}

	/**
	 * Opt json array.
	 *
	 * @param key the key
	 * @return the jSON array
	 */
	public JSONArray optJSONArray(String key) {
		Object o = opt(key);
		if (o instanceof JSONArray) {
			return (JSONArray) o;
		}
		return null;
	}

	/**
	 * Opt json object.
	 *
	 * @param key the key
	 * @return the jSON object
	 */
	public JSONObject optJSONObject(String key) {
		Object o = opt(key);
		if (o instanceof JSONObject) {
			return (JSONObject) o;
		}
		return null;
	}

	/**
	 * Opt string.
	 *
	 * @param key the key
	 * @return the string
	 */
	public String optString(String key) {
		return optString(key, "");
	}

	/**
	 * Opt string.
	 *
	 * @param key the key
	 * @param defaultValue the default value
	 * @return the string
	 */
	public String optString(String key, String defaultValue) {
		Object o = opt(key);
		if (o != null) {
			return o.toString();
		}
		return defaultValue;
	}

	/**
	 * Put.
	 *
	 * @param key the key
	 * @param value the value
	 * @return the jSON object
	 */
	public JSONObject put(String key, boolean value) {
		put(key, new Boolean(value));
		return this;
	}

	/**
	 * Put.
	 *
	 * @param key the key
	 * @param value the value
	 * @return the jSON object
	 */
	public JSONObject put(String key, double value) {
		put(key, new Double(value));
		return this;
	}

	/**
	 * Put.
	 *
	 * @param key the key
	 * @param value the value
	 * @return the jSON object
	 */
	public JSONObject put(String key, int value) {
		put(key, new Integer(value));
		return this;
	}

	/**
	 * Put.
	 *
	 * @param key the key
	 * @param value the value
	 * @return the jSON object
	 * @throws NullPointerException the null pointer exception
	 */
	public JSONObject put(String key, Object value) throws NullPointerException {
		if (key == null) {
			throw new NullPointerException("Null key.");
		}
		if (value != null) {
			myHashMap.put(key, value);
		} else {
			remove(key);
		}
		return this;
	}

	/**
	 * Put opt.
	 *
	 * @param key the key
	 * @param value the value
	 * @return the jSON object
	 * @throws NullPointerException the null pointer exception
	 */
	public JSONObject putOpt(String key, Object value) throws NullPointerException {
		if (value != null) {
			put(key, value);
		}
		return this;
	}

	/**
	 * Quote.
	 *
	 * @param string the string
	 * @return the string
	 */
	public static String quote(String string) {
		if (string == null || string.length() == 0) {
			return "\"\"";
		}

		char c;
		int i;
		int len = string.length();
		StringBuffer sb = new StringBuffer(len + 4);
		String t;

		sb.append('"');
		for (i = 0; i < len; i += 1) {
			c = string.charAt(i);
			switch (c) {
			case '\\':
			case '"':
			case '/':
				sb.append('\\');
				sb.append(c);
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\t':
				sb.append("\\t");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\r':
				sb.append("\\r");
				break;
			default:
				if (c < ' ') {
					t = "000" + Integer.toHexString(c);
					sb.append("\\u" + t.substring(t.length() - 4));
				} else {
					sb.append(c);
				}
			}
		}
		sb.append('"');
		return sb.toString();
	}

	/**
	 * Removes the.
	 *
	 * @param key the key
	 * @return the object
	 */
	public Object remove(String key) {
		return myHashMap.remove(key);
	}

	/**
	 * To json array.
	 *
	 * @param names the names
	 * @return the jSON array
	 */
	public JSONArray toJSONArray(JSONArray names) {
		if (names == null || names.length() == 0) {
			return null;
		}
		JSONArray ja = new JSONArray();
		for (int i = 0; i < names.length(); i += 1) {
			ja.put(this.opt(names.getString(i)));
		}
		return ja;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		Iterator keys = keys();
		Object o = null;
		String s;
		StringBuffer sb = new StringBuffer();

		sb.append('{');
		while (keys.hasNext()) {
			if (o != null) {
				sb.append(',');
			}
			s = keys.next().toString();
			o = myHashMap.get(s);
			if (o != null) {
				sb.append(quote(s));
				sb.append(':');
				if (o instanceof String) {
					sb.append(quote((String) o));
				} else if (o instanceof Character) {
					sb.append(quote(((Character) o).toString()));
				} else if (o instanceof Number) {
					sb.append(numberToString((Number) o));
				} else if (o instanceof Date) {
					sb.append("\"").append(DateUtils.formatDate((Date) o, null)).append("\"");
				} else {
					sb.append(o.toString());
				}
			} else if (sb.charAt(sb.length() - 1) == ',' && !keys.hasNext()) {
				sb.deleteCharAt(sb.length() - 1);
			}
		}
		sb.append('}');
		return sb.toString();
	}

	/**
	 * To string.
	 *
	 * @param indentFactor the indent factor
	 * @return the string
	 */
	public String toString(int indentFactor) {
		return toString(indentFactor, 0);
	}

	/**
	 * To string.
	 *
	 * @param indentFactor the indent factor
	 * @param indent the indent
	 * @return the string
	 */
	String toString(int indentFactor, int indent) {
		int i;
		Iterator keys = keys();
		String pad = "";
		StringBuffer sb = new StringBuffer();
		indent += indentFactor;
		for (i = 0; i < indent; i += 1) {
			pad += ' ';
		}
		sb.append("{\n");
		while (keys.hasNext()) {
			String s = keys.next().toString();
			Object o = myHashMap.get(s);
			if (o != null) {
				if (sb.length() > 2) {
					sb.append(",\n");
				}
				sb.append(pad);
				sb.append(quote(s));
				sb.append(": ");
				if (o instanceof String) {
					sb.append(quote((String) o));
				} else if (o instanceof Number) {
					sb.append(numberToString((Number) o));
				} else if (o instanceof JSONObject) {
					sb.append(((JSONObject) o).toString(indentFactor, indent));
				} else if (o instanceof JSONArray) {
					sb.append(((JSONArray) o).toString(indentFactor, indent));
				} else {
					sb.append(o.toString());
				}
			}
		}
		sb.append('}');
		return sb.toString();
	}
}