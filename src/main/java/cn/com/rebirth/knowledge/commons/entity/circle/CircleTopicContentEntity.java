/**
 * Copyright (c) 2005-2012-9-19 www.china-cti.com
 * Id: CircleTopicContentEntity.java,14:07:41
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

}
