package com.changan.homecloud.dao;

import com.changan.homecloud.po.LoginRecord;
import com.changan.homecloud.po.LoginRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRecordMapper {
	long countByExample(LoginRecordExample example);

	int deleteByExample(LoginRecordExample example);

	int insert(LoginRecord record);

	int insertSelective(LoginRecord record);

	List<LoginRecord> selectByExampleWithBLOBs(LoginRecordExample example);

	List<LoginRecord> selectByExample(LoginRecordExample example);

	int updateByExampleSelective(@Param("record") LoginRecord record, @Param("example") LoginRecordExample example);

	int updateByExampleWithBLOBs(@Param("record") LoginRecord record, @Param("example") LoginRecordExample example);

	int updateByExample(@Param("record") LoginRecord record, @Param("example") LoginRecordExample example);
}