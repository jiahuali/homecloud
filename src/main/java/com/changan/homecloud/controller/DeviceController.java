package com.changan.homecloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.changan.homecloud.po.Device;
import com.changan.homecloud.po.Result;
import com.changan.homecloud.service.DeviceService;

@RequestMapping("/device")
@Controller
/**
 * 
 * @author ljh
 *
 */
public class DeviceController {

	@Autowired
	private DeviceService deviceService;

	@ResponseBody
	@RequestMapping(value = "/getDeviceById.do", method = RequestMethod.GET)
	public Result getDeviceById(@RequestParam String token, @RequestParam String phone, @RequestParam Integer id) {
		try {
			return deviceService.getDeviceById(phone, id);
		} catch (Exception e) {
			return new Result().serverError();
		}

	}

	@ResponseBody
	@RequestMapping(value = "/listDevicesByPhone", method = RequestMethod.GET)
	public Result listDevicesByPhone(@RequestParam String token, @RequestParam String phone) {
		try {
			return deviceService.getDeviceListByPhone(phone);
		} catch (Exception e) {
			return new Result().serverError();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/updateDeviceById.do", method = RequestMethod.POST)
	public Result updateDeviceById(@RequestParam String token, @RequestParam Integer id, Device device) {
		try {
			return deviceService.updateDeviceById(id, device);
		} catch (Exception e) {
			return new Result<>().serverError();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/deleteDeviceById.do", method = RequestMethod.POST)
	public Result deleteDeviceById(@RequestParam String token, @RequestParam Integer id) {
		try {
			return deviceService.deleteDeviceById(id);
		} catch (Exception e) {
			return new Result<>().serverError();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/addDevice.do", method = RequestMethod.POST)
	public Result addDevice(@RequestParam String token, @RequestParam String phone, @RequestParam Integer id,
			Device device) {
		try {
			return deviceService.addDevice(id, phone, device);
		} catch (Exception e) {
			return new Result<>().serverError();
		}
	}

}
