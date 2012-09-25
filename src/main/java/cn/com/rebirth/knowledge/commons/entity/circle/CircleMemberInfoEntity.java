<<<<<<< HEAD
/**
 * Copyright (c) 2005-2012-9-24 www.china-cti.com
 * Id: CircleMemberInfoEntity.java,14:03:13
 * @author wuwei
 */
package cn.com.rebirth.knowledge.commons.entity.circle;

import java.util.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import cn.com.rebirth.commons.entity.*;
import cn.com.rebirth.knowledge.commons.entity.system.*;

// TODO: Auto-generated Javadoc
/**
 * The Class CircleMemberInfoEntity.
 *
 * @author wuwei
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "CIRCLE_MEMBER_INFO")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CircleMemberInfoEntity extends BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5620380411214455414L;

	/**
	 * The Enum MemberType.
	 *
	 * @author wuwei
	 */
	public static enum MemberType {

		/** The master. */
		MASTER("圈主"),
		/** The secondarymaster. */
		SECONDARYMASTER("副圈主"),
		/** The member. */
		MEMBER("成员");

		/** The name. */
		private String name;

		/**
		 * Instantiates a new member type.
		 *
		 * @param name the name
		 */
		private MemberType(String name) {
			this.name = name;
		}

		/**
		 * Gets the value.
		 *
		 * @return the value
		 */
		public String getValue() {
			return name;
		}
	}

	/** The sys user entity. */
	private SysUserEntity sysUserEntity;

	/** The circle entity. */
	private CircleEntity circleEntity;

	/** The member type. */
	private MemberType memberType;

	/** The topic count. */
	private Integer topicCount;

	/** The reply count. */
	private Integer replyCount;

	/** The last reply date. */
	private Date lastReplyDate;

	/** The marrow count. */
	private Integer marrowCount;

	/**
	 * Gets the sys user entity.
	 *
	 * @return the sys user entity
	 */
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	public SysUserEntity getSysUserEntity() {
		return sysUserEntity;
	}

	/**
	 * Sets the sys user entity.
	 *
	 * @param sysUserEntity the new sys user entity
	 */
	public void setSysUserEntity(SysUserEntity sysUserEntity) {
		this.sysUserEntity = sysUserEntity;
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
	 * Gets the member type.
	 *
	 * @return the member type
	 */
	public MemberType getMemberType() {
		return memberType;
	}

	/**
	 * Sets the member type.
	 *
	 * @param memberType the new member type
	 */
	public void setMemberType(MemberType memberType) {
		this.memberType = memberType;
	}

	/**
	 * Gets the topic count.
	 *
	 * @return the topic count
	 */
	public Integer getTopicCount() {
		return topicCount;
	}

	/**
	 * Sets the topic count.
	 *
	 * @param topicCount the new topic count
	 */
	public void setTopicCount(Integer topicCount) {
		this.topicCount = topicCount;
	}

	/**
	 * Gets the reply count.
	 *
	 * @return the reply count
	 */
	public Integer getReplyCount() {
		return replyCount;
	}

	/**
	 * Sets the reply count.
	 *
	 * @param replyCount the new reply count
	 */
	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	/**
	 * Gets the last reply date.
	 *
	 * @return the last reply date
	 */
	public Date getLastReplyDate() {
		return lastReplyDate;
	}

	/**
	 * Sets the last reply date.
	 *
	 * @param lastReplyDate the new last reply date
	 */
	public void setLastReplyDate(Date lastReplyDate) {
		this.lastReplyDate = lastReplyDate;
	}

	/**
	 * Gets the marrow count.
	 *
	 * @return the marrow count
	 */
	public Integer getMarrowCount() {
		return marrowCount;
	}

	/**
	 * Sets the marrow count.
	 *
	 * @param marrowCount the new marrow count
	 */
	public void setMarrowCount(Integer marrowCount) {
		this.marrowCount = marrowCount;
	}
}
=======
/**
 * Copyright (c) 2005-2012-9-24 www.china-cti.com
 * Id: CircleMemberInfoEntity.java,14:03:13
 * @author wuwei
 */
package cn.com.rebirth.knowledge.commons.entity.circle;

import java.util.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import cn.com.rebirth.commons.entity.*;
import cn.com.rebirth.knowledge.commons.entity.system.*;

// TODO: Auto-generated Javadoc
/**
 * The Class CircleMemberInfoEntity.
 *
 * @author wuwei
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "CIRCLE_MEMBER_INFO")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CircleMemberInfoEntity extends BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5620380411214455414L;

	/**
	 * The Enum MemberType.
	 *
	 * @author wuwei
	 */
	public static enum MemberType {

		/** The master. */
		MASTER("圈主"),
		/** The secondarymaster. */
		SECONDARYMASTER("副圈主"),
		/** The member. */
		MEMBER("成员");

		/** The name. */
		private String name;

		/**
		 * Instantiates a new member type.
		 *
		 * @param name the name
		 */
		private MemberType(String name) {
			this.name = name;
		}

		/**
		 * Gets the value.
		 *
		 * @return the value
		 */
		public String getValue() {
			return name;
		}
	}

	/** The sys user entity. */
	private SysUserEntity sysUserEntity;

	/** The circle entity. */
	private CircleEntity circleEntity;

	/** The member type. */
	private MemberType memberType;

	/** The topic count. */
	private Integer topicCount;

	/** The reply count. */
	private Integer replyCount;

	/** The last reply date. */
	private Date lastReplyDate;

	/** The marrow count. */
	private Integer marrowCount;

	/**
	 * Gets the sys user entity.
	 *
	 * @return the sys user entity
	 */
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	public SysUserEntity getSysUserEntity() {
		return sysUserEntity;
	}

	/**
	 * Sets the sys user entity.
	 *
	 * @param sysUserEntity the new sys user entity
	 */
	public void setSysUserEntity(SysUserEntity sysUserEntity) {
		this.sysUserEntity = sysUserEntity;
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
	 * Gets the member type.
	 *
	 * @return the member type
	 */
	public MemberType getMemberType() {
		return memberType;
	}

	/**
	 * Sets the member type.
	 *
	 * @param memberType the new member type
	 */
	public void setMemberType(MemberType memberType) {
		this.memberType = memberType;
	}

	/**
	 * Gets the topic count.
	 *
	 * @return the topic count
	 */
	public Integer getTopicCount() {
		return topicCount;
	}

	/**
	 * Sets the topic count.
	 *
	 * @param topicCount the new topic count
	 */
	public void setTopicCount(Integer topicCount) {
		this.topicCount = topicCount;
	}

	/**
	 * Gets the reply count.
	 *
	 * @return the reply count
	 */
	public Integer getReplyCount() {
		return replyCount;
	}

	/**
	 * Sets the reply count.
	 *
	 * @param replyCount the new reply count
	 */
	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	/**
	 * Gets the last reply date.
	 *
	 * @return the last reply date
	 */
	public Date getLastReplyDate() {
		return lastReplyDate;
	}

	/**
	 * Sets the last reply date.
	 *
	 * @param lastReplyDate the new last reply date
	 */
	public void setLastReplyDate(Date lastReplyDate) {
		this.lastReplyDate = lastReplyDate;
	}

	/**
	 * Gets the marrow count.
	 *
	 * @return the marrow count
	 */
	public Integer getMarrowCount() {
		return marrowCount;
	}

	/**
	 * Sets the marrow count.
	 *
	 * @param marrowCount the new marrow count
	 */
	public void setMarrowCount(Integer marrowCount) {
		this.marrowCount = marrowCount;
	}
}
>>>>>>> e686b726f78555bc1c19fe0b5c4dcebf854df322
