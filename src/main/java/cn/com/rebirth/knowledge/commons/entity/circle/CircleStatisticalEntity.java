/**
 * Copyright (c) 2005-2012-9-20 www.china-cti.com
 * Id: CircleStatisticalEntity.java,17:17:25
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
 * The Class CircleStatisticalEntity.
 * 圈子统计信息
 * @author wuwei
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "CIRCLE_STATC_INFO")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CircleStatisticalEntity extends BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1637072887057370568L;

	/** The circle entity. */
	private CircleEntity circleEntity;

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

	/**
	 * Gets the circle entity.
	 *
	 * @return the circle entity
	 */
	@OneToOne
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
}
