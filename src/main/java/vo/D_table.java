package vo;

public class D_table {
	private String d_id;
	private String d_name;
	private String d_tel;
	private String d_psw;
	private String d_introduction;
	private String d_surgery;
	private String state;
	
	//构造函数
	public D_table() {
		this.d_id="";
		this.d_name="";
		this.d_tel="";
		this.d_psw="";
		this.d_introduction="";
		this.d_surgery="";
		this.state="";
	}
	
	//getters setters
	public String getD_id() {
		return d_id;
	}

	public String getD_name() {
		return d_name;
	}

	public String getD_tel() {
		return d_tel;
	}

	public String getD_psw() {
		return d_psw;
	}

	public String getD_introduction() {
		return d_introduction;
	}

	public String getD_surgery() {
		return d_surgery;
	}

	public String getState() {
		return state;
	}

	public void setD_id(String d_id) {
		this.d_id = d_id;
	}

	public void setD_name(String d_name) {
		this.d_name = d_name;
	}

	public void setD_tel(String d_tel) {
		this.d_tel = d_tel;
	}

	public void setD_psw(String d_psw) {
		this.d_psw = d_psw;
	}

	public void setD_introduction(String d_introduction) {
		this.d_introduction = d_introduction;
	}

	public void setD_surgery(String d_surgery) {
		this.d_surgery = d_surgery;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
	//end

}
