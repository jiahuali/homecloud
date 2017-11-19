package com.changan.homecloud.dao;

import com.changan.homecloud.po.Operation;
import com.changan.homecloud.po.OperationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationMapper {
	long countByExample(OperationExample example);

	int deleteByExample(OperationExample example);

	int deleteByPrimaryKey(String uuid);

	int insert(Operation record);

	int insertSelective(Operation record);

	List<Operation> selectByExampleWithBLOBs(OperationExample example);

	List<Operation> selectByExample(OperationExample example);

	Operation selectByPrimaryKey(String uuid);

	int updateByExampleSelective(@Param("record") Operation record, @Param("example") OperationExample example);

	int updateByExampleWithBLOBs(@Param("record") Operation record, @Param("example") OperationExample example);

	int updateByExample(@Param("record") Operation record, @Param("example") OperationExample example);

	int updateByPrimaryKeySelective(Operation record);

	int updateByPrimaryKeyWithBLOBs(Operation record);

	int updateByPrimaryKey(Operation record);
}