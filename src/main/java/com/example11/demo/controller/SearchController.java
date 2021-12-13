package com.example11.demo.controller;



import com.example11.demo.model.enumerations.Order;
import com.example11.demo.model.enumerations.RouteInfo;
import com.example11.demo.repository.OrderRepository;
import com.example11.demo.repository.SearchRepo;
import com.example11.demo.service.PInfoService;
import com.example11.demo.service.impl.OrderServiceImpl;
import com.example11.demo.service.impl.RouteService;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class SearchController {

    @Autowired
    RouteService fservice;
    @Autowired
    private SearchRepo searchRepo;

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

    ArrayList<RouteInfo> Route_list = new ArrayList<RouteInfo>();


    @Autowired
    private PInfoService service;
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
    @Autowired
    private PInfoService ser;
    @RequestMapping("/list")
    public String allroutes(Model model, @Param("keyword") String keyword) {

        List<RouteInfo> routes =ser.listAll(keyword);
        model.addAttribute("routes",routes);
        model.addAttribute("keyword", keyword);
        return "list-routes";
    }



    @RequestMapping(value="/save", method= RequestMethod.POST)
    public String saveRoute(@ModelAttribute("route") RouteInfo route)
    {
        ser.save(route);
        return "redirect:/list";
    }

    @RequestMapping("/delete/{id}")
    public String deleteRoute(@PathVariable(name="id") long id, Model model) {
        try {
            ser.delete(id);
            return "redirect:/list";
        } catch(ConstraintViolationException | DataIntegrityViolationException exception) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "welcome";
        }
    }



    @Autowired
    private OrderServiceImpl serI;
    @RequestMapping("/edit/{id}")
    public ModelAndView editRoute(@PathVariable(name="id") long id)
    {
        ModelAndView mav= new ModelAndView("edit_route");

        RouteInfo route=ser.getRoute(id);
        mav.addObject("route", route);
        return mav;
    }


//    @Autowired
//    private OrderServiceImpl serI;
//    @RequestMapping("/listorders")
//    public String allordersadmin(Model model, @Param("keyword") Long keyword) {
//
//        List<Order> orders =serI.listAllOrders(keyword);
//        model.addAttribute("orders",orders);
//        model.addAttribute("keyword", keyword);
//        return "admin_orders";
//    }

PInfoService serv;
//    @RequestMapping("/tickets/{id}")
//    public ModelAndView Tickets(@PathVariable(name="id") long id, @Param("keyword") Long keyword ) {
//        ModelAndView mav = new ModelAndView("ticket_route");
//
//        List<Order> orders =serv.listAllRoutesOrders(3L);
//
//        mav.addObject("orders", orders);
//        LOGGER.info("Inside ticketid "+orders);
//
//        return mav;
//
//
//    }



//    @Autowired
//    private PInfoService service;
//    // поиск рейса по дате и городу
//    @RequestMapping(value ="/findRoutes", method =RequestMethod.GET)
//    public String findRoutes(@RequestParam (name="fromA",required=false)String fromA, @RequestParam (name="toA",required=false)String toA,
//                             @RequestParam (name="dept_date",required=false)
////                             @DateTimeFormat(pattern ="yyyy-MM-dd") LocalDate dept_date,
//                                     String dept_date,
//                             @RequestParam (name="seat_price",required=false)String seat_price,ModelMap modelMap) {
//        LOGGER.info("Inside findRoutes() " + fromA + " To: " + toA + " dept_date: " + dept_date + " seat_price: " +seat_price);
//        List<RouteInfo> routes = searchRepo.findRoutes(fromA, toA, dept_date);
//
//        modelMap.addAttribute("routes", routes);
//        LOGGER.info("Routes " + routes);
//        return "displayRoutes";
//    }

    @Autowired

OrderRepository repository;
    @RequestMapping("/tickets/{id}")
    public String Tickets(@PathVariable(name="id") Long id,  ModelMap modelMap ) throws SQLException {


        List<Order> orders=repository.findByRouteId(id);
//
        modelMap.addAttribute("orders", orders);
        LOGGER.info("Routes " + orders);


            return "ticket_route";

        }

//@Autowired
//    OrderRepository repository;
//    @RequestMapping("/tickets")
//    public String Tickets(ModelMap modelMap ) throws SQLException {
//
//
//      List<Order> orders=repository.findByRouteId(3);
//////
//        modelMap.addAttribute("orders", orders);
//
//        LOGGER.info("Orders " + orders);
//
//        return "ticket_route";
//
//    }





//    @Autowired
//    private SearchRepo repo;
//    @RequestMapping("/vitebsk")
//    public String allroutesVitebsk(Model model) {
//
//
//
//
//
//        List<RouteInfo> routes =repo.findRoutesVitebsk();
//        model.addAttribute("routes",routes);
//        return "vitebsk";
//    }



    @Autowired
    private PInfoService serp;
    @RequestMapping("/vitebsk")
    public String allroutesVitebsk(Model model, @Param("keywordfromV") String keywordfromV, @Param("keywordtoV") String keywordtoV) {

        List<RouteInfo> routes =ser.listAllVitebsk(keywordfromV,keywordtoV);
        model.addAttribute("routes",routes);
        model.addAttribute("keywordfromV", keywordfromV);
        model.addAttribute("keywordtoV", keywordtoV);
        return "vitebsk";
    }



    @RequestMapping("/betterroutes")
    public String allroutesSearch(Model model, @Param("keywordfrom") String keywordfrom, @Param("keywordto") String keywordto) {

        List<RouteInfo> routes =ser.listAllroutes(keywordfrom,keywordto);
        model.addAttribute("routes",routes);
        model.addAttribute("keywordfrom", keywordfrom);
        model.addAttribute("keywordto", keywordto);
        return "better_routes";
    }

    @RequestMapping("/minsk")
    public String allroutesMinsk(Model model, @Param("keywordfrom") String keywordfrom, @Param("keywordto") String keywordto) {

        List<RouteInfo> routes =ser.listAllMinsk(keywordfrom,keywordto);
        model.addAttribute("routes",routes);
        model.addAttribute("keywordfrom", keywordfrom);
        model.addAttribute("keywordto", keywordto);
        return "minsk";
    }
  ;
    @RequestMapping("/brest")
    public String allroutesBrest(Model model, @Param("keywordfrom") String keywordfrom, @Param("keywordto") String keywordto) {

        List<RouteInfo> routes =ser.listAllBrest(keywordfrom,keywordto);
        model.addAttribute("routes",routes);
        model.addAttribute("keywordfrom", keywordfrom);
        model.addAttribute("keywordto", keywordto);
        return "brest";
    }


    @RequestMapping("/borisov")
    public String allroutesBorisov(Model model, @Param("keywordfrom") String keywordfrom, @Param("keywordto") String keywordto) {

        List<RouteInfo> routes =ser.listAllBorisov(keywordfrom,keywordto);
        model.addAttribute("routes",routes);
        model.addAttribute("keywordfrom", keywordfrom);
        model.addAttribute("keywordto", keywordto);
        return "borisov";
    }


    @RequestMapping("/gomel")
    public String allroutesGomel(Model model, @Param("keywordfrom") String keywordfrom, @Param("keywordto") String keywordto) {

        List<RouteInfo> routes =ser.listAllGomel(keywordfrom,keywordto);
        model.addAttribute("routes",routes);
        model.addAttribute("keywordfrom", keywordfrom);
        model.addAttribute("keywordto", keywordto);
        return "gomel";
    }


    @RequestMapping("/grodno")
    public String allroutesGrodno(Model model, @Param("keywordfrom") String keywordfrom, @Param("keywordto") String keywordto) {

        List<RouteInfo> routes =ser.listAllGrodno(keywordfrom,keywordto);
        model.addAttribute("routes",routes);
        model.addAttribute("keywordfrom", keywordfrom);
        model.addAttribute("keywordto", keywordto);
        return "grodno";
    }


    @RequestMapping("/mogilev")
    public String allroutesMogilev(Model model, @Param("keywordfrom") String keywordfrom, @Param("keywordto") String keywordto) {

        List<RouteInfo> routes =ser.listAllMogilev(keywordfrom,keywordto);
        model.addAttribute("routes",routes);
        model.addAttribute("keywordfrom", keywordfrom);
        model.addAttribute("keywordto", keywordto);
        return "mogilev";
    }

;
    @RequestMapping("/baranovichi")
    public String allroutesBaranovichi(Model model, @Param("keywordfrom") String keywordfrom, @Param("keywordto") String keywordto) {

        List<RouteInfo> routes =ser.listAllBaranovichi(keywordfrom,keywordto);
        model.addAttribute("routes",routes);
        model.addAttribute("keywordfrom", keywordfrom);
        model.addAttribute("keywordto", keywordto);
        return "baranovichi";
    }

}



