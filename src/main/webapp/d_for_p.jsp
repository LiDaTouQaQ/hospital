<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/base.css">
    <link rel="stylesheet" href="./css/d_for_p.css">
    <title>医患治疗</title>
</head>
<body>
    <div class="counter">
        <div class="work">
            <div class="title">
                病人治疗
            </div>
            <div>
                <span>病人姓名:</span><span><%=session.getAttribute("p_name")%></span>
            </div>
            <div>
            <form action="Insert_Case_table" method="post">
                <span>疾病类型:</span>
            
                <select name="diagnosis" id="diagnosis">
                    <optgroup label="选择疾病名称">
                        <option>感冒</option>
                        <option>发烧</option>
                        <option>头疼</option>
                        <option>肚子疼</option>
                        <option>心脏病</option>
                        </optgroup>
                </select> 
            </div>
            <div>
                <span>疾病描述:</span><textarea name="past_disease" id="past_disease" cols="30" rows="10" placeholder="输入病人症状描述"></textarea>
            </div>
            <div>
                <span>药物治疗:</span>
                <table id="toll_tab">
                    <tr><td><button id="add_btn">添加更多</button></td><td>药品</td><td>数量</td></tr>
                    <tr>
                        <td></td>
                        <td>
                            <select name="opinion" id="opinion" class="toll">
                                <optgroup label="选择药品名称">
                                    <option>阿莫西林</option>
                                    <option>阿司匹林</option>
                                    <option>罗红霉素</option>
                                    <option>青蒿素</option>
                                    <option>布洛芬</option>
                                </optgroup>
                            </select> 
                        </td>
                        <td>
                            <input name="opinion_number" id="opinion_number" type="number" class="toll_num" value="0">
                        </td>
                    </tr>
                </table>
                <script>
                    const add_btn = document.querySelector("#add_btn")
                    const tab = document.querySelector("#toll_tab")
                    add_btn.addEventListener("click",function(e){
                    	e.preventDefault()
                        let tr = document.createElement('tr')
                        tr.innerHTML = `
                          <td></td>
                            <td>
                                <select name="opinion" id="opinion" class="toll" >
                                    <optgroup label="选择药品名称">
                                        <option>阿莫西林</option>
                                        <option>阿司匹林</option>
                                        <option>罗红霉素</option>
                                        <option>青蒿素</option>
                                        <option>布洛芬</option>
                                    </optgroup>
                                </select> 
                            </td>
                            <td>
                                <input type="number" name="opinion_number" id="opinion_number" class="toll_num" value="0"> 
                            </td>  
                        `
                        tab.appendChild(tr)
                    })
                </script>
                
            </div>
            <div>
                <span>手术安排:</span>
                <input name="operation_area" type="text" placeholder="输入手术地点" id="sur_area">
                <input name="operation_date" type="date" placeholder="选择手术时间" id="sur_date">
            </div>
            <div>
                <span>治疗费用:</span>
                <span id="calculate"></span>
                <input id="ppp" name="price_cal" style="display: none;" value="111">
                <button id="cal_btn">计算</button>
                <script>
                    toll_price = [12,16,25,67,100]
                    ppp = document.querySelector("#ppp")
                    cal = document.querySelector("#calculate")
                    sur_a = document.querySelector("#sur_area")
                    sur_d = document.querySelector("#sur_date")
                    cal_btn = document.querySelector("#cal_btn")
                    cal_btn.addEventListener("click",function(e){
                    	e.preventDefault()
                        toll_num = document.querySelectorAll(".toll_num")
                        toll = document.querySelectorAll(".toll")
                        console.log(sur_a.value)
                        let price = 0
                        let text = ""
                        for(let i=0;i<toll.length;i++){
                                price = price + toll_price[toll[i].selectedIndex] * toll_num[i].value
                                text = text + toll[i].value+"*"+toll_num[i].value+"+"
                            }
                        if(sur_a.value == "无" || sur_a.value == "" ||  sur_d.value==""){
                            cal.innerHTML = text+"无手术费用="+price.toString()+"￥"
                            ppp.value = price.toString()
                        }else{
                        	price = price+200
                            cal.innerHTML = text+"手术费用200￥="+(price).toString()+"￥"
                            ppp.value = price.toString()
                        }
                    })
                </script>
            </div>
            <div>
                <button>提交治疗结果</button>
            </div>
            <%
                            	if(request.getAttribute("case_up") =="更新成功"){
                            		out.print("<script>alert('更新成功')</script>");
                            	}
            					if(request.getAttribute("case_up") =="更新失败"){
                            		out.print("<script>alert('更新失败')</script>");
                            	}
                            %>
            </form>
        </div>
    </div>
</body>
</html>