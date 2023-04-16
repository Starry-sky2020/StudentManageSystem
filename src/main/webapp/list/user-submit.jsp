<%--
  Created by IntelliJ IDEA.
  User: Yang Shuailing
  Date: 2023/4/12
  Time: 14:49
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

<body>
<script>
  let res = ${userSubmit.getCode()};
  switch (res){
    case 1: alert("用户名不合法");break;
    case 2: alert("密码不合法");break;
    case 3: alert("用户已存在");break;
    case 4: alert("用户注册成功，点击返回登录");break;
  }
</script>
<!-- form section start -->
<section class="w3l-workinghny-form">
  <!-- /form -->
  <div class="workinghny-form-grid">
    <div class="wrapper">
      <div class="logo">
        <h1><a class="brand-logo" href="index.html"><span>普通用户</span>注册</a></h1>
        <!-- if logo is image enable this
            <a class="brand-logo" href="#index.html">
                <img src="image-path" alt="Your logo" title="Your logo" style="height:35px;" />
            </a> -->
      </div>
      <div class="workinghny-block-grid">
        <div class="workinghny-left-img align-end">
          <img src="${pageContext.request.contextPath}/list/img/2.png" class="img-responsive" alt="img" />
        </div>
        <div class="form-right-inf">

          <div class="login-form-content">
            <h2>Where to?</h2>
            <form action="${pageContext.request.contextPath}/usersubmit" class="signin-form" method="post">
              <div class="one-frm">

                <label>姓名</label>
                <input type="text" name="username"  placeholder="" required="">
              </div>
              <div class="one-frm">
                <label>密码</label>
                <input type="password" name="password"  placeholder="" required="">
              </div>
              <label class="check-remaind">
                <input type="checkbox">
                <span class="checkmark"></span>
                <p class="remember">记住我</p>

              </label>
              <button class="btn btn-style mt-3">注册</button>
              <p class="already">
                已有账户<a href="userLogin.jsp">登录</a>
                管理员<a href="managerLogin.jsp">登录</a>
              </p>
            </form>
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
