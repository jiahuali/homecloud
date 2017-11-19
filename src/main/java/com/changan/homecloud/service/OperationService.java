package com.changan.homecloud.service;

import com.changan.homecloud.po.Operation;
import com.changan.homecloud.po.Result;

public interface OperationService {

	public Result addOperationById(String phone, Integer id, Operation operation);

	public Result getOperationByUuid(String phone, String uuid);

	public Result getOperationListByPhone(String phone, Integer id, Operation conditions);

}
