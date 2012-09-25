/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons EWebeditUtils.java 2012-9-18 9:20:35 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.ewebeditor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * The Class EWebeditUtils.
 *
 * @author l.xue.nong
 */
public class EWebeditorUtils {

	/** The m_request. */
	private HttpServletRequest m_request;

	/** The m_application. */
	private ServletContext m_application;

	/**
	 * Inits the server.
	 *
	 * @param pagecontext the pagecontext
	 */
	public void InitServer(PageContext pagecontext) {
		m_application = pagecontext.getServletContext();
		m_request = (HttpServletRequest) pagecontext.getRequest();
	}

	/**
	 * Split.
	 *
	 * @param source the source
	 * @param div the div
	 * @return the string[]
	 */
	public String[] split(String source, String div) {
		int arynum = 0, intIdx = 0, intIdex = 0, div_length = div.length();
		if (source.compareTo("") != 0) {
			if (source.indexOf(div) != -1) {
				intIdx = source.indexOf(div);
				for (int intCount = 1;; intCount++) {
					if (source.indexOf(div, intIdx + div_length) != -1) {
						intIdx = source.indexOf(div, intIdx + div_length);
						arynum = intCount;
					} else {
						arynum += 2;
						break;
					}
				}
			} else {
				arynum = 1;
			}
		} else {
			arynum = 0;

		}
		intIdx = 0;
		intIdex = 0;
		String[] returnStr = new String[arynum];

		if (source.compareTo("") != 0) {
			if (source.indexOf(div) != -1) {
				intIdx = (int) source.indexOf(div);
				returnStr[0] = (String) source.substring(0, intIdx);
				for (int intCount = 1;; intCount++) {
					if (source.indexOf(div, intIdx + div_length) != -1) {
						intIdex = (int) source.indexOf(div, intIdx + div_length);
						returnStr[intCount] = (String) source.substring(intIdx + div_length, intIdex);
						intIdx = (int) source.indexOf(div, intIdx + div_length);
					} else {
						returnStr[intCount] = (String) source.substring(intIdx + div_length, source.length());
						break;
					}
				}
			} else {
				returnStr[0] = (String) source.substring(0, source.length());
				return returnStr;
			}
		} else {
			return returnStr;
		}
		return returnStr;
	}

	/**
	 * Deal null.
	 *
	 * @param str the str
	 * @return the string
	 */
	public String dealNull(String str) {
		String returnstr = null;
		if (str == null) {
			returnstr = "";
		} else {
			returnstr = str;
		}
		return returnstr;
	}

	/**
	 * Deal null.
	 *
	 * @param obj the obj
	 * @return the object
	 */
	public Object dealNull(Object obj) {
		Object returnstr = null;
		if (obj == null) {
			returnstr = (Object) ("");
		} else {
			returnstr = obj;
		}
		return returnstr;
	}

	/**
	 * Replace.
	 *
	 * @param str the str
	 * @param substr the substr
	 * @param restr the restr
	 * @return the string
	 */
	public String replace(String str, String substr, String restr) {
		String[] tmp = split(str, substr);
		String returnstr = null;
		if (tmp.length != 0) {
			returnstr = tmp[0];
			for (int i = 0; i < tmp.length - 1; i++) {
				returnstr = dealNull(returnstr) + restr + tmp[i + 1];
			}
		}
		return dealNull(returnstr);
	}

	/**
	 * Gets the config string.
	 *
	 * @param s_Key the s_ key
	 * @param s_Config the s_ config
	 * @return the config string
	 */
	public String getConfigString(String s_Key, String s_Config) {
		String s_Result = "";
		Pattern p = Pattern.compile("//" + s_Key + " = \"(.*)\"");
		Matcher m = p.matcher(s_Config);
		while (m.find()) {
			s_Result = m.group(1);
		}
		return s_Result;
	}

	/**
	 * Gets the config array.
	 *
	 * @param s_Key the s_ key
	 * @param s_Config the s_ config
	 * @return the config array
	 */
	public List<String> getConfigArray(String s_Key, String s_Config) {
		ArrayList<String> a_Result = new ArrayList<String>();
		Pattern p = Pattern.compile("//" + s_Key + " = \"(.*)\"");
		Matcher m = p.matcher(s_Config);
		while (m.find()) {
			a_Result.add(m.group(1));
		}
		return a_Result;
	}

	/**
	 * Gets the config file real path.
	 *
	 * @return the config file real path
	 */
	public String getConfigFileRealPath() {
		return getRealPathFromRelative("jsp/config.jsp");
	}

	/**
	 * Gets the real path from relative.
	 *
	 * @param s_RelativePath the s_ relative path
	 * @return the real path from relative
	 */
	public String getRealPathFromRelative(String s_RelativePath) {
		String s_FileSeparator = System.getProperty("file.separator");

		String s_Url = s_RelativePath;
		if (s_Url.substring(0, 1).equals("/")) {
			s_Url = replace(s_Url, "/", s_FileSeparator);
			return getSiteRootRealPath() + s_Url.substring(1, s_Url.length());
		}

		String s_EditorRoot = getEditorRootRealPath();
		String s_PrePath = s_EditorRoot.substring(0, s_EditorRoot.length() - 1);

		while (s_Url.startsWith("../")) {
			s_Url = s_Url.substring(3);
			s_PrePath = s_PrePath.substring(0, s_PrePath.lastIndexOf(s_FileSeparator));
		}

		return s_PrePath + s_FileSeparator + replace(s_Url, "/", s_FileSeparator);
	}

	/**
	 * Gets the editor root real path.
	 *
	 * @return the editor root real path
	 */
	public String getEditorRootRealPath() {
		String s_FileSeparator = System.getProperty("file.separator");

		String s_Path = m_request.getServletPath();
		s_Path = s_Path.substring(0, s_Path.lastIndexOf("/"));
		s_Path = s_Path.substring(0, s_Path.lastIndexOf("/"));

		if (!s_Path.equals("")) {
			s_Path = replace(s_Path, "/", s_FileSeparator);
			s_Path += s_FileSeparator;
			s_Path = s_Path.substring(1, s_Path.length());
		}

		s_Path = getSiteRootRealPath() + s_Path;

		return s_Path;
	}

	/**
	 * Gets the site root real path.
	 *
	 * @return the site root real path
	 */
	public String getSiteRootRealPath() {
		String s_FileSeparator = System.getProperty("file.separator");
		String s_RealPath = m_application.getRealPath("/");
		s_RealPath = replace(s_RealPath, s_FileSeparator + "." + s_FileSeparator, s_FileSeparator);
		if (!s_RealPath.endsWith(s_FileSeparator)) {
			s_RealPath += s_FileSeparator;
		}
		return s_RealPath;
	}

	/**
	 * Write file.
	 *
	 * @param s_FileName the s_ file name
	 * @param s_Text the s_ text
	 */
	public void WriteFile(String s_FileName, String s_Text) {
		try {
			FileOutputStream fos = new FileOutputStream(s_FileName);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
			osw.write(s_Text);
			osw.flush();
			osw.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Read file.
	 *
	 * @param s_FileName the s_ file name
	 * @return the string
	 */
	public String ReadFile(String s_FileName) {
		StringBuffer sb = new StringBuffer();
		try {
			char[] buf = new char[1024];
			FileInputStream fis = new FileInputStream(s_FileName);
			InputStreamReader isr = new InputStreamReader(fis, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			int len;
			while ((len = br.read(buf)) != -1) {
				sb.append(buf, 0, len);
			}
			br.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return sb.toString();

	}

	/**
	 * Format date.
	 *
	 * @param myDate the my date
	 * @param nFlag the n flag
	 * @return the string
	 */
	public String formatDate(Date myDate, int nFlag) {
		String strFormat = "";
		switch (nFlag) {
		case 1:
			strFormat = "yyyy";
			break;
		case 2:
			strFormat = "yyyyMM";
			break;
		case 3:
			strFormat = "yyyyMMdd";
			break;
		case 4:
			strFormat = "yyyyMMddHHmmss";
			break;
		case 5:
			strFormat = "yyyy-MM-dd";
			break;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(strFormat);
		String strDate = formatter.format(myDate);
		return strDate;
	}

	/**
	 * Replace time.
	 *
	 * @param s_Time the s_ time
	 * @param s_Patt the s_ patt
	 * @return the string
	 */
	public String ReplaceTime(Date s_Time, String s_Patt) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(s_Time);
		String y2 = String.valueOf(calendar.get(Calendar.YEAR));
		String y1 = y2.substring(2);
		String m1 = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		String m2 = m1;
		if (m2.length() == 1) {
			m2 = "0" + m2;
		}
		String d1 = String.valueOf(calendar.get(Calendar.DATE));
		String d2 = d1;
		if (d2.length() == 1) {
			d2 = "0" + d2;
		}
		String h1 = String.valueOf(calendar.get(Calendar.HOUR));
		String h2 = h1;
		if (h2.length() == 1) {
			h2 = "0" + h2;
		}
		String i1 = String.valueOf(calendar.get(Calendar.MINUTE));
		String i2 = i1;
		if (i2.length() == 1) {
			i2 = "0" + i2;
		}
		String s1 = String.valueOf(calendar.get(Calendar.SECOND));
		String s2 = s1;
		if (s2.length() == 1) {
			s2 = "0" + s2;
		}

		String ret = s_Patt;
		ret = replace(ret, "{yyyy}", y2);
		ret = replace(ret, "{yy}", y1);
		ret = replace(ret, "{mm}", m2);
		ret = replace(ret, "{m}", m1);
		ret = replace(ret, "{dd}", d2);
		ret = replace(ret, "{d}", d1);
		ret = replace(ret, "{hh}", h2);
		ret = replace(ret, "{h}", h1);
		ret = replace(ret, "{ii}", i2);
		ret = replace(ret, "{i}", i1);
		ret = replace(ret, "{ss}", s2);
		ret = replace(ret, "{s}", s1);

		return ret;
	}

	/**
	 * M d5.
	 *
	 * @param plainText the plain text
	 * @param len the len
	 * @return the string
	 */
	public String MD5(String plainText, int len) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

			if (len == 16) {
				return buf.toString().substring(8, 24);
			} else {
				return buf.toString();
			}

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Check license.
	 *
	 * @param s_Domain the s_ domain
	 * @param s_License the s_ license
	 * @return true, if successful
	 */
	public boolean CheckLicense(String s_Domain, String s_License) {
		s_Domain = s_Domain.toLowerCase();
		if (s_Domain.equals("127.0.0.1") || s_Domain.equals("localhost")) {
			return true;
		}

		if (s_License.equals("")) {
			return false;
		}

		String[] aa = split(s_License, ";");
		for (int i = 0; i < aa.length; i++) {
			String[] a = split(aa[i], ":");
			if (a.length == 8) {
				if (a[7].length() == 32) {

					if (a[0].equals("3")) {
						if (s_Domain.equals(a[6])) {
							return true;
						} else if (s_Domain.length() > a[6].length()) {
							if (s_Domain.substring(s_Domain.length() - a[6].length() - 1).equals("." + a[6])) {
								return true;
							}
						}
					} else {
						if ((s_Domain.equals(a[6])) || (s_Domain.equals("www." + a[6]))) {
							return true;
						}
					}

				}
			}
		}
		return false;
	}

	/**
	 * Checks if is int.
	 *
	 * @param str the str
	 * @return true, if successful
	 */
	public boolean IsInt(String str) {
		if (str.equals("")) {
			return false;
		}

		Pattern p = Pattern.compile("[^0-9]+");
		Matcher m = p.matcher(str);
		if (m.matches()) {
			return false;
		}

		return true;
	}

	/**
	 * Checks if is ok s params.
	 *
	 * @param s_SParams the s_ s params
	 * @param s_EncryptKey the s_ encrypt key
	 * @return true, if successful
	 */
	public boolean IsOkSParams(String s_SParams, String s_EncryptKey) {
		if (s_SParams.equals("")) {
			return false;
		}

		int n = s_SParams.indexOf("|");
		if (n < 0) {
			return false;
		}

		String s1 = s_SParams.substring(0, n);
		String s2 = s_SParams.substring(n + 1);

		if (!MD5(s_EncryptKey + s2, 16).equals(s1)) {
			return false;
		}

		return true;
	}

	/**
	 * Write config.
	 *
	 * @param s_License the s_ license
	 */
	public void WriteConfig(String s_License) {
		String eWebEditorPath = getEditorRootRealPath();

		String sConfig = ReadFile(getConfigFileRealPath());
		String sUsername = getConfigString("Username", sConfig);
		String sPassword = getConfigString("Password", sConfig);
		List<String> aStyle = getConfigArray("Style", sConfig);
		List<String> aToolbar = getConfigArray("Toolbar", sConfig);

		CheckAndUpdateConfig(eWebEditorPath, s_License, sUsername, sPassword, aToolbar, aStyle);
	}

	/**
	 * Check and update config.
	 *
	 * @param eWebEditorPath the e web editor path
	 * @param sLicense the s license
	 * @param sUsername the s username
	 * @param sPassword the s password
	 * @param aToolbar the a toolbar
	 * @param aStyle the a style
	 */
	public void CheckAndUpdateConfig(String eWebEditorPath, String sLicense, String sUsername, String sPassword,
			List<String> aToolbar, List<String> aStyle) {
		int n_Old = split(aStyle.get(1).toString(), "|||").length - 1;
		int n_New = 105;

		if ((n_Old < 66) || (n_Old > n_New)) {
			return;
		}

		for (int i = 0; i < aStyle.size(); i++) {
			String s = "";
			for (int j = n_Old + 1; j <= n_New; j++) {
				s = s + "|||";
				switch (j) {
				case 67:
				case 68:
				case 69:
					s = s + "0";
					break;
				case 70:
					s = s + "";
					break;
				case 71:
					String[] a = split(aStyle.get(i).toString(), "|||");
					if (a[21].equals("1")) {
						s = s + "{yyyy}/";
					} else if (a[21].equals("2")) {
						s = s + "{yyyy}{mm}/";
					} else if (a[21].equals("3")) {
						s = s + "{yyyy}{mm}{dd}/";
					} else {
						s = s + "";
					}
					break;
				case 72:
					s = s + "1";
					break;
				case 73:
					s = s + "{page}";
					break;
				case 74:
					s = s + "0";
					break;
				case 75:
					s = s + "2000";
					break;
				case 76:
					s = s + "1";
					break;
				case 77:
					s = s + "0";
					break;
				case 78:
					s = s + "";
					break;
				case 79:
					s = s + "0";
					break;
				case 80:
					s = s + "200";
					break;
				case 81:
					s = s + "1";
					break;
				case 82:
					s = s + "2";
					break;
				case 83:
					s = s + "1";
					break;
				case 84:
					s = s + "1";
					break;
				case 85:
					s = s + "1";
					break;
				case 86:
					s = s + "0";
					break;
				case 87:
					s = s + "";
					break;
				case 88:
					s = s + "0";
					break;
				case 89:
				case 90:
				case 91:
				case 92:
				case 93:
				case 94:
					s = s + "";
					break;
				case 95:
					s = s + "1";
					break;
				case 96:
				case 97:
					s = s + "";
					break;
				case 98:
					s = s + "300";
					break;
				case 99:
					s = s + "1";
					break;
				case 100:
				case 101:
				case 102:
				case 103:
				case 104:
					s = s + "";
					break;
				case 105:
					s = s + "1";
					break;

				}
			}

			aStyle.set(i, aStyle.get(i).toString() + s);
		}

		WriteConfig(eWebEditorPath, sLicense, sUsername, sPassword, aStyle, aToolbar);
	}

	/**
	 * Write config.
	 *
	 * @param s_eWebEditorPath the s_e web editor path
	 * @param s_License the s_ license
	 * @param s_Username the s_ username
	 * @param s_Password the s_ password
	 * @param a_Style the a_ style
	 * @param a_Toolbar the a_ toolbar
	 */
	public void WriteConfig(String s_eWebEditorPath, String s_License, String s_Username, String s_Password,
			List<String> a_Style, List<String> a_Toolbar) {
		String topConfig = "<%@ page contentType=\"text/html;charset=utf-8\" session=\"false\"%>\r\n";
		String sConfig = topConfig + "<" + "%" + "\r\n";
		sConfig += "//License = \"" + s_License + "\"" + "\r\n";
		sConfig += "\r\n";
		sConfig += "//Username = \"" + s_Username + "\"" + "\r\n";
		sConfig += "//Password = \"" + s_Password + "\"" + "\r\n";
		sConfig += "\r\n";

		String s_Order = "", s_ID = "";
		String[] a_Order, a_ID;

		int nConfigStyle = 0;
		String sConfigStyle = "";
		String[] aTmpStyle;

		int nConfigToolbar = 0;
		String sConfigToolbar = "";
		String[] aTmpToolbar;
		String sTmpToolbar = "";

		for (int i = 0; i < a_Style.size(); i++) {
			if (!a_Style.get(i).toString().equals("")) {
				aTmpStyle = split(a_Style.get(i).toString(), "|||");
				if (!aTmpStyle[0].equals("")) {
					nConfigStyle = nConfigStyle + 1;
					sConfigStyle = sConfigStyle + "//Style = \"" + a_Style.get(i).toString() + "\"" + "\r\n";

					s_Order = "";
					s_ID = "";
					for (int n = 0; n < a_Toolbar.size(); n++) {
						if (!a_Toolbar.get(n).toString().equals("")) {
							aTmpToolbar = split(a_Toolbar.get(n).toString(), "|||");
							if (aTmpToolbar[0].equals(String.valueOf(i))) {
								if (!s_ID.equals("")) {
									s_ID = s_ID + "|";
									s_Order = s_Order + "|";
								}
								s_ID = s_ID + String.valueOf(n);
								s_Order = s_Order + aTmpToolbar[3];
							}
						}
					}

					if (!s_ID.equals("")) {
						a_ID = split(s_ID, "|");
						a_Order = split(s_Order, "|");
						a_ID = Sort(a_ID, a_Order);
						for (int n = 0; n < a_ID.length; n++) {
							nConfigToolbar = nConfigToolbar + 1;
							aTmpToolbar = split(a_Toolbar.get(Integer.valueOf(a_ID[n]).intValue()).toString(), "|||");
							sTmpToolbar = String.valueOf(nConfigStyle - 1) + "|||" + aTmpToolbar[1] + "|||"
									+ aTmpToolbar[2] + "|||" + aTmpToolbar[3];
							sConfigToolbar = sConfigToolbar + "//Toolbar = \"" + sTmpToolbar + "\"" + "\r\n";
						}
					}

				}
			}
		}

		sConfig = sConfig + sConfigStyle + "\r\n" + sConfigToolbar + "%" + ">";

		WriteFile(s_eWebEditorPath + "jsp" + System.getProperty("file.separator") + "config.jsp", sConfig);
	}

	/**
	 * Sort.
	 *
	 * @param aryValue the ary value
	 * @param aryOrder the ary order
	 * @return the string[]
	 */
	public String[] Sort(String[] aryValue, String[] aryOrder) {
		String FirstOrder, SecondOrder;
		String FirstValue, SecondValue;
		boolean KeepChecking = true;
		while (KeepChecking) {
			KeepChecking = false;
			for (int i = 0; i < aryOrder.length; i++) {
				if (i == aryOrder.length - 1) {
					break;
				}
				if (Integer.valueOf(aryOrder[i]).intValue() > Integer.valueOf(aryOrder[i + 1]).intValue()) {
					FirstOrder = aryOrder[i];
					SecondOrder = aryOrder[i + 1];
					aryOrder[i] = SecondOrder;
					aryOrder[i + 1] = FirstOrder;
					FirstValue = aryValue[i];
					SecondValue = aryValue[i + 1];
					aryValue[i] = SecondValue;
					aryValue[i + 1] = FirstValue;
					KeepChecking = true;
				}
			}
		}
		return aryValue;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		String s_Domain = "192.168.85.212";
		String s_License = "2:6966:3:2:1::192.168.85.212:7087b70892eb78fe86ff5f83da17754f";
		EWebeditorUtils eWebeditorUtils = new EWebeditorUtils();
		String[] aa = eWebeditorUtils.split(s_License, ";");
		for (int i = 0; i < aa.length; i++) {
			String[] a = eWebeditorUtils.split(aa[i], ":");
			if (a.length == 8) {
				if (a[7].length() == 32) {

					if (a[0].equals("3")) {
						if (s_Domain.equals(a[6])) {
							System.out.println(true);
						} else if (s_Domain.length() > a[6].length()) {
							if (s_Domain.substring(s_Domain.length() - a[6].length() - 1).equals("." + a[6])) {
								System.out.println(true);
							}
						}
					} else {
						if ((s_Domain.equals(a[6])) || (s_Domain.equals("www." + a[6]))) {
							System.out.println(true);
						}
					}

				}
			}
		}
	}
}
