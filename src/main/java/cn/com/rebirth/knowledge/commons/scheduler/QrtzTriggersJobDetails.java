/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons QrtzTriggersJobDetails.java 2012-8-6 14:45:43 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.scheduler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;

import cn.com.rebirth.commons.search.annotation.AbstractSearchProperty;
import cn.com.rebirth.commons.utils.DateUtils;
import cn.com.rebirth.commons.utils.EncodeUtils;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlColumn;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.Dhtmlx;
import cn.com.rebirth.knowledge.commons.dhtmlx.data.IColumnConverter;
import cn.com.rebirth.knowledge.commons.dhtmlx.entity.AbstractDhtmlxBaseEntity;

/**
 * The Class QrtzTriggersJobDetails.
 *
 * @author l.xue.nong
 */
@Dhtmlx(createCheckAllColumn = true)
public class QrtzTriggersJobDetails extends AbstractDhtmlxBaseEntity {

	/**
	 * The Class DateConverter.
	 *
	 * @author l.xue.nong
	 */
	public static class DateConverter implements IColumnConverter {

		/* (non-Javadoc)
		 * @see cn.com.rebirth.knowledge.commons.dhtmlx.data.IColumnConverter#convert(cn.com.rebirth.commons.search.annotation.AbstractSearchProperty, cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlColumn, cn.com.rebirth.knowledge.commons.dhtmlx.entity.AbstractDhtmlxBaseEntity, java.lang.Object)
		 */
		@Override
		public Object convert(AbstractSearchProperty property, DhtmlColumn dhtmlColumn,
				AbstractDhtmlxBaseEntity dhtmlxBaseEntity, Object converterValue) {
			if (converterValue != null) {
				if (Number.class.isAssignableFrom(property.getRawClass())) {
					Long long1 = (Long) converterValue;
					Date date = new Date(long1);
					return DateUtils.formatDate(date, null);
				}
			}
			return converterValue;
		}

	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1658433931613356433L;

	/** The Constant status. */
	private static final Map<String, String> status = new HashMap<String, String>();
	static {
		status.put("ACQUIRED", "运行中");
		status.put("PAUSED", "暂停中");
		status.put("WAITING", "等待中");
	}

	/** The trigger_name. */
	private String trigger_name;

	/** The trigger_group. */
	private String trigger_group;

	/** The job_name. */
	private String job_name;

	/** The job_group. */
	private String job_group;

	/** The is_volatile. */
	private Boolean is_volatile;

	/** The description. */
	private String description;

	/** The next_fire_time. */
	private Long next_fire_time;

	/** The prev_fire_time. */
	private Long prev_fire_time;

	/** The priority. */
	private Integer priority;

	/** The trigger_state. */
	private String trigger_state;

	/** The trigger_type. */
	private String trigger_type;

	/** The start_time. */
	private Long start_time;

	/** The end time. */
	private Long END_TIME;

	/** The calendar_name. */
	private String calendar_name;

	/** The misfire_instr. */
	private Long misfire_instr;

	/** The job_details_description. */
	private String job_details_description;

	/** The job_details_job_class_name. */
	private String job_details_job_class_name;

	/** The cron expression. */
	private String cronExpression;

	/**
	 * Gets the trigger_name.
	 *
	 * @return the trigger_name
	 */
	public String getTrigger_name() {
		return trigger_name;
	}

	/**
	 * Sets the trigger_name.
	 *
	 * @param trigger_name the new trigger_name
	 */
	public void setTrigger_name(String trigger_name) {
		this.trigger_name = trigger_name;
	}

	/**
	 * Gets the trigger_group.
	 *
	 * @return the trigger_group
	 */
	public String getTrigger_group() {
		return trigger_group;
	}

	/**
	 * Sets the trigger_group.
	 *
	 * @param trigger_group the new trigger_group
	 */
	public void setTrigger_group(String trigger_group) {
		this.trigger_group = trigger_group;
	}

	/**
	 * Gets the job_name.
	 *
	 * @return the job_name
	 */
	@DhtmlColumn(columnIndex = 0, headerName = "任务名称")
	public String getJob_name() {
		return job_name;
	}

	/**
	 * Sets the job_name.
	 *
	 * @param job_name the new job_name
	 */
	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}

	/**
	 * Gets the job_group.
	 *
	 * @return the job_group
	 */
	public String getJob_group() {
		return job_group;
	}

	/**
	 * Sets the job_group.
	 *
	 * @param job_group the new job_group
	 */
	public void setJob_group(String job_group) {
		this.job_group = job_group;
	}

	/**
	 * Gets the is_volatile.
	 *
	 * @return the is_volatile
	 */
	public Boolean getIs_volatile() {
		return is_volatile;
	}

	/**
	 * Sets the is_volatile.
	 *
	 * @param is_volatile the new is_volatile
	 */
	public void setIs_volatile(Boolean is_volatile) {
		this.is_volatile = is_volatile;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the next_fire_time.
	 *
	 * @return the next_fire_time
	 */
	@DhtmlColumn(columnIndex = 4, headerName = "下次运行时间", columnConverter = DateConverter.class, initWidth = 110)
	public Long getNext_fire_time() {
		return next_fire_time;
	}

	/**
	 * Sets the next_fire_time.
	 *
	 * @param next_fire_time the new next_fire_time
	 */
	public void setNext_fire_time(Long next_fire_time) {
		this.next_fire_time = next_fire_time;
	}

	/**
	 * Gets the prev_fire_time.
	 *
	 * @return the prev_fire_time
	 */
	@DhtmlColumn(columnIndex = 5, headerName = "上次运行时间", columnConverter = DateConverter.class, initWidth = 110)
	public Long getPrev_fire_time() {
		return prev_fire_time;
	}

	/**
	 * Sets the prev_fire_time.
	 *
	 * @param prev_fire_time the new prev_fire_time
	 */
	public void setPrev_fire_time(Long prev_fire_time) {
		this.prev_fire_time = prev_fire_time;
	}

	/**
	 * Gets the priority.
	 *
	 * @return the priority
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * Sets the priority.
	 *
	 * @param priority the new priority
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	/**
	 * Gets the trigger_state.
	 *
	 * @return the trigger_state
	 */
	@DhtmlColumn(columnIndex = 7, headerName = "状态")
	public String getTrigger_state() {
		return status.get(trigger_state);
	}

	/**
	 * Sets the trigger_state.
	 *
	 * @param trigger_state the new trigger_state
	 */
	public void setTrigger_state(String trigger_state) {
		this.trigger_state = trigger_state;
	}

	/**
	 * Gets the trigger_type.
	 *
	 * @return the trigger_type
	 */
	public String getTrigger_type() {
		return trigger_type;
	}

	/**
	 * Sets the trigger_type.
	 *
	 * @param trigger_type the new trigger_type
	 */
	public void setTrigger_type(String trigger_type) {
		this.trigger_type = trigger_type;
	}

	/**
	 * Gets the start_time.
	 *
	 * @return the start_time
	 */
	@DhtmlColumn(columnIndex = 2, headerName = "开始时间", columnConverter = DateConverter.class, initWidth = 110)
	public Long getStart_time() {
		return start_time;
	}

	/**
	 * Sets the start_time.
	 *
	 * @param start_time the new start_time
	 */
	public void setStart_time(Long start_time) {
		this.start_time = start_time;
	}

	/**
	 * Gets the end time.
	 *
	 * @return the end time
	 */
	@DhtmlColumn(columnIndex = 3, headerName = "结束时间", columnConverter = DateConverter.class, initWidth = 110)
	public Long getEND_TIME() {
		return END_TIME;
	}

	/**
	 * Sets the end time.
	 *
	 * @param eND_TIME the new end time
	 */
	public void setEND_TIME(Long eND_TIME) {
		END_TIME = eND_TIME;
	}

	/**
	 * Gets the calendar_name.
	 *
	 * @return the calendar_name
	 */
	public String getCalendar_name() {
		return calendar_name;
	}

	/**
	 * Sets the calendar_name.
	 *
	 * @param calendar_name the new calendar_name
	 */
	public void setCalendar_name(String calendar_name) {
		this.calendar_name = calendar_name;
	}

	/**
	 * Gets the misfire_instr.
	 *
	 * @return the misfire_instr
	 */
	public Long getMisfire_instr() {
		return misfire_instr;
	}

	/**
	 * Sets the misfire_instr.
	 *
	 * @param misfire_instr the new misfire_instr
	 */
	public void setMisfire_instr(Long misfire_instr) {
		this.misfire_instr = misfire_instr;
	}

	/**
	 * Gets the job_details_description.
	 *
	 * @return the job_details_description
	 */
	@DhtmlColumn(columnIndex = 1, headerName = "描述")
	public String getJob_details_description() {
		return job_details_description;
	}

	/**
	 * Sets the job_details_description.
	 *
	 * @param job_details_description the new job_details_description
	 */
	public void setJob_details_description(String job_details_description) {
		this.job_details_description = job_details_description;
	}

	/**
	 * Gets the job_details_job_class_name.
	 *
	 * @return the job_details_job_class_name
	 */
	@DhtmlColumn(columnIndex = 6, headerName = "运行类", initWidth = 200)
	public String getJob_details_job_class_name() {
		return job_details_job_class_name;
	}

	/**
	 * Sets the job_details_job_class_name.
	 *
	 * @param job_details_job_class_name the new job_details_job_class_name
	 */
	public void setJob_details_job_class_name(String job_details_job_class_name) {
		this.job_details_job_class_name = job_details_job_class_name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QrtzTriggersJobDetails [trigger_name=" + trigger_name + ", trigger_group=" + trigger_group
				+ ", job_name=" + job_name + ", job_group=" + job_group + ", is_volatile=" + is_volatile
				+ ", description=" + description + ", next_fire_time=" + next_fire_time + ", prev_fire_time="
				+ prev_fire_time + ", priority=" + priority + ", trigger_state=" + getTrigger_state()
				+ ", trigger_type=" + trigger_type + ", start_time=" + start_time + ", END_TIME=" + END_TIME
				+ ", calendar_name=" + calendar_name + ", misfire_instr=" + misfire_instr
				+ ", job_details_description=" + job_details_description + ", job_details_job_class_name="
				+ job_details_job_class_name + "]";
	}

	/* (non-Javadoc)
	 * @see cn.com.rebirth.knowledge.commons.entity.AbstractBaseEntity#isChildTrem()
	 */
	@Override
	public boolean isChildTrem() {
		return false;
	}

	/* (non-Javadoc)
	 * @see cn.com.rebirth.commons.entity.BaseEntity#getId()
	 */
	@Id
	@Override
	public Long getId() {
		return EncodeUtils.decodeBase62(getTrigger_name());
	}

	/**
	 * Gets the cron expression.
	 *
	 * @return the cron expression
	 */
	@DhtmlColumn(columnIndex = 8, headerName = "时间表达式", initWidth = 120)
	public String getCronExpression() {
		return cronExpression;
	}

	/**
	 * Sets the cron expression.
	 *
	 * @param cronExpression the new cron expression
	 */
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public static void main(String[] args) {
		System.out.println(EncodeUtils.decodeBase62("QWJD69seD93"));
	}

}
