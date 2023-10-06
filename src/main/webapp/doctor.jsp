<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/base.css">
    <link rel="stylesheet" href="./css/doctor.css">
    <title>doctor</title>
</head>
<body>
    <div class="counter">
        <div class="work_line">
            <ul>
                <li>
                    <div class="title now_display">基本信息</div>
                    <div class="message_box now_display">
                        <div class="m_title">
                            医生信息
                        </div>
                        <form action="DoctChange" method="post">
                            <div><span>医生姓名</span><input type="text" id="d_name" name="d_name" value="<%=session.getAttribute("d_name")%>">
                            </div>
                            <div><span>所属科室</span><input type="text" id="d_sur" name="d_sur" value="<%=session.getAttribute("d_sur")%>">
                            </div>
                            <div><span>医生简介</span><textarea id="d_intor" name="d_intor" cols="30" rows="10" value=""><%=session.getAttribute("d_intor")%></textarea>
                            </div>
                            <button>确认修改</button>
                            
                        </form>
                        <form action="Exit" method="post">
                        	<button>退出登录</button>
                        </form>
                        
                    </div>
                </li>
                <li>
                    <div class="title">一周出勤</div>
                    <div class="message_box">
                        <div class="m_title">
                            本周排班
                            
                        </div>
                        <div>
                            <table>
                                <tr>
                                    <td>姓名</td><td>科室</td><td>时间</td><td>日期</td>
                                </tr>
                            <div>
                                <c:forEach items="${c_msg}" var="cmsg">
                                    <tr>
                                        <td>${cmsg.s_name}</td><td>${cmsg.s_surgery}</td><td>${cmsg.s_time}</td><td>${cmsg.s_date}</td>
                                    </tr>
                                </c:forEach>
                            </div>
                            </table>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="title">当前病人</div>
                    <div class="message_box">
                        <div class="m_title">
                            病人信息
                        </div>
                        <div>
                            <div class="work_head">
                                <span>姓名</span>
                                <span>疾病</span>
                                <span>治疗方式</span>
                                <span>就诊时间</span>
                                <span>就诊</span>
                            </div>
                            <div class="work_li">
                                <ul>
                                    <c:forEach items="${d_c_msg}" var="dcmsg">
                                        <li>
                                            <div>
                                                <span>${dcmsg.p_name}</span>
                                            </div>
                                            <div>
                                                <span>${dcmsg.diagnosis}</span>
                                            </div>
                                            <div>
                                                <span>${dcmsg.opinion}</span>
                                            </div>
                                            <div>
                                                <span>${dcmsg.case_time}</span>
                                            </div>
                                            <form action="D_For_P" method="post">
                                                <input type="text" name="case_id" id = "case_id" value="${dcmsg.case_id }" style="display: none;">
                                                <button>喊号</button>
                                                                            <%
                            	if(request.getAttribute("d_change") =="跳转失败"){
                            		out.print("<script>alert('跳转失败')</script>");
                            	}
                            %>
                                            </form>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
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