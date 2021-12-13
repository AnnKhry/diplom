package com.example11.demo.controller;


import com.example11.demo.model.enumerations.Order;
import com.example11.demo.model.enumerations.Post;
import com.example11.demo.model.enumerations.RouteInfo;
import com.example11.demo.service.PInfoService;
import com.example11.demo.service.impl.OrderServiceImpl;
import com.example11.demo.service.impl.RouteService;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {

    @Autowired
    RouteService fservice;
//    @Autowired
//    private SearchRepo searchRepo;

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);
    ArrayList<RouteInfo> Route_list = new ArrayList<RouteInfo>();


    @RequestMapping("/new_route")
    public String showNewRoutePage(Model model) {
        RouteInfo route = new RouteInfo();
        model.addAttribute("route", route);

        return "admin_add_route";
    }


    @RequestMapping("/new_admin")
    public String showNewAdmine(Model model) {
        RouteInfo route = new RouteInfo();
        model.addAttribute("route", route);

        return "registeradmin";
    }


    @RequestMapping("/adminfunc")
    public String showAdminPanel(Model model) {
        RouteInfo route = new RouteInfo();
        model.addAttribute("route", route);

        return "admin_str";
    }


//    @GetMapping("/admin_edit_route_details")
//    public String showRouteDetailstoEdit(ModelMap model,@SessionAttribute("admin") Admin admin ) {
//
//        LOGGER.info("Start");
//        model.addAttribute("Route_list", fservice.getRoute_details());
//        LOGGER.info("End");
//        return "admin_edit_route_details";
//    }


    @RequestMapping(value = "/saveroute", method = RequestMethod.POST)
    public String saveRoute(@ModelAttribute("route") RouteInfo route) {
        fservice.save(route);

        return "redirect:/home";
    }


    @RequestMapping("/new_post")
    public String showNewPost(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);

        return "admin_news";
    }



    @Autowired
    private OrderServiceImpl serI;
    @RequestMapping("/listorders")
    public String allordersadmin(Model model, @Param("keyword") Long keyword) {

        List<Order> orders =serI.listAllOrders(keyword);
        model.addAttribute("orders",orders);
        model.addAttribute("keyword", keyword);
        return "admin_orders";
    }


    @RequestMapping("/deleteO/{orderId}")
    public String deleteOrder(@PathVariable(name="orderId") long orderId, Model model) {
        try {
            serI.deleteO(orderId);
            return "redirect:/listorders";
        } catch(ConstraintViolationException | DataIntegrityViolationException exception) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "welcome";
        }}}
//    @GetMapping("/admin_add_route")
//    public String showAddRouteForm() {
//        return "admin_add_route";
//    }
//

//    @PostMapping("/admin_add_route")
//    public String addRouteDetails(ModelMap model, @RequestParam Long id, @RequestParam String fromA,
//                                   @RequestParam String toA, @RequestParam String dept_date,
//                                   @RequestParam String dept_time, @RequestParam String arr_time, @RequestParam int e_seats_left,
//                                @RequestParam float e_seat_price, @RequestParam String status) {
//
//        LOGGER.info("Start");
//        fservice.addRoute(id, fromA, toA, dept_date,  dept_time, arr_time, e_seats_left,
//                e_seat_price,  status);
//        model.addAttribute("Route_list", fservice.getRoute_details());
//        LOGGER.info("End");
//
//        return "admin_edit_route_details";
//    }


//    @GetMapping("/edit_route_details")
//    public String showEditRoutedetails(@RequestParam Long id, ModelMap model) {
//        model.addAttribute("route", fservice.getRoute(id));
//        return "edit_route_details";
//    }
//
//    @PostMapping("/edit_route_details")
//    public String modifyRouteDetails(@RequestParam Long id, RouteInfo route,
//                                      BindingResult bindingResult, ModelMap model ) {
//        LOGGER.info("Start");
//        if (bindingResult.hasErrors()) {
//            return "admin_edit_route_details";
//        }
//
//        fservice.updateRoute(route);
//        model.addAttribute("Route_list", fservice.getRoute_details());
//        LOGGER.info("End");
//
//        return "admin_edit_route_details";

