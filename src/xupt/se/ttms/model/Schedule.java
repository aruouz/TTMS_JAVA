package xupt.se.ttms.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.mysql.fabric.xmlrpc.base.Data;

import net.sourceforge.jtds.jdbc.DateTime;

public class Schedule {
	//create table schedule
	//(
	//		   sched_id             int not null auto_increment,
	//		   studio_id            int,
	//		   play_id              int,
	//		   sched_time           datetime not null,
	//		   sched_ticket_price   numeric(10,2),
	//		   primary key (sched_id)
	//		);
	
	private int sched_id;
	private int studio_id;
	private int play_id;
	private String sched_time;
	private double sched_ticket_price;
	
	public int getSched_id(){
		return sched_id;
	}
	
	public void setSched_id(int sched_id){
		this.sched_id = sched_id;
	}
	
	public int getStudio_id(){
		return studio_id;
	}
	
	public void setStudio_id(int studio_id){
		this.studio_id = studio_id;
	}
	
	public int getPlay_id(){
		return play_id;
	}
	
	public void setPlay_id(int play_id){
		this.play_id = play_id;
	}
	
	public String getSched_time(){
		return sched_time;
	}
	
	public void setSched_time(String sched_time){
		this.sched_time = sched_time;
	}
	
	public double getSched_ticket_price(){
		return sched_ticket_price;
	}
	
	public void setSched_ticket_price(double sched_ticket_price){
		this.sched_ticket_price = sched_ticket_price;
	}
}
