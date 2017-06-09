package xupt.se.ttms.idao;

import java.util.List;

import xupt.se.ttms.model.Employee;

public interface iEmployeeDAO {
	public int insert(Employee emp);
	public int update(Employee emp);
	public int delete(int ID);
	public List<Employee> select(String condt);
}
