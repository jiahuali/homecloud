package com.changan.homecloud.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.changan.homecloud.po.Device;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext.xml" })
public class DeviceServiceTest {
	@Autowired
	private DeviceService deviceService;

	@Test
	public void testInsert() {
		Device device = new Device();
		device.setId(3);
		device.setPhone("18580282962");
		device.setName("厕所马桶");
		device.setUpdateTime(new Date());
		deviceService.addDevice(3, "18580282962", device);
	}

}
