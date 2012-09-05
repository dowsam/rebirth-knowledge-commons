/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons Shell.java 2012-8-29 9:01:42 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons;

import java.io.Serializable;

/**
 * The Interface Shell.
 *
 * @param <T> the generic type
 * @author l.xue.nong
 */
public interface Shell<T extends Serializable> {

	/**
	 * Passive.
	 *
	 * @param t the t
	 */
	void passive(T t);

	/**
	 * Initiative.
	 *
	 * @return the t
	 */
	T initiative();
}
