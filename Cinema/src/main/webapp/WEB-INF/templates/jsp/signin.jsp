<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <title>Sign in</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>


<body class="text-center mt-5">

<img class="mb-4" src="cinema.png" alt="">
<h1 class="h3 mb-3 fw-normal">Please sign in</h1>
<form action="/signin" method="post">
    <div class="form-floating">
        <input type="text" name="email" class="form-control" placeholder="Email" id="floatingInput"/>
        <label for="floatingInput">Username</label>
    </div>
    <div class="form-floating">
        <input type="password" name="password" class="form-control" placeholder="Password" id="floatingPassword"/>
        <label for="floatingPassword">Password</label>
    </div>
    <div class="checkbox mb-3">
        <label>
            <input type="checkbox" value="remember-me"> Remember me
        </label>
    </div>
    <!--    <div><input type="submit" value="Sign In"/></div>-->
    <button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
    <br/>
    <br/>
    <a type="button" class="w-100 btn btn-lg btn-primary" href="/signup">Registration</a>
    <div>
        <td><%= request.getAttribute("error") == null ? "" : request.getAttribute("error") %></td>
    </div>
    <!--    <div th:if="${param.logout}">-->
    <!--        You have been logged out.-->
    <!--    </div>-->
    <p class="mt-5 mb-3 text-muted">&copy; 2022</p>
</form>
</body>


</html>




