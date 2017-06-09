package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iSale_itemDAO;
import xupt.se.ttms.model.Sale;
import xupt.se.ttms.model.Sale_item;

public class Sale_itemSrv {
	private static iSale_itemDAO daosale = DAOFactory.creatSale_item();
	
	public static int add(Sale_item sale){
		
		return daosale.insert(sale);
	}

	public int modify(Sale_item sale){
		return daosale.update(sale);
	}
	
	public int delete(int id){
		return daosale.delete(id);
	}
	
	public List<Sale_item> FetchAll(){
		return daosale.select("");
	}
	
	public List<Sale_item> Fetch_sale_item_id(int id){
		return daosale.select(" sale_item_id= "+id);
	}
}
