<%--
  Created by IntelliJ IDEA.
  User: Yang Shuailing
  Date: 2023/4/11
  Time: 8:26
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <link href="<%=request.getContextPath()%>/list/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/list/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

    <!-- Customized Bootstrap Stylesheet -->
    <link href="${pageContext.request.contextPath}/list/css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="${pageContext.request.contextPath}/list/css/style.css" rel="stylesheet">
</head>
<script type="text/javascript">
    function delStu(id){
        window.location.href='${pageContext.request.contextPath}/delstu?id='+id;
    }

    function updateStu(id){
        window.location.href='${pageContext.request.contextPath}/selectstuid?id='+id;
    }

    function initSort(){
        window.location.href='${pageContext.request.contextPath}/stulist?pageNum=1&condition=1';
    }

    function ageSort(){
        window.location.href='${pageContext.request.contextPath}/stulist?pageNum=1&condition=2';
    }

    function sexSort(){
        window.location.href='${pageContext.request.contextPath}/stulist?pageNum=1&condition=3';
    }
</script>
<body>
    <div class="container-fluid position-relative bg-white d-flex p-0">
<%--        <!-- Spinner Start -->--%>
<%--        <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">--%>
<%--            <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">--%>
<%--                <span class="sr-only">Loading...</span>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <!-- Spinner End -->--%>


        <!-- Sidebar Start -->
        <div class="sidebar pe-4 pb-3">
            <nav class="navbar bg-light navbar-light">
                <a href="${pageContext.request.contextPath}/userList.jsp" class="navbar-brand mx-4 mb-3">
                    <h3 class="text-primary"><i class="fa fa-hashtag me-2"></i>DASHMIN</h3>
                </a>
                <div class="d-flex align-items-center ms-4 mb-4">
                    <div class="position-relative">
                        <img class="rounded-circle" src="img/user.jpg" alt="" style="width: 40px; height: 40px;">
                        <div class="bg-success rounded-circle border border-2 border-white position-absolute end-0 bottom-0 p-1"></div>
                    </div>
                    <div class="ms-3">
                        <h6 class="mb-0">Jhon Doe</h6>
                        <span>Admin</span>
                    </div>
                </div>
                <div class="navbar-nav w-100">
                    <a href="${pageContext.request.contextPath}/list/manager-index.jsp" class="nav-item nav-link"><i class="fa fa-chart-bar me-2"></i>首页</a>
                    <c:if test="${identity == 0}">
                        <a href="${pageContext.request.contextPath}/manager/staff" class="nav-item nav-link active"><i class="fa fa-tachometer-alt me-2"></i>管理员工</a>
                        <a href="${pageContext.request.contextPath}/list/manager-createManager.jsp" class="nav-item nav-link"><i class="fa fa-tachometer-alt me-2"></i>添加管理员</a>
                    </c:if>
                    <a href="${pageContext.request.contextPath}/stulist" class="nav-item nav-link"><i class="fa fa-tachometer-alt me-2"></i>管理学生</a>
                    <a href="${pageContext.request.contextPath}/list/manager-addStu.jsp" class="nav-item nav-link"><i class="fa fa-tachometer-alt me-2"></i>添加学生</a>
                </div>
            </nav>
        </div>
        <!-- Sidebar End -->


        <!-- Content Start -->
        <div class="content">
            <!-- Navbar Start -->
            <nav class="navbar navbar-expand bg-light navbar-light sticky-top px-4 py-0">
                <a href="${pageContext.request.contextPath}/list/manager-userList.jsp" class="navbar-brand d-flex d-lg-none me-4">
                    <h2 class="text-primary mb-0"><i class="fa fa-hashtag"></i></h2>
                </a>
                <a href="#" class="sidebar-toggler flex-shrink-0">
                    <i class="fa fa-bars"></i>
                </a>
                <form class="d-none d-md-flex ms-4">
                    <input class="form-control border-0" type="search" placeholder="Search">
                </form>
                <div class="navbar-nav align-items-center ms-auto">
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
                            <img class="rounded-circle me-lg-2" src="img/user.jpg" alt="" style="width: 40px; height: 40px;">
                            <span class="d-none d-lg-inline-flex">John Doe</span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-end bg-light border-0 rounded-0 rounded-bottom m-0">
                            <a href="#" class="dropdown-item">My Profile</a>
                            <a href="#" class="dropdown-item">Settings</a>
                            <a href="#" class="dropdown-item">Log Out</a>
                        </div>
                    </div>
                </div>
            </nav>
            <!-- Navbar End -->

            <!-- Recent Sales Start -->
            <div class="container-fluid pt-4 px-4">
                <div class="bg-light text-center rounded p-4">
                    <div class="d-flex align-items-center justify-content-between mb-4">
                        <button type="button" class="btn btn-outline-primary" onclick="initSort()">默认排序</button>
                        <button type="button" class="btn btn-outline-primary" onclick="ageSort()">年龄升序</button>
                        <button type="button" class="btn btn-outline-primary" onclick="sexSort()">性别升序</button>
                    </div>
                    <div class="table-responsive">
                        <table class="table text-start align-middle table-bordered table-hover mb-0">
                            <thead>
                                <tr class="text-dark">
                                    <th scope="col">姓名</th>
                                    <th scope="col">年龄</th>
                                    <th scope="col">性别</th>
                                    <th scope="col">学校</th>
                                    <th scope="col">地址</th>
                                    <th scope="col">信息</th>
                                    <th scope="col">更新时间</th>
                                    <th scope="col">操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${paging.getList()}" var="stu">
                                    <tr>
                                        <td>${stu.student_name}</td>
                                        <td>${stu.age}</td>
                                        <td>${stu.sex}</td>
                                        <td>${stu.school}</td>
                                        <td>${stu.address}</td>
                                        <td>${stu.info}</td>
                                        <td>${stu.updateTime}</td>
                                        <td>
                                            <button type="button" class="btn btn-danger m-2" onclick="delStu(${stu.student_id})">删除</button>
                                            <button type="button" class="btn btn-primary m-2" onclick="updateStu(${stu.student_id})">更新</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <!-- Recent Sales End -->
            <div style="width: 200px;margin-left: 450px;">
                <c:if test="${paging.getRecordTotal() > 0}">
                    <c:if test="${paging.getPageNum() <= 1}">
                        <span>首页</span>
                        <span>上一页</span>
                        <a  href="${pageContext.request.contextPath}/stulist?pageNum=${paging.getPageNum()+1}">下一页</a>
                        <a  href="${pageContext.request.contextPath}/stulist?pageNum=${paging.getPageTotal()}">尾页</a>
                    </c:if>

                    <c:if test="${paging.getPageNum() > 1 && paging.getPageNum() < paging.getPageTotal()}">
                        <a  href="${pageContext.request.contextPath}/stulist?pageNum=1">首页</a>
                        <a  href="${pageContext.request.contextPath}/stulist?pageNum=${paging.getPageNum() - 1}">上一页</a>
                        <a  href="${pageContext.request.contextPath}/stulist?pageNum=${paging.getPageNum() + 1}">下一页</a>
                        <a  href="${pageContext.request.contextPath}/stulist?pageNum=${paging.getPageTotal()}">尾页</a>
                    </c:if>

                    <c:if test="${paging.getPageNum() >= paging.getPageTotal()}">
                        <a  href="${pageContext.request.contextPath}/stulist?pageNum=1">首页</a>
                        <a  href="${pageContext.request.contextPath}/stulist?pageNum=${paging.getPageNum() - 1}">上一页</a>
                        <span>下一页</span>
                        <span>尾页</span>
                    </c:if>
                </c:if>
            </div>
        <!-- Content End -->
        <!-- Footer Start -->
            <div class="container-fluid pt-4 px-4">
                <div class="bg-light rounded-top p-4">
                    <div class="row">
                        <div class="col-12 col-sm-6 text-center text-sm-start">
                            &copy; <a href="#">Your Site Name</a>, All Right Reserved.
                        </div>
                        <div class="col-12 col-sm-6 text-center text-sm-end">
                            <%--                                /*** This template is free as long as you keep the footer author’s credit link/attribution link/backlink. If you'd like to use the template without the footer author’s credit link/attribution link/backlink, you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". Thank you for your support. ***/--%>
                            Designed By <a href="https://htmlcodex.com">HTML Codex</a>
                        </div>
                    </div>
                </div>
            </div>
        <!-- Footer End -->

        </div>
        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
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