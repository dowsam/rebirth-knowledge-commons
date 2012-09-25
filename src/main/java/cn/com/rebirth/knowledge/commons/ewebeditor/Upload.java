/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons Upload.java 2012-9-18 9:20:31 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.ewebeditor;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.swing.ImageIcon;

/**
 * The Class Upload.
 *
 * @author l.xue.nong
 */
@SuppressWarnings("all")
public class Upload {

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

	/** The n allow size. */
	private long nAllowSize;

	/** The s auto dir. */
	private String sAllowExt, sUploadDir, sBaseUrl, sContentPath, sSetContentPath, sAutoDir;

	/** The n upload object. */
	private int nUploadObject;

	/** The s sy shadow color. */
	private String sSYText, sSYFontColor, sSYFontName, sSYPicPath, sSLTSYExt, sSYShadowColor;

	/** The n sytp image height. */
	private int nSLTFlag, nSLTMode, nSLTCheckFlag, nSLTMinSize, nSLTOkSize, nSYWZFlag, nSYFontSize, nSLTSYObject,
			nSYWZMinWidth, nSYShadowOffset, nSYWZMinHeight, nSYWZPosition, nSYWZTextWidth, nSYWZTextHeight,
			nSYWZPaddingH, nSYWZPaddingV, nSYTPFlag, nSYTPMinWidth, nSYTPMinHeight, nSYTPPosition, nSYTPPaddingH,
			nSYTPPaddingV, nSYTPImageWidth, nSYTPImageHeight;

	/** The n sytp opacity. */
	private float nSYTPOpacity;

	/** The s mfu mode. */
	private String sSpaceSize, sSpacePath, sMFUMode;

	/** The s param block flag. */
	private String sParamBlockFile, sParamBlockFlag;

	/** The s auto type dir. */
	private String sFileNameMode, sFileNameSameFix, sAutoDirOrderFlag, sAutoTypeDir;

	/** The s sy valid remote. */
	private String sSYValidNormal, sSYValidLocal, sSYValidRemote;

	// param
	/** The s action. */
	private String sAction;

	/** The s type. */
	private String sType;

	/** The s style name. */
	private String sStyleName;

	/** The s cus dir. */
	private String sCusDir;

	/** The s param sy flag. */
	private String sParamSYFlag;

	/** The s param rnd. */
	private String sParamRnd;
	// interface
	/** The s original file name. */
	private String sOriginalFileName;

	/** The s save file name. */
	private String sSaveFileName;

	/** The s path file name. */
	private String sPathFileName;

	/** The s real upload path. */
	private String sRealUploadPath;

	/** The s file separator. */
	private String sFileSeparator;

	/** The my util. */
	private EWebeditorUtils myUtil;

	/**
	 * Instantiates a new upload.
	 */
	public Upload() {
		super();
		sOriginalFileName = "";
		sSaveFileName = "";
		sPathFileName = "";
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

		InitUpload();
	}

	/**
	 * Inits the upload.
	 *
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void InitUpload() throws ServletException, IOException {
		sFileSeparator = System.getProperty("file.separator");

		String sConfig = myUtil.ReadFile(myUtil.getConfigFileRealPath());
		List<String> aStyle = myUtil.getConfigArray("Style", sConfig);

		// param
		String s_QueryStr = m_request.getQueryString();
		String[] a_Params = myUtil.split(s_QueryStr, "&");
		HashMap<String, String> params = new HashMap<String, String>();
		for (int i = 0; i < a_Params.length; i++) {
			String[] a_Param = myUtil.split(a_Params[i], "=");
			if (a_Param.length == 2) {
				params.put(a_Param[0], a_Param[1]);
			}
		}

		sAction = myUtil.dealNull((String) params.get("action")).toUpperCase();
		sType = myUtil.dealNull((String) params.get("type")).toUpperCase();
		sStyleName = myUtil.dealNull((String) params.get("style"));
		sCusDir = myUtil.dealNull((String) params.get("cusdir"));
		sParamSYFlag = myUtil.dealNull((String) params.get("syflag"));
		sParamRnd = myUtil.dealNull((String) params.get("rnd"));

		String s_SKey = myUtil.dealNull((String) params.get("skey"));
		String s_SParams = myUtil.dealNull((String) params.get("sparams"));

		sParamBlockFile = myUtil.dealNull((String) params.get("blockfile"));
		sParamBlockFlag = myUtil.dealNull((String) params.get("blockflag"));
		if (!sParamBlockFile.equals("")) {
			sParamBlockFile = java.net.URLDecoder.decode(sParamBlockFile, "utf-8");
			if (!IsFileNameFormat(sParamBlockFile)) {
				OutScript("blockfile");
				return;
			}
		}

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
			OutScript("parent.UploadError('style')");
			return;
		}

		if (!aStyleConfig[61].equals("1")) {
			sCusDir = "";
		}

		String ss_FileSize = "", ss_FileBrowse = "", ss_SpaceSize = "", ss_SpacePath = "", ss_PathMode = "", ss_PathUpload = "", ss_PathCusDir = "", ss_PathCode = "", ss_PathView = "";
		if ((aStyleConfig[61].equals("2"))
				&& ((!s_SKey.equals("")) || (myUtil.IsOkSParams(s_SParams, aStyleConfig[70])))) {
			if (!s_SKey.equals("")) {
				ss_FileSize = (String) myUtil.dealNull(m_session.getAttribute("eWebEditor_" + s_SKey + "_FileSize"));
				ss_FileBrowse = (String) myUtil
						.dealNull(m_session.getAttribute("eWebEditor_" + s_SKey + "_FileBrowse"));
				ss_SpaceSize = (String) myUtil.dealNull(m_session.getAttribute("eWebEditor_" + s_SKey + "_SpaceSize"));
				ss_SpacePath = (String) myUtil.dealNull(m_session.getAttribute("eWebEditor_" + s_SKey + "_SpacePath"));
				ss_PathMode = (String) myUtil.dealNull(m_session.getAttribute("eWebEditor_" + s_SKey + "_PathMode"));
				ss_PathUpload = (String) myUtil
						.dealNull(m_session.getAttribute("eWebEditor_" + s_SKey + "_PathUpload"));
				ss_PathCusDir = (String) myUtil
						.dealNull(m_session.getAttribute("eWebEditor_" + s_SKey + "_PathCusDir"));
				ss_PathCode = (String) myUtil.dealNull(m_session.getAttribute("eWebEditor_" + s_SKey + "_PathCode"));
				ss_PathView = (String) myUtil.dealNull(m_session.getAttribute("eWebEditor_" + s_SKey + "_PathView"));
			} else {
				String[] a_SParams = myUtil.split(s_SParams, "|");
				ss_FileSize = a_SParams[1];
				ss_FileBrowse = a_SParams[2];
				ss_SpaceSize = a_SParams[3];
				ss_SpacePath = a_SParams[4];
				ss_PathMode = a_SParams[5];
				ss_PathUpload = a_SParams[6];
				ss_PathCusDir = a_SParams[7];
				ss_PathCode = a_SParams[8];
				ss_PathView = a_SParams[9];
			}

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
			sSpacePath = ss_SpacePath;
		} else {
			sSpacePath = "";
		}

		sBaseUrl = aStyleConfig[19];
		nUploadObject = Integer.valueOf(aStyleConfig[20]).intValue();
		sAutoDir = aStyleConfig[71];

		sUploadDir = aStyleConfig[3];

		if (sBaseUrl.equals("1")) {
			sContentPath = RelativePath2RootPath(sUploadDir);
		} else if (sBaseUrl.equals("2")) {
			sContentPath = RootPath2DomainPath(RelativePath2RootPath(sUploadDir));
		} else {
			//0,3
			sContentPath = aStyleConfig[23];
		}
		sSetContentPath = sContentPath;

		if (sType.equals("REMOTE")) {
			sAllowExt = aStyleConfig[10];
			nAllowSize = Long.valueOf(aStyleConfig[15]).longValue();
			sAutoTypeDir = aStyleConfig[93];
		} else if (sType.equals("FILE")) {
			sAllowExt = aStyleConfig[6];
			nAllowSize = Long.valueOf(aStyleConfig[11]).longValue();
			sAutoTypeDir = aStyleConfig[92];
		} else if (sType.equals("MEDIA")) {
			sAllowExt = aStyleConfig[9];
			nAllowSize = Long.valueOf(aStyleConfig[14]).longValue();
			sAutoTypeDir = aStyleConfig[91];
		} else if (sType.equals("FLASH")) {
			sAllowExt = aStyleConfig[7];
			nAllowSize = Long.valueOf(aStyleConfig[12]).longValue();
			sAutoTypeDir = aStyleConfig[90];
		} else if (sType.equals("LOCAL")) {
			sAllowExt = aStyleConfig[44];
			nAllowSize = Long.valueOf(aStyleConfig[45]).longValue();
			sAutoTypeDir = aStyleConfig[94];
		} else {
			sAllowExt = aStyleConfig[8];
			nAllowSize = Long.valueOf(aStyleConfig[13]).longValue();
			sAutoTypeDir = aStyleConfig[89];
		}

		nSLTFlag = Integer.valueOf(aStyleConfig[29]).intValue();
		nSLTMode = Integer.valueOf(aStyleConfig[69]).intValue();
		nSLTCheckFlag = Integer.valueOf(aStyleConfig[77]).intValue();
		nSLTMinSize = Integer.valueOf(aStyleConfig[30]).intValue();
		nSLTOkSize = Integer.valueOf(aStyleConfig[31]).intValue();
		nSYWZFlag = Integer.valueOf(aStyleConfig[32]).intValue();
		sSYText = aStyleConfig[33];
		sSYFontColor = aStyleConfig[34];
		nSYFontSize = Integer.valueOf(aStyleConfig[35]).intValue();
		sSYFontName = aStyleConfig[36];
		sSYPicPath = aStyleConfig[37];
		nSLTSYObject = Integer.valueOf(aStyleConfig[38]).intValue();
		sSLTSYExt = aStyleConfig[39];
		nSYWZMinWidth = Integer.valueOf(aStyleConfig[40]).intValue();
		sSYShadowColor = aStyleConfig[41];
		nSYShadowOffset = Integer.valueOf(aStyleConfig[42]).intValue();
		nSYWZMinHeight = Integer.valueOf(aStyleConfig[46]).intValue();
		nSYWZPosition = Integer.valueOf(aStyleConfig[47]).intValue();
		nSYWZTextWidth = Integer.valueOf(aStyleConfig[48]).intValue();
		nSYWZTextHeight = Integer.valueOf(aStyleConfig[49]).intValue();
		nSYWZPaddingH = Integer.valueOf(aStyleConfig[50]).intValue();
		nSYWZPaddingV = Integer.valueOf(aStyleConfig[51]).intValue();
		nSYTPFlag = Integer.valueOf(aStyleConfig[52]).intValue();
		nSYTPMinWidth = Integer.valueOf(aStyleConfig[53]).intValue();
		nSYTPMinHeight = Integer.valueOf(aStyleConfig[54]).intValue();
		nSYTPPosition = Integer.valueOf(aStyleConfig[55]).intValue();
		nSYTPPaddingH = Integer.valueOf(aStyleConfig[56]).intValue();
		nSYTPPaddingV = Integer.valueOf(aStyleConfig[57]).intValue();
		nSYTPImageWidth = Integer.valueOf(aStyleConfig[58]).intValue();
		nSYTPImageHeight = Integer.valueOf(aStyleConfig[59]).intValue();
		nSYTPOpacity = Float.valueOf(aStyleConfig[60]).floatValue();
		sSpaceSize = aStyleConfig[78];
		sMFUMode = aStyleConfig[79];
		sFileNameMode = aStyleConfig[68];
		sFileNameSameFix = aStyleConfig[87];
		sAutoDirOrderFlag = aStyleConfig[88];
		sSYValidNormal = aStyleConfig[99];
		sSYValidLocal = aStyleConfig[100];
		sSYValidRemote = aStyleConfig[101];

		if (((sAction.equals("SAVE") || sAction.equals("MFU")) && !sSYValidNormal.equals("1"))
				|| (sAction.equals("LOCAL") && !sSYValidLocal.equals("1"))
				|| (sAction.equals("REMOTE") && !sSYValidRemote.equals("1"))) {
			nSYWZFlag = 0;
			nSYTPFlag = 0;
		}

		if (nSYWZFlag == 2) {
			if (sParamSYFlag.equals("1")) {
				nSYWZFlag = 1;
			} else {
				nSYWZFlag = 0;
			}
		}
		if (nSYTPFlag == 2) {
			if (sParamSYFlag.equals("1")) {
				nSYTPFlag = 1;
			} else {
				nSYTPFlag = 0;
			}
		}

		if (!myUtil.IsInt(sParamRnd)) {
			sParamRnd = "";
		}

		if (!sBaseUrl.equals("3")) {
			sRealUploadPath = myUtil.getRealPathFromRelative(sUploadDir);
		} else {
			sRealUploadPath = sUploadDir;
		}
		if (!sRealUploadPath.endsWith(sFileSeparator)) {
			sRealUploadPath += sFileSeparator;
		}

		//if (sSYPicPath.startsWith("../")){
		//	sSYPicPath = sSYPicPath.substring(3);
		//}else if (sSYPicPath.startsWith("./")){
		//	sSYPicPath = "jsp/"+sSYPicPath.substring(2);
		//}else if (!sSYPicPath.startsWith("/")){
		//	sSYPicPath = "jsp/"+sSYPicPath;
		//}
		if (!sSYPicPath.equals("")) {
			sSYPicPath = myUtil.getRealPathFromRelative(sSYPicPath);
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

		DoCreateNewDir();

		if (sAction.equals("REMOTE")) {
			DoRemote();

		} else if (sAction.equals("SAVE")) {
			DoSave();

		} else if (sAction.equals("LOCAL")) {
			DoLocal();

		} else if (sAction.equals("MFU")) {
			DoMFU();

		}

	}

	/**
	 * Do create new dir.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void DoCreateNewDir() throws IOException {
		if (!sCusDir.equals("")) {
			String[] aTmp = myUtil.split(sCusDir, "/");
			for (int i = 0; i < aTmp.length; i++) {
				if (!aTmp[i].equals("")) {
					sRealUploadPath += aTmp[i] + sFileSeparator;
					sContentPath += aTmp[i] + "/";
					Mkdir(sRealUploadPath);
				}
			}
		}

		if (!CheckSpaceSize()) {
			return;
		}

		if (sAutoDirOrderFlag.equals("0")) {
			DoCreateNewTypeDir();
			DoCreateNewDateDir();
		} else if (sAutoDirOrderFlag.equals("1")) {
			DoCreateNewDateDir();
			DoCreateNewTypeDir();
		}
	}

	/**
	 * Do create new date dir.
	 */
	private void DoCreateNewDateDir() {
		if (sAutoDir.equals("")) {
			return;
		}

		String s_NewDir = myUtil.ReplaceTime(new Date(), sAutoDir);
		String[] aTmp = myUtil.split(s_NewDir, "/");
		for (int i = 0; i < aTmp.length; i++) {
			if (!aTmp[i].equals("")) {
				sRealUploadPath += aTmp[i] + sFileSeparator;
				sContentPath += aTmp[i] + "/";
				Mkdir(sRealUploadPath);
			}
		}
	}

	/**
	 * Do create new type dir.
	 */
	private void DoCreateNewTypeDir() {
		if (sAutoTypeDir.equals("")) {
			return;
		}

		String[] aTmp = myUtil.split(sAutoTypeDir, "/");
		for (int i = 0; i < aTmp.length; i++) {
			if (!aTmp[i].equals("")) {
				sRealUploadPath += aTmp[i] + sFileSeparator;
				sContentPath += aTmp[i] + "/";
				Mkdir(sRealUploadPath);
			}
		}
	}

	/**
	 * Do remote.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void DoRemote() throws IOException {

		String sRemoteContent = myUtil.dealNull(m_request.getParameter("eWebEditor_UploadText"));
		if (!sAllowExt.equals("") && !sRemoteContent.equals("")) {
			Pattern p = Pattern
					.compile("((http|https|ftp|rtsp|mms):(\\/\\/|\\\\\\\\){1}(([A-Za-z0-9_-])+[.]){1,}([A-Za-z0-9]{1,5})\\/(\\S+\\.("
							+ sAllowExt + ")))");
			Matcher m = p.matcher(sRemoteContent);
			ArrayList<String> a_RemoteUrl = new ArrayList<String>();
			String sRemoteurl = "";
			boolean bFind = false;

			String s_SameSiteDomain = "";
			if (sBaseUrl.equals("3")) {
				s_SameSiteDomain = GetDomainFromUrl(sSetContentPath);
			}
			if (s_SameSiteDomain.equals("")) {
				s_SameSiteDomain = m_request.getServerName().toLowerCase();
			}

			while (m.find()) {
				sRemoteurl = sRemoteContent.substring(m.start(), m.end());
				boolean b_SameSiteUrl = false;

				if (GetDomainFromUrl(sRemoteurl).equals(s_SameSiteDomain)) {
					b_SameSiteUrl = true;
				}

				if (!b_SameSiteUrl) {
					bFind = false;
					for (int i = 0; i < a_RemoteUrl.size(); i++) {
						if (sRemoteurl.equals(a_RemoteUrl.get(i).toString())) {
							bFind = true;
						}
					}
					if (bFind == false) {
						a_RemoteUrl.add(sRemoteurl);
					}
				}
			}

			String SaveFileType = "";
			String SaveFileName = "";
			for (int i = 0; i < a_RemoteUrl.size(); i++) {
				sRemoteurl = a_RemoteUrl.get(i).toString();
				SaveFileType = sRemoteurl.substring(sRemoteurl.lastIndexOf(".") + 1);
				SaveFileName = GetRndFileName(SaveFileType);
				if (SaveRemoteFile(SaveFileName, sRemoteurl, sRealUploadPath)) {
					makeImageSY(sRealUploadPath + SaveFileName);
					if (!sOriginalFileName.equals("")) {
						sOriginalFileName = sOriginalFileName + "|";
						sSaveFileName = sSaveFileName + "|";
						sPathFileName = sPathFileName + "|";
					}
					sOriginalFileName = sOriginalFileName + sRemoteurl.substring(sRemoteurl.lastIndexOf("/") + 1);
					sSaveFileName = sSaveFileName + SaveFileName;
					sPathFileName = sPathFileName + sContentPath + SaveFileName;
					sRemoteContent = myUtil.replace(sRemoteContent, sRemoteurl, sContentPath + SaveFileName);
				}
			}
		}

		m_out.print("<html><head><title>eWebEditor</title><meta http-equiv='Content-Type' content='text/html; charset=utf-8'></head><body><input type=hidden id=UploadText value=\""
				+ inHTML(sRemoteContent) + "\"></body></html>");
		OutScript("parent.setHTML(document.getElementById('UploadText').value);try{parent.addUploadFile('"
				+ sOriginalFileName + "', '" + sPathFileName + "');} catch(e){} parent.remoteUploadOK();");

	}

	/**
	 * Do save.
	 *
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void DoSave() throws ServletException, IOException {
		m_out.print("<html><head><title>eWebEditor</title><meta http-equiv='Content-Type' content='text/html; charset=utf-8'></head><body>");

		String s_Flag = doingUpload();
		if (!s_Flag.equals("ok")) {
			OutScript("parent.UploadError('" + s_Flag + "')");
			return;
		}

		sOriginalFileName = myUtil.replace(sOriginalFileName, "'", "\\'");
		sOriginalFileName = myUtil.replace(sOriginalFileName, "\"", "\\\"");

		String s_SmallImageFile, s_SmallImagePathFile, s_SmallImageScript;
		boolean b_SY;
		s_SmallImageFile = getSmallImageFile(sSaveFileName);
		s_SmallImagePathFile = "";
		s_SmallImageScript = "";
		if (makeImageSLT(sRealUploadPath, sSaveFileName, s_SmallImageFile)) {
			switch (nSLTMode) {
			case 1:
				b_SY = makeImageSY(sRealUploadPath + s_SmallImageFile);
				b_SY = makeImageSY(sRealUploadPath + sSaveFileName);
				s_SmallImagePathFile = sContentPath + s_SmallImageFile;
				s_SmallImageScript = "try{obj.addUploadFile('" + sOriginalFileName + "', '" + s_SmallImagePathFile
						+ "');} catch(e){} ";
				s_SmallImagePathFile = "";
				break;
			case 2:
				b_SY = makeImageSY(sRealUploadPath + s_SmallImageFile);
				DelFile(sRealUploadPath + sSaveFileName);
				sSaveFileName = s_SmallImageFile;
				break;
			default:
				b_SY = makeImageSY(sRealUploadPath + s_SmallImageFile);
				b_SY = makeImageSY(sRealUploadPath + sSaveFileName);
				s_SmallImagePathFile = sContentPath + s_SmallImageFile;
				s_SmallImageScript = "try{obj.addUploadFile('" + sOriginalFileName + "', '" + s_SmallImagePathFile
						+ "');} catch(e){} ";
				break;
			}

		} else {
			s_SmallImageFile = "";
			b_SY = makeImageSY(sRealUploadPath + sSaveFileName);
		}

		sPathFileName = sContentPath + sSaveFileName;
		OutScript("var obj=parent.EWIN; try{obj.addUploadFile('" + sOriginalFileName + "', '" + sPathFileName
				+ "');} catch(e){} " + s_SmallImageScript + "parent.UploadSaved('" + sPathFileName + "','"
				+ s_SmallImagePathFile + "');");

	}

	/**
	 * Do local.
	 *
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void DoLocal() throws ServletException, IOException {
		String s_Flag = doingUpload();
		if (!s_Flag.equals("ok")) {
			return;
		}
		makeImageSY(sRealUploadPath + sSaveFileName);

		sPathFileName = sContentPath + sSaveFileName;
		m_out.print(sPathFileName);
	}

	/**
	 * Do mfu.
	 *
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void DoMFU() throws ServletException, IOException {
		if (sParamBlockFlag.equals("cancel")) {
			if (!sParamBlockFile.equals("")) {
				DelFile(sRealUploadPath + sParamBlockFile + ".tmp1");
			}
			m_out.print("ok");
			return;
		}

		String s_Flag = doingUpload();
		if (!s_Flag.equals("ok")) {
			return;
		}

		if (sParamBlockFlag.equals("end")) {

			String s_SmallImageFile, s_SmallImagePathFile, s_SmallImageScript;
			boolean b_SY;
			s_SmallImageFile = getSmallImageFile(sSaveFileName);
			s_SmallImagePathFile = "";
			s_SmallImageScript = "";
			if (makeImageSLT(sRealUploadPath, sSaveFileName, s_SmallImageFile)) {
				switch (nSLTMode) {
				case 1:
					b_SY = makeImageSY(sRealUploadPath + s_SmallImageFile);
					b_SY = makeImageSY(sRealUploadPath + sSaveFileName);
					s_SmallImagePathFile = sContentPath + s_SmallImageFile;
					s_SmallImageScript = "try{obj.addUploadFile('" + sOriginalFileName + "', '" + s_SmallImagePathFile
							+ "');} catch(e){} ";
					s_SmallImagePathFile = "";
					break;
				case 2:
					b_SY = makeImageSY(sRealUploadPath + s_SmallImageFile);
					DelFile(sRealUploadPath + sSaveFileName);
					sSaveFileName = s_SmallImageFile;
					break;
				default:
					b_SY = makeImageSY(sRealUploadPath + s_SmallImageFile);
					b_SY = makeImageSY(sRealUploadPath + sSaveFileName);
					s_SmallImagePathFile = sContentPath + s_SmallImageFile;
					s_SmallImageScript = "try{obj.addUploadFile('" + sOriginalFileName + "', '" + s_SmallImagePathFile
							+ "');} catch(e){} ";
					break;
				}

			} else {
				s_SmallImageFile = "";
				b_SY = makeImageSY(sRealUploadPath + sSaveFileName);
			}

			sPathFileName = sContentPath + sSaveFileName;
			m_out.print(sPathFileName + "::" + s_SmallImagePathFile);
		} else {
			m_out.print(sSaveFileName.substring(0, sSaveFileName.lastIndexOf(".")));
		}
	}

	/**
	 * Doing upload.
	 *
	 * @return the string
	 * @throws ServletException the servlet exception
	 */
	private String doingUpload() throws ServletException {
		switch (nUploadObject) {
		case 1:
			return doingUpload_CommonsFileUpload();
		default:
			return doingUpload_CommonsFileUpload();
		}
	}

	/**
	 * Doing upload_ commons file upload.
	 *
	 * @return the string
	 * @throws ServletException the servlet exception
	 */
	private String doingUpload_CommonsFileUpload() throws ServletException {
		// Create a factory for disk-based file items
		org.apache.commons.fileupload.disk.DiskFileItemFactory factory = new org.apache.commons.fileupload.disk.DiskFileItemFactory();

		// Set factory constraints
		factory.setSizeThreshold(4096);
		factory.setRepository(new File(sRealUploadPath));

		// Create a new file upload handler
		org.apache.commons.fileupload.servlet.ServletFileUpload upload = new org.apache.commons.fileupload.servlet.ServletFileUpload(
				factory);

		// Set overall request size constraint
		upload.setSizeMax(nAllowSize * 1024);
		//upload.setFileSizeMax(nAllowSize*1024);

		upload.setHeaderEncoding("utf-8");

		try {
			// Parse the request
			java.util.List items = upload.parseRequest(m_request);
			// Process the uploaded items
			Iterator iter = items.iterator();

			while (iter.hasNext()) {
				org.apache.commons.fileupload.FileItem item = (org.apache.commons.fileupload.FileItem) iter.next();
				if (item.isFormField()) {
					continue;
				} // Process a regular form field

				String s_FileName = item.getName(); // include path
				if (s_FileName != null) {
					s_FileName = org.apache.commons.io.FilenameUtils.getName(s_FileName);
				}

				long n_FileSize = item.getSize();
				if (s_FileName == null || s_FileName.equals("") || n_FileSize == 0) {
					continue;
				}

				String s_FileExt = s_FileName.substring(s_FileName.lastIndexOf(".") + 1);
				if (!CheckValidExt(s_FileExt)) {
					return "ext";
				}

				sOriginalFileName = s_FileName;
				sSaveFileName = GetRndFileName(s_FileExt);

				String s_MapPath = sRealUploadPath + sSaveFileName;
				String s_TrueSavePath = MFU_GetSavePath(s_MapPath);

				item.write(new File(s_TrueSavePath));

				MFU_DoUploadAfter(s_MapPath);
			}
		} catch (Exception e) {
			return "size";
		}
		return "ok";
	}

	/**
	 * MF u_ get save path.
	 *
	 * @param s_Path the s_ path
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private String MFU_GetSavePath(String s_Path) throws IOException {
		if (!sAction.equals("MFU")) {
			return s_Path;
		}

		String s_Ret = "";
		if (sParamBlockFile.equals("")) {
			//new
			if (sParamBlockFlag.equals("end")) {
				s_Ret = s_Path;
			} else {
				s_Ret = s_Path + ".tmp1";
			}
		} else {
			if (!IsFileExists(s_Path + ".tmp1")) {
				OutScript("file");
				return "";
			}

			//append
			s_Ret = s_Path + ".tmp2";
		}
		return s_Ret;
	}

	/**
	 * MF u_ do upload after.
	 *
	 * @param s_Path the s_ path
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void MFU_DoUploadAfter(String s_Path) throws IOException {
		if (!sAction.equals("MFU")) {
			return;
		}

		if (!sParamBlockFile.equals("")) {
			//if (sMFUMode.equals("1")){
			//	MFU_DoMergeFile_Adv(s_Path);
			//}else{
			MFU_DoMergeFile_Normal(s_Path);
			//}

			if (sParamBlockFlag.equals("end")) {
				//rename
				RenameFile(s_Path + ".tmp1", s_Path);
			}
		}
	}

	/**
	 * MF u_ do merge file_ normal.
	 *
	 * @param s_File the s_ file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void MFU_DoMergeFile_Normal(String s_File) throws IOException {
		String s_File1 = s_File + ".tmp1";
		String s_File2 = s_File + ".tmp2";

		FileOutputStream fos = new FileOutputStream(s_File1, true);
		FileInputStream fis = new FileInputStream(s_File2);
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = fis.read(buffer, 0, 1024)) != -1) {
			fos.write(buffer, 0, len);
			fos.flush();
		}
		fis.close();
		fos.close();

		//del tmp2
		DelFile(s_File2);
	}

	/**
	 * Gets the rnd file name.
	 *
	 * @param s_Ext the s_ ext
	 * @return the string
	 */
	private String GetRndFileName(String s_Ext) {
		if (sAction.equals("MFU") && !sParamBlockFile.equals("")) {
			return sParamBlockFile + "." + s_Ext;
		}

		int i_Rnd = (int) (Math.random() * 900) + 100;
		String s_Rnd = String.valueOf(i_Rnd) + sParamRnd;

		String s_RndTime = myUtil.formatDate(new Date(), 4) + s_Rnd;
		String s_FileName = s_RndTime + "." + s_Ext;

		if (sAction.equals("SAVE") || sAction.equals("MFU")) {
			String s_Pre = GetOriginalFileNamePre(sOriginalFileName, s_Ext);
			if (!s_Pre.equals("")) {
				if (sFileNameMode.equals("1") || (sFileNameMode.equals("2") && sType.equals("FILE"))) {
					s_FileName = s_Pre + "." + s_Ext;

					if (!sFileNameSameFix.equals("")) {
						if (IsFileExists(sRealUploadPath + s_FileName)) {
							s_FileName = myUtil.replace(sFileNameSameFix, "{time}", s_RndTime);
							if (s_FileName.indexOf("{sn}") >= 0) {
								int i = 0;
								while (true) {
									i++;
									String s = myUtil.replace(s_FileName, "{sn}", String.valueOf(i));
									s = myUtil.replace(s, "{name}", s_Pre);
									if (!IsFileExists(sRealUploadPath + s + "." + s_Ext)) {
										s_FileName = s;
										break;
									}
								}
							} else {
								s_FileName = myUtil.replace(s_FileName, "{name}", s_Pre);
							}
							s_FileName = s_FileName + "." + s_Ext;
						}
					} else {
						if (IsFileExists(sRealUploadPath + s_FileName)) {
							DelFile(sRealUploadPath + s_FileName);
						}
						return s_FileName;
					}
				}
			}
		}

		if (IsFileExists(sRealUploadPath + s_FileName)) {
			return GetRndFileName(s_Ext);
		} else {
			return s_FileName;
		}
	}

	/**
	 * Gets the original file name pre.
	 *
	 * @param s_FileName the s_ file name
	 * @param s_OkExt the s_ ok ext
	 * @return the string
	 */
	private String GetOriginalFileNamePre(String s_FileName, String s_OkExt) {
		s_FileName = myUtil.replace(s_FileName, "%20", " ");
		s_FileName = s_FileName.trim();
		if (s_FileName.equals("")) {
			return "";
		}

		s_FileName = myUtil.replace(s_FileName, "/", "\\");
		int n = s_FileName.lastIndexOf("\\");
		if (n >= 0) {
			s_FileName = s_FileName.substring(n + 1);
		}

		n = s_FileName.lastIndexOf(".");
		if (n <= 0) {
			return "";
		}

		String s_Ext = s_FileName.substring(n + 1);
		if (!s_Ext.toLowerCase().equals(s_OkExt.toLowerCase())) {
			return "";
		}

		String s_Pre = s_FileName.substring(0, n);
		if (!IsFileNameFormat(s_Pre)) {
			s_Pre = "";
		}

		return s_Pre.trim();
	}

	/**
	 * Out script.
	 *
	 * @param str the str
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void OutScript(String str) throws IOException {
		m_out.print("<script language=javascript>" + str + "</script>");
	}

	/**
	 * Check valid ext.
	 *
	 * @param sExt the s ext
	 * @return true, if successful
	 */
	private boolean CheckValidExt(String sExt) {
		String[] aExt = myUtil.split(sAllowExt, "|");
		for (int i = 0; i < aExt.length; i++) {
			if (aExt[i].toLowerCase().equals(sExt.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Mkdir.
	 *
	 * @param path the path
	 */
	private void Mkdir(String path) {
		java.io.File dir = new java.io.File(path);
		if (dir == null) {
			return;
		}
		if (dir.isFile()) {
			return;
		}
		if (!dir.exists()) {
			boolean result = dir.mkdirs();
		}
	}

	/**
	 * Relative path2 root path.
	 *
	 * @param url the url
	 * @return the string
	 */
	private String RelativePath2RootPath(String url) {
		String sTempUrl = url;

		if (sTempUrl.substring(0, 1).equals("/")) {
			return m_request.getContextPath() + sTempUrl;
		}

		String sWebEditorPath = m_request.getRequestURI();
		sWebEditorPath = sWebEditorPath.substring(0, sWebEditorPath.lastIndexOf("/"));
		sWebEditorPath = sWebEditorPath.substring(0, sWebEditorPath.lastIndexOf("/"));
		while (sTempUrl.startsWith("../")) {
			sTempUrl = sTempUrl.substring(3);
			sWebEditorPath = sWebEditorPath.substring(0, sWebEditorPath.lastIndexOf("/"));
		}
		return sWebEditorPath + "/" + sTempUrl;
	}

	/**
	 * Root path2 domain path.
	 *
	 * @param url the url
	 * @return the string
	 */
	private String RootPath2DomainPath(String url) {
		String s_Protocol = m_request.getProtocol();
		String s_ServerName = m_request.getServerName();
		String s_ServerPort = String.valueOf(m_request.getServerPort());
		String sHost = myUtil.split(s_Protocol, "/")[0] + "://" + s_ServerName;
		String sPort = s_ServerPort;
		if (!sPort.equals("80")) {
			sHost = sHost + ":" + sPort;
		}
		return sHost + url;
	}

	/**
	 * In html.
	 *
	 * @param i the i
	 * @return the string
	 */
	private String inHTML(int i) {
		if (i == '&')
			return "&amp;";
		else if (i == '<')
			return "&lt;";
		else if (i == '>')
			return "&gt;";
		else if (i == '"')
			return "&quot;";
		else
			return "" + (char) i;
	}

	/**
	 * In html.
	 *
	 * @param st the st
	 * @return the string
	 */
	private String inHTML(String st) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < st.length(); i++) {
			buf.append(inHTML(st.charAt(i)));
		}
		return buf.toString();
	}

	/**
	 * Save remote file.
	 *
	 * @param s_LocalFileName the s_ local file name
	 * @param s_RemoteFileUrl the s_ remote file url
	 * @param s_RealUploadPath the s_ real upload path
	 * @return true, if successful
	 */
	private boolean SaveRemoteFile(String s_LocalFileName, String s_RemoteFileUrl, String s_RealUploadPath) {
		try {
			int httpStatusCode;
			URL url = new URL(s_RemoteFileUrl);
			URLConnection conn = url.openConnection();
			conn.connect();
			HttpURLConnection httpconn = (HttpURLConnection) conn;
			httpStatusCode = httpconn.getResponseCode();
			if (httpStatusCode != HttpURLConnection.HTTP_OK) {
				//file://HttpURLConnection return an error code
				//System.out.println("Connect to "+s_RemoteFileUrl+" failed,return code:"+httpStatusCode);
				return false;
			}
			int filelen = conn.getContentLength();
			InputStream is = conn.getInputStream();
			byte[] tmpbuf = new byte[1024];
			File savefile = new File(s_RealUploadPath + s_LocalFileName);
			if (!savefile.exists())
				savefile.createNewFile();
			FileOutputStream fos = new FileOutputStream(savefile);
			int readnum = 0;
			if (filelen < 0) {
				while (readnum > -1) {
					readnum = is.read(tmpbuf);
					if (readnum > 0) {
						fos.write(tmpbuf, 0, readnum);
					}
				}
			} else {
				int readcount = 0;
				while (readcount < filelen && readnum != -1) {
					readnum = is.read(tmpbuf);
					if (readnum > 0) {
						fos.write(tmpbuf, 0, readnum);
						readcount = readcount + readnum;
					}
				}
				if (readcount < filelen) {
					System.out.println("download error");
					is.close();
					fos.close();
					savefile.delete();
					return false;
				}
			}
			fos.flush();
			fos.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Gets the small image file.
	 *
	 * @param s_File the s_ file
	 * @return the small image file
	 */
	private String getSmallImageFile(String s_File) {
		return s_File.substring(0, s_File.lastIndexOf(".")) + "_s" + s_File.substring(s_File.lastIndexOf("."));
	}

	/**
	 * Checks if is valid sltsy ext.
	 *
	 * @param s_File the s_ file
	 * @return true, if is valid sltsy ext
	 */
	private boolean isValidSLTSYExt(String s_File) {
		String sExt = s_File.substring(s_File.lastIndexOf(".") + 1).toLowerCase();
		String[] aExt = myUtil.split(sSLTSYExt.toLowerCase(), "|");
		for (int i = 0; i < aExt.length; i++) {
			if (aExt[i].equals(sExt)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Make image sy.
	 *
	 * @param s_PathFile the s_ path file
	 * @return true, if successful
	 */
	private boolean makeImageSY(String s_PathFile) {
		if ((nSYWZFlag == 0) && (nSYTPFlag == 0)) {
			return false;
		}
		if (!isValidSLTSYExt(s_PathFile)) {
			return false;
		}

		ImageIcon imgIcon = new ImageIcon(s_PathFile);
		Image theImg = imgIcon.getImage();
		int width = theImg.getWidth(null);
		int height = theImg.getHeight(null);
		int posX, posY;

		BufferedImage bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bimage.createGraphics();
		g.drawImage(theImg, 0, 0, null);
		if (nSYWZFlag == 1) {
			if ((width < nSYWZMinWidth) || (height < nSYWZMinHeight)) {
				return false;
			}
			posX = getSYPosX(nSYWZPosition, width, nSYWZTextWidth + nSYShadowOffset, nSYWZPaddingH);
			posY = getSYPosY(nSYWZPosition, height, nSYWZTextHeight + nSYShadowOffset, nSYWZPaddingV);

			Font wordFont = new Font(sSYFontName, Font.PLAIN, nSYFontSize);
			g.setFont(wordFont);
			FontMetrics fm = g.getFontMetrics();
			int a = fm.getAscent();

			//g.setBackground(Color.white);
			g.setColor(new Color(Integer.parseInt(sSYShadowColor, 16)));
			g.drawString(sSYText, posX + nSYShadowOffset, posY + a + nSYShadowOffset);
			g.setColor(new Color(Integer.parseInt(sSYFontColor, 16)));
			g.drawString(sSYText, posX, posY + a);
		}
		if (nSYTPFlag == 1) {
			if ((width < nSYTPMinWidth) || (height < nSYTPMinHeight)) {
				return false;
			}
			posX = getSYPosX(nSYTPPosition, width, nSYTPImageWidth, nSYTPPaddingH);
			posY = getSYPosY(nSYTPPosition, height, nSYTPImageHeight, nSYTPPaddingV);

			ImageIcon waterIcon = new ImageIcon(sSYPicPath);
			Image waterImg = waterIcon.getImage();
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, nSYTPOpacity));
			g.drawImage(waterImg, posX, posY, null);
			//g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
		}
		g.dispose();
		try {
			File fo = new File(s_PathFile);
			ImageIO.write(bimage, "jpeg", fo);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	/**
	 * Gets the sY pos x.
	 *
	 * @param posFlag the pos flag
	 * @param originalW the original w
	 * @param syW the sy w
	 * @param paddingH the padding h
	 * @return the sY pos x
	 */
	private int getSYPosX(int posFlag, int originalW, int syW, int paddingH) {
		switch (posFlag) {
		case 1:
		case 2:
		case 3:
			return paddingH;
		case 4:
		case 5:
		case 6:
			return (int) Math.floor((originalW - syW) / 2);
		case 7:
		case 8:
		case 9:
			return (originalW - paddingH - syW);
		default:
			return 0;
		}
	}

	/**
	 * Gets the sY pos y.
	 *
	 * @param posFlag the pos flag
	 * @param originalH the original h
	 * @param syH the sy h
	 * @param paddingV the padding v
	 * @return the sY pos y
	 */
	private int getSYPosY(int posFlag, int originalH, int syH, int paddingV) {
		switch (posFlag) {
		case 1:
		case 4:
		case 7:
			return paddingV;
		case 2:
		case 5:
		case 8:
			return (int) Math.floor((originalH - syH) / 2);
		case 3:
		case 6:
		case 9:
			return (originalH - paddingV - syH);
		default:
			return 0;
		}
	}

	/**
	 * Make image slt.
	 *
	 * @param s_Path the s_ path
	 * @param s_File the s_ file
	 * @param s_SmallFile the s_ small file
	 * @return true, if successful
	 */
	private boolean makeImageSLT(String s_Path, String s_File, String s_SmallFile) {
		if (nSLTFlag != 1) {
			return false;
		}
		if (!isValidSLTSYExt(s_Path + s_File)) {
			return false;
		}

		try {
			File fi = new File(s_Path + s_File);
			BufferedImage bis = ImageIO.read(fi);
			int w = bis.getWidth();
			int h = bis.getHeight();

			int nw = 0, nh = 0;

			switch (nSLTCheckFlag) {
			case 0:
				if (w <= nSLTMinSize) {
					return false;
				}
				nw = nSLTOkSize;
				nh = (int) ((double) nSLTOkSize / (double) w * (double) h);
				break;
			case 1:
				if (h <= nSLTMinSize) {
					return false;
				}
				nh = nSLTOkSize;
				nw = (int) ((double) nSLTOkSize / (double) h * (double) w);
				break;
			case 2:
				if (w <= nSLTMinSize && h <= nSLTMinSize) {
					return false;
				}
				if (w > h) {
					nw = nSLTOkSize;
					nh = (int) ((double) nSLTOkSize / (double) w * (double) h);
				} else {
					nh = nSLTOkSize;
					nw = (int) ((double) nSLTOkSize / (double) h * (double) w);
				}
				break;
			}

			File fo = new File(s_Path + s_SmallFile);
			BufferedImage bid = resizeImage(bis, nw, nh);
			ImageIO.write(bid, "jpeg", fo);
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	/**
	 * Resize image.
	 *
	 * @param source the source
	 * @param targetW the target w
	 * @param targetH the target h
	 * @return the buffered image
	 */
	private BufferedImage resizeImage(BufferedImage source, int targetW, int targetH) {
		int type = source.getType();
		BufferedImage target = null;
		if (type == BufferedImage.TYPE_CUSTOM) {
			ColorModel cm = source.getColorModel();
			WritableRaster raster = cm.createCompatibleWritableRaster(targetW, targetH);
			boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			target = new BufferedImage(cm, raster, alphaPremultiplied, null);
		} else
			target = new BufferedImage(targetW, targetH, type);
		Graphics2D g = target.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		double sx = (double) targetW / source.getWidth();
		double sy = (double) targetH / source.getHeight();
		g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
		g.dispose();
		return target;
	}

	/**
	 * Del file.
	 *
	 * @param s_MapFile the s_ map file
	 */
	private void DelFile(String s_MapFile) {
		java.io.File f = new java.io.File(s_MapFile);
		if (f.exists()) {
			f.delete();
		}
	}

	/**
	 * Rename file.
	 *
	 * @param s_File1 the s_ file1
	 * @param s_File2 the s_ file2
	 */
	private void RenameFile(String s_File1, String s_File2) {
		java.io.File f1 = new java.io.File(s_File1);
		java.io.File f2 = new java.io.File(s_File2);
		boolean b = f1.renameTo(f2);
	}

	/**
	 * Checks if is file exists.
	 *
	 * @param s_MapFile the s_ map file
	 * @return true, if successful
	 */
	private boolean IsFileExists(String s_MapFile) {
		java.io.File f = new java.io.File(s_MapFile);
		if (f.exists()) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the domain from url.
	 *
	 * @param s_Url the s_ url
	 * @return the string
	 */
	private String GetDomainFromUrl(String s_Url) {
		String s = s_Url.toLowerCase();
		int n = s.indexOf("://");
		if (n >= 0) {
			s = s.substring(n + 3);
		} else {
			return "";
		}
		n = s.indexOf("/");
		if (n >= 0) {
			s = s.substring(0, n - 1);
		}
		n = s.indexOf(":");
		if (n >= 0) {
			s = s.substring(0, n - 1);
		}
		return s;
	}

	/**
	 * Check space size.
	 *
	 * @return true, if successful
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private boolean CheckSpaceSize() throws IOException {
		if (sSpaceSize.equals("")) {
			return true;
		}

		String s_Dir = "";
		if (sSpacePath.equals("")) {
			s_Dir = sRealUploadPath;
		} else {
			s_Dir = sSpacePath;
		}

		if (GetFolderSize(new File(s_Dir)) >= (Long.parseLong(sSpaceSize) * 1024 * 1024)) {
			OutScript("parent.UploadError('space')");
			return false;
		}
		return true;
	}

	/**
	 * Gets the folder size.
	 *
	 * @param dir the dir
	 * @return the long
	 */
	private long GetFolderSize(File dir) {
		if (dir == null) {
			return 0;
		}
		if (!dir.isDirectory()) {
			return 0;
		}
		long dirSize = 0;
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if (file.isFile()) {
				dirSize += file.length();
			} else if (file.isDirectory()) {
				dirSize += file.length();
				dirSize += GetFolderSize(file);
			}
		}
		return dirSize;
	}

	/**
	 * Checks if is file name format.
	 *
	 * @param s_Name the s_ name
	 * @return true, if successful
	 */
	private boolean IsFileNameFormat(String s_Name) {
		if (s_Name.startsWith(".")) {
			return false;
		}

		Pattern p = Pattern.compile("[\\/\\\\\\:\\*\\?\"\\<\\>\\|\\r\\n\\t]+");
		if (p.matcher(s_Name).matches()) {
			return false;
		}
		return true;
	}

}
