<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${lang_id}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <c:import url="blocks/resources.jsp"/>
    <title>error</title>
</head>
<body>
<h1>Эх братец, что-то пошло не так!</h1>
<h6>Sorry(( Сорямба</h6>
<div class="w3-panel w3-pale-red w3-border" role="alert">
    ${error}
</div>
</body>
</html>
