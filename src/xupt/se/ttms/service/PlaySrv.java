package xupt.se.ttms.service;


import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iPlayDAO;
import xupt.se.ttms.model.Play;

public class PlaySrv {
	private iPlayDAO plyDAO = DAOFactory.creatPlayDAO();
	
	public int add(Play ply){
		return plyDAO.insert(ply);
	}
	
	public int modify(Play ply){
		return plyDAO.update(ply);
	}
	
	public int delete(int ID){
		return plyDAO.delete(ID);
	}
	
	public List<Play> Fetch(String condt){
		return plyDAO.select(condt);
	}
	
	public List<Play> FetchALL(){
		return plyDAO.select("");
	}

}
