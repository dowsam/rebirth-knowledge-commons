/**
 * Copyright (c) 2005-2012-9-21 www.china-cti.com
 * Id: CircleTopicStatisticalEntity.java,11:16:21
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
 * The Class CircleStatisticalInfoEntity.
 * 话题统计信息
 * @author wuwei
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "CIRCLE_TOPIC_STATC_INFO")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CircleTopicStatisticalEntity extends BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4901495917790091286L;

	/** The topic entity. */
	private CircleTopicEntity topicEntity;

	/** The total visit count. */
	private Long totalVisitCount;

	/** The total reply count. */
	private Long totalReplyCount;

	/** The week visit count. */
	private Long weekVisitCount;

	/** The week reply count. */
	private Long weekReplyCount;

	/** The day visit count. */
	private Long dayVisitCount;

	/** The day reply count. */
	private Long dayReplyCount;

	/** The last reply date. */
	private Date lastReplyDate;

	/** The last reply user. */
	private SysUserEntity lastReplyUser;

	/**
	 * Gets the topic entity.
	 *
	 * @return the topic entity
	 */
	@OneToOne
	@JoinColumn(name = "CIRCLE_TOPIC_ID")
	public CircleTopicEntity getTopicEntity() {
		return topicEntity;
	}

	/**
	 * Sets the topic entity.
	 *
	 * @param topicEntity the new topic entity
	 */
	public void setTopicEntity(CircleTopicEntity topicEntity) {
		this.topicEntity = topicEntity;
	}

	/**
	 * Gets the total visit count.
	 *
	 * @return the total visit count
	 */
	public Long getTotalVisitCount() {
		return totalVisitCount;
	}

	/**
	 * Sets the total visit count.
	 *
	 * @param totalVisitCount the new total visit count
	 */
	public void setTotalVisitCount(Long totalVisitCount) {
		this.totalVisitCount = totalVisitCount;
	}

	/**
	 * Gets the total reply count.
	 *
	 * @return the total reply count
	 */
	public Long getTotalReplyCount() {
		return totalReplyCount;
	}

	/**
	 * Sets the total reply count.
	 *
	 * @param totalReplyCount the new total reply count
	 */
	public void setTotalReplyCount(Long totalReplyCount) {
		this.totalReplyCount = totalReplyCount;
	}

	/**
	 * Gets the week visit count.
	 *
	 * @return the week visit count
	 */
	public Long getWeekVisitCount() {
		return weekVisitCount;
	}

	/**
	 * Sets the week visit count.
	 *
	 * @param weekVisitCount the new week visit count
	 */
	public void setWeekVisitCount(Long weekVisitCount) {
		this.weekVisitCount = weekVisitCount;
	}

	/**
	 * Gets the week reply count.
	 *
	 * @return the week reply count
	 */
	public Long getWeekReplyCount() {
		return weekReplyCount;
	}

	/**
	 * Sets the week reply count.
	 *
	 * @param weekReplyCount the new week reply count
	 */
	public void setWeekReplyCount(Long weekReplyCount) {
		this.weekReplyCount = weekReplyCount;
	}

	/**
	 * Gets the day visit count.
	 *
	 * @return the day visit count
	 */
	public Long getDayVisitCount() {
		return dayVisitCount;
	}

	/**
	 * Sets the day visit count.
	 *
	 * @param dayVisitCount the new day visit count
	 */
	public void setDayVisitCount(Long dayVisitCount) {
		this.dayVisitCount = dayVisitCount;
	}

	/**
	 * Gets the day reply count.
	 *
	 * @return the day reply count
	 */
	public Long getDayReplyCount() {
		return dayReplyCount;
	}

	/**
	 * Sets the day reply count.
	 *
	 * @param dayReplyCount the new day reply count
	 */
	public void setDayReplyCount(Long dayReplyCount) {
		this.dayReplyCount = dayReplyCount;
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
	 * Gets the last reply user.
	 *
	 * @return the last reply user
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LAST_REPLY_USER_ID")
	public SysUserEntity getLastReplyUser() {
		return lastReplyUser;
	}

	/**
	 * Sets the last reply user.
	 *
	 * @param lastReplyUser the new last reply user
	 */
	public void setLastReplyUser(SysUserEntity lastReplyUser) {
		this.lastReplyUser = lastReplyUser;
	}
}
