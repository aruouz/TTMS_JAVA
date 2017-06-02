package xupt.se.ttms.service;

import xupt.se.ttms.model.Studio;

public class test_studil {
	public static void main(String[] args){
		Studio stu = new Studio();
		StudioSrv test = new StudioSrv();
		
		stu.setName("123");
		stu.setIntroduction("123456");
		stu.setColCount(2);
		stu.setRowCount(2);
		System.out.print(test.Fetch("123"));
	}
}
