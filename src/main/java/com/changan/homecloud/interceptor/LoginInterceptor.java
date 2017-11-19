package com.changan.homecloud.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.changan.homecloud.po.OnlineUser;
import com.changan.homecloud.po.OnlineUserHandler;
import com.changan.homecloud.po.Result;
import com.changan.homecloud.util.StringUtils;

public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private OnlineUserHandler onlineUserHandler;

	private static Logger logger = Logger.getLogger(LoginInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object handler, Exception exception)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse resp, Object handler, ModelAndView mv)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		String token = req.getParameter("token");
		if (StringUtils.isNull(token)) {
			// req.getRequestDispatcher("/login.html").forward(req, resp);
			// resp.sendRedirect("/icar-admin/login.html");
			resp.getOutputStream().print(JSON.toJSONString(new Result<>().authFailed("auth failed")));
			logger.info("no auth");
			return false;
		}

		OnlineUser onlineUser = onlineUserHandler.getOnlineUserByToken(token);
		if (onlineUser == null) {
			// req.getRequestDispatcher("/login.html").forward(req, resp);

			resp.getOutputStream().print(JSON.toJSONString(new Result<>().authFailed("auth failed"), true));
			logger.info("no auth");
			logger.info("token:" + token + " is invalid");
			return false;
		}

		return true;
	}

}
