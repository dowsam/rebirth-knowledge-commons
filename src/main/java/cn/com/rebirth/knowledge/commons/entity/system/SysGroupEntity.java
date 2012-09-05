/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons SysGroupEntity.java 2012-8-20 13:19:13 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.entity.system;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotBlank;

import cn.com.rebirth.commons.search.annotation.AbstractSearchProperty;
import cn.com.rebirth.commons.utils.CollectionMapperUtils;
import cn.com.rebirth.commons.utils.ConvertUtils;
import cn.com.rebirth.knowledge.commons.dhtmlx.ColumnDataSets;
import cn.com.rebirth.knowledge.commons.dhtmlx.DhtmlxJsonObjectUtils.Cell;
import cn.com.rebirth.knowledge.commons.dhtmlx.GridType;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlColumn;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.Dhtmlx;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlxBaseType;
import cn.com.rebirth.knowledge.commons.dhtmlx.entity.AbstractDhtmlxBaseEntity;
import cn.com.rebirth.knowledge.commons.dhtmlx.entity.Grid;
import cn.com.rebirth.knowledge.commons.entity.AbstractBaseEntity;

import com.google.common.collect.Lists;

/**
 * The Class SysGroupEntity.
 *
 * @author l.xue.nong
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "SYS_GROUP")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Dhtmlx(createCheckAllColumn = false, dataType = "xml", gridType = GridType.dhtmlxTreeGrid, isPage = false, isDataProcessor = true, parentClass = SysRoleEntity.class)
public class SysGroupEntity extends AbstractDhtmlxBaseEntity {

	/**
	 * The Class RoleEntitiesColumnDataSet.
	 *
	 * @author l.xue.nong
	 */
	public static class RoleEntitiesColumnDataSet implements ColumnDataSets {

		/**
		 * Bulid column data.
		 *
		 * @param columnIndex the column index
		 * @param column the column
		 * @param grid the grid
		 * @param stringBuilder the string builder
		 */
		@Override
		public void bulidColumnData(int columnIndex, cn.com.rebirth.knowledge.commons.dhtmlx.entity.Column column,
				Grid grid, StringBuilder stringBuilder) {
			stringBuilder.append(grid.getId()).append(".setSubGrid(").append(((Grid) grid.getParentGrid()).getId())
					.append(",").append(columnIndex).append(",").append("1").append(");\n");
		}

	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5558211588690551358L;
	//自身的基本属性
	/** The group name. */
	private String groupName;//组织的名

	/** The group code. */
	private String groupCode;//组织的编号

	//角色的分配
	/** The role entities. */
	private List<SysRoleEntity> roleEntities = Lists.newArrayList();
	//组织下的用户
	/** The sys user entities. */
	private List<SysUserEntity> sysUserEntities = Lists.newArrayList();

	//自身的关联
	/** The parent id. */
	private Long parentId;

	/** The parent sys group. */
	private SysGroupEntity parentSysGroup;

	/** The child sys group. */
	private List<SysGroupEntity> childSysGroup = Lists.newArrayList();

	/* (non-Javadoc)
	 * @see cn.com.rebirth.knowledge.commons.entity.AbstractBaseEntity#isChildTrem()
	 */
	/**
	 * Checks if is child trem.
	 *
	 * @return true, if is child trem
	 */
	@Override
	@Transient
	public boolean isChildTrem() {
		return getChildSysGroup().size() > 0;
	}

	/**
	 * Gets the group name.
	 *
	 * @return the group name
	 */
	@NotBlank
	@DhtmlColumn(columnIndex = 0, headerName = "组织名称", coulumnType = DhtmlxBaseType.TREE)
	public String getGroupName() {
		return groupName;
	}

	/**
	 * Sets the group name.
	 *
	 * @param groupName the new group name
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * Gets the group code.
	 *
	 * @return the group code
	 */
	@Column(nullable = false)
	@NotBlank
	@DhtmlColumn(columnIndex = 1, headerName = "组织标识")
	public String getGroupCode() {
		return groupCode;
	}

	/**
	 * Sets the group code.
	 *
	 * @param groupCode the new group code
	 */
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	/**
	 * Gets the role ids.
	 *
	 * @return the role ids
	 */
	@Transient
	public String getRoleIds() {
		return CollectionMapperUtils.extractToString(roleEntities, "id", ",");
	}

	/**
	 * Gets the role entities.
	 *
	 * @return the role entities
	 */

	/**
	 * Gets the role entities.
	 *
	 * @return the role entities
	 */
	@ManyToMany
	// 中间表定义,表名采用默认命名规则
	@JoinTable(name = "SYS_GROUP_ROLE", joinColumns = { @JoinColumn(name = "GROUP_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	// Fecth策略定义
	@Fetch(FetchMode.SUBSELECT)
	// 集合按id排序.
	@OrderBy("id desc")
	// 集合中对象id的缓存.
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@DhtmlColumn(columnIndex = 2, headerName = "角色", coulumnType = DhtmlxBaseType.GRID, columnDataSets = RoleEntitiesColumnDataSet.class, columnId = "roles")
	public List<SysRoleEntity> getRoleEntities() {
		return roleEntities;
	}

	/**
	 * Sets the role entities.
	 *
	 * @param roleEntities the new role entities
	 */
	public void setRoleEntities(List<SysRoleEntity> roleEntities) {
		this.roleEntities = roleEntities;
	}

	/**
	 * Gets the sys user entities.
	 *
	 * @return the sys user entities
	 */

	/**
	 * Gets the sys user entities.
	 *
	 * @return the sys user entities
	 */
	@ManyToMany
	// 中间表定义,表名采用默认命名规则
	@JoinTable(name = "SYS_USER_GROUP", joinColumns = { @JoinColumn(name = "GROUP_ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
	// Fecth策略定义
	@Fetch(FetchMode.SUBSELECT)
	// 集合按id排序.
	@OrderBy("id desc")
	// 集合中对象id的缓存.
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<SysUserEntity> getSysUserEntities() {
		return sysUserEntities;
	}

	/**
	 * Sets the sys user entities.
	 *
	 * @param sysUserEntities the new sys user entities
	 */
	public void setSysUserEntities(List<SysUserEntity> sysUserEntities) {
		this.sysUserEntities = sysUserEntities;
	}

	/**
	 * Gets the parent id.
	 *
	 * @return the parent id
	 */
	@Column(insertable = false, updatable = false)
	public Long getParentId() {
		return parentId;
	}

	/**
	 * Sets the parent id.
	 *
	 * @param parentId the new parent id
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * Gets the parent sys group.
	 *
	 * @return the parent sys group
	 */
	@ManyToOne
	@JoinColumn(name = "parentId")
	public SysGroupEntity getParentSysGroup() {
		return parentSysGroup;
	}

	/**
	 * Sets the parent sys group.
	 *
	 * @param parentSysGroup the new parent sys group
	 */
	public void setParentSysGroup(SysGroupEntity parentSysGroup) {
		this.parentSysGroup = parentSysGroup;
	}

	/**
	 * Gets the child sys group.
	 *
	 * @return the child sys group
	 */

	/**
	 * Gets the child sys group.
	 *
	 * @return the child sys group
	 */
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "parentSysGroup")
	@OnDelete(action = OnDeleteAction.CASCADE)
	public List<SysGroupEntity> getChildSysGroup() {
		return childSysGroup;
	}

	/**
	 * Sets the child sys group.
	 *
	 * @param childSysGroup the new child sys group
	 */
	public void setChildSysGroup(List<SysGroupEntity> childSysGroup) {
		this.childSysGroup = childSysGroup;
	}

	/* (non-Javadoc)
	 * @see cn.com.rebirth.knowledge.commons.dhtmlx.entity.AbstractDhtmlxBaseEntity#toValue(cn.com.rebirth.commons.search.annotation.AbstractSearchProperty, cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlColumn)
	 */
	/**
	 * To value.
	 *
	 * @param abstractSearchProperty the abstract search property
	 * @param dhtmlColumn the dhtml column
	 * @return the object
	 */
	@Override
	protected Object toValue(AbstractSearchProperty abstractSearchProperty, DhtmlColumn dhtmlColumn) {
		Object v = super.toValue(abstractSearchProperty, dhtmlColumn);
		String fieldName = abstractSearchProperty.getFieldName();
		if (v == null) {
			v = "";
		}
		if ("roleEntities".equalsIgnoreCase(fieldName)) {
			v = getRoleIds();
		}
		String _v = ConvertUtils.convertObjectToObject(v, abstractSearchProperty.getRawClass());
		if ("groupName".equalsIgnoreCase(fieldName)) {
			return new Cell("folder.gif", _v);
		}
		return new Cell(_v);
	}

	/* (non-Javadoc)
	 * @see cn.com.rebirth.knowledge.commons.dhtmlx.entity.AbstractDhtmlxBaseEntity#getChildObject()
	 */
	@Transient
	@Override
	public Collection<? extends AbstractBaseEntity> getChildObject() {
		return getChildSysGroup();
	}

}
