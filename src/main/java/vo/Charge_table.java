package vo;

public class Charge_table {
	private String c_id;
	private String c_name;
	private String c_tel;
	private String c_psw;
	private String state;
	
	//构造函数
	public Charge_table() {
		this.c_id="";
		this.c_name="";
		this.c_tel="";
		this.c_psw="";
		this.state="";
	}
	
	//getters setters
	public String getC_id() {
		return c_id;
	}

	public String getC_name() {
		return c_name;
	}

	public String getC_tel() {
		return c_tel;
	}

	public String getC_psw() {
		return c_psw;
	}

	public String getState() {
		return state;
	}

	public void setC_id(String c_id) {
		this.c_id = c_id;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public void setC_tel(String c_tel) {
		this.c_tel = c_tel;
	}

	public void setC_psw(String c_psw) {
		this.c_psw = c_psw;
	}

	public void setState(String state) {
		this.state = state;
	}

	//end
}
