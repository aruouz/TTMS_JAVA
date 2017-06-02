package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iStudioDAO;
import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Studio;

public class StudioSrv {
	private static iStudioDAO stuDAO=DAOFactory.creatStudioDAO();
	
	public int add(Studio stu){
		return stuDAO.insert(stu); 		
	}
	
	public int modify(Studio stu){
		return stuDAO.update(stu); 		
	}
	
	public int delete(int ID){
		return stuDAO.delete(ID); 		
	}
	
	public List<Studio> Fetch_name(String condt){
		return stuDAO.select(" WHERE studio_name = "+"'"+condt+"'");		
	}
	
	public static List<Studio> Fetch_id(int ID){
		return stuDAO.select(" WHERE studio_id = "+ID);		
	}
	
	public List<Studio> FetchAll(){
		return stuDAO.select("");		
	}
	

	public void addstu(Studio stu){
		SeatSrv seat1 = new SeatSrv();
		stu.setStudio_flag(0);
		add(stu);

		List list =Fetch_name(stu.getName());
		for (int i = 0; i < list.size(); i++) {
			 stu = (Studio) list.get(i);
		}
		
		if(stu.getStudio_flag()==0){
			stu.setStudio_flag(1);
			seat1.seatadd(stu);
		}
		else{
			System.out.println("该演出厅已用，无法添加座位");
		}
	}
	
	public String status(Studio stu){
		String rtn = "";
		if(stu.getStudio_flag()==1){
			rtn ="正常使用演出厅";
		}
		else if(stu.getStudio_flag()==-1){
			rtn = "无法使用演出厅";
		}
		return rtn;
	}
	
	public int r_status(String str){
		if(str=="正常使用演出厅"){
			return 1;
		}
		else{
			return -1;
		}
	}
	
	public String Fetch_all_name(){
		String rtn = "";
		List<Studio> list = FetchAll();
		for(int i =0; i<= list.size();i++){
			Studio stu =new Studio();
			return stu.getName();
		}
		return rtn ;
	}
	

}
