package xupt.se.ttms.idao;

import java.util.List;

import xupt.se.ttms.model.Seat;

public interface iSeatDAO {
	public int insert(Seat seat);
	public int delete(int ID);
	public int update(Seat seat);
	public List<Seat> select(String condt);
}
