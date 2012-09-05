/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons EntityInfoDhtmlxConfig.java 2012-8-2 13:15:48 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx.impl;

import java.lang.reflect.AnnotatedElement;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.com.rebirth.commons.search.annotation.AbstractSearchProperty;
import cn.com.rebirth.commons.search.annotation.AnnotationInfo;
import cn.com.rebirth.commons.search.annotation.AnnotationManager;
import cn.com.rebirth.knowledge.commons.dhtmlx.AbstractDhtmlxConfig;
import cn.com.rebirth.knowledge.commons.dhtmlx.DhtmlxConfig;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlColumn;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlxSort;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * The Class EntityInfoDhtmlxConfig.
 *
 * @author l.xue.nong
 */
public class EntityInfoDhtmlxConfig extends AbstractDhtmlxConfig implements DhtmlxConfig {

	/** The annotation info. */
	protected final AnnotationInfo annotationInfo;

	/** The info. */
	private final Info info;

	/**
	 * The Class Info.
	 *
	 * @author l.xue.nong
	 */
	class Info {

		/** The header. */
		public List<String> header;

		/** The init widths. */
		public List<String> initWidths;

		/** The column ids. */
		public List<String> columnIds;

		/** The col align. */
		public List<String> colAlign;

		/** The col types. */
		public List<String> colTypes;

		/** The col sorting. */
		public List<String> colSorting;

		/** The columns visibility. */
		public List<String> columnsVisibility;

		/**
		 * Instantiates a new info.
		 *
		 * @param annotationInfo the annotation info
		 */
		public Info(AnnotationInfo annotationInfo) {
			super();
			Map<String, AbstractSearchProperty> map = annotationInfo.getProperties();
			List<AbstractSearchProperty> columnPropertys = Lists.newArrayList();
			for (Map.Entry<String, AbstractSearchProperty> entry : map.entrySet()) {
				AbstractSearchProperty property = entry.getValue();
				DhtmlColumn dhtmlColumn = property.execute(new AbstractSearchProperty.ElementCallback<DhtmlColumn>() {

					@Override
					public DhtmlColumn doExecute(AnnotatedElement element) {
						return element.getAnnotation(DhtmlColumn.class);
					}
				});
				if (dhtmlColumn != null) {
					columnPropertys.add(property);
				}
			}
			Collections.sort(columnPropertys, new Comparator<AbstractSearchProperty>() {

				@Override
				public int compare(AbstractSearchProperty o1, AbstractSearchProperty o2) {
					Integer index1 = o1.getElement().getAnnotation(DhtmlColumn.class).columnIndex();
					Integer index2 = o2.getElement().getAnnotation(DhtmlColumn.class).columnIndex();
					return index1.compareTo(index2);
				}
			});
			int siz = columnPropertys.size();
			header = Lists.newArrayListWithCapacity(siz);
			initWidths = Lists.newArrayListWithCapacity(siz);
			columnIds = Lists.newArrayListWithCapacity(siz);
			colAlign = Lists.newArrayListWithCapacity(siz);
			colTypes = Lists.newArrayListWithCapacity(siz);
			colSorting = Lists.newArrayListWithCapacity(siz);
			columnsVisibility = Lists.newArrayListWithCapacity(siz);
			for (AbstractSearchProperty property : columnPropertys) {
				DhtmlColumn dhtmlColumn = property.getElement().getAnnotation(DhtmlColumn.class);
				if (dhtmlColumn != null) {
					header.add(dhtmlColumn.headerName());
					initWidths.add(dhtmlColumn.initWidth() + "");
					columnIds.add(findIdName(dhtmlColumn, property));
					colAlign.add(dhtmlColumn.coulumnAlign().name().toLowerCase());
					colTypes.add(dhtmlColumn.coulumnType().name().toLowerCase());
					colSorting.add(convterSort(dhtmlColumn, property));
					columnsVisibility.add(dhtmlColumn.columnVisibility() + "");
				}
			}
		}

		/**
		 * Convter sort.
		 *
		 * @param dhtmlColumn the dhtml column
		 * @param property the property
		 * @return the string
		 */
		private String convterSort(DhtmlColumn dhtmlColumn, AbstractSearchProperty property) {
			String name = dhtmlColumn.columnSort().name().toLowerCase();
			if (DhtmlxSort.NONE.equals(dhtmlColumn.columnSort())) {
				Class<?> class1 = property.getPropertyClass();
				if (Number.class.isAssignableFrom(class1)) {
					return DhtmlxSort.INT.name().toLowerCase();
				} else if (Date.class.isAssignableFrom(class1)) {
					return DhtmlxSort.DATE.name().toLowerCase();
				} else {
					return DhtmlxSort.STR.name().toLowerCase();
				}
			}
			return name;
		}

		/**
		 * Find id name.
		 *
		 * @param dhtmlColumn the dhtml column
		 * @param property the property
		 * @return the string
		 */
		private String findIdName(DhtmlColumn dhtmlColumn, AbstractSearchProperty property) {
			String name = dhtmlColumn.columnId();
			if (DhtmlxConfig.FIELDNAME.equals(name)) {
				return property.getFieldName();
			}
			return name;
		}

	}

	/**
	 * Instantiates a new entity info dhtmlx config.
	 *
	 * @param annotationInfo the annotation info
	 */
	public EntityInfoDhtmlxConfig(AnnotationInfo annotationInfo) {
		super();
		this.annotationInfo = annotationInfo;
		this.info = new Info(annotationInfo);
	}

	/**
	 * Instantiates a new entity info dhtmlx config.
	 *
	 * @param entityClass the entity class
	 */
	public EntityInfoDhtmlxConfig(Class<?> entityClass) {
		this(AnnotationManager.getInstance().getAnnotationInfo(entityClass));
	}

	/* (non-Javadoc)
	 * @see cn.com.rebirth.knowledge.commons.dhtmlx.DhtmlxConfig#getHeader()
	 */
	@Override
	public String getHeader() {
		return join(info.header);
	}

	/**
	 * Join.
	 *
	 * @param strings the strings
	 * @return the string
	 */
	protected String join(List<String> strings) {
		return StringUtils.join(strings, ",");
	}

	/* (non-Javadoc)
	 * @see cn.com.rebirth.knowledge.commons.dhtmlx.DhtmlxConfig#getInitWidths()
	 */
	@Override
	public String getInitWidths() {
		return join(info.initWidths);
	}

	/* (non-Javadoc)
	 * @see cn.com.rebirth.knowledge.commons.dhtmlx.DhtmlxConfig#getColumnIds()
	 */
	@Override
	public String getColumnIds() {
		return join(info.columnIds);
	}

	/* (non-Javadoc)
	 * @see cn.com.rebirth.knowledge.commons.dhtmlx.DhtmlxConfig#getColAlign()
	 */
	@Override
	public String getColAlign() {
		return join(info.colAlign);
	}

	/* (non-Javadoc)
	 * @see cn.com.rebirth.knowledge.commons.dhtmlx.DhtmlxConfig#getColTypes()
	 */
	@Override
	public String getColTypes() {
		return join(info.colTypes);
	}

	/* (non-Javadoc)
	 * @see cn.com.rebirth.knowledge.commons.dhtmlx.DhtmlxConfig#getColSorting()
	 */
	@Override
	public String getColSorting() {
		return join(info.colSorting);
	}

	/* (non-Javadoc)
	 * @see cn.com.rebirth.knowledge.commons.dhtmlx.DhtmlxConfig#getColumnsVisibility()
	 */
	@Override
	public String getColumnsVisibility() {
		return join(info.columnsVisibility);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EntityInfoDhtmlxConfig [getHeader()=" + getHeader() + ", getInitWidths()=" + getInitWidths()
				+ ", getColumnIds()=" + getColumnIds() + ", getColAlign()=" + getColAlign() + ", getColTypes()="
				+ getColTypes() + ", getColSorting()=" + getColSorting() + ", getColumnsVisibility()="
				+ getColumnsVisibility() + "]";
	}

	/* (non-Javadoc)
	 * @see cn.com.rebirth.knowledge.commons.dhtmlx.AbstractDhtmlxConfig#toParam()
	 */
	@Override
	public Map<String, Object> toParam() {
		Map<String, Object> param = Maps.newHashMap();
		param.put("imagePath", getImagePath());
		param.put("header", getHeader());
		param.put("initWidths", getInitWidths());
		param.put("columnIds", getColumnIds());
		param.put("colAlign", getColAlign());
		param.put("colTypes", getColTypes());
		param.put("skin", getSkin());
		param.put("colSorting", getColSorting());
		return param;
	}

}
