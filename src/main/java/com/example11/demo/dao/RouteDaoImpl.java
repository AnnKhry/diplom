package com.example11.demo.dao;




import com.example11.demo.model.enumerations.RouteInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class RouteDaoImpl implements RouteDao {
    @Override
    public void addRoute(Long id, String fromA, String toA, String dept_date, String dept_time, String arr_time, int seats_left, float seat_price, String status) {

    }

    @Override
    public ArrayList<RouteInfo> getRoute_details() {
        return null;
    }

    @Override
    public RouteInfo getRoute(Long id) {
        return null;
    }

    @Override
    public void updateRoute(RouteInfo route) {

    }

    @Override
    public void deleteRoute(Long id) {

    }

    @Override
    public ArrayList<RouteInfo> getUserRoute_details(String fromA, String toA, String departure) {
        return null;
    }


    }





