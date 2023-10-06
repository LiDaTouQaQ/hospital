package vo;

public class Case_table {
	private int case_id;
	private String p_id;
	private String case_time;
	private String past_disease;
	private String diagnosis;
	private String opinion;
	private String cost;
	private String case_type;
	private String d_id;
	private String tip;
	private String p_name;
	private String d_name;
	private String medicine_state;
	
	
	
	public Case_table(int case_id, String p_id, String case_time, String past_disease, String diagnosis, String opinion,
			String cost, String case_type, String d_id) {
		super();
		this.case_id = case_id;
		this.p_id = p_id;
		this.case_time = case_time;
		this.past_disease = past_disease;
		this.diagnosis = diagnosis;
		this.opinion = opinion;
		this.cost = cost;
		this.case_type = case_type;
		this.d_id = d_id;
	}
	
	public Case_table() {
		super();
		this.case_id = 1001;
		this.p_id = "";
		this.case_time = "";
		this.past_disease = "";
		this.diagnosis = "";
		this.opinion = "";
		this.cost = "";
		this.case_type = "";
		this.d_id = "";
		this.tip = "";
		this.p_name = "";
		this.d_name="";
		this.medicine_state="";
	}

	
	
	public String getMedicine_state() {
		return medicine_state;
	}

	public void setMedicine_state(String medicine_state) {
		this.medicine_state = medicine_state;
	}

	public String getD_name() {
		return d_name;
	}

	public void setD_name(String d_name) {
		this.d_name = d_name;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public int getCase_id() {
		return case_id;
	}
	public void setCase_id(int case_id) {
		this.case_id = case_id;
	}
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public String getCase_time() {
		return case_time;
	}
	public void setCase_time(String case_time) {
		this.case_time = case_time;
	}
	public String getPast_disease() {
		return past_disease;
	}
	public void setPast_disease(String past_disease) {
		this.past_disease = past_disease;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getCase_type() {
		return case_type;
	}
	public void setCase_type(String case_type) {
		this.case_type = case_type;
	}
	public String getD_id() {
		return d_id;
	}
	public void setD_id(String d_id) {
		this.d_id = d_id;
	}
	
}
