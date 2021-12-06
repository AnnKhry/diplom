package com.example11.demo.repository;

import com.example11.demo.model.enumerations.RouteInfo;
import com.example11.demo.model.enumerations.User;

import java.util.Optional;

public class RouteRepositoryImpl {


        private RouteRepository routeRepository;

        public Optional<RouteInfo> loadRouteById(Long id) {
            return routeRepository.findById(id);
        }

    }

