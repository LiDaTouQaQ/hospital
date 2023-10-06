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

@WebServlet("/Register")
public class Register extends HttpServlet {
	//变量声明
	private static final long serialVersionUID=1L;
	private Svc s=new Svc();
			
	//doPost方法
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
						//声明变量
						RequestDispatcher dispatcher=null;
						HttpSession session=request.getSession();
						String name=request.getParameter("name");
						String tel=request.getParameter("tel");
						String id=request.getParameter("id");
						String psw=request.getParameter("psw");	
						String iden=request.getParameter("identity");
						String tip="";				
						//需要重新编码，避免从前端获取的数据变成乱码
						byte n[]=name.getBytes("iso-8859-1");
						name=new String(n);						
						byte t[]=tel.getBytes("iso-8859-1");
						tel=new String(t);
						byte i[]=id.getBytes("iso-8859-1");
						id=new String(i);	
						byte p[]=psw.getBytes("iso-8859-1");
						psw=new String(p);	
						byte idn[]=iden.getBytes("iso-8859-1");
						iden=new String(idn);		
						//注册判断
						if(iden.equals("0")) {tip=s.P_register(id,name,tel, psw);}
						if(iden.equals("1")) {tip=s.D_register(id,name,tel, psw);}				
						if(iden.equals("2")) {tip=s.C_register(id,name,tel, psw);}
						if(iden.equals("3")) {tip=s.Ph_register(id,name,tel, psw);}		
						if(tip.equals("success")) {
							dispatcher=request.getServletContext().getRequestDispatcher("/index.jsp");
							System.out.println(tip);
							session.setAttribute("msg_r","注册成功" );
						}else
							{
							System.out.println(tip);
							session.setAttribute("msg_r","注册失败" );
							dispatcher=request.getServletContext().getRequestDispatcher("/register.jsp");
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
