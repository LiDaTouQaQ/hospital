<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/patient.css">
    <link rel="stylesheet" href="./css/base.css">
    <title>用户界面</title>
</head>
<body>
    <div class="counter">
        <div class="work_line">
            <ul>
                <li>
                    <div class="title now_display">挂号</div>
                    <div class="message_box now_display">  
                        <div>
                            <form action="P_SearchD" method="post">
                                <span>查询医生:</span><input type="text" value="" placeholder="输入医生姓名" name="str_name">
                                <button class="docutor_sel">查询</button>
                            </form>
                            <form action="P_SearchSur" method="post">
                                <span>选择科室:</span>
                                <select name="str_surgery" id="">
                                    <optgroup label="选择科室">
                                        <option>耳鼻喉</option>
                                        <option>精神科</option>
                                        <option>妇产科</option>
                                        <option>皮肤科</option>
                                        <option>儿童科</option>
                                    </optgroup>
                                </select>                                
                                <button class="docutor_sel">查询</button>
                            </form>
                            <table class="tb_old">
                                   <tr>
                                       <th>科室</th><th>医生</th><th>值班时间</th><th>医生介绍</th>                                      
                                   </tr>                          
                            <c:forEach items="${s_msg}" var="smsg">                                 
                                        <tr><td>${smsg.s_surgery}</td><td>${smsg.s_name}</td><td>${smsg.s_time}</td><td>${smsg.s_introduction}</td>
                                        <td>                                        
                                       <form action="Reserve" method="post">
            						   <input type="text" name="r_dname" value="${smsg.s_name}" style="display: none;">
            						   <input type="text" name="r_pname" value="<%=session.getAttribute("p_name") %> " style="display: none;"> 
            						   <input type="text" name="r_surgery" value="${smsg.s_surgery}" style="display: none;">           			
            						   <input type="text" name="p_id" value="<%=session.getAttribute("p_id") %>" style="display: none;">
            						   <button>挂号</button>
            						   </form>                                     
                                        </td>
                                        </tr>                                        
                            </c:forEach>
                            <% if(request.getAttribute("gh_tip")=="挂号成功"){
                        		out.print("<script>alert('挂号成功')</script>");
                            }
                            if(request.getAttribute("gh_tip")=="repeat"){
                            	out.print("<script>alert('存在挂号信息不能重复挂号')</script>");
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
                    <div class="title">病例信息</div>
                    <div class="message_box">  
                        <div>
                        
                            <form action="P_SearchCase" method="post">
                                <span>查询病例信息:</span><input type="text" value="" placeholder="输入医生姓名查找" name="case_str">
                                <input type="text" value="0" name="t" style="display:none">
                                <input type="text" value="<%=session.getAttribute("p_id") %>" name="pid" style="display:none">
                                <button class="docutor_sel">查询</button>                                
                            </form>
                            <form action="P_SearchCase" method="post">
                                <span>选择日期:</span><input type="date" value="" name="case_str">
                                <input type="text" value="1" name="t" style="display:none"> 
                                <input type="text" value="<%=session.getAttribute("p_id") %>" name="pid" style="display:none">                                                     
                                <button class="docutor_sel">查询</button>
                            </form>
                            <table class="tb_old">
                                   <tr>
                                       <th>日期</th><th>类型</th><th>主治医生</th><th>详细信息</th><th>挂号状态</th>                                   
                                   </tr>                          
                            <c:forEach items="${pcase_msg}" var="pcasemsg">                             
                                        <tr>
                                        <td>${pcasemsg.case_time}</td><td>${pcasemsg.type}</td>
                                        <td>${pcasemsg.case_dname}</td><td>${pcasemsg.case_introduction}</td>
                                        <td>${pcasemsg.case_state}</td>
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
                    <div class="title">药物信息</div>
                    <div class="message_box">                       
                        <div class="m_work">
                        
                           <form action="SearchDrug" method="post">
                                <span>输入筛选条件</span><input type="text" value="" placeholder="药物名/类型/功效" name="drug_str">
                                <button class="docutor_sel">查询</button>
                            </form>
                            <table class="tb_old">
                                   <tr>
                                       <th>名称</th><th>类型</th><th>价格</th><th>功效</th>                                      
                                   </tr>                          
                            <c:forEach items="${drug_msg}" var="drugmsg">                                 
                                        <tr><td>${drugmsg.g_name}</td><td>${drugmsg.g_type}</td>
                                        <td>${drugmsg.g_price}</td><td>${drugmsg.g_brief}</td> 
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
                    <div class="title">疾病信息</div>
                    <div class="message_box">             
                        <div class="m_work">
                        
                            <form action="#" method="post">
                                <span>输入筛选条件</span><input type="text" value="" placeholder="疾病名称/类型/描述" name="disease_str">
                                <button class="docutor_sel">查询</button>
                            </form>
                            <table class="tb_old">
                                   <tr>
                                       <th>名称</th><th>类型</th><th>详细介绍</th>                                      
                                   </tr>                          
                            <c:forEach items="${disease_msg}" var="diseasemsg">                                 
                                        <tr><td>${diseasemsg.disease_name}</td><td>${diseasemsg.disease_type}</td>
                                        <td>${diseasemsg.disease_introduction}</td>
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
                    <div class="title">个人信息</div>
                    <div class="message_box">                      
                        <div><span>姓名:</span><span><%=session.getAttribute("p_name")%></span>
                        </div>
                        <div><span>手机号:</span><span><%=session.getAttribute("p_tel")%></span>
                        </div>
                         <form action="Exit"  method="post">
                         <button>退出登录</button>
                         </form>
                         <form action="Change"  method="post">
                         <input type="text" value="<%=session.getAttribute("p_id")%>" name="p_id" style="display:none">
                          <input type="text" value="0" name="select" style="display:none">
                         <button>注销账号</button>
                         </form>                  
                        <form action="Change" class="p_mess" method="post">
                         	<input type="text" value="<%=session.getAttribute("p_id")%>" name="p_id" style="display:none">
                         	<input type="text" value="1" name="select" style="display:none">
                            <div><span>性别:</span><span id="sex_msg" style="display: none;"><%=session.getAttribute("p_gender")%></span>
                            <input type="radio" name="gender" value="男">男
                            <input type="radio" name="gender" value="女">女                            
                            </div>
                            <script>
                                const sex = document.querySelectorAll("input[type='radio']")
                               const sex_msg = document.querySelector("#sex_msg")
                                if(sex_msg.innerHTML=="男"){
                                    sex[0].checked = true
                                }else{
                                    sex[1].checked = true
                                }

                            </script>
                            
                            <div>
                                <span>年龄:</span>
                                <input type="number" name="age" max="100" min="1" placeholder="<%=session.getAttribute("p_age")%>">
                            </div>
                            <div>
                                <span>民族:</span>
                                <input type="text" name="nation" placeholder="<%=session.getAttribute("p_nation")%>">
                            </div>
                            <button>修改个人信息</button>
                        </form>
                        <form action="Change" class="p_mess" method="post"> 
                        	<input type="text" value="<%=session.getAttribute("p_id")%>" name="p_id" style="display:none">  
                        	<input type="text" value="2" name="select" style="display:none">                         
                            <div>
                                <span>旧密码:</span>
                                <input type="password" name="oldpsw" placeholder="填写旧密码" id="oldpsw">
                            </div>
                            <div>
                                <span>新密码:</span>
                                <input type="password" name="newpsw" placeholder="填写新密码" id="newpsw">
                            </div>
                            <button>修改密码</button>
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