package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iDictDAO;
import xupt.se.ttms.model.Dict;

public class DictSrv {
	private static iDictDAO dicDAO = DAOFactory.createDictDAO();
	
	public int add(Dict dic){
		return dicDAO.insert(dic);
	}
	
	public int modify(Dict dic){
		return dicDAO.update(dic);
	}
	
	public int delete(int id ){
		return dicDAO.delete(id);
	}
	
	public List<Dict> FetchAll(){
		return dicDAO.select("");
	}
	
	public static List<Dict> Fetch_Value(int dict_id){
		return dicDAO.select(" WHERE dict_id = "+dict_id);
	}
	
	public static List<Dict> Fetch_Type_id(int dict_id){
		return dicDAO.select(" WHERE dict_parent_id = "+dict_id);
	}
	
	public static List<Dict> Fetch_value_id(String condt){
		return dicDAO.select(" WHERE dict_value = '"+condt+"'");
	}
	
	

}
