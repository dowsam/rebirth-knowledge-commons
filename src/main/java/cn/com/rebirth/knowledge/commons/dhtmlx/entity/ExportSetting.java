/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons ExportSetting.java 2012-8-4 10:43:51 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx.entity;

import org.apache.commons.lang3.StringUtils;

/**
 * The Class ExportSetting.
 *
 * @author l.xue.nong
 */
public class ExportSetting {

	/** The enabled. */
	private boolean enabled = true;

	/** The file name. */
	private String fileName;

	/** The template file name. */
	private String templateFileName;

	/** The row converter. */
	private String rowConverter;

	/** The data exporter. */
	private String dataExporter;

	/** The action handler. */
	private String actionHandler;

	/**
	 * Checks if is enabled.
	 *
	 * @return true, if is enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Sets the enabled.
	 *
	 * @param enabled the new enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the file name.
	 *
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Gets the template file name.
	 *
	 * @return the template file name
	 */
	public String getTemplateFileName() {
		return templateFileName;
	}

	/**
	 * Sets the template file name.
	 *
	 * @param templateFileName the new template file name
	 */
	public void setTemplateFileName(String templateFileName) {
		this.templateFileName = templateFileName;
		if (StringUtils.isNotEmpty(this.templateFileName)) {
			this.setDataExporter("templateExcelExporter");
		}
	}

	/**
	 * Gets the row converter.
	 *
	 * @return the row converter
	 */
	public String getRowConverter() {
		return rowConverter;
	}

	/**
	 * Sets the row converter.
	 *
	 * @param rowConverter the new row converter
	 */
	public void setRowConverter(String rowConverter) {
		this.rowConverter = rowConverter;
	}

	/**
	 * Gets the data exporter.
	 *
	 * @return the data exporter
	 */
	public String getDataExporter() {
		return dataExporter;
	}

	/**
	 * Sets the data exporter.
	 *
	 * @param dataExporter the new data exporter
	 */
	public void setDataExporter(String dataExporter) {
		this.dataExporter = dataExporter;
	}

	/**
	 * Gets the action handler.
	 *
	 * @return the action handler
	 */
	public String getActionHandler() {
		return actionHandler;
	}

	/**
	 * Sets the action handler.
	 *
	 * @param actionHandler the new action handler
	 */
	public void setActionHandler(String actionHandler) {
		this.actionHandler = actionHandler;
	}

}