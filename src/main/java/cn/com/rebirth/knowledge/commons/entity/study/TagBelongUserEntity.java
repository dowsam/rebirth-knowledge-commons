package cn.com.rebirth.knowledge.commons.entity.study;

import java.util.*;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import cn.com.rebirth.knowledge.commons.dhtmlx.entity.*;
import cn.com.rebirth.knowledge.commons.entity.system.*;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "STUDY_TAG_BELONG_USER")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TagBelongUserEntity extends AbstractDhtmlxBaseEntity {

	private static final long serialVersionUID = -6934980072649341580L;
	private TagEntity tag;
	private Date attetionDate;
	private SysUserEntity sysUser;

	@Column(name = "ATTENTION_DATE")
	public Date getAttetionDate() {
		return attetionDate;
	}

	public void setAttetionDate(Date attetionDate) {
		this.attetionDate = attetionDate;
	}

	@ManyToOne
	@JoinColumn(name = "tag")
	public TagEntity getTag() {
		return tag;
	}

	public void setTag(TagEntity tag) {
		this.tag = tag;
	}

	@ManyToOne
	public SysUserEntity getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUserEntity sysUser) {
		this.sysUser = sysUser;
	}

	@Override
	@Transient
	public boolean isChildTrem() {
		// TODO Auto-generated method stub
		return false;
	}
}
