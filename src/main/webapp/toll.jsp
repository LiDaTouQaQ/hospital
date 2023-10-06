<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/base.css">
    <link rel="stylesheet" href="./css/toll.css">
    <title>收费界面</title>
</head>
<body>
    <div class="counter">
        <div class="work_line">
            <ul>
                <li>
                    <div class="title now_display">基本信息</div>
                    <div class="message_box now_display">
                        <div class="m_title">
                            收费人员信息
                        </div>
                        <form action="C_Change" method="post">
                        	<div><span>电话信息</span><input type="text" id="c_tel" name="c_tel" value="<%=session.getAttribute("c_tel")%>">
	                        </div>
	                        <div><span>人员姓名</span><input type="text" id="c_name" name="c_name" value="<%=session.getAttribute("c_name")%>">
	                        </div>
	                        <button>确认修改</button>
	                        <%
                            	if(request.getAttribute("c_change") =="更新失败"){
                            		out.print("<script>alert('更新失败')</script>");
                            	}
	                        if(request.getAttribute("c_change") =="更新成功"){
                        		out.print("<script>alert('更新成功')</script>");
                        	}
                            %>
                        </form>
                        <form action="Exit" method="post">
                        	<button>退出登录</button>
                        </form>
                        
                    </div>
                </li>
                <li>
                    <div class="title">病人缴费</div>
                    <div class="message_box">
                        <div class="m_title">
                            病人账单
                            <div>
                                <input type="radio" name="charge" value="charged">已缴费
                                <input type="radio" name="charge" value="charging">未缴费
                                <input type="text" id="p_name" value="" placeholder="输入病人姓名查询">
                                <button id="p_name_btn">查询</button>
                            </div>
                            <table>
                                <tr>
                                    <td>姓名</td><td>花费</td><td>时间</td><td>开方医生</td><td>缴费状态</td><td>修改</td>
                                </tr>
                            <div class="tb_old">
                                <c:forEach items="${ch_msg}" var="chmsg">
                                    <form action="C_Change_P" method="post">
                                        <tr class="t_list">
                                        	<input type="text" name="case_id" id = "case_id" value="${chmsg.case_id }" style="display: none;">
                                            <td>${chmsg.p_name}</td><td>${chmsg.cost}</td><td>${chmsg.case_time}</td><td>${chmsg.d_name}</td><td>${chmsg.case_type}</td><td><button>收款</button></td>
                                        	
                                        </tr>                                        
                                    </form>
                                </c:forEach>
                                <%
                            	if(request.getAttribute("toll_tip") =="缴费失败"){
                            		out.print("<script>alert('缴费失败')</script>");
                            	}
	                        if(request.getAttribute("toll_tip") =="缴费成功"){
                        		out.print("<script>alert('缴费成功')</script>");
                        	}
                            %>
                            </div>
                            <script>
                                const rad = document.querySelectorAll("input[type='radio']")
                                for(let i=0;i<rad.length;i++){
                                    rad[i].addEventListener("click",function(){
                                        if(this.value=="charged"){
                                            const td_list = document.querySelectorAll(".t_list td:nth-child(5)")
                                            for(let i=0;i<td_list.length;i++){
                                                td_list[i].parentNode.classList.remove("pre")
                                                if(td_list[i].innerHTML=="未缴费"){
                                                    td_list[i].parentNode.classList.add("pre")
                                                }
                                            }
                                        }
                                        if(this.value=="charging"){
                                            const td_list = document.querySelectorAll(".t_list td:nth-child(5)")
                                            for(let i=0;i<td_list.length;i++){
                                                td_list[i].parentNode.classList.remove("pre")
                                                if(td_list[i].innerHTML=="已缴费"){
                                                    td_list[i].parentNode.classList.add("pre")
                                                }
                                            }
                                        }
                                    })
                                }
                                const in_p_name = document.querySelector("#p_name")
                                const p_name_btn = document.querySelector("#p_name_btn")
                                const td_list_name = document.querySelectorAll(".t_list td:nth-child(1)")
                                p_name_btn.addEventListener("click",function(e){
                                    e.preventDefault()
                                    let name = in_p_name.value
                                    for(let i=0;i<td_list_name.length;i++){
                                        td_list_name[i].parentNode.classList.add("pre")
                                        if(td_list_name[i].innerHTML==name){
                                            td_list_name[i].parentNode.classList.remove("pre")
                                        }
                                    }
                                })
                            </script>
                            </table>
                        </div>
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