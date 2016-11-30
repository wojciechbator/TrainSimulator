<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div>
    <main class="mdl-layout__content">
        <section class="mdl-layout__tab-panel is-active" id="indexTab">
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
    </main>
</div>