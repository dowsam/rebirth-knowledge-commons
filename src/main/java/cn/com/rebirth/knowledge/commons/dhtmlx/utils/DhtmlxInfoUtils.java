/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons DhtmlxInfoUtils.java 2012-8-6 10:16:18 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx.utils;

import java.lang.reflect.AnnotatedElement;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import cn.com.rebirth.commons.search.annotation.AbstractSearchProperty;
import cn.com.rebirth.commons.search.annotation.AnnotationInfo;
import cn.com.rebirth.commons.search.annotation.AnnotationManager;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlColumn;
import cn.com.rebirth.knowledge.commons.dhtmlx.entity.AbstractDhtmlxBaseEntity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * The Class DhtmlxInfoUtils.
 *
 * @author l.xue.nong
 */
public abstract class DhtmlxInfoUtils {

	/** The cached map. */
	private static Map<Class<? extends AbstractDhtmlxBaseEntity>, List<AbstractSearchProperty>> cachedMap = Maps
			.newLinkedHashMap();

	/**
	 * Gets the properties.
	 *
	 * @param entityClass the entity class
	 * @return the properties
	 */
	public static List<AbstractSearchProperty> getProperties(Class<? extends AbstractDhtmlxBaseEntity> entityClass) {
		if (cachedMap.get(entityClass) != null)
			return cachedMap.get(entityClass);
		AnnotationInfo annotationInfo = AnnotationManager.getInstance().getAnnotationInfo(entityClass);
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
		cachedMap.put(entityClass, columnPropertys);
		return columnPropertys;
	}
}
