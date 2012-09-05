/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons OperatingEntityListener.java 2012-9-3 10:19:49 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.entity.listener;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.rebirth.commons.utils.DateUtils;
import cn.com.rebirth.knowledge.commons.entity.AbstractOperatingEntity;

/**
 * The listener interface for receiving operatingEntity events.
 * The class that is interested in processing a operatingEntity
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addOperatingEntityListener<code> method. When
 * the operatingEntity event occurs, that object's appropriate
 * method is invoked.
 *
 * @see OperatingEntityEvent
 */
public class OperatingEntityListener {

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(OperatingEntityListener.class);

	/**
	 * Pre persist.
	 *
	 * @param object the object
	 */
	@PrePersist
	public void prePersist(Object object) {
		if (object instanceof AbstractOperatingEntity) {
			AbstractOperatingEntity baseEntity = (AbstractOperatingEntity) object;
			//创建新对象
			baseEntity.setCreateTime(DateUtils.getCurrentDateTime());
			Object o = SecurityUtils.getSubject().getPrincipal();
			baseEntity.setCreateBy(o == null ? "" : o.toString());
		}
	}

	/**
	 * Pre update.
	 *
	 * @param object the object
	 */
	@PreUpdate
	public void preUpdate(Object object) {
		if (object instanceof AbstractOperatingEntity) {
			AbstractOperatingEntity baseEntity = (AbstractOperatingEntity) object;
			//修改旧对象
			baseEntity.setLastModifyTime(DateUtils.getCurrentDateTime());
			Object o = SecurityUtils.getSubject().getPrincipal();
			baseEntity.setLastModifyBy(o == null ? "" : o.toString());
			logger.info("{}对象(ID:{}) 被 {} 在 {} 修改", new Object[] { object.getClass().getName(), baseEntity.getId(),
					baseEntity.getLastModifyBy(), new Date() });
		}
	}
}
