/**
 * Copyright (c) 2005-2012-9-19 www.china-cti.com
 * Id: CircleTopicVisitEntity.java,10:28:50
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
}
