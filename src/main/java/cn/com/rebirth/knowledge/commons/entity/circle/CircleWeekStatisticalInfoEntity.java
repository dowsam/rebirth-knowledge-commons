/**
 * Copyright (c) 2005-2012-9-19 www.china-cti.com
 * Id: CircleWeekStatisticalInfo.java,10:23:44
 * @author wuwei
 */
package cn.com.rebirth.knowledge.commons.entity.circle;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import cn.com.rebirth.commons.entity.*;

// TODO: Auto-generated Javadoc
/**
 * The Class CircleWeekStatisticalInfo.
 * 一周内的统计信息
 * @author wuwei
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "CIRCLE_WEEK_STATC_INFO")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CircleWeekStatisticalInfoEntity extends BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4129782194559814426L;

	/** The circle topic entity. 话题*/
	private CircleTopicEntity circleTopicEntity;

	/** The visit count. 访问次数*/
	private Long visitCount;

	/** The reply count. 回复次数*/
	private Long replyCount;

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
