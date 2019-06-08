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
    <script src="../js/show.js"></script>

    <link rel="stylesheet" href="../../style/style.css">
    <link rel="stylesheet" href="../../style/startPage.css">
    <link rel="stylesheet" href="../../style/font.css">
    <link rel="stylesheet" href="../../style/font-awesome.css">
</head>
<body>

<div class="w3-top">
    <c:import url="menuBar.jsp"/>
</div>


<div class="w3-container w3-light-grey" style="padding:128px 16px">
    <c:choose>
        <c:when test="${user.getRole() ne 'admin'}">
            <div class="w3">
                <div class="w3-container w3-light-grey" id="contact">
                    <h3 class="w3-center">Account</h3>
                    <form action="">
                        <p>
                            <label><b>Username</b></label>
                            <input class="w3-input w3-border" type="text" placeholder="New Username" required
                                   name="usrname"></p>
                        <p>
                            <label><b>Password</b></label>
                            <input class="w3-input w3-border" type="password" placeholder="Current Password" required
                                   name="password"></p>
                        <p><input class="w3-input w3-border" type="password" placeholder="New Password" required
                                  name="Subject"></p>
                        <p>
                            <label><b>E-mail</b></label>
                            <input class="w3-input w3-border" type="Email" placeholder="New Email" required
                                   name="Email"></p>
                        <p>
                            <button class="w3-button w3-black" type="submit">
                                <i class="fa fa-pencil-alt"></i> Save changes
                            </button>
                        </p>
                    </form>
                </div>
            </div>
            <div class=" w3-margin-top">
                <h2>Your orders</h2>
                <table class="w3-table-all w3-card-8 " style="overflow: auto;">
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Points</th>
                    </tr>
                    <tr>
                        <td>Jill</td>
                        <td>Smith</td>
                        <td>50</td>
                    </tr>
                    <tr>
                        <td>Eve</td>
                        <td>Jackson</td>
                        <td>94</td>
                    </tr>
                    <tr>
                        <td>Adam</td>
                        <td>Johnson</td>
                        <td>67</td>
                    </tr>
                    <tr>
                        <td>Adam</td>
                        <td>Johnson</td>
                        <td>67</td>
                    </tr>
                    <tr>
                        <td>Adam</td>
                        <td>Johnson</td>
                        <td>67</td>
                    </tr>
                </table>
            </div>
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
                            <tr>
                                <td>${userInTable.getUsername()}</td>
                                <td>${userInTable.getEmail()}</td>
                                <td>${userInTable.getFirstname()}</td>
                                <td>${userInTable.getLastname()}</td>
                                <td>${userInTable.getRole()}</td>
                                <form method="post">
                                    <td>
                                        <button class="w3-medium w3-btn w3-white w3-border w3-round-medium w3-border-orange"
                                                style="outline: none" type="submit" name="edit"
                                                value="${userInTable.getId()}">Edit
                                        </button>
                                    </td>
                                    <c:if test="${userInTable.getRole() ne 'admin'}">
                                        <td>
                                            <button class="w3-medium w3-btn w3-white w3-border w3-round-medium w3-border-blue"
                                                    style="outline: none" type="submit" name="blacklist"
                                                    value="${userInTable.getId()}">
                                                <c:choose>
                                                    <c:when test="${userInTable.getRole() eq 'blocked_client'}">Delete from backlist</c:when>
                                                    <c:otherwise>Add to Blacklist
                                                    </c:otherwise>
                                                </c:choose>
                                            </button>
                                        </td>
                                        <td>
                                            <button class="w3-medium w3-btn w3-white w3-border w3-round-medium w3-border-red"
                                                    style="outline: none" type="submit" name="delete"
                                                    value="${userInTable.getId()}">
                                                Remove
                                            </button>
                                        </td>
                                    </c:if>
                                </form>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="w3-margin-top">
                <h2>Goods</h2>
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
                                <td>${productInTable.getManufacturerName()}</td>
                                <td>${productInTable.getManufacturerCountry()}</td>
                                <td>${productInTable.getCategory()}</td>
                                <td>
                                    <button class="w3-medium w3-btn w3-white w3-border w3-round-medium w3-border-orange"
                                            style="outline: none" type="submit" name="delete"
                                            value="${productInTable.getId()}">
                                        Add
                                    </button>
                                </td>
                                <c:if test="${productInTable.getAmount() ne 0}">
                                    <td>
                                        <button class="w3-medium w3-btn w3-white w3-border w3-round-medium w3-border-blue"
                                                style="outline: none" type="submit" name="delete"
                                                value="${productInTable.getId()}">
                                            Take
                                        </button>
                                    </td>
                                </c:if>
                                <td>
                                    <button class="w3-medium w3-btn w3-white w3-border w3-round-medium w3-border-yellow"
                                            style="outline: none" type="submit" name="delete"
                                            value="${productInTable.getId()}">
                                        Edit
                                    </button>
                                </td>
                                <td>
                                    <button class="w3-medium w3-btn w3-white w3-border w3-round-medium w3-border-red"
                                            style="outline: none" type="submit" name="delete"
                                            value="${productInTable.getId()}">
                                        Remove
                                    </button>
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
