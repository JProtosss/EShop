<%--
  Created by IntelliJ IDEA.
  User: Евгений
  Date: 14.06.2019
  Time: 12:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${lang_id}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <title>Order</title>
    <c:import url="blocks/resources.jsp"/>
    <style>
        .row {
            display: -ms-flexbox; /* IE10 */
            display: flex;
            -ms-flex-wrap: wrap; /* IE10 */
            flex-wrap: wrap;
            margin: 0 -16px;
        }

        .col-25 {
            -ms-flex: 25%; /* IE10 */
            flex: 25%;
        }

        .col-50 {
            -ms-flex: 50%; /* IE10 */
            flex: 50%;
        }

        .col-75 {
            -ms-flex: 75%; /* IE10 */
            flex: 75%;
        }

        .col-25,
        .col-50,
        .col-75 {
            padding: 0 16px;
        }

        .container {
            background-color: #f2f2f2;
            padding: 5px 20px 15px 20px;
            border: 1px solid lightgrey;
            border-radius: 3px;
        }

        label {
            margin-bottom: 10px;
            display: block;
        }

        .icon-container {
            margin-bottom: 20px;
            padding: 7px 0;
            font-size: 24px;
        }

        .btn {
            background-color: #4CAF50;
            color: white;
            padding: 12px;
            margin: 10px 0;
            border: none;
            width: 100%;
            border-radius: 3px;
            cursor: pointer;
            font-size: 17px;
        }

        .btn:hover {
            background-color: #45a049;
        }

        hr {
            border: 1px solid lightgrey;
        }

        span.price {
            float: right;
            color: grey;
        }

        /* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other (also change the direction - make the "cart" column go on top) */
        @media (max-width: 800px) {
            .row {
                flex-direction: column-reverse;
            }

            .col-25 {
                margin-bottom: 20px;
            }
        }
    </style>
</head>
<body>
<div class="w3-top">
    <c:import url="menuBar.jsp"/>
</div>
<div class="w3-container" style="padding:128px 16px">
    <div class="row">
        <div class="col-75">
            <div class="container">
                <form method="post" action="/account">
                    <input type="hidden" name="product_id" value="${product.getId()}">
                    <div class="row">
                        <div class="col-50">
                            <h3>Billing Address</h3>
                            <label for="fname"><i class="fa fa-user"></i> Full Name</label>
                            <input class="w3-input" type="text" id="fname" name="firstname" placeholder="full name"
                                   value="${user.getFirstname()} ${user.getLastname()}">
                            <label for="email"><i class="fa fa-envelope"></i> Email</label>
                            <input class="w3-input" type="text" id="email" name="email" placeholder="john@example.com"
                                   value="${user.getEmail()}">
                            <label for="adr"><i class="fa fa-address-card-o"></i> Address</label>
                            <input class="w3-input" type="text" id="adr" name="address" placeholder="address"
                                   value="${user.getAddress()}">
                        </div>

                        <div class="col-50">
                            <h3>Payment</h3>
                            <label for="fname">Accepted Cards</label>
                            <div class="icon-container">
                                <i class="fa fa-cc-visa" style="color:navy;"></i>
                                <i class="fa fa-cc-amex" style="color:blue;"></i>
                                <i class="fa fa-cc-mastercard" style="color:red;"></i>
                                <i class="fa fa-cc-discover" style="color:orange;"></i>
                            </div>
                            <label for="cname">Name on Card</label>
                            <input class="w3-input" type="text" id="cname" name="cardname" placeholder="name">
                            <label for="ccnum">Credit card number</label>
                            <input class="w3-input" type="text" id="ccnum" name="cardnumber" placeholder="number">
                            <label for="expmonth">Exp Month</label>
                            <input class="w3-input" type="text" id="expmonth" name="expmonth" placeholder="expmonth">
                            <div class="row">
                                <div class="col-50">
                                    <label for="expyear">Exp Year</label>
                                    <input class="w3-input" type="text" id="expyear" name="expyear"
                                           placeholder="expyear">
                                </div>
                                <div class="col-50">
                                    <label for="cvv">CVV</label>
                                    <input class="w3-input" type="text" id="cvv" name="cvv" placeholder="cvv">
                                </div>
                            </div>
                        </div>

                    </div>
                    <button type="submit" name="command" value="makeOrder" class="btn">Confirm order</button>
                </form>
                <form method="post" action="/${product.getType().getType()}">
                    <input type="hidden" name="product" value="${product.getType().getType()}">
                    <button type="submit" name="command" value="product" class="btn w3-red">Cancel</button>
                </form>
            </div>
        </div>
        <div class="col-25">
            <div class="container">
                <h4>Cart <span class="price" style="color:black"><i class="fa fa-shopping-cart"></i> <b>1</b></span>
                </h4>
                <p>Product Name<span>${product.getName()}</span></p>
                <p>Price <span class="price">${product.getPrice()}<i class="fa fa-dollar"></i></span></p>
                <hr>
                <p>Total <span class="price" style="color:black"><b>${product.getPrice()}<i class="fa fa-dollar"></i></b></span></p>
            </div>
        </div>
    </div>
</div>

<footer class="w3-center w3-black w3-padding-64">
    <c:import url="blocks/footer.jsp"/>
</footer>
</body>
</html>
