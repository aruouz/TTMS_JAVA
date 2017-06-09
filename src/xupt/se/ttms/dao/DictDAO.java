package xupt.se.ttms.dao;

import java.util.LinkedList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import xupt.se.ttms.idao.iDictDAO;
import xupt.se.ttms.idao.iStudioDAO;
import xupt.se.ttms.model.Dict;
import xupt.se.ttms.model.Studio;
import xupt.se.util.DBUtil;



public class DictDAO implements iDictDAO {
	public int insert(Dict dic) {
		try {
			String sql = "insert into dict(dict_parent_id,dict_index,dict_name, dict_value)"
					+ " values("
					+dic.getDict_parent()
					+ ", "
					+dic.getDict_index()
					+ ", '" + dic.getDict_name()
					+ "', '" + dic.getDict_value()
					+"')";
			DBUtil db = new DBUtil();
			db.openConnection();
			ResultSet rst = db.getInsertObjectIDs(sql);
			if (rst!=null && rst.first()) {
				dic.setDict_id(rst.getInt(1));
			}
			db.close(rst);
			db.close();
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int update(Dict dic) {
		int rtn=0;
		try {
			String sql = "update studio set " + " dict_parent ="
					+ dic.getDict_parent() +", " + "dict_index = "
					+ dic.getDict_index() + ", " + " dict_name = ' "
					+ dic.getDict_name()+ "', " + " dict_value = '"
					+ dic.getDict_value() + "' ";

			sql += " where studio_id = " + dic.getDict_id()+" ORDER BY studio_id";
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn =db.execCommand(sql);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}

	@Override
	public int delete(int ID) {
		int rtn=0;		
		
		try{
			String sql = "delete from  dict ";
			sql += " where studio_id = " + ID;
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn=db.execCommand(sql);
			db.close();
		} 	catch (Exception e){
			e.printStackTrace();
		}
		return rtn;
	}

	@Override
	public List<Dict> select(String condt) {
		List<Dict> diclist = null;
		diclist=new LinkedList<Dict>();
		try {
			String sql = "select  dict_id,dict_parent_id,dict_index,dict_name, dict_value from data_dict ";
			
			condt.trim();
			
			if(!condt.isEmpty())
				sql=sql+ condt +" order by dict_id"; 
			DBUtil db =new DBUtil();
			if(!db.openConnection()){
				System.out.print("fail to connect database");
				return null;
			}
			ResultSet rst = db.execQuery(sql);
			if (rst!=null) {
				while(rst.next()){
					Dict dic = new Dict();
					dic.setDict_id(rst.getInt("dict_id"));;
					dic.setDict_index(rst.getInt("dict_index"));
					dic.setDict_parent(rst.getInt("dict_parent_id"));
					dic.setDict_name(rst.getString("dict_name"));
					dic.setDcit_value(rst.getString("dict_value"));
					
					diclist.add(dic);
					
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			
		}
		
		return diclist;
	}
	
	
}

