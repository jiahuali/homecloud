package com.changan.homecloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.changan.homecloud.po.OnlineUser;
import com.changan.homecloud.po.OnlineUserHandler;
import com.changan.homecloud.po.Result;
import com.changan.homecloud.po.User;
import com.changan.homecloud.service.UserService;
import com.changan.homecloud.util.UuidUtil;

@RequestMapping("/user")
@Controller
/**
 * 
 * @author ljh
 *
 */
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private OnlineUserHandler onlineUserHandler;

	@ResponseBody
	@RequestMapping(value = "/getUserByPhone.do", method = RequestMethod.GET)
	public Result getUserByPhone(@RequestParam String token, @RequestParam String phone) {
		try {
			return userService.getUserByPhone(phone);
		} catch (Exception e) {
			return new Result().serverError();
		}

	}

	@ResponseBody
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public Result login(@RequestParam String phone, @RequestParam String password) {
		try {
			Result result = userService.login(phone, password);
			if (result.getCode() == 200) {
				// 成功后给前端返回token，并将用户放入在线列表
				String token = UuidUtil.generateUuid();
				result.setData(token);
				OnlineUser onlineUser = new OnlineUser();
				onlineUser.setPassword(password);
				onlineUser.setPhone(phone);
				onlineUserHandler.addOnlineUserByToken(token, onlineUser);
			}
			return result;

		} catch (Exception e) {
			return new Result().serverError();
		}

	}

	// @ResponseBody
	// @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
	// public Result getUserList() {
	// try {
	// return userService.getUserList();
	// } catch (Exception e) {
	// return new Result().serverError();
	// }
	// }

	@ResponseBody
	@RequestMapping(value = "/updateUserByPhone.do", method = RequestMethod.POST)
	public Result updateUserByUsername(@RequestParam String token, @RequestParam String phone, User user) {
		try {
			return userService.updateUserByPhone(phone, user);
		} catch (Exception e) {
			return new Result<>().serverError();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/deleteUserByPhone.do", method = RequestMethod.POST)
	public Result deleteUserByPhone(@RequestParam String token, @RequestParam String phone) {
		try {
			return userService.deleteUserByPhone(phone);
		} catch (Exception e) {
			return new Result<>().serverError();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/addUser.do", method = RequestMethod.POST)
	public Result addUser(@RequestParam String phone, @RequestParam String password, User user) {
		try {
			return userService.addUser(phone, password, user);
		} catch (Exception e) {
			return new Result<>().serverError();
		}
	}

}
