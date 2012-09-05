/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons SysUserEntity.java 2012-7-21 12:57:56 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.entity.system;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import cn.com.rebirth.commons.search.annotation.AbstractSearchProperty;
import cn.com.rebirth.commons.utils.CollectionMapperUtils;
import cn.com.rebirth.knowledge.commons.dhtmlx.ColumnDataSets;
import cn.com.rebirth.knowledge.commons.dhtmlx.ColumnDataSets.CListColumnDataSets;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlColumn;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.Dhtmlx;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlxBaseType;
import cn.com.rebirth.knowledge.commons.dhtmlx.data.IColumnConverter;
import cn.com.rebirth.knowledge.commons.dhtmlx.entity.AbstractDhtmlxBaseEntity;
import cn.com.rebirth.knowledge.commons.dhtmlx.entity.Grid;
import cn.com.rebirth.knowledge.commons.dhtmlx.entity.Tree;

import com.google.common.collect.Lists;

/**
 * The Class SysUserEntity.
 *
 * @author l.xue.nong
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "SYS_USER")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Dhtmlx(createCheckAllColumn = false, isDataProcessor = true)
public class SysUserEntity extends AbstractDhtmlxBaseEntity {

	/**
	 * The Class RoleListColumnConverter.
	 *
	 * @author l.xue.nong
	 */
	public static class RoleListColumnConverter implements IColumnConverter {

		/**
		 * Convert.
		 *
		 * @param property the property
		 * @param dhtmlColumn the dhtml column
		 * @param dhtmlxBaseEntity the dhtmlx base entity
		 * @param converterValue the converter value
		 * @return the object
		 */
		@SuppressWarnings("unchecked")
		@Override
		public Object convert(AbstractSearchProperty property, DhtmlColumn dhtmlColumn,
				AbstractDhtmlxBaseEntity dhtmlxBaseEntity, Object converterValue) {
			if (converterValue != null && converterValue instanceof List) {
				List<SysRoleEntity> roleEntities = (List<SysRoleEntity>) converterValue;
				return CollectionMapperUtils.extractToString(roleEntities, "id", ",");
			}
			return converterValue;
		}

	}

	/**
	 * The Class RoleListDataSets.
	 *
	 * @author l.xue.nong
	 */
	public static class RoleListDataSets extends CListColumnDataSets implements ColumnDataSets {

		/** The role entities. */
		private final List<SysRoleEntity> roleEntities;

		/**
		 * Instantiates a new role list data sets.
		 *
		 * @param roleEntities the role entities
		 */
		public RoleListDataSets(List<SysRoleEntity> roleEntities) {
			super();
			this.roleEntities = roleEntities;
		}

		/**
		 * Gets the keys.
		 *
		 * @return the keys
		 */
		@Override
		protected String getKeys() {
			return CollectionMapperUtils.extractToString(roleEntities, "id", "\",\"");
		}

		/**
		 * Gets the names.
		 *
		 * @return the names
		 */
		@Override
		protected String getNames() {
			return CollectionMapperUtils.extractToString(roleEntities, "dispName", "\",\"");
		}

	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5657519373742623006L;

	/** The login name. */
	private String loginName;// 登入的工号

	/** The pass word. */
	private String passWord;// 密码

	/** The user name. */
	private String userName;// 名称

	/** The email. */
	private String email;// 邮箱

	/** The role list. */
	private List<SysRoleEntity> roleList = Lists.newArrayList();

	/** The remember me. */
	private boolean rememberMe;

	/** The disabled. */
	private boolean disabled = false;

	/** The locked. */
	private boolean locked = false;

	/** The manner. */
	private int manner = 0;

	/** The group entities. */
	private List<SysGroupEntity> groupEntities = Lists.newArrayList();

	/**
	 * Gets the role list name.
	 *
	 * @return the role list name
	 */
	@Transient
	public String getRoleListName() {
		return CollectionMapperUtils.extractToString(roleList, "name", ",");
	}

	/**
	 * Gets the role list.
	 *
	 * @return the role list
	 */
	@ManyToMany
	// 中间表定义,表名采用默认命名规则
	@JoinTable(name = "SYS_USER_ROLE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	// Fecth策略定义
	@Fetch(FetchMode.SUBSELECT)
	// 集合按id排序.
	@OrderBy("id desc")
	// 集合中对象id的缓存.
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@DhtmlColumn(columnIndex = 3, headerName = "角色", sortable = false, coulumnType = DhtmlxBaseType.CLIST, columnId = "roles", columnConverter = RoleListColumnConverter.class, columnDataSets = RoleListDataSets.class)
	public List<SysRoleEntity> getRoleList() {
		return roleList;
	}

	/**
	 * Sets the role list.
	 *
	 * @param roleList the new role list
	 */
	public void setRoleList(List<SysRoleEntity> roleList) {
		this.roleList = roleList;
	}

	/**
	 * Gets the login name.
	 *
	 * @return the login name
	 */
	@NotBlank(message = "{cn.com.rebirth.knowledge.commons.entity.system.SysUserEntity.loginName.message}")
	@DhtmlColumn(columnIndex = 0, headerName = "登入名")
	@Column(nullable = false, unique = true)
	public String getLoginName() {
		return loginName;
	}

	/**
	 * Sets the login name.
	 *
	 * @param loginName the new login name
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * Gets the pass word.
	 *
	 * @return the pass word
	 */
	public String getPassWord() {
		return passWord;
	}

	/**
	 * Sets the pass word.
	 *
	 * @param passWord the new pass word
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	@DhtmlColumn(columnIndex = 1, headerName = "姓名")
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	@DhtmlColumn(columnIndex = 2, headerName = "邮箱")
	@Email
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * Checks if is remember me.
	 *
	 * @return true, if is remember me
	 */
	@Transient
	public boolean isRememberMe() {
		return rememberMe;
	}

	/**
	 * Sets the remember me.
	 *
	 * @param rememberMe the new remember me
	 */
	@Transient
	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

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
		return false;
	}

	/**
	 * Checks if is disabled.
	 *
	 * @return true, if is disabled
	 */
	@DhtmlColumn(columnIndex = 5, headerName = "是否禁用", coulumnType = DhtmlxBaseType.ACHECK)
	public boolean isDisabled() {
		return disabled;
	}

	/**
	 * Sets the disabled.
	 *
	 * @param disabled the new disabled
	 */
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	/**
	 * Checks if is locked.
	 *
	 * @return true, if is locked
	 */
	@DhtmlColumn(columnIndex = 6, headerName = "是否锁定", coulumnType = DhtmlxBaseType.ACHECK)
	public boolean isLocked() {
		return locked;
	}

	/**
	 * Sets the locked.
	 *
	 * @param locked the new locked
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	/**
	 * Gets the manner.
	 *
	 * @return the manner
	 */
	@Transient
	public int getManner() {
		return manner;
	}

	/**
	 * Sets the manner.
	 *
	 * @param manner the new manner
	 */
	public void setManner(int manner) {
		this.manner = manner;
	}

	/**
	 * Gets the group entities.
	 *
	 * @return the group entities
	 */
	@ManyToMany
	// 中间表定义,表名采用默认命名规则
	@JoinTable(name = "SYS_USER_GROUP", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "GROUP_ID") })
	// Fecth策略定义
	@Fetch(FetchMode.SUBSELECT)
	// 集合按id排序.
	@OrderBy("id desc")
	// 集合中对象id的缓存.
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@DhtmlColumn(columnIndex = 4, headerName = "组织", columnId = "groups", columnDataSets = GroupEntitiesColumn.class, coulumnType = DhtmlxBaseType.STREE, columnConverter = GroupEntitiesColumnConverter.class)
	public List<SysGroupEntity> getGroupEntities() {
		return groupEntities;
	}

	/**
	 * Sets the group entities.
	 *
	 * @param groupEntities the new group entities
	 */
	public void setGroupEntities(List<SysGroupEntity> groupEntities) {
		this.groupEntities = groupEntities;
	}

	/**
	 * The Class GroupEntitiesColumn.
	 *
	 * @author l.xue.nong
	 */
	public static class GroupEntitiesColumn implements ColumnDataSets {

		/* (non-Javadoc)
		 * @see cn.com.rebirth.knowledge.commons.dhtmlx.ColumnDataSets#bulidColumnData(int, cn.com.rebirth.knowledge.commons.dhtmlx.entity.Column, cn.com.rebirth.knowledge.commons.dhtmlx.entity.Grid, java.lang.StringBuilder)
		 */
		@Override
		public void bulidColumnData(int columnIndex, cn.com.rebirth.knowledge.commons.dhtmlx.entity.Column column,
				Grid grid, StringBuilder stringBuilder) {
			stringBuilder.append(grid.getId()).append(".setSubTree(").append(((Tree) grid.getParentGrid()).getId())
					.append(",").append(columnIndex).append(",").append("0").append(");\n");
		}

	}

	/**
	 * The Class GroupEntitiesColumnConverter.
	 *
	 * @author l.xue.nong
	 */
	public static class GroupEntitiesColumnConverter implements IColumnConverter {

		/* (non-Javadoc)
		 * @see cn.com.rebirth.knowledge.commons.dhtmlx.data.IColumnConverter#convert(cn.com.rebirth.commons.search.annotation.AbstractSearchProperty, cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlColumn, cn.com.rebirth.knowledge.commons.dhtmlx.entity.AbstractDhtmlxBaseEntity, java.lang.Object)
		 */
		@SuppressWarnings("unchecked")
		@Override
		public Object convert(AbstractSearchProperty property, DhtmlColumn dhtmlColumn,
				AbstractDhtmlxBaseEntity dhtmlxBaseEntity, Object converterValue) {
			if (converterValue != null && converterValue instanceof List) {
				List<SysGroupEntity> groupEntities = (List<SysGroupEntity>) converterValue;
				return CollectionMapperUtils.extractToString(groupEntities, "id", ",");
			}
			return converterValue;
		}

	}

}
