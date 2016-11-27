<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../common/commonTaglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Index</title>
</head>
<body>
<header id="header">
    <tiles:insertAttribute name="header"/>
</header>

<section id="title">
    <tiles:insertAttribute name="title"/>
</section>

<section id="site-content">
    <tiles:insertAttribute name="body"/>
</section>

<footer id="footer">
    <tiles:insertAttribute name="footer"/>
</footer>
<div>
    <main class="mdl-layout__content">
        <section class="mdl-layout__tab-panel is-active" id="main-section">
            <div class="page-content">
                <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp" id="indexTable">
                    <thead>
                    <tr>
                        <th class="mdl-data-table__cell--non-numeric" id="indexTableId"
                            style="width: 25%; text-align: center">
                            <div>id</div>
                        </th>
                        <th class="mdl-data-table__cell--non-numeric" id="indexTableJSON"
                            style="width: 75%; text-align: center;">
                            <div>szajs</div>
                        </th>
                        <th class="mdl-data-table__cell--non-numeric" id="indexTableTimestamp"
                            style="width: 25%; text-align: center;">
                            <div>szajs</div>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr class="testHistoryRow">
                        <td class="mdl-data-table__cell--non-numeric ID" style="text-align: center">${greeting}</td>
                        <td class="mdl-data-table__cell--non-numeric json" style="text-align: center">szajs</td>
                        <td class="mdl-data-table__cell--non-numeric timest" style="text-align: center">szajs</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </section>
        <section class="mdl-layout__tab-panel" id="fixed-tab-2">
            <div class="page-content"><!-- Your content goes here --></div>
        </section>
        <section class="mdl-layout__tab-panel" id="fixed-tab-3">
            <div class="page-content"><!-- Your content goes here --></div>
        </section>
    </main>
</div>
</body>
</html>