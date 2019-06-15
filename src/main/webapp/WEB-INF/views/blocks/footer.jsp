<%--
  Created by IntelliJ IDEA.
  User: Евгений
  Date: 20.05.2019
  Time: 1:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${lang_id}"/>
<fmt:setBundle basename="language"/>

<a id="footer" onclick="toTopOfPage()" class="w3-button w3-light-grey"><i class="fa fa-arrow-up w3-margin-right"></i><fmt:message key="toTheTop"/></a>
<div class="w3-xlarge w3-section">
    <form method="post" style="display: inline">
        <input type="hidden" name="lang_id" value="ru">
        <button class="w3-button w3-hover-black w3-medium" style="outline: none" type="submit" name="command"
                value="language"><u
                class="fa w3-hover-opacity">EN</u></button>
        <i class="fa">/</i>
    </form>
    <form method="post" style="display: inline">
        <input type="hidden" name="lang_id" value="en">
        <button class="w3-button w3-hover-black w3-medium" style="outline: none" type="submit" name="command"
                value="language"><u
                class="fa w3-hover-opacity">RU</u></button>
    </form>
</div>
<p><fmt:message key="madeBy"/> <a href="https://vk.com/evgenii.protasov" title="<fmt:message key="creator"/>" class="w3-hover-text-green"><fmt:message key="evgeniyProtasov"/></a></p>
<script>
    function toTopOfPage() {
        if (window.scrollY > 0) {
            window.scrollTo(0, window.scrollY - 20)
            setTimeout("toTopOfPage()", 10)
        }
    }
</script>

