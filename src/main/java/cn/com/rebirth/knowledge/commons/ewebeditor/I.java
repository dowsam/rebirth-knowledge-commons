/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons I.java 2012-9-18 9:22:21 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.ewebeditor;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

/**
 * The Class I.
 *
 * @author l.xue.nong
 */
public class I {

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

	/** The s config. */
	private String sConfig;

	/** The s license. */
	private String sLicense;

	/** The a style. */
	private List<String> aStyle;

	/** The a toolbar. */
	private List<String> aToolbar;

	/**
	 * Instantiates a new i.
	 */
	public I() {
		super();
		myUtil = new EWebeditorUtils();
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

		sConfig = myUtil.ReadFile(myUtil.getConfigFileRealPath());
		sLicense = myUtil.getConfigString("License", sConfig);

		String sAction = myUtil.dealNull(m_request.getParameter("action")).toUpperCase();
		if (sAction.equals("LICENSE")) {
			ShowLicense();
		} else if (sAction.equals("CONFIG")) {
			ShowConfig();
		}
	}

	/**
	 * Show license.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void ShowLicense() throws IOException {
		if (sLicense.equals("")) {
			return;
		}

		String r = myUtil.dealNull(m_request.getParameter("r"));
		if (r.length() < 10) {
			return;
		}

		String s_Domain = GetDomain();
		if (s_Domain.equals("127.0.0.1") || s_Domain.equals("localhost")) {
			return;
		}

		String ret = "";
		String[] aa = myUtil.split(sLicense, ";");
		for (int i = 0; i < aa.length; i++) {
			String[] a = myUtil.split(aa[i], ":");
			if (a.length == 8) {
				if (a[7].length() == 32) {
					boolean b = false;
					if (a[0].equals("3")) {
						if (s_Domain.equals(a[6])) {
							b = true;
						} else if (s_Domain.length() > a[6].length()) {
							if (s_Domain.substring(s_Domain.length() - a[6].length() - 1).equals("." + a[6])) {
								b = true;
							}
						}
					} else {
						if ((s_Domain.equals(a[6])) || (s_Domain.equals("www." + a[6]))) {
							b = true;
						}
					}

					if (b) {
						for (int j = 0; j <= 6; j++) {
							ret = ret + a[j] + ":";
						}
						ret = ret + myUtil.MD5(a[7].substring(0, 16) + r, 16) + myUtil.MD5(a[7].substring(16) + r, 16);
						break;
					}
				}
			}
		}
		m_out.print(ret);
	}

	/**
	 * Show config.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void ShowConfig() throws IOException {
		aStyle = myUtil.getConfigArray("Style", sConfig);
		aToolbar = myUtil.getConfigArray("Toolbar", sConfig);

		String s_License = "";
		if (myUtil.CheckLicense(GetDomain(), sLicense)) {
			s_License = "ok";
		}

		String s_StyleName = myUtil.dealNull(m_request.getParameter("style"));

		int n_StyleID = 0;

		String[] aTmpStyle = new String[1];
		boolean b = false;

		for (int i = 0; i < aStyle.size(); i++) {
			aTmpStyle = myUtil.split(aStyle.get(i).toString(), "|||");
			if (s_StyleName.toLowerCase().equals(aTmpStyle[0].toLowerCase())) {
				n_StyleID = i;
				b = true;
				break;
			}
		}

		if (!b) {
			return;
		}

		String s_SKey = myUtil.dealNull(m_request.getParameter("skey"));
		String s_SParams = "";

		String ss_FileSize = "";
		String ss_FileBrowse = "";
		String ss_SpaceSize = "";
		String ss_SpacePath = "";
		String ss_PathMode = "";
		String ss_PathUpload = "";
		String ss_PathCusDir = "";
		String ss_PathCode = "";
		String ss_PathView = "";

		if ((aTmpStyle[61].equals("2")) && (!s_SKey.equals(""))) {
			ss_FileSize = (String) myUtil.dealNull(m_session.getAttribute("eWebEditor_" + s_SKey + "_FileSize"));
			ss_FileBrowse = (String) myUtil.dealNull(m_session.getAttribute("eWebEditor_" + s_SKey + "_FileBrowse"));
			ss_SpaceSize = (String) myUtil.dealNull(m_session.getAttribute("eWebEditor_" + s_SKey + "_SpaceSize"));
			ss_SpacePath = (String) myUtil.dealNull(m_session.getAttribute("eWebEditor_" + s_SKey + "_SpacePath"));
			ss_PathMode = (String) myUtil.dealNull(m_session.getAttribute("eWebEditor_" + s_SKey + "_PathMode"));
			ss_PathUpload = (String) myUtil.dealNull(m_session.getAttribute("eWebEditor_" + s_SKey + "_PathUpload"));
			ss_PathCusDir = (String) myUtil.dealNull(m_session.getAttribute("eWebEditor_" + s_SKey + "_PathCusDir"));
			ss_PathCode = (String) myUtil.dealNull(m_session.getAttribute("eWebEditor_" + s_SKey + "_PathCode"));
			ss_PathView = (String) myUtil.dealNull(m_session.getAttribute("eWebEditor_" + s_SKey + "_PathView"));

			if (myUtil.IsInt(ss_FileSize)) {
				aTmpStyle[11] = ss_FileSize;
				aTmpStyle[12] = ss_FileSize;
				aTmpStyle[13] = ss_FileSize;
				aTmpStyle[14] = ss_FileSize;
				aTmpStyle[15] = ss_FileSize;
				aTmpStyle[45] = ss_FileSize;
			} else {
				ss_FileSize = "";
			}
			if (ss_FileBrowse.equals("0") || ss_FileBrowse.equals("1")) {
				aTmpStyle[43] = ss_FileBrowse;
			} else {
				ss_FileBrowse = "";
			}
			if (myUtil.IsInt(ss_SpaceSize)) {
				aTmpStyle[78] = ss_SpaceSize;
			} else {
				ss_SpaceSize = "";
			}
			if (!ss_PathMode.equals("")) {
				aTmpStyle[19] = ss_PathMode;
			}
			if (!ss_PathUpload.equals("")) {
				aTmpStyle[3] = ss_PathUpload;
			}
			if (!ss_PathCode.equals("")) {
				aTmpStyle[23] = ss_PathCode;
			}
			if (!ss_PathView.equals("")) {
				aTmpStyle[22] = ss_PathView;
			}

			s_SParams = ss_FileSize + "|" + ss_FileBrowse + "|" + ss_SpaceSize + "|" + ss_SpacePath + "|" + ss_PathMode
					+ "|" + ss_PathUpload + "|" + ss_PathCusDir + "|" + ss_PathCode + "|" + ss_PathView;
			s_SParams = myUtil.MD5(aTmpStyle[70] + s_SParams, 16) + "|" + s_SParams;
		}

		String ret = "";
		ret = ret + "config.FixWidth = \"" + aTmpStyle[1] + "\";" + "\r\n";

		if (aTmpStyle[19].equals("3")) {
			ret = ret + "config.UploadUrl = \"" + aTmpStyle[23] + "\";" + "\r\n";
		} else {
			ret = ret + "config.UploadUrl = \"" + aTmpStyle[3] + "\";" + "\r\n";
		}

		ret = ret + "config.InitMode = \"" + aTmpStyle[18] + "\";" + "\r\n";
		ret = ret + "config.AutoDetectPaste = \"" + aTmpStyle[17] + "\";" + "\r\n";
		ret = ret + "config.BaseUrl = \"" + aTmpStyle[19] + "\";" + "\r\n";
		ret = ret + "config.BaseHref = \"" + aTmpStyle[22] + "\";" + "\r\n";
		ret = ret + "config.AutoRemote = \"" + aTmpStyle[24] + "\";" + "\r\n";
		ret = ret + "config.ShowBorder = \"" + aTmpStyle[25] + "\";" + "\r\n";
		ret = ret + "config.StateFlag = \"" + aTmpStyle[16] + "\";" + "\r\n";

		ret = ret + "config.SBCode = \"" + aTmpStyle[62] + "\";" + "\r\n";
		ret = ret + "config.SBEdit = \"" + aTmpStyle[63] + "\";" + "\r\n";
		ret = ret + "config.SBText = \"" + aTmpStyle[64] + "\";" + "\r\n";
		ret = ret + "config.SBView = \"" + aTmpStyle[65] + "\";" + "\r\n";
		ret = ret + "config.SBSize = \"" + aTmpStyle[76] + "\";" + "\r\n";
		ret = ret + "config.EnterMode = \"" + aTmpStyle[66] + "\";" + "\r\n";

		ret = ret + "config.Skin = \"" + aTmpStyle[2] + "\";" + "\r\n";
		ret = ret + "config.AllowBrowse = \"" + aTmpStyle[43] + "\";" + "\r\n";
		ret = ret + "config.AllowImageSize = \"" + aTmpStyle[13] + "\";" + "\r\n";
		ret = ret + "config.AllowFlashSize = \"" + aTmpStyle[12] + "\";" + "\r\n";
		ret = ret + "config.AllowMediaSize = \"" + aTmpStyle[14] + "\";" + "\r\n";
		ret = ret + "config.AllowFileSize = \"" + aTmpStyle[11] + "\";" + "\r\n";
		ret = ret + "config.AllowRemoteSize = \"" + aTmpStyle[15] + "\";" + "\r\n";
		ret = ret + "config.AllowLocalSize = \"" + aTmpStyle[45] + "\";" + "\r\n";

		ret = ret + "config.AllowImageExt = \"" + aTmpStyle[8] + "\";" + "\r\n";
		ret = ret + "config.AllowFlashExt = \"" + aTmpStyle[7] + "\";" + "\r\n";
		ret = ret + "config.AllowMediaExt = \"" + aTmpStyle[9] + "\";" + "\r\n";
		ret = ret + "config.AllowFileExt = \"" + aTmpStyle[6] + "\";" + "\r\n";
		ret = ret + "config.AllowRemoteExt = \"" + aTmpStyle[10] + "\";" + "\r\n";
		ret = ret + "config.AllowLocalExt = \"" + aTmpStyle[44] + "\";" + "\r\n";

		ret = ret + "config.AreaCssMode = \"" + aTmpStyle[67] + "\";" + "\r\n";
		ret = ret + "config.SLTFlag = \"" + aTmpStyle[29] + "\";" + "\r\n";
		ret = ret + "config.SLTMinSize = \"" + aTmpStyle[30] + "\";" + "\r\n";
		ret = ret + "config.SLTOkSize = \"" + aTmpStyle[31] + "\";" + "\r\n";
		ret = ret + "config.SLTMode = \"" + aTmpStyle[69] + "\";" + "\r\n";
		ret = ret + "config.SLTCheckFlag = \"" + aTmpStyle[77] + "\";" + "\r\n";
		ret = ret + "config.SYWZFlag = \"" + aTmpStyle[32] + "\";" + "\r\n";
		ret = ret + "config.SYTPFlag = \"" + aTmpStyle[52] + "\";" + "\r\n";
		ret = ret + "config.FileNameMode = \"" + aTmpStyle[68] + "\";" + "\r\n";
		ret = ret + "config.PaginationMode = \"" + aTmpStyle[72] + "\";" + "\r\n";
		ret = ret + "config.PaginationKey = \"" + aTmpStyle[73] + "\";" + "\r\n";
		ret = ret + "config.PaginationAutoFlag = \"" + aTmpStyle[74] + "\";" + "\r\n";
		ret = ret + "config.PaginationAutoNum = \"" + aTmpStyle[75] + "\";" + "\r\n";
		ret = ret + "config.SParams = \"" + myUtil.replace(s_SParams, "\\", "\\\\") + "\";" + "\r\n";
		ret = ret + "config.SpaceSize = \"" + aTmpStyle[78] + "\";" + "\r\n";
		ret = ret + "config.MFUBlockSize = \"" + aTmpStyle[80] + "\";" + "\r\n";
		ret = ret + "config.MFUEnable = \"" + aTmpStyle[81] + "\";" + "\r\n";
		ret = ret + "config.CodeFormat = \"" + aTmpStyle[82] + "\";" + "\r\n";
		ret = ret + "config.TB2Flag = \"" + aTmpStyle[83] + "\";" + "\r\n";
		ret = ret + "config.TB2Mode = \"" + aTmpStyle[84] + "\";" + "\r\n";
		ret = ret + "config.TB2Max = \"" + aTmpStyle[85] + "\";" + "\r\n";
		ret = ret + "config.ShowBlock = \"" + aTmpStyle[86] + "\";" + "\r\n";
		ret = ret + "config.WIIMode = \"" + aTmpStyle[95] + "\";" + "\r\n";
		ret = ret + "config.QFIFontName = \"" + aTmpStyle[96] + "\";" + "\r\n";
		ret = ret + "config.QFIFontSize = \"" + aTmpStyle[97] + "\";" + "\r\n";
		ret = ret + "config.UIMinHeight = \"" + aTmpStyle[98] + "\";" + "\r\n";
		ret = ret + "config.SYVNormal = \"" + aTmpStyle[99] + "\";" + "\r\n";
		ret = ret + "config.SYVLocal = \"" + aTmpStyle[100] + "\";" + "\r\n";
		ret = ret + "config.SYVRemote = \"" + aTmpStyle[101] + "\";" + "\r\n";
		ret = ret + "config.AutoDonePasteWord = \"" + aTmpStyle[102] + "\";" + "\r\n";
		ret = ret + "config.AutoDonePasteExcel = \"" + aTmpStyle[103] + "\";" + "\r\n";
		ret = ret + "config.AutoDoneQuickFormat = \"" + aTmpStyle[104] + "\";" + "\r\n";
		ret = ret + "config.WIAPI = \"" + aTmpStyle[105] + "\";" + "\r\n";

		ret = ret + "config.L = \"" + s_License + "\";" + "\r\n";
		ret = ret + "config.ServerExt = \"jsp\";" + "\r\n";
		ret = ret + "config.Charset = \"utf-8\";" + "\r\n";

		if (!ss_PathCusDir.equals("")) {
			ret = ret + "config.CusDir = \"" + ss_PathCusDir + "\";" + "\r\n";
		}

		ret = ret + "\r\n";
		ret = ret + "config.Toolbars = [\r\n";

		String s_Order = "";
		String s_ID = "";
		for (int n = 0; n < aToolbar.size(); n++) {
			if (!aToolbar.get(n).toString().equals("")) {
				String[] aTmpToolbar = myUtil.split(aToolbar.get(n).toString(), "|||");
				if (aTmpToolbar[0].equals(String.valueOf(n_StyleID))) {
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
			String[] a_ID = myUtil.split(s_ID, "|");

			for (int n = 0; n < a_ID.length; n++) {
				String[] aTmpToolbar = myUtil
						.split(aToolbar.get(Integer.valueOf(a_ID[n]).intValue()).toString(), "|||");
				String[] aTmpButton = myUtil.split(aTmpToolbar[1], "|");

				if (n > 0) {
					ret = ret + ",\r\n";
				}
				ret = ret + "\t[";

				for (int i = 0; i < aTmpButton.length; i++) {
					if (i > 0) {
						ret = ret + ", ";
					}
					ret = ret + "\"" + aTmpButton[i] + "\"";
				}
				ret = ret + "]";
			}
		}

		ret = ret + "\r\n];\r\n";
		m_out.print(ret);
	}

	/**
	 * Gets the domain.
	 *
	 * @return the string
	 */
	private String GetDomain() {

		String s_Url = myUtil.dealNull(m_request.getParameter("h"));
		if (!s_Url.equals("")) {
			return s_Url.toLowerCase();
		}

		s_Url = m_request.getRequestURL().toString().toLowerCase();
		boolean b = false;
		int n = s_Url.indexOf("://");
		if (n > 0) {
			s_Url = s_Url.substring(n + 3, s_Url.length());
			n = s_Url.indexOf("/");
			if (n > 0) {
				s_Url = s_Url.substring(0, n);
				n = s_Url.indexOf(":");
				if (n > 0) {
					s_Url = s_Url.substring(0, n);
				}
			}
			if (!s_Url.equals("")) {
				b = true;
			}
		}
		if (!b) {
			s_Url = m_request.getServerName().toLowerCase();
		}
		return s_Url;
	}

}
