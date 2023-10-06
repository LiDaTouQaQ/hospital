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
import vo.Charge_table;
import vo.D_table;
import vo.P_table;
import vo.Pharmacy_table;
import vo.Case_table;


@WebServlet("/Insert_Case_table")
public class Insert_Case_table extends HttpServlet{
	//变量声明
	private static final long serialVersionUID=1L;
	private Svc s=new Svc();
	private Case_table cat = new Case_table();
	private String tip="";
	private List<Case_table> cats = new ArrayList<>();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				//声明变量
		tip="";
		RequestDispatcher dispatcher=null;
		HttpSession session=request.getSession();
		String d_name = session.getAttribute("d_name").toString();
		String case_id = session.getAttribute("case_id").toString();
		String diagnosis = request.getParameter("diagnosis");
		String past_disease = request.getParameter("past_disease");
		String opinion = request.getParameter("opinion");
		String opinion_number = request.getParameter("opinion_number");
		String operation_area = request.getParameter("operation_area");
		String operation_time = request.getParameter("operation_date");
		String cost = request.getParameter("price_cal");
		String operation = "area: "+operation_area +" time: "+ operation_time;
		byte c1[]=case_id.getBytes("iso-8859-1");
		case_id=new String(c1);						
		byte di[]=diagnosis.getBytes("iso-8859-1");
		diagnosis=new String(di);
		byte op[]=opinion.getBytes("iso-8859-1");
		opinion=new String(op);
		byte pa[]=past_disease.getBytes("iso-8859-1");
		past_disease=new String(pa);	
		byte op_number[]=opinion_number.getBytes("iso-8859-1");
		opinion_number=new String(op_number);
		byte cc[]=cost.getBytes("iso-8859-1");
		cost=new String(cc);
		byte oo[]=operation.getBytes("iso-8859-1");
		operation=new String(oo);
		System.out.println(opinion);		
		tip = s.UpdateCase_tableBycase_id(case_id, diagnosis, past_disease, opinion, opinion_number, operation, cost);
		
		if(tip.equals("success")) {
			cats=s.Search_casetableAll(d_name);
			session.setAttribute("d_c_msg", cats);
			dispatcher=request.getServletContext().getRequestDispatcher("/doctor.jsp");
			request.setAttribute("case_up","更新成功" );
		}else {
			cats=s.Search_casetableAll(d_name);
			session.setAttribute("d_c_msg", cats);
			dispatcher=request.getServletContext().getRequestDispatcher("/doctor.jsp");
			request.setAttribute("case_up","更新失败" );
		}
		
		dispatcher.forward(request, response);
	}
	
}
