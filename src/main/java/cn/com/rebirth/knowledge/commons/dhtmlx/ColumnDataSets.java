/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons ColumnDataSets.java 2012-8-10 17:28:09 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx;

import cn.com.rebirth.knowledge.commons.dhtmlx.entity.Column;
import cn.com.rebirth.knowledge.commons.dhtmlx.entity.Grid;

/**
 * The Interface ColumnDataSets.
 *
 * @author l.xue.nong
 */
public interface ColumnDataSets {

	/**
	 * Bulid column data.
	 *
	 * @param columnIndex the column index
	 * @param column the column
	 * @param grid the grid
	 * @param stringBuilder the string builder
	 */
	void bulidColumnData(int columnIndex, Column column, Grid grid, StringBuilder stringBuilder);

	/**
	 * The Class CListColumnDataSets.
	 *
	 * @author l.xue.nong
	 */
	public abstract static class CListColumnDataSets implements ColumnDataSets {

		/* (non-Javadoc)
		 * @see cn.com.rebirth.knowledge.commons.dhtmlx.ColumnDataSets#bulidColumnData(int, cn.com.rebirth.knowledge.commons.dhtmlx.entity.Column, cn.com.rebirth.knowledge.commons.dhtmlx.entity.Grid, java.lang.StringBuilder)
		 */
		@Override
		public void bulidColumnData(int columnIndex, Column column, Grid grid, StringBuilder stringBuilder) {
			stringBuilder.append(grid.getId()).append(".registerCList(").append(columnIndex).append(",").append("[\"")
					.append(getNames()).append("\"]").append(",").append("[\"").append(getKeys()).append("\"]")
					.append(");\n");
		}

		/**
		 * Gets the keys.
		 *
		 * @return the keys
		 */
		protected abstract String getKeys();

		/**
		 * Gets the names.
		 *
		 * @return the names
		 */
		protected abstract String getNames();

	}

	/**
	 * The Class DefaultColumnDataSets.
	 *
	 * @author l.xue.nong
	 */
	public static class DefaultColumnDataSets implements ColumnDataSets {

		/* (non-Javadoc)
		 * @see cn.com.rebirth.knowledge.commons.dhtmlx.ColumnDataSets#bulidColumnData(int, cn.com.rebirth.knowledge.commons.dhtmlx.entity.Column, cn.com.rebirth.knowledge.commons.dhtmlx.entity.Grid, java.lang.StringBuilder)
		 */
		@Override
		public void bulidColumnData(int columnIndex, Column column, Grid grid, StringBuilder stringBuilder) {

		}

	}
}
