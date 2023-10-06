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
import vo.Medicine_table;

/**
 * Servlet implementation class drugAddControl
 */
@WebServlet("/drugAddControl")
public class drugAddControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
   private Svc svc=new Svc();
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public drugAddControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");//设置字符集
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//获取页面提交数据
		RequestDispatcher dispatcher=null;
		HttpSession session=request.getSession();
		
		String id = request.getParameter("g_id");
		String name = request.getParameter("g_name");
		String type = request.getParameter("g_type");
		String price = request.getParameter("g_price");
		String number = request.getParameter("g_number");
		String brief = request.getParameter("g_brief");
		String date = request.getParameter("g_date");
		List<Medicine_table> mds=new ArrayList<>();
		String tip="";
		
		//调用service
		tip=svc.AddMedicine(id, name, type, price, number, brief, date);
		System.out.println(tip);
		mds=svc.Search_Medicine();
		//指定跳转界
		System.out.println(mds.size());
		session.setAttribute("drug_msg", mds);
		dispatcher=request.getServletContext().getRequestDispatcher("/medicine.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
