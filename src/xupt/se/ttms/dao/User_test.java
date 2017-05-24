package xupt.se.ttms.dao;

import xupt.se.ttms.model.User;
import xupt.se.ttms.service.UserSrv;

public class User_test {
	public static void main(String [] args){
	User user1 = new User();
	UserSrv test = new UserSrv();
	
	
	test.delete(10);
	}
}
