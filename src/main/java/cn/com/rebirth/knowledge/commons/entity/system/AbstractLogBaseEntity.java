/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons AbstractLogBaseEntity.java 2012-8-14 8:26:50 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.entity.system;

import java.util.Date;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import cn.com.rebirth.commons.RebirthContainer;
import cn.com.rebirth.commons.VersionFactory;
import cn.com.rebirth.commons.search.annotation.AbstractSearchProperty;
import cn.com.rebirth.commons.utils.DateUtils;
import cn.com.rebirth.commons.utils.IpUtils;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlColumn;
import cn.com.rebirth.knowledge.commons.dhtmlx.data.IColumnConverter;
import cn.com.rebirth.knowledge.commons.dhtmlx.entity.AbstractDhtmlxBaseEntity;

/**
 * The Class AbstractLogBaseEntity.
 *
 * @author l.xue.nong
 */
@MappedSuperclass
public abstract class AbstractLogBaseEntity extends AbstractDhtmlxBaseEntity {

	/**
	 * The Class CreateTimeColumnConverter.
	 *
	 * @author l.xue.nong
	 */
	public static class CreateTimeColumnConverter implements IColumnConverter {

		/**
		 * Convert.
		 *
		 * @param property the property
		 * @param dhtmlColumn the dhtml column
		 * @param dhtmlxBaseEntity the dhtmlx base entity
		 * @param converterValue the converter value
		 * @return the object
		 */
		@Override
		public Object convert(AbstractSearchProperty property, DhtmlColumn dhtmlColumn,
				AbstractDhtmlxBaseEntity dhtmlxBaseEntity, Object converterValue) {
			Date date = (Date) converterValue;
			if (date == null)
				return null;
			return DateUtils.formatDate(date, null);
		}

	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5609702653573586120L;

	/** The sys user entity. */
	private SysUserEntity sysUserEntity;//操作的用户

	/** The app ip. */
	private String appIp;//应用服务器的IP

	/** The app name. */
	private String appName;//应用的名称

	/** The create time. */
	private Date createTime;//创建时间

	/**
	 * Gets the sys user entity.
	 *
	 * @return the sys user entity
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	@DhtmlColumn(columnId = "sysUserEntity.loginName", columnIndex = 3, headerName = "用户")
	public SysUserEntity getSysUserEntity() {
		return sysUserEntity;
	}

	/**
	 * Sets the sys user entity.
	 *
	 * @param sysUserEntity the new sys user entity
	 */
	public void setSysUserEntity(SysUserEntity sysUserEntity) {
		this.sysUserEntity = sysUserEntity;
	}

	/**
	 * Gets the app ip.
	 *
	 * @return the app ip
	 */
	@DhtmlColumn(columnIndex = 2, headerName = "应用服务器IP")
	public String getAppIp() {
		return appIp == null ? appIp = IpUtils.getRealIp() : appIp;
	}

	/**
	 * Sets the app ip.
	 *
	 * @param appIp the new app ip
	 */
	public void setAppIp(String appIp) {
		this.appIp = appIp;
	}

	/**
	 * Gets the app name.
	 *
	 * @return the app name
	 */
	@DhtmlColumn(columnIndex = 1, headerName = "应用名称")
	public String getAppName() {
		return appName == null ? appName = RebirthContainer.getInstance().get(VersionFactory.class).currentVersion()
				.getModuleName() : appName;
	}

	/**
	 * Sets the app name.
	 *
	 * @param appName the new app name
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}

	/**
	 * Gets the creates the time.
	 *
	 * @return the creates the time
	 */

	/**
	 * Gets the creates the time.
	 *
	 * @return the creates the time
	 */
	@DhtmlColumn(columnIndex = 4, headerName = "创建时间", columnConverter = CreateTimeColumnConverter.class)
	public Date getCreateTime() {
		return createTime == null ? createTime = DateUtils.getCurrentDateTime() : createTime;
	}

	/**
	 * Sets the creates the time.
	 *
	 * @param createTime the new creates the time
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

}
