/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons UserData.java 2012-8-3 21:35:45 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx;

import java.io.Serializable;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The Class UserData.
 *
 * @author l.xue.nong
 */
public class UserData implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 151433570147958419L;

	/** The name. */
	private Object name;

	/** The content. */
	private Object content;

	/**
	 * Instantiates a new user data.
	 */
	public UserData() {
		super();
	}

	/**
	 * Instantiates a new user data.
	 *
	 * @param name the name
	 * @param content the content
	 */
	public UserData(Object name, Object content) {
		super();
		this.name = name;
		this.content = content;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public Object getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(Object name) {
		this.name = name;
	}

	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public Object getContent() {
		return content;
	}

	/**
	 * Sets the content.
	 *
	 * @param content the new content
	 */
	public void setContent(Object content) {
		this.content = content;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof UserData)) {
			return false;
		}
		UserData tempData = (UserData) obj;
		return getName().equals(tempData.getName());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
