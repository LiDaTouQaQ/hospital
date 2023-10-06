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
import vo.Pharmacy_table;
import vo.Schedule_table;
import vo.Case_table;
import vo.Disease_table;
import vo.Drug;
import vo.Pcase;
import vo.Medicine_table;


@WebServlet("/Login")
public class Login extends HttpServlet{
	//变量声明
	private static final long serialVersionUID=1L;
	private Svc s=new Svc();
		
	//doPost方法
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
					//声明变量
					RequestDispatcher dispatcher=null;
					HttpSession session=request.getSession();
					String tel=request.getParameter("tel");
					String psw=request.getParameter("psw");
					String iden=request.getParameter("identity");
					P_table pt=new P_table();
					D_table dt=new D_table();
					Charge_table ct=new Charge_table();
					Pharmacy_table pht=new Pharmacy_table();
					String tip="";
					List<Schedule_table> sts = new ArrayList<>();
					List<Case_table> cats = new ArrayList<>();
					List<Disease_table> diseases=new ArrayList<>();
					List<Pcase> pcases=new ArrayList<>();
					List<Drug> drugs=new ArrayList<>();
					List<Medicine_table> medicines=new ArrayList<>();
					List<Case_table> cases=new ArrayList<>();
					
					//测试
					System.out.println(tel);
					System.out.println(psw);
					System.out.println(iden);
					//需要重新编码，避免从前端获取的数据变成乱码
					byte t[]=tel.getBytes("iso-8859-1");
					tel=new String(t);
					byte p[]=psw.getBytes("iso-8859-1");
					psw=new String(p);
					byte i[]=iden.getBytes("iso-8859-1");
					iden=new String(i);		
					//登录判断
					if(iden.equals("0")) {
						pt=s.P_login(tel, psw);
						if(pt.getState().equals("success")) {	
							//返回用户名称、用户手机号账号、用户身份证号id
							session.setAttribute("p_name", pt.getP_name());
							session.setAttribute("p_tel", pt.getP_tel());
							session.setAttribute("p_id", pt.getP_id());						
							session.setAttribute("p_gender", pt.getP_gender());
							session.setAttribute("p_age", pt.getP_age());
							session.setAttribute("p_nation", pt.getP_nation());
							//如果病人用户登录成功，获取当天日期
							SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd");											
							//返回当天所有医生值班信息
							sts=s.S_show(date.format(new Date()));	
							session.setAttribute("s_msg", sts);
							//返回所有个人病例信息
							pcases=s.Case_showSelf(pt.getP_id());
							session.setAttribute("pcase_msg", pcases);
							//返回所有药物信息
							drugs=s.Drug_showAll();
							session.setAttribute("drug_msg", drugs);
							//返回所有疾病信息
							diseases=s.Disease_showAll();
							session.setAttribute("disease_msg", diseases);
							dispatcher=request.getServletContext().getRequestDispatcher("/patient.jsp");
							System.out.println(pt.getP_name());
						}else
						{
							System.out.println(pt.getState());
							dispatcher=request.getServletContext().getRequestDispatcher("/index.jsp");
						}
					}
					if(iden.equals("1")) {
						dt=s.D_login(tel, psw);
						if(dt.getState().equals("success")) {
							//调用医生排班
							tip = s.D_Schedul();
							System.out.println(tip);
							SimpleDateFormat date1=new SimpleDateFormat("yyyy-MM-dd");
							
							cats=s.Search_casetableAll(dt.getD_name());
							session.setAttribute("d_c_msg", cats);
							dispatcher=request.getServletContext().getRequestDispatcher("/doctor.jsp");
							sts=s.S_show_name(dt.getD_name(), date1.format(new Date()));
							session.setAttribute("c_msg",sts);
							session.setAttribute("d_tel", dt.getD_tel());
							session.setAttribute("d_name", dt.getD_name());
							session.setAttribute("d_sur", dt.getD_surgery());
							session.setAttribute("d_intor", dt.getD_introduction());
						}else
						{
							System.out.println(dt.getState());
							dispatcher=request.getServletContext().getRequestDispatcher("/index.jsp");
						}
					}				
					if(iden.equals("2")) {
						ct=s.C_login(tel, psw);
						if(ct.getState().equals("success")) {
							System.out.println("缴费数量");
							cats=s.Search_case_tableAllFor_toll();
							System.out.println(cats.size());
							session.setAttribute("ch_msg", cats);
							dispatcher=request.getServletContext().getRequestDispatcher("/toll.jsp");
							session.setAttribute("msg", ct);
							session.setAttribute("c_name", ct.getC_name());
							session.setAttribute("c_tel", ct.getC_tel());
							session.setAttribute("c_id", ct.getC_id());
						}else
						{
							System.out.println(ct.getState());
							dispatcher=request.getServletContext().getRequestDispatcher("/index.jsp");
						}
					}
					if(iden.equals("3")) {
						pht=s.Ph_login(tel, psw);
						if(pht.getState().equals("success")) {
						//返回个人信息
						session.setAttribute("ph_name", pht.getPh_name());
						session.setAttribute("ph_tel", pht.getPh_tel());
						//返回病例信息
						cases=s.Search_CaseByState();
						session.setAttribute("pcase_msg", cases);
						//返回所有药物信息
						medicines=s.Search_Medicine();
						System.out.println(medicines.size());
						session.setAttribute("drug_msg", medicines);
						//指定跳转页面
						dispatcher=request.getServletContext().getRequestDispatcher("/medicine.jsp");
						}else
						{
							System.out.println(pht.getState());
							dispatcher=request.getServletContext().getRequestDispatcher("/index.jsp");
						}
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
