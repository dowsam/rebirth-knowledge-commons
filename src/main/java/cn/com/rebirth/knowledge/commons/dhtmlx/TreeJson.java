/**
* Copyright (c) 2005-2011 www.china-cti.com
* Id: TreeJson.java 2011-1-9 18:16:34 l.xue.nong$$
*/
package cn.com.rebirth.knowledge.commons.dhtmlx;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

/**
 * Dhtmlx Tree json top.
 *
 * @author l.xue.nong
 */
public class TreeJson implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2834249906576010852L;

	/** The id. */
	private Object id;

	/** The item. */
	private List<? extends Object> item = Lists.newArrayList();

	/**
	 * Instantiates a new tree json.
	 */
	public TreeJson() {
		super();
	}

	/**
	 * Instantiates a new tree json.
	 *
	 * @param id the id
	 * @param item the item
	 */
	public TreeJson(Object id, List<? extends Object> item) {
		super();
		this.id = id;
		this.item = item;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Object getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Object id) {
		this.id = id;
	}

	/**
	 * Gets the item.
	 *
	 * @return the item
	 */
	public List<? extends Object> getItem() {
		return item;
	}

	/**
	 * Sets the item.
	 *
	 * @param item the new item
	 */
	public void setItem(List<? extends Object> item) {
		this.item = item;
	}

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		TreeJson json = new TreeJson(0, Lists.newArrayList());
		ObjectMapper objectMapper = new ObjectMapper();
		String s = objectMapper.writeValueAsString(json);
		System.out.println(s);
	}

}
