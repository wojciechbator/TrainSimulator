<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="../oldCommonForBackup/commonTaglibs.jsp"%>

<main class="mdl-layout__content">
    <section class="mdl-layout__tab-panel is-active" id="timetableTabPanel">
        <div class="page-content">
            <table class="mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp">
                <thead>
                <tr>
                    <th class="mdl-data-table__cell--non-numeric">ID pociągu</th>
                    <th>Nazwa pociągu</th>
                    <th>ID trasy</th>
                    <th>Bieżąca stacja</th>
                    <th>Stan pociągu</th>
                    <th>Godzina odjazdu</th>
                </tr>
                </thead>
                <c:forEach items="${trainsList}" var="train">
                    <c:if test="${train != null}">
                        <tbody>
                        <tr>
                            <td class="mdl-data-table__cell--non-numeric">${train.id}</td>
                            <td>${train.name}</td>
                            <td class="mdl-data-table__cell--non-numeric">${train.route.id}</td>
                            <td>${train.station.name}</td>
                            <td>${train.state}</td>
                            <td>${train.timetable.get(0).departureTime}</td>
                        </tr>
                        </tbody>
                    </c:if>
                </c:forEach>
            </table>
        </div>
    </section>
</main>