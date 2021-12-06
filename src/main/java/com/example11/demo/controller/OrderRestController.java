package com.example11.demo.controller;

import com.example11.demo.model.enumerations.Order;
import com.example11.demo.model.exceptions.ReservationNotFound;
import com.example11.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;

@RestController
public class OrderRestController {

    @Autowired
    OrderRepository orderRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderRestController.class);

    @RequestMapping("/order/{id}")
    public Order findOrder(@PathVariable("id") Long id){
        // handle error here what if no reservation found
        LOGGER.info("Inside findReservation() for id: " + id);
        Optional<Order> order=orderRepository.findById(id);
        if(!order.isPresent()){
            LOGGER.error("No reservation exist with id "+id);
            throw new ReservationNotFound("No reservation exist with id "+id);
        }
        return order.get();
    }


}