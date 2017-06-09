package xupt.se.ttms.dao;

import java.util.List;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.LinkedList;

import xupt.se.ttms.idao.iPlayDAO;
import xupt.se.ttms.model.Play;
import xupt.se.util.DBUtil;
import xupt.se.util.SaveIMage;


public class PlayDAO implements iPlayDAO{
	@Override
	public int insert(Play ply){
		try{
			String  sql ="INSERT INTO play(play_type_id,play_lang_id,play_name,play_introduction,play_length,play_ticket_price,play_status) "
					+" VALUES("+ply.getPlay_type_id()+","
					+ply.getPlay_lang_id()+","+" '"
					+ply.getPlay_name()+"' , '"
					+ply.getPlay_introduction()+"', "
					+ply.getPlay_length()+","
					+ply.getPlay_ticket_price()+","
					+ply.getPlay_status()
					+")";
			DBUtil db = new DBUtil();
			db.openConnection();
			ResultSet rst = db.getInsertObjectIDs(sql);
			if(rst!=null && rst.first()){
				ply.setPlay_id(rst.getInt(1));
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
	public int update(Play ply){
		int rtn = 0;
		try{
			String sql = "UPDATE play SET" 
						+" play_type_id="+ply.getPlay_type_id()
						+",play_lang_id="+ply.getPlay_lang_id()
						+",play_name='"+ply.getPlay_name()
						+"',play_introduction='"+ply.getPlay_introduction()
						+"',play_length="+ply.getPlay_length()
						+",play_ticket_Price="+ply.getPlay_ticket_price()
						+",play_status="+ply.getPlay_status()+ "  where play_id ="+ply.getPlay_id();
			
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
	
	public int delete(int play_id){
		int rtn = 0;
		
		try{
			String sql ="DELETE FROM play where play_id ="+play_id+";";
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
	public List<Play> select(String condt){
		List<Play> plyList =null;
		plyList =new LinkedList<Play>();
		try{
			String sql = "select play_id,play_type_id,play_lang_id,play_name,"
					+ "play_introduction,play_length,"
					+ "play_ticket_price,play_status from play";
			condt.trim();
			if(!condt.isEmpty())
					sql=sql+ condt+" ORDER BY play_id";
			DBUtil db = new  DBUtil();
			if(!db.openConnection()){
				System.out.print("fail to connect database");
				return null;
			}
			ResultSet rst = db.execQuery(sql);
			if(rst != null){
				while(rst.next()){
					Play ply = new Play();
					ply.setPlay_id(rst.getInt("play_id"));
					ply.setPlay_type_id(rst.getInt("play_type_id"));
					ply.setPlay_lang_id(rst.getInt("play_lang_id"));
					ply.setPlay_name(rst.getString("play_name"));
					ply.setPlay_introduction(rst.getString("play_introduction"));
					ply.setPlay_length(rst.getInt("play_length"));
					ply.setPlay_ticket_price(rst.getFloat("play_ticket_price"));
					ply.setPlay_status(rst.getInt("play_status"));
					plyList.add(ply);
				}
			}
			db.close(rst);
			db.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			
		}
		
		return plyList;
	}
	
	public String read(Play ply){
    	String targetPath ="D:/image/1.png";
    	try{
			String sql ="SELECT play_image FROM play WHERE play_id="+ply.getPlay_id();
			DBUtil db = new  DBUtil();
			ResultSet rst = db.execQuery(sql);
			while (rst.next()) {
				              InputStream in = rst.getBinaryStream("play_image");
				              SaveIMage.readBin2Image(in, targetPath);
				           }    
			db.close(rst);
			 db.close();
    	}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			
		}
    	return targetPath;
    }

}