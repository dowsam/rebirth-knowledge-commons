/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons DhtmlColumn.java 2012-8-6 13:51:34 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.com.rebirth.knowledge.commons.dhtmlx.ColumnDataSets;
import cn.com.rebirth.knowledge.commons.dhtmlx.DhtmlxConfig;
import cn.com.rebirth.knowledge.commons.dhtmlx.data.IColumnConverter;

/**
 * The Interface DhtmlColumn.
 *
 * @author l.xue.nong
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.FIELD, ElementType.METHOD })
@Documented
public @interface DhtmlColumn {

	/**
	 * Column index.
	 *
	 * @return the int
	 */
	int columnIndex();//顺序

	/**
	 * Header name.
	 *
	 * @return the string
	 */
	String headerName();//列名

	/**
	 * Inits the width.
	 *
	 * @return the int
	 */
	int initWidth() default 100;//列宽

	/**
	 * Column id.
	 *
	 * @return the string
	 */
	String columnId() default DhtmlxConfig.FIELDNAME;//列id

	/**
	 * Coulumn align.
	 *
	 * @return the align
	 */
	Align coulumnAlign() default Align.LEFT;//列位置

	/**
	 * Coulumn type.
	 *
	 * @return the dhtmlx base type
	 */
	DhtmlxBaseType coulumnType() default DhtmlxBaseType.ED;//列的类型

	/**
	 * Column visibility.
	 *
	 * @return true, if successful
	 */
	boolean columnVisibility() default true;//列是否可见

	/**
	 * Column sort.
	 *
	 * @return the dhtmlx sort
	 */
	DhtmlxSort columnSort() default DhtmlxSort.NONE;//列排序的类型

	/**
	 * Sortable.
	 *
	 * @return true, if successful
	 */
	boolean sortable() default true;//是否排序

	/**
	 * Frozen.
	 *
	 * @return true, if successful
	 */
	boolean frozen() default false;//是否冻结

	/**
	 * Checks if is key.
	 *
	 * @return true, if is key
	 */
	boolean isKey() default false;//是否主建列

	/**
	 * Exportable.
	 *
	 * @return true, if successful
	 */
	boolean exportable() default true;//是否导出

	/**
	 * Color.
	 *
	 * @return the string
	 */
	String color() default "";//色

	/**
	 * Column converter.
	 *
	 * @return the class<? extends i column converter>
	 */
	Class<? extends IColumnConverter> columnConverter() default IColumnConverter.DefaultColumnConverter.class;

	Class<? extends ColumnDataSets> columnDataSets() default ColumnDataSets.class;

	boolean group() default false;
}
