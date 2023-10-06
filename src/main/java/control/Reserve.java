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

import service.Svc;
import vo.Charge_table;
import vo.D_table;
import vo.P_table;
import vo.Pcase;
import vo.Pharmacy_table;
import vo.Schedule_table;

@WebServlet("/Reserve")
public class Reserve extends HttpServlet{
    //变量声明
	private static final long serialVersionUID = 1L;
	private Svc s1=new Svc();
	List<Pcase> pcases=new ArrayList<>();
	
	//doPost方法
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
					//声明变量
					RequestDispatcher dispatcher=null;
					HttpSession session=request.getSession();
					String dname=request.getParameter("r_dname");
					String pname=request.getParameter("r_pname");
					String p_id = request.getParameter("p_id");
					String surgery=request.getParameter("r_surgery");
					int r=0;
					String tip="";
					
					P_table pt=new P_table();
					D_table dt=new D_table();
					Charge_table ct=new Charge_table();
					Pharmacy_table pht=new Pharmacy_table();					
					List<Schedule_table> sts=new ArrayList<>();
					//测试
//					System.out.println(tel);
//					System.out.println(psw);
//					System.out.println(iden);
					//需要重新编码，避免从前端获取的数据变成乱码
					byte d[]=dname.getBytes("iso-8859-1");
					dname=new String(d);
					byte p[]=pname.getBytes("iso-8859-1");
					pname=new String(p);
					byte s[]=surgery.getBytes("iso-8859-1");
					surgery=new String(s);	
					byte p1[]=p_id.getBytes("iso-8859-1");
					p_id=new String(p1);
					//判断是否已经挂过号
					r=s1.Check(p_id);						
					//如果病人用户没挂过号
					if(r==0) {
						SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
						tip=s1.Register(date.format(new Date()), dname, p_id, surgery);					
						System.out.println(tip);
						tip = "挂号成功";
						request.setAttribute("gh_tip", tip);//提示挂号成功或失败
					}else//如果病人用户挂过号
						{
							request.setAttribute("gh_tip", "repeat");//提示不能重复挂号
							System.out.println(r);
						}	
					pcases=s1.Case_showSelf(p_id);
					session.setAttribute("pcase_msg", pcases);
					dispatcher=request.getServletContext().getRequestDispatcher("/patient.jsp");						
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
