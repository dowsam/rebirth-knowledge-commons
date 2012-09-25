/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons SysLogEntity.java 2012-8-13 15:14:47 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.entity.system;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import cn.com.rebirth.commons.search.annotation.AbstractSearchProperty;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.Align;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlColumn;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.Dhtmlx;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlxBaseType;
import cn.com.rebirth.knowledge.commons.dhtmlx.data.IColumnConverter;
import cn.com.rebirth.knowledge.commons.dhtmlx.entity.AbstractDhtmlxBaseEntity;

/**
 * The Class SysLogEntity.
 *	暂不考虑现有的分表实现，等数据量大的时候，在增加对日志记录的分表及分库的实现
 * @author l.xue.nong
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "SYS_LOG")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Dhtmlx(createCheckAllColumn = false, isDataProcessor = false)
public class SysLogEntity extends AbstractLogBaseEntity {

	/**
	 * The Class LogContextColumnConverter.
	 *
	 * @author l.xue.nong
	 */
	public static class LogContextColumnConverter implements IColumnConverter {

		/**
		 * Convert.
		 *
		 * @param property the property
		 * @param dhtmlColumn the dhtml column
		 * @param dhtmlxBaseEntity the dhtmlx base entity
		 * @param converterValue the converter value
		 * @return the object
		 */
		@Override
		public Object convert(AbstractSearchProperty property, DhtmlColumn dhtmlColumn,
				AbstractDhtmlxBaseEntity dhtmlxBaseEntity, Object converterValue) {
			if (converterValue instanceof String) {
				return converterValue;
			}
			byte[] b = (byte[]) converterValue;
			return new String(b);
		}

	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2600877658139444588L;

	/** The request ip. */
	protected String requestIp;

	/** The log context. */
	protected String logContext;

	/**
	 * Gets the request ip.
	 *
	 * @return the request ip
	 */
	@DhtmlColumn(columnIndex = 5, headerName = "请求IP")
	public String getRequestIp() {
		return requestIp;
	}

	/**
	 * Sets the request ip.
	 *
	 * @param requestIp the new request ip
	 */
	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp;
	}

	/**
	 * Gets the log context.
	 *
	 * @return the log context
	 */

	/**
	 * Gets the log context.
	 *
	 * @return the log context
	 */

	/**
	 * Gets the log context.
	 *
	 * @return the log context
	 */
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(length = 4000)
	@DhtmlColumn(columnIndex = 0, headerName = "&nbsp;", columnConverter = LogContextColumnConverter.class, sortable = false, coulumnType = DhtmlxBaseType.SUB_ROW, initWidth = 20, coulumnAlign = Align.RIGHT)
	public String getLogContext() {
		return logContext;
	}

	/**
	 * Sets the log context.
	 *
	 * @param logContext the new log context
	 */
	public void setLogContext(String logContext) {
		if (logContext != null && logContext.length() > 4000) {
			logContext = logContext.substring(0, 3999);
		}
		this.logContext = logContext;
	}

}
