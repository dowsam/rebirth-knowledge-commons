/**
 * Copyright (c) 2005-2012-9-20 www.china-cti.com
 * Id: CircleCategoryEntity.java,14:38:59
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

	/* (non-Javadoc)
	 * @see cn.com.rebirth.commons.entity.BaseEntity#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
		result = prime * result + ((categoryType == null) ? 0 : categoryType.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		result = prime * result + ((secondCategory == null) ? 0 : secondCategory.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see cn.com.rebirth.commons.entity.BaseEntity#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CircleCategoryEntity other = (CircleCategoryEntity) obj;
		if (categoryName == null) {
			if (other.categoryName != null)
				return false;
		} else if (!categoryName.equals(other.categoryName))
			return false;
		if (categoryType != other.categoryType)
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		if (secondCategory == null) {
			if (other.secondCategory != null)
				return false;
		} else if (!secondCategory.equals(other.secondCategory))
			return false;
		return true;
	}

}
