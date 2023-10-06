package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.JDBCUtil;
import vo.P_table;

public class Register_tableDao {
	//声明变量
	private Connection connection;
	private int r=0;
	private String tip="";

	//构造函数，初始化，获取数据库接口
	public Register_tableDao() {
				try {
						connection = JDBCUtil.getConnection();
				}catch(SQLException e)
				{
						e.getStackTrace();
				}
	}
		
	//用户挂号，向r_table写入数据
	public String InsertR_table(String time,String dname,String p_id,String sur){
			//清空String
			tip="";
			try {
				String sql="insert into r_table values(?,?,?,?);";
				//执行SQL语句
				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,time);
				pstmt.setString(2,dname);
				pstmt.setString(3,p_id);
				pstmt.setString(4,sur);
				int i = pstmt.executeUpdate();
				if(i>0) {
					tip="success";//挂号成功
				}
				
				sql = "insert into case_table (case_p_id,case_d_id) values(?,?);";
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,p_id);
				pstmt.setString(2,dname);
				i = pstmt.executeUpdate();
				if(i>0) {
					tip="success";//病例写入成功
				}
				
			} catch (SQLException e) {
				tip="error";
				e.printStackTrace();
			}
			return tip;
		}
		
	//判断是否挂过号
	public int SearchR_table(String pname){
			//清空
			r=0;
			try {
						String sql="select * from case_table where case_p_id=? and case_state='未缴费';";
						//执行SQL语句
						PreparedStatement pstmt = connection.prepareStatement(sql);
						pstmt.setString(1,pname);						
						ResultSet rs = pstmt.executeQuery();
						//若有结果（有且只有一条记录）
						if(rs.next()) 
						{						
							r=1;
						}
						
			} catch (SQLException e) {
							e.printStackTrace();
					}
					return r;
		}
		
		//end
}
