/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons SysPageButtonEntity.java 2012-8-27 10:24:30 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.entity.system;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;

import cn.com.rebirth.knowledge.commons.dhtmlx.ColumnDataSets;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlColumn;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.Dhtmlx;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlxBaseType;
import cn.com.rebirth.knowledge.commons.dhtmlx.entity.AbstractDhtmlxBaseEntity;
import cn.com.rebirth.knowledge.commons.dhtmlx.entity.Column;
import cn.com.rebirth.knowledge.commons.dhtmlx.entity.Grid;

/**
 * The Class SysPageButtonEntity.
 *
 * @author l.xue.nong
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "SYS_PAGE_BUTTON")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Dhtmlx(createCheckAllColumn = false, isDataProcessor = true)
public class SysPageButtonEntity extends AbstractDhtmlxBaseEntity {
	
	/** The Constant add. */
	public static final SysPageButtonEntity add = new SysPageButtonEntity();
	
	/** The Constant update. */
	public static final SysPageButtonEntity update = new SysPageButtonEntity();
	
	/** The Constant delete. */
	public static final SysPageButtonEntity delete = new SysPageButtonEntity();
	static {
		add.setButtonId("add");
		add.setEventFun("add();");
		add.setEventName(ButtonEventType.onClick);
		add.setName("新增");
		add.setImagePath("/images/icon/icon_new.gif");
		delete.setButtonId("del");
		delete.setEventFun("del();");
		delete.setEventName(ButtonEventType.onClick);
		delete.setName("删除");
		delete.setImagePath("/images/icon/trash.gif");
		update.setButtonId("update");
		update.setEventFun("update();");
		update.setEventName(ButtonEventType.onClick);
		update.setName("保存");
		update.setImagePath("/images/icon/icon_save.gif");
	}

	/**
	 * The Enum ButtonEventType.
	 *
	 * @author l.xue.nong
	 */
	public static enum ButtonEventType {

		/** The on click. */
		onClick(),
		/** The on blur. */
		onBlur(),
		/** The on dblclick. */
		onDblclick(),
		/** The on focus. */
		onFocus(),
		/** The on keydown. */
		onKeydown(),
		/** The on keypress. */
		onKeypress(),
		/** The on keyup. */
		onKeyup(),
		/** The on mousedown. */
		onMousedown(),
		/** The on mousemove. */
		onMousemove(),
		/** The on mouseout. */
		onMouseout(),
		/** The on mouseover. */
		onMouseover(),
		/** The on mouseup. */
		onMouseup();
	}

	/**
	 * The Class ButtonEventTypeCobo.
	 *
	 * @author l.xue.nong
	 */
	public static class ButtonEventTypeCobo implements ColumnDataSets {

		/**
		 * Bulid column data.
		 *
		 * @param columnIndex the column index
		 * @param column the column
		 * @param grid the grid
		 * @param stringBuilder the string builder
		 */
		@Override
		public void bulidColumnData(int columnIndex, Column column, Grid grid, StringBuilder stringBuilder) {
			ButtonEventType[] buttonEventTypes = ButtonEventType.class.getEnumConstants();
			for (ButtonEventType buttonEventType : buttonEventTypes) {
				stringBuilder.append(grid.getId()).append(".getCombo(" + columnIndex + ")")
						.append(".put(\"" + buttonEventType.name() + "\",\"" + buttonEventType.name() + "\");\n");

			}
		}

	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2827188816358053077L;

	/** The name. */
	private String name;//名称

	/** The button id. */
	private String buttonId;//按钮的Id
	/** The event name. */
	private ButtonEventType eventName = ButtonEventType.onClick;//事件名称,默认单击事件

	/** The event fun. */
	private String eventFun;//事件处理fun

	//符加信息
	/** The image path. */
	private String imagePath;//按钮旁边的图标

	/* (non-Javadoc)
	 * @see cn.com.rebirth.knowledge.commons.entity.AbstractBaseEntity#isChildTrem()
	 */
	/**
	 * Checks if is child trem.
	 *
	 * @return true, if is child trem
	 */
	@Override
	@Transient
	public boolean isChildTrem() {
		return false;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	@DhtmlColumn(columnIndex = 0, headerName = "名称")
	@NotBlank
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the button id.
	 *
	 * @return the button id
	 */
	@DhtmlColumn(columnIndex = 1, headerName = "标志")
	@NotBlank
	public String getButtonId() {
		return buttonId;
	}

	/**
	 * Sets the button id.
	 *
	 * @param buttonId the new button id
	 */
	public void setButtonId(String buttonId) {
		this.buttonId = buttonId;
	}

	/**
	 * Gets the event name.
	 *
	 * @return the event name
	 */

	/**
	 * Gets the event name.
	 *
	 * @return the event name
	 */
	@Enumerated(EnumType.STRING)
	@DhtmlColumn(columnIndex = 2, headerName = "事件", coulumnType = DhtmlxBaseType.CORO, columnDataSets = ButtonEventTypeCobo.class)
	@NotNull
	public ButtonEventType getEventName() {
		return eventName;
	}

	/**
	 * Sets the event name.
	 *
	 * @param eventName the new event name
	 */
	public void setEventName(ButtonEventType eventName) {
		this.eventName = eventName;
	}

	/**
	 * Gets the event fun.
	 *
	 * @return the event fun
	 */
	@DhtmlColumn(columnIndex = 3, headerName = "函数", coulumnType = DhtmlxBaseType.TXT)
	@NotBlank
	public String getEventFun() {
		return eventFun;
	}

	/**
	 * Sets the event fun.
	 *
	 * @param eventFun the new event fun
	 */
	public void setEventFun(String eventFun) {
		this.eventFun = eventFun;
	}

	/**
	 * Gets the image path.
	 *
	 * @return the image path
	 */
	@DhtmlColumn(columnIndex = 4, headerName = "图标")
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * Sets the image path.
	 *
	 * @param imagePath the new image path
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}
