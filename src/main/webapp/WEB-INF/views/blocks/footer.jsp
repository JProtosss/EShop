<%--
  Created by IntelliJ IDEA.
  User: Евгений
  Date: 20.05.2019
  Time: 1:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><script src="../../js/show.js"></script></head>
<body>
<a id="footer" onclick="toTopOfPage()" class="w3-button w3-light-grey"><i class="fa fa-arrow-up w3-margin-right"></i>To the top</a>
<div class="w3-xlarge w3-section">
    <i class="fa fa-facebook-official w3-hover-opacity"></i>
    <i class="fa fa-instagram w3-hover-opacity"></i>
    <i class="fa fa-snapchat w3-hover-opacity"></i>
    <i class="fa fa-pinterest-p w3-hover-opacity"></i>
    <i class="fa fa-twitter w3-hover-opacity"></i>
    <i class="fa fa-linkedin w3-hover-opacity"></i>
</div>
<p>Made by <a href="https://vk.com/evgenii.protasov" title="Creator" target="_blank" class="w3-hover-text-green">Evgeniy
    Protasov</a></p>
<script>
    function toTopOfPage() {
    if (window.scrollY>0) {
        window.scrollTo(0,window.scrollY-20)
        setTimeout("toTopOfPage()",10)
    }
}
</script>
</body>
</html>
