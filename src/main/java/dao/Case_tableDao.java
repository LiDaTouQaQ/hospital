package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;
import vo.Case_table;
import vo.Schedule_table;
import vo.Pcase;

public class Case_tableDao {
	//声明变量
	private Connection connection;
	private String tip="";
	private Case_table cat;
	private List<Case_table> cats;
	private List<Pcase> pcases;
	
	public Case_tableDao() {
		try {
				connection = JDBCUtil.getConnection();
		}catch(SQLException e)
		{
				e.getStackTrace();
		}
	}
	//查询病例是否存在
	public Case_table Search_Case_table(String case_id) {
		cat = new Case_table();
		try {
			String sql = "select p_name from p_table,case_table where p_id=case_p_id and case_id=?;";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,case_id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				tip = "success";
				cat.setP_name(rs.getString("p_name"));
				cat.setTip(tip);
			}else 
			{
				tip="error";
				cat.setTip(tip);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return cat;
	}
	//医生查询手下所有病人挂号信息
	public List<Case_table> SearchCase_tableByd_id(String d_id){
		cats = new ArrayList<>();
		try {
			String sql = "select p_name,case_time,diagnosis,opinion,case_id from p_table,r_table,case_table where p_id=r_p_id and p_id=case_p_id and case_d_id=? and case_state='未缴费';";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,d_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Case_table cat=new Case_table();
				cat.setP_name(rs.getString("p_name"));
				cat.setCase_time(rs.getString("case_time"));
				cat.setDiagnosis(rs.getString("diagnosis"));
				cat.setOpinion(rs.getString("opinion"));
				cat.setCase_id(rs.getInt("case_id"));
				cats.add(cat);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return cats;
	}
	//查询某个病人用户自己的病例信息
		public List<Pcase> Search_CaseSelf(String pid){
			pcases = new ArrayList<>();
			try {
				String sql = "select case_time,past_disease,diagnosis,opinion,operation,d_name,case_state from case_table,d_table where case_p_id=? and d_name=case_d_id;";
				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,pid);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					Pcase pcase=new Pcase();
					pcase.setCase_time(rs.getString("case_time"));
					pcase.setType("病例");
					pcase.setCase_dname(rs.getString("d_name"));				
					pcase.setCase_introduction(rs.getString("past_disease")+";"+rs.getString("diagnosis")+";"+rs.getString("opinion")+";"+rs.getString("operation"));
					pcase.setCase_state(rs.getString("case_state"));
					pcases.add(pcase);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return pcases;
		}
	
	//医生修改病人病例信息
	public String UpdateCase_tableBycase_id(String case_id,String diagnosis,String past_disease,String opinion,String opinion_number,String operation,String cost) {
		tip="00";
		try {
			String sql = "update case_table set diagnosis=?,past_disease=?,opinion=?,opinion_number=?,operation=?,cost=? where case_id=?;";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,diagnosis);
			pstmt.setString(2,past_disease);
			pstmt.setString(3,opinion);
			pstmt.setString(4,opinion_number);
			pstmt.setString(5,operation);
			pstmt.setString(6,cost);
			pstmt.setString(7,case_id);
			if(pstmt.executeUpdate()>0) {
				tip = "success";
				return tip;
			}else {
				tip = "error";
				return tip;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return tip;
	}
	//根据医生名字或日期查找自己的病例信息
		public List<Pcase> Search_CaseByStr(String str,String pid){
			pcases = new ArrayList<>();
			try {
				String sql = "select case_time,past_disease,diagnosis,opinion,d_name,case_state from case_table,d_table where case_p_id=? and d_id=case_d_id and (d_name like ? or case_time like ?);";
				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,pid);
				pstmt.setString(2,"%"+str+"%");
				pstmt.setString(3,"%"+str+"%");
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					Pcase pcase=new Pcase();
					pcase.setCase_time(rs.getString("case_time"));
					pcase.setType("病例");
					pcase.setCase_dname(rs.getString("d_name"));				
					pcase.setCase_introduction(rs.getString("past_disease")+rs.getString("diagnosis")+rs.getString("opinion"));
					pcase.setCase_state(rs.getString("case_state"));
					pcases.add(pcase);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return pcases;
		}
		
	//收费人员查询所有收费信息
	public List<Case_table> Search_case_tableAllFor_toll(){
		cats = new ArrayList<>();
		try {
			String sql = "select case_id,p_name,case_time,d_name,cost,case_state from p_table,d_table,case_table where d_name=case_d_id and p_id=case_p_id;";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Case_table cat=new Case_table();
				cat.setCase_id(rs.getInt("case_id"));
				cat.setP_name(rs.getString("p_name"));
				cat.setCase_time(rs.getString("case_time"));
				cat.setD_name(rs.getString("d_name"));
				cat.setCase_type(rs.getString("case_state"));
				cat.setCost(rs.getString("cost"));
				cats.add(cat);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return cats;
	}
	//收费人员更新收费信息
	public String Update_case_table_case_state(String case_id) {
		tip = "";
		try {
			String sql = "Update case_table set case_state='已缴费' where case_id=?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,case_id);
			if(pstmt.executeUpdate()>0) {
				tip = "success";
			}else {
				tip = "error";
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return tip;
	}
	//取药时获取数量更新药品数量
	public String Update_g_table_num(String case_id) {
		String g_name="";
		String ng_number="";
		String g_number="";
		try {
			String sql = "select opinion,opinion_number from case_table where case_id=?;";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,case_id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				g_name = rs.getString("opinion");
				g_number = rs.getString("opinion_number");
			}
			rs.close();
			sql = "select g_number from g_table where g_name=?;";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,g_name);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ng_number = rs.getString("g_number");
			}
			int num=Integer.valueOf(ng_number)-Integer.valueOf(g_number);
			sql = "Update g_table set g_number=? where g_name=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,String.valueOf(num));
			pstmt.setString(2,g_name);
			if(pstmt.executeUpdate()>0) {
				tip = "success";
			}else {
				tip = "error";
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return tip;
	}
	//根据病例id修改病例的开药状态
		public String Update_case_table_medicine_state(String case_id) {
			tip = "";
			try {
				String sql = "Update case_table set medicine_state='已取药' where case_id=?";
				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,case_id);
				if(pstmt.executeUpdate()>0) {
					tip = "success";
				}else {
					tip = "error";
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return tip;
		}
		//查询已缴费未开药的所有病例信息
		public List<Case_table> Search_CaseByState(){
			cats = new ArrayList<>();
			try {
				String sql = "select case_id,p_name,case_time,opinion,cost,case_state,medicine_state from p_table,case_table where p_id=case_p_id and case_state='已缴费' and medicine_state='未取药';";
				PreparedStatement pstmt = connection.prepareStatement(sql);			
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					Case_table cat=new Case_table();
					cat.setCase_id(rs.getInt("case_id"));
					cat.setP_name(rs.getString("p_name"));
					cat.setCase_time(rs.getString("case_time"));
					cat.setOpinion(rs.getString("opinion"));
					cat.setCost(rs.getString("cost"));
					cat.setCase_type(rs.getString("case_state"));
					cat.setMedicine_state(rs.getString("medicine_state"));
					
					cats.add(cat);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return cats;
		}
		//根据病人姓名查询病例信息
		public List<Case_table> Search_CaseByName(String str){
			cats = new ArrayList<>();
			try {
				String sql = "select case_id,p_name,case_time,opinion,cost,case_type,medicine_state from p_table,case_table where p_id=case_p_id and p_name like ?;";
				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,"%"+str+"%");
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					Case_table cat=new Case_table();
					cat.setCase_id(rs.getInt("case_id"));
					cat.setP_name(rs.getString("p_name"));
					cat.setCase_time(rs.getString("case_time"));
					cat.setOpinion(rs.getString("opinion"));
					cat.setCost(rs.getString("cost"));
					cat.setCase_type(rs.getString("case_type"));
					cat.setMedicine_state(rs.getString("p_name"));
					
					cats.add(cat);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return cats;
		}
}
