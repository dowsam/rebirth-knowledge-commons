/**
 * Copyright (c) 2005-2012-9-14 www.china-cti.com
 * Id: SimpleGroupEntity.java,9:39:34
 * @author wuwei
 */
package cn.com.rebirth.knowledge.commons.entity.system;

import java.io.*;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleGroupEntity.
 * 将数据 转化为JSON格式传至前台的一个简单类。
 * 只有ID和NAME两个属性
 * @author wuwei
 */
public class SimpleJsonEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3231294960574655409L;

	/** The id. */
	private Long id;

	/** The name. */
	private String name;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
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
}
