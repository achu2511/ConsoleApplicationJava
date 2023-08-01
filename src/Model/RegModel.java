package Model;


public class RegModel {
	
	private String name;
	private int mobile;
	private String password;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMobile() {
		return mobile;
	}
	public void setMobile(int mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public RegModel(String name, int mobile, String password) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.password = password;
	}
	public RegModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
