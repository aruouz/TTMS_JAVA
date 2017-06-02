package xupt.se.ttms.model;

public class Ticket {
	
	//ticket_id            bigint not null auto_increment,
	 // seat_id              int,
	   //sched_id             int,
	   //ticket_price         numeric(10,2),
	   //ticket_status        smallint comment '含义如下：
	     //       0：待销售
	       //     1：锁定
	         //   9：卖出',
	   //ticket_locked_time   datetime,
	   //primary key (ticket_id)
	
	private int ticket_id;
	private int seat_id;
	private int sched_id;
	private double ticket_price;
	private int ticket_status;
	private String ticket_locked_time;
	
	public int getTicket_id() {
		return ticket_id;
	}
	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
	}
	
	public int getSeat_id() {
		return seat_id;
	}
	public void setSeat_id(int seat_id) {
		this.seat_id = seat_id;
	}
	public int getSched_id() {
		return sched_id;
	}
	public void setSched_id(int sched_id) {
		this.sched_id = sched_id;
	}
	public double getTicket_price() {
		return ticket_price;
	}
	public void setTicket_price(double ticket_price) {
		this.ticket_price = ticket_price;
	}
	public int getTicket_status() {
		return ticket_status;
	}
	public void setTicket_status(int ticket_status) {
		this.ticket_status = ticket_status;
	}
	public String getTicket_locked_time(){
		return ticket_locked_time;
	}
	public void  setTicket_locked_time(String ticket_locked_time){
		this.ticket_locked_time = ticket_locked_time;
	}
}
