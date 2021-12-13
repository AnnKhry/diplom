package com.example11.demo.controller;


import com.example11.demo.dao.OrderRequest;
import com.example11.demo.model.enumerations.Order;
import com.example11.demo.model.enumerations.RouteInfo;
import com.example11.demo.model.enumerations.User;
import com.example11.demo.model.exceptions.RouteNotFound;
import com.example11.demo.repository.OrderRepository;
import com.example11.demo.repository.UserRepository;
import com.example11.demo.service.AuthService;
import com.example11.demo.service.OrderService;
import com.example11.demo.service.PInfoService;
import com.example11.demo.service.impl.OrderServiceImpl;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.lang.Object;


@Controller
public class MainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

//    private final AuthService authService;
@Autowired
 AuthService authService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
   OrderService orderService;

    OrderRequest orderRequest;


    private final EntityManager em;


    public MainController(AuthService authService, EntityManager em) {
        this.authService =authService;
        this.em = em;
    }


    @GetMapping("/")
    public String home( Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }

        @GetMapping("/about")
        public String about( Model model) {
            model.addAttribute("title", "О нас");
            return "about";
    }


    @GetMapping("/reservationR")
    public String reservationR( Model model) {
        model.addAttribute("title", "Правила бронирования");
        return "reservationR";
    }


    @GetMapping("/userAccount")
    public String getUserAccount(HttpServletRequest req, Model model){
        String username = req.getRemoteUser();
        System.out.println(username);
        Optional<User> user = this.authService.findByUsername(username);

        model.addAttribute("user",user);
        model.addAttribute("title","UserAccount");
        model.addAttribute("bodyContent","userAccount");

        return "/userAccount";
    }

    @Autowired
    private PInfoService ser;

        @RequestMapping("/betteroutes")
        public String allroutes(Model model,HttpServletRequest req, @Param("keyword") String keyword) {

            List<RouteInfo> routes =ser.listAll(keyword);
            model.addAttribute("routes",routes);
            model.addAttribute("keyword", keyword);

        return "better_routes";
    }

    @GetMapping("/userAccount/settings")
    public String getSettings(HttpServletRequest req,Model model){
        String username = req.getRemoteUser();

        Optional<User> user = this.authService.findByUsername(username);

        model.addAttribute("user",user);

//        model.addAttribute("title","Settings");
//        model.addAttribute("bodyContent","settings");

        return "settings";

    }





    @GetMapping("/userAccount/myorders")
    public String getUserOrders(HttpServletRequest req, Model model, Principal principal,@AuthenticationPrincipal UserDetails currentUser) throws IOException, MessagingException, SQLException {
//        String username = req.getRemoteUser();
//        Optional<User> user=this.authService.findByUsername(username);
//
//        if(!user.isPresent()){
//            LOGGER.error("User {}",user.toString());
//            throw new RouteNotFound("userId"+username);
//        }

//        User user = (User) userRepository.findUserByUsername(principal.getName());
//        model.addAttribute("currentStudent", user);

//        LOGGER.info("User{}",user);
//        userRepository.findByUsername(principal.getName());



//
//            List<Order> orders =serI.listAllOrders(keyword);
//            model.addAttribute("orders",orders);
//            model.addAttribute("keyword", keyword);
//            return "admin_orders";
//        }



        String  username;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        username = auth.getName();
        LOGGER.info("NNNN" + username);


        LOGGER.info("NNNN" + userRepository.findByUsername(username));
        LOGGER.info("NNNN" + userRepository.findByUsername(username).get().getId());
        LOGGER.info("NNNN"+ orderService.findById(userRepository.findByUsername(username).get().getId()));
        Long iU=userRepository.findByUsername(username).get().getId();
        List<Order> ordersUser=orderRepository.searchOrdersU(iU);

//       Optional<Order> optionalOrder= orderRepository.findById(userRepository.findByUsername(username).get().getId());
        model.addAttribute("ordersUser",ordersUser);

//               long i = userRepository.findByUsername(username).get().getId();
//        String sql = "Select * from order_table where o_user_id=" + i;
//        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/infodb?useUnicode=true&serverTimezone=UTC", "root", "root");
//        Statement stmt = con.createStatement();
//        ResultSet result =stmt.executeQuery(sql);


////
//        LOGGER.info("NNNN" + i);
//String iU;
//        List<Order> orderU=orderService.listAllOrdersU(1L);
//        model.addAttribute("orderU",orderU);
////
//        LOGGER.info("NNNN" + i);








        //        LOGGER.info("User{}", orderService.orderUsers(principal.getName()));
//        ResultSet result=  orderService.orderUsers(principal.getName());
//                LOGGER.info("User{}",result);
//        while(result.next()) {
//            Long order_id = result.getLong("order_id");
//            Optional<Order> order =orderService.findById(order_id);
//            LOGGER.info("OrderRRRRR" + order);
//
//            String froma = result.getString("fromA");
////    Query query = (Query) em.createNativeQuery(sql);
////           String fromA=
////                   String toA=result.getLong("order_id");
//            String dateCreateOrder = result.getString("date_created_order");
//            int passSeats = result.getInt("pass_enter_seats");
//            LOGGER.info("NNNNfff" + dateCreateOrder + froma + order_id);



//        LOGGER.info("User id",user.username);
//        model.addAttribute("user1", user1);
//        String sql = "Select order_id from order_table where o_user_id= (SELECT id FROM users_table WHERE name Like 'Anna')";
//        Query query =  em.createNativeQuery(sql);
//        LOGGER.info("id заказов пользователя" +  query.getResultList());

//        Long userId=orderRequest.getUserId();
//        Optional<User> userOptional=userRepository.findById(userId);
//        if (!userOptional.isPresent()) {
//            throw new RouteNotFound("No user found with id "+userId);
//        }
//        User user =userOptional.get();

//
//        model.addAttribute("user", user);
//            long userId = 1;
//            Optional<Order> orderOptional = orderRepository.findById(1);
//            if (!orderOptional.isPresent()) {
//                throw new RouteNotFound("No route found with id " + userId);
//            }
//            Order order = orderOptional.get();
//
//
//            model.addAttribute("order", order);


//        List<RouteInfo> routes   this.orderService.orderUsers(order);
//        LOGGER.info("User id",orderService.orderUsers(order));
            return "userOrderss";

        }


    @PostMapping("/userAccount/{username}")
    public String update(@PathVariable String username,
                         @RequestParam String usernameOfUser,
                         @RequestParam String nameOfUser,
                         @RequestParam String lastnameOfUser,
                         @RequestParam String emailOfUser,
                         @RequestParam String phoneOfUser){
        this.authService.update(username,usernameOfUser, nameOfUser, lastnameOfUser, emailOfUser, phoneOfUser);
        return "redirect:/userAccount";

    }

    @PostMapping("/user/delete/{username}")
    public String delete(@PathVariable String username){
        this.authService.delete(username);
        return "redirect:/home";
    }



@Autowired
OrderServiceImpl serI;
    @RequestMapping("/deleteOrdersMy/{orderId}")
    public String deleteOrder(@PathVariable(name="orderId") long orderId,
//                              @RequestParam(name="passEnterSeats",required=false) Long passEnterSeats,
                              Model model) {
//RouteInfo route=new RouteInfo();
     Optional<Order> ooo=orderService.findById(22L);

//        List<Order> myorder=orderRepository.findByRouteIdUser(orderId);
        Optional<Order> order = this.orderService.findByIdd(22);
//        int passEnterSeats =order.getPassEnterSeats();
//        myorder.ifPresentOrElse(
//                value -> System.out.println("Found: " + value),
//                () -> System.out.println("Not found")
//        );
        LOGGER.info("passEnterSeats",order.toString());
//
//
//route.setSeats_left(route.getSeats_left()+passEnterSeats);
//            serI.deleteO(orderId);


            return "redirect:/userAccount/myorders";
      }

}