package trainSimulator.services;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.*;
import java.util.*;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Service
@Transactional
public class TimetableGeneratorService {
    private static final Logger logger = Logger.getLogger(TimetableGeneratorService.class);
    private final EventLogService eventLogService;
    private final RouteService routeService;
    private final TimetableEntityService timetableEntityService;
    private final TrainService trainService;
    private final GeneratorParametersService generatorParametersService;

    @Autowired
    public TimetableGeneratorService(EventLogService eventLogService, RouteService routeService, TimetableEntityService timetableEntityService,
                                     TrainService trainService, GeneratorParametersService generatorParametersService) {
        this.eventLogService = eventLogService;
        this.routeService = routeService;
        this.timetableEntityService = timetableEntityService;
        this.trainService = trainService;
        this.generatorParametersService = generatorParametersService;
    }

    private Set<Passenger> passengersForTrain(int passengersCount) {
        String[] names = {"Wojtek", "Janek", "Jurek", "Piotrek", "Andrzej", "Eustachy", "Reniek", "Maciek", "Mariusz", "Marek",
                "Paulina", "Kasia", "Basia", "Zosia", "Ola", "Karolina", "Ula", "Marzena", "Marta", "Marysia"};
        Set<Passenger> passengers = new LinkedHashSet<>();
        for (int i = 0; i < passengersCount; i++) {
            int index = new Random().nextInt(names.length);
            Passenger passenger = new Passenger();
            passenger.setName(names[index]);
            passengers.add(passenger);
        }
        logger.info("Passengers prepared for train!");
        return passengers;
    }

    private void createTrains(Date startingTime, Date endingTime, int passengersCount) {
        String[] trainNames = {"Matejko", "Malczewski", "Batory", "Kossak", "Rejtan", "Błyskawica", "Komancz", "Andrzej",
        "Moniuszko", "Sienkiewicz", "Mickiewicz", "Jagiełło", "Łokietek", "Dmowski", "Piłsudski", "Mieszko"};
        while (startingTime.getTime() < endingTime.getTime()) {
            int index = new Random().nextInt(trainNames.length);
            Train train = new Train();
            train.setName(trainNames[index]);
            Set<Passenger> passengers = passengersForTrain(passengersCount);
            List<Train> allTrains = trainService.getAllTrains();
            Route firstRoute = routeService.findRouteById(1);
            Route secondRoute = routeService.findRouteById(2);
            if(allTrains.size() > 0) {
                if(allTrains.get(allTrains.size() - 1).getRoute() == firstRoute && secondRoute.isAvailable()) {
                    train.setRoute(secondRoute);
                    logger.info("Route: " + secondRoute.getName() + " set for train: " + train.getId());
                }
                else if(allTrains.get(allTrains.size() - 1).getRoute() == secondRoute && firstRoute.isAvailable()) {
                    train.setRoute(firstRoute);
                    logger.info("Route: " + firstRoute.getName() + " set for train: " + train.getId());
                }
                else {
                    for (Route route : routeService.getAllRoutes()) {
                        if(route.isAvailable()) {
                            train.setRoute(route);
                            logger.info("Route: " + route.getName() + " set for train: " + train.getId());
                        }
                    }
                }
            }
            else {
                train.setRoute(firstRoute);
                logger.info("Route: " + firstRoute.getName() + " set for train: " + train.getId());
            }
            List<Station> stationsOnRoute = train.getRoute().getStationsOnRoute();
            List<TimetableEntity> timetable = new ArrayList<>();
            for (Station station : stationsOnRoute) {
                TimetableEntity timetableEntity = new TimetableEntity();
                timetableEntity.setArrivalTime(startingTime);
                startingTime = DateUtils.addSeconds(startingTime, Integer.valueOf(generatorParametersService.findGeneratorParameterById(1).getParameterValue()));
                timetableEntity.setDepartureTime(startingTime);
                timetableEntity.setStation(station);
                startingTime = DateUtils.addSeconds(startingTime, Integer.valueOf(generatorParametersService.findGeneratorParameterById(3).getParameterValue()));
                timetable.add(timetableEntity);
                timetableEntityService.createTimetableEntity(timetableEntity);
            }
            train.setTimetable(timetable);
            logger.info("Timetable has been set for train: " + train.getId());
            for (Passenger passenger : passengers) {
                passenger.setTrain(train);
            }
            train.setPassengers(passengers);
            logger.info("Passengers has been set for train: " + train.getId());
            train.setStation(stationsOnRoute.get(0));
            train.setState("PLANNED");
            EventLog eventLog = new EventLog();
            eventLog.setType("INFO");
            eventLog.setTimestamp(new Date());
            eventLog.setStationName(train.getStation().getName());
            trainService.saveTrain(train);
            eventLog.setComment("Created train with ID: " + train.getId() + ", on route id: " + train.getRoute().getId() +
                     ", with " + train.getPassengers().size() + " passengers on board!");
            eventLogService.saveEvent(eventLog);
            startingTime = DateUtils.addSeconds(startingTime, Integer.valueOf(generatorParametersService.findGeneratorParameterById(2).getParameterValue()));
        }
    }

    public void generateTimetable() {
        Date startingDate = new Date();
        Date endingDate = DateUtils.addHours(startingDate, 24);
        //First of all - clear old timetable and data
        clearTimetable();
        createTrains(startingDate, endingDate, Integer.valueOf(generatorParametersService.findGeneratorParameterById(4).getParameterValue()));
    }

    public void clearTimetable() {
        List<Train> allTrains = trainService.getAllTrains();
        for(Train train : allTrains) {
            trainService.deleteTrain(train);
        }
        String comment = "Cleared old trains, timetable for them, passengers and tickets!";
        logger.info(comment);
        eventLogService.saveEvent(new EventLog("INFO", "", new Date(), comment));
    }
}
