package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iTicketDAO;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.Ticket;

public class TicketSrv {
	
	private static  iTicketDAO ticDAO = DAOFactory.createTicketDAO(); 
	
	public int add(Ticket tic){
		return ticDAO.insert(tic);
	}
	
	public static int modify(Ticket tic){
		return ticDAO.update(tic);
	}
	
	public List<Ticket> FetchAll(){
		return ticDAO.select("");
	}
	
	public static List<Ticket> Fetch_ID(int ID){
		return ticDAO.select(" WHERE ticket_id ="+ ID);
	}
	
	public static List<Ticket> Fetch_studio(int ID){
		return ticDAO.select("  WHERE seat_id = "+ ID);
	}

	public static List<Ticket> Fetch_sched(int ID){
		return ticDAO.select("  WHERE sched_id = "+ ID);
	}
	
	public static int lock(int ID, String time){
		return ticDAO.lockTicket(ID, time);
	}
	
	public static int unlock(int ID){
		return ticDAO.unlockTicket(ID);
	}

	public static int delete(int ticket_id) {
		return ticDAO.delete(ticket_id);
		
	}
	

	
}