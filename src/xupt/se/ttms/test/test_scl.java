package xupt.se.ttms.test;


import java.util.Date;

import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.service.ScheduleSrv;

public class test_scl {
	public static void main(String [] args){
		Schedule scl = new Schedule();
		ScheduleSrv test = new ScheduleSrv();
		
		scl.setPlay_id(41);
		scl.setSched_ticket_price(36);
		
		
		scl.setSched_time("2017-8-3 08:00:00");
		scl.setStudio_id(12);
		
		test.addscl(scl);
		System.out.println("创建成功啦啦啦啦♪(^∇^*)");
	}
}
