package com.changan.homecloud.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.changan.homecloud.dao.OperationMapper;
import com.changan.homecloud.po.Operation;
import com.changan.homecloud.po.OperationExample;
import com.changan.homecloud.po.OperationExample.Criteria;
import com.changan.homecloud.po.Result;
import com.changan.homecloud.po.User;
import com.changan.homecloud.service.OperationService;
import com.changan.homecloud.util.StringUtils;
import com.changan.homecloud.util.UuidUtil;

@Service
@Transactional
public class OperationServiceImpl implements OperationService {
	@Autowired
	private OperationMapper operationMapper;

	@Override
	public Result addOperationById(String phone, Integer id, Operation operation) {
		try {
			if (StringUtils.isNull(phone) || id == null)
				return new Result<User>().paramNull();
			operation.setId(id);
			operation.setUuid(UuidUtil.generateUuid());
			operation.setPhone(phone);
			operation.setTime(new Date());
			int rows = operationMapper.insertSelective(operation);
			if (rows != 1)
				return new Result<>().insertFailed("insert failed");
			return new Result<>().ok();
		} catch (Exception e) {
			return new Result<User>().serverError();
		}
	}

	@Override
	public Result getOperationByUuid(String phone, String uuid) {
		try {
			if (StringUtils.isNull(phone, uuid))
				return new Result<>().paramNull();
			OperationExample example = new OperationExample();
			example.createCriteria().andUuidEqualTo(uuid).andPhoneEqualTo(phone);
			List<Operation> operationList = operationMapper.selectByExampleWithBLOBs(example);

			if (operationList == null || operationList.isEmpty())
				return new Result<>().noRecord("no record");
			return new Result<>().ok(operationList.get(0));
		} catch (Exception e) {
			return new Result<User>().serverError();
		}
	}

	@Override
	public Result getOperationListByPhone(String phone, Integer id, Operation conditions) {
		try {
			if (StringUtils.isNull(phone) || id == null)
				return new Result<>().paramNull();
			OperationExample example = new OperationExample();
			Criteria criteria = example.createCriteria();
			criteria.andPhoneEqualTo(phone);
			criteria.andIdEqualTo(id);
			if (conditions.getType() != null) {
				criteria.andTypeEqualTo(conditions.getType());
			}
			List<Operation> operationList = operationMapper.selectByExampleWithBLOBs(example);

			if (operationList == null || operationList.isEmpty())
				return new Result<>().noRecord("no record");
			return new Result<>().ok(operationList);
		} catch (Exception e) {
			return new Result<User>().serverError();
		}
	}

}
