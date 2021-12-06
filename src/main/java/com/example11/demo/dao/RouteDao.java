package com.example11.demo.dao;






import com.example11.demo.model.enumerations.RouteInfo;

import java.util.ArrayList;
import java.util.Date;


public interface RouteDao {

    public void addRoute(Long id, String fromA, String toA, String dept_date, String dept_time, String arr_time,
                         int seats_left, float seat_price, String status);

    public ArrayList<RouteInfo> getRoute_details();

    public RouteInfo getRoute(Long id);

    public void updateRoute(RouteInfo route);

    public void deleteRoute(Long id);

    public ArrayList<RouteInfo> getUserRoute_details(String fromA, String toA, String departure);

}
