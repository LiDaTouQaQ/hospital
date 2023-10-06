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

@WebServlet("/Finish")
public class Finish extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Svc svc=new Svc();
       
    public Finish() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");//设置字符集
		RequestDispatcher dispatcher=null;
		HttpSession session=request.getSession();
		List<Case_table> cases=new ArrayList<>();
		String caseid=request.getParameter("case_id");
		String tip = "";
		//取药
		String tip_num = svc.Update_g_table_num(caseid);
		//更新状态
		if(tip_num=="success") {
			request.setAttribute("tip_num", tip);
		}else {
			request.setAttribute("tip_num", "数量存在问题");	
		}
		tip = svc.Update_case_table_medicine_state(caseid);
		cases=svc.Search_CaseByState();
		dispatcher=request.getServletContext().getRequestDispatcher("/medicine.jsp");	
		if(tip=="success") {
			request.setAttribute("fin_tip", tip);
			session.setAttribute("pcase_msg", cases);	
		}else {
			request.setAttribute("fin_tip", "修改失败");
			session.setAttribute("pcase_msg", cases);	
		}
		
		dispatcher.forward(request, response);
		
	}

}
