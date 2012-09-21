/**
 * Copyright (c) 2005-2012-9-20 www.china-cti.com
 * Id: CircleTopicContentEntity.java,14:39:32
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

// TODO: Auto-generated Javadoc
/**
 * The Class CircleTopicContent.
 * 话题内容
 * @author wuwei
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "CIRCLE_TOPIC_CONTENT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CircleTopicContentEntity extends BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2935326657597421910L;

	/** The circle topic entity. 所属圈子*/
	private CircleTopicEntity circleTopicEntity;

	/** The content. 内容*/
	private byte[] content;

	/**
	 * Gets the circle topic entity.
	 *
	 * @return the circle topic entity
	 */
	@OneToOne
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
	 * Gets the content.
	 *
	 * @return the content
	 */
	public byte[] getContent() {
		return content;
	}

	/**
	 * Sets the content.
	 *
	 * @param content the new content
	 */
	public void setContent(byte[] content) {
		this.content = content;
	}

	/**
	 * Gets the content str.
	 *
	 * @return the content str
	 */
	@Transient
	public String getContentStr() {
		return new String(content);
	}

	/* (non-Javadoc)
	 * @see cn.com.rebirth.commons.entity.BaseEntity#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((circleTopicEntity == null) ? 0 : circleTopicEntity.hashCode());
		result = prime * result + Arrays.hashCode(content);
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
		CircleTopicContentEntity other = (CircleTopicContentEntity) obj;
		if (circleTopicEntity == null) {
			if (other.circleTopicEntity != null)
				return false;
		} else if (!circleTopicEntity.equals(other.circleTopicEntity))
			return false;
		if (!Arrays.equals(content, other.content))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CircleTopicContentEntity [circleTopicEntity=" + circleTopicEntity + ", content="
				+ Arrays.toString(content) + "]";
	}

}
