/**
 * 
 */
package cn.com.rebirth.knowledge.commons.dhtmlx.utils.json;

/**
 * 构建JSON文本的工具类
 * @author elvis
 */
public final class JSONBuilder {

	/** 是否对字符进行trim操作 */
	private boolean trimSpace = true;

	/** 是否忽略值为空的属性*/
	private boolean ignoreNull = true;

	private StringBuilder buf = new StringBuilder();

	public JSONBuilder() {

	}

	public JSONBuilder(boolean trimSpace) {
		this.trimSpace = trimSpace;
	}

	public JSONBuilder(boolean trimSpace, boolean ignoreNull) {
		this(trimSpace);
		this.ignoreNull = ignoreNull;
	}

	public boolean isTrimSpace() {
		return trimSpace;
	}

	public void setTrimSpace(boolean trimSpace) {
		this.trimSpace = trimSpace;
	}

	public boolean isIgnoreNull() {
		return ignoreNull;
	}

	public void setIgnoreNull(boolean ignoreNull) {
		this.ignoreNull = ignoreNull;
	}

	@Override
	public String toString() {
		return buf.toString();
	}

	public void append(String string) {
		buf.append(string);
	}

	public void startObject() {
		buf.append("{");
	}

	public void endObject() {
		deleteSeparator();
		buf.append("}");
	}

	public void startArray() {
		buf.append("[");
	}

	public void endArray() {
		deleteSeparator();
		buf.append("]");
	}

	public void setValue(String value) {
		setValueQuoted(JSONUtil.quote(value));
	}

	public void setObject(String name, String objectJSON) {
		setPropertyQuoted(JSONUtil.quote(name), objectJSON);
	}

	public void setProperty(String name, String value) {
		if (!isIgnoreNull() || value != null) {
			setPropertyQuoted(JSONUtil.quote(name), JSONUtil.quote(value));
		}
	}

	public void setProperty(String name, long value) {
		setPropertyQuoted(JSONUtil.quote(name), String.valueOf(value));
	}

	public void setProperty(String name, boolean value) {
		setPropertyQuoted(JSONUtil.quote(name), String.valueOf(value).toLowerCase());
	}

	public void setValueQuoted(String value) {
		buf.append(value).append(",");
	}

	public void setPropertyQuoted(String name, String value) {
		if (!isIgnoreNull() || value != null) {
			buf.append(name).append(":").append(value).append(",");
		}
	}

	private void deleteSeparator() {
		if (buf.length() > 0) {
			if (buf.charAt(buf.length() - 1) == ',') {
				buf.deleteCharAt(buf.length() - 1);
			} else if (buf.length() > 1 && buf.charAt(buf.length() - 2) == ',' && buf.charAt(buf.length() - 1) == '}') {
				buf.deleteCharAt(buf.length() - 2);
			}
		}
	}
}
