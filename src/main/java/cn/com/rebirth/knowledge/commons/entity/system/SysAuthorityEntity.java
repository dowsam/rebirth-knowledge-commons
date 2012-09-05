/**
* Copyright (c) 2005-2011 www.china-cti.com
* Id: SysAuthority.java 2011-6-24 11:11:11 l.xue.nong$$
*/
package cn.com.rebirth.knowledge.commons.entity.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;

import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlColumn;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.Dhtmlx;
import cn.com.rebirth.knowledge.commons.dhtmlx.entity.AbstractDhtmlxBaseEntity;

/**
 * 权限
 * The Class SysAuthority.
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "SYS_AUTHORITY")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Dhtmlx(createCheckAllColumn = false, isDataProcessor = true)
public class SysAuthorityEntity extends AbstractDhtmlxBaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6218988858024773207L;

	/** The name. */
	@Column(nullable = false, unique = true)
	private String name;// 权限名称

	/** The display name. */
	@Column(nullable = false, unique = true)
	private String displayName;// 权限标志

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	@NotBlank
	@NotNull
	@DhtmlColumn(columnIndex = 0, headerName = "权限名称")
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
	 * Gets the display name.
	 *
	 * @return the display name
	 */
	@NotBlank
	@NotNull
	@DhtmlColumn(columnIndex = 1, headerName = "权限标志")
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Sets the display name.
	 *
	 * @param displayName the new display name
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	@Transient
	public boolean isChildTrem() {
		return false;
	}

}
