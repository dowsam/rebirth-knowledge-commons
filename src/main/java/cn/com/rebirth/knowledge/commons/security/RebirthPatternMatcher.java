/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons RebirthPatternMatcher.java 2012-8-24 14:11:29 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.security;

import org.apache.shiro.util.PatternMatcher;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 * The Class RebirthPatternMatcher.
 *
 * @author l.xue.nong
 */
public class RebirthPatternMatcher implements PatternMatcher {

	/** The path matcher. */
	private final PathMatcher pathMatcher;

	/**
	 * Instantiates a new rebirth pattern matcher.
	 */
	public RebirthPatternMatcher() {
		this(new AntPathMatcher());
	}

	/**
	 * Instantiates a new rebirth pattern matcher.
	 *
	 * @param pathMatcher the path matcher
	 */
	public RebirthPatternMatcher(PathMatcher pathMatcher) {
		super();
		this.pathMatcher = pathMatcher;
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.util.PatternMatcher#matches(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean matches(String pattern, String source) {
		return this.pathMatcher.match(pattern, source);
	}

}
