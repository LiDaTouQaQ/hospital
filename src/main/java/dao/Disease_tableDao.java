package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;
import vo.Disease_table;
import vo.P_table;

public class Disease_tableDao {
	//声明变量
		private Connection connection;
		private String tip="";
		private List<Disease_table> dists;

		//构造函数，初始化，获取数据库接口
		public Disease_tableDao() {
				try {
						connection = JDBCUtil.getConnection();
				}catch(SQLException e)
				{
						e.getStackTrace();
				}
		}
		
		//超级管理员，向disease_table写入数据
		public String InsertDisease_table(String id,String name,String type,String intro){
			//清空String
			tip="";
			try {
				String sql="insert into disease_table values(?,?,?,?);";
				//执行SQL语句
				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,id);
				pstmt.setString(2,name);
				pstmt.setString(3,type);
				pstmt.setString(4,intro);
				pstmt.executeUpdate();
				tip="success";
				} catch (SQLException e) {
				tip="error";
				e.printStackTrace();
			}
			return tip;
		}
		
		//显示disease_table所有数据
		public List<Disease_table> SearchDisease_tableAll(){
			//清空dists
			dists=new ArrayList<>();
			try {
						String sql="select * from disease_table;";
						//执行SQL语句
						PreparedStatement pstmt = connection.prepareStatement(sql);						
						ResultSet rs = pstmt.executeQuery();
						//若有结果
						while(rs.next()) 
						{
							Disease_table dist=new Disease_table();							
							dist.setDisease_name(rs.getString("disease_name"));
							dist.setDisease_type(rs.getString("disease_type"));
							dist.setDisease_introduction(rs.getString("disease_introduction"));
							dists.add(dist);
						}						
			} catch (SQLException e) {
							e.printStackTrace();
					}
					return dists;
		}
		
		//根据名称、类型、描述 模糊查询数据
		public List<Disease_table> SearchDisease_table(String str){
			//清空dists
			dists=new ArrayList<>();
			try {
						String sql="select * from disease_table where disease_name like ? or disease_type like ? or disease_introduction like ?;";
						//执行SQL语句
						PreparedStatement pstmt = connection.prepareStatement(sql);
						pstmt.setString(1,"%"+str+"%");		
						ResultSet rs = pstmt.executeQuery();
						//若有结果
						while(rs.next()) 
						{
							Disease_table dist=new Disease_table();							
							dist.setDisease_name(rs.getString("disease_name"));
							dist.setDisease_type(rs.getString("disease_type"));
							dist.setDisease_introduction(rs.getString("disease_introduction"));
							dists.add(dist);
						}						
			} catch (SQLException e) {
							e.printStackTrace();
					}
					return dists;
		}	
		//end

}
