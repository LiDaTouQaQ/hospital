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
import vo.Case_table;

@WebServlet("/C_Change_P")
public class C_Change_P extends HttpServlet {
	//变量声明
	private static final long serialVersionUID=1L;
	private Svc s=new Svc();
	private Case_table cat = new Case_table();
	private String tip="";
	private List<Case_table> cats = new ArrayList<>();
	
	//doPost方法
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
						//声明变量
						RequestDispatcher dispatcher=null;
						HttpSession session=request.getSession();
						String case_id = request.getParameter("case_id");
						String tip = s.Update_case_table_case_state(case_id);
						if(tip=="success") {
							cats=s.Search_case_tableAllFor_toll();
							session.setAttribute("ch_msg", cats);
							dispatcher=request.getServletContext().getRequestDispatcher("/toll.jsp");
							request.setAttribute("toll_tip","缴费成功" );
						}else {
							dispatcher=request.getServletContext().getRequestDispatcher("/toll.jsp");
							request.setAttribute("toll_tip","缴费失败" );
						}
						dispatcher.forward(request, response);
	}
}