<%--
  Created by IntelliJ IDEA.
  User: Евгений
  Date: 07.06.2019
  Time: 9:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

        </c:when>
        <c:otherwise>
            <div class="w3-margin-top w3-margin-bottom">
                <h2>Clients</h2>
                <div style="overflow-y: scroll;max-height: 500px;">
                    <table class="w3-table-all w3-card-8 w3-border w3-bordered">
                        <tbody>
                        <tr>
                            <th>Username</th>
                            <th>Email</th>
                            <th>Firstname</th>
                            <th>Lastname</th>
                            <th>Role</th>
                            <th></th>
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
                                    <td>${userInTable.getRole()}</td>
                                    <form method="post" action="">
                                        <input type="hidden" name="user_id" value="${userInTable.getId()}">
                                        <td>
                                            <c:choose>
                                                <c:when test="${userInTable.getRole() eq 'blocked_client'}">
                                                    <button class="w3-medium w3-btn w3-white w3-border w3-round-medium w3-border-blue"
                                                            style="outline: none" type="submit" name="command"
                                                            value="blacklist">
                                                        Delete from backlist
                                                    </button>
                                                </c:when>
                                                <c:otherwise>
                                                    <button class="w3-medium w3-btn w3-white w3-border w3-round-medium w3-border-blue"
                                                            style="outline: none" type="submit" name="command"
                                                            value="blacklist">
                                                        Add to blacklits
                                                    </button>
                                                </c:otherwise>
                                            </c:choose>
                                            </button>
                                        </td>
                                        <td>
                                            <button class="w3-medium w3-btn w3-white w3-border w3-round-medium w3-border-red"
                                                    style="outline: none" type="submit" name="command"
                                                    value="removeUser">
                                                Remove
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
                        Add
                    </button>
                </form>
                <div style="overflow-y: scroll;max-height: 600px;">
                    <table class="w3-table-all w3-card-8 w3-border w3-bordered">
                        <tbody>
                        <tr>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Amount</th>
                            <th>Description</th>
                            <th>Image</th>
                            <th>Manufacturer</th>
                            <th>Country</th>
                            <th>Type</th>
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
                                            Edit
                                        </button>
                                    </form>
                                </td>

                                <td>
                                    <form method="post" action="/account">
                                        <input type="hidden" name="product_id" value="${productInTable.getId()}">
                                        <button class="w3-medium w3-btn w3-white w3-border w3-round-medium w3-border-red"
                                                style="outline: none" type="submit" name="command"
                                                value="removeProduct">
                                            Remove
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
