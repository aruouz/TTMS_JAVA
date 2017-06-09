package xupt.se.ttms.idao;

import java.util.List;

import xupt.se.ttms.model.Ticket;

public interface iTicketDAO {
	public int insert(Ticket tik);
	public int update(Ticket tik);
	public List<Ticket> select(String condt);
	public int lockTicket(int ID, String time);
	public int unlockTicket(int ID);
	public int delete(int ticket_id);
}
