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





}
