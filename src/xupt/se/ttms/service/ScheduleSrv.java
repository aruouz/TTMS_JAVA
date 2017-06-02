package xupt.se.ttms.service;

import java.util.List;

import org.omg.CORBA.PUBLIC_MEMBER;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iScheduleDAO;
import xupt.se.ttms.model.Play;
import xupt.se.ttms.model.Schedule;

public class ScheduleSrv {
	  private iScheduleDAO schDAO = DAOFactory.createScheduleDAO();
	  
	  public int add(Schedule scl){
		  return schDAO.insert(scl);
	  }
	  
	  public int modify(Schedule scl){
		  return schDAO.update(scl);
	  }
	  
	  public int delete(int ID){
		  return schDAO.delete(ID);
	  }
	  
	  public List<Schedule> fetchall(){
		  return schDAO.select("");
	  }
	  
	  public List<Schedule> fetch_name(int id){
		  return schDAO.select(" WHERE play_id= "+id);
	  }
	  
	  public int r_id(String condt){
		  List<Play> list =PlaySrv.Fetch(condt);
		  for(int i = 0;i<list.size();i++){
			  Play ply =new Play();
			  return ply.getPlay_id();
		  }
		  return 0;
	  }
}
