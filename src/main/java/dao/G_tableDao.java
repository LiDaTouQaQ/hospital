package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;
import vo.Drug;

public class G_tableDao {
		//声明变量
		private Connection connection;
		private List<Drug> drugs;
		

		//构造函数，初始化，获取数据库接口
		public G_tableDao() {
			try {
					connection = JDBCUtil.getConnection();
			}catch(SQLException e)
			{
					e.getStackTrace();
			}
		}
		
		//显示所有药品信息
		public List<Drug> SearchG_tableAll(){
					//清空drug
					drugs=new ArrayList<>();
					try {
								String sql="select g_name,g_type,g_price,g_brief from g_table;";
								//执行SQL语句
								PreparedStatement pstmt = connection.prepareStatement(sql);								
								ResultSet rs = pstmt.executeQuery();
								//若有结果
								while(rs.next()) 
								{
									Drug drug=new Drug();
									drug.setG_name(rs.getString("g_name"));
									drug.setG_type(rs.getString("g_type"));
									drug.setG_price(rs.getString("g_price"));
									drug.setG_brief(rs.getString("g_brief"));
									drugs.add(drug);
								}								
					} catch (SQLException e) {
									e.printStackTrace();
							}
					return drugs;
		}
		
		//根据药物名/类型/功效模糊查找显示药物信息
		public List<Drug> SearchG_table(String str){
			//清空drug
			drugs=new ArrayList<>();
			try {
						String sql="select g_name,g_type,g_price,g_brief from g_table "
								+ "where g_name like ? or g_type like ? or g_brief like ?;";
						//执行SQL语句
						PreparedStatement pstmt = connection.prepareStatement(sql);		
						pstmt.setString(1, "%"+str+"%");
						pstmt.setString(2, "%"+str+"%");
						pstmt.setString(3, "%"+str+"%");
						ResultSet rs = pstmt.executeQuery();
						//若有结果
						while(rs.next()) 
						{
							Drug drug=new Drug();
							drug.setG_name(rs.getString("g_name"));
							drug.setG_type(rs.getString("g_type"));
							drug.setG_price(rs.getString("g_price"));
							drug.setG_brief(rs.getString("g_brief"));
							drugs.add(drug);
						}								
			} catch (SQLException e) {
							e.printStackTrace();
					}
				return drugs;
		}
				
		//end
			

}
