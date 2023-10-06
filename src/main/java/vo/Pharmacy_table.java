package vo;

public class Pharmacy_table {
	private String ph_id;
	private String ph_name;
	private String ph_tel;
	private String ph_psw;
	private String state;
	
	//构造函数
	public Pharmacy_table() {
		this.ph_id="";
		this.ph_name="";
		this.ph_tel="";
		this.ph_psw="";
		this.state="";
	}

	//getters setters
	public String getPh_id() {
		return ph_id;
	}

	public String getPh_name() {
		return ph_name;
	}

	public String getPh_tel() {
		return ph_tel;
	}

	public String getPh_psw() {
		return ph_psw;
	}

	public String getState() {
		return state;
	}

	public void setPh_id(String ph_id) {
		this.ph_id = ph_id;
	}

	public void setPh_name(String ph_name) {
		this.ph_name = ph_name;
	}

	public void setPh_tel(String ph_tel) {
		this.ph_tel = ph_tel;
	}

	public void setPh_psw(String ph_psw) {
		this.ph_psw = ph_psw;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	//end

}
