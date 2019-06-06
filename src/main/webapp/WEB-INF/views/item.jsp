<%--
  Created by IntelliJ IDEA.
  User: Евгений
  Date: 20.05.2019
  Time: 1:53 PM
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
<div class="w3-top">
    <c:import url="menuBar.jsp"/>
</div>
<div class="w3-container" style="padding:128px 16px" id="laptops">
    <h3 class="w3-center">Laptops</h3>
    <p class="w3-center w3-large">Actually what you need</p>
    <div class="w3-center w3-grayscale w3-padding" style="margin-top:25px">
        <div class="w3-card w3-border">
            <input class="w3-input" type="text" id="search" placeholder="Search...">
        </div>
    </div>
    <div class="w3-row-padding w3-grayscale" style="margin-top:15px">
        <div class="w3-col l3 m6 w3-margin-bottom">
            <div class=" w3-card">
                <img src="../../images/xps.jpg" style="width:100%">
                <div class="w3-container">
                    <h3>Name</h3>
                    <p class="w3-opacity">Model</p>
                    <p>Description</p>
                    <p><button class="w3-button w3-light-grey w3-block"><i class="fa"></i> Buy</button></p>
                </div>
            </div>
        </div>
        <div class="w3-col l3 m6 w3-margin-bottom">
            <div class=" w3-card">
                <img src="../../images/xps.jpg" style="width:100%">
                <div class="w3-container">
                    <h3>Name</h3>
                    <p class="w3-opacity">Model</p>
                    <p>Description</p>
                    <p><button class="w3-button w3-light-grey w3-block"><i class="fa"></i> Buy</button></p>
                </div>
            </div>
        </div>
        <div class="w3-col l3 m6 w3-margin-bottom">
            <div class=" w3-card">
                <img src="../../images/xps.jpg" style="width:100%">
                <div class="w3-container">
                    <h3>Name</h3>
                    <p class="w3-opacity">Model</p>
                    <p>Description</p>
                    <p><button class="w3-button w3-light-grey w3-block"><i class="fa"></i> Buy</button></p>
                </div>
            </div>
        </div>
        <div class="w3-col l3 m6 w3-margin-bottom">
            <div class=" w3-card">
                <img src="../../images/xps.jpg" style="width:100%">
                <div class="w3-container">
                    <h3>Name</h3>
                    <p class="w3-opacity">Model</p>
                    <p>Description</p>
                    <p><button class="w3-button w3-light-grey w3-block"><i class="fa"></i> Buy</button></p>
                </div>
            </div>
        </div>
        <div class="w3-col l3 m6 w3-margin-bottom">
            <div class=" w3-card">
                <img src="../../images/xps.jpg" style="width:100%">
                <div class="w3-container">
                    <h3>Name</h3>
                    <p class="w3-opacity">Model</p>
                    <p>Description</p>
                    <p><button class="w3-button w3-light-grey w3-block"><i class="fa"></i> Buy</button></p>
                </div>
            </div>
        </div>
    </div>
</div>

<footer class="w3-center w3-black w3-padding-64">
    <c:import url="blocks/footer.jsp"/>
</footer>

</body>
</html>
