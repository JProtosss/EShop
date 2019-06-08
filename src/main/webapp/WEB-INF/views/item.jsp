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
    <c:import url="blocks/resources.jsp"/>
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
            <input class="w3-input" type="text" id="Search" onkeyup="searchItem()" placeholder="Search...">
        </div>
    </div>
    <div class="w3-row-padding w3-grayscale" style="margin-top:15px">
        <div class="w3-col l3 m6 w3-margin-bottom target">
            <div class=" w3-card">
                <img src="../../images/xps.jpg" style="width:100%">
                <div class="w3-container">
                    <h3>Apple</h3>
                    <p class="w3-opacity">Model</p>
                    <p>Description</p>
                    <p>
                        <button class="w3-button w3-light-grey w3-block"><i class="fa"></i> Add to cart</button>
                    </p>
                </div>
            </div>
        </div>
        <div class="w3-col l3 m6 w3-margin-bottom target">
            <div class=" w3-card">
                <img src="../../images/xps.jpg" style="width:100%">
                <div class="w3-container">
                    <h3>Lenovo</h3>
                    <p class="w3-opacity">Model</p>
                    <p>Description</p>
                    <p>
                        <button class="w3-button w3-light-grey w3-block"><i class="fa"></i> Add to cart</button>
                    </p>
                </div>
            </div>
        </div>
        <div class="w3-col l3 m6 w3-margin-bottom target">
            <div class=" w3-card">
                <img src="../../images/xps.jpg" style="width:100%">
                <div class="w3-container">
                    <h3>Samsung</h3>
                    <p class="w3-opacity">Model</p>
                    <p>Description</p>
                    <p>
                        <button class="w3-button w3-light-grey w3-block"><i class="fa"></i> Add to cart</button>
                    </p>
                </div>
            </div>
        </div>
        <div class="w3-col l3 m6 w3-margin-bottom target">
            <div class=" w3-card">
                <img src="../../images/xps.jpg" style="width:100%">
                <div class="w3-container">
                    <h3>Xiaomi</h3>
                    <p class="w3-opacity">Model</p>
                    <p>Description</p>
                    <p>
                        <button class="w3-button w3-light-grey w3-block"><i class="fa"></i> Add to cart</button>
                    </p>
                </div>
            </div>
        </div>
        <div class="w3-col l3 m6 w3-margin-bottom target">
            <div class=" w3-card">
                <img src="../../images/xps.jpg" style="width:100%">
                <div class="w3-container">
                    <h3>Dell</h3>
                    <p class="w3-opacity">Model</p>
                    <p>Description</p>
                    <p>
                        <button class="w3-button w3-light-grey w3-block"><i class="fa"></i> Add to cart</button>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>

<footer class="w3-center w3-black w3-padding-64">
    <c:import url="blocks/footer.jsp"/>
</footer>
</body>
<script>
    function searchItem() {
        var input = document.getElementById("Search");
        var filter = input.value.toLowerCase();
        var nodes = document.getElementsByClassName('target');
        for (var i = 0; i < nodes.length; i++) {
            if (nodes[i].innerText.toLowerCase().includes(filter)) {
                nodes[i].style.display = "block";
            } else {
                nodes[i].style.display = "none";
            }
        }
    }
</script>
</html>
