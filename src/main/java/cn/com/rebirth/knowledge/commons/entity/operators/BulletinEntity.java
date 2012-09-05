/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons BulletinEntity.java 2012-9-3 10:32:53 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.entity.operators;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;

import cn.com.rebirth.knowledge.commons.entity.AbstractOperatingEntity;
import cn.com.rebirth.knowledge.commons.entity.system.DictionaryEntity;

/**
 * 公告.
 *
 * @author l.xue.nong
 */
@Entity
@Table(name = "BULLETIN")
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BulletinEntity extends AbstractOperatingEntity {

	/**
	 * 公告的状态.
	 *
	 * @author l.xue.nong
	 */
	public static enum BulletinStatus {

		/** 提交. */
		commit(),
		/** 保存. */
		save(),
		/** 删除. */
		delete(),
		/** 过期. */
		expired(),
		/** 未知. */
		unknown();
	}

	/**
	 * 公告重要程度.
	 *
	 * @author l.xue.nong
	 */
	public static enum BulletionDegree {

		/** 紧急. */
		urgent(),
		/** 重要. */
		important(),
		/** 普通. */
		ordinary();
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1141217076726425637L;

	/** The title. */
	private String title;//标题

	/** The content. */
	private byte[] content;//内容

	/** The dictionary entity. */
	private DictionaryEntity dictionaryEntity;//公告的类别

	/** The bulletin status. */
	private BulletinStatus bulletinStatus = BulletinStatus.unknown;//公告的状态，默认未知

	/** The begin time. */
	private Date beginTime;//开始时间

	/** The end time. */
	private Date endTime;//结束时间

	/** The bulletion degree. */
	private BulletionDegree bulletionDegree = BulletionDegree.ordinary;//公告紧急程度，默认普通

	/* (non-Javadoc)
	 * @see cn.com.rebirth.knowledge.commons.entity.AbstractBaseEntity#isChildTrem()
	 */
	@Transient
	@Override
	public boolean isChildTrem() {
		return false;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	@NotBlank
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	@Lob
	@Basic(fetch = FetchType.LAZY)
	public byte[] getContent() {
		return content;
	}

	/**
	 * Sets the content.
	 *
	 * @param content the new content
	 */
	public void setContent(byte[] content) {
		this.content = content;
	}

	/**
	 * Gets the dictionary entity.
	 *
	 * @return the dictionary entity
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dic_id")
	public DictionaryEntity getDictionaryEntity() {
		return dictionaryEntity;
	}

	/**
	 * Sets the dictionary entity.
	 *
	 * @param dictionaryEntity the new dictionary entity
	 */
	public void setDictionaryEntity(DictionaryEntity dictionaryEntity) {
		this.dictionaryEntity = dictionaryEntity;
	}

	/**
	 * Gets the bulletin status.
	 *
	 * @return the bulletin status
	 */
	@Enumerated
	public BulletinStatus getBulletinStatus() {
		return bulletinStatus;
	}

	/**
	 * Sets the bulletin status.
	 *
	 * @param bulletinStatus the new bulletin status
	 */
	public void setBulletinStatus(BulletinStatus bulletinStatus) {
		this.bulletinStatus = bulletinStatus;
	}

	/**
	 * Gets the begin time.
	 *
	 * @return the begin time
	 */
	public Date getBeginTime() {
		return beginTime;
	}

	/**
	 * Sets the begin time.
	 *
	 * @param beginTime the new begin time
	 */
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	/**
	 * Gets the end time.
	 *
	 * @return the end time
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * Sets the end time.
	 *
	 * @param endTime the new end time
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * Gets the bulletion degree.
	 *
	 * @return the bulletion degree
	 */
	@Enumerated
	public BulletionDegree getBulletionDegree() {
		return bulletionDegree;
	}

	/**
	 * Sets the bulletion degree.
	 *
	 * @param bulletionDegree the new bulletion degree
	 */
	public void setBulletionDegree(BulletionDegree bulletionDegree) {
		this.bulletionDegree = bulletionDegree;
	}

}
