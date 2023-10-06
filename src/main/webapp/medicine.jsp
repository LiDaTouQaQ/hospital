<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/medicine.css">
    <link rel="stylesheet" href="./css/base.css">
    <title>药房人员界面</title>
</head>
<body>
    <div class="counter">
        <div class="work_line">
            <ul>                
                <li>
                    <div class="title now_display">病例信息</div>
                    <div class="message_box now_display">  
                        <div>
                        
                            <form action="SearchCaseByName" method="post">
                                <span>查询病例信息:</span><input type="text" value="" placeholder="输入病人姓名查找" name="case_pname">                               
                                <button class="docutor_sel">查询</button>                                
                            </form>
                            
                            <table class="tb_old">
                                   <tr>
                                    <th>病例ID</th> 
                                    <th>姓名</th>                                                                       
                                    <th>就诊时间</th>
                                    <th>药品名称</th>
                                    <th>费用</th>
                                    <th>缴费状态</th>
                                    <th>交易状态</th>                                   
                                </tr>
                                                          
                            <c:forEach items="${pcase_msg}" var="pcasemsg">                                 
                                        <tr><td>${pcasemsg.case_id}</td><td>${pcasemsg.p_name}</td><td>${pcasemsg.case_time}</td>
                                        <td>${pcasemsg.opinion}</td><td>${pcasemsg.cost}</td> 
                                        <td>${pcasemsg.case_type}</td><td>${pcasemsg.medicine_state}</td>
                                         <td>                                        
                                       <form action="Finish" method="post">            						    
            						   <input type="text" name="case_id" value="${pcasemsg.case_id}" style="display: none;">           			
            						   <button>完成</button>
            						   </form>                                     
                                        </td>
                                        </tr>                                                                           
                            </c:forEach>
                             <% if(request.getAttribute("fin_tip")=="success" && request.getAttribute("tip_num")=="success"){
                        		out.print("<script>alert('取药成功')</script>");
                            }
                            if(request.getAttribute("fin_tip")=="修改失败" || request.getAttribute("tip_num")=="数量存在问题"){
                            	out.print("<script>alert('取药存在问题')</script>");
                            } 
                            %>
                            </table>
                            <script>
                                const d_btn = document.querySelector(".docutor_sel")
                                d_btn.addEventListener("click",function(){
                                    old = document.querySelector(".tb_old")
                                    num = old.children[0].children.length
                                    if(old!=undefined){
                                        for(let i=1;i<num;i++){
                                            old.children[0].children[i].style.display = "none"
                                        }
                                        console.log(old.children[0])
                                    }
                                })
                            </script>
                            
                        </div>                     
                    </div>
                </li>
               
                <li>
                    <div class="title">药物信息</div>
                    <div class="message_box">                       
                        <div class="m_work">
                        
                  
                                <span>输入筛选条件</span><input type="text" value="" placeholder="药物名/类型/功效" name="drug_str">
                                <button class="docutor_sel">查询</button>

                            
                            <table class="tb_old">                                     
                                    <tr>
                                    <th>编号</th>
                                    <th>名称</th>
                                    <th>类型</th>
                                    <th>价格</th>
                                    <th>数量</th>
                                    <th>简介</th>
                                    <th>日期</th>
                                </tr>                        
                            <c:forEach items="${drug_msg}" var="drugmsg">                                 
                                        <tr><td>${drugmsg.g_id}</td><td>${drugmsg.g_name}</td>
                                        <td>${drugmsg.g_type}</td><td>${drugmsg.g_price}</td> 
                                        <td>${drugmsg.g_number}</td> <td>${drugmsg.g_brief}</td> <td>${drugmsg.g_date}</td> 
                                        </tr>                                                                           
                            </c:forEach>
                            </table>
                            <script>
                                const d_btn = document.querySelector(".docutor_sel")
                                d_btn.addEventListener("click",function(){
                                    old = document.querySelector(".tb_old")
                                    num = old.children[0].children.length
                                    if(old!=undefined){
                                        for(let i=1;i<num;i++){
                                            old.children[0].children[i].style.display = "none"
                                        }
                                        console.log(old.children[0])
                                    }
                                })
                            </script>
                            
                        </div>
                    </div>
                </li>
                
                <li>
                    <div class="title">药品入库</div>
                    <div class="message_box">
                        <div class="m_title">
                            录入信息
                        </div>
                        <div>
                            <form action="drugAddControl" method="post">
                                <table id="drug_table">
                                    <tr>
                                        <td>药品ID</td>
                                        <td><input type="text" name="g_id" id=""></td>
                                        
                                    </tr>
                                    <tr>
                                        <td>药品名称</td>
                                        <td><input type="text" name="g_name" id=""></td>
                                        
                                    </tr>
                                    <tr>
                                        <td>药品类型</td>
                                        <td>
                                            <input type="radio" name="g_type" value="处方药">处方药
                                            <input type="radio" name="g_type" value="非处方药">非处方药
                                        </td>
                                        
                                    </tr>
                                    <tr>
                                        <td>药品价格</td>
                                        <td><input type="text" name="g_price" id=""></td>
                                        
                                    </tr>
                                    <tr>
                                        <td>药品数量</td>
                                        <td><input type="text" name="g_number" id=""></td>
                                        
                                    </tr>
                                    <tr>
                                        <td>药品简介</td>
                                        <td><input type="text" name="g_brief" id=""></td>
                                        
                                    </tr>
                                    <tr>
                                        <td>到期时间</td>
                                        <td><input type="date" name="g_date" id=""></td>
                                        
                                    </tr>
                                    <tr id="addbut">
                                        <td colspan="2">
                                            <input type="submit" value="添加">
                                            <input type="reset" value="重置">
                                        </td>
                                       
                                    </tr>
                                   
                                </table>
                            </form>
                        </div>
                    </div>
                </li>
               	
              
                <li>
                    <div class="title">个人信息</div>
                    <div class="message_box">                        
                        <div><span>姓名:</span><span><%=session.getAttribute("ph_name")%></span>
                        </div>
                        <div><span>手机号:</span><span><%=session.getAttribute("ph_tel")%></span>
                        </div>
                         <form action="Exit"  method="post">
                         <button>退出登录</button>
                         </form>
                        
                    </div>
                </li>
               	
            </ul>
        </div>
        <script>
            const ul = document.querySelector('ul')
            ul.addEventListener('click',function(e){
                    if(e.target.tagName === "DIV" && (e.target.className=="title" || e.target.className=="title now_display")){
                    const old = document.querySelector('.title.now_display')
                    const old2 = document.querySelector('.message_box.now_display')
                    if(old && old2) {
                        old.classList.remove('now_display')
                        old2.classList.remove('now_display')
                    }
                    e.target.classList.add('now_display')
                    e.target.parentNode.querySelector('.message_box').classList.add('now_display')
                    
                    }
            })
        </script>
    </div>
</body>
</html>