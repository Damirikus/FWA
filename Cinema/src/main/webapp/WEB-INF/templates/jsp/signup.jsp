
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org"
      xmlns:sec="https://www.thymeleaf.org/thymeleaf-extras-springsecurity3" lang="en">
<head>
    <title>Sign up</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>


<body class="text-center mt-5">

<h1 class="h3 mb-3 fw-normal">Registration</h1>
<form action="/signup" method="post">

    <div class="form-floating">
        <div>
            <% String nameError = (String) request.getAttribute("nameError");%>
            <input type="text" name="name"
                   class="<%= nameError == null ? "form-control" : "form-control is-invalid"%>"
                   placeholder="Name"/>
            <%= nameError == null ? "" :
                    "<div class=\"invalid-feedback\">" +
                            nameError + "</div>"  %>
        </div>
    </div>

    <div class="form-floating">
        <div>
            <% String surnameError = (String) request.getAttribute("surnameError");%>
            <input type="text" name="surname"
                   class="<%= surnameError == null ? "form-control" : "form-control is-invalid"%>"
                   placeholder="Surname"/>
            <%= surnameError == null ? "" :
                    "<div class=\"invalid-feedback\">" +
                            surnameError + "</div>"  %>
        </div>
    </div>


    <div class="form-floating">
        <div>
            <% String phoneError = (String) request.getAttribute("phoneError");%>
            <input type="number" name="phone"
                   class="<%= phoneError == null ? "form-control" : "form-control is-invalid"%>"
                   placeholder="8(888)888-88-88"/>
            <%= phoneError == null ? "" :
                    "<div class=\"invalid-feedback\">" +
                            phoneError + "</div>"  %>
        </div>
    </div>

    <div class="form-floating">

        <div>
            <% String emailError = (String) request.getAttribute("emailError");%>
            <input type="email" name="email"
                   class="<%= emailError == null ? "form-control" : "form-control is-invalid"%>"
                   placeholder="name@example.com"/>
            <%= emailError == null ? "" :
                    "<div class=\"invalid-feedback\">" +
                            emailError + "</div>"  %>
        </div>
    </div>

    <div class="form-floating">
        <div>
            <% String passwordError = (String) request.getAttribute("passwordError");%>
            <input type="password" name="password"
                   class="<%= passwordError == null ? "form-control" : "form-control is-invalid"%>"
                   placeholder="Password"/>
            <%= passwordError == null ? "" :
                    "<div class=\"invalid-feedback\">" +
                            passwordError + "</div>"  %>
        </div>
    </div>

    <div class="form-floating">
        <div>
            <% String password2Error = (String) request.getAttribute("password2Error");%>
            <input type="password" name="password2"
                   class="<%= password2Error == null ? "form-control" : "form-control is-invalid"%>"
                   placeholder="Confirm the password"/>
            <%= password2Error == null ? "" :
                    "<div class=\"invalid-feedback\">" +
                            password2Error + "</div>"  %>
        </div>
    </div>


    <div>
        <% String captchaError = (String) request.getAttribute("captchaError");%>
        <div class="g-recaptcha" data-sitekey="6LfSXgAgAAAAACvl_dBFMhSO3fgK9vrB5yw3efNZ"></div>
        <%= captchaError == null ? "" :
                "<div class=\"alert alert-danger\" role=\"alert\">" +
                        captchaError + "</div>"  %>

    </div>

    <button class="w-100 btn btn-lg btn-dark" type="submit">Register</button>

    <p class="mt-5 mb-3 text-muted">&copy; 2023</p>
</form>
</body>


</html>





