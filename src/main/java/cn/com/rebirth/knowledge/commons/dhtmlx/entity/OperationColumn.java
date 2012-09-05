/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons OperationColumn.java 2012-8-3 21:06:52 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class OperationColumn.
 *
 * @author l.xue.nong
 */
public class OperationColumn extends Column {

	/** The actions. */
	private List<Action> actions = new ArrayList<Action>();

	/** The on before menu render. */
	private String onBeforeMenuRender;

	/**
	 * Gets the on before menu render.
	 *
	 * @return the on before menu render
	 */
	public String getOnBeforeMenuRender() {
		return onBeforeMenuRender;
	}

	/**
	 * Sets the on before menu render.
	 *
	 * @param onBeforeMenuRender the new on before menu render
	 */
	public void setOnBeforeMenuRender(String onBeforeMenuRender) {
		this.onBeforeMenuRender = onBeforeMenuRender;
	}

	/**
	 * Instantiates a new operation column.
	 */
	public OperationColumn() {
		this.setId("_operation");
		this.setAlign("center");
		this.setHeader("操作");
		this.setType("opt");
		this.setFrozen(true);
		this.setWidth("6");
		this.setExportable(false);
	}

	/**
	 * Gets the actions.
	 *
	 * @return the actions
	 */
	public List<Action> getActions() {
		return actions;
	}

	/**
	 * Sets the actions.
	 *
	 * @param actions the new actions
	 */
	public void setActions(List<Action> actions) {
		this.actions = actions;
	}
}