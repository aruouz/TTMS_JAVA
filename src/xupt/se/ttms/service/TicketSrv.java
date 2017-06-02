package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iTicketDAO;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.Ticket;

public class TicketSrv {
	
	private  iTicketDAO ticDAO = DAOFactory.createTicketDAO(); 
	
	public int add(Ticket tic){
		return ticDAO.insert(tic);
	}
	
	public int modify(Ticket tic){
		return ticDAO.update(tic);
	}
	
	public List<Ticket> FetchAll(){
		return ticDAO.select("");
	}
	
	public List<Ticket> Fetch_ID(int ID){
		return ticDAO.select(" WHERE ticket_id = "+ ID);
	}
	
	public int lock(int ID, String time){
		return ticDAO.lockTicket(ID, time);
	}
	
	public int unlock(int ID){
		return ticDAO.unlockTicket(ID);
	}
	
	public int addscl(Schedule scl){
		ScheduleSrv sche = new ScheduleSrv();
		StudioSrv stus = new StudioSrv();
		SeatSrv seat = new SeatSrv();
		sche.add(scl);
		
		List<Schedule> lists = ScheduleSrv.fetchall();
		for(int i = 0;i<lists.size();i++){
			scl = (Schedule)lists.get(i);
		}
		Studio studio = new Studio();
		List<Studio> list = StudioSrv.Fetch_id(scl.getStudio_id());
		for(int i = 0;i<list.size();i++){
			studio = (Studio)list.get(i);
		}
		Seat seat1 = new Seat();
		int seat_start=0;
		List<Seat> listss = SeatSrv.Fetch(scl.getSched_id());
		for(int i = 0; i<listss.size();i++){
			seat1 = (Seat)listss.get(i);
			seat_start = seat1.getSeat_id();
			break;
		}
		int seat_num = studio.getColCount() *studio.getRowCount();
		
		for(int i=0;i<seat_num;i++){
			Ticket tic =new Ticket();
			tic.setSched_id(scl.getSched_id());
			tic.setTicket_locked_time("");
			tic.setTicket_price(scl.getSched_ticket_price());
			tic.setTicket_status(0);
			tic.setSeat_id(seat_start+i);
			add(tic);
		}
		return 1;
	}
}
