package com.changan.homecloud.service;

import com.changan.homecloud.po.Device;
import com.changan.homecloud.po.Result;

public interface DeviceService {

	public Result addDevice(Integer id, String phone, Device device);

	public Result updateDeviceById(Integer id, Device device);

	public Result deleteDeviceById(Integer id);

	public Result getDeviceListByPhone(String phone);

	// TODO 是否加入phone和Id
	public Result getDeviceById(String phone, Integer id);

}
