/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-web-admin JsonActionTemplate.java 2012-8-4 14:05:14 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.rebirth.commons.exception.RebirthException;
import cn.com.rebirth.commons.utils.ResponseTypeOutputUtils;
import cn.com.rebirth.core.mapper.JsonMapper;

import com.google.common.collect.Maps;

/**
 * The Class JsonActionTemplate.
 *
 * @author l.xue.nong
 */
public final class JsonActionTemplate {

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(JsonActionTemplate.class);

	/**
	 * The Interface MeesageCallback.
	 *
	 * @author l.xue.nong
	 */
	public static interface MeesageCallback {

		/**
		 * Business.
		 *
		 * @param returnMsg the return msg
		 * @throws RebirthException the rebirth exception
		 */
		void business(Map<String, Object> returnMsg) throws RebirthException;

		/**
		 * To writer.
		 *
		 * @param object the object
		 * @return the string
		 * @throws RebirthException the rebirth exception
		 */
		String toWriter(Object object) throws RebirthException;
	}

	/**
	 * The Class DefaultMeesageCallback.
	 *
	 * @author l.xue.nong
	 */
	public static abstract class AbstractMeesageCallback implements MeesageCallback {

		/* (non-Javadoc)
		 * @see cn.com.rebirth.knowledge.web.admin.JsonActionTemplate.MeesageCallback#toWriter(java.lang.Object)
		 */
		@Override
		public String toWriter(Object object) throws RebirthException {
			return JsonMapper.nonDefaultMapper().toJson(object);
		}

	}

	/**
	 * The Class AbstractResponseMeesageCallback.
	 *
	 * @author l.xue.nong
	 */
	public static abstract class AbstractResponseMeesageCallback extends AbstractMeesageCallback implements
			MeesageCallback {

		/** The response. */
		private final HttpServletResponse response;

		/**
		 * Instantiates a new abstract response meesage callback.
		 *
		 * @param response the response
		 */
		public AbstractResponseMeesageCallback(HttpServletResponse response) {
			super();
			this.response = response;
		}

		/* (non-Javadoc)
		 * @see cn.com.rebirth.knowledge.web.admin.JsonActionTemplate.AbstractMeesageCallback#toWriter(java.lang.Object)
		 */
		@Override
		public String toWriter(Object object) throws RebirthException {
			ResponseTypeOutputUtils.renderJson(response, object);
			return super.toWriter(object);
		}

	}

	/**
	 * The Class SingleObjectMeesageCallBack.
	 *
	 * @author l.xue.nong
	 */
	public static abstract class SingleObjectMeesageCallBack extends AbstractResponseMeesageCallback implements
			MeesageCallback {

		/**
		 * Instantiates a new single object meesage call back.
		 *
		 * @param response the response
		 */
		public SingleObjectMeesageCallBack(HttpServletResponse response) {
			super(response);
		}

		/* (non-Javadoc)
		 * @see cn.com.rebirth.knowledge.web.admin.JsonActionTemplate.MeesageCallback#business(java.util.Map)
		 */
		@Override
		public void business(Map<String, Object> returnMsg) throws RebirthException {
			Object o = toObject();
			returnMsg.put(SingleObjectMeesageCallBack.class.getName(), o);
		}

		/**
		 * To object.
		 *
		 * @return the object
		 * @throws RebirthException the rebirth exception
		 */
		protected abstract Object toObject() throws RebirthException;
	}

	/**
	 * Render json.
	 *
	 * @param meesageCallback the meesage callback
	 * @return the string
	 */
	public static String renderJson(MeesageCallback meesageCallback) {
		Map<String, Object> returnMsg = Maps.newHashMap();
		try {
			meesageCallback.business(returnMsg);
			returnMsg.put("success", true);
		} catch (Exception e) {
			logger.error("[{}] to Error Msg[{}]", JsonActionTemplate.class, e.getMessage());
			returnMsg.put("msg", e.getMessage());
			returnMsg.put("success", false);
		}
		if (meesageCallback instanceof SingleObjectMeesageCallBack) {
			return meesageCallback.toWriter(returnMsg.get(SingleObjectMeesageCallBack.class.getName()));
		}
		return meesageCallback.toWriter(returnMsg);
	}

}
