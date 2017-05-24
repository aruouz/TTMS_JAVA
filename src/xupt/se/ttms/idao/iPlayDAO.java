package xupt.se.ttms.idao;
import java.util.List;

import xupt.se.ttms.model.Play;

public interface iPlayDAO {
	public int insert(Play ply);
	public int update(Play ply);
	public int delete(int play_id);
	public List<Play> select(String condt);
}
