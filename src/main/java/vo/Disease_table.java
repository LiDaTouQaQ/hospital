package vo;

public class Disease_table {
	private String disease_id;
	private String disease_name;
	private String disease_type;
	private String disease_introduction;
		
	//构造函数
	public Disease_table() {
		this.disease_id="";
		this.disease_name="";
		this.disease_type="";
		this.disease_introduction="";
	}

	//getters setters
	public String getDisease_id() {
		return disease_id;
	}

	public String getDisease_name() {
		return disease_name;
	}

	public String getDisease_type() {
		return disease_type;
	}

	public String getDisease_introduction() {
		return disease_introduction;
	}

	public void setDisease_id(String disease_id) {
		this.disease_id = disease_id;
	}

	public void setDisease_name(String disease_name) {
		this.disease_name = disease_name;
	}

	public void setDisease_type(String disease_type) {
		this.disease_type = disease_type;
	}

	public void setDisease_introduction(String disease_introduction) {
		this.disease_introduction = disease_introduction;
	}

}
