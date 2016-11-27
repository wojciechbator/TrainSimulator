<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%--
  Created by IntelliJ IDEA.
  User: mitron-wojtek
  Date: 19.11.16
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/commonTaglibs.jsp" %>
<head>
    <title>Show trains</title>
</head>
<body>
<header id="header">
    <tiles:insertAttribute name="header"/>
</header>

<section id="sidemenu">
    <tiles:insertAttribute name="title"/>
</section>

<section id="site-content">
    <tiles:insertAttribute name="body"/>
</section>

<footer id="footer">
    <tiles:insertAttribute name="footer"/>
</footer>
<c:forEach items="${trainsList}">JEBAJ SIE!</c:forEach>
Rozkład
</body>
</html>
