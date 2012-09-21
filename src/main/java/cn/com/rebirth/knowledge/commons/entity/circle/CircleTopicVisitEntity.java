/**
 * Copyright (c) 2005-2012-9-20 www.china-cti.com
 * Id: CircleTopicVisitEntity.java,14:40:09
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
 * The Class CircleTopicVisitEntity.
 * 话题访问的详细信息
 * @author wuwei
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "CIRCLE_TOPIC_VISIT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CircleTopicVisitEntity extends BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6596017952785169952L;

	/** The visit user. 访问者*/
	private SysUserEntity visitUser;

	/** The visit date. 访问时间*/
	private Date visitDate;

	/** The circle topic entity. 访问圈子*/
	private CircleTopicEntity circleTopicEntity;

	/**
	 * Gets the visit user.
	 *
	 * @return the visit user
	 */
	@OneToOne
	@JoinColumn(name = "USER_ID")
	public SysUserEntity getVisitUser() {
		return visitUser;
	}

	/**
	 * Sets the visit user.
	 *
	 * @param visitUser the new visit user
	 */
	public void setVisitUser(SysUserEntity visitUser) {
		this.visitUser = visitUser;
	}

	/**
	 * Gets the visit date.
	 *
	 * @return the visit date
	 */
	public Date getVisitDate() {
		return visitDate;
	}

	/**
	 * Sets the visit date.
	 *
	 * @param visitDate the new visit date
	 */
	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
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

	/* (non-Javadoc)
	 * @see cn.com.rebirth.commons.entity.BaseEntity#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((circleTopicEntity == null) ? 0 : circleTopicEntity.hashCode());
		result = prime * result + ((visitDate == null) ? 0 : visitDate.hashCode());
		result = prime * result + ((visitUser == null) ? 0 : visitUser.hashCode());
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
		CircleTopicVisitEntity other = (CircleTopicVisitEntity) obj;
		if (circleTopicEntity == null) {
			if (other.circleTopicEntity != null)
				return false;
		} else if (!circleTopicEntity.equals(other.circleTopicEntity))
			return false;
		if (visitDate == null) {
			if (other.visitDate != null)
				return false;
		} else if (!visitDate.equals(other.visitDate))
			return false;
		if (visitUser == null) {
			if (other.visitUser != null)
				return false;
		} else if (!visitUser.equals(other.visitUser))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CircleTopicVisitEntity [visitUser=" + visitUser + ", visitDate=" + visitDate + ", circleTopicEntity="
				+ circleTopicEntity + "]";
	}
}
