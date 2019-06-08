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
            <div class="w3-margin-top">
                <h2>Clients</h2>
                <table class="w3-table-all w3-card-8 w3-border w3-bordered" style="overflow: auto;">
                    <tr style="text-align: center">
                        <th>Username</th>
                        <th>Email</th>
                        <th>Firstname</th>
                        <th>Lastname</th>
                        <th>Role</th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
                    <c:forEach var="user" items="${usersList}">
                        <tr style="text-align: center;">
                            <td>${user.getUsername()}</td>
                            <td>${user.getEmail()}</td>
                            <td>${user.getFirstname()}</td>
                            <td>${user.getLastname()}</td>
                            <td>${user.getRole()}</td>
                            <form method="post">
                                <td>
                                    <button class="w3-medium w3-btn w3-white w3-border w3-round-medium w3-border-orange"
                                            style="outline: none" type="submit" name="edit" value="${user.getId()}">Edit
                                    </button>
                                </td>
                                <td>
                                    <button class="w3-medium w3-btn w3-white w3-border w3-round-medium w3-border-blue"
                                            style="outline: none" type="submit" name="blacklist"
                                            value="${user.getId()}">Add to Blacklist
                                    </button>
                                </td>
                                <td>
                                    <button class="w3-medium w3-btn w3-white w3-border w3-round-medium w3-border-red"
                                            style="outline: none" type="submit" name="delete" value="${user.getId()}">
                                        Delete
                                    </button>
                                </td>
                            </form>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<footer class="w3-center w3-black w3-padding-64">
    <c:import url="blocks/footer.jsp"/>
</footer>
</body>
</html>
