package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.Svc;
import vo.Pcase;

@WebServlet("/P_SearchCase")
public class P_SearchCase extends HttpServlet{
	//变量声明
		private static final long serialVersionUID=1L;
		private Svc s=new Svc();
		private Svc a=new Svc();
				
		//doPost方法
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
						throws ServletException, IOException {
							//声明变量
							RequestDispatcher dispatcher=null;
							HttpSession session=request.getSession();
							String case_str=request.getParameter("case_str");	
							String t=request.getParameter("t");
							String pid=request.getParameter("pid");	
							List<Pcase> pcases=new ArrayList<>();
							//测试
							System.out.println(case_str+"case_str");	
							System.out.println(t+"t");	
							//需要重新编码，避免从前端获取的数据变成乱码
							if(t.equals("0")) {
								byte s[]=case_str.getBytes("iso-8859-1");
								case_str=new String(s);
								System.out.println(case_str+"case_str0");	
								pcases=a.Case_showSelfByStr(case_str,pid);
								System.out.println(pcases.size()+"size");	
							}
							
							//处理，如果传过来的是日期，因为格式为2023/06/14与数据库中的2023-06-14不符导致查询出错	
							if(t.equals("1")&&!case_str.isEmpty()) {
								//拆分重组日期
								String str=case_str.substring(0, 4);
								str=str+"-"+case_str.substring(5,7);
								str=str+"-"+case_str.substring(8);
								System.out.println(str+"str");	
								System.out.println(pid+"pid");	
								pcases=s.Case_showSelfByStr(str,pid);
								System.out.println(pcases.size()+"size");	
								}
							else
								{pcases=s.Case_showSelfByStr(case_str,pid);}
							//跳转
							dispatcher=request.getServletContext().getRequestDispatcher("/patient.jsp");					
							session.setAttribute("pcase_msg", pcases);							
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
