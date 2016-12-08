<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="../oldCommonForBackup/commonTaglibs.jsp"%>
<head>
    <noscript>
        Zainstaluj wtyczkę JavaScript, żeby oglądać dynamiczną zawartość tej strony!
    </noscript>
</head>
<section class="mdl-layout__tab-panel is-active" id="generatorTabPanel">
    <div class="page-content">
        <a href="<spring:url value="/adminPanel/generate"/>" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored" id="generateTrainsButton">
            Wygeneruj pociągi
        </a>
        <a href="<spring:url value="/adminPanel/clearTimetable"/> " class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored" id="clearTrainsButton">
            Wyczyść pociągi
        </a>
        <a href="<spring:url value="/adminPanel/clearLogs"/> " class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored" id="clearLogsButton">
            Wyczyść logi
        </a>
        <a href="<spring:url value="/adminPanel/runSimulation"/> " class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored" id="runSimulationButton">
            Włącz symulację
        </a>
        <a href="<spring:url value="/adminPanel/stopSimulation"/> " class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored" id="stopSimulationButton">
            Wyłącz symulację
        </a>
    </div>
    <script type="text/javascript" src="../../../resources/js/generatorUtilities.js"></script>
</section>