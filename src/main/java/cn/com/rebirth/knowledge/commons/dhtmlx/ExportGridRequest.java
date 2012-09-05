/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons ExportGridRequest.java 2012-8-3 21:35:45 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx;

/**
 * The Class ExportGridRequest.
 *
 * @author l.xue.nong
 */
public class ExportGridRequest extends GridRequest {

	/** The Constant PARAM_FILE_NAME_KEY. */
	public static final String PARAM_FILE_NAME_KEY = "fileName";

	/** The Constant PARAM_DATA_EXPORTER_KEY. */
	public static final String PARAM_DATA_EXPORTER_KEY = "dataExporter";

	/** The Constant PARAM_ROW_CONVERTER_KEY. */
	public static final String PARAM_ROW_CONVERTER_KEY = "rowConverter";

	/** The Constant PARAM_EXPORT_COLUMN_IDS_KEY. */
	public static final String PARAM_EXPORT_COLUMN_IDS_KEY = "exportColumnIds";

	/** The Constant PARAM_EXPORT_COLUMN_HEADERS_KEY. */
	public static final String PARAM_EXPORT_COLUMN_HEADERS_KEY = "exportColumnHeaders";

	/** The Constant PARAM_EXPORT_COLUMN_ALIGNS_KEY. */
	public static final String PARAM_EXPORT_COLUMN_ALIGNS_KEY = "exportColumnAligns";

	/** The Constant PARAM_EXPORT_COLUMN_TYPES_KEY. */
	public static final String PARAM_EXPORT_COLUMN_TYPES_KEY = "exportColumnTypes";

	/** The Constant PARAM_TEMPLATE_FILE_NAME_KEY. */
	public static final String PARAM_TEMPLATE_FILE_NAME_KEY = "templateFileName";

	/** The file name. */
	private String fileName = "gridExport.xls";

	/** The data exporter. */
	private String dataExporter;

	/** The row converter. */
	private String rowConverter;

	/** The template file name. */
	private String templateFileName;

	/** The export column ids. */
	private String exportColumnIds;

	/** The export column types. */
	private String exportColumnTypes;

	/** The export column aligns. */
	private String exportColumnAligns;

	/** The export column headers. */
	private String exportColumnHeaders;

	/**
	 * Instantiates a new export grid request.
	 *
	 * @param request the request
	 */
	public ExportGridRequest(GridRequest request) {
		this.setPostParameters(request.getPostParameters());
		this.setParameters(request.getParameters());
		this.setHttpRequest(request.getHttpRequest());
		this.setSqlId(request.getSqlId());
		this.setCountSqlId(request.getCountSqlId());
		this.setOtherParameters();
	}

	/**
	 * Sets the other parameters.
	 */
	private void setOtherParameters() {
		readRequredParam(PARAM_FILE_NAME_KEY, this, String.class, "gridExport.xls", false);
		readRequredParam(PARAM_DATA_EXPORTER_KEY, this, String.class, null, false);
		readRequredParam(PARAM_ROW_CONVERTER_KEY, this, String.class, "basicRowConverter", false);
		readRequredParam(PARAM_EXPORT_COLUMN_IDS_KEY, this, String.class, null, true);
		readRequredParam(PARAM_EXPORT_COLUMN_TYPES_KEY, this, String.class, null, true);
		readRequredParam(PARAM_EXPORT_COLUMN_HEADERS_KEY, this, String.class, null, true);
		readRequredParam(PARAM_TEMPLATE_FILE_NAME_KEY, this, String.class, null, false);
		readRequredParam(PARAM_EXPORT_COLUMN_ALIGNS_KEY, this, String.class, "center", false);
	}

	/**
	 * Gets the export column ids.
	 *
	 * @return the export column ids
	 */
	public String getExportColumnIds() {
		return exportColumnIds;
	}

	/**
	 * Sets the export column ids.
	 *
	 * @param exportColumnIds the new export column ids
	 */
	public void setExportColumnIds(String exportColumnIds) {
		this.exportColumnIds = exportColumnIds;
	}

	/**
	 * Gets the export column headers.
	 *
	 * @return the export column headers
	 */
	public String getExportColumnHeaders() {
		return exportColumnHeaders;
	}

	/**
	 * Sets the export column headers.
	 *
	 * @param exportColumnHeaders the new export column headers
	 */
	public void setExportColumnHeaders(String exportColumnHeaders) {
		this.exportColumnHeaders = exportColumnHeaders;
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
	 * Gets the export column types.
	 *
	 * @return the export column types
	 */
	public String getExportColumnTypes() {
		return exportColumnTypes;
	}

	/**
	 * Sets the export column types.
	 *
	 * @param exportColumnTypes the new export column types
	 */
	public void setExportColumnTypes(String exportColumnTypes) {
		this.exportColumnTypes = exportColumnTypes;
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
	 * Gets the export column aligns.
	 *
	 * @return the export column aligns
	 */
	public String getExportColumnAligns() {
		return exportColumnAligns;
	}

	/**
	 * Sets the export column aligns.
	 *
	 * @param exportColumnAligns the new export column aligns
	 */
	public void setExportColumnAligns(String exportColumnAligns) {
		this.exportColumnAligns = exportColumnAligns;
	}

}