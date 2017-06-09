package xupt.se.ttms.model;



public class Employee  {
	private int access;
	private int id;
	private String name;
	private String password;
	private String cName;
	private String tel;
	private String email;
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Employee(){
		
	}

	public int getAccess() {
		return access;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public void setAccess(int access) {
		this.access = access;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}



}
