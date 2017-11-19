package com.changan.homecloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.changan.homecloud.dao.UserMapper;
import com.changan.homecloud.po.Result;
import com.changan.homecloud.po.User;
import com.changan.homecloud.service.UserService;
import com.changan.homecloud.util.StringUtils;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public Result<User> getUserByPhone(String phone) {
		try {
			if (StringUtils.isNull(phone))
				return new Result<User>().paramNull();
			User user = userMapper.selectByPrimaryKey(phone);
			if (user == null)
				return new Result<User>().noRecord("no record");
			return new Result<User>().ok(user);
		} catch (Exception e) {
			return new Result<User>().serverError();
		}
	}

	@Override
	public Result updateUserByPhone(String phone, User user) {
		try {
			if (StringUtils.isNull(phone))
				return new Result<>().paramNull();
			user.setPhone(phone);
			int rows = userMapper.updateByPrimaryKeySelective(user);
			if (rows == 0)
				return new Result<>().updateFailed("更新失败，检查参数");
			return new Result<>().ok();
		} catch (Exception e) {
			return new Result<>().serverError();
		}
	}

	@Override
	public Result getUserList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result deleteUserByPhone(String phone) {
		try {
			if (StringUtils.isNull(phone))
				return new Result<>().paramNull();

			int rows = userMapper.deleteByPrimaryKey(phone);
			if (rows == 0)
				return new Result<>().deleteFailed();
			return new Result<>().ok();
		} catch (Exception e) {
			return new Result<>().serverError();
		}
	}

	@Override
	public Result addUser(String phone, String password, User user) {
		try {
			if (StringUtils.isNull(phone, password))
				return new Result<>().paramNull();

			int rows = userMapper.insertSelective(user);
			if (rows == 0)
				return new Result<>().updateFailed("更新失败，检查参数");
			return new Result<>().ok();
		} catch (Exception e) {
			return new Result<>().serverError();
		}
	}

	@Override
	public Result login(String phone, String password) {
		try {
			if (StringUtils.isNull(phone, password))
				return new Result<>().paramNull();

			Result<User> result = getUserByPhone(phone);
			// 账号错误
			if (result.getCode() != 200)
				return new Result<>().loginFailed("账号不存在");
			// 密码错误
			if (!result.getData().getPassword().equals(password))
				return new Result<>().loginFailed("密码错误");
			// TODO 需要在Controller层返回token
			return new Result<>().ok();
		} catch (Exception e) {
			return new Result<>().serverError();
		}
	}

}
