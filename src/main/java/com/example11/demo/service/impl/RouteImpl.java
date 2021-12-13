package com.example11.demo.service.impl;


import com.example11.demo.model.enumerations.RouteInfo;
import com.example11.demo.model.enumerations.User;
import com.example11.demo.repository.RouteRepository;
import com.example11.demo.service.RouteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RouteImpl implements RouteService {

    private final RouteRepository routeRepository;


    public RouteImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;

    }



    @Override
    public List<RouteInfo> listAll() {
        return this.routeRepository.findAll();
    }





    @Override
    public Optional<RouteInfo> findById(Long id) {
        return this.routeRepository.findById(id);
    }


    @Override
    public void updateRoute( long id, String fromA, String toA,
                             String dept_date, String dept_time, String arr_time, int seats_left, long price) {
        RouteInfo route =  this.findById(id).orElseThrow();

        route.setId(id);
        route.setFromA(fromA);
        route.setToA(toA);
        route.setDept_date(dept_date);
        route.setDept_time(dept_time);
        route.setArr_time(arr_time);
        route.setSeats_left(seats_left);
        route.setSeat_price(price);

        this.routeRepository.save(route);

    }

}
