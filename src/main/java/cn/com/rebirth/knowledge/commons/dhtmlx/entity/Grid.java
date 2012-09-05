/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons Grid.java 2012-8-4 10:59:21 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cn.com.rebirth.knowledge.commons.dhtmlx.GridType;

import com.google.common.collect.Lists;

/**
 * The Class Grid.
 *
 * @author l.xue.nong
 */
public class Grid {
	/** The id. */
	private String id;

	/** The container. */
	private String container;

	/** The load on first. */
	private boolean loadOnFirst = true;

	/** The allow copy data. */
	private boolean allowCopyData = true;

	/** The column width unit. */
	private String columnWidthUnit = "%";

	/** The data setting. */
	private DataSetting dataSetting;

	/** The page setting. */
	private PageSetting pageSetting;

	/** The data processor. */
	private DataProcessor dataProcessor;

	/** The footer express. */
	private String footerExpress;

	/** The show header. */
	private boolean showHeader = true;

	/** The auto height. */
	private boolean autoHeight = true;

	/** The auto width. */
	private boolean autoWidth = true;

	/** The min height. */
	private int minHeight = 150;

	/** The export. */
	private ExportSetting export;

	/** The skin. */
	private String skin = "xp";

	/** The grid type. */
	private GridType gridType = GridType.dhtmlxGrid;

	/** The check all column. */
	private CheckAllColumn checkAllColumn;

	/** The operation column. */
	private OperationColumn operationColumn;

	/** The columns. */
	private List<Object> columns = new ArrayList<Object>();

	/** The child grid. */
	private List<Grid> childGrid = Lists.newArrayList();
	
	/** The parent grid. */
	private Object parentGrid;

	/** The enable undo redo. */
	private boolean enableUndoRedo = true;

	/** The select first row after load. */
	private boolean selectFirstRowAfterLoad = true;

	/** The enable auto size saving. */
	private boolean enableAutoSizeSaving = false;

	/** The enable order saving. */
	private boolean enableOrderSaving = false;

	/** The enable auto hidden columns saving. */
	private boolean enableAutoHiddenColumnsSaving = false;

	/** The enable column move. */
	private boolean enableColumnMove = true;

	/** The container css style. */
	private String containerCssStyle = "height:290px;  width:99.5%; background-color:white;";
	/** The on edit cell. */
	private String onEditCell;

	/** The on row double clicked. */
	private String onRowDoubleClicked;

	/** The on cell changed. */
	private String onCellChanged;

	/** The on cell check. */
	private String onCellCheck;

	/** The on row added. */
	private String onRowAdded;

	/** The on row created. */
	private String onRowCreated;

	/** The on before row deleted. */
	private String onBeforeRowDeleted;

	/** The on after row deleted. */
	private String onAfterRowDeleted;

	/** The on before data load. */
	private String onBeforeDataLoad;

	/** The on after data load. */
	private String onAfterDataLoad;

	/** The on row click. */
	private String onRowClick;

	/** The on right click. */
	private String onRightClick;

	/** The on validation error. */
	private String onValidationError;

	/** The on validation sucess. */
	private String onValidationSucess;

	/** The on header click. */
	private String onHeaderClick;

	/** The on before sorting. */
	private String onBeforeSorting;

	/** The on after sorting. */
	private String onAfterSorting;

	/** The on before page changed. */
	private String onBeforePageChanged;

	/** The on after page changed. */
	private String onAfterPageChanged;

	/** The on after grid script load. */
	private String onAfterGridScriptLoad;

	/** The on grid script load. */
	private String onGridScriptLoad;

	/**
	 * Gets the on grid script load.
	 *
	 * @return the on grid script load
	 */
	public String getOnGridScriptLoad() {
		return onGridScriptLoad;
	}

	/**
	 * Sets the on grid script load.
	 *
	 * @param onGridScriptLoad the new on grid script load
	 */
	public void setOnGridScriptLoad(String onGridScriptLoad) {
		this.onGridScriptLoad = onGridScriptLoad;
	}

	/**
	 * Gets the on after grid script load.
	 *
	 * @return the on after grid script load
	 */
	public String getOnAfterGridScriptLoad() {
		return onAfterGridScriptLoad;
	}

	/**
	 * Sets the on after grid script load.
	 *
	 * @param onAfterGridScriptLoad the new on after grid script load
	 */
	public void setOnAfterGridScriptLoad(String onAfterGridScriptLoad) {
		this.onAfterGridScriptLoad = onAfterGridScriptLoad;
	}

	/**
	 * Gets the on before page changed.
	 *
	 * @return the on before page changed
	 */
	public String getOnBeforePageChanged() {
		return onBeforePageChanged;
	}

	/**
	 * Sets the on before page changed.
	 *
	 * @param onBeforePageChanged the new on before page changed
	 */
	public void setOnBeforePageChanged(String onBeforePageChanged) {
		this.onBeforePageChanged = onBeforePageChanged;
	}

	/**
	 * Gets the on after page changed.
	 *
	 * @return the on after page changed
	 */
	public String getOnAfterPageChanged() {
		return onAfterPageChanged;
	}

	/**
	 * Sets the on after page changed.
	 *
	 * @param onAfterPageChanged the new on after page changed
	 */
	public void setOnAfterPageChanged(String onAfterPageChanged) {
		this.onAfterPageChanged = onAfterPageChanged;
	}

	/**
	 * Checks if is enable auto size saving.
	 *
	 * @return true, if is enable auto size saving
	 */
	public boolean isEnableAutoSizeSaving() {
		return enableAutoSizeSaving;
	}

	/**
	 * Sets the enable auto size saving.
	 *
	 * @param enableAutoSizeSaving the new enable auto size saving
	 */
	public void setEnableAutoSizeSaving(boolean enableAutoSizeSaving) {
		this.enableAutoSizeSaving = enableAutoSizeSaving;
	}

	/**
	 * Checks if is enable order saving.
	 *
	 * @return true, if is enable order saving
	 */
	public boolean isEnableOrderSaving() {
		return enableOrderSaving;
	}

	/**
	 * Checks if is enable column move.
	 *
	 * @return true, if is enable column move
	 */
	public boolean isEnableColumnMove() {
		return enableColumnMove;
	}

	/**
	 * Sets the enable column move.
	 *
	 * @param enableColumnMove the new enable column move
	 */
	public void setEnableColumnMove(boolean enableColumnMove) {
		this.enableColumnMove = enableColumnMove;
	}

	/**
	 * Gets the check all column.
	 *
	 * @return the check all column
	 */
	public CheckAllColumn getCheckAllColumn() {
		return checkAllColumn;
	}

	/**
	 * Sets the check all column.
	 *
	 * @param checkAllColumn the new check all column
	 */
	public void setCheckAllColumn(CheckAllColumn checkAllColumn) {
		this.checkAllColumn = checkAllColumn;
	}

	/**
	 * Checks if is allow copy data.
	 *
	 * @return true, if is allow copy data
	 */
	public boolean isAllowCopyData() {
		return allowCopyData;
	}

	/**
	 * Sets the allow copy data.
	 *
	 * @param allowCopyData the new allow copy data
	 */
	public void setAllowCopyData(boolean allowCopyData) {
		this.allowCopyData = allowCopyData;
	}

	/**
	 * Gets the operation column.
	 *
	 * @return the operation column
	 */
	public OperationColumn getOperationColumn() {
		return operationColumn;
	}

	/**
	 * Sets the operation column.
	 *
	 * @param operationColumn the new operation column
	 */
	public void setOperationColumn(OperationColumn operationColumn) {
		this.operationColumn = operationColumn;
	}

	/**
	 * Checks if is auto width.
	 *
	 * @return true, if is auto width
	 */
	public boolean isAutoWidth() {
		return autoWidth;
	}

	/**
	 * Sets the auto width.
	 *
	 * @param autoWidth the new auto width
	 */
	public void setAutoWidth(boolean autoWidth) {
		this.autoWidth = autoWidth;
	}

	/**
	 * Sets the enable order saving.
	 *
	 * @param enableOrderSaving the new enable order saving
	 */
	public void setEnableOrderSaving(boolean enableOrderSaving) {
		this.enableOrderSaving = enableOrderSaving;
	}

	/**
	 * Checks if is enable auto hidden columns saving.
	 *
	 * @return true, if is enable auto hidden columns saving
	 */
	public boolean isEnableAutoHiddenColumnsSaving() {
		return enableAutoHiddenColumnsSaving;
	}

	/**
	 * Sets the enable auto hidden columns saving.
	 *
	 * @param enableAutoHiddenColumnsSaving the new enable auto hidden columns saving
	 */
	public void setEnableAutoHiddenColumnsSaving(boolean enableAutoHiddenColumnsSaving) {
		this.enableAutoHiddenColumnsSaving = enableAutoHiddenColumnsSaving;
	}

	/**
	 * Gets the grid type.
	 *
	 * @return the grid type
	 */
	public GridType getGridType() {
		return gridType;
	}

	/**
	 * Sets the grid type.
	 *
	 * @param gridType the new grid type
	 */
	public void setGridType(GridType gridType) {
		this.gridType = gridType;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Gets the container css style.
	 *
	 * @return the container css style
	 */
	public String getContainerCssStyle() {
		return containerCssStyle;
	}

	/**
	 * Sets the container css style.
	 *
	 * @param containerCssStyle the new container css style
	 */
	public void setContainerCssStyle(String containerCssStyle) {
		this.containerCssStyle = containerCssStyle;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the container.
	 *
	 * @return the container
	 */
	public String getContainer() {
		return container;
	}

	/**
	 * Sets the container.
	 *
	 * @param container the new container
	 */
	public void setContainer(String container) {
		this.container = container;
	}

	/**
	 * Checks if is load on first.
	 *
	 * @return true, if is load on first
	 */
	public boolean isLoadOnFirst() {
		return loadOnFirst;
	}

	/**
	 * Sets the load on first.
	 *
	 * @param loadOnFirst the new load on first
	 */
	public void setLoadOnFirst(boolean loadOnFirst) {
		this.loadOnFirst = loadOnFirst;
	}

	/**
	 * Gets the column width unit.
	 *
	 * @return the column width unit
	 */
	public String getColumnWidthUnit() {
		return columnWidthUnit;
	}

	/**
	 * Sets the column width unit.
	 *
	 * @param columnWidthUnit the new column width unit
	 */
	public void setColumnWidthUnit(String columnWidthUnit) {
		this.columnWidthUnit = columnWidthUnit;
	}

	/**
	 * Gets the page setting.
	 *
	 * @return the page setting
	 */
	public PageSetting getPageSetting() {
		return pageSetting;
	}

	/**
	 * Sets the page setting.
	 *
	 * @param pageSetting the new page setting
	 */
	public void setPageSetting(PageSetting pageSetting) {
		this.pageSetting = pageSetting;
	}

	/**
	 * Gets the footer express.
	 *
	 * @return the footer express
	 */
	public String getFooterExpress() {
		return footerExpress;
	}

	/**
	 * Sets the footer express.
	 *
	 * @param footerExpress the new footer express
	 */
	public void setFooterExpress(String footerExpress) {
		this.footerExpress = footerExpress;
	}

	/**
	 * Checks if is show header.
	 *
	 * @return true, if is show header
	 */
	public boolean isShowHeader() {
		return showHeader;
	}

	/**
	 * Sets the show header.
	 *
	 * @param showHeader the new show header
	 */
	public void setShowHeader(boolean showHeader) {
		this.showHeader = showHeader;
	}

	/**
	 * Checks if is auto height.
	 *
	 * @return true, if is auto height
	 */
	public boolean isAutoHeight() {
		return autoHeight;
	}

	/**
	 * Sets the auto height.
	 *
	 * @param autoHeight the new auto height
	 */
	public void setAutoHeight(boolean autoHeight) {
		this.autoHeight = autoHeight;
	}

	/**
	 * Gets the min height.
	 *
	 * @return the min height
	 */
	public int getMinHeight() {
		return minHeight;
	}

	/**
	 * Sets the min height.
	 *
	 * @param minHeight the new min height
	 */
	public void setMinHeight(int minHeight) {
		this.minHeight = minHeight;
	}

	/**
	 * Gets the export.
	 *
	 * @return the export
	 */
	public ExportSetting getExport() {
		return export;
	}

	/**
	 * Sets the export.
	 *
	 * @param export the new export
	 */
	public void setExport(ExportSetting export) {
		this.export = export;
	}

	/**
	 * Gets the skin.
	 *
	 * @return the skin
	 */
	public String getSkin() {
		return skin;
	}

	/**
	 * Sets the skin.
	 *
	 * @param skin the new skin
	 */
	public void setSkin(String skin) {
		this.skin = skin;
	}

	/**
	 * Gets the on row double clicked.
	 *
	 * @return the on row double clicked
	 */
	public String getOnRowDoubleClicked() {
		return onRowDoubleClicked;
	}

	/**
	 * Sets the on row double clicked.
	 *
	 * @param onRowDoubleClicked the new on row double clicked
	 */
	public void setOnRowDoubleClicked(String onRowDoubleClicked) {
		this.onRowDoubleClicked = onRowDoubleClicked;
	}

	/**
	 * Gets the columns.
	 *
	 * @return the columns
	 */
	public List<Object> getColumns() {
		return columns;
	}

	/**
	 * Gets the all columns.
	 *
	 * @return the all columns
	 */
	public List<Column> getAllColumns() {
		return getChildColumns(this.getColumns());
	}

	/**
	 * Gets the child columns.
	 *
	 * @param columnList the column list
	 * @return the child columns
	 */
	public static List<Column> getChildColumns(List<Object> columnList) {
		List<Column> columns = new LinkedList<Column>();
		for (Object column : columnList) {
			if (Column.class.isInstance(column)) {
				columns.add((Column) column);
			} else if (Group.class.isInstance(column)) {
				columns.addAll(getChildColumns(((Group) column).getColumns()));
			}
		}
		return columns;
	}

	/**
	 * Sets the columns.
	 *
	 * @param columns the new columns
	 */
	public void setColumns(List<Object> columns) {
		this.columns = columns;
	}

	/**
	 * Gets the on row click.
	 *
	 * @return the on row click
	 */
	public String getOnRowClick() {
		return onRowClick;
	}

	/**
	 * Sets the on row click.
	 *
	 * @param onRowClick the new on row click
	 */
	public void setOnRowClick(String onRowClick) {
		this.onRowClick = onRowClick;
	}

	/**
	 * Gets the on right click.
	 *
	 * @return the on right click
	 */
	public String getOnRightClick() {
		return onRightClick;
	}

	/**
	 * Sets the on right click.
	 *
	 * @param onRightClick the new on right click
	 */
	public void setOnRightClick(String onRightClick) {
		this.onRightClick = onRightClick;
	}

	/**
	 * Gets the on cell check.
	 *
	 * @return the on cell check
	 */
	public String getOnCellCheck() {
		return onCellCheck;
	}

	/**
	 * Sets the on cell check.
	 *
	 * @param onCellCheck the new on cell check
	 */
	public void setOnCellCheck(String onCellCheck) {
		this.onCellCheck = onCellCheck;
	}

	/**
	 * Gets the on before data load.
	 *
	 * @return the on before data load
	 */
	public String getOnBeforeDataLoad() {
		return onBeforeDataLoad;
	}

	/**
	 * Sets the on before data load.
	 *
	 * @param onBeforeDataLoad the new on before data load
	 */
	public void setOnBeforeDataLoad(String onBeforeDataLoad) {
		this.onBeforeDataLoad = onBeforeDataLoad;
	}

	/**
	 * Gets the on after data load.
	 *
	 * @return the on after data load
	 */
	public String getOnAfterDataLoad() {
		return onAfterDataLoad;
	}

	/**
	 * Sets the on after data load.
	 *
	 * @param onAfterDataLoad the new on after data load
	 */
	public void setOnAfterDataLoad(String onAfterDataLoad) {
		this.onAfterDataLoad = onAfterDataLoad;
	}

	/**
	 * Gets the on validation error.
	 *
	 * @return the on validation error
	 */
	public String getOnValidationError() {
		return onValidationError;
	}

	/**
	 * Sets the on validation error.
	 *
	 * @param onValidationError the new on validation error
	 */
	public void setOnValidationError(String onValidationError) {
		this.onValidationError = onValidationError;
	}

	/**
	 * Gets the on validation sucess.
	 *
	 * @return the on validation sucess
	 */
	public String getOnValidationSucess() {
		return onValidationSucess;
	}

	/**
	 * Sets the on validation sucess.
	 *
	 * @param onValidationSucess the new on validation sucess
	 */
	public void setOnValidationSucess(String onValidationSucess) {
		this.onValidationSucess = onValidationSucess;
	}

	/**
	 * Gets the on header click.
	 *
	 * @return the on header click
	 */
	public String getOnHeaderClick() {
		return onHeaderClick;
	}

	/**
	 * Sets the on header click.
	 *
	 * @param onHeaderClick the new on header click
	 */
	public void setOnHeaderClick(String onHeaderClick) {
		this.onHeaderClick = onHeaderClick;
	}

	/**
	 * Gets the on before sorting.
	 *
	 * @return the on before sorting
	 */
	public String getOnBeforeSorting() {
		return onBeforeSorting;
	}

	/**
	 * Sets the on before sorting.
	 *
	 * @param onBeforeSorting the new on before sorting
	 */
	public void setOnBeforeSorting(String onBeforeSorting) {
		this.onBeforeSorting = onBeforeSorting;
	}

	/**
	 * Gets the on after sorting.
	 *
	 * @return the on after sorting
	 */
	public String getOnAfterSorting() {
		return onAfterSorting;
	}

	/**
	 * Sets the on after sorting.
	 *
	 * @param onAfterSorting the new on after sorting
	 */
	public void setOnAfterSorting(String onAfterSorting) {
		this.onAfterSorting = onAfterSorting;
	}

	/**
	 * Gets the on cell changed.
	 *
	 * @return the on cell changed
	 */
	public String getOnCellChanged() {
		return onCellChanged;
	}

	/**
	 * Sets the on cell changed.
	 *
	 * @param onCellChanged the new on cell changed
	 */
	public void setOnCellChanged(String onCellChanged) {
		this.onCellChanged = onCellChanged;
	}

	/**
	 * Gets the on edit cell.
	 *
	 * @return the on edit cell
	 */
	public String getOnEditCell() {
		return onEditCell;
	}

	/**
	 * Sets the on edit cell.
	 *
	 * @param onEditCell the new on edit cell
	 */
	public void setOnEditCell(String onEditCell) {
		this.onEditCell = onEditCell;
	}

	/**
	 * Gets the on row added.
	 *
	 * @return the on row added
	 */
	public String getOnRowAdded() {
		return onRowAdded;
	}

	/**
	 * Sets the on row added.
	 *
	 * @param onRowAdded the new on row added
	 */
	public void setOnRowAdded(String onRowAdded) {
		this.onRowAdded = onRowAdded;
	}

	/**
	 * Gets the on row created.
	 *
	 * @return the on row created
	 */
	public String getOnRowCreated() {
		return onRowCreated;
	}

	/**
	 * Sets the on row created.
	 *
	 * @param onRowCreated the new on row created
	 */
	public void setOnRowCreated(String onRowCreated) {
		this.onRowCreated = onRowCreated;
	}

	/**
	 * Gets the on before row deleted.
	 *
	 * @return the on before row deleted
	 */
	public String getOnBeforeRowDeleted() {
		return onBeforeRowDeleted;
	}

	/**
	 * Sets the on before row deleted.
	 *
	 * @param onBeforeRowDeleted the new on before row deleted
	 */
	public void setOnBeforeRowDeleted(String onBeforeRowDeleted) {
		this.onBeforeRowDeleted = onBeforeRowDeleted;
	}

	/**
	 * Gets the on after row deleted.
	 *
	 * @return the on after row deleted
	 */
	public String getOnAfterRowDeleted() {
		return onAfterRowDeleted;
	}

	/**
	 * Sets the on after row deleted.
	 *
	 * @param onAfterRowDeleted the new on after row deleted
	 */
	public void setOnAfterRowDeleted(String onAfterRowDeleted) {
		this.onAfterRowDeleted = onAfterRowDeleted;
	}

	/**
	 * Adds the column.
	 *
	 * @param column the column
	 */
	public void addColumn(Column column) {
		this.columns.add(column);
	}

	/**
	 * Checks if is select first row after load.
	 *
	 * @return true, if is select first row after load
	 */
	public boolean isSelectFirstRowAfterLoad() {
		return selectFirstRowAfterLoad;
	}

	/**
	 * Sets the select first row after load.
	 *
	 * @param selectFirstRowAfterLoad the new select first row after load
	 */
	public void setSelectFirstRowAfterLoad(boolean selectFirstRowAfterLoad) {
		this.selectFirstRowAfterLoad = selectFirstRowAfterLoad;
	}

	/**
	 * Gets the data setting.
	 *
	 * @return the data setting
	 */
	public DataSetting getDataSetting() {
		return dataSetting;
	}

	/**
	 * Sets the data setting.
	 *
	 * @param dataSetting the new data setting
	 */
	public void setDataSetting(DataSetting dataSetting) {
		this.dataSetting = dataSetting;
	}

	/**
	 * Gets the data processor.
	 *
	 * @return the data processor
	 */
	public DataProcessor getDataProcessor() {
		return dataProcessor;
	}

	/**
	 * Sets the data processor.
	 *
	 * @param dataProcessor the new data processor
	 */
	public void setDataProcessor(DataProcessor dataProcessor) {
		this.dataProcessor = dataProcessor;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grid other = (Grid) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
	 * Gets the child grid.
	 *
	 * @return the child grid
	 */
	public List<Grid> getChildGrid() {
		return childGrid;
	}

	/**
	 * Sets the child grid.
	 *
	 * @param childGrid the new child grid
	 */
	public void setChildGrid(List<Grid> childGrid) {
		this.childGrid = childGrid;
	}

	/**
	 * Gets the parent grid.
	 *
	 * @return the parent grid
	 */
	public Object getParentGrid() {
		return parentGrid;
	}

	/**
	 * Sets the parent grid.
	 *
	 * @param parentGrid the new parent grid
	 */
	public void setParentGrid(Object parentGrid) {
		this.parentGrid = parentGrid;
	}

	/**
	 * Checks if is enable undo redo.
	 *
	 * @return true, if is enable undo redo
	 */
	public boolean isEnableUndoRedo() {
		return enableUndoRedo;
	}

	/**
	 * Sets the enable undo redo.
	 *
	 * @param enableUndoRedo the new enable undo redo
	 */
	public void setEnableUndoRedo(boolean enableUndoRedo) {
		this.enableUndoRedo = enableUndoRedo;
	}

}