/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons Browse.java 2012-9-18 9:25:16 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.ewebeditor;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

/**
 * The Class Browse.
 *
 * @author l.xue.nong
 */
public class Browse {

	/** The m_request. */
	protected HttpServletRequest m_request;

	/** The m_response. */
	protected HttpServletResponse m_response;

	/** The m_session. */
	private HttpSession m_session;

	/** The m_application. */
	protected ServletContext m_application;

	/** The m_pagecontext. */
	protected PageContext m_pagecontext;

	/** The m_out. */
	protected JspWriter m_out;

	/** The my util. */
	private EWebeditorUtils myUtil;

	/** The s file separator. */
	private String sFileSeparator;

	/**
	 * Instantiates a new browse.
	 */
	public Browse() {
		super();
		myUtil = new EWebeditorUtils();
		sFileSeparator = System.getProperty("file.separator");
	}

	/**
	 * Load.
	 *
	 * @param pagecontext the pagecontext
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public final void Load(PageContext pagecontext) throws ServletException, IOException {
		m_pagecontext = pagecontext;
		m_application = pagecontext.getServletContext();
		m_request = (HttpServletRequest) pagecontext.getRequest();
		m_response = (HttpServletResponse) pagecontext.getResponse();
		m_session = (HttpSession) pagecontext.getSession();
		m_out = pagecontext.getOut();
		myUtil.InitServer(pagecontext);

		InitUpload();
	}

	/**
	 * Inits the upload.
	 *
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void InitUpload() throws ServletException, IOException {
		String sConfig = myUtil.ReadFile(myUtil.getConfigFileRealPath());
		List<String> aStyle = myUtil.getConfigArray("Style", sConfig);

		String sAllowExt, sUploadDir, sBaseUrl;
		int nAllowBrowse;
		String sPathShareImage, sPathShareFlash, sPathShareMedia, sPathShareOther;

		// param
		String sType = myUtil.dealNull(m_request.getParameter("type")).toUpperCase();
		String sStyleName = myUtil.dealNull(m_request.getParameter("style"));
		String sCusDir = myUtil.dealNull(m_request.getParameter("cusdir"));
		String sAction = myUtil.dealNull(m_request.getParameter("action")).toUpperCase();

		String s_SKey = myUtil.dealNull(m_request.getParameter("skey"));

		// InitUpload

		String[] aStyleConfig = new String[1];
		boolean bValidStyle = false;

		for (int i = 0; i < aStyle.size(); i++) {
			aStyleConfig = myUtil.split(aStyle.get(i).toString(), "|||");
			if (sStyleName.toLowerCase().equals(aStyleConfig[0].toLowerCase())) {
				bValidStyle = true;
				break;
			}
		}

		if (!bValidStyle) {
			m_out.print(getOutScript("alert('Invalid Style!')"));
			m_out.close();
			return;
		}

		if (!aStyleConfig[61].equals("1")) {
			sCusDir = "";
		}

		String ss_FileSize = "", ss_FileBrowse = "", ss_SpaceSize = "", ss_PathMode = "", ss_PathUpload = "", ss_PathCusDir = "", ss_PathCode = "", ss_PathView = "";
		if ((aStyleConfig[61].equals("2")) && (!s_SKey.equals(""))) {
			ss_FileSize = (String) myUtil.dealNull(m_session.getAttribute("eWebEditor_" + s_SKey + "_FileSize"));
			ss_FileBrowse = (String) myUtil.dealNull(m_session.getAttribute("eWebEditor_" + s_SKey + "_FileBrowse"));
			ss_SpaceSize = (String) myUtil.dealNull(m_session.getAttribute("eWebEditor_" + s_SKey + "_SpaceSize"));
			ss_PathMode = (String) myUtil.dealNull(m_session.getAttribute("eWebEditor_" + s_SKey + "_PathMode"));
			ss_PathUpload = (String) myUtil.dealNull(m_session.getAttribute("eWebEditor_" + s_SKey + "_PathUpload"));
			ss_PathCusDir = (String) myUtil.dealNull(m_session.getAttribute("eWebEditor_" + s_SKey + "_PathCusDir"));
			ss_PathCode = (String) myUtil.dealNull(m_session.getAttribute("eWebEditor_" + s_SKey + "_PathCode"));
			ss_PathView = (String) myUtil.dealNull(m_session.getAttribute("eWebEditor_" + s_SKey + "_PathView"));

			if (myUtil.IsInt(ss_FileSize)) {
				aStyleConfig[11] = ss_FileSize;
				aStyleConfig[12] = ss_FileSize;
				aStyleConfig[13] = ss_FileSize;
				aStyleConfig[14] = ss_FileSize;
				aStyleConfig[15] = ss_FileSize;
				aStyleConfig[45] = ss_FileSize;
			} else {
				ss_FileSize = "";
			}
			if (ss_FileBrowse.equals("0") || ss_FileBrowse.equals("1")) {
				aStyleConfig[43] = ss_FileBrowse;
			} else {
				ss_FileBrowse = "";
			}
			if (myUtil.IsInt(ss_SpaceSize)) {
				aStyleConfig[78] = ss_SpaceSize;
			} else {
				ss_SpaceSize = "";
			}
			if (!ss_PathMode.equals("")) {
				aStyleConfig[19] = ss_PathMode;
			}
			if (!ss_PathUpload.equals("")) {
				aStyleConfig[3] = ss_PathUpload;
			}
			if (!ss_PathCode.equals("")) {
				aStyleConfig[23] = ss_PathCode;
			}
			if (!ss_PathView.equals("")) {
				aStyleConfig[22] = ss_PathView;
			}

			sCusDir = ss_PathCusDir;
		}

		sBaseUrl = aStyleConfig[19];
		nAllowBrowse = Integer.valueOf(aStyleConfig[43]).intValue();

		if (nAllowBrowse != 1) {
			m_out.print(getOutScript("alert('Do not allow browse!')"));
			m_out.close();
			return;
		}

		if (!sCusDir.equals("")) {
			sCusDir = myUtil.replace(sCusDir, "\\", "/");
			if ((sCusDir.startsWith("/")) || (sCusDir.startsWith(".")) || (sCusDir.endsWith("."))
					|| (sCusDir.indexOf("./") >= 0) || (sCusDir.indexOf("/.") >= 0) || (sCusDir.indexOf("//") >= 0)
					|| (sCusDir.indexOf("..") >= 0)) {
				sCusDir = "";
			} else {
				if (!sCusDir.endsWith("/")) {
					sCusDir = sCusDir + "/";
				}
			}
		}

		sUploadDir = aStyleConfig[3];
		if (!sBaseUrl.equals("3")) {
			sUploadDir = myUtil.getRealPathFromRelative(sUploadDir);
		}
		sUploadDir = GetSlashPath(sUploadDir);
		sUploadDir = sUploadDir + myUtil.replace(myUtil.replace(sCusDir, "/", sFileSeparator), "\\", sFileSeparator);

		if (sType.equals("FILE")) {
			sAllowExt = aStyleConfig[6];
		} else if (sType.equals("MEDIA")) {
			sAllowExt = aStyleConfig[9];
		} else if (sType.equals("FLASH")) {
			sAllowExt = aStyleConfig[7];
		} else {
			sAllowExt = aStyleConfig[8];
		}

		sPathShareImage = GetSlashPath(myUtil.getRealPathFromRelative("sharefile/image/"));
		sPathShareFlash = GetSlashPath(myUtil.getRealPathFromRelative("sharefile/flash/"));
		sPathShareMedia = GetSlashPath(myUtil.getRealPathFromRelative("sharefile/media/"));
		sPathShareOther = GetSlashPath(myUtil.getRealPathFromRelative("sharefile/other/"));

		String s_Out = "";
		if (sAction.equals("FILE")) {

			String s_ReturnFlag = myUtil.dealNull(m_request.getParameter("returnflag"));
			String s_FolderType = myUtil.dealNull(m_request.getParameter("foldertype"));
			String s_Dir = myUtil.dealNull(m_request.getParameter("dir"));
			s_Dir = java.net.URLDecoder.decode(s_Dir, "UTF-" + "8");

			String s_CurrDir = "";
			if (s_FolderType.equals("upload")) {
				s_CurrDir = sUploadDir;
			} else if (s_FolderType.equals("shareimage")) {
				sAllowExt = "";
				s_CurrDir = sPathShareImage;
			} else if (s_FolderType.equals("shareflash")) {
				sAllowExt = "";
				s_CurrDir = sPathShareFlash;
			} else if (s_FolderType.equals("sharemedia")) {
				sAllowExt = "";
				s_CurrDir = sPathShareMedia;
			} else {
				s_FolderType = "shareother";
				sAllowExt = "";
				s_CurrDir = sPathShareOther;
			}

			s_Dir = myUtil.replace(s_Dir, "\\", "/");
			if ((s_Dir.startsWith("/")) || (s_Dir.startsWith(".")) || (s_Dir.endsWith("."))
					|| (s_Dir.indexOf("./") >= 0) || (s_Dir.indexOf("/.") >= 0) || (s_Dir.indexOf("//") >= 0)
					|| (s_Dir.indexOf("..") >= 0)) {
				s_Dir = "";
			}

			String s_Dir2 = myUtil.replace(s_Dir, "/", sFileSeparator);
			s_Dir2 = myUtil.replace(s_Dir2, "\\", sFileSeparator);

			if (!s_Dir.equals("")) {
				if (CheckValidDir(s_CurrDir + s_Dir2)) {
					s_CurrDir += s_Dir2;
				} else {
					s_Dir = "";
				}
			}

			if (CheckValidDir(s_CurrDir)) {
				File file = new File(s_CurrDir);
				File[] filelist = file.listFiles();
				if (filelist != null && filelist.length > 0) {
					int n = -1;
					for (int i = 0; i < filelist.length; i++) {
						if (filelist[i].isFile()) {
							String s_FileName = filelist[i].getName();
							String s_FileExt = s_FileName.substring(s_FileName.lastIndexOf(".") + 1);
							s_FileExt = s_FileExt.toLowerCase();
							if (CheckValidExt(sAllowExt, s_FileExt)) {
								n++;
								s_Out = s_Out + "arr[" + String.valueOf(n) + "]=new Array(\"" + s_FileName + "\", \""
										+ String.valueOf(convertFileSize(filelist[i].length())) + "\",\""
										+ formatDate(new Date(filelist[i].lastModified())) + "\");\n";
							}
						}
					}
				}
			}

			s_Out = "var arr = new Array();\n" + s_Out + "parent.setFileList('" + s_ReturnFlag + "', '" + s_FolderType
					+ "', '" + s_Dir + "', arr);";
			m_out.print(getOutScript(s_Out));

		} else {

			s_Out = "var arrUpload = new Array();\n";
			s_Out += "var arrShareImage = new Array();\n";
			s_Out += "var arrShareFlash = new Array();\n";
			s_Out += "var arrShareMedia = new Array();\n";
			s_Out += "var arrShareOther = new Array();\n";

			s_Out += GetFolderTree(sUploadDir, "Upload", 1, 0).get(0).toString();

			sAllowExt = "";
			if (sType.equals("FILE")) {
				s_Out += GetFolderTree(sPathShareImage, "ShareImage", 1, 0).get(0).toString();
				s_Out += GetFolderTree(sPathShareFlash, "ShareFlash", 1, 0).get(0).toString();
				s_Out += GetFolderTree(sPathShareMedia, "ShareMedia", 1, 0).get(0).toString();
				s_Out += GetFolderTree(sPathShareOther, "ShareOther", 1, 0).get(0).toString();
			} else if (sType.equals("MEDIA")) {
				s_Out += GetFolderTree(sPathShareMedia, "ShareMedia", 1, 0).get(0).toString();
			} else if (sType.equals("FLASH")) {
				s_Out += GetFolderTree(sPathShareFlash, "ShareFlash", 1, 0).get(0).toString();
			} else {
				s_Out += GetFolderTree(sPathShareImage, "ShareImage", 1, 0).get(0).toString();
			}

			s_Out += "parent.setFolderList(arrUpload, arrShareImage, arrShareFlash, arrShareMedia, arrShareOther);";
			m_out.print(getOutScript(s_Out));
		}

	}

	/**
	 * Gets the slash path.
	 *
	 * @param str the str
	 * @return the string
	 */
	private String GetSlashPath(String str) {
		if (!str.endsWith(sFileSeparator)) {
			return str + sFileSeparator;
		}
		return str;
	}

	/**
	 * Gets the out script.
	 *
	 * @param str the str
	 * @return the out script
	 */
	private String getOutScript(String str) {
		String html = "";
		html += "<HTML><HEAD><meta http-equiv='Content-Type' content='text/html; charset=utf-8'><TITLE>eWebEditor</TITLE></head><body>";
		html += "<script language=javascript>" + str + "</script>";
		html += "</body></html>";
		return html;
	}

	/**
	 * Check valid ext.
	 *
	 * @param s_AllowExt the s_ allow ext
	 * @param sExt the s ext
	 * @return true, if successful
	 */
	private boolean CheckValidExt(String s_AllowExt, String sExt) {
		if (s_AllowExt.equals("")) {
			return true;
		}
		String[] aExt = myUtil.split(s_AllowExt, "|");
		for (int i = 0; i < aExt.length; i++) {
			if (aExt[i].toLowerCase().equals(sExt)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the folder tree.
	 *
	 * @param s_Dir the s_ dir
	 * @param s_Flag the s_ flag
	 * @param n_Indent the n_ indent
	 * @param n_TreeIndex the n_ tree index
	 * @return the array list
	 */
	private ArrayList<String> GetFolderTree(String s_Dir, String s_Flag, int n_Indent, int n_TreeIndex) {
		String s_List = "";
		ArrayList<String> aSubFolders = new ArrayList<String>();

		File file = new File(s_Dir);
		File[] filelist = file.listFiles();

		if (filelist != null && filelist.length > 0) {
			for (int i = 0; i < filelist.length; i++) {
				if (filelist[i].isDirectory()) {
					aSubFolders.add(filelist[i].getName());
				}
			}

			int n_Count = aSubFolders.size();
			String s_LastFlag = "";
			String s_Folder = "";
			for (int i = 1; i <= n_Count; i++) {
				if (i < n_Count) {
					s_LastFlag = "0";
				} else {
					s_LastFlag = "1";
				}

				s_Folder = aSubFolders.get(i - 1).toString();
				s_List = s_List + "arr" + s_Flag + "[" + String.valueOf(n_TreeIndex) + "]=new Array(\"" + s_Folder
						+ "\"," + String.valueOf(n_Indent) + ", " + s_LastFlag + ");\n";
				n_TreeIndex = n_TreeIndex + 1;
				ArrayList<String> a_Temp = GetFolderTree(s_Dir + s_Folder + sFileSeparator, s_Flag, n_Indent + 1,
						n_TreeIndex);
				s_List = s_List + a_Temp.get(0).toString();
				n_TreeIndex = Integer.valueOf(a_Temp.get(1).toString()).intValue();
			}
		}

		ArrayList<String> a_Return = new ArrayList<String>();
		a_Return.add(s_List);
		a_Return.add(String.valueOf(n_TreeIndex));
		return a_Return;
	}

	/**
	 * Check valid dir.
	 *
	 * @param path the path
	 * @return true, if successful
	 */
	private boolean CheckValidDir(String path) {
		java.io.File dir = new java.io.File(path);
		if (dir.isFile()) {
			return false;
		}
		if (!dir.exists()) {
			return false;
		}
		return true;
	}

	/**
	 * Convert file size.
	 *
	 * @param size the size
	 * @return the string
	 */
	private String convertFileSize(long size) {
		int divisor = 1024;
		String unit = "K";
		if (divisor == 1)
			return size / divisor + " " + unit;
		String aftercomma = "" + 100 * (size % divisor) / divisor;
		if (aftercomma.length() == 1)
			aftercomma = "0" + aftercomma;
		return size / divisor + "." + aftercomma + " " + unit;
	}

	/**
	 * Format date.
	 *
	 * @param myDate the my date
	 * @return the string
	 */
	private String formatDate(Date myDate) {
		String strFormat = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat formatter = new SimpleDateFormat(strFormat);
		String strDate = formatter.format(myDate);
		return strDate;
	}

}
