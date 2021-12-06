package com.example11.demo.controller;



import com.example11.demo.model.enumerations.RouteInfo;
import com.example11.demo.model.enumerations.User;
import com.example11.demo.repository.SearchRepo;
import com.example11.demo.service.impl.RouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    RouteService fservice;
    @Autowired
    private SearchRepo searchRepo;

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

    ArrayList<RouteInfo> Route_list = new ArrayList<RouteInfo>();



    // поиск рейса по дате и городу
    @RequestMapping(value ="/findRoutes", method =RequestMethod.GET)
    public String findRoutes(@RequestParam (name="fromA",required=false)String fromA, @RequestParam (name="toA",required=false)String toA,
                             @RequestParam (name="dept_date",required=false)
//                             @DateTimeFormat(pattern ="yyyy-MM-dd") LocalDate dept_date,
                             String dept_date,
                             @RequestParam (name="seat_price",required=false)String seat_price,ModelMap modelMap) {
        LOGGER.info("Inside findRoutes() " + fromA + " To: " + toA + " dept_date: " + dept_date + " seat_price: " +seat_price);
        List<RouteInfo> routes = searchRepo.findRoutes(fromA, toA, dept_date);

        modelMap.addAttribute("routes", routes);
        LOGGER.info("Routes " + routes);
        return "displayRoutes";
    }

    @GetMapping("/fin")
    public String search( Model model) {

        return "findRoutes";
    }

    }



