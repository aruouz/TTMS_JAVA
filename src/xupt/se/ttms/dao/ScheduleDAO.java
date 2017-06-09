package xupt.se.ttms.dao;
import java.util.List;
import java.sql.ResultSet;
import java.util.LinkedList;
import xupt.se.ttms.idao.iScheduleDAO;
import xupt.se.ttms.model.Schedule;
import xupt.se.util.DBUtil;


public class ScheduleDAO implements iScheduleDAO{
	@Override
	public int insert(Schedule scl) {
		try {
			String sql = "INSERT INTO schedule(studio_id,play_id,sched_time,sched_ticket_price) VALUES("+scl.getStudio_id()
			+","+scl.getPlay_id()+",'"+scl.getSched_time()+"',"
			+scl.getSched_ticket_price()+")";
			DBUtil db =new DBUtil();
			db.openConnection();
			ResultSet rst =db.getInsertObjectIDs(sql);
			if(rst!=null&&rst.first()){
				scl.setSched_id(rst.getInt(1));
			}
			db.close(rst);
			db.close();
			return 1;
		} catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}

	public int update(Schedule scl) {
		int rtn = 0;
		try{
			String sql ="UPDATE schedule set "+"studio_id = "
					+ scl.getStudio_id() +","+"play_id = " 
					+ scl.getPlay_id() +","+"sched_time ='"
					+ scl.getSched_time() +"',"+"sched_ticket_price="
					+ scl.getSched_ticket_price();
			
			sql += "WHERE sched_id = "+ scl.getSched_id();
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn = db.execCommand(sql);
			db.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return rtn;
	}

	public int delete(int ID) {
		int rtn=0;
		
		try{
			String sql ="DELETE FROM schedule ";
			sql += "WHERE sched_id = "+ ID;
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
	public List<Schedule> select(String condt) {
		List<Schedule> sclList = null;
		sclList = new LinkedList<Schedule>();
		try{
			String sql ="SELECT sched_id, studio_id, play_id, sched_time,sched_ticket_price FROM schedule ";
			condt.trim();
			if(!condt.isEmpty())
				sql += condt+" ORDER BY sched_id";
			DBUtil db = new DBUtil();
			if(!db.openConnection()){
				System.out.print("Fail to connect database");
				return null;
			}
			ResultSet rst = db.execQuery(sql);
			if (rst!=null) {
				while(rst.next()){
				Schedule sch = new Schedule();
				sch.setSched_id(rst.getInt("sched_id"));
				sch.setStudio_id(rst.getInt("studio_id"));
				sch.setPlay_id(rst.getInt("play_id"));
				sch.setSched_time(rst.getString("sched_time"));
				sch.setSched_ticket_price(rst.getDouble("sched_ticket_price"));
				sclList.add(sch);
			}
		}
		db.close(rst);
		db.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			
		}
		return sclList;
	}
	
	  
}