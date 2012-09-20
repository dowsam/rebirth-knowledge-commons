package cn.com.rebirth.knowledge.commons.entity.system;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import cn.com.rebirth.knowledge.commons.dhtmlx.entity.*;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "SYS_USER_REAL_INFO")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysUserRealInfoEntity extends AbstractDhtmlxBaseEntity {

	private static final long serialVersionUID = 5174424555947433478L;
	private SysUserEntity sysUserEntity;
	private String realName;
	private Long idCardNum;
	private String phoneNum;
	private Long province;
	private Long city;
	private String address;
	private String MSNorQQ;

	@Override
	@Transient
	public boolean isChildTrem() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Long getIdCardNum() {
		return idCardNum;
	}

	public void setIdCardNum(Long idCardNum) {
		this.idCardNum = idCardNum;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Long getProvince() {
		return province;
	}

	public void setProvince(Long province) {
		this.province = province;
	}

	public Long getCity() {
		return city;
	}

	public void setCity(Long city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@OneToOne
	public SysUserEntity getSysUserEntity() {
		return sysUserEntity;
	}

	public void setSysUserEntity(SysUserEntity sysUserEntity) {
		this.sysUserEntity = sysUserEntity;
	}

	public String getMSNorQQ() {
		return MSNorQQ;
	}

	public void setMSNorQQ(String mSNorQQ) {
		MSNorQQ = mSNorQQ;
	}

}
