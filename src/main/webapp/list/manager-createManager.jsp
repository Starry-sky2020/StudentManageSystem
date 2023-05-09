<%@ page import="com.njfu.edu.pojo.SubmitResult" %><%--
  Created by IntelliJ IDEA.
  User: Yang Shuailing
  Date: 2023/4/11
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
  <meta charset="utf-8">
  <title>DASHMIN - Bootstrap Admin Template</title>
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <meta content="" name="keywords">
  <meta content="" name="description">

  <!-- Favicon -->
  <link href="img/favicon.ico" rel="icon">

  <!-- Google Web Fonts -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&display=swap" rel="stylesheet">

  <!-- Icon Font Stylesheet -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

  <!-- Libraries Stylesheet -->
  <link href="${pageContext.request.contextPath}/list/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/list/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

  <!-- Customized Bootstrap Stylesheet -->
  <link href="${pageContext.request.contextPath}/list/css/bootstrap.min.css" rel="stylesheet">

  <!-- Template Stylesheet -->
  <link href="${pageContext.request.contextPath}/list/css/style.css" rel="stylesheet">
</head>

<body>
<div class="container-fluid position-relative bg-white d-flex p-0">
  <!-- Spinner Start -->
  <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
    <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
      <span class="sr-only">Loading...</span>
    </div>
  </div>
  <!-- Spinner End -->

  <script>
    let res = ${resManager.getCode()};
      switch (res){
        case 3: alert("管理员已存在");break;
        case 4: alert("管理员创建成功");break;
      }

    function bak(){
      window.location.href="${pageContext.request.contextPath}/list/manager-index.jsp";
    }

    window.onload = function (){
      document.getElementById("btn").onclick = function (){
        if (CheckPassword() && CheckUserName){
            let xhr = new XMLHttpRequest();
            if (xhr.readyState == 4){
              if (xhr.status >= 200 && xhr.status < 300){
                window.location.href="${pageContext.request.contextPath}/list/manager-createManager.jsp";
              }
            }

            let mName = document.getElementById("mName").value;
            let mPassword = document.getElementById("mPassword").value;
            let mRemark = document.getElementById("mRemark").value;

            xhr.open("POST","${pageContext.request.contextPath}/manafer/createmanager",true)
            xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded;");
            xhr.send("mName="+mName+"&mPassword="+mPassword+"&mRemark="+mRemark);
          }
        }
    }

    function CheckUserName(){
      let regUserName = /^([\u4e00-\u9fa5a-zA-Z0-9]{2,12}$|([a-zA-Z]{2,16})$)/;
      let userName = document.getElementById("mName").value;
      if (regUserName.test(userName) && userName.length > 1){
        return true;
      } else return false;
    }

    function CheckPassword(){
      let regUserPhone = /^(?=.*[0-9])|(?=.*[a-z])|(?=.*[A-Z])|(?=.*[@#$%^&+=])|(?=\S+$).{4,20}$/;
      let passWord = document.getElementById("mPassword").value;
      if(regUserPhone.test(passWord) && passWord.length==11){
        return true;
      }else return false;
    }

  </script>
  <!-- Sign In Start -->
  <div class="container-fluid">
    <div class="row h-100 align-items-center justify-content-center" style="min-height: 100vh;">
      <div class="col-12 col-sm-8 col-md-6 col-lg-5 col-xl-4">
        <div class="bg-light rounded p-4 p-sm-5 my-4 mx-3">
          <div class="d-flex align-items-center justify-content-between mb-3">
            <a href="${pageContext.request.contextPath}/list/manager-userList.jsp" class="">
              <h3 class="text-primary"><i class="fa fa-hashtag me-2"></i>创建管理账号</h3>
            </a>
          </div>
            <div class="form-floating mb-3">
              <input id="mName" type="text" class="form-control" id="floatingInput" placeholder="管理员姓名" name="mName">
              <label for="floatingInput">管理员姓名</label>
            </div>
            <div class="form-floating mb-4">
              <input id="mPassword" type="password" class="form-control" id="floatingPassword" placeholder="密码" name="mPassword">
              <label for="floatingPassword">密码</label>
            </div>
            <div class="form-floating mb-4">
              <input id="mRemark" type="text" class="form-control" id="floatingRemark" placeholder="管理员信息" name="mRemark">
              <label for="floatingRemark">管理员信息</label>
            </div>
            <button id="btn" class="btn btn-primary py-3 w-100 mb-4">确认创建</button>
            <button onclick="bak()">返回主页</button>
        </div>
      </div>
    </div>
  </div>
  <!-- Sign In End -->
</div>

<!-- JavaScript Libraries -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/list/lib/chart/chart.min.js"></script>
<script src="${pageContext.request.contextPath}/list/lib/easing/easing.min.js"></script>
<script src="${pageContext.request.contextPath}/list/lib/waypoints/waypoints.min.js"></script>
<script src="${pageContext.request.contextPath}/list/lib/owlcarousel/owl.carousel.min.js"></script>
<script src="${pageContext.request.contextPath}/list/lib/tempusdominus/js/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/list/lib/tempusdominus/js/moment-timezone.min.js"></script>
<script src="${pageContext.request.contextPath}/list/lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

<!-- Template Javascript -->
<script src="${pageContext.request.contextPath}/list/js/main.js"></script>
</body>

</html>
