/**
 * Copyright (c) 2005-2012-9-19 www.china-cti.com
 * Id: CircleTopicEntity.java,23:52:26
 * @author wuwei
 */
package cn.com.rebirth.knowledge.commons.entity.circle;

import java.util.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import cn.com.rebirth.knowledge.commons.dhtmlx.entity.*;
import cn.com.rebirth.knowledge.commons.entity.system.*;

// TODO: Auto-generated Javadoc
/**
 * The Class CircleTopicEntity.
 *  圈子话题
 * @author wuwei
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "CIRCLE_TOPIC")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CircleTopicEntity extends AbstractDhtmlxBaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6105719127479059586L;

	/**
	 * The Enum CircleTopicStatu.
	 *
	 * @author wuwei
	 */
	public static enum CircleTopicStatu {

		/** The unchecked. 未审核*/
		UNCHECKED,
		/** The checked. 审核通过*/
		CHECKED,
		/** The block. 话题锁定*/
		BLOCK,
		/** The delete. 删除*/
		DELETE;
	}

	/** The topic name.  话题名称*/
	private String topicName;

	/** The create date. 创建时间*/
	private Date createDate;

	/** The creater.  创建者*/
	private SysUserEntity creater;

	/** The circle entity. 所属圈子*/
	private CircleEntity circleEntity;

	/** The topic content. 话题内容*/
	private CircleTopicContentEntity topicContent;

	/** The topic reply entities.  话题回复*/
	private List<CircleTopicReplyEntity> topicReplyEntities;

	/** The visit count. 访问量*/
	private Long visitCount = 0l;

	/** The reply count. 回复量*/
	private Long replyCount = 0l;

	/** The sticky. 置顶*/
	private boolean sticky;

	/** The marrow. 精华*/
	private boolean marrow;

	/** The statu. 状态，默认为未审核*/
	private CircleTopicStatu statu = CircleTopicStatu.UNCHECKED;

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
	 * Gets the topic name.
	 *
	 * @return the topic name
	 */
	public String getTopicName() {
		return topicName;
	}

	/**
	 * Sets the topic name.
	 *
	 * @param topicName the new topic name
	 */
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	/**
	 * Gets the creates the date.
	 *
	 * @return the creates the date
	 */
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

	/**
	 * Gets the creater.
	 *
	 * @return the creater
	 */
	@ManyToOne()
	@JoinColumn(name = "CREATER_ID")
	public SysUserEntity getCreater() {
		return creater;
	}

	/**
	 * Sets the creater.
	 *
	 * @param creater the new creater
	 */
	public void setCreater(SysUserEntity creater) {
		this.creater = creater;
	}

	/**
	 * Gets the circle entity.
	 *
	 * @return the circle entity
	 */
	@ManyToOne
	@JoinColumn(name = "CIRCLE_ID")
	public CircleEntity getCircleEntity() {
		return circleEntity;
	}

	/**
	 * Sets the circle entity.
	 *
	 * @param circleEntity the new circle entity
	 */
	public void setCircleEntity(CircleEntity circleEntity) {
		this.circleEntity = circleEntity;
	}

	/**
	 * Checks if is sticky.
	 *
	 * @return true, if is sticky
	 */
	public boolean isSticky() {
		return sticky;
	}

	/**
	 * Sets the sticky.
	 *
	 * @param sticky the new sticky
	 */
	public void setSticky(boolean sticky) {
		this.sticky = sticky;
	}

	/**
	 * Checks if is marrow.
	 *
	 * @return true, if is marrow
	 */
	public boolean isMarrow() {
		return marrow;
	}

	/**
	 * Sets the marrow.
	 *
	 * @param marrow the new marrow
	 */
	public void setMarrow(boolean marrow) {
		this.marrow = marrow;
	}

	/**
	 * Gets the statu.
	 *
	 * @return the statu
	 */
	@Enumerated(EnumType.ORDINAL)
	public CircleTopicStatu getStatu() {
		return statu;
	}

	/**
	 * Sets the statu.
	 *
	 * @param statu the new statu
	 */
	public void setStatu(CircleTopicStatu statu) {
		this.statu = statu;
	}

	/**
	 * Gets the topic content.
	 *
	 * @return the topic content
	 */
	@OneToOne
	@JoinColumn(name = "CIRCLE_TOPIC_CONTENT_ID")
	public CircleTopicContentEntity getTopicContent() {
		return topicContent;
	}

	/**
	 * Sets the topic content.
	 *
	 * @param topicContent the new topic content
	 */
	public void setTopicContent(CircleTopicContentEntity topicContent) {
		this.topicContent = topicContent;
	}

	/**
	 * Gets the topic reply entities.
	 *
	 * @return the topic reply entities
	 */
	@OneToMany(mappedBy = "circleTopicEntity", fetch = FetchType.LAZY)
	public List<CircleTopicReplyEntity> getTopicReplyEntities() {
		return topicReplyEntities;
	}

	/**
	 * Sets the topic reply entities.
	 *
	 * @param topicReplyEntities the new topic reply entities
	 */
	public void setTopicReplyEntities(List<CircleTopicReplyEntity> topicReplyEntities) {
		this.topicReplyEntities = topicReplyEntities;
	}

	/**
	 * Gets the visit count.
	 *
	 * @return the visit count
	 */
	public Long getVisitCount() {
		return visitCount;
	}

	/**
	 * Sets the visit count.
	 *
	 * @param visitCount the new visit count
	 */
	public void setVisitCount(Long visitCount) {
		this.visitCount = visitCount;
	}

	/**
	 * Gets the reply count.
	 *
	 * @return the reply count
	 */
	public Long getReplyCount() {
		return replyCount;
	}

	/**
	 * Sets the reply count.
	 *
	 * @param replyCount the new reply count
	 */
	public void setReplyCount(Long replyCount) {
		this.replyCount = replyCount;
	}

}
