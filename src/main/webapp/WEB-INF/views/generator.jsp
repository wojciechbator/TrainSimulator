<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<head>
    <noscript>
        Zainstaluj wtyczkę JavaScript, żeby oglądać dynamiczną zawartość tej strony!
    </noscript>
</head>
<section class="mdl-layout__tab-panel" id="generatorTabPanel">
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
    </div>
    <script type="text/javascript" src="../../resources/js/generatorUtilities.js"></script>
</section>