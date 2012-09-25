/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons BulletinEntity.java 2012-9-3 10:32:53 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.entity.operators;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.util.HtmlUtils;

import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlColumn;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.Dhtmlx;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlxBaseType;
import cn.com.rebirth.knowledge.commons.entity.AbstractOperatingEntity;
import cn.com.rebirth.knowledge.commons.entity.system.DictionaryEntity;
import cn.com.rebirth.knowledge.commons.entity.system.SysPageButtonEntity;
import cn.com.rebirth.knowledge.commons.entity.system.SysPageButtonEntity.ButtonEventType;

/**
 * 公告.
 *
 * @author l.xue.nong
 */
@Entity
@Table(name = "BULLETIN")
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Dhtmlx(createCheckAllColumn = true, isDataProcessor = false, isPage = true, onRowDblClicked = "BulletinEntity_RowDblClicked")
public class BulletinEntity extends AbstractOperatingEntity {

	/** The Constant add. */
	public static final SysPageButtonEntity add = new SysPageButtonEntity();
	public static final SysPageButtonEntity delete = new SysPageButtonEntity();
	static {
		add.setButtonId("BulletinEntity_add");
		add.setEventFun("BulletinEntity_add();");
		add.setEventName(ButtonEventType.onClick);
		add.setName("新增公告");
		add.setImagePath("/images/icon/icon_new.gif");

		delete.setButtonId("BulletinEntity_delete");
		delete.setEventFun("BulletinEntity_delete();");
		delete.setEventName(ButtonEventType.onClick);
		delete.setName("删除公告");
		delete.setImagePath("/images/icon/trash.gif");
	}

	/**
	 * 公告的状态.
	 *
	 * @author l.xue.nong
	 */
	public static enum BulletinStatus {

		/** 提交. */
		commit("提交"),
		/** 保存. */
		save("保存"),
		/** 删除. */
		delete("删除"),
		/** 过期. */
		expired("过期"),
		/** 未知. */
		unknown("未知");

		/** The title. */
		final String title;

		/**
		 * Instantiates a new bulletin status.
		 *
		 * @param title the title
		 */
		BulletinStatus(String title) {
			this.title = title;
		}

		/**
		 * Gets the title.
		 *
		 * @return the title
		 */
		public String getTitle() {
			return title;
		}

	}

	/**
	 * The Class BulletionDegreePropertyEditor.
	 *
	 * @author l.xue.nong
	 */
	public static class BulletionDegreePropertyEditor extends PropertyEditorSupport implements PropertyEditor {

		/* (non-Javadoc)
		 * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
		 */
		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			super.setValue(BulletionDegree.voist(text));
		}

	}

	/**
	 * 公告重要程度.
	 *
	 * @author l.xue.nong
	 */
	public static enum BulletionDegree {

		/** 紧急. */
		urgent("紧急"),
		/** 重要. */
		important("重要"),
		/** 普通. */
		ordinary("普通");

		/** The title. */
		final String title;

		/**
		 * Instantiates a new bulletion degree.
		 *
		 * @param name the name
		 */
		BulletionDegree(String name) {
			this.title = name;
		}

		/**
		 * Gets the title.
		 *
		 * @return the title
		 */
		public String getTitle() {
			return title;
		}

		/**
		 * Voist.
		 *
		 * @param name the name
		 * @return the bulletion degree
		 */
		public static BulletionDegree voist(String name) {
			BulletionDegree[] bulletionDegrees = BulletionDegree.class.getEnumConstants();
			for (BulletionDegree bulletionDegree : bulletionDegrees) {
				if (bulletionDegree.name().equalsIgnoreCase(name)) {
					return bulletionDegree;
				}
			}
			return null;
		}
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1141217076726425637L;

	/** The title. */
	private String title;//标题

	/** The content. */
	private byte[] content;//内容

	/** The dictionary entity. */
	private DictionaryEntity dictionaryEntity;//公告的类别

	/** The bulletin status. */
	private BulletinStatus bulletinStatus = BulletinStatus.unknown;//公告的状态，默认未知

	/** The begin time. */
	private Date beginTime;//开始时间

	/** The end time. */
	private Date endTime;//结束时间

	/** The bulletion degree. */
	private BulletionDegree bulletionDegree = BulletionDegree.ordinary;//公告紧急程度，默认普通

	/* (non-Javadoc)
	 * @see cn.com.rebirth.knowledge.commons.entity.AbstractBaseEntity#isChildTrem()
	 */
	/**
	 * Checks if is child trem.
	 *
	 * @return true, if is child trem
	 */
	@Transient
	@Override
	public boolean isChildTrem() {
		return false;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	@NotBlank
	@DhtmlColumn(columnIndex = 0, headerName = "标题", coulumnType = DhtmlxBaseType.RO)
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	@Lob
	@Basic(fetch = FetchType.LAZY)
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

	@Transient
	public String getContentToString() {
		return this.content == null ? "" : HtmlUtils.htmlEscape(new String(this.content));
	}

	/**
	 * Gets the dictionary entity.
	 *
	 * @return the dictionary entity
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dic_id")
	public DictionaryEntity getDictionaryEntity() {
		return dictionaryEntity;
	}

	/**
	 * Sets the dictionary entity.
	 *
	 * @param dictionaryEntity the new dictionary entity
	 */
	public void setDictionaryEntity(DictionaryEntity dictionaryEntity) {
		this.dictionaryEntity = dictionaryEntity;
	}

	/**
	 * Gets the bulletin status.
	 *
	 * @return the bulletin status
	 */
	@Enumerated
	public BulletinStatus getBulletinStatus() {
		return bulletinStatus;
	}

	/**
	 * Sets the bulletin status.
	 *
	 * @param bulletinStatus the new bulletin status
	 */
	public void setBulletinStatus(BulletinStatus bulletinStatus) {
		this.bulletinStatus = bulletinStatus;
	}

	/**
	 * Gets the begin time.
	 *
	 * @return the begin time
	 */
	public Date getBeginTime() {
		return beginTime;
	}

	/**
	 * Sets the begin time.
	 *
	 * @param beginTime the new begin time
	 */
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	/**
	 * Gets the end time.
	 *
	 * @return the end time
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * Sets the end time.
	 *
	 * @param endTime the new end time
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * Gets the bulletion degree.
	 *
	 * @return the bulletion degree
	 */
	@Enumerated
	public BulletionDegree getBulletionDegree() {
		return bulletionDegree;
	}

	/**
	 * Sets the bulletion degree.
	 *
	 * @param bulletionDegree the new bulletion degree
	 */
	public void setBulletionDegree(BulletionDegree bulletionDegree) {
		this.bulletionDegree = bulletionDegree;
	}

}
