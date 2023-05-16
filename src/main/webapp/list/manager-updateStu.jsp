<%--
  Created by IntelliJ IDEA.
  User: Yang Shuailing
  Date: 2023/4/11
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<script>

  window.onload = function (){
    document.getElementById("btn").onclick = function (){
      let xhr = new XMLHttpRequest();
      xhr.onreadystatechange = function (){
        if (xhr.readyState == 4){
          if (xhr.status >= 200 && xhr.status < 300){
            window.location.href="${pageContext.request.contextPath}/list/manager-stuList.jsp"
          }
        }
      }

      let stuId = document.getElementById("stuId").value;
      let stuName = document.getElementById("stuName").value;
      let stuAge = document.getElementById("stuAge").value;
      let stuSex = document.getElementById("stuSex").value;
      let stuSch = document.getElementById("stuSch").value;
      let stuAdd = document.getElementById("stuAdd").value;
      let stuInfo = document.getElementById("stuInfo").value;

      xhr.open("POST","${pageContext.request.contextPath}/updatestu",true)
      xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded;");
      xhr.send("/"+stuId+"/"+stuName+"/"+stuAge+"/"+stuSex+"/"+stuSch+"/"+stuAdd+"/"+stuInfo);
    }
  }

  function bak(){
    window.location.href="${pageContext.request.contextPath}/list/manager-index.jsp";
  }
</script>

<body>
<div class="container-fluid position-relative bg-white d-flex p-0">
  <!-- Spinner Start -->
  <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
    <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
      <span class="sr-only">Loading...</span>
    </div>
  </div>
  <!-- Spinner End -->


  <!-- Sign In Start -->
  <div class="container-fluid">
    <div class="row h-100 align-items-center justify-content-center" style="min-height: 100vh;">
      <div class="col-12 col-sm-8 col-md-6 col-lg-5 col-xl-4">
        <div class="bg-light rounded p-4 p-sm-5 my-4 mx-3">
          <div class="d-flex align-items-center justify-content-between mb-3">
            <a href="${pageContext.request.contextPath}/list/userList.html" class="">
              <h3 class="text-primary"><i class="fa fa-hashtag me-2"></i>修改学生信息</h3>
            </a>
          </div>
            学号：<input id="stuId" type="text" name="stuId" value="${updateStu.student_id}" readonly><br>
            姓名：<input id="stuName" type="text" name="stuName" value="${updateStu.student_name}"><br>
            年龄：<input id="stuAge" type="text" name="stuAge" value="${updateStu.age}"><br>
            性别：<input id="stuSex" type="text" name="stuSex" value="${updateStu.sex}"><br>
            学校：<input id="stuSch" type="text" name="stuSch" value="${updateStu.school}"><br>
            地址：<input id="stuAdd" type="text" name="stuAdd" value="${updateStu.address}"><br>
            信息：<input id="stuInfo" type="text" name="stuInfo" value="无"><br>
            <button id="btn" type="button" class="btn btn-primary w-100 mb-4">确认修改</button>
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
