package service;

import java.util.List;

import dao.P_tableDao;
import dao.D_tableDao;
import dao.Charge_tableDao;
import dao.Pharmacy_tableDao;
import dao.Schedule_tableDao;
import dao.Case_tableDao;
import dao.Disease_tableDao;
import dao.G_tableDao;
import dao.Register_tableDao;
import dao.Medicine_tableDao;

import vo.P_table;
import vo.D_table;
import vo.Charge_table;
import vo.Pharmacy_table;
import vo.Schedule_table;
import vo.Case_table;
import vo.Pcase;
import vo.Disease_table;
import vo.Drug;
import vo.Medicine_table;

public class Svc {
	//变量声明
	private P_tableDao ptd = new P_tableDao();
	private D_tableDao dtd = new D_tableDao();
	private Charge_tableDao ctd = new Charge_tableDao();
	private Pharmacy_tableDao phtd = new Pharmacy_tableDao();
	private Schedule_tableDao std = new Schedule_tableDao();
	private Case_tableDao catd = new Case_tableDao();
	private Medicine_tableDao med = new Medicine_tableDao();
	
	private P_table pt=new P_table();
	private D_table dt=new D_table();
	private Charge_table ct=new Charge_table();
	private Pharmacy_table pht=new Pharmacy_table();
	private Schedule_table st=new Schedule_table();
	private Case_table cat=new Case_table();
	private Register_tableDao rtd = new Register_tableDao();
	private Case_tableDao casetd = new Case_tableDao();
	private G_tableDao gtd = new G_tableDao();
	private Disease_tableDao distd = new Disease_tableDao();

	
	public Svc() {};
	//病人注册
	public String P_register(String id,String name,String tel,String psw) {
		return ptd.InsertP_table(id, name, tel, psw);
	}
	//医生注册
	public String D_register(String id,String name,String tel,String psw) {
		return dtd.InsertD_table(id, name, tel, psw);
	}
	//收费人员注册
	public String C_register(String id,String name,String tel,String psw) {
		return ctd.InsertCharge_table(id, name, tel, psw);
	}
	//药房人员注册
	public String Ph_register(String id,String name,String tel,String psw) {
		return phtd.InsertPharmacy_table(id, name, tel, psw);
	}
	
	//病人登录
	public P_table P_login(String tel,String psw){
		return ptd.SearchP_table(tel, psw);
	}
	//医生登录
	public D_table D_login(String tel,String psw){
		return dtd.SearchP_table(tel, psw);
	}
	//收费登录
	public Charge_table C_login(String tel,String psw){
		return ctd.SearchP_table(tel, psw);
	}
	//药房人员登录
	public Pharmacy_table Ph_login(String tel,String psw){
		return phtd.SearchPharmacy_table(tel, psw);
	}
	//医生信息修改
	public String Doct_Change(String d_name,String d_sur,String d_intor,String d_tel) {
		return dtd.UpdateDoct_Change(d_name, d_sur, d_intor,d_tel);
	}
	//医生排班自定义
	public String D_Schedul() {
		return std.InsertSchedule_table();
	}
	//医生排班查找
	public List<Schedule_table> S_show_name(String d_name,String date){
		return std.SearchSchedule_table_name(d_name,date);
	}
	//医生所有病人病例信息查找
	public List<Case_table> Search_casetableAll(String d_name){
		return catd.SearchCase_tableByd_id(d_name);
	}
	//医生进入病人病例信息
	public Case_table Search_casetableByCase_id(String case_id) {
		return catd.Search_Case_table(case_id);
	}
	//医生修改病人病例信息
	public String UpdateCase_tableBycase_id(String case_id,String diagnosis,String past_disease,String opinion,String opinion_number,String operation,String cost) {
		return catd.UpdateCase_tableBycase_id(case_id, diagnosis, past_disease, opinion, opinion_number, operation, cost);
	}
	//收费人员信息修改
	public String UpdateCharge_table_Byc_id(String c_tel,String c_name,String c_id) {
		return ctd.UpdateCharge_table_Byc_id(c_tel,c_name,c_id);
	}
	//收费人员查找所有收费信息
	public List<Case_table> Search_case_tableAllFor_toll(){
		return catd.Search_case_tableAllFor_toll();
	}
	//收费人员更新收费状态
	public String Update_case_table_case_state(String case_id) {
		return catd.Update_case_table_case_state(case_id);
	}
	//显示当天所有医生值班信息
		public List<Schedule_table> S_show(String date){
			return std.SearchSchedule_table(date);
		}
		
		//病人根据医生名字查找
		public List<Schedule_table> S_showM(String date,String str){
			return std.SearchSchedule_tableByM(date, str);
		}
		
		//病人根据科室名字查找
		public List<Schedule_table> S_showSur(String date,String str){
			return std.SearchSchedule_tableBySur(date, str);
		}
		
		//判断某个病人是否已经挂过号
		public int Check(String pname)
		{
			return rtd.SearchR_table(pname);
		}
			
		//病人挂号
		public String Register(String time,String dname,String pname,String sur) {
			return rtd.InsertR_table(time, dname, pname, sur);
		}
		
		//病人查看自己的病例信息
		public List<Pcase> Case_showSelf(String pid){
			return casetd.Search_CaseSelf(pid);
		}
		
		//病人根据医生姓名模糊查看自己的病例信息
		public List<Pcase> Case_showSelfByStr(String str,String pid){
			return casetd.Search_CaseByStr(str, pid);
		}
		
		//显示所有药物信息
		public List<Drug> Drug_showAll(){
			return gtd.SearchG_tableAll();
		}
		
		//显示查找出来的药物信息
		public List<Drug> Drug_show(String str){
			return gtd.SearchG_table(str);
		}
		
		//病人注销账号
		public String DelP(String pid) {
			return ptd.DeleteP(pid);
		}
		
		//病人修改密码
		public String UpdatePsw(String pid,String oldpsw,String newpsw) {
				return ptd.UpdatePsw(pid, oldpsw, newpsw);
		}
		
		//病人修改性别、年龄、民族
		public P_table UpdateP(String pid,String pgender,String page,String pnation){
				return ptd.UpdateP(pid, pgender, page, pnation);
		}
		
		//显示所有疾病信息
		public List<Disease_table> Disease_showAll(){
			return distd.SearchDisease_tableAll();
		}
		
		//根据名称、类型或描述模糊查询疾病信息
		public List<Disease_table> Disease_show(String str){
			return distd.SearchDisease_table(str);
		}
		//查询所有已缴费
		public List<Case_table> Search_CaseByState(){
			return casetd.Search_CaseByState();
		}
		//
		public List<Case_table> Search_CaseByName(String str){
			return casetd.Search_CaseByName(str);
		}
		
		//查询所有药材和器械信息
		public List<Medicine_table> Search_Medicine(){
			return med.Search_Medicine();
		}

		//根据名称查询器材查询病例信息
		public List<Medicine_table> Search_MedicineByName(String str){
			
			return med.Search_MedicineByName(str);
			
		}
		
		//根据名称查询器材查询病例信息
			public String Update_case_table_medicine_state(String case_id){
				
				return casetd.Update_case_table_medicine_state(case_id);
				
			}
		
		//
		public String AddMedicine(String id,String name,String type,String price,String num,String brief,String date){
			
				return med.AddMedicine(id, name, type, price, num, brief, date);
			}
		//取药时更新药品数量
		public String Update_g_table_num(String case_id) {
			return catd.Update_g_table_num(case_id);
		}
}