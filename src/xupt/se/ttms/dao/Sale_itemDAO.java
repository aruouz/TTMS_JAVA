package xupt.se.ttms.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iSale_itemDAO;
import xupt.se.ttms.model.Sale;
import xupt.se.ttms.model.Sale_item;
import xupt.se.util.DBUtil;

public class Sale_itemDAO implements iSale_itemDAO{

	@Override
	public int insert(Sale_item sale_i) {
		try{
			String sql ="INSERT INTO sale_item(ticket_id,sale_ID,sale_item_price) VALUES("+sale_i.getTicket_id()
						+","+sale_i.getSale_id()+","+sale_i.getSale_item_price()
						+")";
			DBUtil db =new DBUtil();
			db.openConnection();
			ResultSet rst = db.getInsertObjectIDs(sql);
			if(rst!=null && rst.first()){
				sale_i.setSale_item_id(rst.getInt(1));
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
	public int update(Sale_item sale_i) {
		int rtn = 0;
		try{
			String sql = "UPDATE sale SET ticket_id="+sale_i.getTicket_id()
						+",sale_ID ='"+sale_i.getSale_id()
						+"',sale_item_price="+sale_i.getSale_item_price()
						+"WHERE sale_item_id = "+sale_i.getSale_item_id();
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
			String sql ="DELETE FROM sale_item where sale_item_id ="+id+";";
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
	public List<Sale_item> select(String condt) {
		List<Sale_item> saleList =null;
		saleList = new LinkedList<Sale_item>();
		try{
			String sql ="SELECT sale_item_id,ticket_id,sale_ID,sale_item_price From sale";
			condt.trim();
			if(!condt.isEmpty())
				sql=sql+condt+"WHERE order by sale_item_id";
			DBUtil db =new DBUtil();
			if(!db.openConnection()){
				System.out.println("fail to connect databse aaaaaaa");
				return null;
			}
			ResultSet rst = db.execQuery(sql);
			if(rst!=null){
				while(rst.next()){
					Sale_item sale =new Sale_item();
					sale.setSale_item_id(rst.getInt("sale_item_id"));
					sale.setTicket_id(rst.getInt("ticket_id"));
					sale.setSale_id(rst.getInt("sale_ID"));
					sale.setSale_item_price(rst.getInt("sale_item_price"));;
		
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
