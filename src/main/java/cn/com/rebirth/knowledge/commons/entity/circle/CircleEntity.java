/**
 * Copyright (c) 2005-2012-9-19 www.china-cti.com
 * Id: CircleEntity.java,9:27:11
 * @author wuwei
 */
package cn.com.rebirth.knowledge.commons.entity.circle;

import java.util.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import cn.com.rebirth.knowledge.commons.dhtmlx.entity.*;
import cn.com.rebirth.knowledge.commons.entity.system.*;

import com.google.common.collect.*;

// TODO: Auto-generated Javadoc
/**
 * The Class CircleEntity.
 *  圈子实体
 * @author wuwei
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "CIRCLE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CircleEntity extends AbstractDhtmlxBaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 222566075167051326L;

	/**
	 * The Enum CircleStatu.
	 *
	 * @author wuwei
	 */
	public static enum CircleStatu {

		/** 未审核. */
		UNCHECKED,

		/** 正常状态. */
		NORMAL,

		/** 删除. */
		DELETE;
	}

	/**
	 * The Enum CircleType.
	 *
	 * @author wuwei
	 */
	public static enum CircleType {

		/** 所有人都可以浏览圈子内容并发帖. */
		PUBLIC,

		/** 所有人都可以浏览圈子内容，但只有加入圈子的会员可以发帖. */
		PROTECT,

		/** 只有加入圈子的会员，可以浏览圈子内容并发帖. */
		PRIVATE;
	}

	/** The circle name. */
	private String circleName;

	/** The create date. */
	private Date createDate;

	/** The master 圈子主人. */
	private SysUserEntity master;

	/** The secondary master 副圈主. */
	private List<SysUserEntity> secondaryMaster = Lists.newArrayList();

	/** The circle type. 圈子的三种开放类型*/
	private CircleType circleType;

	/** The statu.  圈子的状态*/
	private CircleStatu statu = CircleStatu.UNCHECKED;

	/** The member user.  圈子成员列表*/
	private List<SysUserEntity> memberUser = Lists.newArrayList();

	/** The category .  圈子一级类别*/
	private CircleCategoryEntity category;

	/** The sec category . 圈子二级类别*/
	private CircleCategoryEntity secCategory;

	/** The master message.  圈主寄语*/
	private String masterMessage;

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
	 * Gets the circle name.
	 *
	 * @return the circle name
	 */
	public String getCircleName() {
		return circleName;
	}

	/**
	 * Sets the circle name.
	 *
	 * @param circleName the new circle name
	 */
	public void setCircleName(String circleName) {
		this.circleName = circleName;
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
	 * Gets the circle type.
	 *
	 * @return the circle type
	 */
	@Enumerated(EnumType.ORDINAL)
	public CircleType getCircleType() {
		return circleType;
	}

	/**
	 * Sets the circle type.
	 *
	 * @param circleType the new circle type
	 */
	public void setCircleType(CircleType circleType) {
		this.circleType = circleType;
	}

	/**
	 * Gets the member user.
	 *
	 * @return the member user
	 */
	@ManyToMany
	// 中间表定义,表名采用默认命名规则
	@JoinTable(name = "MEMBER_CIRCLE", joinColumns = { @JoinColumn(name = "CIRCLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
	// Fecth策略定义
	@Fetch(FetchMode.SUBSELECT)
	// 集合按id排序.
	@OrderBy("id desc")
	// 集合中对象id的缓存.
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<SysUserEntity> getMemberUser() {
		return memberUser;
	}

	/**
	 * Sets the member user.
	 *
	 * @param memberUser the new member user
	 */
	public void setMemberUser(List<SysUserEntity> memberUser) {
		this.memberUser = memberUser;
	}

	/**
	 * Gets the category entities.
	 *
	 * @return the category entities
	 */
	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID")
	@Where(clause = "CATEGORY_TYPE=0")
	public CircleCategoryEntity getCategory() {
		return category;
	}

	/**
	 * Sets the category entities.
	 *
	 * @param category the new category 
	 */
	public void setCategory(CircleCategoryEntity category) {
		this.category = category;
	}

	/**
	 * Gets the master.
	 *
	 * @return the master
	 */
	@ManyToOne
	@JoinColumn(name = "CIRCLE_MASTER")
	public SysUserEntity getMaster() {
		return master;
	}

	/**
	 * Sets the master.
	 *
	 * @param master the new master
	 */
	public void setMaster(SysUserEntity master) {
		this.master = master;
	}

	/**
	 * Gets the secondary master.
	 *
	 * @return the secondary master
	 */
	@ManyToMany
	// 中间表定义,表名采用默认命名规则
	@JoinTable(name = "SEC_MASTER_CIRCLE", joinColumns = { @JoinColumn(name = "CIRCLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
	// Fecth策略定义
	@Fetch(FetchMode.SUBSELECT)
	// 集合按id排序.
	@OrderBy("id desc")
	// 集合中对象id的缓存.
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<SysUserEntity> getSecondaryMaster() {
		return secondaryMaster;
	}

	/**
	 * Sets the secondary master.
	 *
	 * @param secondaryMaster the new secondary master
	 */
	public void setSecondaryMaster(List<SysUserEntity> secondaryMaster) {
		this.secondaryMaster = secondaryMaster;
	}

	/**
	 * Gets the statu.
	 *
	 * @return the statu
	 */
	@Enumerated(EnumType.ORDINAL)
	public CircleStatu getStatu() {
		return statu;
	}

	/**
	 * Sets the statu.
	 *
	 * @param statu the new statu
	 */
	public void setStatu(CircleStatu statu) {
		this.statu = statu;
	}

	/**
	 * Gets the sec category entities.
	 *
	 * @return the sec category entities
	 */
	@ManyToOne
	@JoinColumn(name = "SEC_CATEGORY_ID")
	@Where(clause = "CATEGORY_TYPE=1")
	public CircleCategoryEntity getSecCategory() {
		return secCategory;
	}

	/**
	 * Sets the sec category entities.
	 *
	 * @param secCategory the new sec category 
	 */
	public void setSecCategory(CircleCategoryEntity secCategory) {
		this.secCategory = secCategory;
	}

	/**
	 * Gets the master message.
	 *
	 * @return the master message
	 */
	public String getMasterMessage() {
		return masterMessage;
	}

	/**
	 * Sets the master message.
	 *
	 * @param masterMessage the new master message
	 */
	public void setMasterMessage(String masterMessage) {
		this.masterMessage = masterMessage;
	}

	@Transient
	public int getMemberNum() {
		return memberUser.size() + secondaryMaster.size() + 1;
	}

}
