package xupt.se.ttms.idao;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.List;

import xupt.se.ttms.model.Play;
import xupt.se.util.DBUtil;

public interface iPlayDAO {
	public int insert(Play ply);
	public int update(Play ply);
	public int delete(int play_id);
	public List<Play> select(String condt);
	public String read(Play ply);
}
