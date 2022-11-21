package com.bb.envifastservice.adapter.out.persistence.impl;

import com.bb.envifastservice.adapter.out.persistence.dtos.AirportCoord;
import com.bb.envifastservice.adapter.out.persistence.mappers.AirportMapper;
import com.bb.envifastservice.adapter.out.persistence.repos.AirportCapacityRepository;
import com.bb.envifastservice.adapter.out.persistence.repos.AirportRepository;
import com.bb.envifastservice.adapter.out.persistence.repos.DateTimeRepository;
import com.bb.envifastservice.algo.Aeropuerto;
import com.bb.envifastservice.algo.CapacidadAeropuerto;
import com.bb.envifastservice.algo.Ciudad;
import com.bb.envifastservice.algo.FechaHora;
import com.bb.envifastservice.application.port.in.GenerateNextWeekDateTimeService;
import com.bb.envifastservice.application.port.out.GenerateNextWeekDateTimePort;
import com.bb.envifastservice.application.port.out.ListAirportCoordPort;
import com.bb.envifastservice.application.port.out.ListAllAirportsPort;
import com.bb.envifastservice.hexagonal.PersistenceAdapter;
import com.bb.envifastservice.models.AirportsCapacityModel;
import com.bb.envifastservice.models.AirportsModel;
import com.bb.envifastservice.models.DateTimeModel;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class AirportAdapter implements ListAllAirportsPort, ListAirportCoordPort, GenerateNextWeekDateTimePort {

    private final AirportRepository airportRepository;
    private final DateTimeRepository dateTimeRepository;
    private final AirportCapacityRepository airportCapacityRepository;

    @Override
    public List<Aeropuerto> listAllAirports() {
        var table= airportRepository.findAllByActive(1);
        List<Aeropuerto> list = new ArrayList<>();
        for (AirportsModel aeropuerto:table) {
            var aerop = new Aeropuerto();
            aerop.setId(Math.toIntExact(aeropuerto.getId()));
            aerop.setCapacidad(aeropuerto.getMaxCapacity());
            aerop.setCodigo(aeropuerto.getAirportCode());


            var ciudad = new Ciudad();
            ciudad.setAbreviacion(aeropuerto.getCityShortName());
            ciudad.setContinente(aeropuerto.getContinent());
            ciudad.setNombre(aeropuerto.getCityName());
            ciudad.setPais(aeropuerto.getCountryName());
            aerop.setCiudad(ciudad);
            list.add(aerop);

        }
        return list;
    }

    @Override
    public List<AirportCoord> listCoord() {
        var table = airportRepository.findAllByActive(1);
        var answer =new ArrayList<AirportCoord>();
        for (AirportsModel aeropuerto:table
             ) {
            var coord = new AirportCoord();
            coord.setId(aeropuerto.getId());
            coord.setCityName(aeropuerto.getCityName());
            coord.setX_pos(aeropuerto.getXPos());
            coord.setY_pos(aeropuerto.getYPos());
            answer.add(coord);
        }
        return answer;
    }

    @Override
    @Transactional
    public void generateNextWeekDateTime(String fecha, Integer dias, Integer forSim){
        var dateTimeList = new ArrayList<DateTimeModel>();

        for(int i=0;i<dias;i++){
            for(int j=0;j<24;j++){
                for(int k=0;k<60;k++){
                    FechaHora fechaHora = new FechaHora();
                    fechaHora.setDia(LocalDate.parse(fecha).plusDays(i));
                    fechaHora.setHora(LocalTime.of(j,k));
                    var dateTimeModelsInBD = dateTimeRepository.findByFechaHoraActive(LocalDateTime.of(fechaHora.getDia(), fechaHora.getHora()), 1);
                    //Comprobar que no se repita
                    if(dateTimeModelsInBD.size() == 0) {
                        DateTimeModel dateTimeModel = new DateTimeModel();
                        //dateTimeModel.setDate(fechaHora.getDia());
                        //dateTimeModel.setTime(fechaHora.getHora());
                        dateTimeModel.setDateTime(LocalDateTime.of(fechaHora.getDia(),fechaHora.getHora()));
                        dateTimeModel.setActive(1);
                        dateTimeList.add(dateTimeModel);
                    }
                }
            }
        }
        dateTimeRepository.saveAll(dateTimeList);
        var airportsModels = airportRepository.findAllByActive(1);
        var dateTimesModels = dateTimeRepository.findDateTimesActiveRange(LocalDateTime.of(LocalDate.parse(fecha),LocalTime.of(0,0,0)),LocalDateTime.of(LocalDate.parse(fecha).plusDays(dias),LocalTime.of(23,59,0)),1);

        //if(forSim>0) {
        //    airportCapacityRepository.deleteByForSim(forSim,1);
        //}

        //Pendiente: agregar que no se repita para el dia a dia...
        for(AirportsModel airportsModel: airportsModels){

            var airportsCapacityModels = new ArrayList<AirportsCapacityModel>();
            for(DateTimeModel dateTimeModel: dateTimesModels){
                var airportsCapacityModel = new AirportsCapacityModel();
                airportsCapacityModel.setDateTime(dateTimeModel);
//                airportsCapacityModel.setAirport(airportsModel);
                airportsCapacityModel.setAvailableCapacity(airportsModel.getMaxCapacity());
                airportsCapacityModel.setActive(1);
                airportsCapacityModel.setForSim(forSim);
                airportsCapacityModels.add(airportsCapacityModel);
//                airportCapacityRepository.save(airportsCapacityModel);
            }
            airportsModel.setCapacity(airportsCapacityModels);
        }
        airportRepository.saveAll(airportsModels);
    }


}
