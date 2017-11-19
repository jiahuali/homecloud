package com.changan.homecloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.changan.homecloud.po.Device;
import com.changan.homecloud.po.Operation;
import com.changan.homecloud.po.Result;
import com.changan.homecloud.service.DeviceService;
import com.changan.homecloud.service.OperationService;

@RequestMapping("/operation")
@Controller
/**
 * 
 * @author ljh
 *
 */
public class OperationController {

	@Autowired
	private OperationService operationService;

	@ResponseBody
	@RequestMapping(value = "/getOperationByUuid.do", method = RequestMethod.GET)
	public Result getOperationByUuid(@RequestParam String token, @RequestParam String phone,
			@RequestParam String uuid) {
		try {
			return operationService.getOperationByUuid(phone, uuid);
		} catch (Exception e) {
			return new Result().serverError();
		}

	}

	@ResponseBody
	@RequestMapping(value = "/listOperationsByPhone", method = RequestMethod.GET)
	public Result listDevicesByPhone(@RequestParam String token, @RequestParam String phone, @RequestParam Integer id,
			Operation conditions) {
		try {
			return operationService.getOperationListByPhone(phone, id, conditions);
		} catch (Exception e) {
			return new Result().serverError();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/addOperationsByPhone.do", method = RequestMethod.POST)
	public Result addDevice(@RequestParam String token, @RequestParam String phone, @RequestParam Integer id,
			Operation operation) {
		try {
			return operationService.addOperationById(phone, id, operation);
		} catch (Exception e) {
			return new Result<>().serverError();
		}
	}

}
