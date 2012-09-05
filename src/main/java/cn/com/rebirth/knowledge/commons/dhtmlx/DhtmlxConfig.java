/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons DhtmlxConfig.java 2012-8-3 21:35:45 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx;

import java.io.IOException;

/**
 * The Interface DhtmlxConfig.
 *
 * @author l.xue.nong
 */
public interface DhtmlxConfig {

	/** The Constant IMAGEPATH. */
	public static final String IMAGEPATH = "${base}/js/dhtmlx/imgs/";

	/** The Constant SKIN. */
	public static final String SKIN = "${skin}";

	/** The Constant DEFAULT_SKIN. */
	public static final String DEFAULT_SKIN = "xp";

	/** The Constant FIELDNAME. */
	public static final String FIELDNAME = "_field_name_";

	/**
	 * Gets the image path.
	 *
	 * @return the image path
	 */
	String getImagePath();

	/**
	 * Gets the skin.
	 *
	 * @return the skin
	 */
	String getSkin();

	/**
	 * Gets the header.
	 *
	 * @return the header
	 */
	String getHeader();

	/**
	 * Gets the inits the widths.
	 *
	 * @return the inits the widths
	 */
	String getInitWidths();

	/**
	 * Gets the column ids.
	 *
	 * @return the column ids
	 */
	String getColumnIds();

	/**
	 * Gets the col align.
	 *
	 * @return the col align
	 */
	String getColAlign();

	/**
	 * Gets the col types.
	 *
	 * @return the col types
	 */
	String getColTypes();

	/**
	 * Gets the col sorting.
	 *
	 * @return the col sorting
	 */
	String getColSorting();

	/**
	 * Gets the columns visibility.
	 *
	 * @return the columns visibility
	 */
	String getColumnsVisibility();

	/**
	 * Writer.
	 *
	 * @param templateName the template name
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	String writer(String templateName) throws IOException;
}
