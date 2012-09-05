/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons DataProcessor.java 2012-8-10 23:17:46 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx.entity;

import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * The Class DataProcessor.
 *
 * @author l.xue.nong
 */
public class DataProcessor extends BodyTagSupport {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4497650126070500103L;

	/** The action handler. */
	private String actionHandler;

	/** The transaction mode. */
	private String transactionMode = "POST";
	
	/** The update mode. */
	private String updateMode = "off";
	/** The enable data names. */
	private boolean enableDataNames = true;

	/** The on before update. */
	private String onBeforeUpdate;

	/** The on after update. */
	private String onAfterUpdate;

	/** The enabled. */
	private boolean enabled = false;

	/**
	 * Gets the action handler.
	 *
	 * @return the action handler
	 */
	public String getActionHandler() {
		return actionHandler;
	}

	/**
	 * Sets the action handler.
	 *
	 * @param actionHandler the new action handler
	 */
	public void setActionHandler(String actionHandler) {
		this.actionHandler = actionHandler;
	}

	/**
	 * Gets the transaction mode.
	 *
	 * @return the transaction mode
	 */
	public String getTransactionMode() {
		return transactionMode;
	}

	/**
	 * Sets the transaction mode.
	 *
	 * @param transactionMode the new transaction mode
	 */
	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}

	/**
	 * Checks if is enable data names.
	 *
	 * @return true, if is enable data names
	 */
	public boolean isEnableDataNames() {
		return enableDataNames;
	}

	/**
	 * Sets the enable data names.
	 *
	 * @param enableDataNames the new enable data names
	 */
	public void setEnableDataNames(boolean enableDataNames) {
		this.enableDataNames = enableDataNames;
	}

	/**
	 * Gets the on before update.
	 *
	 * @return the on before update
	 */
	public String getOnBeforeUpdate() {
		return onBeforeUpdate;
	}

	/**
	 * Sets the on before update.
	 *
	 * @param onBeforeUpdate the new on before update
	 */
	public void setOnBeforeUpdate(String onBeforeUpdate) {
		this.onBeforeUpdate = onBeforeUpdate;
	}

	/**
	 * Gets the on after update.
	 *
	 * @return the on after update
	 */
	public String getOnAfterUpdate() {
		return onAfterUpdate;
	}

	/**
	 * Sets the on after update.
	 *
	 * @param onAfterUpdate the new on after update
	 */
	public void setOnAfterUpdate(String onAfterUpdate) {
		this.onAfterUpdate = onAfterUpdate;
	}

	/**
	 * Checks if is enabled.
	 *
	 * @return true, if is enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Sets the enabled.
	 *
	 * @param enabled the new enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Gets the update mode.
	 *
	 * @return the update mode
	 */
	public String getUpdateMode() {
		return updateMode;
	}

	/**
	 * Sets the update mode.
	 *
	 * @param updateMode the new update mode
	 */
	public void setUpdateMode(String updateMode) {
		this.updateMode = updateMode;
	}

}
