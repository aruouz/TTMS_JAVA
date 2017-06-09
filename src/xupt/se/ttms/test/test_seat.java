package xupt.se.ttms.test;
import java.util.List;

import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.SeatSrv;

public class test_seat {
	public static void main(String[] args){
		SeatSrv test1 = new SeatSrv();
		Studio stu = new Studio();
		
		stu.setID(2);
		stu.setColCount(20);
		stu.setRowCount(20);
		
	}
}
