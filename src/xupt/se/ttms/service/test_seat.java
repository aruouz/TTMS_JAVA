package xupt.se.ttms.service;
import java.util.List;

import xupt.se.ttms.model.Studio;

public class test_seat {
	public static void main(String[] args){
		SeatSrv test1 = new SeatSrv();
		Studio stu = new Studio();
		
		stu.setID(2);
		stu.setColCount(20);
		stu.setRowCount(20);
		System.out.println(test1.FetchAll(stu));
		
	}
}
