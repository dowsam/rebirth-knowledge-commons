<<<<<<< HEAD
/**
 * Copyright (c) 2005-2012-9-23 www.china-cti.com
 * Id: CircleMemberApprovalEntity.java,22:59:12
 * @author wuwei
 */
package cn.com.rebirth.knowledge.commons.entity.circle;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import cn.com.rebirth.knowledge.commons.dhtmlx.entity.*;
import cn.com.rebirth.knowledge.commons.entity.system.*;

// TODO: Auto-generated Javadoc
/**
 * The Class CircleMemberApprovalEntity.
 *
 * @author wuwei
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "CIRCLE_MEMBER_APPROVAL")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CircleMemberApprovalEntity extends AbstractDhtmlxBaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6444099023890454873L;

	/**
	 * The Enum ApprovalType.
	 *
	 * @author wuwei
	 */
	public static enum ApprovalType {

		/** The new member approval. 新成员申请*/
		NEW_MEMBER_APPROVAL,

		/** The seconday approval. 副圈主申请*/
		SECONDAY_APPROVAL;
	}

	/** The circle entity. */
	private CircleEntity circleEntity;

	/** The user entity. */
	private SysUserEntity userEntity;

	/** The approval type. */
	private ApprovalType approvalType;

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
	 * Gets the user entity.
	 *
	 * @return the user entity
	 */
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	public SysUserEntity getUserEntity() {
		return userEntity;
	}

	/**
	 * Sets the user entity.
	 *
	 * @param userEntity the new user entity
	 */
	public void setUserEntity(SysUserEntity userEntity) {
		this.userEntity = userEntity;
	}

	/**
	 * Gets the approval type.
	 *
	 * @return the approval type
	 */
	public ApprovalType getApprovalType() {
		return approvalType;
	}

	/**
	 * Sets the approval type.
	 *
	 * @param approvalType the new approval type
	 */
	public void setApprovalType(ApprovalType approvalType) {
		this.approvalType = approvalType;
	}

}
=======
/**
 * Copyright (c) 2005-2012-9-23 www.china-cti.com
 * Id: CircleMemberApprovalEntity.java,22:59:12
 * @author wuwei
 */
package cn.com.rebirth.knowledge.commons.entity.circle;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import cn.com.rebirth.knowledge.commons.dhtmlx.entity.*;
import cn.com.rebirth.knowledge.commons.entity.system.*;

// TODO: Auto-generated Javadoc
/**
 * The Class CircleMemberApprovalEntity.
 *
 * @author wuwei
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "CIRCLE_MEMBER_APPROVAL")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CircleMemberApprovalEntity extends AbstractDhtmlxBaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6444099023890454873L;

	/**
	 * The Enum ApprovalType.
	 *
	 * @author wuwei
	 */
	public static enum ApprovalType {

		/** The new member approval. 新成员申请*/
		NEW_MEMBER_APPROVAL,

		/** The seconday approval. 副圈主申请*/
		SECONDAY_APPROVAL;
	}

	/** The circle entity. */
	private CircleEntity circleEntity;

	/** The user entity. */
	private SysUserEntity userEntity;

	/** The approval type. */
	private ApprovalType approvalType;

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
	 * Gets the user entity.
	 *
	 * @return the user entity
	 */
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	public SysUserEntity getUserEntity() {
		return userEntity;
	}

	/**
	 * Sets the user entity.
	 *
	 * @param userEntity the new user entity
	 */
	public void setUserEntity(SysUserEntity userEntity) {
		this.userEntity = userEntity;
	}

	/**
	 * Gets the approval type.
	 *
	 * @return the approval type
	 */
	public ApprovalType getApprovalType() {
		return approvalType;
	}

	/**
	 * Sets the approval type.
	 *
	 * @param approvalType the new approval type
	 */
	public void setApprovalType(ApprovalType approvalType) {
		this.approvalType = approvalType;
	}

}
>>>>>>> e686b726f78555bc1c19fe0b5c4dcebf854df322
