/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons AbstractOperatingEntity.java 2012-9-3 10:18:13 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlColumn;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlxBaseType;
import cn.com.rebirth.knowledge.commons.dhtmlx.entity.AbstractDhtmlxBaseEntity;
import cn.com.rebirth.knowledge.commons.entity.listener.OperatingEntityListener;
import cn.com.rebirth.knowledge.commons.entity.system.AbstractLogBaseEntity.CreateTimeColumnConverter;

/**
 * The Class AbstractOperatingEntity.
 *
 * @author l.xue.nong
 */
@MappedSuperclass
@EntityListeners(value = { OperatingEntityListener.class })
public abstract class AbstractOperatingEntity extends AbstractDhtmlxBaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7006173118052928707L;
	/** The create time. */
	protected Date createTime;//创建时间

	/** The create by. */
	protected String createBy;//创建人

	/** The last modify time. */
	protected Date lastModifyTime;//最后修改时间

	/** The last modify by. */
	protected String lastModifyBy;//最后修改人

	/**
	 * Gets the creates the time.
	 *
	 * @return the creates the time
	 */
	@Column(updatable = false)
	@DhtmlColumn(columnIndex = 2, headerName = "创建时间", columnConverter = CreateTimeColumnConverter.class, coulumnType = DhtmlxBaseType.RO)
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * Sets the creates the time.
	 *
	 * @param createTime the new creates the time
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * Gets the creates the by.
	 *
	 * @return the creates the by
	 */
	@Column(updatable = false)
	@DhtmlColumn(columnIndex = 1, headerName = "创建人", coulumnType = DhtmlxBaseType.RO)
	public String getCreateBy() {
		return createBy;
	}

	/**
	 * Sets the creates the by.
	 *
	 * @param createBy the new creates the by
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	/**
	 * Gets the last modify time.
	 *
	 * @return the last modify time
	 */
	@Column(insertable = false)
	@DhtmlColumn(columnIndex = 4, headerName = "修改时间", columnConverter = CreateTimeColumnConverter.class, coulumnType = DhtmlxBaseType.RO)
	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	/**	
	 * Sets the last modify time.
	 *
	 * @param lastModifyTime the new last modify time
	 */
	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	/**
	 * Gets the last modify by.
	 *
	 * @return the last modify by
	 */
	@Column(insertable = false)
	@DhtmlColumn(columnIndex = 3, headerName = "修改人", coulumnType = DhtmlxBaseType.RO)
	public String getLastModifyBy() {
		return lastModifyBy;
	}

	/**
	 * Sets the last modify by.
	 *
	 * @param lastModifyBy the new last modify by
	 */
	public void setLastModifyBy(String lastModifyBy) {
		this.lastModifyBy = lastModifyBy;
	}

}
