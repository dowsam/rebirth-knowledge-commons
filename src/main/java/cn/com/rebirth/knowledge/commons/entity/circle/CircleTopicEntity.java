/**
 * Copyright (c) 2005-2012-9-20 www.china-cti.com
 * Id: CircleTopicEntity.java,14:39:43
 * @author wuwei
 */
package cn.com.rebirth.knowledge.commons.entity.circle;

import java.util.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
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
	private boolean sticky = false;

	/** The marrow. 精华*/
	private boolean marrow = false;

	/** The statu. 状态，默认为未审核*/
	private CircleTopicStatu statu = CircleTopicStatu.UNCHECKED;

	private CircleTopicStatisticalEntity statisticalEntity;

	private CircleTopicVisitEntity circleTopicVisitEntity;

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
	@OneToOne(cascade = { CascadeType.ALL })
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
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "circleTopicEntity", fetch = FetchType.LAZY)
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

	/* (non-Javadoc)
	 * @see cn.com.rebirth.commons.entity.BaseEntity#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((circleEntity == null) ? 0 : circleEntity.hashCode());
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((creater == null) ? 0 : creater.hashCode());
		result = prime * result + (marrow ? 1231 : 1237);
		result = prime * result + ((replyCount == null) ? 0 : replyCount.hashCode());
		result = prime * result + ((statu == null) ? 0 : statu.hashCode());
		result = prime * result + (sticky ? 1231 : 1237);
		result = prime * result + ((topicContent == null) ? 0 : topicContent.hashCode());
		result = prime * result + ((topicName == null) ? 0 : topicName.hashCode());
		result = prime * result + ((topicReplyEntities == null) ? 0 : topicReplyEntities.hashCode());
		result = prime * result + ((visitCount == null) ? 0 : visitCount.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see cn.com.rebirth.commons.entity.BaseEntity#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CircleTopicEntity other = (CircleTopicEntity) obj;
		if (circleEntity == null) {
			if (other.circleEntity != null)
				return false;
		} else if (!circleEntity.equals(other.circleEntity))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (creater == null) {
			if (other.creater != null)
				return false;
		} else if (!creater.equals(other.creater))
			return false;
		if (marrow != other.marrow)
			return false;
		if (replyCount == null) {
			if (other.replyCount != null)
				return false;
		} else if (!replyCount.equals(other.replyCount))
			return false;
		if (statu != other.statu)
			return false;
		if (sticky != other.sticky)
			return false;
		if (topicContent == null) {
			if (other.topicContent != null)
				return false;
		} else if (!topicContent.equals(other.topicContent))
			return false;
		if (topicName == null) {
			if (other.topicName != null)
				return false;
		} else if (!topicName.equals(other.topicName))
			return false;
		if (topicReplyEntities == null) {
			if (other.topicReplyEntities != null)
				return false;
		} else if (!topicReplyEntities.equals(other.topicReplyEntities))
			return false;
		if (visitCount == null) {
			if (other.visitCount != null)
				return false;
		} else if (!visitCount.equals(other.visitCount))
			return false;
		return true;
	}

	@OneToOne(cascade = { CascadeType.ALL }, mappedBy = "topicEntity")
	public CircleTopicStatisticalEntity getStatisticalEntity() {
		return statisticalEntity;
	}

	public void setStatisticalEntity(CircleTopicStatisticalEntity statisticalEntity) {
		this.statisticalEntity = statisticalEntity;
	}

	@OneToOne(mappedBy = "circleTopicEntity")
	public CircleTopicVisitEntity getCircleTopicVisitEntity() {
		return circleTopicVisitEntity;
	}

	public void setCircleTopicVisitEntity(CircleTopicVisitEntity circleTopicVisitEntity) {
		this.circleTopicVisitEntity = circleTopicVisitEntity;
	}

}
