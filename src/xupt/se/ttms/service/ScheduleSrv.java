package xupt.se.ttms.service;

import java.util.List;

import javax.print.attribute.standard.RequestingUserName;

import org.omg.CORBA.PUBLIC_MEMBER;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iScheduleDAO;
import xupt.se.ttms.model.Play;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.Ticket;

public class ScheduleSrv {
	  private static iScheduleDAO schDAO = DAOFactory.createScheduleDAO();
	  
	  public int add(Schedule scl){
		  return schDAO.insert(scl);
	  }
	  
	  public int modify(Schedule scl){

		  return schDAO.update(scl);
	  }
	  
	  public int delete(int ID){
		  List<Ticket> list = TicketSrv.Fetch_studio(ID);
		  for(int i=0;i<list.size();i++){
			  TicketSrv.delete(list.get(0).getTicket_id());
		  }
		  return schDAO.delete(ID);
	  }
	  
	  public static List<Schedule> FetchAll(){
		  return schDAO.select("");
	  }
	  
	  public static List<Schedule> Fetch_name(int id){
		  return schDAO.select(" WHERE play_id= "+id);
	  }
	  
	  public static List<Schedule> Fetch_id(int play_id,int studio_id,String sched_time ){
		  return schDAO.select("  WHERE play_id ="+play_id+" and studio_id="+studio_id+" and sched_time ='"+sched_time+"' ");
	  }
	  public int addscl(Schedule scl){
			TicketSrv ticket = new TicketSrv();
			StudioSrv stus = new StudioSrv();
			SeatSrv seat = new SeatSrv();
			add(scl);
			Play play = new Play();
			play=PlaySrv.Fetch_id(scl.getPlay_id()).get(0);
			play.setPlay_status(1);
			PlaySrv.modify(play);
			
			//返回演出计划ID
			int id =ScheduleSrv.Fetch_id(scl.getPlay_id(), scl.getStudio_id(), scl.getSched_time()).get(0).getSched_id();
			
			System.out.println(id);
			//座位数
			
			List<Seat> list = SeatSrv.Fetch(scl.getStudio_id());

			System.out.println("sdfsadf");
			for(int i=0;i<list.size();i++){
				Ticket tic =new Ticket();
				Seat seat1 = list.get(i);
				tic.setSched_id(id);
				tic.setTicket_locked_time("");
				tic.setTicket_price(scl.getSched_ticket_price());
				tic.setTicket_status(0);
				tic.setSeat_id(seat1.getSeat_id());
				ticket.add(tic);
			}
			return 1;
		}
	  
	  public int delete_scl(int id){
		  	List<Schedule> list1 =ScheduleSrv.Fetch_name(id);
		  	Schedule scl = new Schedule();
		  	scl =list1.get(0);
		  	delete(scl.getSched_id());
		  	List<Ticket> list = TicketSrv.Fetch_studio(scl.getStudio_id());
		  	for(int i=0;i<list.size();i++){
		  		Ticket seat = new Ticket();
		  		seat=list.get(i);
		  		TicketSrv.modify(seat);
		  	}
		  	return 1;
	  }
	  
}
