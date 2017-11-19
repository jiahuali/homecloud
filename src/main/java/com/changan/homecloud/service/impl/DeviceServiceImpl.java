package com.changan.homecloud.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.changan.homecloud.dao.DeviceMapper;
import com.changan.homecloud.po.Device;
import com.changan.homecloud.po.DeviceExample;
import com.changan.homecloud.po.Result;
import com.changan.homecloud.service.DeviceService;
import com.changan.homecloud.util.StringUtils;

@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	private DeviceMapper deviceMapper;

	@Override
	public Result addDevice(Integer id, String phone, Device device) {
		try {
			if (StringUtils.isNull(phone) || id == null)
				return new Result<>().paramNull();
			device.setId(id);
			device.setPhone(phone);
			device.setUpdateTime(new Date());
			int rows = deviceMapper.insertSelective(device);
			if (rows != 1)
				return new Result<>().insertFailed("insert failed");
			return new Result<>().ok();
		} catch (Exception e) {
			return new Result<>().serverError();
		}
	}

	@Override
	public Result updateDeviceById(Integer id, Device device) {
		try {
			if (id == null)
				return new Result<>().paramNull();
			device.setId(id);
			int rows = deviceMapper.updateByPrimaryKeySelective(device);
			if (rows != 1)
				return new Result<>().updateFailed("update failed");
			return new Result<>().ok();
		} catch (Exception e) {
			return new Result<>().serverError();
		}
	}

	@Override
	public Result deleteDeviceById(Integer id) {
		try {
			if (id == null)
				return new Result<>().paramNull();
			int rows = deviceMapper.deleteByPrimaryKey(id);
			if (rows != 1)
				return new Result<>().deleteFailed();
			return new Result<>().ok();
		} catch (Exception e) {
			return new Result<>().serverError();
		}
	}

	@Override
	public Result getDeviceListByPhone(String phone) {
		try {
			if (phone == null)
				return new Result<>().paramNull();
			DeviceExample example = new DeviceExample();
			example.createCriteria().andPhoneEqualTo(phone);
			List<Device> deviceList = deviceMapper.selectByExample(example);

			if (deviceList == null || deviceList.isEmpty())
				return new Result<>().noRecord("noRecord");
			return new Result<>().ok(deviceList);
		} catch (Exception e) {
			return new Result<>().serverError();
		}
	}

	@Override
	public Result getDeviceById(String phone, Integer id) {
		try {
			if (StringUtils.isNull(phone) || id == null)
				return new Result<>().paramNull();
			DeviceExample example = new DeviceExample();
			example.createCriteria().andPhoneEqualTo(phone).andIdEqualTo(id);
			List<Device> deviceList = deviceMapper.selectByExample(example);

			if (deviceList == null || deviceList.isEmpty())
				return new Result<>().noRecord("noRecord");
			return new Result<>().ok(deviceList.get(0));
		} catch (Exception e) {
			return new Result<>().serverError();
		}
	}

}
