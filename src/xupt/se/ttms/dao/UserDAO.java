package xupt.se.ttms.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iUserDAO;
import xupt.se.ttms.model.User;
import xupt.se.util.DBUtil;

public class UserDAO implements iUserDAO{
	@Override
	public int insert(User user){
		try{
			String sql = "INSERT INTO UserM "
					+" VALUES('"+user.getName()+"', '"
					+user.getPassword()+"', '"
					+user.getEmail()+"', '"
					+user.getPhoneNumber()+"' ,"
					+user.getAge()+",'"
					+user.getSex()+"')";
			DBUtil db = new DBUtil();
			db.openConnection();
			ResultSet rst = db.getInsertObjectIDs(sql);
			if(rst!=null &&rst.first()){
				user.setUserID(rst.getInt(1));
			}
			db.close(rst);
			db.close();
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int update(User user){
		int rtn=0;
		try{
			String sql = "UPDATE UserM SET"+" userName='"+user.getName()
						+"',userPassword= '"+user.getPassword()
						+"',userEmail='"+user.getEmail()
						+"',userPhoneNumber='"+user.getPhoneNumber()
						+"',userAge="+user.getAge()
						+",userSex='"+user.getSex()+"'";
			sql += "WHERE userID ="+user.getUserID();
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn = db.execCommand(sql);
			db.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}
	
	public int delete(int ID){
		int  rtn=0;
		
		try{
			String sql="DELETE FROM UserM WHERE userID="+ID;
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn=db.execCommand(sql);
			db.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;	
	}
	
	public List<User> select(String condt){
		List<User> userList =null;
		userList=new LinkedList<User>();
		try{
			String sql ="select userID,userName,userPassword,userEmail,userPhoneNumber,userAge,userSex from UserM";
			condt.trim();
			if(!condt.isEmpty())
				sql=sql+" WHERE "+ condt;
			DBUtil db = new DBUtil();
			if(!db.openConnection()){
				System.out.print("fail to connect database");
				return null;
			}
			ResultSet rst = db.execQuery(sql);
			if(rst!=null){
				while(rst.next()){
					User user = new User();
					user.setUserID(rst.getInt("userID"));
					user.setName(rst.getString("userName"));
					user.setPassword(rst.getString("userPassword"));
					user.setEmail(rst.getString("userEmail"));
					user.setPhoneNumber(rst.getString("userPhoneNumber"));
					user.setAge(rst.getInt("userAge"));
					user.setSex(rst.getString("userSex"));
					userList.add(user);
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			
		}
		
		return userList;
	}

	public User return_user(User user,String condt){
	try{
		String sql ="select userID,userName,userPassword,userEmail,userPhoneNumber,userAge,userSex from UserM";
		condt.trim();
		if(!condt.isEmpty())
		sql=sql+" WHERE "+ condt;
		DBUtil db = new DBUtil();
		if(!db.openConnection()){
		System.out.print("fail to connect database");
		return null;
	}
	ResultSet rst = db.execQuery(sql);
	if(rst!=null){
		while(rst.next()){
			user.setUserID(rst.getInt("userID"));
			user.setName(rst.getString("userName"));
			user.setPassword(rst.getString("userPassword"));
			user.setEmail(rst.getString("userEmail"));
			user.setPhoneNumber(rst.getString("userPhoneNumber"));
			user.setAge(rst.getInt("userAge"));
			user.setSex(rst.getString("userSex"));
		}
	}
	db.close(rst);
	db.close();
	} catch (Exception e) {
	e.printStackTrace();
	}
	finally{
	
	}

	return user;
}
	

}

