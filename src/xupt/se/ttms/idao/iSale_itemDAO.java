package xupt.se.ttms.idao;

import java.util.List;

import xupt.se.ttms.model.Sale_item;

public interface iSale_itemDAO {
	public int insert(Sale_item sale_i);
	public int update(Sale_item sale_i);
	public int delete(int id);
	public List<Sale_item> select(String condt);
}
