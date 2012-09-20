/**
 * Copyright (c) 2005-2012-9-12 www.china-cti.com
 * Id: TagEntity.java,16:45:41
 * @author wuwei
 */
package cn.com.rebirth.knowledge.commons.entity.study;

import java.util.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import cn.com.rebirth.knowledge.commons.dhtmlx.entity.*;

// TODO: Auto-generated Javadoc
/**
 * The Class TagEntity.
 *
 * @author wuwei
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "STUDY_TAG")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TagEntity extends AbstractDhtmlxBaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3451563896500828519L;

	/* (non-Javadoc)
	 * @see cn.com.rebirth.knowledge.commons.entity.AbstractBaseEntity#isChildTrem()
	 */
	@Override
	@Transient
	public boolean isChildTrem() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * The Enum TagEntityType.
	 *
	 * @author wuwei
	 */
	public static enum TagEntityType {

		/** The systag. */
		SYSTAG(),
		/** The usertag. */
		USERTAG(),
		/** The doctag. */
		DOCTAG();
	}

	/** The tag name. */
	private String tagName;

	/** The create date. */
	private Date createDate;

	private List<TagBelongUserEntity> belongEntities;

	/**
	 * Gets the tag name.
	 *
	 * @return the tag name
	 */
	@Column(name = "TAG_NAME")
	public String getTagName() {
		return tagName;
	}

	/**
	 * Sets the tag name.
	 *
	 * @param tagName the new tag name
	 */
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	/**
	 * Gets the creates the date.
	 *
	 * @return the creates the date
	 */
	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Sets the creates the date.
	 *
	 * @param createDate the new creates the date
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@OneToMany(cascade = { CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY, mappedBy = "tag")
	public List<TagBelongUserEntity> getBelongEntities() {
		return belongEntities;
	}

	public void setBelongEntities(List<TagBelongUserEntity> belongEntities) {
		this.belongEntities = belongEntities;
	}

}
