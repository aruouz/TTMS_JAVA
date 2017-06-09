package xupt.se.ttms.test;

import xupt.se.ttms.model.Seat;
import xupt.se.ttms.service.SeatSrv;

public class Seat_test {
	public static void main(String[] args){
		Seat seat =new Seat();
		seat.setSeat_row(1);
		seat.setSeat_column(1);
		seat.setStudio_id(12);
		
		System.out.println(SeatSrv.Fetch_r_c(seat.getSeat_row(), seat.getSeat_column(), seat.getStudio_id()));
		
	}
}
