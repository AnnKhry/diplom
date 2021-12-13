package com.example11.demo.service;


import com.example11.demo.model.enumerations.RouteInfo;
import com.example11.demo.model.enumerations.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RouteService {

  List<RouteInfo> listAll();



  Optional<RouteInfo> findById(Long id);
  void updateRoute( long id,String fromA, String toA,
              String dept_date, String dept_time, String arr_time, int seats_left, long price);
    }
