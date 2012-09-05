/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons OnlineSysUserEntity.java 2012-8-14 14:42:50 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.entity.system;

import java.util.List;

import javax.persistence.Id;

import cn.com.rebirth.commons.entity.SessionInformations;
import cn.com.rebirth.commons.utils.EncodeUtils;
import cn.com.rebirth.commons.utils.ShortUrlGenerator;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlColumn;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.Dhtmlx;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlxBaseType;
import cn.com.rebirth.knowledge.commons.entity.system.AbstractLogBaseEntity.CreateTimeColumnConverter;

/**
 * The Class OnlineSysUserEntity.
 *
 * @author l.xue.nong
 */
@Dhtmlx(createCheckAllColumn = false, isDataProcessor = true)
public class OnlineSysUserEntity extends SysUserEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4536239562203990635L;

	/** The session id. */
	private String sessionId;

	/** The current request url. */
	private String currentRequestUrl;

	/** The session informations. */
	private SessionInformations sessionInformations;

	/** The sys user entity. */
	private SysUserEntity sysUserEntity;
	/** The clinet ip. */
	private String clinetIp;
	/** The fail. */
	private volatile boolean fail = false;

	/** The error code. */
	private int errorCode = 3;

	/**
	 * Gets the session id.
	 *
	 * @return the session id
	 */
	@DhtmlColumn(columnIndex = 0, headerName = "Session ID")
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * Sets the session id.
	 *
	 * @param sessionId the new session id
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * Gets the current request url.
	 *
	 * @return the current request url
	 */
	@DhtmlColumn(columnIndex = 4, headerName = "当前请求的URL")
	public String getCurrentRequestUrl() {
		return currentRequestUrl;
	}

	/**
	 * Sets the current request url.
	 *
	 * @param currentRequestUrl the new current request url
	 */
	public void setCurrentRequestUrl(String currentRequestUrl) {
		this.currentRequestUrl = currentRequestUrl;
	}

	/* (non-Javadoc)
	 * @see cn.com.rebirth.knowledge.commons.entity.system.SysUserEntity#isDisabled()
	 */
	@Override
	public boolean isDisabled() {
		return super.isDisabled();
	}

	/* (non-Javadoc)
	 * @see cn.com.rebirth.knowledge.commons.entity.system.SysUserEntity#isLocked()
	 */
	@Override
	public boolean isLocked() {
		return super.isLocked();
	}

	/* (non-Javadoc)
	 * @see cn.com.rebirth.knowledge.commons.entity.system.SysUserEntity#getRoleList()
	 */
	@Override
	public List<SysRoleEntity> getRoleList() {
		return super.getRoleList();
	}

	/**
	 * Gets the session informations.
	 *
	 * @return the session informations
	 */
	@DhtmlColumn(columnIndex = 7, headerName = "登入时间", columnId = "sessionInformations.expirationDate", columnConverter = CreateTimeColumnConverter.class)
	public SessionInformations getSessionInformations() {
		return sessionInformations;
	}

	/**
	 * Sets the session informations.
	 *
	 * @param sessionInformations the new session informations
	 */
	public void setSessionInformations(SessionInformations sessionInformations) {
		this.sessionInformations = sessionInformations;
	}

	/**
	 * Checks if is fail.
	 *
	 * @return true, if is fail
	 */
	@DhtmlColumn(columnIndex = 5, headerName = "是否失效", coulumnType = DhtmlxBaseType.ACHECK)
	public boolean isFail() {
		return fail;
	}

	/**
	 * Sets the fail.
	 *
	 * @param fail the new fail
	 */
	public void setFail(boolean fail) {
		this.fail = fail;
	}

	/* (non-Javadoc)
	 * @see cn.com.rebirth.commons.entity.BaseEntity#getId()
	 */
	@Id
	@Override
	public Long getId() {
		return EncodeUtils.decodeBase62(ShortUrlGenerator.shortUrl(getSessionId()));
	}

	/**
	 * Gets the clinet ip.
	 *
	 * @return the clinet ip
	 */
	@DhtmlColumn(columnIndex = 6, headerName = "客户端IP")
	public String getClinetIp() {
		return clinetIp;
	}

	/**
	 * Sets the clinet ip.
	 *
	 * @param clinetIp the new clinet ip
	 */
	public void setClinetIp(String clinetIp) {
		this.clinetIp = clinetIp;
	}

	/**
	 * Gets the error code.
	 *
	 * @return the error code
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * Sets the error code.
	 *
	 * @param errorCode the new error code
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * Gets the sys user entity.
	 *
	 * @return the sys user entity
	 */
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

	@Override
	public List<SysGroupEntity> getGroupEntities() {
		return super.getGroupEntities();
	}

}
