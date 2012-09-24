/**
 * Copyright (c) 2005-2012-9-20 www.china-cti.com
 * Id: SysUserRealInfoEntity.java,9:28:02
 * @author wuwei
 */
package cn.com.rebirth.knowledge.commons.entity.system;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import cn.com.rebirth.knowledge.commons.dhtmlx.entity.*;

// TODO: Auto-generated Javadoc
/**
 * The Class SysUserRealInfoEntity.
 *
 * @author wuwei
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "SYS_USER_REAL_INFO")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysUserRealInfoEntity extends AbstractDhtmlxBaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5174424555947433478L;

	/** The sys user entity. */
	private SysUserEntity sysUserEntity;

	/** The real name. */
	private String realName;

	/** The id card num. */
	private Long idCardNum;

	/** The phone num. */
	private String phoneNum;

	/** The province. */
	private Long province;

	/** The city. */
	private Long city;

	/** The address. */
	private String address;

	/** The MS nor qq. */
	private String MSNorQQ;

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
	 * Gets the real name.
	 *
	 * @return the real name
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * Sets the real name.
	 *
	 * @param realName the new real name
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * Gets the id card num.
	 *
	 * @return the id card num
	 */
	public Long getIdCardNum() {
		return idCardNum;
	}

	/**
	 * Sets the id card num.
	 *
	 * @param idCardNum the new id card num
	 */
	public void setIdCardNum(Long idCardNum) {
		this.idCardNum = idCardNum;
	}

	/**
	 * Gets the phone num.
	 *
	 * @return the phone num
	 */
	public String getPhoneNum() {
		return phoneNum;
	}

	/**
	 * Sets the phone num.
	 *
	 * @param phoneNum the new phone num
	 */
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	/**
	 * Gets the province.
	 *
	 * @return the province
	 */
	public Long getProvince() {
		return province;
	}

	/**
	 * Sets the province.
	 *
	 * @param province the new province
	 */
	public void setProvince(Long province) {
		this.province = province;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public Long getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(Long city) {
		this.city = city;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the sys user entity.
	 *
	 * @return the sys user entity
	 */
	@OneToOne
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
	 * Gets the mS nor qq.
	 *
	 * @return the mS nor qq
	 */
	public String getMSNorQQ() {
		return MSNorQQ;
	}

	/**
	 * Sets the mS nor qq.
	 *
	 * @param mSNorQQ the new mS nor qq
	 */
	public void setMSNorQQ(String mSNorQQ) {
		MSNorQQ = mSNorQQ;
	}

}
