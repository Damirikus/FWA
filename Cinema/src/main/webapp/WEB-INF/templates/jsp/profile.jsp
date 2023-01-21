<%@ page import="edu.school21.cinema.models.User" %>
<%@ page import="edu.school21.cinema.models.SessionData" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.Locale" %><%--
  Created by IntelliJ IDEA.
  User: damirikus
  Date: 21.11.2022
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      lang="en">

<head>
    <title>Profile</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
    <!--    <link rel="stylesheet" href="/static/styles/profile.css">-->
</head>



<body>
<% User user = (User) request.getSession().getAttribute("currentUser"); %>
<header>
    <div class="px-3 py-2 bg-dark text-white">
        <div class="container">
            <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                <a href="/profile" class="d-flex align-items-center my-2 my-lg-0 me-lg-auto text-white text-decoration-none">
                    <img src="static/cinema.png" width="80" height="80" alt="">
                    <span ><%= user.getName()%></span>
                </a>

                <ul class="nav col-12 col-lg-auto my-2 justify-content-center my-md-0 text-small">
                    <li>
                        <a href="/profile" class="nav-link text-secondary">
                            <svg class="bi d-block mx-auto mb-1" width="24" height="24"></svg>
                            Home
                        </a>
                    </li>
                    <li>
                        <a href="/profile" class="nav-link text-white">
                            <svg class="bi d-block mx-auto mb-1" width="24" height="24"></svg>
                            Dashboard
                        </a>
                    </li>
                    <li>
                        <a href="/profile" class="nav-link text-white">
                            <svg class="bi d-block mx-auto mb-1" width="24" height="24"></svg>
                            Orders
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="px-3 py-2 border-bottom mb-3">
        <div class="container d-flex flex-wrap justify-content-center">
            <form class="col-12 col-lg-auto mb-2 mb-lg-0 me-lg-auto">
                <input type="search" class="form-control" placeholder="Search..." aria-label="Search">
            </form>

        </div>
    </div>
</header>

<div class="container">
    <div class="main-body">
        <!-- /Breadcrumb -->
        <div class="row gutters-sm">
            <div class="col-md-4 mb-3">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex flex-column align-items-center text-center">

                            <% String filename = user.getFilename();
                                System.out.println("name - " + filename);
                                if (filename == null){
                                    filename = "https://bootdey.com/img/Content/avatar/avatar7.png";
                                }
                            %>

                            <img src="images/<%=filename%>" alt="Admin" class="rounded-circle" width="240">

                            <div class="mt-3">
                                <h4><%= user.getName() + "" + user.getSurname() %></h4>
                                <p class="text-secondary mb-1">Full Stack Developer</p>
                                <p class="text-muted font-size-sm">Bay Area, San Francisco, CA</p>
                            </div>
                            <form action="${pageContext.request.contextPath}/profile" method="post" enctype="multipart/form-data">
                                <input type="file" id="file" name="file">
                                <input type="submit" value="Upload">
                            </form>
                        </div>
                    </div>
                </div>
                <div class="card mt-3">
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                            <h6 class="mb-0"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-globe mr-2 icon-inline"><circle cx="12" cy="12" r="10"></circle><line x1="2" y1="12" x2="22" y2="12"></line><path d="M12 2a15.3 15.3 0 0 1 4 10 15.3 15.3 0 0 1-4 10 15.3 15.3 0 0 1-4-10 15.3 15.3 0 0 1 4-10z"></path></svg>Website</h6>
                            <span class="text-secondary">https://bootdey.com</span>
                        </li>
                        <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                            <h6 class="mb-0"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-github mr-2 icon-inline"><path d="M9 19c-5 1.5-5-2.5-7-3m14 6v-3.87a3.37 3.37 0 0 0-.94-2.61c3.14-.35 6.44-1.54 6.44-7A5.44 5.44 0 0 0 20 4.77 5.07 5.07 0 0 0 19.91 1S18.73.65 16 2.48a13.38 13.38 0 0 0-7 0C6.27.65 5.09 1 5.09 1A5.07 5.07 0 0 0 5 4.77a5.44 5.44 0 0 0-1.5 3.78c0 5.42 3.3 6.61 6.44 7A3.37 3.37 0 0 0 9 18.13V22"></path></svg>Github</h6>
                            <span class="text-secondary">bootdey</span>
                        </li>
                        <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                            <h6 class="mb-0"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-twitter mr-2 icon-inline text-info"><path d="M23 3a10.9 10.9 0 0 1-3.14 1.53 4.48 4.48 0 0 0-7.86 3v1A10.66 10.66 0 0 1 3 4s-4 9 5 13a11.64 11.64 0 0 1-7 2c9 5 20 0 20-11.5a4.5 4.5 0 0 0-.08-.83A7.72 7.72 0 0 0 23 3z"></path></svg>Twitter</h6>
                            <span class="text-secondary">@bootdey</span>
                        </li>
                        <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                            <h6 class="mb-0"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-instagram mr-2 icon-inline text-danger"><rect x="2" y="2" width="20" height="20" rx="5" ry="5"></rect><path d="M16 11.37A4 4 0 1 1 12.63 8 4 4 0 0 1 16 11.37z"></path><line x1="17.5" y1="6.5" x2="17.51" y2="6.5"></line></svg>Instagram</h6>
                            <span class="text-secondary">bootdey</span>
                        </li>
                        <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                            <h6 class="mb-0"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-facebook mr-2 icon-inline text-primary"><path d="M18 2h-3a5 5 0 0 0-5 5v3H7v4h3v8h4v-8h3l1-4h-4V7a1 1 0 0 1 1-1h3z"></path></svg>Facebook</h6>
                            <span class="text-secondary">bootdey</span>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-md-8">
                <div class="card mb-3">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Name</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <%= user.getName()%>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Surname</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <%= user.getSurname()%>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Mobile</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <%= user.getPhone()%>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Email</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <%= user.getEmail()%>
                            </div>
                        </div>
                        <hr>
<%--                        <div class="row">--%>
<%--                            <div class="col-sm-12">--%>
<%--                                <a class="btn btn-info" href="">Edit</a>--%>
<%--                            </div>--%>
<%--                        </div>--%>
                    </div>
                </div>
                <br>
                <div class="row gutters-sm">
                    <div class="col-sm-6 mb-3">
                        <div class="card h-100">


                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col">Date</th>
                                    <th scope="col">Time</th>
                                    <th scope="col">IP</th>
                                </tr>
                                </thead>
                                    <%
                                        int count = 0;
                                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM dd,yyyy", Locale.ENGLISH);
                                        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm");
                                        for (int i = user.getSessionDataList().size() - 1; i >= 0;  i--){
                                            if (count == 10){
                                                break;
                                            }
                                            out.println("<tbody><tr>");
                                            out.println("<td>" + dtf.format(user.getSessionDataList().get(i).getDate()) + "</td>");
                                            out.println("<td>" + dtf2.format(user.getSessionDataList().get(i).getDate()) + "</td>");
                                            out.println("<td>" + user.getSessionDataList().get(i).getIp() + "</td>");
                                            out.println("</tr></tbody>");
                                            count++;
                                        }
                                    %>
                            </table>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>




<footer class="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top">
    <div class="col-md-4 d-flex align-items-center">
        <a href="/" class="mb-3 me-2 mb-md-0 text-muted text-decoration-none lh-1">
            <svg class="bi" width="30" height="24"><use xlink:href="#bootstrap"></use></svg>
        </a>
        <span class="text-muted">© 2022 FWA, Inc</span>
    </div>

    <ul class="nav col-md-4 justify-content-end list-unstyled d-flex">
        <li class="ms-3"><a class="text-muted" href="#">
            <img src="WEB-INF/images/cinema.png" class="bi" width="24" height="24" alt=""></a>
        </li>
        <li class="ms-3"><a class="text-muted" href="#">
            <img src="facebook.png" class="bi" width="24" height="24" alt=""></a>
        </li>
        <li class="ms-3"><a class="text-muted" href="#">
            <img src="twitter.png" class="bi" width="24" height="24" alt=""></a>
        </li>
    </ul>
</footer>

</body>

</html>
