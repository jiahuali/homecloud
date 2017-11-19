package com.changan.homecloud.dao;

import com.changan.homecloud.po.User;
import com.changan.homecloud.po.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
	long countByExample(UserExample example);

	int deleteByExample(UserExample example);

	int deleteByPrimaryKey(String phone);

	int insert(User record);

	int insertSelective(User record);

	List<User> selectByExampleWithBLOBs(UserExample example);

	List<User> selectByExample(UserExample example);

	User selectByPrimaryKey(String phone);

	int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

	int updateByExampleWithBLOBs(@Param("record") User record, @Param("example") UserExample example);

	int updateByExample(@Param("record") User record, @Param("example") UserExample example);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKeyWithBLOBs(User record);

	int updateByPrimaryKey(User record);
}