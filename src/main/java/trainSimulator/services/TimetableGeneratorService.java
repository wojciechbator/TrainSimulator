package trainSimulator.services;

import org.apache.commons.lang3.time.DateUtils;
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
    private final EventLogService eventLogService;
    private final RouteService routeService;
    private final TimetableEntityService timetableEntityService;
    private final TrainService trainService;
    private final PassengerService passengerService;
    private final GeneratorParametersService generatorParametersService;

    @Autowired
    public TimetableGeneratorService(EventLogService eventLogService, RouteService routeService, TimetableEntityService timetableEntityService,
                                     TrainService trainService, PassengerService passengerService, GeneratorParametersService generatorParametersService) {
        this.eventLogService = eventLogService;
        this.routeService = routeService;
        this.timetableEntityService = timetableEntityService;
        this.trainService = trainService;
        this.passengerService = passengerService;
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
        return passengers;
    }

    public void createTrains(Date startingTime, Date endingTime, int passengersCount) {
        while (startingTime.getTime() < endingTime.getTime()) {
            Train train = new Train();
            Set<Passenger> passengers = passengersForTrain(passengersCount);
            List<Train> allTrains = trainService.getAllTrains();
            Route firstRoute = routeService.findRouteById(1);
            Route secondRoute = routeService.findRouteById(2);
            //TODO: fix routes, now routeService goes on 1 route
            if(allTrains.size() > 0) {
                if(allTrains.get(allTrains.size() - 1).getRoute() == firstRoute && secondRoute.isAvailable()) {
                    train.setRoute(secondRoute);
                }
                else if(allTrains.get(allTrains.size() - 1).getRoute() == secondRoute && firstRoute.isAvailable()) {
                    train.setRoute(firstRoute);
                }
                else {
                    for (Route route : routeService.getAllRoutes()) {
                        if(route.isAvailable()) train.setRoute(route);
                    }
                }
            }
            else train.setRoute(firstRoute);
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
                timetableEntityService.saveTimetableEntity(timetableEntity);
            }
            train.setTimetable(timetable);
            for (Passenger passenger : passengers) {
                passenger.setTrain(train);
            }
            train.setPassengers(passengers);
            //train.setStation(stationsOnRoute.get(0));
            EventLog eventLog = new EventLog();
            eventLog.setType("INFO");
            eventLog.setTimestamp(new Date());
            eventLog.setStationName("");
            eventLog.setComment("Created train with ID: " + train.getId() + ", on route id: " + train.getRoute().getId() +
                    ", with " + train.getPassengers().size() + " passengers on board!");
            eventLogService.saveEvent(eventLog);
            trainService.saveTrain(train);
            startingTime = DateUtils.addSeconds(startingTime, Integer.valueOf(generatorParametersService.findGeneratorParameterById(2).getParameterValue()));
        }
    }

    public void generateTimetable() {
        Date startingDate = new Date();
        Date endingDate = DateUtils.addHours(startingDate, 24);
        //First of all - clear old timetable and data
        trainService.clearTrains();
        EventLog eventLog = new EventLog();
        eventLog.setStationName("");
        eventLog.setType("INFO");
        eventLog.setTimestamp(new Date());
        eventLog.setComment("Cleared old trains, timetable for them, passengers and tickets!");
        eventLogService.saveEvent(eventLog);

        //TODO: Get param by ID somehow, for now it will be hardcoded :(
        createTrains(startingDate, endingDate, Integer.valueOf(generatorParametersService.findGeneratorParameterById(4).getParameterValue()));
    }
}
