/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons Dhtmlx.java 2012-8-6 14:55:33 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.com.rebirth.knowledge.commons.dhtmlx.DhtmlxConfig;
import cn.com.rebirth.knowledge.commons.dhtmlx.GridType;
import cn.com.rebirth.knowledge.commons.dhtmlx.data.IRowConverter;
import cn.com.rebirth.knowledge.commons.dhtmlx.entity.AbstractDhtmlxBaseEntity;

/**
 * The Interface Dhtmlx.
 *
 * @author l.xue.nong
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.TYPE })
@Documented
public @interface Dhtmlx {

	/**
	 * Image path.
	 *
	 * @return the string
	 */
	String imagePath() default DhtmlxConfig.IMAGEPATH;

	/**
	 * Skin.
	 *
	 * @return the string
	 */
	String skin() default DhtmlxConfig.DEFAULT_SKIN;

	/**
	 * Row converter.
	 *
	 * @return the class<? extends i row converter>
	 */
	Class<? extends IRowConverter> rowConverter() default IRowConverter.DefaultRowConverter.class;

	/**
	 * Creates the check all column.
	 *
	 * @return true, if successful
	 */
	boolean createCheckAllColumn() default true;

	/**
	 * Grid type.
	 *
	 * @return the grid type
	 */
	GridType gridType() default GridType.dhtmlxGrid;

	/**
	 * Data type.
	 *
	 * @return the string
	 */
	String dataType() default "json";

	/**
	 * Checks if is page.
	 *
	 * @return true, if is page
	 */
	boolean isPage() default true;

	/**
	 * Checks if is data processor.
	 *
	 * @return true, if is data processor
	 */
	boolean isDataProcessor() default false;

	/**
	 * Parent class.
	 *
	 * @return the class<? extends abstract dhtmlx base entity>
	 */
	Class<? extends AbstractDhtmlxBaseEntity> parentClass() default AbstractDhtmlxBaseEntity.class;

	/**
	 * Auto heigth.
	 *
	 * @return true, if successful
	 */
	boolean autoHeigth() default false;

	/**
	 * Enable undo redo.
	 *
	 * @return true, if successful
	 */
	boolean enableUndoRedo() default true;

	boolean enableSearchBar() default true;

	String onRowDblClicked() default "";
}
