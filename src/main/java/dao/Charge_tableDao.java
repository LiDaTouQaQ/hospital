package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.JDBCUtil;
import vo.Charge_table;

public class Charge_tableDao {
	//声明变量
	private Connection connection;
	private String tip="";
	private Charge_table ct;

	//构造函数，初始化，获取数据库接口
	public Charge_tableDao() {
			try {
					connection = JDBCUtil.getConnection();
			}catch(SQLException e)
			{
					e.getStackTrace();
			}
	}
	
	//医生注册，向charge_table写入数据
	public String InsertCharge_table(String id,String name,String tel,String psw){
				//清空String
				tip="";
				try {
					String sql="insert into charge_table(c_id,c_name,c_tel,c_psw) values(?,?,?,?);";
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
		public Charge_table SearchP_table(String tel,String psw){
			//清空ct
			ct=new Charge_table();
			try {
						String sql="select * from charge_table where c_tel=? and c_psw=?;";
						//执行SQL语句
						PreparedStatement pstmt = connection.prepareStatement(sql);
						pstmt.setString(1,tel);
						pstmt.setString(2,psw);
						ResultSet rs = pstmt.executeQuery();
						//若有结果（有且只有一条记录）
						if(rs.next()) 
						{
							tip="success";
							ct.setC_id(rs.getString("c_id"));
							ct.setC_name(rs.getString("c_name"));
							ct.setC_tel(rs.getString("c_tel"));
							ct.setC_psw(rs.getString("c_psw"));
							ct.setState(tip);
						}
						else 
						{
							tip="error";
							ct.setState(tip);
						}
						
			} catch (SQLException e) {
							e.printStackTrace();
					}
					return ct;
			}
		
		//end
		
		public String UpdateCharge_table_Byc_id(String c_tel,String c_name,String c_id) {
			tip = "";
			try {
				String sql="Update charge_table set c_name=?,c_tel=? where c_id=?;";
				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,c_name);
				pstmt.setString(2,c_tel);
				pstmt.setString(3,c_id);
				int i=pstmt.executeUpdate();
				if(i>0) {
					tip = "success";
				}else {
					tip = "error";
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return tip;
		}

}
