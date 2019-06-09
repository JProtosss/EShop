<%--
  Created by IntelliJ IDEA.
  User: Евгений
  Date: 09.06.2019
  Time: 9:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Change</title>
    <c:import url="blocks/resources.jsp"/>
</head>
<body>
<div class="w3-top">
    <c:import url="menuBar.jsp"/>
</div>

<div class="w3-container w3-light-grey" style="padding:128px 16px">
    <div class="w3-margin-top w3-margin-bottom">

        <c:choose>
        <c:when test="${userForUpdate eq 'null'}">
        <form method="post" action="" class="w3-container">
            <h4>Name:</h4>
            <input class="w3-input" style="border-radius: 15px 50px 30px 5px; outline: none"
                   value="<c:if test="${productAdd eq null}">${product.getName()}</c:if>" name="productName">
            <h4>Price:</h4>
            <input class="w3-input" style="border-radius: 15px 50px 30px 5px; outline: none"
                   value="<c:if test="${productAdd eq null}">${product.getPrice()}</c:if>" name="productPrice">
            <h4>Amount:</h4>
            <input class="w3-input" style="border-radius: 15px 50px 30px 5px; outline: none"
                   value="<c:if test="${productAdd eq null}">${product.getAmount()}</c:if>" name="productAmount">
            <h4>Description:</h4>
            <input class="w3-input" style="border-radius: 15px 50px 30px 5px; outline: none"
                   value="<c:if test="${productAdd eq null}">${product.getDescription()}</c:if>" name="productDescription">
            <h4>Image:</h4>
            <input class="w3-input" style="border-radius: 15px 50px 30px 5px; outline: none"
                   value="<c:if test="${productAdd eq null}">${product.getImage()}</c:if>" name="productImage">
            <h4>Manufacturer:</h4>
            <select class="w3-input" style="border-radius: 15px 50px 30px 5px; outline: none" name="manufacturerName">
                <c:forEach var="manufacturer" items="${manufacturers}">
                    <c:choose>
                        <c:when test="${productAdd eq null}">
                            <c:choose>
                                <c:when test="${product.getManufacturer().getName() eq manufacturer.getName()}">
                                    <option selected="selected"
                                            value="${manufacturer.getName()}">${manufacturer.getName()}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${manufacturer.getName()}">${manufacturer.getName()}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <option value="${manufacturer.getName()}">${manufacturer.getName()}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            <h4>Type:</h4>
            <select class="w3-input" style="border-radius: 15px 50px 30px 5px; outline: none" name="productType">
                <c:forEach var="type" items="${types}">
                    <c:choose>
                        <c:when test="${productAdd eq null}">
                            <c:choose>
                                <c:when test="${product.getType().getType() eq type.getType()}">
                                    <option selected="selected" value="${type.getType()}">${type.getType()}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${type.getType()}">${type.getType()}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <option value="${type.getType()}">${type.getType()}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            <span id="option-container" style="visibility: hidden; position:absolute;"></span>
            <div class="w3-margin-top">
                <button class="w3-padding-16 w3-left w3-button w3-border w3-round-medium w3-border-red"
                        type="submit" name="command" value="account">
                    Cancel <c:if test="${productAdd eq null}">changes</c:if>
                </button>
                <button class="w3-padding-16 w3-right w3-button w3-border w3-round-medium w3-border-orange"
                        type="submit" name="command" value="updateProduct">
                    Save <c:if test="${productAdd eq null}">changes</c:if>
                </button>
            </div>
            </c:when>
            <c:otherwise>
                <h4>oops</h4>
            </c:otherwise>
            </c:choose>

        </form>
    </div>
</div>

<footer class="w3-center w3-black w3-padding-64">
    <c:import url="blocks/footer.jsp"/>
</footer>
</body>
</html>