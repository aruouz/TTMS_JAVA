package xupt.se.ttms.service;


import java.util.List;

import xupt.se.ttms.dao.PlayDAO;
import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iPlayDAO;
import xupt.se.ttms.model.Play;

public class PlaySrv {
	private static iPlayDAO plyDAO = DAOFactory.creatPlayDAO();
	
	public static int add(Play ply){
		return plyDAO.insert(ply);
	}
	
	public static int modify(Play ply){
		return plyDAO.update(ply);
	}
	
	public int delete(int ID){
		return plyDAO.delete(ID);
	}
	
	public static List<Play> Fetch_name(String condt){
		return plyDAO.select(" WHERE play_name = "+"'"+condt+"'");
	}
	
	public List<Play> FetchALL(){
		return plyDAO.select("");
	}
	
	public static List<Play> Fetch_id(int id){
		return plyDAO.select( " WHERE play_id ="+id);
	}
	
	public static String status(int sta){
		String rtn = null;
		if(sta == 0){
			rtn = "未安排演出";
		}
		else if(sta == 1){
			rtn = "已安排演出";
		}
		else if(sta == -1){
			rtn = "下线";
		}
		
		return rtn;
	}
	
	public static int r_status(String sta){
		int rtn = 0;
		if(sta=="未安排演出"){
			rtn =0;
		}
		else if(sta == "已安排演出"){
			rtn = 1;
		}
		else if(sta == "下线" ){
			rtn = -1;
		}
		
		return rtn;
	}

}
