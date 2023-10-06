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
import vo.P_table;
import vo.Pharmacy_table;
import vo.Schedule_table;

@WebServlet("/P_SearchD")
public class P_SearchD extends HttpServlet{
	//变量声明
	private static final long serialVersionUID=1L;
	private Svc s=new Svc();
			
	//doPost方法
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
						//声明变量
						RequestDispatcher dispatcher=null;
						HttpSession session=request.getSession();
						String str_name=request.getParameter("str_name");						
						List<Schedule_table> sts=new ArrayList<>();
						//测试
						System.out.println(str_name);					
						//需要重新编码，避免从前端获取的数据变成乱码
//						byte n[]=str_name.getBytes("iso-8859-1");
//						str_name=new String(n);
						//处理
						SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd");
						System.out.println(date.format(new Date()));
						sts=s.S_showM(date.format(new Date()),str_name);
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
