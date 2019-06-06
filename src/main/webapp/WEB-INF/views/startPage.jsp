<%--
  Created by IntelliJ IDEA.
  User: Евгений
  Date: 19.05.2019
  Time: 4:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>EShop</title>
    <meta http-equiv="Content-Language" content="en"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script src="../js/show.js"></script>


    <link rel="stylesheet" href="../../style/style.css">
    <link rel="stylesheet" href="../../style/startPage.css">
    <link rel="stylesheet" href="../../style/font.css">
    <link rel="stylesheet" href="../../style/font-awesome.css">
</head>

<body>
<!-- Navbar (sit on top) -->

<c:import url="menuBar.jsp"/>

<div id="login" class="w3-modal" style="display:none"><c:import url="authorization/login.jsp"/></div>
<div id="registration" class="w3-modal" style="display:none"><c:import url="authorization/registration.jsp"/></div>
<div id="recover" class="w3-modal" style="display:none"><c:import url="authorization/recover.jsp"/></div>

<!-- Sidebar on small screens when clicking the menu icon -->
<nav class="w3-sidebar w3-bar-block w3-black w3-card w3-animate-left w3-hide-medium w3-hide-large" style="display:none"
     id="mySidebar">
    <a href="javascript:void(0)" onclick="w3_close()" class="w3-bar-item w3-button w3-large w3-padding-16">Close ×</a>
</nav>
<!-- Header with full-height image -->
<header class="bgimg-1 w3-display-container w3-grayscale-min" id="home">
    <div class="w3-display-left w3-text-white" style="padding:48px">
        <span class="w3-jumbo w3-hide-small">Make your choice</span><br>
        <span class="w3-xxlarge w3-hide-large w3-hide-medium">Make your choice</span><br>
        <span class="w3-large">Dont to be scammed.</span>
        <p>
            <button class="w3-button w3-white w3-padding-large w3-large w3-margin-top w3-opacity w3-hover-opacity-off">
                Start shoping
            </button>
        </p>
    </div>
    <div class="w3-display-bottomleft w3-text-grey w3-large" style="padding:24px 48px">
        <i class="fa fa-facebook-official w3-hover-opacity"></i>
        <i class="fa fa-instagram w3-hover-opacity"></i>
        <i class="fa fa-snapchat w3-hover-opacity"></i>
        <i class="fa fa-pinterest-p w3-hover-opacity"></i>
        <i class="fa fa-twitter w3-hover-opacity"></i>
        <i class="fa fa-linkedin w3-hover-opacity"></i>
    </div>
</header>
<!-- About Section -->
<!-- Contact Section -->
<div class="w3-container w3-light-grey" style="padding:128px 16px" id="contact">
    <h3 class="w3-center">CONTACT</h3>
    <p class="w3-center w3-large">Lets get in touch. Send us a message:</p>
    <div style="margin-top:48px">
        <p><i class="fa fa-map-marker fa-fw w3-xxlarge w3-margin-right"></i> Grodno, BY</p>
        <p><i class="fa fa-phone fa-fw w3-xxlarge w3-margin-right"></i> Phone: +375 33 673-42-25</p>
        <p><i class="fa fa-envelope fa-fw w3-xxlarge w3-margin-right"> </i> Email: jprotossswork@gmail.com</p>
        <br>
        <form method="" target="_blank">
            <p><input class="w3-input w3-border" type="text" placeholder="Name" required name="Name"></p>
            <p><input class="w3-input w3-border" type="text" placeholder="Email" required name="Email"></p>
            <p><input class="w3-input w3-border" type="text" placeholder="Subject" required name="Subject"></p>
            <p><input class="w3-input w3-border" type="text" placeholder="Message" required name="Message"></p>
            <p>
                <button class="w3-button w3-black w3-right" type="submit">
                    SEND MESSAGE <i class="fa fa-paper-plane"></i>
                </button>
            </p>
        </form>
    </div>
</div>
<!-- Footer -->
<footer class="w3-center w3-black w3-padding-64">
    <c:import url="blocks/footer.jsp"/>
</footer>
</body>
<script>
    var mySidebar = document.getElementById("mySidebar");


    function w3_open() {
        if (mySidebar.style.display === 'block') {
            mySidebar.style.display = 'none';
        } else {
            mySidebar.style.display = 'block';
        }
    }

    function w3_close() {
        mySidebar.style.display = "none";
    }
</script>
</html>
