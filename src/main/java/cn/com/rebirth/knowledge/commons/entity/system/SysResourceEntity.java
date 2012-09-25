/**

* Copyright (c) 2005-2011 www.china-cti.com
* Id: SysResource.java 2011-6-25 22:16:00 l.xue.nong$$
*/
package cn.com.rebirth.knowledge.commons.entity.system;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotBlank;

import cn.com.rebirth.commons.search.annotation.AbstractSearchProperty;
import cn.com.rebirth.commons.utils.CollectionMapperUtils;
import cn.com.rebirth.commons.utils.ConvertUtils;
import cn.com.rebirth.knowledge.commons.dhtmlx.ColumnDataSets;
import cn.com.rebirth.knowledge.commons.dhtmlx.DhtmlxJsonObjectUtils.Cell;
import cn.com.rebirth.knowledge.commons.dhtmlx.GridType;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlColumn;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.Dhtmlx;
import cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlxBaseType;
import cn.com.rebirth.knowledge.commons.dhtmlx.data.IColumnConverter;
import cn.com.rebirth.knowledge.commons.dhtmlx.entity.AbstractDhtmlxBaseEntity;
import cn.com.rebirth.knowledge.commons.dhtmlx.entity.Grid;
import cn.com.rebirth.knowledge.commons.entity.system.SysGroupEntity.RoleEntitiesColumnDataSet;

import com.google.common.collect.Lists;

/**
 * The Class SysResource.
 *
 * @author l.xue.nong
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "SYS_RESOURCES")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Dhtmlx(createCheckAllColumn = false, dataType = "xml", gridType = GridType.dhtmlxTreeGrid, isPage = false, isDataProcessor = true, enableSearchBar = false)
public class SysResourceEntity extends AbstractDhtmlxBaseEntity {
	public static final String RESOURCE_PARAM = "_resource_id";
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8662302010561896906L;

	/** The Constant QUERY_BY_RESOURCETYPE_WITH_AUTHORITY. */
	public static final String QUERY_BY_RESOURCETYPE_WITH_AUTHORITY = "select distinct r from SysResourceEntity r left join fetch r.authorityList WHERE r.resourceType=? ORDER BY r.position ASC";

	/** The Constant QUERY_BY_RESOURCETYPE_WITH_NO_NULL_AUTHORITY. */
	public static final String QUERY_BY_RESOURCETYPE_WITH_NO_NULL_AUTHORITY = "select distinct r from SysResourceEntity r left join fetch r.authorityList WHERE r.valueName=? and r.parentId is null ORDER BY r.position ASC";

	/** The Constant QUERY_BY_RESOURCEPARENT_WHERE_NULL. */
	public static final String QUERY_BY_RESOURCEPARENT_WHERE_NULL = "select distinct r from SysResourceEntity r left join fetch r.childResource where r.parentId is null and r.resourceType=? order by r.position asc";
	// -- resourceType常量 --//
	/** The Constant URL_TYPE. */
	public static final String URL_TYPE = "url";

	/** The Constant MENU_TYPE. */
	public static final String MENU_TYPE = "menu";

	/** The resource type. */
	private String resourceType;// 资源类型

	/** The value name. */
	private String valueName;// 资源名称

	/** The value. */
	private String value;// 资源标识

	/** The position. */
	private double position;// 资源排序字段

	/** The is show menu. */
	private boolean showMenu = false;// 是否左边显示

	/** The open menu. */
	private boolean openMenu = false;//是否展开,只针对menu

	/** The request type. */
	private String requestType;//该资源请求的方法,default get

	/**
	 * Checks if is open menu.
	 *
	 * @return true, if is open menu
	 */
	@DhtmlColumn(columnIndex = 6, headerName = "是否展开", columnConverter = BooleanConveter.class, coulumnType = DhtmlxBaseType.ACHECK)
	public boolean isOpenMenu() {
		return openMenu;
	}

	/**
	 * Sets the open menu.
	 *
	 * @param openMenu the new open menu
	 */
	public void setOpenMenu(boolean openMenu) {
		this.openMenu = openMenu;
	}

	/**
	 * Checks if is show menu.
	 *
	 * @return true, if is show menu
	 */
	@DhtmlColumn(columnIndex = 5, headerName = "是否菜单", columnConverter = BooleanConveter.class, coulumnType = DhtmlxBaseType.ACHECK)
	public boolean isShowMenu() {
		return showMenu;
	}

	/**
	 * Sets the show menu.
	 *
	 * @param isShowMenu the new show menu
	 */
	public void setShowMenu(boolean isShowMenu) {
		this.showMenu = isShowMenu;
	}

	/** The authority list. */
	private List<SysAuthorityEntity> authorityList = Lists.newArrayList();

	/** The parent id. */
	private Long parentId;

	/** The parent resource. */
	private SysResourceEntity parentResource;

	/** The child resource. */
	private List<SysResourceEntity> childResource = Lists.newArrayList();

	/** The button entitie. */
	private List<SysPageButtonEntity> buttonEntitie = Lists.newArrayList();

	/**
	 * 可访问该资源的授权名称字符串, 多个授权用','分隔.
	 *
	 * @return the auth names
	 */
	@Transient
	public String getAuthNames() {
		return CollectionMapperUtils.extractToString(authorityList, "name", ",");
	}

	/**
	 * Gets the auth ids.
	 *
	 * @return the auth ids
	 */
	@Transient
	public String getAuthIds() {
		return CollectionMapperUtils.extractToString(authorityList, "id", ",");
	}

	/**
	 * Gets the resource type.
	 *
	 * @return the resource type
	 */

	/**
	 * Gets the resource type.
	 *
	 * @return the resource type
	 */
	@DhtmlColumn(columnIndex = 2, headerName = "资源类型", coulumnType = DhtmlxBaseType.CORO, columnDataSets = ResourceTypeColumnDataSets.class)
	@NotBlank
	public String getResourceType() {
		return resourceType;
	}

	/**
	 * Sets the resource type.
	 *
	 * @param resourceType the new resource type
	 */
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	@DhtmlColumn(columnIndex = 1, headerName = "资源标识")
	@NotBlank
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

	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	@DhtmlColumn(columnIndex = 4, headerName = "排序", coulumnType = DhtmlxBaseType.DYN)
	public double getPosition() {
		return position;
	}

	/**
	 * Sets the position.
	 *
	 * @param position the new position
	 */
	public void setPosition(double position) {
		this.position = position;
	}

	/**
	 * Gets the authority list.
	 *
	 * @return the authority list
	 */

	/**
	 * Gets the authority list.
	 *
	 * @return the authority list
	 */
	@ManyToMany
	@JoinTable(name = "SYS_RESOURCES_AUTHORITIES", joinColumns = { @JoinColumn(name = "RESOURCE_ID") }, inverseJoinColumns = { @JoinColumn(name = "AUTHORITY_ID") })
	@Fetch(FetchMode.JOIN)
	@OrderBy("id DESC")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@DhtmlColumn(columnId = "authorDisplayNames", columnIndex = 3, headerName = "权限", coulumnType = DhtmlxBaseType.CLIST, columnDataSets = AuthorityListColumnDataSets.class, sortable = false)
	public List<SysAuthorityEntity> getAuthorityList() {
		return authorityList;
	}

	/**
	 * Sets the authority list.
	 *
	 * @param authorityList the new authority list
	 */
	public void setAuthorityList(List<SysAuthorityEntity> authorityList) {
		this.authorityList = authorityList;
	}

	/**
	 * Gets the value name.
	 *
	 * @return the value name
	 */
	@DhtmlColumn(columnIndex = 0, headerName = "资源名称", coulumnType = DhtmlxBaseType.TREE)
	@NotBlank
	public String getValueName() {
		return valueName;
	}

	/**
	 * Sets the value name.
	 *
	 * @param valueName the new value name
	 */
	public void setValueName(String valueName) {
		this.valueName = valueName;
	}

	/**
	 * Gets the parent id.
	 *
	 * @return the parent id
	 */
	@Column(insertable = false, updatable = false)
	public Long getParentId() {
		return parentId;
	}

	/**
	 * Sets the parent id.
	 *
	 * @param parentId the new parent id
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * Gets the parent resource.
	 *
	 * @return the parent resource
	 */
	@ManyToOne
	@JoinColumn(name = "parentId")
	public SysResourceEntity getParentResource() {
		return parentResource;
	}

	/**
	 * Sets the parent resource.
	 *
	 * @param parentResource the new parent resource
	 */
	public void setParentResource(SysResourceEntity parentResource) {
		this.parentResource = parentResource;
	}

	/**
	 * Gets the child resource.
	 *
	 * @return the child resource
	 */

	/**
	 * Gets the child resource.
	 *
	 * @return the child resource
	 */
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "parentResource")
	@OnDelete(action = OnDeleteAction.CASCADE)
	public List<SysResourceEntity> getChildResource() {
		return childResource;
	}

	/**
	 * Sets the child resource.
	 *
	 * @param childResource the new child resource
	 */
	public void setChildResource(List<SysResourceEntity> childResource) {
		this.childResource = childResource;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/* (non-Javadoc)
	 * @see cn.com.rebirth.knowledge.commons.dhtmlx.entity.AbstractDhtmlxBaseEntity#getDhtmlxJsonOrXmlObject()
	 */
	/**
	 * Gets the dhtmlx json or xml object.
	 *
	 * @return the dhtmlx json or xml object
	 */
	@Override
	@Transient
	public List<Object> getDhtmlxJsonOrXmlObject() {
		List<Object> cells = Lists.newArrayList();
		cells.add(new Cell("folder.gif", getValueName()));
		cells.add(new Cell(getValue()));
		cells.add(new Cell(getResourceType()));
		cells.add(new Cell(getAuthIds()));
		cells.add(new Cell(getPosition() + ""));
		cells.add(new Cell(isShowMenu() ? "1" : "0"));
		cells.add(new Cell(isOpenMenu() ? "1" : "0"));
		cells.add(new Cell(getRequestType() == null ? "" : getRequestType()));
		cells.add(new Cell(CollectionMapperUtils.extractToString(buttonEntitie, "id", ",")));
		return cells;
	}

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
		return getChildResource().size() > 0;
	}

	/**
	 * The Class BooleanConveter.
	 *
	 * @author l.xue.nong
	 */
	class BooleanConveter implements IColumnConverter {

		/* (non-Javadoc)
		 * @see cn.com.rebirth.knowledge.commons.dhtmlx.data.IColumnConverter#convert(cn.com.rebirth.commons.search.annotation.AbstractSearchProperty, cn.com.rebirth.knowledge.commons.dhtmlx.annotation.DhtmlColumn, cn.com.rebirth.knowledge.commons.dhtmlx.entity.AbstractDhtmlxBaseEntity, java.lang.Object)
		 */
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
			Boolean b = ConvertUtils.convertObjectToObject(converterValue, Boolean.class);
			return b ? "1" : "0";
		}

	}

	/**
	 * The Class ResourceTypeColumnDataSets.
	 *
	 * @author l.xue.nong
	 */
	public static class ResourceTypeColumnDataSets implements ColumnDataSets {

		/**
		 * Bulid column data.
		 *
		 * @param columnIndex the column index
		 * @param column the column
		 * @param grid the grid
		 * @param stringBuilder the string builder
		 */
		@Override
		public void bulidColumnData(int columnIndex, cn.com.rebirth.knowledge.commons.dhtmlx.entity.Column column,
				Grid grid, StringBuilder stringBuilder) {
			stringBuilder.append(grid.getId()).append(".getCombo(" + columnIndex + ")")
					.append(".put(\"url\",\"url\");\n");
			stringBuilder.append(grid.getId()).append(".getCombo(" + columnIndex + ")")
					.append(".put(\"menu\",\"menu\");\n");
		}

	}

	/**
	 * The Class AuthorityListColumnDataSets.
	 *
	 * @author l.xue.nong
	 */
	public static class AuthorityListColumnDataSets implements ColumnDataSets {

		/** The authority entities. */
		private final List<SysAuthorityEntity> authorityEntities;

		/**
		 * Instantiates a new authority list column data sets.
		 *
		 * @param authorityEntities the authority entities
		 */
		public AuthorityListColumnDataSets(List<SysAuthorityEntity> authorityEntities) {
			super();
			this.authorityEntities = authorityEntities;
		}

		/* (non-Javadoc)
		 * @see cn.com.rebirth.knowledge.commons.dhtmlx.ColumnDataSets#bulidColumnData(int, cn.com.rebirth.knowledge.commons.dhtmlx.entity.Column, cn.com.rebirth.knowledge.commons.dhtmlx.entity.Grid, java.lang.StringBuilder)
		 */
		@Override
		public void bulidColumnData(int columnIndex, cn.com.rebirth.knowledge.commons.dhtmlx.entity.Column column,
				Grid grid, StringBuilder stringBuilder) {
			stringBuilder.append(grid.getId()).append(".registerCList(").append(columnIndex).append(",").append("[\"")
					.append(CollectionMapperUtils.extractToString(authorityEntities, "displayName", "\",\""))
					.append("\"]").append(",").append("[\"")
					.append(CollectionMapperUtils.extractToString(authorityEntities, "id", "\",\"")).append("\"]")
					.append(");\n");
		}
	}

	/**
	 * Gets the request type.
	 *
	 * @return the request type
	 */
	@DhtmlColumn(columnIndex = 7, headerName = "请求类型")
	public String getRequestType() {
		return requestType;
	}

	/**
	 * Sets the request type.
	 *
	 * @param requestType the new request type
	 */
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	/**
	 * Gets the button entitie.
	 *
	 * @return the button entitie
	 */
	@ManyToMany
	@JoinTable(name = "SYS_RESOURCES_PAGE_BUTTON", joinColumns = { @JoinColumn(name = "RESOURCE_ID") }, inverseJoinColumns = { @JoinColumn(name = "BUTTON_ID") })
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("id DESC")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@DhtmlColumn(columnIndex = 8, headerName = "按钮", coulumnType = DhtmlxBaseType.GRID, columnDataSets = RoleEntitiesColumnDataSet.class, columnId = "buttons")
	public List<SysPageButtonEntity> getButtonEntitie() {
		return buttonEntitie;
	}

	/**
	 * Sets the button entitie.
	 *
	 * @param buttonEntitie the new button entitie
	 */
	public void setButtonEntitie(List<SysPageButtonEntity> buttonEntitie) {
		this.buttonEntitie = buttonEntitie;
	}

}
