/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons Tree.java 2012-8-21 16:30:40 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx.entity;

import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * The Class Tree.
 *
 * @author l.xue.nong
 */
public class Tree extends BodyTagSupport {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1759874332646134588L;

	/** The id. */
	private String id;//对象的
	/** The container. */
	private String container;//div 容器

	/** The width. */
	private String width = "100%";//宽

	/** The height. */
	private String height = "100%";//高

	/** The root id. */
	private int rootId = 0;//父亲
	/** The skin. */
	private String skin;//样式

	/** The image path. */
	private String imagePath;//图片

	/** The data mode. */
	private String dataMode = "json";//数据模型

	/** The action handler. */
	private String actionHandler;//数据请求的远程地址

	/** The syn. */
	private boolean syn = true;//是否同步异步
	private boolean enableCheckBoxes = false;//是否创建checkBox

	/**
	 * Gets the skin.
	 *
	 * @return the skin
	 */
	public String getSkin() {
		return skin;
	}

	/**
	 * Sets the skin.
	 *
	 * @param skin the new skin
	 */
	public void setSkin(String skin) {
		this.skin = skin;
	}

	/**
	 * Gets the image path.
	 *
	 * @return the image path
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * Sets the image path.
	 *
	 * @param imagePath the new image path
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	/**
	 * Gets the data mode.
	 *
	 * @return the data mode
	 */
	public String getDataMode() {
		return dataMode;
	}

	/**
	 * Sets the data mode.
	 *
	 * @param dataMode the new data mode
	 */
	public void setDataMode(String dataMode) {
		this.dataMode = dataMode;
	}

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
	 * Gets the container.
	 *
	 * @return the container
	 */
	public String getContainer() {
		return container;
	}

	/**
	 * Sets the container.
	 *
	 * @param container the new container
	 */
	public void setContainer(String container) {
		this.container = container;
	}

	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * Sets the width.
	 *
	 * @param width the new width
	 */
	public void setWidth(String width) {
		this.width = width;
	}

	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * Sets the height.
	 *
	 * @param height the new height
	 */
	public void setHeight(String height) {
		this.height = height;
	}

	/**
	 * Gets the root id.
	 *
	 * @return the root id
	 */
	public int getRootId() {
		return rootId;
	}

	/**
	 * Sets the root id.
	 *
	 * @param rootId the new root id
	 */
	public void setRootId(int rootId) {
		this.rootId = rootId;
	}

	/**
	 * Checks if is syn.
	 *
	 * @return true, if is syn
	 */
	public boolean isSyn() {
		return syn;
	}

	/**
	 * Sets the syn.
	 *
	 * @param syn the new syn
	 */
	public void setSyn(boolean syn) {
		this.syn = syn;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#getId()
	 */
	public String getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#setId(java.lang.String)
	 */
	public void setId(String id) {
		this.id = id;
	}

	public boolean isEnableCheckBoxes() {
		return enableCheckBoxes;
	}

	public void setEnableCheckBoxes(boolean enableCheckBoxes) {
		this.enableCheckBoxes = enableCheckBoxes;
	}

}
