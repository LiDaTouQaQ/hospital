package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;
import vo.Case_table;
import vo.Medicine_table;

public class Medicine_tableDao {
	//声明变量
		private Connection connection;
		private String tip="";
		private Medicine_table med;
		private List<Medicine_table> meds;
		//构造函数
		public Medicine_tableDao() {
			try {
					connection = JDBCUtil.getConnection();
			}catch(SQLException e)
			{
					e.getStackTrace();
			}
		}
		//查询所有药材和器械信息
		public List<Medicine_table> Search_Medicine(){
			meds = new ArrayList<>();
			try {
				String sql = "select g_id,g_name,g_type,g_price,g_number,g_brief,g_date from g_table;";
				PreparedStatement pstmt = connection.prepareStatement(sql);			
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					Medicine_table med=new Medicine_table();
					med.setG_id(rs.getString("g_id"));
					med.setG_name(rs.getString("g_name"));
					med.setG_type(rs.getString("g_type"));
					med.setG_price(rs.getString("g_price"));
					med.setG_number(rs.getString("g_number"));
					med.setG_brief(rs.getString("g_brief"));
					med.setG_date(rs.getString("g_date"));
					
					meds.add(med);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return meds;
		}

		//根据名称查询器材查询病例信息
		public List<Medicine_table> Search_MedicineByName(String str){
			meds = new ArrayList<>();
			try {
				String sql = "select g_id,g_name,g_type,g_price,g_number,g_brife,g_date from g_table where g_name = ?";
				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,str);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					Medicine_table cat=new Medicine_table();
					med.setG_id(rs.getNString("g_id"));
					med.setG_name(rs.getString("g_name"));
					med.setG_type(rs.getNString("g_type"));
					med.setG_price(rs.getString("g_price"));
					med.setG_number(rs.getNString("g_number"));
					med.setG_brief(rs.getString("g_briefe"));
					med.setG_date(rs.getNString("g_date"));
					
					meds.add(med);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return meds;
			
		}
		
	//药品入库
		public String AddMedicine(String id,String name,String type,String price,String num,String brief,String date){
			tip="";
			try {
				String sql = "insert into g_table values(?,?,?,?,?,?,?);";
				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,id);
				pstmt.setString(2,name);
				pstmt.setString(3,type);
				pstmt.setString(4,price);
				pstmt.setString(5,num);
				pstmt.setString(6,brief);
				pstmt.setString(7,date);
				int i=pstmt.executeUpdate();
				if(i>0) {tip="success";}
				else {tip="error";}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return tip;
			
		}

		
}
