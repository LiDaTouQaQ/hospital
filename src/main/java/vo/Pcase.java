package vo;

public class Pcase {
	private String case_time;
	private String case_dname;
	private String type;
	private String case_introduction;
	private String case_state;
	
	//构造函数
	public Pcase() {
		this.case_time="";
		this.case_dname="";
		this.type="";
		this.case_introduction="";
		this.case_state="";
	}

	//getters setters
	public String getCase_time() {
		return case_time;
	}

	public String getCase_dname() {
		return case_dname;
	}

	public String getCase_state() {
		return case_state;
	}

	public void setCase_state(String case_state) {
		this.case_state = case_state;
	}

	public String getType() {
		return type;
	}

	public String getCase_introduction() {
		return case_introduction;
	}

	public void setCase_time(String case_time) {
		this.case_time = case_time;
	}

	public void setCase_dname(String case_dname) {
		this.case_dname = case_dname;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setCase_introduction(String case_introduction) {
		this.case_introduction = case_introduction;
	}

}
