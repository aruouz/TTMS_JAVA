package xupt.se.ttms.test;

import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.StudioSrv;

public class test_studil {
	public static void main(String[] args){
		Studio stu = new Studio();
		StudioSrv test = new StudioSrv();
		
		stu.setName("123");
		stu.setIntroduction("123456");
		stu.setColCount(2);
		stu.setRowCount(2);
		System.out.print(test.Fetch_name("123"));
	}
}
