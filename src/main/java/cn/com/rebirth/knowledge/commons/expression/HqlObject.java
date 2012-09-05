/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons HqlObject.java 2012-9-3 11:16:06 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.expression;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * The Class HqlObject.
 *
 * @author l.xue.nong
 */
public class HqlObject implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1754274649739694723L;
	
	/** The query. */
	private String query;
	
	/** The values. */
	private List<Object> values;
	
	/** The entity class. */
	private Class<?> entityClass;
	
	/** The alias. */
	private String alias;

	/**
	 * Instantiates a new hql object.
	 */
	public HqlObject() {

	}

	/**
	 * Adds the where.
	 *
	 * @param where the where
	 */
	public void addWhere(String where) {
		System.out.println(this.query);
		if (!StringUtils.isBlank(this.query)) {

			if (this.query.indexOf(" where") == -1) {
				this.query = this.query + " where " + where;
			} else {
				this.query = this.query + " and (" + where + ")";
			}
		}
	}

	/**
	 * Adds the codition.
	 *
	 * @param where the where
	 */
	public void addCodition(String where) {

		if (!StringUtils.isBlank(this.query)) {
			this.query = this.query + " and (" + where + ")";
		} else {
			this.query = this.query + where;
		}
	}

	/**
	 * Addleft join.
	 *
	 * @param leftJoin the left join
	 */
	public void addleftJoin(String leftJoin) {
		if (!StringUtils.isBlank(this.query)) {
			if (this.query.indexOf(" where") == -1) {
				this.query = this.query + leftJoin;
			} else {

				this.query = this.query.subSequence(0, this.query.indexOf(" where")) + leftJoin
						+ this.query.subSequence(this.query.indexOf(" where"), this.query.length());
			}
		}
	}

	/**
	 * Adds the group by.
	 *
	 * @param groupby the groupby
	 */
	public void addGroupBy(String groupby) {
		this.query = this.query + " group by " + groupby;

	}

	/**
	 * Adds the order by.
	 *
	 * @param filedName the filed name
	 * @param order the order
	 */
	public void addOrderBy(String filedName, String order) {
		if (!StringUtils.isBlank(this.query)) {
			if (this.query.indexOf(" order by") == -1) {
				this.query = this.query + "  order by " + filedName + " " + order;
			} else {
				this.query = this.query + " ," + filedName + " " + order;
			}
		}
	}

	/**
	 * Adds the reproject.
	 *
	 * @param select the select
	 */
	public void addReproject(String select) {
		this.query = " select " + select + " " + this.query;

	}

	/**
	 * Instantiates a new hql object.
	 *
	 * @param query the query
	 * @param values the values
	 * @param entityClass the entity class
	 */
	public HqlObject(String query, List<Object> values, Class<?> entityClass) {
		this.query = query;
		this.values = values;
		this.entityClass = entityClass;
	}

	/**
	 * Gets the query.
	 *
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * Sets the query.
	 *
	 * @param query the new query
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * Gets the values.
	 *
	 * @return the values
	 */
	public List<Object> getValues() {
		return values;
	}

	/**
	 * Sets the values.
	 *
	 * @param values the new values
	 */
	public void setValues(List<Object> values) {
		this.values = values;
	}

	/**
	 * Gets the entity class.
	 *
	 * @return the entity class
	 */
	public Class<?> getEntityClass() {
		return entityClass;
	}

	/**
	 * Sets the entity class.
	 *
	 * @param entityClass the new entity class
	 */
	public void setEntityClass(Class<?> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * Gets the alias.
	 *
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Sets the alias.
	 *
	 * @param alias the new alias
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}
}
