/**
 * 
 */
package cn.com.rebirth.knowledge.commons.dhtmlx.utils.json;

/**
 * 处理JSON格式化文本的工具类
 * @author elvis
 */
public final class JSONUtil {
	public static final char[] HEX_CHARS = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B',
			'C', 'D', 'E', 'F' };

	public static char[] toHexChars(byte[] bytes) {
		char[] chars = new char[bytes.length * 2];
		int index = 0;
		for (int i = 0; i < bytes.length; i++) {
			chars[index++] = HEX_CHARS[(bytes[i] >>> 4) & 0x0F];
			chars[index++] = HEX_CHARS[bytes[i] & 0x0F];
		}
		return chars;
	}

	public static String toHexString(byte b) {
		return String.valueOf(toHexChars(new byte[] { b }));
	}

	public static String quote(String string, boolean trim) {
		if (trim) {
			return quote(null == string ? string : string.trim());
		} else {
			return quote(string);
		}
	}

	public static String quote(String string) {
		if (string == null || string.length() == 0) {
			return "''";
		}
		char c = 0;
		int len = string.length();
		StringBuilder sb = new StringBuilder(len + 10);
		sb.append("'");
		for (int i = 0; i < len; i += 1) {
			c = string.charAt(i);
			switch (c) {
			case '\\':
				sb.append("\\\\");
				break;
			case '\'':
				sb.append("\\'");
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
				sb.append(c);
			}
		}
		sb.append("'");
		return sb.toString();
	}
}
