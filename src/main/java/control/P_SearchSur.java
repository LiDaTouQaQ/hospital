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
import vo.Schedule_table;

@WebServlet("/P_SearchSur")
public class P_SearchSur extends HttpServlet{
	//声明变量
	private static final long serialVersionUID = 1L;
	private Svc s=new Svc();
	
	//doPost方法
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
						//声明变量
						RequestDispatcher dispatcher=null;
						HttpSession session=request.getSession();
						String str_surgery=request.getParameter("str_surgery");						
						List<Schedule_table> sts=new ArrayList<>();
						//测试
						System.out.println(str_surgery);					
						//需要重新编码，避免从前端获取的数据变成乱码
//						byte sur[]=str_surgery.getBytes("iso-8859-1");
//						str_surgery=new String(sur);
						//处理
						SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd");						
						sts=s.S_showSur(date.format(new Date()),str_surgery);
						System.out.println(date.format(new Date()));
						System.out.println(str_surgery);
						System.out.println(sts.size());
						//System.out.println(sts.get(2).getS_surgery()+sts.get(2).getS_name());
						//跳转
						dispatcher=request.getServletContext().getRequestDispatcher("/patient.jsp");					
						session.setAttribute("s_msg", sts);	
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
