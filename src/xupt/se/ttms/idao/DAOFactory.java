package xupt.se.ttms.idao;
import xupt.se.ttms.dao.*;

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
}
