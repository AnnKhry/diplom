package com.example11.demo.service.impl;


import com.example11.demo.dao.RouteDao;
import com.example11.demo.model.enumerations.RouteInfo;
import com.example11.demo.repository.SearchRepo;
import com.example11.demo.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class RouteService {
    @Autowired
    private SearchRepo searchRepo;

    //    @Autowired
    RouteDao fdao;



    DateUtil date = new DateUtil();
    int seatcount = 0;


    public void save(RouteInfo route) {
        searchRepo.save(route);
    }


    public ArrayList<RouteInfo> getRoute_details() {

        ArrayList<RouteInfo> Routes_list =fdao.getRoute_details();
        return Routes_list;
    }


    public RouteInfo getRoute(Long id ) {
        RouteInfo route=fdao.getRoute(id);

        return route;

    }

    public void updateRoute(RouteInfo route) {
        fdao.updateRoute(route);
    }





}

