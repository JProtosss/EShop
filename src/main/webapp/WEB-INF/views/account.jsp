<%--
  Created by IntelliJ IDEA.
  User: Евгений
  Date: 07.06.2019
  Time: 9:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${lang_id}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <title>Account</title>
    <c:import url="blocks/resources.jsp"/>
</head>
<body>

<div class="w3-top">
    <c:import url="menuBar.jsp"/>
</div>


<div class="w3-container w3-light-grey" style="padding:128px 16px">
    <c:choose>
        <c:when test="${role ne 'admin' && role ne null}">
            <div class="w3-margin-top w3-margin-bottom">
                <form method="post" action="/account" class="w3-container">
                    <input type="hidden" name="id" value="${user.getId()}">
                    <h4><fmt:message key="username"/>:</h4>
                    <input class="w3-input" style="border-radius: 15px 50px 30px 5px; outline: none"
                           value="${user.getUsername()}" name="username">
                    <h4><fmt:message key="password"/>:</h4>
                    <input type="password" class="w3-input" style="border-radius: 15px 50px 30px 5px; outline: none"
                           value="" name="password">
                    <h4><fmt:message key="confirmPassword"/>:</h4>
                    <input type="password" class="w3-input" style="border-radius: 15px 50px 30px 5px; outline: none"
                           value="" name="confirmPassword">
                    <h4>Email:</h4>
                    <input class="w3-input" style="border-radius: 15px 50px 30px 5px; outline: none"
                           value="${user.getEmail()}" name="email">
                    <h4><fmt:message key="firstname"/>:</h4>
                    <input class="w3-input" style="border-radius: 15px 50px 30px 5px; outline: none"
                           value="${user.getFirstname()}" name="firstname">
                    <h4><fmt:message key="lastname"/>:</h4>
                    <input class="w3-input" style="border-radius: 15px 50px 30px 5px; outline: none"
                           value="${user.getLastname()}"
                           name="lastname">
                    <h4><fmt:message key="address"/>:</h4>
                    <input class="w3-input" style="border-radius: 15px 50px 30px 5px; outline: none"
                           value="${user.getAddress()}" name="address">
                    <div class="w3-margin-top">
                        <button class="w3-padding-16 w3-right w3-button w3-border w3-round-medium w3-border-orange"
                                type="submit" name="command" value="updateUser">
                            <fmt:message key="save"/>
                        </button>
                    </div>
                </form>
            </div>
        </c:when>
        <c:otherwise>
            <div class="w3-margin-top w3-margin-bottom">
                <h2><fmt:message key="clients"/></h2>
                <div style="overflow-y: scroll;max-height: 500px;">
                    <table class="w3-table-all w3-card-8 w3-border w3-bordered">
                        <tbody>
                        <tr>
                            <th><fmt:message key="username"/></th>
                            <th>Email</th>
                            <th><fmt:message key="firstname"/></th>
                            <th><fmt:message key="lastname"/></th>
                            <th><fmt:message key="amountOfOrders"/></th>
                            <th><fmt:message key="role"/></th>
                            <th></th>
                            <th></th>
                        </tr>
                        <c:forEach var="userInTable" items="${usersList}">
                            <c:if test="${userInTable.getRole() ne 'admin'}">
                                <tr>
                                    <td>${userInTable.getUsername()}</td>
                                    <td>${userInTable.getEmail()}</td>
                                    <td>${userInTable.getFirstname()}</td>
                                    <td>${userInTable.getLastname()}</td>
                                    <td>${userInTable.getAmountOfOrders()}</td>
                                    <td>${userInTable.getRole()}</td>
                                    <form method="post" action="">
                                        <input type="hidden" name="user_id" value="${userInTable.getId()}">
                                        <td>
                                            <c:choose>
                                                <c:when test="${userInTable.getRole() eq 'blocked_client'}">
                                                    <button class="w3-medium w3-btn w3-white w3-border w3-round-medium w3-border-blue"
                                                            style="outline: none" type="submit" name="command"
                                                            value="blacklist">
                                                        <fmt:message key="deleteFromBlacklist"/>
                                                    </button>
                                                </c:when>
                                                <c:otherwise>
                                                    <button class="w3-medium w3-btn w3-white w3-border w3-round-medium w3-border-blue"
                                                            style="outline: none" type="submit" name="command"
                                                            value="blacklist">
                                                        <fmt:message key="addToBlacklist"/>
                                                    </button>
                                                </c:otherwise>
                                            </c:choose>
                                            </button>
                                        </td>
                                        <td>
                                            <button class="w3-medium w3-btn w3-white w3-border w3-round-medium w3-border-red"
                                                    style="outline: none" type="submit" name="command"
                                                    value="removeUser">
                                                <fmt:message key="remove"/>
                                            </button>
                                        </td>
                                    </form>
                                </tr>
                            </c:if>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="w3-margin-top">
                <h2>Goods</h2>
                <form method="post" action="/edit">
                    <button class="w3-medium w3-btn w3-white w3-border w3-round-medium w3-border-green"
                            style="outline: none" type="submit" name="command" value="editProduct">
                        <fmt:message key="add"/>
                    </button>
                </form>
                <div style="overflow-y: scroll;max-height: 600px;">
                    <table class="w3-table-all w3-card-8 w3-border w3-bordered">
                        <tbody>
                        <tr>
                            <th><fmt:message key="name"/></th>
                            <th><fmt:message key="price"/></th>
                            <th><fmt:message key="amount"/></th>
                            <th><fmt:message key="description"/></th>
                            <th><fmt:message key="image"/></th>
                            <th><fmt:message key="manufacturer"/></th>
                            <th><fmt:message key="country"/></th>
                            <th><fmt:message key="type"/></th>
                            <th></th>
                            <th></th>
                        </tr>
                        <c:forEach var="productInTable" items="${productsList}">
                            <tr>
                                <td>${productInTable.getName()}</td>
                                <td>${productInTable.getPrice()}</td>
                                <td>${productInTable.getAmount()}</td>
                                <td>${productInTable.getDescription()}</td>
                                <td>${productInTable.getImage()}</td>
                                <td>${productInTable.getManufacturer().getName()}</td>
                                <td>${productInTable.getManufacturer().getCountry()}</td>
                                <td>${productInTable.getType().getType()}</td>
                                <td>
                                    <form method="post" action="/edit">
                                        <input type="hidden" name="product_id" value="${productInTable.getId()}">
                                        <button class="w3-medium w3-btn w3-white w3-border w3-round-medium w3-border-orange"
                                                style="outline: none" type="submit" name="command"
                                                value="editProduct">
                                            <fmt:message key="edit"/>
                                        </button>
                                    </form>
                                </td>

                                <td>
                                    <form method="post" action="/account">
                                        <input type="hidden" name="product_id" value="${productInTable.getId()}">
                                        <button class="w3-medium w3-btn w3-white w3-border w3-round-medium w3-border-red"
                                                style="outline: none" type="submit" name="command"
                                                value="removeProduct">
                                            <fmt:message key="remove"/>
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<footer class="w3-center w3-black w3-padding-64">
    <c:import url="blocks/footer.jsp"/>
</footer>
</body>
</html>