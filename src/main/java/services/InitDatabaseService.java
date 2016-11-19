package services;

import models.EventLog;
import models.GeneratorParameter;
import models.Route;
import models.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Transactional
@Service
public class InitDatabaseService {
    private final EventLogService eventLogService;
    private final StationService stationService;
    private final TimetableGeneratorService timetableGeneratorService;
    private final GeneratorParametersService generatorParametersService;

    @Autowired
    public InitDatabaseService(EventLogService eventLogService, StationService stationService,
                               TimetableGeneratorService timetableGeneratorService, GeneratorParametersService generatorParametersService) {
        this.eventLogService = eventLogService;
        this.stationService = stationService;
        this.timetableGeneratorService = timetableGeneratorService;
        this.generatorParametersService = generatorParametersService;
    }

    @PostConstruct
    public void init() {
        eventLogService.clearEvents();
        EventLog eventLog = new EventLog();
        eventLog.setId(0);
        eventLog.setComment("A new session started!");
        eventLog.setStationName("All stations active");
        eventLog.setTimestamp(new Date());
        eventLog.setType("INFO");
        eventLogService.saveEvent(eventLog);
        GeneratorParameter onStationStopTime = new GeneratorParameter();
        onStationStopTime.setParameterName("on_station_stop_time");
        onStationStopTime.setParameterValue("60");
        generatorParametersService.saveGeneratorParameters(onStationStopTime);
        GeneratorParameter departureFrequency = new GeneratorParameter();
        departureFrequency.setParameterName("departure_frequency");
        departureFrequency.setParameterValue("300");
        generatorParametersService.saveGeneratorParameters(departureFrequency);
        GeneratorParameter periodBetweenStation = new GeneratorParameter();
        periodBetweenStation.setParameterName("period_between_station");
        periodBetweenStation.setParameterValue("300");
        generatorParametersService.saveGeneratorParameters(periodBetweenStation);
        GeneratorParameter passengersCount = new GeneratorParameter();
        passengersCount.setParameterName("passengers_count");
        passengersCount.setParameterValue("50");
        generatorParametersService.saveGeneratorParameters(passengersCount);
        //Prepared infrastructure for train simulator
        Station wroclaw = new Station();
        wroclaw.setName("Wroclaw");
        stationService.saveStation(wroclaw);
        Station poznan = new Station();
        poznan.setName("Poznan");
        stationService.saveStation(poznan);
        Station szczecin = new Station();
        szczecin.setName("Szczecin");
        stationService.saveStation(szczecin);
        Station krakow = new Station();
        krakow.setName("Krakow");
        stationService.saveStation(krakow);
        Station warszawa = new Station();
        warszawa.setName("Warszawa");
        stationService.saveStation(warszawa);
        //Now routes
        Route firstRoute = new Route();
        List<Station> stationsOnFirstRoute = new ArrayList<>();
        stationsOnFirstRoute.add(wroclaw);
        stationsOnFirstRoute.add(poznan);
        stationsOnFirstRoute.add(szczecin);
        firstRoute.setStationsOnRoute(stationsOnFirstRoute);
        firstRoute.setAvailable(true);
        Route secondRoute = new Route();
        List<Station> stationsOnSecondRoute = new ArrayList<>();
        stationsOnSecondRoute.add(wroclaw);
        stationsOnSecondRoute.add(krakow);
        stationsOnSecondRoute.add(warszawa);
        secondRoute.setStationsOnRoute(stationsOnSecondRoute);
        secondRoute.setAvailable(true);
    }
}
