package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.JDBCUtil;
import vo.Pharmacy_table;

public class Pharmacy_tableDao {
	//声明变量
	private Connection connection;
	private String tip="";
	private Pharmacy_table pht;

	//构造函数，初始化，获取数据库接口
	public Pharmacy_tableDao() {
			try {
					connection = JDBCUtil.getConnection();
			}catch(SQLException e)
			{
					e.getStackTrace();
			}
	}
	
	//药房人员注册，向pharmacy_table写入数据
	public String InsertPharmacy_table(String id,String name,String tel,String psw){
				//清空String
				tip="";
				try {
					String sql="insert into pharmacy_table(ph_id,ph_name,ph_tel,ph_psw) values(?,?,?,?);";
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
	
	//药房人员登录，从p_table匹配数据
		public Pharmacy_table SearchPharmacy_table(String tel,String psw){
			//清空pht
			pht=new Pharmacy_table();
			try {
						String sql="select * from pharmacy_table where ph_tel=? and ph_psw=?;";
						//执行SQL语句
						PreparedStatement pstmt = connection.prepareStatement(sql);
						pstmt.setString(1,tel);
						pstmt.setString(2,psw);
						ResultSet rs = pstmt.executeQuery();
						//若有结果（有且只有一条记录）
						if(rs.next()) 
						{
							tip="success";
							pht.setPh_id(rs.getString("ph_id"));
							pht.setPh_name(rs.getString("ph_name"));
							pht.setPh_tel(rs.getString("ph_tel"));
							pht.setPh_psw(rs.getString("ph_psw"));
							pht.setState(tip);
						}
						else 
						{
							tip="error";
							pht.setState(tip);
						}
						
			} catch (SQLException e) {
							e.printStackTrace();
					}
					return pht;
			}
		
		//end
}
