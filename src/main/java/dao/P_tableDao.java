package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.JDBCUtil;
import vo.P_table;

public class P_tableDao {
	//声明变量
	private Connection connection;
	private String tip="";
	private P_table pt;

	//构造函数，初始化，获取数据库接口
	public P_tableDao() {
			try {
					connection = JDBCUtil.getConnection();
			}catch(SQLException e)
			{
					e.getStackTrace();
			}
	}
	
	//用户注册，向p_table写入数据
	public String InsertP_table(String id,String name,String tel,String psw){
		//清空String
		tip="";
		try {
			String sql="insert into p_table(p_id,p_name,p_tel,p_psw) values(?,?,?,?);";
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
	public P_table SearchP_table(String tel,String psw){
		//清空pt
		pt=new P_table();
		try {
					String sql="select * from p_table where p_tel=? and p_psw=?;";
					//执行SQL语句
					PreparedStatement pstmt = connection.prepareStatement(sql);
					pstmt.setString(1,tel);
					pstmt.setString(2,psw);
					ResultSet rs = pstmt.executeQuery();
					//若有结果（有且只有一条记录）
					if(rs.next()) 
					{
						tip="success";
						pt.setP_id(rs.getString("p_id"));
						pt.setP_name(rs.getString("p_name"));
						pt.setP_tel(rs.getString("p_tel"));
						pt.setP_psw(rs.getString("p_psw"));
						pt.setP_gender(rs.getString("p_gender"));
						pt.setP_age(rs.getString("p_age"));
						pt.setP_nation(rs.getString("p_nation"));
						pt.setState(tip);
					}
					else 
					{
						tip="error";
						pt.setState(tip);
					}
					
		} catch (SQLException e) {
						e.printStackTrace();
				}
				return pt;
		}
	
	//用户注销账号
		public String DeleteP(String pid){
			//清空tip
			tip="";
			try {
						String sql="delete from p_table where p_id=?;";
						//执行SQL语句
						PreparedStatement pstmt = connection.prepareStatement(sql);
						pstmt.setString(1,pid);					
						int i=pstmt.executeUpdate();
						//若有结果（有且只有一条记录）
						if(i>0) 
						{						
							tip="success";									
						}
						else 
						{
							tip="error";						
						}
						
			} catch (SQLException e) {
							e.printStackTrace();
					}
					return tip;
			}

		//用户修改信息
		public P_table UpdateP(String pid,String gender,String age,String nation){
				//清空tip
				pt=new P_table();
				try {
							String sql="update p_table set p_gender=?,p_age=?,p_nation=? where p_id=?;";
							//执行SQL语句
							PreparedStatement pstmt = connection.prepareStatement(sql);
							pstmt.setString(1,gender);	
							pstmt.setString(2,age);	
							pstmt.setString(3,nation);	
							pstmt.setString(4,pid);					
							pstmt.executeUpdate();
							//若修改成功
							if(pstmt.executeUpdate()>0) 
							{						
								String sql2="select p_gender,p_age,p_nation from p_table where p_id=?;";
								//执行SQL语句
								PreparedStatement pstmt2 = connection.prepareStatement(sql2);	
								pstmt2.setString(1,pid);
								ResultSet rs2 = pstmt2.executeQuery();
								//若有结果（有且只有一条记录）
								if(rs2.next()) 
								{						
									tip="success";
									pt.setP_gender(rs2.getString("p_gender"));
									pt.setP_age(rs2.getString("p_age"));
									pt.setP_nation(rs2.getString("p_nation"));
									pt.setState(tip);						
								}
								else 
								{
									tip="error";
									pt.setState(tip);
								}
								
							}
							//若修改失败
							if(pstmt.executeUpdate()==0) 
							{
									tip="error";
									pt.setState(tip);							
							}
							
							
				} catch (SQLException e) {
								e.printStackTrace();
						}
						return pt;
		}
		
		//用户修改密码
		public String UpdatePsw(String pid,String oldpsw,String newpsw){
			//清空tip
			tip="";
			try {
					String sql="update p_table set p_psw=? where p_id=? and p_psw=?;";
						//执行SQL语句
						PreparedStatement pstmt = connection.prepareStatement(sql);
						pstmt.setString(1,newpsw);		
						pstmt.setString(2,pid);		
						pstmt.setString(3,oldpsw);		
						int i=pstmt.executeUpdate();
						//若修改成功
						if(i>0) 
						{						
							tip="success";									
						}
						else 
						{
							tip="error";						
						}
						
			} catch (SQLException e) {
							e.printStackTrace();
					}
					return tip;
			}
		
	//end
	

}
