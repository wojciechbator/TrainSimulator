<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.2.1/material.indigo-pink.min.css">
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:300,400,500,700" type="text/css">
    <link rel="stylesheet" href="../../resources/css/style.css" type="text/css">
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <script data-require="angular.js@1.2.15" data-semver="1.2.15" src="//code.angularjs.org/1.2.15/angular.js"></script>
    <script defer src="https://code.getmdl.io/1.2.1/material.min.js"></script>
</head>
<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header
            mdl-layout--fixed-tabs">
    <header class="mdl-layout__header">
        <div class="mdl-layout__header-row">
            <!-- Title -->
            <span class="mdl-layout-title">Spring template</span>
        </div>
        <!-- Tabs -->
        <div class="mdl-layout__tab-bar mdl-js-ripple-effect">
            <a href="#main-section" class="mdl-layout__tab is-active" id="whatever1ID" style="text-align: center">Whatever
                1</a>
            <a href="#fixed-tab-2" class="mdl-layout__tab" id="whatever2ID" style="text-align: center">Whatever
                2</a>
            <a href="#fixed-tab-3" class="mdl-layout__tab" id="whatever3ID" style="text-align: center">Whatever
                3</a>
        </div>
    </header>
    <div class="mdl-layout__drawer">
        <span class="mdl-layout-title">Spring template menu</span>
        <nav class="mdl-navigation">
            <a class="mdl-navigation__link" href="">Whatever 1</a>
            <a class="mdl-navigation__link" href="">Whatever 2</a>
            <a class="mdl-navigation__link" href="">Whatever 3</a>
        </nav>
    </div>
</div>
</body>
</html>