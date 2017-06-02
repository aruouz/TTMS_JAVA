package xupt.se.ttms.service;


import java.text.SimpleDateFormat;
import java.util.Date;

import xupt.se.ttms.model.Schedule;

public class test_scl {
	public static void main(String [] args){
		Schedule scl = new Schedule();
		ScheduleSrv test = new ScheduleSrv();
		
		scl.setPlay_id(49);
		scl.setSched_ticket_price(20);
		Date date = new Date();
		System.out.println(date.getTime());
		
		scl.setSched_time("2008-8-8 08:00:00");
		scl.setStudio_id(3);
		
		test.add(scl);
		System.out.println("创建成功啦啦啦啦♪(^∇^*)");
	}
}
