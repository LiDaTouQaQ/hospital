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
import vo.Case_table;


@WebServlet("/D_For_P")
public class D_For_P extends HttpServlet{
	//变量声明
	private static final long serialVersionUID=1L;
	private Svc s=new Svc();
	private Case_table cat = new Case_table();
		
	//doPost方法
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
					//声明变量
					RequestDispatcher dispatcher=null;
					HttpSession session=request.getSession();
					String case_id = request.getParameter("case_id");
					session.setAttribute("case_id", case_id);
					cat = s.Search_casetableByCase_id(case_id);
					String p_name = cat.getP_name();
					System.out.println(cat.getTip());
					if(cat.getTip().equals("success")) {
						dispatcher=request.getServletContext().getRequestDispatcher("/d_for_p.jsp");
						session.setAttribute("p_name",p_name );
					}else {
						request.setAttribute("d_change","跳转失败" );
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