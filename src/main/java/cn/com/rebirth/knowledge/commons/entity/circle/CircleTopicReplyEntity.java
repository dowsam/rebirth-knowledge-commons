/**
 * Copyright (c) 2005-2012-9-20 www.china-cti.com
 * Id: CircleTopicReplyEntity.java,14:39:58
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
 * The Class CircleTopicReply.
 * 话题回复
 * @author wuwei
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "CIRCLE_TOPIC_REPLY")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CircleTopicReplyEntity extends AbstractDhtmlxBaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4553765829124746206L;

	/** The reply user. 回复人*/
	private SysUserEntity replyUser;

	/** The reply date. 回复日期*/
	private Date replyDate;

	/** The circle topic entity. 所属话题*/
	private CircleTopicEntity circleTopicEntity;

	/** The reply content. 内容*/
	private byte[] replyContent;

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
	 * Gets the reply user.
	 *
	 * @return the reply user
	 */
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	public SysUserEntity getReplyUser() {
		return replyUser;
	}

	/**
	 * Sets the reply user.
	 *
	 * @param replyUser the new reply user
	 */
	public void setReplyUser(SysUserEntity replyUser) {
		this.replyUser = replyUser;
	}

	/**
	 * Gets the reply date.
	 *
	 * @return the reply date
	 */
	public Date getReplyDate() {
		return replyDate;
	}

	/**
	 * Sets the reply date.
	 *
	 * @param replyDate the new reply date
	 */
	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

	/**
	 * Gets the circle topic entity.
	 *
	 * @return the circle topic entity
	 */
	@ManyToOne
	@JoinColumn(name = "CIRCLE_TOPIC_ID")
	public CircleTopicEntity getCircleTopicEntity() {
		return circleTopicEntity;
	}

	/**
	 * Sets the circle topic entity.
	 *
	 * @param circleTopicEntity the new circle topic entity
	 */
	public void setCircleTopicEntity(CircleTopicEntity circleTopicEntity) {
		this.circleTopicEntity = circleTopicEntity;
	}

	/**
	 * Gets the reply content.
	 *
	 * @return the reply content
	 */
	public byte[] getReplyContent() {
		return replyContent;
	}

	/**
	 * Sets the reply content.
	 *
	 * @param replyContent the new reply content
	 */
	public void setReplyContent(byte[] replyContent) {
		this.replyContent = replyContent;
	}

	/**
	 * Gets the content str.
	 *
	 * @return the content str
	 */
	@Transient
	public String getContentStr() {
		return new String(replyContent);
	}

	/* (non-Javadoc)
	 * @see cn.com.rebirth.commons.entity.BaseEntity#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((circleTopicEntity == null) ? 0 : circleTopicEntity.hashCode());
		result = prime * result + Arrays.hashCode(replyContent);
		result = prime * result + ((replyDate == null) ? 0 : replyDate.hashCode());
		result = prime * result + ((replyUser == null) ? 0 : replyUser.hashCode());
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
		CircleTopicReplyEntity other = (CircleTopicReplyEntity) obj;
		if (circleTopicEntity == null) {
			if (other.circleTopicEntity != null)
				return false;
		} else if (!circleTopicEntity.equals(other.circleTopicEntity))
			return false;
		if (!Arrays.equals(replyContent, other.replyContent))
			return false;
		if (replyDate == null) {
			if (other.replyDate != null)
				return false;
		} else if (!replyDate.equals(other.replyDate))
			return false;
		if (replyUser == null) {
			if (other.replyUser != null)
				return false;
		} else if (!replyUser.equals(other.replyUser))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CircleTopicReplyEntity [replyUser=" + replyUser + ", replyDate=" + replyDate + ", circleTopicEntity="
				+ circleTopicEntity + ", replyContent=" + Arrays.toString(replyContent) + "]";
	}

}
