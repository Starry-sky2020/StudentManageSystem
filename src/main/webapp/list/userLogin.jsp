<%--
  Created by IntelliJ IDEA.
  User: Yang Shuailing
  Date: 2023/4/12
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
    <title></title>
    <!-- Meta tag Keywords -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8" />
    <meta name="keywords"
          content="学生信息管理系统" />
    <!-- //Meta tag Keywords -->
    <link href="//fonts.googleapis.com/css2?family=Karla:wght@400;700&display=swap" rel="stylesheet">
    <!--/Style-CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/list/css/login.css" type="text/css" media="all" />
    <!--//Style-CSS -->
</head>

<script>
    window.onload = function init() {
        document.getElementById("btn").onclick = function (){
            if (CheckUserName() && CheckPassword()){
                let username = document.getElementById("username").value
                let password = document.getElementById("password").value
                    let xhr = new XMLHttpRequest();
                    xhr.onreadystatechange = function (){
                        if (xhr.readyState == 4){
                            if (xhr.status >= 200 && xhr.status < 300){
                                if (this.responseText == "OK")
                                    window.location.href="${pageContext.request.contextPath}/list/manager-index.jsp";
                                else if (this.responseText == "ERROR")
                                    window.location.href="${pageContext.request.contextPath}/list/error.html";
                            }
                        }
                    }
                    xhr.open("get","${pageContext.request.contextPath}/userlogin/"+username+"/"+password,true);
                    xhr.send()
            } else {
                alert("用户名或密码格式不正确，请重新输入")
            }
        }
    }

    function CheckUserName(){
        let regUserName = /^([\u4e00-\u9fa5a-zA-Z0-9]{2,12}$|([a-zA-Z]{2,16})$)/;
        let userName = document.getElementById("username").value;
        if (regUserName.test(userName) && userName.length > 1){
            return true;
        } else return false;
    }

     function CheckPassword(){
        let regUserPhone = /^(?=.*[0-9])|(?=.*[a-z])|(?=.*[A-Z])|(?=.*[@#$%^&+=])|(?=\S+$).{4,20}$/;
         let passWord = document.getElementById("password").value;
        if(regUserPhone.test(passWord) && passWord.length==11){
            return true;
        }else return false;
    }
</script>

<body>

<!-- form section start -->
<section class="w3l-workinghny-form">
    <!-- /form -->
    <div class="workinghny-form-grid">
        <div class="wrapper">
            <div class="logo">
                <h1><a class="brand-logo" href="index.html"><span>普通用户</span>登录</a></h1>

            </div>
            <div class="workinghny-block-grid">
                <div class="workinghny-left-img align-end">
                    <img src="${pageContext.request.contextPath}/list/img/2.png" class="img-responsive" alt="img" />
                </div>
                <div class="form-right-inf">

                    <div class="login-form-content">
                        <h2>Where to?</h2>
                            <div class="one-frm">
                                <label>姓名</label>
                                <input id="username" type="text" name="username" onblur="CheckUserName()"  placeholder="" required="">
                            </div>
                            <div class="one-frm">
                                <label>密码</label>
                                <input id="password" type="password" name="password"  placeholder="" required="">
                            </div>
                            <label class="check-remaind">
                                <input type="checkbox">
                                <span class="checkmark"></span>
                                <p class="remember">记住我</p>

                            </label>
                            <button id="btn" class="btn btn-style mt-3">登录</button>
                            <p class="already">
                                没有账号<a href="user-submit.jsp">创建</a>
                                管理员<a href="managerLogin.jsp">登录</a>
                            </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- //form -->
    <!-- copyright-->
    <div class="copyright text-center">
        <div class="wrapper">
            <p class="copy-footer-29">© 2023 南京林业大学 学生信息管理系统| 杨帅领 <a
                    href="https://github.com/Starry-sky2020/StudentManageSystem">Github</a></p>
        </div>
    </div>
    <!-- //copyright-->
</section>
<!-- //form section start -->

</body>

</html>

