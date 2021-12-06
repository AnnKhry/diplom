package com.example11.demo.controller;

import com.example11.demo.dao.OrderRequest;
import com.example11.demo.model.enumerations.Order;
import com.example11.demo.model.enumerations.RouteInfo;
import com.example11.demo.model.enumerations.User;
import com.example11.demo.model.exceptions.*;
import com.example11.demo.repository.OrderRepository;
import com.example11.demo.repository.SearchRepo;
import com.example11.demo.repository.UserRepository;
import com.example11.demo.service.AuthService;
import com.example11.demo.service.OrderService;
import com.example11.demo.service.RouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.example11.demo.model.MailSender;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

//@SessionAttributes("passEnterSeats")
@Controller
public class ReservationController {
	@Autowired
	SearchRepo searchRepo;
	@Autowired
	OrderService orderService;
	@Autowired
	AuthService authService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	RouteService routeService;

MailSender mailSender;
	private RouteInfo route;

	public ReservationController() {
	}

	//
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);
	private Order savedOrder;


	@GetMapping("/showCompleteReservation")
    public String showCompleteReservation(@RequestParam("id") long id , HttpServletRequest req,
										  ModelMap modelMap, Model model

	){
    	LOGGER.info("Inside showCompleteReservation() Route_ID: "+id);


		Optional<RouteInfo> route=this.routeService.findById(id);

		// handle error here
		if(!route.isPresent()){
			LOGGER.error("Route 1{}",route.toString());
			throw new RouteNotFound("id "+id);
		}
		LOGGER.info("Route {}",route.toString());
		modelMap.addAttribute("route",route.get());


		String username = req.getRemoteUser();
		Optional<User> user=this.authService.findByUsername(username);

		if(!user.isPresent()){
			LOGGER.error("User {}",user.toString());
			throw new RouteNotFound("userId"+username);
		}
		LOGGER.info("User{}",user.toString());
		modelMap.addAttribute("user",user.get());

		Order order=new Order();
		model.addAttribute("order", order);

		order.setoRoute(route.get());
		order.setoUser(user.get());
		order.setDateCreatedOrder(LocalDateTime.now());
		final Order savedOrder = orderRepository.save(order);
		LOGGER.info("Saving the reservation:" + order);

		return "completeReservation";
	}

	public ReservationController(OrderService orderService) {

	}
	private RouteInfo routes;
	@RequestMapping(value = "/completeReservation", method = RequestMethod.POST)
//	@PostMapping(value = "/completeReservation")
	public String completeReservation(OrderRequest orderRequest,

									  HttpServletRequest req,
									  @ModelAttribute("order") Order orderq,
									  ModelMap modelMap, Model model) throws IOException, MessagingException {


		LOGGER.info("ccccccc " + orderq.getPassEnterSeats());
		int num = orderq.getPassEnterSeats();

		orderRequest.setPassEnterSeats(num);
		LOGGER.info("TTT " + orderRequest.toString());
		Order order = (Order) orderService.bookRoute(orderRequest);

//order.setPassEnterSeats(passEnterSeats);
//		modelMap.addAttribute("passEnterSeats", passEnterSeats);
//		orderService.setPassEnterSeats(passEnterSeats);
//		LOGGER.info("complPPPP: " + passEnterSeats);


//		modelMap.addAttribute("msg","Reservation created successfully and the reservation id is "+order.getId());
			return "reservationConfirmation";
		}


	}
