package vo;

public class P_table {
	private String p_id;
	private String p_name;
	private String p_tel;
	private String p_psw;
	private String p_gender;
	private String p_age;
	private String p_nation;
	private String state;
	
	//构造函数
	public P_table() {
		this.p_id="";
		this.p_name="";
		this.p_tel="";
		this.p_psw="";
		this.p_gender="";
		this.p_age="";
		this.p_nation="";
		this.state="";
	}
	
	//getters setters
	public String getP_id() {
		return p_id;
	}
	public String getP_name() {
		return p_name;
	}
	public String getP_tel() {
		return p_tel;
	}
	public String getP_psw() {
		return p_psw;
	}
	public String getP_gender() {
		return p_gender;
	}
	public String getP_age() {
		return p_age;
	}
	public String getP_nation() {
		return p_nation;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public void setP_tel(String p_tel) {
		this.p_tel = p_tel;
	}
	public void setP_psw(String p_psw) {
		this.p_psw = p_psw;
	}
	public void setP_gender(String p_gender) {
		this.p_gender = p_gender;
	}
	public void setP_age(String p_age) {
		this.p_age = p_age;
	}
	public void setP_nation(String p_nation) {
		this.p_nation = p_nation;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	//end
}
