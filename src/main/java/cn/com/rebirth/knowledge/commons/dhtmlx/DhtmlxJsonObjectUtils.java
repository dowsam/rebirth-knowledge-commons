/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons DhtmlxJsonObjectUtils.java 2012-8-3 21:35:45 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import cn.com.rebirth.commons.Page;
import cn.com.rebirth.core.mapper.JaxbMapper;
import cn.com.rebirth.knowledge.commons.entity.AbstractBaseEntity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * The Class DhtmlxJsonObjectUtils.
 *
 * @author l.xue.nong
 */
public abstract class DhtmlxJsonObjectUtils {

	/** The Constant ROWS. */
	public static final String ROWS = "rows";

	/**
	 * Gets the abstract json entities.
	 *
	 * @param data the data
	 * @return the abstract json entities
	 */
	public static Map<String, List<DhtmlxJsonObject>> getAbstractJsonEntities(List<? extends AbstractBaseEntity> data) {
		Map<String, List<DhtmlxJsonObject>> map = Maps.newHashMap();
		List<DhtmlxJsonObject> dhtmlxJsonObjects = Lists.newArrayList();
		for (AbstractBaseEntity abstractBaseEntity : data) {
			dhtmlxJsonObjects.add(new DhtmlxJsonObject(abstractBaseEntity.getId(), abstractBaseEntity
					.getDhtmlxJsonOrXmlObject()));
		}
		map.put(ROWS, dhtmlxJsonObjects);
		return map;
	}

	/**
	 * 输出Tree json数据.
	 *
	 * @param data the data
	 * @param syn the syn
	 * @return the abstract json tree entities
	 */
	public static Map<String, List<TreeJsonInfo>> getAbstractJsonTreeEntities(List<? extends AbstractBaseEntity> data,
			boolean syn) {
		Map<String, List<TreeJsonInfo>> map = Maps.newHashMap();
		List<TreeJsonInfo> dhtmlxJsonObjects = Lists.newArrayList();
		for (AbstractBaseEntity abstractJsonEntity : data) {
			TreeJsonInfo info = (TreeJsonInfo) abstractJsonEntity.getDhtmlxJsonOrXmlObject().iterator().next();
			if (!syn) {
				bulidChidToItem(info, abstractJsonEntity);
			}
			dhtmlxJsonObjects.add(info);
		}
		map.put(ROWS, dhtmlxJsonObjects);
		return map;
	}

	/**
	 * Bulid chid to item.
	 *
	 * @param info the info
	 * @param abstractJsonEntity the abstract json entity
	 */
	private static void bulidChidToItem(TreeJsonInfo info, AbstractBaseEntity abstractJsonEntity) {
		Collection<? extends AbstractBaseEntity> collection = abstractJsonEntity.getChildObject();
		if (collection != null && !collection.isEmpty()) {
			for (AbstractBaseEntity abstractBaseEntity : collection) {
				TreeJsonInfo info2 = (TreeJsonInfo) abstractBaseEntity.getDhtmlxJsonOrXmlObject().iterator().next();
				info.addItem(info2);
				bulidChidToItem(info2, abstractBaseEntity);
			}
		}
	}

	/**
	 * Gets the abstract json entities.
	 *
	 * @param map the map
	 * @param data the data
	 * @return the abstract json entities
	 */
	public static Map<String, Object> getAbstractJsonEntities(Map<String, Object> map,
			Collection<? extends AbstractBaseEntity> data) {
		List<DhtmlxJsonObject> dhtmlxJsonObjects = Lists.newArrayList();
		for (AbstractBaseEntity abstractJsonEntity : data) {
			dhtmlxJsonObjects.add(new DhtmlxJsonObject(abstractJsonEntity.getId(), abstractJsonEntity
					.getDhtmlxJsonOrXmlObject()));
		}
		map.put(ROWS, dhtmlxJsonObjects);
		return map;
	}

	/**
	 * Gets the abstract xml tree gird entities.
	 *
	 * @param parentId the parent id
	 * @param data the data
	 * @return the abstract xml tree gird entities
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String getAbstractXmlTreeGirdEntities(Long parentId, List<? extends AbstractBaseEntity> data) {
		Rows rows = new Rows();
		rows.setParent(parentId);
		List<Row> list = new ArrayList<Row>(data.size());
		for (AbstractBaseEntity entity : data) {
			Row row = new Row();
			row.setId(entity.getId());
			if (entity.isChildTrem()) {
				row.setCall(1);
				row.setXmlkids(1);
			}
			List<Cell> cells = (List) entity.getDhtmlxJsonOrXmlObject();
			row.setCells(cells);
			list.add(row);
		}
		rows.setRows(list);
		return new JaxbMapper(Rows.class).toXml(rows);
	}

	/**
	 * Gets the abstract xml tree gird entities.
	 *
	 * @param parentId the parent id
	 * @param page the page
	 * @return the abstract xml tree gird entities
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String getAbstractXmlTreeGirdEntities(Long parentId, Page<? extends AbstractBaseEntity> page) {
		Rows rows = new Rows();
		rows.setParent(parentId);
		rows.setTotal_count(page.getTotalItems());
		rows.setPos(page.getOffset());
		List<Row> list = new ArrayList<Row>(page.getResult().size());
		for (AbstractBaseEntity entity : page) {
			Row row = new Row();
			row.setId(entity.getId());
			if (entity.isChildTrem()) {
				row.setCall(1);
				row.setXmlkids(1);
			}
			List<Cell> cells = (List) entity.getDhtmlxJsonOrXmlObject();
			row.setCells(cells);
			list.add(row);
		}
		rows.setRows(list);
		return new JaxbMapper(Rows.class).toXml(rows);
	}

	/**
	 * The Class Rows.
	 *
	 * @author l.xue.nong
	 */
	@XmlRootElement
	public static class Rows {

		/** The parent. */
		private Long parent;
		
		/** The total_count. */
		private Long total_count;
		
		/** The pos. */
		private int pos;
		/** The rows. */
		private List<Row> rows;

		/**
		 * Gets the parent.
		 *
		 * @return the parent
		 */
		@XmlAttribute
		public Long getParent() {
			return parent;
		}

		/**
		 * Sets the parent.
		 *
		 * @param parent the new parent
		 */
		public void setParent(Long parent) {
			this.parent = parent;
		}

		/**
		 * Gets the rows.
		 *
		 * @return the rows
		 */
		@XmlElement(name = "row")
		public List<Row> getRows() {
			return rows;
		}

		/**
		 * Sets the rows.
		 *
		 * @param rows the new rows
		 */
		public void setRows(List<Row> rows) {
			this.rows = rows;
		}

		/**
		 * Gets the total_count.
		 *
		 * @return the total_count
		 */
		@XmlAttribute
		public Long getTotal_count() {
			return total_count;
		}

		/**
		 * Sets the total_count.
		 *
		 * @param total_count the new total_count
		 */
		public void setTotal_count(Long total_count) {
			this.total_count = total_count;
		}

		/**
		 * Gets the pos.
		 *
		 * @return the pos
		 */
		@XmlAttribute
		public int getPos() {
			return pos;
		}

		/**
		 * Sets the pos.
		 *
		 * @param pos the new pos
		 */
		public void setPos(int pos) {
			this.pos = pos;
		}

	}

	/**
	 * The Class Row.
	 *
	 * @author l.xue.nong
	 */
	public static class Row {

		/** The id. */
		private Long id;

		/** The call. */
		private Integer call;

		/** The xmlkids. */
		private Integer xmlkids;

		/** The cells. */
		private List<Cell> cells;

		/**
		 * Gets the id.
		 *
		 * @return the id
		 */
		@XmlAttribute
		public Long getId() {
			return id;
		}

		/**
		 * Sets the id.
		 *
		 * @param id the new id
		 */
		public void setId(Long id) {
			this.id = id;
		}

		/**
		 * Gets the call.
		 *
		 * @return the call
		 */
		@XmlAttribute
		public Integer getCall() {
			return call;
		}

		/**
		 * Sets the call.
		 *
		 * @param call the new call
		 */
		public void setCall(Integer call) {
			this.call = call;
		}

		/**
		 * Gets the xmlkids.
		 *
		 * @return the xmlkids
		 */
		@XmlAttribute
		public Integer getXmlkids() {
			return xmlkids;
		}

		/**
		 * Sets the xmlkids.
		 *
		 * @param xmlkids the new xmlkids
		 */
		public void setXmlkids(Integer xmlkids) {
			this.xmlkids = xmlkids;
		}

		/**
		 * Gets the cells.
		 *
		 * @return the cells
		 */
		@XmlElement(name = "cell")
		public List<Cell> getCells() {
			return cells;
		}

		/**
		 * Sets the cells.
		 *
		 * @param cells the new cells
		 */
		public void setCells(List<Cell> cells) {
			this.cells = cells;
		}

	}

	/**
	 * The Class Cell.
	 *
	 * @author l.xue.nong
	 */
	public static class Cell {

		/** The image. */
		private String image;

		/** The value. */
		private String value;

		/**
		 * Instantiates a new cell.
		 */
		public Cell() {
			super();
		}

		/**
		 * Instantiates a new cell.
		 *
		 * @param value the value
		 */
		public Cell(String value) {
			super();
			this.value = value;
		}

		/**
		 * Instantiates a new cell.
		 *
		 * @param image the image
		 * @param value the value
		 */
		public Cell(String image, String value) {
			super();
			this.image = image;
			this.value = value;
		}

		/**
		 * Gets the image.
		 *
		 * @return the image
		 */
		@XmlAttribute
		public String getImage() {
			return image;
		}

		/**
		 * Sets the image.
		 *
		 * @param image the new image
		 */
		public void setImage(String image) {
			this.image = image;
		}

		/**
		 * Gets the value.
		 *
		 * @return the value
		 */
		@XmlValue
		public String getValue() {
			return value;
		}

		/**
		 * Sets the value.
		 *
		 * @param value the new value
		 */
		public void setValue(String value) {
			this.value = value;
		}

	}

}
