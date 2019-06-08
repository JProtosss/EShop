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

<div class="w3-bar w3-white w3-card" id="myNavbar">
    <c:choose>
        <c:when test="${auth}">
            <form method="post" action="" style="display: inline;">
                <c:if test="${role ne 'admin' && role ne 'quest'}">
                    <button name="command" class="w3-button w3-bar-item fa-lg" value="account" type="submit"
                            style="padding: 19.5px;"><i class="fa fa-user" aria-hidden="true"></i>
                    </button>
                    <button name="command" class="w3-button w3-bar-item fa-lg" value="cart" type="submit"
                            style="padding: 19.5px;"><i class="fa fa-shopping-cart" aria-hidden="true"></i>
                    </button>
                </c:if>

                <button name="command" class="w3-button w3-bar-item fa-lg" value="logout" type="submit"
                        style="padding: 19.5px;"><i class="fa fa-sign-out" aria-hidden="true"></i></button>
            </form>
            <c:if test="${role ne 'admin' && role ne 'quest'}">
                <form method="post" action="" style="display: inline;">
                    <div class="w3-right w3-hide-small">
                        <button class="w3-bar-item w3-button" style="font-weight: bold" type="submit" name="command"
                                value="index">
                            HOME
                        </button>
                        <button class="w3-button w3-bar-item" type="submit" name="item" value="laptop">
                            <i class="fa">
                            </i> LAPTOPS
                        </button>
                        <button class="w3-button w3-bar-item" type="submit" name="item" value="desktop">
                            <i class="fa"></i> DESKTOP COMPUTERS
                        </button>
                        <button class="w3-button w3-bar-item" type="submit" name="item" value="tablets"><i
                                class="fa"></i>
                            IPAD&TABLETS
                        </button>
                        <button class="w3-button w3-bar-item" type="submit" name="item" value="accesories"><i
                                class="fa"></i>
                            ACCESORIES
                        </button>
                    </div>
                </form>

                <a href="javascript:void(0)" class="w3-bar-item w3-button w3-right w3-hide-large w3-hide-medium"
                   onclick="w3_open()">
                    <i class="fa fa-bars"></i>
                </a>
            </c:if>
        </c:when>
        <c:otherwise>
            <button onclick="document.getElementById('login').style.display='block'"
                    class="w3-bar-item w3-button w3-wide">
                LOGIN
            </button>
        </c:otherwise>
    </c:choose>
</div>

