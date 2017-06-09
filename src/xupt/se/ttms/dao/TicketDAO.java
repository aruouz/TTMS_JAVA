package xupt.se.ttms.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iTicketDAO;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.Ticket;
import xupt.se.util.DBUtil;

public class TicketDAO  implements iTicketDAO{
	
	@Override
	public int insert(Ticket tik) {
		try{
			String sql ="INSERT INTO ticket VALUES("+tik.getSeat_id()
						+","+tik.getSched_id()+","+tik.getTicket_price()
						+","+tik.getTicket_status()+",'"+tik.getTicket_locked_time()+"')";
			DBUtil db =new DBUtil();
			db.openConnection();
			ResultSet rst = db.getInsertObjectIDs(sql);
			if(rst!=null && rst.first()){
				tik.setSched_id(rst.getInt(1));
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
	public int update(Ticket tik) {
		int rtn = 0;
		try{
			String sql = "UPDATE ticket set ticket_status="
					+tik.getTicket_status();
			sql+=" WHERE ticket_id = "+tik.getTicket_id();
			DBUtil db =new DBUtil();
			db.openConnection();
			rtn = db.execCommand(sql);
			db.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}


	public int delete(int ticket_id) {
		int  rtn=0;
		try{
		String sql="DELETE FROM ticket WHERE ticekt_id="+ticket_id;
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
	public List<Ticket> select(String condt) {
		List<Ticket> tikList = null;
		tikList=new LinkedList<Ticket>();
		try {
			String sql = "select ticket_id, seat_id, sched_id, ticket_price,ticket_status,ticket_locked_time from ticket ";
			
			condt.trim();
			
			if(!condt.isEmpty())
				sql=sql+ condt+" ORDER BY ticket_id"; 
			DBUtil db =new DBUtil();
			if(!db.openConnection()){
				System.out.print("fail to connect database");
				return null;
			}
			ResultSet rst = db.execQuery(sql);
			if (rst!=null) {
				while(rst.next()){
					Ticket tik = new Ticket();
					tik.setTicket_id(rst.getInt("ticket_id"));
					tik.setSeat_id(rst.getInt("seat_id"));
					tik.setSched_id(rst.getInt("sched_id"));
					tik.setTicket_price(rst.getDouble("ticket_price"));
					tik.setTicket_status(rst.getInt("ticket_status"));
					tik.setTicket_locked_time(rst.getString("ticket_locked_time"));
					tikList.add(tik);
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			
		}
		return tikList;
	}
	
	@Override
	public int lockTicket(int ID, String time) {
		int rtn=0;
		try {
			String sql = "update ticket set ticket_status=1, ticket_locked_time='" + time + "'";
			sql += " where ticket_id = " + ID;
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
	public int unlockTicket(int ID) {
		int rtn=0;
		try {
			String sql = "update ticket set ticket_status=0";
			sql += " where ticket_id = " + ID;
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn =db.execCommand(sql);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}



}