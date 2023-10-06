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

@WebServlet("/Exit")
public class Exit extends HttpServlet{
	//变量声明
	private static final long serialVersionUID = 1L;
		
	//doPost方法
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
						//声明变量
						RequestDispatcher dispatcher=null;
						HttpSession session=request.getSession();
						session.invalidate();						
						dispatcher=request.getServletContext().getRequestDispatcher("/index.jsp");						
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
