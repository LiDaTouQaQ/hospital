package dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import util.JDBCUtil;
import vo.P_table;
import vo.Schedule_table;

public class Schedule_tableDao {
		//声明变量
		private Connection connection;
		private List<Schedule_table> sts;
		private String tip="";

		//构造函数，初始化，获取数据库接口
		public Schedule_tableDao() {
				try {
						connection = JDBCUtil.getConnection();
				}catch(SQLException e)
				{
						e.getStackTrace();
				}
		}
		
		//医生排班自定义
		public String InsertSchedule_table(){
			//清空String
			tip="";
			SimpleDateFormat date1=new SimpleDateFormat("yyyy-MM-dd");
			String[] d_list = new String[100];
			String[] s_list = new String[100];
			for(int i=0;i<100;i++) {
				d_list[i]="";
				s_list[i]="";
			}
			String[] t_list = {"8:00-12:00","12:00-14:00","14:00-18:00","18:00-22:00","22:00-2:00"};
			try {
				String sql="select d_name,d_surgery from d_table;";
				PreparedStatement pstmt = connection.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				int h=0;
				while(rs.next()) {
					d_list[h] = rs.getString("d_name");
					s_list[h]=rs.getString("d_surgery");
					h++;
				}
				rs.close();
				sql = "select s_date from schedule_table;";
				pstmt = connection.prepareStatement(sql);
				rs = pstmt.executeQuery();
				String m_date = "";
				if(rs.next()) {
					m_date = rs.getString("s_date");
				}
				rs.close();
				// 当前日期和数据库日期相同，已排班
//				if(m_date.equals(date1.format(new Date()))) {
//					tip="当前时间内已经排班";
//					return tip;
//				}
				System.out.println(h);
				sql = "delete from schedule_table;";
				pstmt = connection.prepareStatement(sql);
				pstmt.executeUpdate();
				Random r = new Random();
				for (int i = 0; i < h; i++) {
		            int r1 = r.nextInt(h);
		            String temp = "";
		            temp = d_list[i];
		            d_list[i] = d_list[r1];
		            d_list[r1] = temp;
		            temp = s_list[i];
		            s_list[i] = s_list[r1];
		            s_list[r1] = temp;
		        }
				int k=t_list.length;
				for(int i=0,j=0;i<k;i++,j++) {
					try {
						if(j>i && d_list[j].equals("")) {
							break;
						}						
						sql = "insert into schedule_table(s_date,s_surgery,s_name,s_time) values(?,?,?,?);";
						pstmt = connection.prepareStatement(sql);
						pstmt.setString(1,date1.format(new Date()));
						pstmt.setString(2,s_list[j]);
						pstmt.setString(3,d_list[j]);
						pstmt.setString(4,t_list[i]);
						pstmt.executeUpdate();
						tip="success";
						if(j>=k-1 && !d_list[j+1].equals("") && i==k-1) {
							i=-1;
						}
						if(j<k && d_list[j+1].equals("")) {
							j=-1;
						}
					}catch (SQLException e) {
						tip="error";
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				tip="error";
				e.printStackTrace();
			}
			return tip;
		}
		
		//病人挂号,显示当天所有医生信息
		public List<Schedule_table> SearchSchedule_table(String date){
			//清空sts
			sts=new ArrayList<>();
			try {
						String sql="select s_surgery,s_name,s_time,d_introduction from schedule_table,d_table where s_date=? and s_surgery=d_surgery and s_name=d_name;";
						//执行SQL语句
						PreparedStatement pstmt = connection.prepareStatement(sql);
						pstmt.setString(1,date);
						ResultSet rs = pstmt.executeQuery();
						while(rs.next()) 
						{
							Schedule_table st = new Schedule_table();
							st.setS_surgery(rs.getString("s_surgery"));
							st.setS_name(rs.getString("s_name"));
							st.setS_time(rs.getString("s_time"));
							st.setS_introduction(rs.getString("d_introduction"));
							//将单条排班信息写入列表
							sts.add(st);
						}
			} catch (SQLException e) {
							e.printStackTrace();
					}
					return sts;
		}
		
		//病人根据医生姓名搜索医生信息，模糊查找
		public List<Schedule_table> SearchSchedule_tableByM(String date,String str){
			//清空sts
			sts=new ArrayList<>();
			try {
						String sql="select s_surgery,s_name,s_time,d_introduction from schedule_table,d_table where s_date=? and s_name like ? and s_surgery=d_surgery and s_name=d_name;";//待完善
						//执行SQL语句
						PreparedStatement pstmt = connection.prepareStatement(sql);
						pstmt.setString(1,date);
						pstmt.setString(2,"%"+str+"%");
						ResultSet rs = pstmt.executeQuery();
						while(rs.next()) 
						{
							Schedule_table st = new Schedule_table();
							st.setS_surgery(rs.getString("s_surgery"));
							st.setS_name(rs.getString("s_name"));
							st.setS_time(rs.getString("s_time"));
							st.setS_introduction(rs.getString("d_introduction"));
							//将单条排班信息写入列表
							sts.add(st);
						}
			} catch (SQLException e) {
							e.printStackTrace();
					}
					return sts;
		}
		
		//医生根据工号查看自己的排班，病人根据科室查询医生信息
		public List<Schedule_table> SearchSchedule_table_name(String d_name,String date){
			//清空sts
			sts=new ArrayList<>();
			try {
						String sql="select * from schedule_table where s_name=? and s_date=?;";//待完善
						//执行SQL语句
						PreparedStatement pstmt = connection.prepareStatement(sql);
						pstmt.setString(1,d_name);
						pstmt.setString(2,date);
						ResultSet rs = pstmt.executeQuery();
						if(rs.next()) 
						{
							Schedule_table st = new Schedule_table();							
							st.setS_surgery(rs.getString("s_surgery"));
							st.setS_name(rs.getString("s_name"));
							st.setS_time(rs.getString("s_time"));
							st.setS_date(rs.getString("s_date"));
							//将单条排班信息写入列表
							sts.add(st);
						}
			} catch (SQLException e) {
							e.printStackTrace();
					}
					return sts;
		}
		
		//医生根据工号查看自己的排班
				public List<Schedule_table> SearchSchedule_tableSelf(String date,String str){
					//清空sts
					sts=new ArrayList<>();
					try {
								String sql="select s_surgery,s_name,s_time,d_introduction from schedule_table,d_table where s_date=? and s_name=? and s_surgery=d_surgery and s_name=d_name;";//待完善
								//执行SQL语句
								PreparedStatement pstmt = connection.prepareStatement(sql);
								ResultSet rs = pstmt.executeQuery();
								if(rs.next()) 
								{
									Schedule_table st = new Schedule_table();
									
									st.setS_surgery(rs.getString("s_surgery"));
									st.setS_name(rs.getString("s_name"));
									st.setS_time(rs.getString("s_time"));
									//将单条排班信息写入列表
									sts.add(st);
								}
					} catch (SQLException e) {
									e.printStackTrace();
							}
							return sts;
				}
				
				//病人根据科室查询医生信息
				public List<Schedule_table> SearchSchedule_tableBySur(String date,String str){
					//清空sts
					sts=new ArrayList<>();
					try {
								String sql="select s_surgery,s_name,s_time,d_introduction from schedule_table,d_table where s_date=? and s_surgery=? and s_surgery=d_surgery and s_name=d_name;";
								//执行SQL语句
								PreparedStatement pstmt = connection.prepareStatement(sql);
								pstmt.setString(1, date);
								pstmt.setString(2, str);
								ResultSet rs = pstmt.executeQuery();
								while(rs.next()) 
								{
									Schedule_table st = new Schedule_table();							
									st.setS_surgery(rs.getString("s_surgery"));
									st.setS_name(rs.getString("s_name"));
									st.setS_time(rs.getString("s_time"));
									st.setS_introduction(rs.getString("d_introduction"));
									//将单条排班信息写入列表
									sts.add(st);
								}
					} catch (SQLException e) {
									e.printStackTrace();
							}
							return sts;
				}
				
		//end

}
