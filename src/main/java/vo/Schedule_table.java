package vo;

public class Schedule_table {
	private String s_surgery;
	private String s_name;
	private String s_time;
	private String s_introduction;
	private String s_date;
	
	//构造函数
	public Schedule_table() {
			this.s_surgery="";
			this.s_name="";
			this.s_time="";
			this.s_introduction="";
			this.s_date="";
		}

	//getters setters
	
	public String getS_surgery() {
		return s_surgery;
	}

	public String getS_date() {
		return s_date;
	}

	public void setS_date(String s_date) {
		this.s_date = s_date;
	}

	public String getS_name() {
		return s_name;
	}

	public String getS_time() {
		return s_time;
	}

	public String getS_introduction() {
		return s_introduction;
	}

	public void setS_introduction(String s_introduction) {
		this.s_introduction = s_introduction;
	}

	public void setS_surgery(String s_surgery) {
		this.s_surgery = s_surgery;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public void setS_time(String s_time) {
		this.s_time = s_time;
	}
		
	//end
}
