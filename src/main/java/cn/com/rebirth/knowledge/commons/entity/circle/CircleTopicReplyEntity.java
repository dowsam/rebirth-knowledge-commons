/**
 * Copyright (c) 2005-2012-9-19 www.china-cti.com
 * Id: CircleTopicReplyEntity.java,14:17:03
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

}
