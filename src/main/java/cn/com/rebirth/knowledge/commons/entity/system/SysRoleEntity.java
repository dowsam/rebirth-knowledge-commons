/**
* Copyright (c) 2005-2011 www.china-cti.com
* Id: SysRole.java 2011-6-23 16:26:01 l.xue.nong$$
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
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import cn.com.rebirth.commons.search.annotation.AbstractSearchProperty;
import cn.com.rebirth.commons.utils.CollectionMapperUtils;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlColumn;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.Dhtmlx;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlxBaseType;
import cn.com.rebirth.knowledge.commons.dhtmlx.data.IColumnConverter;
import cn.com.rebirth.knowledge.commons.dhtmlx.entity.AbstractDhtmlxBaseEntity;
import cn.com.rebirth.knowledge.commons.entity.system.SysResourceEntity.AuthorityListColumnDataSets;

import com.google.common.collect.Lists;

/**
 * 角色
 * The Class SysRole.
 *
 * @author l.xue.nong
 */
@Entity
@Table(name = "SYS_ROLES")
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Dhtmlx(createCheckAllColumn = false, isDataProcessor = true)
public class SysRoleEntity extends AbstractDhtmlxBaseEntity {
	/**
	 * The Class AuthorityListColumnConverter.
	 *
	 * @author l.xue.nong
	 */
	public static class AuthorityListColumnConverter implements IColumnConverter {

		/* (non-Javadoc)
		 * @see cn.com.rebirth.knowledge.commons.dhtmlx.data.IColumnConverter#convert(cn.com.rebirth.commons.search.annotation.AbstractSearchProperty, cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlColumn, cn.com.rebirth.knowledge.commons.dhtmlx.entity.AbstractDhtmlxBaseEntity, java.lang.Object)
		 */
		@SuppressWarnings("unchecked")
		@Override
		public Object convert(AbstractSearchProperty property, DhtmlColumn dhtmlColumn,
				AbstractDhtmlxBaseEntity dhtmlxBaseEntity, Object converterValue) {
			if (converterValue != null && converterValue instanceof List) {
				List<SysAuthorityEntity> authorityEntities = (List<SysAuthorityEntity>) converterValue;
				return CollectionMapperUtils.extractToString(authorityEntities, "id", ",");
			}
			return converterValue;
		}

	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7480292663270054373L;

	/** The name. */
	@Column(nullable = false, unique = true)
	private String name;//角色名称

	/** The disp name. */
	private String dispName;//简称

	/** The authority list. */
	private List<SysAuthorityEntity> authorityList = Lists.newArrayList();

	/**
	 * Gets the authority list.
	 *
	 * @return the authority list
	 */
	@ManyToMany
	@JoinTable(name = "SYS_ROLES_AUTHORITIES", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "AUTHORITY_ID") })
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("id desc")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@DhtmlColumn(columnIndex = 2, headerName = "权限", columnId = "authorDisplayNames", sortable = false, columnDataSets = AuthorityListColumnDataSets.class, columnConverter = AuthorityListColumnConverter.class, coulumnType = DhtmlxBaseType.CLIST)
	public List<SysAuthorityEntity> getAuthorityList() {
		return authorityList;
	}

	/**
	 * Sets the authority list.
	 *
	 * @param authorityList the new authority list
	 */
	public void setAuthorityList(List<SysAuthorityEntity> authorityList) {
		this.authorityList = authorityList;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	@DhtmlColumn(columnIndex = 0, headerName = "角色名称")
	@NotBlank
	@NotEmpty
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
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Gets the disp name.
	 *
	 * @return the disp name
	 */
	@DhtmlColumn(columnIndex = 1, headerName = "简称")
	public String getDispName() {
		return dispName;
	}

	/**
	 * Sets the disp name.
	 *
	 * @param dispName the new disp name
	 */
	public void setDispName(String dispName) {
		this.dispName = dispName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/* (non-Javadoc)
	 * @see cn.com.rebirth.knowledge.commons.entity.AbstractBaseEntity#isChildTrem()
	 */
	@Override
	@Transient
	public boolean isChildTrem() {
		return false;
	}

}
