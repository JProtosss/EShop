<%--
  Created by IntelliJ IDEA.
  User: Евгений
  Date: 19.05.2019
  Time: 8:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${lang_id}"/>
<fmt:setBundle basename="language"/>
<div class="w3-modal-content w3-card-4 w3-animate-zoom" style="max-width:600px">
    <div class="w3-center"><br>
        <span onclick="document.getElementById('login').style.display='none'"
              class="w3-button w3-xlarge w3-hover-red w3-display-topright w3-padding" title="Close">&times;</span>
    </div>
    <form method="post" action="/" class="w3-container">
        <div class="w3-section w3-large">

            <%--Username field begin --%>
            <div>
                <label><b><fmt:message key="username"/></b></label>
                <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="<fmt:message key="enter"/> <fmt:message key="username"/>"
                       name="username" required>
            </div>

            <%--Password field begin --%>

            <label><b><fmt:message key="password"/></b></label>
            <input class="w3-input w3-border" type="password" placeholder="<fmt:message key="enter"/> <fmt:message key="password"/>" name="password" required>
            <div class="g-recaptcha w3-margin-top"
                 data-sitekey="6Ldy5qgUAAAAAODSi5SjRnqgYGSsGm9fqT0THH-r"></div>
                <c:if test="${loginError!=null}">
                    <h6 style="color: red">${loginError}</h6>
                </c:if>
            <button class="w3-button w3-block w3-black w3-section w3-padding" type="submit" name="command"
                    value="auth">
                <fmt:message key="login"/>
            </button>
        </div>
    </form>
    <div class="w3-container w3-border-top w3-padding-16 w3-light-grey">
        <button onclick="document.getElementById('login').style.display='none'" type="button"
                class=" w3-hover-opacity w3-red w3-medium w3-padding" style="border:none;"><fmt:message key="cancel"/>
        </button>
        <span class="w3-right w3-padding w3-hide-small" style="cursor: pointer; text-decoration: underline"><a
                onclick="document.getElementById('login').style.display='none';document.getElementById('recover').style.display='block'"><fmt:message key="forgotPassword"/>?</a></span>
        <span class="w3-right w3-padding w3-hide-small" style="cursor: pointer; text-decoration: underline"><a
                onclick="document.getElementById('login').style.display='none';document.getElementById('registration').style.display='block'"><fmt:message key="registration"/></a></span>
    </div>
</div>

