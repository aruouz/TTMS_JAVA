package xupt.se.ttms.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iSaleDAO;
import xupt.se.ttms.model.Sale;
import xupt.se.util.DBUtil;

public class SaleDAO implements iSaleDAO{

	@Override
	public int insert(Sale sale) {
		try{
			String sql ="INSERT INTO sale VALUES("+sale.getEmp_id()
						+",'"+sale.getSale_time()+"',"+sale.getSale_payment()
						+","+sale.getSale_change()+","+sale.getSale_type()
						+","+sale.getSale_status()+")";
			DBUtil db =new DBUtil();
			db.openConnection();
			ResultSet rst = db.getInsertObjectIDs(sql);
			if(rst!=null && rst.first()){
				sale.setSale_ID(rst.getInt(1));
			}
			db.close(rst);
			db.close();
			return 1;
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(Sale sale) {
		int rtn = 0;
		try{
			String sql = "UPDATE sale SET emp_id="+sale.getEmp_id()
						+",sale_time ='"+sale.getSale_time()
						+"',sale_payment="+sale.getSale_payment()
						+",sale_change="+sale.getSale_change()
						+",sale_type="+sale.getSale_type()
						+",sale_status="+sale.getSale_status()+"WHERE sale_id = "+sale.getSale_ID();
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn = db.execCommand(sql);
			db.close();
			}
		catch (Exception e){
			e.printStackTrace();
		}
		return rtn;
	}

	@Override
	public int delete(int id) {
		int rtn = 0;
		
		try{
			String sql ="DELETE FROM sale where sale_id ="+id+";";
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn = db.execCommand(sql);
			db.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		return rtn;
	}

	@Override
	public List<Sale> select(String condt) {
		List<Sale> saleList =null;
		saleList = new LinkedList<Sale>();
		try{
			String sql ="SELECT sale_ID,emp_id,sale_time,sale_payment,sale_change,sale_type,sale_status From sale";
			condt.trim();
			if(!condt.isEmpty())
				sql=sql+" WHERE "+condt+" order by sale_ID";
			DBUtil db =new DBUtil();
			if(!db.openConnection()){
				System.out.println("fail to connect databse aaaaaaa");
				return null;
			}
			ResultSet rst = db.execQuery(sql);
			if(rst!=null){
				while(rst.next()){
					Sale sale =new Sale();
					sale.setSale_ID(rst.getInt("sale_ID"));
					sale.setEmp_id(rst.getInt("emp_id"));
					sale.setSale_time(rst.getString("sale_time"));
					sale.setSale_payment(rst.getDouble("sale_payment"));
					sale.setSale_change(rst.getDouble("sale_change"));
					sale.setSale_type(rst.getInt("sale_type"));
					sale.setSale_status(rst.getInt("sale_status"));
					saleList.add(sale);
				}
			}
			db.close(rst);
			db.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			
		}
		
		return saleList;
	}

}
