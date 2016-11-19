package trainSimulator.services;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.*;
import trainSimulator.repository.RouteRepository;
import trainSimulator.repository.TimetableEntityRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Service
@Transactional
public class TimetableGeneratorService {
    private final TimetableEntityRepository timetableEntityRepository;
    private final EventLogService eventLogService;
    private final RouteRepository routeRepository;
    private final TimetableEntityService timetableEntityService;
    private final TrainService trainService;
    private final PassengerService passengerService;
    private final GeneratorParametersService generatorParametersService;

    @Autowired
    public TimetableGeneratorService(TimetableEntityRepository timetableEntityRepository, EventLogService eventLogService,
                                     RouteRepository routeRepository, TimetableEntityService timetableEntityService,
                                     TrainService trainService, PassengerService passengerService, GeneratorParametersService generatorParametersService) {
        this.timetableEntityRepository = timetableEntityRepository;
        this.eventLogService = eventLogService;
        this.routeRepository = routeRepository;
        this.timetableEntityService = timetableEntityService;
        this.trainService = trainService;
        this.passengerService = passengerService;
        this.generatorParametersService = generatorParametersService;
    }

    public void saveTimetableEntity(TimetableEntity TimetableEntity) {
        timetableEntityRepository.save(TimetableEntity);
    }

    public void deleteTimetableEntity(TimetableEntity TimetableEntity) {
        timetableEntityRepository.delete(TimetableEntity);
    }

    public void findTimetableEntity(Integer id) {
        timetableEntityRepository.findOne(String.valueOf(id));
    }

    public void clearTimetableEntities() {
        List<TimetableEntity> timetableEntities = timetableEntityRepository.findAll();
        timetableEntities.forEach(this::deleteTimetableEntity);
    }

    private List<Passenger> passengersForTrain(int passengersCount) {
        String[] names = {"Wojtek", "Janek", "Jurek", "Piotrek", "Andrzej", "Eustachy", "Reniek", "Maciek", "Mariusz", "Marek",
                "Paulina", "Kasia", "Basia", "Zosia", "Ola", "Karolina", "Ula", "Marzena", "Marta", "Marysia"};
        List<Passenger> passengers = new ArrayList<>();
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
            List<Passenger> passengers = passengersForTrain(passengersCount);
            List<Route> allRoutes = routeRepository.findAll();
            train.setRoute(allRoutes.get(train.getId() % allRoutes.size()));
            train.getRoute().setAvailable(true);
            List<Station> stationsOnRoute = train.getRoute().getStationsOnRoute();
            List<TimetableEntity> timetable = new ArrayList<>();
            for (Station station : stationsOnRoute) {
                TimetableEntity timetableEntity = new TimetableEntity();
                timetableEntity.setArrivalTime(startingTime);
                startingTime = DateUtils.addSeconds(startingTime, Integer.valueOf(generatorParametersService.findGeneratorParameters("on_station_stop_time").getParameterValue()));
                timetableEntity.setDepartureTime(startingTime);
                timetableEntity.setStation(station);
                startingTime = DateUtils.addSeconds(startingTime, Integer.valueOf(generatorParametersService.findGeneratorParameters("period_between_station").getParameterValue()));
                timetable.add(timetableEntity);
            }
            train.setTimetable(timetable);
            timetableEntityService.saveTimetable(timetable);
            for (Passenger passenger : passengers) {
                passenger.setTrainID(train.getId());
            }
            train.setPassengers(passengers);
            EventLog eventLog = new EventLog();
            eventLog.setType("INFO");
            eventLog.setTimestamp(new Date());
            eventLog.setStationName("");
            eventLog.setComment("Created train with ID: " + train.getId() + ", on route id: " + train.getRoute().getId() +
                    ", with " + train.getPassengers().size() + " passengers on board!");
            eventLogService.saveEvent(eventLog);
            trainService.saveTrain(train);
            startingTime = DateUtils.addSeconds(startingTime, Integer.parseInt(generatorParametersService.findGeneratorParameters("departure_frequency").getParameterValue()));
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

        createTrains(startingDate, endingDate, Integer.valueOf(generatorParametersService.findGeneratorParameters("passengers_count").getParameterValue()));
    }
}
