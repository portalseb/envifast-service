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

import java.time.LocalDate;
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
    public void generateNextWeekDateTime(){
        for(int i=0;i<7;i++){
                for(int j=0;j<24;j++){
                    for(int k=0;k<60;k++){
                        FechaHora fechaHora = new FechaHora();
                        fechaHora.setDia(LocalDate.now().plusDays(i));
                        fechaHora.setHora(LocalTime.of(j,k));

                        DateTimeModel dateTimeModel = new DateTimeModel();
                        dateTimeModel.setDate(fechaHora.getDia());
                        dateTimeModel.setTime(fechaHora.getHora());
                        dateTimeModel.setActive(1);
                        dateTimeRepository.save(dateTimeModel);
                    }
                }
        }
        var airportsModels = airportRepository.findAllByActive(1);
        var dateTimeModels = dateTimeRepository.findAll();

        for(AirportsModel airportsModel: airportsModels){
            var aeropuertoModel = airportRepository.findByCityShortNameAndActive(airportsModel.getAirportCode(),airportsModel.getActive());

            List<AirportsCapacityModel> airportsCapacityModels=new ArrayList<>();
            for(DateTimeModel dateTimeModel: dateTimeModels){
                var fechaHoraModel = dateTimeRepository.findByIdOfDateTimeActive(dateTimeModel.getId(),dateTimeModel.getActive());
                var airportsCapacityModel = new AirportsCapacityModel();
                airportsCapacityModel.setDateTime(fechaHoraModel);
//                airportsCapacityModel.setAirport(airportsModel);
                airportsCapacityModel.setAvailableCapacity(airportsModel.getMaxCapacity());
                airportsCapacityModel.setActive(1);
                airportsCapacityModels.add(airportsCapacityModel);
//                airportCapacityRepository.save(airportsCapacityModel);
            }

            aeropuertoModel.setCapacity(airportsCapacityModels);
            airportRepository.save(aeropuertoModel);
        }

    }


}