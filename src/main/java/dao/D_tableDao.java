package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.JDBCUtil;
import vo.D_table;


public class D_tableDao {
	//声明变量
	private Connection connection;
	private String tip="";
	private D_table dt;
	

	//构造函数，初始化，获取数据库接口
	public D_tableDao() {
		try {
				connection = JDBCUtil.getConnection();
		}catch(SQLException e)
		{
				e.getStackTrace();
		}
	}
	
	//医生注册，向d_table写入数据
	public String InsertD_table(String id,String name,String tel,String psw){
			//清空String
			tip="";
			try {
				String sql="insert into d_table(d_id,d_name,d_tel,d_psw) values(?,?,?,?);";
				//执行SQL语句
				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,id);
				pstmt.setString(2,name);
				pstmt.setString(3,tel);
				pstmt.setString(4,psw);
				pstmt.executeUpdate();
				tip="success";//注册成功
			} catch (SQLException e) {
				tip="error";//注册失败
				e.printStackTrace();
			}
			return tip;
	}
	
		//用户登录，从p_table匹配数据
		public D_table SearchP_table(String tel,String psw){
			//清空dt
			dt=new D_table();
			try {
						String sql="select * from d_table where d_tel=? and d_psw=?;";
						//执行SQL语句
						PreparedStatement pstmt = connection.prepareStatement(sql);
						pstmt.setString(1,tel);
						pstmt.setString(2,psw);
						ResultSet rs = pstmt.executeQuery();
						//若有结果（有且只有一条记录）
						if(rs.next()) 
						{
							tip="success";
							dt.setD_id(rs.getString("d_id"));
							dt.setD_name(rs.getString("d_name"));
							dt.setD_tel(rs.getString("d_tel"));
							dt.setD_psw(rs.getString("d_psw"));
							dt.setD_introduction(rs.getString("d_introduction"));
							dt.setD_surgery(rs.getString("d_surgery"));
							dt.setState(tip);
						}
						else 
						{
							tip="error";
							dt.setState(tip);
						}
						
			} catch (SQLException e) {
							e.printStackTrace();
					}
					return dt;
			}
		
		//end
	
		//医生基本信息修改
		public String UpdateDoct_Change(String d_name,String d_sur,String d_intor,String d_tel) {
			dt = new D_table();
			int i=0;
			try {
				String sql="Update d_table set d_name=?,d_surgery=?,d_introduction=? where d_tel=?;";
				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,d_name);
				pstmt.setString(2,d_sur);
				pstmt.setString(3,d_intor);
				pstmt.setString(4,d_tel);
				i=pstmt.executeUpdate();
			}catch (SQLException e) {					
				e.printStackTrace();
			}
			if(i==1) 
			{
				tip="success";//修改成功
				dt.setState(tip);
			}
			else
			{
				tip="error";//修改失败
				dt.setState(tip);
			}
			return tip;
		}
	
}
