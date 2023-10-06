package control;

import java.io.IOException;

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

@WebServlet("/DoctChange")
public class DoctChange extends HttpServlet {
	//变量声明
	private static final long serialVersionUID=1L;
	private Svc a=new Svc();
	
	//doPost方法
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
						//声明变量
						RequestDispatcher dispatcher=null;
						HttpSession session=request.getSession();
						String name=request.getParameter("d_name");
						String sur=request.getParameter("d_sur");
						String intor=request.getParameter("d_intor");
						String d_tel = (String) session.getAttribute("d_tel");
						String tip="";	
						System.out.println(name);						
						System.out.println(sur);
						System.out.println(intor);
						System.out.println(d_tel);
						byte n[]=name.getBytes("iso-8859-1");
						name=new String(n);						
						byte s[]=sur.getBytes("iso-8859-1");
						sur=new String(s);
						byte i[]=intor.getBytes("iso-8859-1");
						intor=new String(i);
						//
						tip = a.Doct_Change(name,sur,intor,d_tel);
						System.out.println(tip);
						if(tip.equals("success")) {
							dispatcher=request.getServletContext().getRequestDispatcher("/doctor.jsp");
							session.setAttribute("d_change","更新成功" );
							session.setAttribute("d_intor", intor);
							session.setAttribute("d_sur", sur);
							session.setAttribute("d_name", name);
							
						}else {
							request.setAttribute("d_change","更新失败" );
							dispatcher=request.getServletContext().getRequestDispatcher("/doctor.jsp");
						}
						dispatcher.forward(request, response);
	}
	//doGet方法
	public void doGet(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException
	{
		doPost(request,response);
	}
}