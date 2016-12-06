<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<main class="mdl-layout__content">
    <section class="mdl-layout__tab-panel is-active" id="timetableTabPanel">
        <div class="page-content">
            <table class="mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp">
                <thead>
                <tr>
                    <th class="mdl-data-table__cell--non-numeric">ID pociągu</th>
                    <th>ID trasy</th>
                    <th>Bieżąca stacja</th>
                </tr>
                </thead>
                <c:forEach items="${trainsList}" var="train">
                    <c:if test="${train == null}">
                        <tbody>
                        Na razie nie ma tu żadnych pociągów
                        </tbody>
                    </c:if>
                    <tbody>
                    <tr>
                        <td class="mdl-data-table__cell--non-numeric">${train.id}</td>
                        <td class="mdl-data-table__cell--non-numeric">${train.route.id}</td>
                        <td>${train.station.name}</td>
                    </tr>
                    </tbody>
                </c:forEach>
            </table>
        </div>
    </section>
</main>