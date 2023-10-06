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
import vo.Drug;

@WebServlet("/SearchDrug")
public class SearchDrug extends HttpServlet{
	//变量声明
	private static final long serialVersionUID=1L;
	private Svc s=new Svc();
				
	//doPost方法
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
						throws ServletException, IOException {
							//声明变量
							RequestDispatcher dispatcher=null;
							HttpSession session=request.getSession();
							String drug_str=request.getParameter("drug_str");						
							List<Drug> drugs=new ArrayList<>();
							//测试
							System.out.println(drug_str+"drugstr");					
							//需要重新编码，避免从前端获取的数据变成乱码
							byte d[]=drug_str.getBytes("iso-8859-1");
							drug_str=new String(d);
							System.out.println(drug_str+"drugstr");		
							//处理
							drugs=s.Drug_show(drug_str);
							//跳转
							dispatcher=request.getServletContext().getRequestDispatcher("/patient.jsp");					
							session.setAttribute("drug_msg", drugs);	
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
