package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iSeatDAO;
import xupt.se.ttms.model.Dict;
import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Studio;

public class SeatSrv {
	private static iSeatDAO setDAO = DAOFactory.createSeatDAO();
	
	public int add(Seat seat){
		return setDAO.insert(seat);
	}
	
	public int modify(Seat seat){
		return setDAO.update(seat);
	}
	
	public static List<Seat> Fetch(int id){
		return setDAO.select(" studio_id= "+id);
	}
	
	public static List<Seat> Fetch_r_c(int row, int col , int id){
		return setDAO.select(" seat_row ="+row+" and seat_column ="+col+" and studio_id = "+id);
	}
	
	public List<Seat> FetchAll(){
		return setDAO.select("");
	}
	
	public int seatadd(Studio stu){
		int rtn = 0;
		Seat seat = new Seat();
		seat.setStudio_id(stu.getID());
		seat.setSeat_status(1);
		
		for(int i=1;i<=stu.getColCount();i++){
			seat.setSeat_column(i);
			for(int j=1;j<=stu.getRowCount();j++){
				seat.setSeat_row(j);
				add(seat);
			}	
	
		}
		return rtn;
	}
	
	public String status(Seat seat){
		String rtn = "";
		if(seat.getSeat_status() == 1){
			rtn = "正常座位";
		}
		if(seat.getSeat_status() == 0){
			rtn = "空缺座位";
		}
		if(seat.getSeat_status() == -1){
			rtn = "损坏座位";
		}
		return rtn;
	}
	

	public int  r_status(String str){
		if(str == "正常座位"){
			return 1;
		}
		else if(str == "空缺座位"){
			return 0;
		}
		else if (str == "损坏座位"){
			return -1;
		}
		return 0;
	}

	public static List<Seat> Fetch_id(int seat_id) {
		return setDAO.select(" seat_id= "+seat_id);
	}	

}
