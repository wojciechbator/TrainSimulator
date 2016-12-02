<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<main class="mdl-layout__content">
    <section class="mdl-layout__tab-panel is-active" id="timetableTabPanel">
        <div class="page-content">
            <div class="mdl-layout-title">Rozkład jazdy pociągów</div>
            <div class="author-table-row" id="indexTable">
                <div class="author-table-cell" id="indexTableId">
                    <div class="author-table-div">id</div>
                </div>
                <div class="author-table-cell" id="indexTableJSON">
                    <div class="author-table-div">szajs</div>
                </div>
                <div class="author-table-cell" id="indexTableTimestamp">
                    <div class="author-table-div">szajs</div>
                </div>
            </div>
            <%--FOR LOOP (ROWS)--%>
            <div class="author-table-row">
                <div class="author-table-cell">
                    <div class="author-table-div">${greeting}</div>
                </div>
                <div class="author-table-cell">
                    <div class="author-table-div">szajs</div>
                </div>
                <div class="author-table-cell">
                    <div class="author-table-div">szajs</div>
                </div>
            </div>
        </div>
    </section>
</main>