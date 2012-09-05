/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons TreeJsonInfo.java 2012-8-3 21:35:45 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.google.common.collect.Lists;

/**
 * The Class TreeJsonInfo.
 *
 * @author l.xue.nong
 */
public class TreeJsonInfo implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6744239676335184671L;

	/** The id. */
	private Object id;

	/** The text. */
	private Object text;

	/** The child. */
	private Object child;

	/** The item. */
	private List<TreeJsonInfo> item = Lists.newArrayList();

	/** The userdata. */
	private List<UserData> userdata = Lists.newArrayList();

	/**
	 * Adds the user datas.
	 *
	 * @param userData the user data
	 */
	public void addUserDatas(UserData userData) {
		if (!this.userdata.contains(userData)) {
			this.userdata.add(userData);
		}
	}

	/**
	 * Adds the item.
	 *
	 * @param info the info
	 */
	public void addItem(TreeJsonInfo info) {
		if (!this.item.contains(info)) {
			this.item.add(info);
		}
	}

	/**
	 * Gets the item.
	 *
	 * @return the item
	 */
	public List<TreeJsonInfo> getItem() {
		return item;
	}

	/**
	 * Sets the item.
	 *
	 * @param item the new item
	 */
	public void setItem(List<TreeJsonInfo> item) {
		this.item = item;
	}

	/**
	 * Gets the userdata.
	 *
	 * @return the userdata
	 */
	public List<UserData> getUserdata() {
		return userdata;
	}

	/**
	 * Sets the userdata.
	 *
	 * @param userdata the new userdata
	 */
	public void setUserdata(List<UserData> userdata) {
		this.userdata = userdata;
	}

	/**
	 * Instantiates a new tree json info.
	 */
	public TreeJsonInfo() {
		super();
	}

	/**
	 * Instantiates a new tree json info.
	 *
	 * @param id the id
	 * @param text the text
	 * @param child the child
	 */
	public TreeJsonInfo(Object id, Object text, Object child) {
		super();
		this.id = id;
		this.text = text;
		this.child = child;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Object getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Object id) {
		this.id = id;
	}

	/**
	 * Gets the text.
	 *
	 * @return the text
	 */
	public Object getText() {
		return text;
	}

	/**
	 * Sets the text.
	 *
	 * @param text the new text
	 */
	public void setText(Object text) {
		this.text = text;
	}

	/**
	 * Gets the child.
	 *
	 * @return the child
	 */
	public Object getChild() {
		return child;
	}

	/**
	 * Sets the child.
	 *
	 * @param child the new child
	 */
	public void setChild(Object child) {
		this.child = child;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof TreeJsonInfo)) {
			return false;
		}
		TreeJsonInfo tempInfo = (TreeJsonInfo) obj;
		return getId() == tempInfo.getId();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}