/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons AbstractDhtmlxBaseEntity.java 2012-8-6 15:07:27 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx.entity;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;

import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.ConstructorUtils;

import cn.com.rebirth.commons.search.annotation.AbstractSearchProperty;
import cn.com.rebirth.commons.utils.ReflectionUtils;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlColumn;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.Dhtmlx;
import cn.com.rebirth.knowledge.commons.dhtmlx.data.IColumnConverter;
import cn.com.rebirth.knowledge.commons.dhtmlx.data.IRowConverter;
import cn.com.rebirth.knowledge.commons.dhtmlx.utils.DhtmlxInfoUtils;
import cn.com.rebirth.knowledge.commons.entity.AbstractBaseEntity;

import com.google.common.collect.Lists;

/**
 * The Class AbstractDhtmlxBaseEntity.
 *
 * @author l.xue.nong
 */
public abstract class AbstractDhtmlxBaseEntity extends AbstractBaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4418877598668273986L;

	/**
	 * Instantiates a new abstract dhtmlx base entity.
	 */
	public AbstractDhtmlxBaseEntity() {
		super();

	}

	/* (non-Javadoc)
	 * @see cn.com.rebirth.knowledge.commons.entity.AbstractBaseEntity#getDhtmlxJsonOrXmlObject()
	 */
	@Override
	public Collection<Object> getDhtmlxJsonOrXmlObject() {
		List<AbstractSearchProperty> properties = getProperties();
		beforeValue(properties);
		List<Object> paramObject = Lists.newArrayList();
		List<DhtmlColumn> columns = Lists.newArrayList();
		for (AbstractSearchProperty abstractSearchProperty : properties) {
			DhtmlColumn dhtmlColumn = abstractSearchProperty.getElement().getAnnotation(DhtmlColumn.class);
			Object value = toValue(abstractSearchProperty, dhtmlColumn);
			if (dhtmlColumn != null) {
				columns.add(dhtmlColumn);
				Class<? extends IColumnConverter> converterClass = dhtmlColumn.columnConverter();
				if (converterClass != null) {
					try {
						IColumnConverter columnConverter = ConstructorUtils.invokeConstructor(converterClass);
						paramObject.add(columnConverter.convert(abstractSearchProperty, dhtmlColumn, this, value));
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					}
				} else {
					paramObject.add(value);
				}
			}
		}
		return afterValue(paramObject, properties, columns);
	}

	/**
	 * Before value.
	 *
	 * @param properties the properties
	 */
	protected void beforeValue(List<AbstractSearchProperty> properties) {

	}

	/**
	 * After value.
	 *
	 * @param paramObject the param object
	 * @param properties the properties
	 * @param columns the columns
	 * @return the collection
	 */
	protected Collection<Object> afterValue(List<Object> paramObject, List<AbstractSearchProperty> properties,
			List<DhtmlColumn> columns) {
		Dhtmlx dhtmlx = getClass().getAnnotation(Dhtmlx.class);
		if (dhtmlx != null) {
			Class<? extends IRowConverter> rowConverterClass = dhtmlx.rowConverter();
			if (rowConverterClass != null) {
				try {
					IRowConverter iRowConverter = ConstructorUtils.invokeConstructor(rowConverterClass);
					return iRowConverter.converter(paramObject, properties, columns, dhtmlx);
				} catch (Exception e) {
					//nothing
				}
			}
		}
		return paramObject;
	}

	/**
	 * To value.
	 *
	 * @param abstractSearchProperty the abstract search property
	 * @param dhtmlColumn 
	 * @return the object
	 */
	protected Object toValue(AbstractSearchProperty abstractSearchProperty, DhtmlColumn dhtmlColumn) {
		String name = dhtmlColumn.columnId();
		String pros[] = StringUtils.split(name, ".");
		Object o = abstractSearchProperty.getProperty(this);
		if (pros.length > 1 && o != null) {
			for (int i = 1; i < pros.length; i++) {
				o = ReflectionUtils.invokeGetterMethod(o, pros[i]);
				if (o == null)
					break;
			}
		}
		return o;
	}

	/**
	 * Gets the properties.
	 *
	 * @return the properties
	 */
	public List<AbstractSearchProperty> getProperties() {
		return DhtmlxInfoUtils.getProperties(getClass());
	}

	@Transient
	@Override
	public Collection<? extends AbstractBaseEntity> getChildObject() {
		return null;
	}

}
