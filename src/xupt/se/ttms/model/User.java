package xupt.se.ttms.model;

public class User {
	protected int userID;
	//CREATE TABLE UserM(
	//		userID int primary key,
	//		userName varchar(20),
	//		userPassword varchar(20) NOT NULL,
	//		userEmail varchar(30) NOT NULL,
	//		userPhoneNumber varchar(20),
	//		userAge INT,
	//		userSex char(2));
	protected String name;
	protected String password;
	protected String email;
	protected String phoneNumber;
	protected int age;
	protected String sex;
	
	public int getUserID(){
		return userID;
	}
	
	public void setUserID(int userID){
		this.userID=userID;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name=name;
	}
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password=password;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email=email;
	}
	
	public String getPhoneNumber(){
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber=phoneNumber;
	}
	
	public int getAge(){
		return age;
	}
	
	public void setAge(int age){
		this.age=age;
	}

	public String getSex(){
		return sex;
	}
	
	public void setSex(String sex){
		this.sex=sex;
	}
}
