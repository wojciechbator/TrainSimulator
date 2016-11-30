<%@ taglib prefix="spring" uri="http://tiles.apache.org/tags-tiles" %>
<head>
    <script type="text/javascript" src="../../resources/js/generateTimetable.js"></script>
    <noscript>
        Sorry... Install JavaScript to have dynamic content working on this site!
    </noscript>
</head>
<section class="mdl-layout__tab-panel" id="generatorTab">
    <div class="page-content">
        <button class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored"
        ${generateTimetable} >
            Generate trains
        </button>
    </div>
</section>