<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../basicLayout/commonTaglibs.jsp" %>
<ui:insert name="content">
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>
    <head>
        <title>Index</title>
    </head>
    <body>
    <!-- Simple header with scrollable tabs. -->
    <!-- Simple header with fixed tabs. -->
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
        <main class="mdl-layout__content">
            <section class="mdl-layout__tab-panel is-active" id="main-section">
                <div class="page-content">
                    <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp" id="testHistoryTable">
                        <thead>
                        <tr>
                            <th class="mdl-data-table__cell--non-numeric" id="testHistoryId"
                                style="width: 25%; text-align: center">
                                <div>id</div>
                            </th>
                            <th class="mdl-data-table__cell--non-numeric" id="testHistoryJSON"
                                style="width: 75%; text-align: center;">
                                <div>szajs</div>
                            </th>
                            <th class="mdl-data-table__cell--non-numeric" id="testHistoryTimestamp"
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
</ui:insert>