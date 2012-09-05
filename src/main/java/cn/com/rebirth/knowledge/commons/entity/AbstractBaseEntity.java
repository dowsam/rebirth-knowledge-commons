/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons AbstractBaseEntity.java 2012-7-19 15:35:52 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.entity;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

import cn.com.rebirth.commons.entity.BaseEntity;

/**
 * The Class AbstractBaseEntity.
 *
 * @author l.xue.nong
 */
public abstract class AbstractBaseEntity extends BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5047511240163076893L;

	/** The nativeeditor_status. */
	private String nativeeditor_status;

	/** The gr_id. */
	private Long gr_id;

	/** The gr_pid. */
	private Long gr_pid;

	/** The tr_id. */
	private Long tr_id;

	/** The tr_pid. */
	private Long tr_pid;

	/**
	 * Gets the dhtmlx json or xml object.
	 *
	 * @return the dhtmlx json or xml object
	 */
	public abstract Collection<Object> getDhtmlxJsonOrXmlObject();

	/**
	 * Gets the child object.
	 *
	 * @return the child object
	 */
	public abstract Collection<? extends AbstractBaseEntity> getChildObject();

	/**
	 * Checks if is child trem.
	 *
	 * @return true, if is child trem
	 */
	public abstract boolean isChildTrem();

	/**
	 * Gets the dhtmlx value.
	 *
	 * @return the dhtmlx value
	 */
	public String getDhtmlxValue() {
		Collection<Object> dhList = getDhtmlxJsonOrXmlObject();
		return StringUtils.join(dhList, ",");
	}

	/**
	 * Gets the nativeeditor_status.
	 *
	 * @return the nativeeditor_status
	 */
	public String getNativeeditor_status() {
		return nativeeditor_status;
	}

	/**
	 * Sets the nativeeditor_status.
	 *
	 * @param nativeeditor_status the new nativeeditor_status
	 */
	public void setNativeeditor_status(String nativeeditor_status) {
		this.nativeeditor_status = nativeeditor_status;
	}

	/**
	 * Gets the gr_id.
	 *
	 * @return the gr_id
	 */
	public Long getGr_id() {
		return gr_id;
	}

	/**
	 * Sets the gr_id.
	 *
	 * @param gr_id the new gr_id
	 */
	public void setGr_id(Long gr_id) {
		this.gr_id = gr_id;
	}

	/**
	 * Gets the gr_pid.
	 *
	 * @return the gr_pid
	 */
	public Long getGr_pid() {
		return gr_pid;
	}

	/**
	 * Sets the gr_pid.
	 *
	 * @param gr_pid the new gr_pid
	 */
	public void setGr_pid(Long gr_pid) {
		this.gr_pid = gr_pid;
	}

	/**
	 * Gets the tr_id.
	 *
	 * @return the tr_id
	 */
	public Long getTr_id() {
		return tr_id;
	}

	/**
	 * Sets the tr_id.
	 *
	 * @param tr_id the new tr_id
	 */
	public void setTr_id(Long tr_id) {
		this.tr_id = tr_id;
	}

	/**
	 * Gets the tr_pid.
	 *
	 * @return the tr_pid
	 */
	public Long getTr_pid() {
		return tr_pid;
	}

	/**
	 * Sets the tr_pid.
	 *
	 * @param tr_pid the new tr_pid
	 */
	public void setTr_pid(Long tr_pid) {
		this.tr_pid = tr_pid;
	}

}
