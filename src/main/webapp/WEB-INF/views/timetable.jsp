<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<main class="mdl-layout__content">
    <section class="mdl-layout__tab-panel is-active" id="timetableTabPanel">
        <div class="page-content">
            <div class="mdl-layout-title">Rozkład jazdy pociągów</div>
            <div class="author-table-row" id="indexTable">
                <div class="author-table-cell" id="indexTableId">
                    <div>id</div>
                </div>
                <div class="author-table-cell" id="indexTableJSON">
                    <div>szajs</div>
                </div>
                <div class="author-table-cell" id="indexTableTimestamp">
                    <div>szajs</div>
                </div>
            </div>
            <%--FOR LOOP (ROWS)--%>
            <c:forEach items="${trainsList}" var="train">
            <div class="author-table-row">
                <div class="author-table-cell">
                    <div>jakies dane</div>
                </div>
                <div class="author-table-cell">
                    <div>szajs</div>
                </div>
                <div class="author-table-cell">
                    <div>szajs</div>
                </div>
            </div>
            </c:forEach>
        </div>
    </section>
</main>