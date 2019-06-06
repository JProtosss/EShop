<%--
  Created by IntelliJ IDEA.
  User: Евгений
  Date: 20.05.2019
  Time: 1:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<div class="w3-bar w3-white w3-card" id="myNavbar">


    <c:choose>
        <c:when test="${not empty user && empty userError}">
            <button class="w3-button w3-bar-item w3-wide"><img src="images/user.png" style="padding-bottom: 8px">
            </button>
            <button class="w3-button w3-bar-item w3-wide"><img src="images/basket.png" style="padding-bottom: 8px">
            </button>
            <form method="POST" name="logout" id="logout" action="">
                <button name="command" value="logout" class="w3-button w3-bar-item w3-wide"><img src="../../images/shopping-basket-button.png" style="padding-bottom: 8px"
                                                                   type="submit"></button>
            </form>
        </c:when>
        <c:otherwise>
            <button onclick="document.getElementById('login').style.display='block'"
                    class="w3-bar-item w3-button w3-wide">
                LOGIN
            </button>
        </c:otherwise>

    </c:choose>

    <div id="login" class="w3-modal" style="display:none"><c:import url="authorization/login.jsp"/></div>
    <div id="registration" class="w3-modal" style="display:none"><c:import url="authorization/registration.jsp"/></div>
    <div id="recover" class="w3-modal" style="display:none"><c:import url="authorization/recover.jsp"/></div>

    <!-- Right-sided navbar links -->
    <div class="w3-right w3-hide-small">
        <button class="w3-bar-item w3-button" style="font-weight: bold">HOME</button>
        <button class="w3-button w3-bar-item" href="${pageContext.request.contextPath}/laptops">
            <i class="fa">
            </i> LAPTOPS
        </button>
        <button class="w3-button w3-bar-item">
            <i class="fa"></i> DESKTOP COMPUTERS
        </button>
        <button class="w3-button w3-bar-item"><i class="fa"></i>
            IPAD&TABLETS</button>
            <button class="w3-button w3-bar-item" href="${pageContext.request.contextPath}/accessories"><i class="fa"></i>
                ACCESORIES</button>
                <a href="#contact" class="w3-bar-item w3-button"><i class="fa fa-envelope"></i> CONTACT</a>
    </div>
    <!-- Hide right-floated links on small screens and replace them with a menu icon -->
    <a href="javascript:void(0)" class="w3-bar-item w3-button w3-right w3-hide-large w3-hide-medium"
       onclick="w3_open()" style="height: 59px;">
        <i class="fa fa-bars" style="margin-top:5px;"></i>
    </a>
</div>
</body>
</html>
