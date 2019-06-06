<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Евгений
  Date: 19.05.2019
  Time: 8:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<div class="w3-modal-content w3-card-4 w3-animate-zoom" style="max-width:600px">
    <div class="w3-center"><br>
        <span onclick="document.getElementById('login').style.display='none'"
              class="w3-button w3-xlarge w3-hover-red w3-display-topright w3-padding" title="Close">&times;</span>
    </div>
    <form method="post" action="" class="w3-container">
        <div class="w3-section w3-large">

            <%--Username field begin --%>
            <c:choose>
                <c:when test="${not empty userError.username && not empty auth}">
                    <c:set var="classUsername" value="has-error"/>
                </c:when>
                <c:otherwise>
                    <c:set var="classUsername" value=""/>
                </c:otherwise>
            </c:choose>
                <div class="${classUsername}">
                    <label><b>Username</b></label>
                    <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="Enter Username"
                           name="username" required>
                </div>

            <%--Password field begin --%>
            <c:choose>
                <c:when test="${not empty userError.password && not empty auth}">
                    <c:set var="classPassword" value="has-error"/>
                </c:when>
                <c:otherwise>
                    <c:set var="classPassword" value=""/>
                </c:otherwise>
            </c:choose>
            <label><b>Password</b></label>
            <input class="w3-input w3-border" type="password" placeholder="Enter Password" name="password" required>
            <button class="w3-button w3-block w3-black w3-section w3-padding" type="submit" name="command"
                    value="auth">
                Login
            </button>
        </div>
    </form>
    <div class="w3-container w3-border-top w3-padding-16 w3-light-grey">
        <button onclick="document.getElementById('login').style.display='none'" type="button"
                class=" w3-hover-opacity w3-red w3-medium w3-padding" style="border:none;">Cancel
        </button>
        <span class="w3-right w3-padding w3-hide-small" style="cursor: pointer; text-decoration: underline"><a
                onclick="document.getElementById('login').style.display='none';document.getElementById('recover').style.display='block'">Forgot password?</a></span>
        <span class="w3-right w3-padding w3-hide-small" style="cursor: pointer; text-decoration: underline"><a
                onclick="document.getElementById('login').style.display='none';document.getElementById('registration').style.display='block'">Registration</a></span>
    </div>
</div>
</body>
</html>
