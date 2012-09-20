/**
 * Copyright (c) 2005-2012-9-19 www.china-cti.com
 * Id: CircleCategoryEntity.java,10:31:14
 * @author wuwei
 */
package cn.com.rebirth.knowledge.commons.entity.circle;

import java.util.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import cn.com.rebirth.knowledge.commons.dhtmlx.entity.*;

import com.google.common.collect.*;

// TODO: Auto-generated Javadoc
/**
 * The Class CircleCategoryEntity.
 *  圈子类别
 * @author wuwei
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "CIRCLE_CATEGORY")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CircleCategoryEntity extends AbstractDhtmlxBaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7851723202881373196L;

	/**
	 * The Enum CircleCategoryType.
	 *
	 * @author wuwei
	 */
	public static enum CircleCategoryType {

		/** The first. 一级类别*/
		FIRST,
		/** The second. 二级类别*/
		SECOND;
	}

	/** The category name. */
	private String categoryName;

	/** The second category. */
	private List<CircleCategoryEntity> secondCategory = Lists.newArrayList();

	/** The category type. */
	private CircleCategoryType categoryType;

	/** The parent. */
	private CircleCategoryEntity parent;

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
	 * Gets the category name.
	 *
	 * @return the category name
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * Sets the category name.
	 *
	 * @param categoryName the new category name
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * Gets the second category.
	 *
	 * @return the second category
	 */
	@OneToMany(mappedBy = "parent")
	@OnDelete(action = OnDeleteAction.CASCADE)
	public List<CircleCategoryEntity> getSecondCategory() {
		return secondCategory;
	}

	/**
	 * Sets the second category.
	 *
	 * @param secondCategory the new second category
	 */
	public void setSecondCategory(List<CircleCategoryEntity> secondCategory) {
		this.secondCategory = secondCategory;
	}

	/**
	 * Gets the parent.
	 *
	 * @return the parent
	 */
	@ManyToOne
	@JoinColumn(name = "parentId")
	public CircleCategoryEntity getParent() {
		return parent;
	}

	/**
	 * Sets the parent.
	 *
	 * @param parent the new parent
	 */
	public void setParent(CircleCategoryEntity parent) {
		this.parent = parent;
	}

	/**
	 * Sets the category type.
	 *
	 * @param categoryType the new category type
	 */
	public void setCategoryType(CircleCategoryType categoryType) {
		this.categoryType = categoryType;
	}

	/**
	 * Gets the category type.
	 *
	 * @return the category type
	 */
	public CircleCategoryType getCategoryType() {
		return categoryType;
	}

}
