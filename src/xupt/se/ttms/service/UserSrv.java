package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iUserDAO;
import xupt.se.ttms.model.User;

public class UserSrv {
	private iUserDAO userDAO = DAOFactory.creatUserDAO();
	
	public int add(User user){
		return userDAO.insert(user);
	}
	
	public int modify(User user){
		return userDAO.update(user);
	}
	
	public int delete(int ID){
		return userDAO.delete(ID);
	}
	
	public List<User> Fetch(String condt){
		return userDAO.select(condt);
	}
	
	public List<User> FetchAll(){
		return userDAO.select("");
	}
	
	public User R_user(User user,String condt){
		return userDAO.return_user(user, condt);
	}

}
