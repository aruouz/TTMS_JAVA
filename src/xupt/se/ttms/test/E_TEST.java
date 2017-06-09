package xupt.se.ttms.test;

import xupt.se.ttms.service.EmployeeSrv;

public class E_TEST {
	public static void main(String [] args){
		xupt.se.ttms.model.Employee emp = new xupt.se.ttms.model.Employee();
		emp.setName("王子怡");
		emp.setPassword("199684");
		emp.setAccess(2);
		emp.setcName("aruouz");
		emp.setEmail("wzy9681@126.com");
		emp.setTel("15891706563");
		
		EmployeeSrv.add(emp);
		
		
	}

}
