package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.dao.SaleDAO;
import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iSaleDAO;
import xupt.se.ttms.model.Sale;

public class SaleSrv {
	private static iSaleDAO daosale = DAOFactory.createSaleDAO();
	
	public static int add(Sale sale){
		sale.setSale_type(1);
		sale.setSale_status(1);
		return daosale.insert(sale);
	}
	
	public int modify(Sale sale){
		return daosale.update(sale);
	}
	
	public int delete(int id){
		return daosale.delete(id);
	}
	
	public List<Sale> FetchAll(){
		return daosale.select("");
	}
	
	public static List<Sale> Fetch_id(int id,String time){
		return daosale.select(" emp_id="+id+"and sale_time= '"+time+"'");
	}
	
	public List<Sale> Fetch_sale_id(int id){
		return daosale.select(" sale_id = "+id);
	}
	
	public List<Sale> Fetch_emp_id(int id){
		return daosale.select("  emp_id = "+id);
	}
	
	public static double change(double t_price,double g_price){
		return (g_price-t_price);
	}

	
}
