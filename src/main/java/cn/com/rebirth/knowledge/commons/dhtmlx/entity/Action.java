/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons Action.java 2012-8-3 21:06:52 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx.entity;

/**
 * The Class Action.
 *
 * @author l.xue.nong
 */
public class Action {

	/** The id. */
	private String id;

	/** The icon. */
	private String icon;

	/** The label. */
	private String label;

	/** The on click. */
	private String onClick;

	/** The visible. */
	private boolean visible = true;

	/**
	 * Checks if is visible.
	 *
	 * @return true, if is visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * Sets the visible.
	 *
	 * @param visible the new visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the icon.
	 *
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * Sets the icon.
	 *
	 * @param icon the new icon
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the label.
	 *
	 * @param label the new label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Gets the on click.
	 *
	 * @return the on click
	 */
	public String getOnClick() {
		return onClick;
	}

	/**
	 * Sets the on click.
	 *
	 * @param onClick the new on click
	 */
	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}
}