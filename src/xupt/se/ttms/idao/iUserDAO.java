package xupt.se.ttms.idao;

import java.util.List;

import xupt.se.ttms.model.User;

public interface iUserDAO {
	public int insert(User user);
	public int update(User user);
	public int delete(int ID);
	public List<User> select(String condt);
	public User return_user(User user,String condt);
}
