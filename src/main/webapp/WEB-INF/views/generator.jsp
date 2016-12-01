<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<head>
    <noscript>
        Sorry... Install JavaScript to have dynamic content working on this site!
    </noscript>
</head>
<section class="mdl-layout__tab-panel" id="generatorTab">
    <div class="page-content">
        <a href="<spring:url value="/generator/generateTrains"/>" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored" id="generateTrainsButton">
            Generate trains
        </a>
    </div>
    <script type="text/javascript" src="../../resources/js/generatorUtilities.js"></script>
</section>