package xupt.se.ttms.model;

public class Seat {
	//seat_id              int not null auto_increment,
	//studio_id            int,
	//seat_row             int,
	//seat_column          int,
	//primary key (seat_id)
	
	private int seat_id;
	private int studio_id;
	private int seat_row;
	private int seat_column;
	private int seat_status;

	public int getSeat_id(){
		return seat_id;
	}
	
	public void setSeat_id(int seat_id){
		this.seat_id = seat_id;
	}
	
	public int getStudio_id(){
		return studio_id;
	}
	
	public void setStudio_id(int studio_id){
		this.studio_id = studio_id;
	}
	
	public int getSeat_row(){
		return seat_row;
	}
	
	public void setSeat_row(int seat_row){
		this.seat_row = seat_row;
	}
	
	public int getSeat_column(){
		return seat_column;
	}
	
	public void setSeat_column(int seat_column){
		this.seat_column = seat_column;
	}
	
	public int getSeat_status(){
		return seat_status;
	}
	
	public void setSeat_status(int seat_status){
		this.seat_status = seat_status;
	}
}
