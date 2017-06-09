package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iEmployeeDAO;
import xupt.se.ttms.model.Employee;

public class EmployeeSrv {
	private static iEmployeeDAO empdao = DAOFactory.createEmployee();
	
	public static int add(Employee emp){
		return empdao.insert(emp);
	}
	
	public int modify(Employee emp){
		return empdao.update(emp);
	}
	
	public int delete(int ID){
		return empdao.delete(ID);
	}
	
	public static List<Employee> FetchAll(){
		return empdao.select("");
	}
	
	public static List<Employee> Fetch_id(int id){
		return empdao.select(" WHERE emp_id ="+id);
	}
	
}
