/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons DhtmlxGridBuilder.java 2012-8-3 22:17:27 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx.builder;

import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.rebirth.commons.search.annotation.AbstractSearchProperty;
import cn.com.rebirth.knowledge.commons.dhtmlx.ColumnDataSets;
import cn.com.rebirth.knowledge.commons.dhtmlx.Configuration;
import cn.com.rebirth.knowledge.commons.dhtmlx.ExportGridRequest;
import cn.com.rebirth.knowledge.commons.dhtmlx.GridRequest;
import cn.com.rebirth.knowledge.commons.dhtmlx.GridResponse;
import cn.com.rebirth.knowledge.commons.dhtmlx.GridType;
import cn.com.rebirth.knowledge.commons.dhtmlx.entity.Action;
import cn.com.rebirth.knowledge.commons.dhtmlx.entity.Column;
import cn.com.rebirth.knowledge.commons.dhtmlx.entity.DataProcessor;
import cn.com.rebirth.knowledge.commons.dhtmlx.entity.Grid;
import cn.com.rebirth.knowledge.commons.dhtmlx.entity.Group;
import cn.com.rebirth.knowledge.commons.dhtmlx.entity.OperationColumn;
import cn.com.rebirth.knowledge.commons.dhtmlx.utils.json.JSONEncoder;

/**
 * The Class DhtmlxGridBuilder.
 *
 * @author l.xue.nong
 */
public class DhtmlxGridBuilder implements IGridBuilder {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(DhtmlxGridBuilder.class);

	/** The Constant encoder. */
	private static final JSONEncoder encoder = new JSONEncoder();

	/* (non-Javadoc)
	 * @see cn.com.rebirth.knowledge.commons.dhtmlx.builder.IGridBuilder#build(cn.com.rebirth.knowledge.commons.dhtmlx.GridRequest, cn.com.rebirth.knowledge.commons.dhtmlx.GridResponse, cn.com.rebirth.knowledge.commons.dhtmlx.entity.Grid)
	 */
	public String build(GridRequest request, GridResponse response, Grid grid) {
		String gridVar = grid.getId();
		StringBuilder scripts = new StringBuilder();
		// 建构容器
		buildGridContainer(scripts, grid);

		//开始构建头
		bulidScriptTop(scripts, gridVar, grid);

		//构建内容
		bulidContext(grid, scripts, gridVar, request);

		// 结束建构
		buildScriptAfterEnd(scripts, gridVar, grid);

		String output = scripts.toString();
		logger.debug("dhtmlxgrid build script:\n{}", output);
		return output;
	}

	/**
	 * Builds the script after end.
	 *
	 * @param scripts the scripts
	 * @param gridVar the grid var
	 * @param grid the grid
	 */
	private void buildScriptAfterEnd(StringBuilder scripts, String gridVar, Grid grid) {
		scripts.append("}\n");
		scripts.append("</script>").append("\n");
	}

	/**
	 * Bulid script top.
	 *
	 * @param scripts the scripts
	 * @param gridVar the grid var
	 * @param grid the grid
	 */
	protected void bulidScriptTop(StringBuilder scripts, String gridVar, Grid grid) {
		scripts.append("<script type=\"text/javascript\">").append("\n");
		scripts.append("var ").append(gridVar).append("initEnblaed").append(" = false").append(";\n");
		scripts.append("var ").append(gridVar).append(" = null").append(";\n");
		scripts.append("var ").append(gridVar).append("DataProcessor").append(" = null").append(";\n");
		bulidVar(scripts, grid);
		scripts.append("function initGrid(){\n");
		scripts.append("if(").append(gridVar).append("initEnblaed").append("){return;}\n");
		scripts.append(gridVar).append("initEnblaed").append("=true;\n");
		scripts.append("document.getElementById(\"").append(getGridContainerName(grid))
				.append("\").style.height = (document.body.offsetHeight - 50)").append("+\"px\";\n");
	}

	/**
	 * Bulid var.
	 *
	 * @param scripts the scripts
	 * @param grid the grid
	 */
	protected void bulidVar(StringBuilder scripts, Grid grid) {
		List<Grid> grids = grid.getChildGrid();
		for (Grid sub_grid : grids) {
			scripts.append("var ").append(sub_grid.getId()).append("initEnblaed").append(" = false").append(";\n");
			scripts.append("var ").append(sub_grid.getId()).append(" = null").append(";\n");
			scripts.append("var ").append(sub_grid.getId()).append("DataProcessor").append(" = null").append(";\n");
			List<Grid> s = sub_grid.getChildGrid();
			if (s != null && !s.isEmpty()) {
				buildGridContainer(scripts, sub_grid);
			}
		}
	}

	/**
	 * Bulid context.
	 *
	 * @param grid the grid
	 * @param scripts the scripts
	 * @param gridVar the grid var
	 * @param request the request
	 */
	protected void bulidContext(Grid grid, StringBuilder scripts, String gridVar, GridRequest request) {
		// 开始建构
		buildScriptBegin(scripts, gridVar, grid);
		// 建构基础脚本
		StringBuilder splitAtScript = new StringBuilder();
		List<String> multiHeader = new ArrayList<String>();
		buildMultiHeader(grid.getColumns(), multiHeader, true);
		// 多表头不支持列移动
		grid.setEnableColumnMove(multiHeader.size() == 1);
		StringBuilder[] initSetting = buildBaseSetting(scripts, gridVar, grid, splitAtScript, multiHeader);

		// 建构事件脚本
		buildEvenHandle(scripts, gridVar, grid);
		//构建列的数据源
		buildColumnDataSets(grid, scripts);
		// 添加其他设置
		String requestUrlParams = buildOptionSetting(scripts, gridVar, grid, request, splitAtScript);
		//构建操作的对象
		bulidDataProcessorScript(grid, scripts, request);
		// 建构导出到Excel脚本
		buildExportToExcelScript(scripts, gridVar, grid, request, requestUrlParams, initSetting[11].substring(1)
				.toString(), initSetting[12].substring(1).toString(), initSetting[13].substring(1).toString(),
				initSetting[14].substring(1).toString());

		// 构建操作菜单
		buildOperationMenu(scripts, gridVar, getOperationColumnActions(grid));
		buildScriptEnd(scripts, gridVar, grid);
	}

	/**
	 * Bulid data processor script.
	 *
	 * @param grid the grid
	 * @param scripts the scripts
	 * @param request the request
	 */
	protected void bulidDataProcessorScript(Grid grid, StringBuilder scripts, GridRequest request) {
		DataProcessor dataProcessor = grid.getDataProcessor();
		if (dataProcessor != null) {
			scripts.append(grid.getId()).append("DataProcessor").append("=").append("new dataProcessor(\"")
					.append(request.getContextPath()).append(dataProcessor.getActionHandler()).append("\");\n");
			scripts.append(grid.getId()).append("DataProcessor").append(".enableDataNames(")
					.append(dataProcessor.isEnableDataNames()).append(");\n");
			scripts.append(grid.getId()).append("DataProcessor").append(".setTransactionMode(\"")
					.append(dataProcessor.getTransactionMode()).append("\");\n");
			scripts.append(grid.getId()).append("DataProcessor").append(".setUpdateMode(\"")
					.append(dataProcessor.getUpdateMode()).append("\");\n");
			List<Column> columns = grid.getAllColumns();
			for (int i = 0; i < columns.size(); i++) {
				Column column = columns.get(i);
				AbstractSearchProperty property = column.getProperty();
				if (property != null) {
					Boolean b = property.execute(new AbstractSearchProperty.ElementCallback<Boolean>() {

						@Override
						public Boolean doExecute(AnnotatedElement element) {
							return element.isAnnotationPresent(NotBlank.class)
									|| element.isAnnotationPresent(NotEmpty.class);
						}
					});
					if (b) {
						scripts.append(grid.getId()).append("DataProcessor").append(".setVerificator(").append(i)
								.append(",");
						if (Number.class.isAssignableFrom(property.getRawClass())) {
							scripts.append("greater").append(");\n");
						} else {
							scripts.append("not_empty").append(");\n");
						}
					}
				}
			}
			scripts.append(grid.getId()).append("DataProcessor").append(".init(").append(grid.getId()).append(");\n");
			if (StringUtils.isNotBlank(dataProcessor.getOnAfterUpdate())) {
				scripts.append(grid.getId()).append("DataProcessor").append(".attachEvent(\"").append("onAfterUpdate")
						.append("\",").append(dataProcessor.getOnAfterUpdate()).append(");\n");
			}
			if (StringUtils.isNotBlank(dataProcessor.getOnBeforeUpdate())) {
				scripts.append(grid.getId()).append("DataProcessor").append(".attachEvent(\"").append("onBeforeUpdate")
						.append("\",").append(dataProcessor.getOnBeforeUpdate()).append(");\n");
			}
		}
	}

	/**
	 * Builds the column data sets.
	 *
	 * @param grid the grid
	 * @param scripts the scripts
	 */
	protected void buildColumnDataSets(Grid grid, StringBuilder scripts) {
		List<Column> columns = grid.getAllColumns();
		for (int i = 0; i < columns.size(); i++) {
			Column column = columns.get(i);
			ColumnDataSets columnDataSets = column.getColumnDataSets();
			if (columnDataSets != null) {
				columnDataSets.bulidColumnData(i, column, grid, scripts);
			}
		}
	}

	/**
	 * Builds the grid container.
	 *
	 * @param scripts the scripts
	 * @param grid the grid
	 */
	protected void buildGridContainer(StringBuilder scripts, Grid grid) {
		List<Grid> grids = grid.getChildGrid();
		for (Grid sub_grid : grids) {
			if (sub_grid.getParentGrid() == null) {
				sub_grid.setParentGrid(grid);
			}
			buildGridContainer(scripts, sub_grid);
			List<Grid> s = sub_grid.getChildGrid();
			if (s != null && !s.isEmpty()) {
				buildGridContainer(scripts, sub_grid);
			}
		}
		StringBuilder containerScripts = new StringBuilder();
		containerScripts.append("<div id=\"").append(getGridContainerName(grid)).append("\" style=\"")
				.append(grid.getContainerCssStyle()).append("\"></div>\n");
		StringBuilder pageContainerScripts = new StringBuilder();
		pageContainerScripts.append("<div id=\"PanPage\" style=\"width:100%;\">").append("<div id=\"")
				.append(getGridPageContainerName(grid)).append("\" style=\"").append(grid.getPageSetting().getStyle())
				.append("\"></div>\n").append("</div>\n");
		if (grid.getPageSetting().isEnabled()) {
			if ("bottom".equalsIgnoreCase(grid.getPageSetting().getPosition())
					|| StringUtils.isEmpty(grid.getPageSetting().getPosition())) {
				scripts.append(containerScripts);
				scripts.append(pageContainerScripts);
			} else {
				scripts.append(pageContainerScripts);
				scripts.append(containerScripts);
			}
		} else {
			scripts.append(containerScripts);
		}
	}

	/**
	 * Builds the script begin.
	 *
	 * @param scripts the scripts
	 * @param gridVar the grid var
	 * @param grid the grid
	 */
	private void buildScriptBegin(StringBuilder scripts, String gridVar, Grid grid) {
		String gridContainer = getGridContainerName(grid);
		scripts.append(gridVar).append(" = new dhtmlXGridObject(\"").append(gridContainer).append("\",")
				.append(grid.isAllowCopyData()).append(")").append(";\n");
		scripts.append(gridVar).append(".id=\"").append(gridVar).append("\";\n");
	}

	/**
	 * Builds the script end.
	 *
	 * @param scripts the scripts
	 * @param gridVar the grid var
	 * @param grid the grid
	 */
	private void buildScriptEnd(StringBuilder scripts, String gridVar, Grid grid) {
		scripts.append(gridVar).append(".callEvent(\"onAfterGridScriptLoad\",[").append(gridVar).append("]);\n");
	}

	/**
	 * Builds the event handle script.
	 *
	 * @param gridVar the grid var
	 * @param eventName the event name
	 * @param eventHandler the event handler
	 * @return the string
	 */
	private String buildEventHandleScript(String gridVar, String eventName, String eventHandler) {
		StringBuilder eventHandleScript = new StringBuilder();
		if (StringUtils.isNotEmpty(eventHandler)) {
			eventHandleScript.append(gridVar).append(".attachEvent(\"").append(eventName).append("\",\"")
					.append(eventHandler).append("\");\n");
		}
		return eventHandleScript.toString();
	}

	/**
	 * Builds the base setting.
	 *
	 * @param scripts the scripts
	 * @param gridVar the grid var
	 * @param grid the grid
	 * @param splitAtScript the split at script
	 * @param multiHeader the multi header
	 * @return the string builder[]
	 */
	private StringBuilder[] buildBaseSetting(StringBuilder scripts, String gridVar, Grid grid,
			StringBuilder splitAtScript, List<String> multiHeader) {
		StringBuilder[] initSetting = getInitSetting(grid.getAllColumns(), grid);
		//scripts.append("var label = \"").append(initSetting[0].substring(1)).append("\";\n");
		scripts.append("var label = \"").append(multiHeader.get(0)).append("\";\n");
		scripts.append("var id = \"").append(initSetting[1].substring(1)).append("\";\n");
		scripts.append("var type = \"").append(initSetting[2].substring(1)).append("\";\n");
		if (initSetting[3].substring(1).indexOf("*") >= 0) {
			scripts.append("var width = \"").append(initSetting[3].substring(1)).append("\";\n");
		} else {
			scripts.append("var width = \"")
					.append(grid.isAutoWidth() ? processGridColumnWidths(grid.getAllColumns(),
							StringUtils.split(initSetting[3].substring(1), ","), grid.getColumnWidthUnit())
							: initSetting[3].substring(1)).append("\";\n");
		}
		scripts.append("var align = \"").append(initSetting[4].substring(1)).append("\";\n");
		scripts.append("var format = \"").append(initSetting[5].substring(1)).append("\";\n");
		scripts.append("var hidden = \"").append(initSetting[6].substring(1)).append("\";\n");
		scripts.append("var sort = \"").append(initSetting[7].substring(1)).append("\";\n");
		scripts.append("var keyField = \"").append(getConfigItem(initSetting[8])).append("\";\n");
		scripts.append("var color = \"").append(initSetting[9].substring(1)).append("\";\n");
		scripts.append("var widthUnit = \"").append(grid.getColumnWidthUnit()).append("\";\n");
		scripts.append("var dataType = \"").append(grid.getDataSetting().getType()).append("\";\n");

		//		if (StringUtils.isNotEmpty(initSetting[10].toString())) {
		//			splitAtScript.append(gridVar).append(".splitAt(").append(initSetting[10].substring(1).split(",").length)
		//					.append(");\n");
		//		}

		scripts.append(gridVar).append(".setSkin(\"").append(grid.getSkin()).append("\")").append(";\n");
		if (StringUtils.isNotEmpty(grid.getDataSetting().getFixedQueryCondition())) {
			scripts.append(gridVar).append(".setFixedQueryCondition(\"")
					.append(grid.getDataSetting().getFixedQueryCondition()).append("\");\n");
		}

		// 添加多表头
		for (int i = 1; i < multiHeader.size(); i++) {
			scripts.append(gridVar).append(".attachHeader([").append(multiHeader.get(i)).append("])").append(";\n");
		}

		return initSetting;
	}

	/**
	 * Builds the multi header.
	 *
	 * @param columns the columns
	 * @param result the result
	 * @param topHeader the top header
	 */
	private void buildMultiHeader(List<Object> columns, List<String> result, boolean topHeader) {
		StringBuilder header = new StringBuilder();
		List<Object> nextLevel = new ArrayList<Object>();
		boolean buildNextlevel = false;
		for (Object column : columns) {
			if (Column.class.isInstance(column)) {
				Column columnCast = (Column) column;
				if (null == columnCast.getParent()) {
					header.append(",").append(wrapColumnHeader("#rspan", topHeader));
				} else {
					header.append(",").append(wrapColumnHeader(columnCast.getHeader(), topHeader));
				}
				columnCast.setParent(null);
				nextLevel.add(column);
			} else if (Group.class.isInstance(column)) {
				Group group = (Group) column;
				buildNextlevel = true;
				nextLevel.addAll(group.getColumns());
				header.append(",").append(wrapColumnHeader(group.getHeader(), topHeader));
				for (int i = 1; i < Grid.getChildColumns(group.getColumns()).size(); i++) {
					header.append(",").append(wrapColumnHeader("#cspan", topHeader));
				}
			}
		}
		result.add(header.substring(1));
		if (buildNextlevel) {
			buildMultiHeader(nextLevel, result, false);
		}
	}

	/**
	 * Wrap column header.
	 *
	 * @param header the header
	 * @param topHeader the top header
	 * @return the string
	 */
	private String wrapColumnHeader(String header, boolean topHeader) {
		return topHeader ? header : "\"" + header + "\"";
	}

	/**
	 * Process grid column widths.
	 *
	 * @param columns the columns
	 * @param widths the widths
	 * @param widhtUnit the widht unit
	 * @return the string
	 */
	private String processGridColumnWidths(List<Column> columns, String[] widths, String widhtUnit) {
		if (!("%".equalsIgnoreCase(widhtUnit))) {
			return StringUtils.join(widths, ",");
		}

		int targettotalWidth = 99;
		int totalWidth = 0;
		for (int i = 0; i < widths.length; i++) {
			if ("*".equals(widths[i].trim())) {
				return StringUtils.join(widths, ",");
			}
			if (!columns.get(i).isFrozen()) {
				totalWidth += Integer.parseInt(widths[i]);
			}
		}

		if (totalWidth == targettotalWidth) {
			return StringUtils.join(widths, ",");
		}

		int notZeroColumnIndex = 0;
		int notZeroColumnIndexWidth = 0;
		for (int i = widths.length - 1; i >= 0; i--) {
			if (!columns.get(i).isFrozen()) {
				int currentColumnIndexWidth = Integer.parseInt(widths[i]);
				if (currentColumnIndexWidth + targettotalWidth - totalWidth > 0
						&& currentColumnIndexWidth > notZeroColumnIndexWidth) {
					notZeroColumnIndexWidth = currentColumnIndexWidth;
					notZeroColumnIndex = i;
				}
			}
		}

		if (notZeroColumnIndexWidth + (targettotalWidth - totalWidth) > 0) {
			widths[notZeroColumnIndex] = String.valueOf((notZeroColumnIndexWidth + (targettotalWidth - totalWidth)));
		}

		return StringUtils.join(widths, ",");
	}

	/**
	 * Gets the config item.
	 *
	 * @param configItemValue the config item value
	 * @return the config item
	 */
	private String getConfigItem(StringBuilder configItemValue) {
		if (configItemValue.length() > 0) {
			return configItemValue.substring(1);
		}
		return "";
	}

	/**
	 * Gets the inits the setting.
	 *
	 * @param columns the columns
	 * @param grid the grid
	 * @return the inits the setting
	 */
	private StringBuilder[] getInitSetting(List<Column> columns, Grid grid) {
		StringBuilder[] initSetting = new StringBuilder[15];
		for (int i = 0; i < initSetting.length; i++) {
			initSetting[i] = new StringBuilder();
		}

		for (Column column : columns) {
			fillInitSettingBuffer(initSetting, column, grid);
		}
		return initSetting;
	}

	/**
	 * Fill init setting buffer.
	 *
	 * @param initSetting the init setting
	 * @param column the column
	 * @param grid the grid
	 */
	private void fillInitSettingBuffer(StringBuilder[] initSetting, Column column, Grid grid) {
		initSetting[0].append(",").append(column.getHeader().trim());
		initSetting[1].append(",").append(column.getId());
		initSetting[2].append(",").append(column.getType());
		if ("opt".equalsIgnoreCase(column.getType()) && grid.getOperationColumn().getActions().isEmpty()) {
			initSetting[3].append(",").append(0);
		} else {
			initSetting[3].append(",").append(column.getWidth());
		}

		initSetting[4].append(",").append(column.getAlign());
		initSetting[5].append("^").append(column.getFormat());
		if ("opt".equalsIgnoreCase(column.getType()) && grid.getOperationColumn().getActions().isEmpty()) {
			initSetting[6].append(",").append(true);
		} else {
			initSetting[6].append(",").append(!column.isVisible());
		}

		initSetting[7].append(",");
		if (column.isSortable()) {
			initSetting[7].append(StringUtils.isEmpty(column.getQueryExpression()) ? column.getId() : column
					.getQueryExpression());
		}

		// 获取唯一标识一行数据的关键列
		if (column.isKey()) {
			initSetting[8].append(",").append(column.getId());
		}

		initSetting[9].append(",").append(column.getColor());
		if (column.isFrozen()) {
			initSetting[10].append(",").append(true);
		}
		// 获取需要导出到Excel的列名称（用户不设置表示需要导出）
		if (column.isExportable()) {
			initSetting[11].append(",").append(column.getId());
			initSetting[12].append(",").append(column.getHeader());
			initSetting[13].append(",").append(column.getType());
			initSetting[14].append(",").append(column.getAlign());
		}
	}

	/**
	 * Builds the even handle.
	 *
	 * @param scripts the scripts
	 * @param gridVar the grid var
	 * @param grid the grid
	 */
	private void buildEvenHandle(StringBuilder scripts, String gridVar, Grid grid) {
		scripts.append(buildEventHandleScript(gridVar, "onRowDblClicked", grid.getOnRowDoubleClicked()));
		scripts.append(buildEventHandleScript(gridVar, "onRowSelect", grid.getOnRowClick()));
		scripts.append(buildEventHandleScript(gridVar, "onRightClick", grid.getOnRightClick()));
		scripts.append(buildEventHandleScript(gridVar, "onCheck", grid.getOnCellCheck()));
		scripts.append(buildEventHandleScript(gridVar, "onXLS", grid.getOnBeforeDataLoad()));
		scripts.append(buildEventHandleScript(gridVar, "onXLE", grid.getOnAfterDataLoad()));
		scripts.append(buildEventHandleScript(gridVar, "onValidationError", grid.getOnValidationError()));
		scripts.append(buildEventHandleScript(gridVar, "onValidationCorrect", grid.getOnValidationSucess()));
		scripts.append(buildEventHandleScript(gridVar, "onHeaderClick", grid.getOnHeaderClick()));
		scripts.append(buildEventHandleScript(gridVar, "onBeforeSorting", grid.getOnBeforeSorting()));
		scripts.append(buildEventHandleScript(gridVar, "onAfterSorting", grid.getOnAfterSorting()));
		scripts.append(buildEventHandleScript(gridVar, "onCellChanged", grid.getOnCellChanged()));
		scripts.append(buildEventHandleScript(gridVar, "onEditCell", grid.getOnEditCell()));
		scripts.append(buildEventHandleScript(gridVar, "onRowAdded", grid.getOnRowAdded()));
		scripts.append(buildEventHandleScript(gridVar, "onRowCreated", grid.getOnRowCreated()));
		scripts.append(buildEventHandleScript(gridVar, "onBeforeRowDeleted", grid.getOnBeforeRowDeleted()));
		scripts.append(buildEventHandleScript(gridVar, "onAfterRowDeleted", grid.getOnAfterRowDeleted()));
		if (null != grid.getOperationColumn()
				&& StringUtils.isNotEmpty(grid.getOperationColumn().getOnBeforeMenuRender())) {
			scripts.append(buildEventHandleScript(gridVar, "onBeforeOperationMenuPopup", grid.getOperationColumn()
					.getOnBeforeMenuRender()));
		}
		scripts.append(buildEventHandleScript(gridVar, "onBeforePageChanged", grid.getOnBeforePageChanged()));
		scripts.append(buildEventHandleScript(gridVar, "onPageChanged", grid.getOnAfterPageChanged()));
		scripts.append(buildEventHandleScript(gridVar, "onAfterGridScriptLoad", grid.getOnAfterGridScriptLoad()));
		scripts.append(buildEventHandleScript(gridVar, "onGridScriptLoad", grid.getOnGridScriptLoad()));
	}

	/**
	 * Builds the export to excel script.
	 *
	 * @param scripts the scripts
	 * @param gridVar the grid var
	 * @param grid the grid
	 * @param request the request
	 * @param requestUrlParams the request url params
	 * @param exportIds the export ids
	 * @param exportLabels the export labels
	 * @param exportTypes the export types
	 * @param exportAligns the export aligns
	 */
	protected void buildExportToExcelScript(StringBuilder scripts, String gridVar, Grid grid, GridRequest request,
			String requestUrlParams, String exportIds, String exportLabels, String exportTypes, String exportAligns) {
		scripts.append(gridVar).append(".exportURI=\"").append(request.getContextPath())
				.append(grid.getExport().getActionHandler() == null ? "" : grid.getExport().getActionHandler())
				.append(requestUrlParams).append("\";\n");
		scripts.append(gridVar).append(".exportable=").append(grid.getExport().isEnabled()).append(";\n");
		scripts.append(gridVar).append(".").append(ExportGridRequest.PARAM_DATA_EXPORTER_KEY).append("=\"")
				.append(nullToEmptyString(grid.getExport().getDataExporter())).append("\";\n");
		scripts.append(gridVar).append(".").append(ExportGridRequest.PARAM_ROW_CONVERTER_KEY).append("=\"")
				.append(nullToEmptyString(grid.getExport().getRowConverter())).append("\";\n");
		scripts.append(gridVar).append(".").append(ExportGridRequest.PARAM_EXPORT_COLUMN_IDS_KEY).append("=\"")
				.append(nullToEmptyString(exportIds)).append("\";\n");
		scripts.append(gridVar).append(".").append(ExportGridRequest.PARAM_EXPORT_COLUMN_TYPES_KEY).append("=\"")
				.append(nullToEmptyString(exportTypes)).append("\";\n");
		scripts.append(gridVar).append(".").append(ExportGridRequest.PARAM_EXPORT_COLUMN_HEADERS_KEY).append("=\"")
				.append(nullToEmptyString(exportLabels)).append("\";\n");
		scripts.append(gridVar).append(".").append(ExportGridRequest.PARAM_FILE_NAME_KEY).append("=\"")
				.append(nullToEmptyString(grid.getExport().getFileName())).append("\";\n");
		scripts.append(gridVar).append(".").append(ExportGridRequest.PARAM_EXPORT_COLUMN_ALIGNS_KEY).append("=\"")
				.append(nullToEmptyString(exportAligns)).append("\";\n");
		scripts.append(gridVar).append(".").append(ExportGridRequest.PARAM_TEMPLATE_FILE_NAME_KEY).append("=\"")
				.append(nullToEmptyString(grid.getExport().getTemplateFileName())).append("\";\n");
	}

	/**
	 * Null to empty string.
	 *
	 * @param paramString the param string
	 * @return the string
	 */
	private String nullToEmptyString(String paramString) {
		return StringUtils.isEmpty(paramString) ? "" : paramString;
	}

	/**
	 * Builds the option setting.
	 *
	 * @param scripts the scripts
	 * @param gridVar the grid var
	 * @param grid the grid
	 * @param request the request
	 * @param splitAtScript the split at script
	 * @return the string
	 */
	protected String buildOptionSetting(StringBuilder scripts, String gridVar, Grid grid, GridRequest request,
			StringBuilder splitAtScript) {
		//没有定义分页模型代表不需要分页
		if (grid.getPageSetting().isEnabled()) {
			int pageSize = grid.getPageSetting().getSize();
			pageSize = pageSize == 0 ? Configuration.getInstance().getPageSetting().getSize() : pageSize;
			scripts.append(gridVar).append(".enablePaging(true,").append(pageSize).append(",null,\"")
					.append(getGridPageContainerName(grid)).append("\");\n");
			scripts.append(gridVar).append(".setPagingSkin(\"").append("pgToolbar").append("\",\"pagerstyle\")")
					.append(";\n");
			request.addParameter("pageNo", "0");
			request.addParameter("pageSize", pageSize + "");
		}

		if (grid.isAutoHeight()) {
			scripts.append(gridVar).append(".enableAutoHeight(true, null, null,").append(grid.getMinHeight())
					.append(");\n");
		}

		if (grid.isEnableColumnMove()) {
			scripts.append(gridVar).append(".enableColumnMove(true);\n");
		}

		if (grid.isEnableAutoSizeSaving()) {
			scripts.append(gridVar).append(".enableAutoSizeSaving();\n");
		}

		if (grid.isEnableOrderSaving()) {
			scripts.append(gridVar).append(".enableOrderSaving();\n");
		}

		if (grid.isEnableUndoRedo()) {
			scripts.append(gridVar).append(".enableUndoRedo();\n");
		}

		if (grid.isEnableAutoHiddenColumnsSaving()) {
			scripts.append(gridVar).append(".enableAutoHiddenColumnsSaving();\n");
		}
		if (!grid.isAllowCopyData() && GridType.dhtmlxGrid.equals(grid.getGridType())) {
			scripts.append(gridVar).append(".enableBlockSelection()").append(";\n");
			scripts.append(gridVar).append(".attachEvent(\"onKeyPress\",").append(gridVar).append(".doOnCtrlCPressed)")
					.append(";\n");
		}
		if (GridType.dhtmlxTreeGrid.equals(grid.getGridType())) {
			scripts.append(gridVar).append(".enableDragAndDrop(true);\n");
			scripts.append(gridVar).append(".enableMultiselect(true);\n");
		}
		scripts.append(splitAtScript);
		String requestUrlParams = request.buildParamsAsQueryString();
		if (GridType.dhtmlxTreeGrid.equals(grid.getGridType())) {
			scripts.append(gridVar).append(".kidsXmlFile = ").append("\"").append(request.getContextPath())
					.append(grid.getDataSetting().getActionHandler()).append("\";\n");
		} else {
			scripts.append(gridVar).append(".xmlFileUrl = ").append("\"").append(request.getContextPath())
					.append(grid.getDataSetting().getActionHandler()).append("\";\n");
		}
		scripts.append(gridVar)
				.append(".initSetting({label:label,id:id,type:type,align:align,sort:sort,width:width,hidden:hidden,format:format,keyField:keyField,color:color,widthUnit:widthUnit,dataType:dataType});\n");
		if (grid.isLoadOnFirst()) {
			boolean isLoadJson = false;
			if ("xml".equalsIgnoreCase(grid.getDataSetting().getType())) {
				scripts.append(gridVar).append(".loadXML(\"").append(request.getContextPath())
						.append(grid.getDataSetting().getActionHandler()).append(requestUrlParams).append("\"")
						.append(",function(){\n");
			} else {
				scripts.append(gridVar).append(".load(\"").append(request.getContextPath())
						.append(grid.getDataSetting().getActionHandler()).append(requestUrlParams).append("\"")
						.append(",function(){\n");
				isLoadJson = true;
			}
			scripts.append(gridVar).append(".enableHeaderMenu();\n");
			//scripts.append(gridVar).append(".selectRow(0, true);\n");
			if (grid.isEnableAutoSizeSaving()) {
				scripts.append(gridVar).append(".loadSizeFromCookie();\n");
			}
			if (grid.isEnableOrderSaving()) {
				scripts.append(gridVar).append(".loadOrderFromCookie();\n");
			}
			if (grid.getAllColumns() != null) {
				for (int i = 0; i < grid.getAllColumns().size(); i++) {
					Column column = grid.getAllColumns().get(i);
					if (column.isGroup()) {
						scripts.append(gridVar).append(".groupBy(" + i + ");\n");
					}
				}
			}
			//childGrid
			bulidChildGrid(grid, scripts, request);
			/*
			if (grid.isEnableAutoHiddenColumnsSaving()) {
				scripts.append(gridVar).append(".loadHiddenColumnsFromCookie();\n");
			}*/
			scripts.append("}");
			if (isLoadJson)
				scripts.append(",").append("\"").append("json").append("\"");
			scripts.append(");\n");
		}

		return requestUrlParams;
	}

	/**
	 * Bulid child grid.
	 *
	 * @param grid the grid
	 * @param scripts the scripts
	 * @param request the request
	 */
	protected void bulidChildGrid(Grid grid, StringBuilder scripts, GridRequest request) {
		List<Grid> childGrid = grid.getChildGrid();
		for (Grid sub_grid : childGrid) {
			bulidContext(sub_grid, scripts, sub_grid.getId(), request);
			List<Grid> c = sub_grid.getChildGrid();
			if (c != null && !c.isEmpty()) {
				bulidChildGrid(sub_grid, scripts, request);
			}
		}
	}

	/**
	 * Builds the operation menu.
	 *
	 * @param scripts the scripts
	 * @param gridVar the grid var
	 * @param actions the actions
	 */
	protected void buildOperationMenu(StringBuilder scripts, String gridVar, List<Action> actions) {
		if (!actions.isEmpty()) {
			String menuContainerId = gridVar + "_opt_menu";
			// 构建上下文菜单
			scripts.append("if(typeof initOptMenu=='function'){\n");
			scripts.append(gridVar).append(".optMenuContainerId = '").append(menuContainerId).append("';\n");
			scripts.append(gridVar).append(".menuItems = ").append(encoder.encode(actions)).append(";\n");
			scripts.append("  initOptMenu(").append(gridVar).append(",'").append(menuContainerId).append("','")
					.append("dg_tools_menu").append("',").append(gridVar).append(".menuItems);\n");
			scripts.append("}\n");
		}
	}

	/**
	 * Gets the operation column actions.
	 *
	 * @param grid the grid
	 * @return the operation column actions
	 */
	@SuppressWarnings("unchecked")
	private List<Action> getOperationColumnActions(Grid grid) {
		OperationColumn operationColumn = grid.getOperationColumn();
		if (null != operationColumn) {
			return operationColumn.getActions();
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * Gets the grid container name.
	 *
	 * @param grid the grid
	 * @return the grid container name
	 */
	private String getGridContainerName(Grid grid) {
		return grid.getId() + "Container";
	}

	/**
	 * Gets the grid page container name.
	 *
	 * @param grid the grid
	 * @return the grid page container name
	 */
	private String getGridPageContainerName(Grid grid) {
		return grid.getId() + "PageContainer";
	}
}