package xupt.se.ttms.idao;
import java.util.List;
import xupt.se.ttms.model.Schedule;

public interface iScheduleDAO {
	public int insert(Schedule scl);
	public int update(Schedule scl);
	public int delete(int ID);
	public List<Schedule> select(String condt);
}
