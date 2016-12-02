<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <title><tiles:getAsString name="title"/></title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.2.1/material.indigo-pink.min.css">
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:300,400,500,700" type="text/css">
    <link rel="stylesheet" href="../../resources/css/style.css" type="text/css">
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script data-require="angular.js@1.2.15" data-semver="1.2.15" src="//code.angularjs.org/1.2.15/angular.js"></script>
    <script defer src="https://code.getmdl.io/1.2.1/material.min.js"></script>
</head>
<body>
<tilesx:useAttribute name="current"/>
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header
            mdl-layout--fixed-tabs">
    <header class="mdl-layout__header">
        <div class="mdl-layout__header-row">
            <!-- Title -->
            <span class="mdl-layout-title">Spring template</span>
        </div>
        <!-- Tabs -->
        <div class="mdl-layout__tab-bar mdl-js-ripple-effect">
            <a href="<spring:url value="/" />" class="mdl-layout__tab is-active" id="indexTabPanel" style="text-align: center">Strona główna</a>
            <a href="<spring:url value="/timetable" />" class="mdl-layout__tab" id="timetableTabPanel" style="text-align: center">Rozkład jazdy</a>
            <a href="<spring:url value="/generator" /> " class="mdl-layout__tab" id="generatorTabPanel" style="text-align: center">Generator pociagów</a>
        </div>
    </header>
    <div class="mdl-layout__drawer">
        <span class="mdl-layout-title">Spring template menu</span>
        <nav class="mdl-navigation">
            <a class="mdl-navigation__link" href="<spring:url value="/" />">Indeks</a>
            <a class="mdl-navigation__link" href="<spring:url value="/timetable" />">Rozkład</a>
            <a class="mdl-navigation__link" href="<spring:url value="/generator" />">Generator</a>
        </nav>
    </div>
    <tiles:insertAttribute name="body"/>
    <br><br>
    <div style="text-align: center;">
        <tiles:insertAttribute name="footer"/>
    </div>
</div>
</body>
</html>