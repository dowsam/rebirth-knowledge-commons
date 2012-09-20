/**
 * Copyright (c) 2005-2012-9-12 www.china-cti.com
 * Id: AbstractStudyEntity.java,9:30:58
 * @author wuwei
 */
package cn.com.rebirth.knowledge.commons.entity.study;

import javax.persistence.*;

import cn.com.rebirth.knowledge.commons.dhtmlx.entity.*;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractStudyEntity.
 *
 * @author wuwei
 */
public class AbstractStudyEntity extends AbstractDhtmlxBaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5009413626921809989L;

	/** The name 标题. */
	private String name;

	/** The label 标签. */
	private String label;

	/** The read permission 阅读权限. */
	private Integer readPermission;

	/** The category 分类. */
	private Integer category;

	/** The abstract info 摘要. */
	private String abstractInfo;

	/** The originality 原创投稿. */
	private boolean originality;

	/* (non-Javadoc)
	 * @see cn.com.rebirth.knowledge.commons.entity.AbstractBaseEntity#isChildTrem()
	 */
	@Override
	@Transient
	public boolean isChildTrem() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the label.
	 *
	 * @param label the new label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Gets the read permission.
	 *
	 * @return the read permission
	 */
	public Integer getReadPermission() {
		return readPermission;
	}

	/**
	 * Sets the read permission.
	 *
	 * @param readPermission the new read permission
	 */
	public void setReadPermission(Integer readPermission) {
		this.readPermission = readPermission;
	}

	/**
	 * Gets the category.
	 *
	 * @return the category
	 */
	public Integer getCategory() {
		return category;
	}

	/**
	 * Sets the category.
	 *
	 * @param category the new category
	 */
	public void setCategory(Integer category) {
		this.category = category;
	}

	/**
	 * Gets the abstract info.
	 *
	 * @return the abstract info
	 */
	public String getAbstractInfo() {
		return abstractInfo;
	}

	/**
	 * Sets the abstract info.
	 *
	 * @param abstractInfo the new abstract info
	 */
	public void setAbstractInfo(String abstractInfo) {
		this.abstractInfo = abstractInfo;
	}

	/**
	 * Checks if is originality.
	 *
	 * @return true, if is originality
	 */
	public boolean isOriginality() {
		return originality;
	}

	/**
	 * Sets the originality.
	 *
	 * @param originality the new originality
	 */
	public void setOriginality(boolean originality) {
		this.originality = originality;
	}

}
