<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/base.css">
    <link rel="stylesheet" href="./css/regist.css">
    <title>欢迎登录xx医院系统</title>
</head>
<body>
    <div class="head">
        <div class="counter">
            <div class="title">
                <span>大头医院注册</span>
            </div>
            <form action="Register" method="post">
                <div>
                    <span>姓名:</span>
                    <input type="text" placeholder="输入个人姓名" id="name" name="name">
                    <em class="pre">姓名不能为空</em>
                </div>
                <div>
                    <span>手机号:</span>
                    <input type="text" placeholder="填写个人手机号" id="tel" name="tel">
                    <em class="pre">手机号不能为空</em>
                    <em class="pre2">手机号存在错误</em>
                </div>
                <div>
                    <span>身份证号:</span>
                    <input type="text" placeholder="填写个人身份证" id="id" name="id">
                    <em class="pre">身份证号不能为空</em>
                    <em class="pre2">身份证号有误</em>
                </div>
                <div>
                    <span>密码:</span>
                    <input type="password" placeholder="输入账户密码" id="psw" name="psw">
                     <em class="pre">密码不能为空</em>
                    <em class="pre2">密码长度必须为8-20</em>
                </div>
                <div>
                    <span>确认密码:</span>
                    <input type="password" placeholder="确认账户密码" id="repsw">
                    <em class="pre">确认密码不能为空</em>
                    <em class="pre2">两次密码不一致</em>
                </div>
                <div class="check">
                    <input type="radio" name="identity" value="0" ><label for="identity">病人</label>
                    <input type="radio" name="identity" value="1"><label for="identity">医生</label>
                    <input type="radio" name="identity" value="2"><label for="identity">收费</label>
                    <input type="radio" name="identity" value="3"><label for="identity">药房</label>
                    <em class="pre">请选择身份</em>
                </div>
                <button>注册信息</button>
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
                   let n = document.querySelector('#name')
                   let t = document.querySelector('#tel')
                   let i = document.querySelector('#id')
                   let p = document.querySelector('#psw')
                   let rp = document.querySelector('#repsw')
                   let em=document.querySelectorAll('.pre')
                   if(n.value == "" || t.value == ""|| i.value == ""|| p.value == ""|| rp.value == ""||check==0){
                       	for(let i=0;i<em.length;i++){
                       		em[i].style.display = "block"
                       	}          	
                        e.preventDefault()
                        }
                    })
            </script>
        </div>
    </div>
</body>
</html>