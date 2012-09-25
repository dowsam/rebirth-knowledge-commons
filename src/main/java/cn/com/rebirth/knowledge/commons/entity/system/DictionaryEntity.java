/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons DictionaryEntity.java 2012-9-3 10:37:18 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.entity.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import cn.com.rebirth.commons.entity.BaseEntity;

/**
 * 字典.
 *
 * @author l.xue.nong
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "SYS_DICTIONARY")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DictionaryEntity extends BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7502813081806522231L;

	/** The key. */
	private String key;//字典key

	/** The value. */
	private String value;//字典值

	/**
	 * Instantiates a new dictionary entity.
	 */
	public DictionaryEntity() {
		super();
	}

	/**
	 * Instantiates a new dictionary entity.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public DictionaryEntity(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	@Column(name = "code_key", unique = true)
	public String getKey() {
		return key;
	}

	/**
	 * Sets the key.
	 *
	 * @param key the new key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	@Column(name = "code_value")
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
