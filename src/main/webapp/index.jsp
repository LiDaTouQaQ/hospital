<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>欢迎登录xx医院系统</title>
    <link rel="stylesheet" href="./css/base.css">
    <link rel="stylesheet" href="./css/login.css">
</head>
<body>
    <div class="head">
        <div class="counter">
            <div class="title">
                <span>大头医院登录</span>
            </div>
            <form action="Login" method="post">
                <div>
                    <span>账户:</span>
                    <input type="text" placeholder="请填写注册时的手机号"  id="tel" name="tel" >
                    <em class="pre">账户号存在错误</em>
                </div>
                <div>
                    <span>密码:</span>
                    <input type="password" placeholder="请输入账户密码" id="psw" name="psw">
                    <em class="pre">密码错误</em>
                </div>
                <div class="check">
                    <input type="radio" name="identity" value="0" ><label for="identity">病人</label>
                    <input type="radio" name="identity" value="1"><label for="identity">医生</label>
                    <input type="radio" name="identity" value="2"><label for="identity">收费</label>
                    <input type="radio" name="identity" value="3"><label for="identity">药房</label>
                    <em class="pre">请选择身份</em>
                </div>
                <button>登录</button>
                <button><a href="register.jsp">注册</a></button>
            </form>
            <script>
            let check = 0
            const inp = document.querySelectorAll('input[type="radio"]')
            for(let i=0;i<inp.length;i++){
                inp[i].addEventListener("click",function(){
                    check = 1
                })
            }
                   const login_form = document.querySelector('.counter form')
                   login_form.addEventListener('submit',function(e){
                   let t = document.querySelector('#tel')
                   let p = document.querySelector('#psw')
                   let em=document.querySelectorAll('.pre')
                   if(t.value == "" || p.value == ""||check==0){
                       	for(let i=0;i<em.length;i++){
                       		em[i].style.display = "block"
                       	}
                        e.preventDefault()
                        }
                    })
            </script>
            <script>
                const box = document.querySelectorAll("input[type='checkbox']")
                for(let i=0;i<box.length;i++){
                    box[i].addEventListener("click",function(){
                        const old = document.querySelector("input[type='checkbox']:checked")
                        old.checked = false
                        this.checked = true
                    })
                }
            </script>
        </div>
    </div>
</body>
</html>