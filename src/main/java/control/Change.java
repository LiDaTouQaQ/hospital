package control;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.Svc;
import vo.Charge_table;
import vo.D_table;
import vo.Drug;
import vo.P_table;
import vo.Pcase;
import vo.Pharmacy_table;
import vo.Schedule_table;

@WebServlet("/Change")
public class Change extends HttpServlet{
		//变量声明
		private static final long serialVersionUID=1L;
		private Svc s=new Svc();
			
		//doPost方法
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
						//声明变量
						RequestDispatcher dispatcher=null;
						HttpSession session=request.getSession();
						String pid=request.getParameter("p_id");
						String select=request.getParameter("select");
						String tip="";//是否注销成功，是否修改成功
						P_table pt=new P_table();//是否注销成功，是否修改成功
						
						//测试
						System.out.println(pid);
						System.out.println(select);					
						//需要重新编码，避免从前端获取的数据变成乱码
						byte p[]=pid.getBytes("iso-8859-1");
						pid=new String(p);	
						
						//注销账号，退出登录
						if(select.equals("0")) {							
							tip=s.DelP(pid);
							
							if(tip.equals("success")) 
							{
								dispatcher=request.getServletContext().getRequestDispatcher("/index.jsp");
								session.invalidate();
								
							}		
							else {
								//指定跳转界面
								dispatcher=request.getServletContext().getRequestDispatcher("/patient.jsp");
							}
							request.setAttribute("logout", tip);							
							
						}
						
						//修改个人信息
						if(select.equals("1")) {
							String gender=request.getParameter("gender");
							String age=request.getParameter("age");
							String nation=request.getParameter("nation");
							//测试
							System.out.println(gender);
							System.out.println(age);							
							System.out.println(nation);							
							//需要重新编码，避免从前端获取的数据变成乱码
							byte g[]=gender.getBytes("iso-8859-1");
							gender=new String(g);	
							byte a[]=age.getBytes("iso-8859-1");
							age=new String(a);	
							byte n[]=nation.getBytes("iso-8859-1");
							nation=new String(n);	
							//测试
							System.out.println(gender);
							System.out.println(age);							
							System.out.println(nation);
							//处理
							pt=s.UpdateP(pid, gender, age, nation);
							System.out.println(pt.getState());
							//修改成功
							if(pt.getState().equals("success"))
							{
								//赋值				
								session.setAttribute("p_gender", pt.getP_gender());
								session.setAttribute("p_age", pt.getP_age());
								session.setAttribute("p_nation", pt.getP_nation());								
							}
							request.setAttribute("tip",pt.getState());							
							dispatcher=request.getServletContext().getRequestDispatcher("/patient.jsp");
							
						}	
						
						//修改密码
						if(select.equals("2")) {
							String oldpsw=request.getParameter("oldpsw");
							String newpsw=request.getParameter("newpsw");
							//测试
							System.out.println(oldpsw);
							System.out.println(newpsw);	
							//需要重新编码，避免从前端获取的数据变成乱码
							byte o[]=oldpsw.getBytes("iso-8859-1");
							oldpsw=new String(o);	
							byte n[]=newpsw.getBytes("iso-8859-1");
							newpsw=new String(n);					
							//测试
							System.out.println(oldpsw);
							System.out.println(newpsw);	
							
							//处理
							tip=s.UpdatePsw(pid, oldpsw, newpsw);
							request.setAttribute("tip", tip);
							System.out.println(tip);
							//指定跳转页面
							if(tip.equals("success")) {
								dispatcher=request.getServletContext().getRequestDispatcher("/index.jsp");
							}
							else {
								dispatcher=request.getServletContext().getRequestDispatcher("/patient.jsp");
							}
						}		
						
						//跳转
						dispatcher.forward(request, response);
			}		
			
		//doGet方法
		public void doGet(HttpServletRequest request,HttpServletResponse response)
				throws ServletException,IOException
			{
				doPost(request,response);
			}	
			
			//end


}
