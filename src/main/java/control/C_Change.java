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

@WebServlet("/C_Change")
public class C_Change extends HttpServlet {
	//变量声明
	private static final long serialVersionUID=1L;
	private Svc a=new Svc();
	
	//doPost方法
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
						//声明变量
						RequestDispatcher dispatcher=null;
						HttpSession session=request.getSession();
						String c_tel = request.getParameter("c_tel");
						String c_name = request.getParameter("c_name");
						String c_id = (String) session.getAttribute("c_id");
						String tip="";
						byte n[]=c_tel.getBytes("iso-8859-1");
						c_tel=new String(n);						
						byte s[]=c_name.getBytes("iso-8859-1");
						c_name=new String(s);
						
						tip = a.UpdateCharge_table_Byc_id(c_tel, c_name, c_id);
						if(tip.equals("success")) {
							dispatcher=request.getServletContext().getRequestDispatcher("/toll.jsp");
							request.setAttribute("c_change","更新成功" );
							session.setAttribute("c_tel", c_tel);
							session.setAttribute("c_name", c_name);
							
						}else {
							request.setAttribute("c_change","更新失败" );
							dispatcher=request.getServletContext().getRequestDispatcher("/toll.jsp");
						}
						dispatcher.forward(request, response);
	}
}