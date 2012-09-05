/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons JSONArray.java 2012-8-3 21:34:34 l.xue.nong$$
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.NoSuchElementException;

import cn.com.rebirth.commons.utils.DateUtils;

/**
 * The Class JSONArray.
 *
 * @author l.xue.nong
 */
@SuppressWarnings("all")
public class JSONArray {

	/** The my array list. */
	private ArrayList myArrayList;

	/**
	 * Instantiates a new jSON array.
	 */
	public JSONArray() {
		myArrayList = new ArrayList();
	}

	/**
	 * Instantiates a new jSON array.
	 *
	 * @param x the x
	 * @throws ParseException the parse exception
	 */
	public JSONArray(JSONTokener x) throws ParseException {
		this();
		if (x.nextClean() != '[') {
			throw x.syntaxError("A JSONArray must start with '['");
		}
		if (x.nextClean() == ']') {
			return;
		}
		x.back();
		while (true) {
			if (x.nextClean() == ',') {
				x.back();
				myArrayList.add(null);
			} else {
				x.back();
				myArrayList.add(x.nextValue());
			}
			switch (x.nextClean()) {
			case ',':
				if (x.nextClean() == ']') {
					return;
				}
				x.back();
				break;
			case ']':
				return;
			default:
				throw x.syntaxError("Expected a ',' or ']'");
			}
		}
	}

	/**
	 * Instantiates a new jSON array.
	 *
	 * @param string the string
	 * @throws ParseException the parse exception
	 */
	public JSONArray(String string) throws ParseException {
		this(new JSONTokener(string));
	}

	/**
	 * Instantiates a new jSON array.
	 *
	 * @param collection the collection
	 */
	public JSONArray(Collection<?> collection) {
		myArrayList = new ArrayList(collection);
	}

	/**
	 * Gets the.
	 *
	 * @param index the index
	 * @return the object
	 * @throws NoSuchElementException the no such element exception
	 */
	public Object get(int index) throws NoSuchElementException {
		Object o = opt(index);
		if (o == null) {
			throw new NoSuchElementException("JSONArray[" + index + "] not found.");
		}
		return o;
	}

	/**
	 * Gets the array list.
	 *
	 * @return the array list
	 */
	ArrayList getArrayList() {
		return myArrayList;
	}

	/**
	 * Gets the boolean.
	 *
	 * @param index the index
	 * @return the boolean
	 * @throws ClassCastException the class cast exception
	 * @throws NoSuchElementException the no such element exception
	 */
	public boolean getBoolean(int index) throws ClassCastException, NoSuchElementException {
		Object o = get(index);
		if (o == Boolean.FALSE || o.equals("false")) {
			return false;
		} else if (o == Boolean.TRUE || o.equals("true")) {
			return true;
		}
		throw new ClassCastException("JSONArray[" + index + "] not a Boolean.");
	}

	/**
	 * Gets the double.
	 *
	 * @param index the index
	 * @return the double
	 * @throws NoSuchElementException the no such element exception
	 * @throws NumberFormatException the number format exception
	 */
	public double getDouble(int index) throws NoSuchElementException, NumberFormatException {
		Object o = get(index);
		if (o instanceof Number) {
			return ((Number) o).doubleValue();
		}
		if (o instanceof String) {
			return new Double((String) o).doubleValue();
		}
		throw new NumberFormatException("JSONObject[" + index + "] is not a number.");
	}

	/**
	 * Gets the int.
	 *
	 * @param index the index
	 * @return the int
	 * @throws NoSuchElementException the no such element exception
	 * @throws NumberFormatException the number format exception
	 */
	public int getInt(int index) throws NoSuchElementException, NumberFormatException {
		Object o = get(index);
		if (o instanceof Number) {
			return ((Number) o).intValue();
		}
		return (int) getDouble(index);
	}

	/**
	 * Gets the jSON array.
	 *
	 * @param index the index
	 * @return the jSON array
	 * @throws NoSuchElementException the no such element exception
	 */
	public JSONArray getJSONArray(int index) throws NoSuchElementException {
		Object o = get(index);
		if (o instanceof JSONArray) {
			return (JSONArray) o;
		}
		throw new NoSuchElementException("JSONArray[" + index + "] is not a JSONArray.");
	}

	/**
	 * Gets the jSON object.
	 *
	 * @param index the index
	 * @return the jSON object
	 * @throws NoSuchElementException the no such element exception
	 */
	public JSONObject getJSONObject(int index) throws NoSuchElementException {
		Object o = get(index);
		if (o instanceof JSONObject) {
			return (JSONObject) o;
		}
		throw new NoSuchElementException("JSONArray[" + index + "] is not a JSONObject.");
	}

	/**
	 * Gets the string.
	 *
	 * @param index the index
	 * @return the string
	 * @throws NoSuchElementException the no such element exception
	 */
	public String getString(int index) throws NoSuchElementException {
		return get(index).toString();
	}

	/**
	 * Checks if is null.
	 *
	 * @param index the index
	 * @return true, if is null
	 */
	public boolean isNull(int index) {
		Object o = opt(index);
		return o == null || o.equals(null);
	}

	/**
	 * Join.
	 *
	 * @param separator the separator
	 * @return the string
	 */
	public String join(String separator) {
		int i;
		Object o;
		StringBuffer sb = new StringBuffer();
		for (i = 0; i < myArrayList.size(); i += 1) {
			if (i > 0) {
				sb.append(separator);
			}
			o = myArrayList.get(i);
			if (o == null) {
				sb.append("null");
			} else if (o instanceof String) {
				sb.append(JSONObject.quote((String) o));
			} else if (o instanceof Character) {
				sb.append(JSONObject.quote(o.toString()));
			} else if (o instanceof Number) {
				sb.append(JSONObject.numberToString((Number) o));
			} else if (o instanceof Date) {
				sb.append(DateUtils.formatDate((Date) o, null));
			} else {
				sb.append(o.toString());
			}
		}
		return sb.toString();
	}

	/**
	 * Length.
	 *
	 * @return the int
	 */
	public int length() {
		return myArrayList.size();
	}

	/**
	 * Opt.
	 *
	 * @param index the index
	 * @return the object
	 */
	public Object opt(int index) {
		if (index < 0 || index >= length()) {
			return null;
		} else {
			return myArrayList.get(index);
		}
	}

	/**
	 * Opt boolean.
	 *
	 * @param index the index
	 * @return true, if successful
	 */
	public boolean optBoolean(int index) {
		return optBoolean(index, false);
	}

	/**
	 * Opt boolean.
	 *
	 * @param index the index
	 * @param defaultValue the default value
	 * @return true, if successful
	 */
	public boolean optBoolean(int index, boolean defaultValue) {
		Object o = opt(index);
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
	 * @param index the index
	 * @return the double
	 */
	public double optDouble(int index) {
		return optDouble(index, Double.NaN);
	}

	/**
	 * Opt double.
	 *
	 * @param index the index
	 * @param defaultValue the default value
	 * @return the double
	 */
	public double optDouble(int index, double defaultValue) {
		Object o = opt(index);
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
	 * @param index the index
	 * @return the int
	 */
	public int optInt(int index) {
		return optInt(index, 0);
	}

	/**
	 * Opt int.
	 *
	 * @param index the index
	 * @param defaultValue the default value
	 * @return the int
	 */
	public int optInt(int index, int defaultValue) {
		Object o = opt(index);
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
	 * Opt json array.
	 *
	 * @param index the index
	 * @return the jSON array
	 */
	public JSONArray optJSONArray(int index) {
		Object o = opt(index);
		if (o instanceof JSONArray) {
			return (JSONArray) o;
		}
		return null;
	}

	/**
	 * Opt json object.
	 *
	 * @param index the index
	 * @return the jSON object
	 */
	public JSONObject optJSONObject(int index) {
		Object o = opt(index);
		if (o instanceof JSONObject) {
			return (JSONObject) o;
		}
		return null;
	}

	/**
	 * Opt string.
	 *
	 * @param index the index
	 * @return the string
	 */
	public String optString(int index) {
		return optString(index, "");
	}

	/**
	 * Opt string.
	 *
	 * @param index the index
	 * @param defaultValue the default value
	 * @return the string
	 */
	public String optString(int index, String defaultValue) {
		Object o = opt(index);
		if (o != null) {
			return o.toString();
		}
		return defaultValue;
	}

	/**
	 * Put.
	 *
	 * @param value the value
	 * @return the jSON array
	 */
	public JSONArray put(boolean value) {
		put(new Boolean(value));
		return this;
	}

	/**
	 * Put.
	 *
	 * @param value the value
	 * @return the jSON array
	 */
	public JSONArray put(double value) {
		put(new Double(value));
		return this;
	}

	/**
	 * Put.
	 *
	 * @param value the value
	 * @return the jSON array
	 */
	public JSONArray put(int value) {
		put(new Integer(value));
		return this;
	}

	/**
	 * Put.
	 *
	 * @param value the value
	 * @return the jSON array
	 */
	public JSONArray put(Object value) {
		myArrayList.add(value);
		return this;
	}

	/**
	 * Put.
	 *
	 * @param index the index
	 * @param value the value
	 * @return the jSON array
	 */
	public JSONArray put(int index, boolean value) {
		put(index, new Boolean(value));
		return this;
	}

	/**
	 * Put.
	 *
	 * @param index the index
	 * @param value the value
	 * @return the jSON array
	 */
	public JSONArray put(int index, double value) {
		put(index, new Double(value));
		return this;
	}

	/**
	 * Put.
	 *
	 * @param index the index
	 * @param value the value
	 * @return the jSON array
	 */
	public JSONArray put(int index, int value) {
		put(index, new Integer(value));
		return this;
	}

	/**
	 * Put.
	 *
	 * @param index the index
	 * @param value the value
	 * @return the jSON array
	 * @throws NoSuchElementException the no such element exception
	 * @throws NullPointerException the null pointer exception
	 */
	public JSONArray put(int index, Object value) throws NoSuchElementException, NullPointerException {
		if (index < 0) {
			throw new NoSuchElementException("JSONArray[" + index + "] not found.");
		} else if (value == null) {
			throw new NullPointerException();
		} else if (index < length()) {
			myArrayList.set(index, value);
		} else {
			while (index != length()) {
				put(null);
			}
			put(value);
		}
		return this;
	}

	/**
	 * To json object.
	 *
	 * @param names the names
	 * @return the jSON object
	 */
	public JSONObject toJSONObject(JSONArray names) {
		if (names == null || names.length() == 0 || length() == 0) {
			return null;
		}
		JSONObject jo = new JSONObject();
		for (int i = 0; i < names.length(); i += 1) {
			jo.put(names.getString(i), this.opt(i));
		}
		return jo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return '[' + join(",") + ']';
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
		Object o;
		String pad = "";
		StringBuffer sb = new StringBuffer();
		indent += indentFactor;
		for (i = 0; i < indent; i += 1) {
			pad += ' ';
		}
		sb.append("[\n");
		for (i = 0; i < myArrayList.size(); i += 1) {
			if (i > 0) {
				sb.append(",\n");
			}
			sb.append(pad);
			o = myArrayList.get(i);
			if (o == null) {
				sb.append("null");
			} else if (o instanceof String) {
				sb.append(JSONObject.quote((String) o));
			} else if (o instanceof Number) {
				sb.append(JSONObject.numberToString((Number) o));
			} else if (o instanceof JSONObject) {
				sb.append(((JSONObject) o).toString(indentFactor, indent));
			} else if (o instanceof JSONArray) {
				sb.append(((JSONArray) o).toString(indentFactor, indent));
			} else {
				sb.append(o.toString());
			}
		}
		sb.append(']');
		return sb.toString();
	}
}