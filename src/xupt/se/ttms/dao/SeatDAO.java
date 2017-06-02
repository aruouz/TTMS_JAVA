package xupt.se.ttms.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import com.mysql.jdbc.UpdatableResultSet;

import xupt.se.ttms.idao.iSeatDAO;
import xupt.se.ttms.model.Seat;
import xupt.se.util.DBUtil;

public class SeatDAO implements iSeatDAO{

	@Override
	public int insert(Seat seat) {
		try{
			String sql = "INSERT INTO seat VALUES("
					+seat.getStudio_id()+","+seat.getSeat_row()+","
					+seat.getSeat_column()+","+seat.getSeat_status()+")";
			DBUtil db = new DBUtil();
			db.openConnection();
			ResultSet rst = db.getInsertObjectIDs(sql);
			if(rst!=null &&rst.first())
				seat.setSeat_id(rst.getInt(1));
			db.close(rst);
			db.close();
			return 1;
		} catch (Exception e){
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(int ID) {
		int rtn = 0;
		try{
			String sql = "DELETE FROM seat";
			sql += "WHERE seat_id ="+ ID;
			DBUtil db = new DBUtil();
			rtn = db.execCommand(sql);
			db.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return rtn;
	}
	
	public int update(Seat seat){
		int rtn = 0;
		try{
			String sql = "UPDATE seat_status = "+ seat.getSeat_status()+ " FROM seat";
			sql+= "WHERE seat_id = "+seat.getSeat_id();
			DBUtil db=new DBUtil();
			rtn = db.execCommand(sql);
			db.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}

	@Override
	public List<Seat> select(String condt) {
		List<Seat> seatList = null;
		seatList = new LinkedList<Seat>();
		try{
			String sql ="SELECT seat_id, studio_id, seat_row,seat_column FROM seat";
			condt.trim();
			if(!condt.isEmpty())
				sql += " WHERE " +condt;
			DBUtil db = new DBUtil();
			if(!db.openConnection()){
				System.out.println("fail to connect database");
				return null;
			}
			ResultSet rst =db.execQuery(sql);
			if(rst!=null){
				while(rst.next()){
					Seat seat = new Seat();
					seat.setSeat_id(rst.getInt("seat_id"));
					seat.setStudio_id(rst.getInt("studio_id"));
					seat.setSeat_row(rst.getInt("seat_row"));
					seat.setSeat_column(rst.getInt("seat_column"));
					System.out.println(seat.getSeat_id());
					seatList.add(seat);
				}
			}
			db.close(rst);
			db.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			
		}
		return seatList;
	}
	
}
