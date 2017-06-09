package xupt.se.ttms.idao;
import xupt.se.ttms.dao.*;
import xupt.se.ttms.model.Employee;
import xupt.se.ttms.model.Sale_item;

public class DAOFactory {
	public static iStudioDAO creatStudioDAO(){
		return new StudioDAO();
	}
	
	public static iPlayDAO creatPlayDAO(){
		return new PlayDAO();
	}
	
	public static iUserDAO creatUserDAO(){
		return new UserDAO();
	}
	
	public static iScheduleDAO createScheduleDAO(){
		return new ScheduleDAO();
	}
	
	public static iSeatDAO createSeatDAO(){
		return new SeatDAO();
	}
	
	public static iTicketDAO createTicketDAO(){
		return new TicketDAO();
	}
	
	public static iDictDAO createDictDAO(){
		return  new DictDAO();
	}
	
	public static iEmployeeDAO createEmployee(){
		return new EmployeeDAO();
	}
	
	public static iSaleDAO createSaleDAO(){
		return new SaleDAO();
	}
	public static iSale_itemDAO creatSale_item(){
		return new Sale_itemDAO();
	}
}
