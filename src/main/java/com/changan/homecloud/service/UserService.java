package com.changan.homecloud.service;

import com.changan.homecloud.po.Result;
import com.changan.homecloud.po.User;

public interface UserService {

	public Result<User> getUserByPhone(String phone);

	public Result updateUserByPhone(String phone, User user);

	public Result getUserList();

	public Result deleteUserByPhone(String phone);

	public Result addUser(String phone, String password, User user);

	public Result login(String phone, String password);

}
