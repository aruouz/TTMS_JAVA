package xupt.se.ttms.dao;


import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iEmployeeDAO;
import xupt.se.ttms.model.Employee;
import xupt.se.util.DBUtil;

public class EmployeeDAO implements iEmployeeDAO{

	@Override
	public int insert(Employee emp) {
		try{
			String sql = "INSERT INTO employee "
					+" VALUES('"+emp.getcName()+"', '"
					+emp.getName()+"', '"
					+emp.getTel()+"', '"
					+emp.getClass()+"' ,'"
					+emp.getEmail()+"','"
					+emp.getPassword()+"',"
					+emp.getAccess()+")";
			DBUtil db = new DBUtil();
			db.openConnection();
			ResultSet rst = db.getInsertObjectIDs(sql);
			if(rst!=null &&rst.first()){
				emp.setId(rst.getInt(1));
			}
			db.close(rst);
			db.close();
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(Employee emp) {
		int rtn=0;
		try{
			String sql = "UPDATE UserM SET"+" emp_no='"+emp.getcName()
						+"',emp_name='"+emp.getName()
						+"',emp_tel_num='"+emp.getTel()
						+"',emp_addr='"+emp.getClass()
						+"',emp_email='"+emp.getEmail()
						+"',emp_password='"+emp.getPassword()+"',emp_access="+emp.getAccess();
			sql += "  WHERE userID ="+emp.getId();
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn = db.execCommand(sql);
			db.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}

	@Override
	public int delete(int ID) {
int  rtn=0;
		
		try{
			String sql="DELETE FROM employee WHERE emp_id="+ID;
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn=db.execCommand(sql);
			db.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;	
	}

	@Override
	public List<Employee> select(String condt) {
		List<Employee> empList =null;
		empList=new LinkedList<Employee>();
		try{
			String sql ="SELECT  emp_id,emp_no,emp_name,emp_tel_num,emp_addr,emp_email,emp_password,emp_access From employee";
			condt.trim();
			if(!condt.isEmpty())
				sql= sql+ condt +" order by emp_id";
			DBUtil db = new DBUtil();
			if(!db.openConnection()){
				System.out.print("fail to connect database");
				return null;
			}
			ResultSet rst = db.execQuery(sql);
			if(rst!=null){
				while(rst.next()){
					Employee emp =new Employee();
					emp.setId(rst.getInt("emp_ID"));
					emp.setcName(rst.getString("emp_no"));
					emp.setName(rst.getString("emp_name"));
					emp.setTel(rst.getString("emp_tel_num"));
					emp.setEmail(rst.getString("emp_email"));
					emp.setPassword(rst.getString("emp_password"));
					emp.setAccess(rst.getInt("emp_access"));
					empList.add(emp);
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			
		}
		
		return empList;
	}

}
