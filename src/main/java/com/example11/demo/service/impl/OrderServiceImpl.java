package com.example11.demo.service.impl;

import com.example11.demo.dao.ConnectorDB;
import com.example11.demo.dao.OrderRequest;
import com.example11.demo.model.MailSender;
import com.example11.demo.model.enumerations.Order;
import com.example11.demo.model.enumerations.RouteInfo;
import com.example11.demo.model.enumerations.User;
import com.example11.demo.model.exceptions.RouteNotFound;
import com.example11.demo.repository.OrderRepository;
import com.example11.demo.repository.RouteRepository;
import com.example11.demo.repository.UserRepository;
import com.example11.demo.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Date;


@Service
@Transactional
public class OrderServiceImpl implements OrderService {


    public OrderServiceImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void setPassEnterSeats(int num) {

    }

    MailSender mailSender;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    OrderService orderService;
    @Autowired
    private RouteRepository routeRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    double cost;
    int seatcount = 0;

    private final EntityManager em;
    @Override
    public Optional<Order> findByIdd(long orderId) {
        return this.orderRepository.findById(orderId);
    }


    public List<Order> listAllOrders(Long keyword) {
        if (keyword != null) {
            return orderRepository.searchOrders(keyword);
        }
        return orderRepository.findAll();
    }

//
//    public List<Order> listAllOrdersRoutes(Long id) {
//        if (id != null) {
//            return orderRepository.searchOrdersRoutes(id);
//        }
//        return orderRepository.findAll();
//    }

    public List<Order> listAllOrdersU(Long iU) {
        if (iU != null) {
            return orderRepository.searchOrdersU(iU);
        }
        return orderRepository.findAll();
    }





    public void deleteO(long orderId)
    {
        orderRepository.deleteById(orderId);
    }


//    @Override
//    public  List orderUsers(ResultSet result) throws  SQLException {


//    Optional<User> userOptional=userRepository.findById(userId);
//    if (!userOptional.isPresent()) {
//        throw new RouteNotFound("No user found with id "+userId);
//    }
//    User user =userOptional.get();
//    String sql = "Select order_id from order_table where o_user_id= (SELECT id FROM users_table WHERE name Like 'Anna'";
//    Query query = (Query) em.createNativeQuery(sql);
//        LOGGER.info("NNNN" + query.value());

//    return query.value();
//Principal principal;
//       String  username;
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        username = auth.getName();
//        LOGGER.info("NNNN" + username);
//
//
//        LOGGER.info("NNNN" + userRepository.findByUsername(username));
//        LOGGER.info("NNNN" + userRepository.findByUsername(username).get().getId());
////        LOGGER.info("NNNN"+ orderService.findById(userRepository.findByUsername(username).get().getId()));
//        LOGGER.info("NNNN" + orderRepository.findById(userRepository.findByUsername(username).get().getId()));
//        long i = userRepository.findByUsername(username).get().getId();
//        String sql = "Select * from order_table where o_user_id=" + i;
//        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/infodb?useUnicode=true&serverTimezone=UTC", "root", "root");
//        Statement stmt = con.createStatement();
//
//         result = stmt.executeQuery("SELECT route_info.froma, route_info.toa, route_info.dept_date, route_info.dept_time,route_info.arr_time, order_table.date_created_order,order_table.order_id, order_table.pass_enter_seats From order_table, route_info where  order_table.o_route_id = route_info.id and order_table.o_user_id=" + i);
//        while(result.next()) {
//            Long order_id = result.getLong("order_id");


//        ResultSetMetaData metadata = result.getMetaData();
//        int numberOfColumns = metadata.getColumnCount();
//        List<String> list = new ArrayList<>(numberOfColumns);
//        while (result.next()) {
//            int n = 1;
//            while (n <= numberOfColumns) {
//                list.add(result.getString(n++));
//            }
//        }
//        return list;
//    }

//
//            String froma = result.getString("fromA");
////    Query query = (Query) em.createNativeQuery(sql);
////           String fromA=
////                   String toA=result.getLong("order_id");
//                  String dateCreateOrder=result.getString("date_created_order");
//                    int passSeats=result.getInt("pass_enter_seats");
//            LOGGER.info("NNNN" + dateCreateOrder+froma+ order_id);
//
////        Long userId=orderRequest.getUserId();
////        Optional<User> userOptional=userRepository.findById(userId);
////        if (!userOptional.isPresent()) {
////            throw new RouteNotFound("No user found with id "+userId);
////        }
////        User user =userOptional.get();
////        String email=user.getEmail();
//        }
//    return result;
//    }

    @Override
    public Object bookRoute(OrderRequest orderRequest) throws IOException, MessagingException {
        // make payment here
        // if the payment is successful proceed..
        LOGGER.info("Inside bookRoute()");
        Long routeId = orderRequest.getRouteId();

        Long userId = orderRequest.getUserId();
        int num = orderRequest.getPassEnterSeats();


        Optional<RouteInfo> routeOptional = routeRepository.findById(routeId);
        if (!routeOptional.isPresent()) {
            throw new RouteNotFound("No route found with id " + routeId);
        }
        RouteInfo route = routeOptional.get();


        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new RouteNotFound("No user found with id " + userId);
        }
        User user = userOptional.get();


//        LOGGER.info("Flight found with id: {}",email);
//        User email =emailOptional.get();
//
        Order order = new Order();
        order.setoRoute(route);
        order.setoUser(user);

//        order.setoEmail(email);

        order.setPassEnterSeats(num);
        LOGGER.info("NNNN" + num);


        order.setDateCreatedOrder(LocalDateTime.now());


        route.setSeats_left(route.getSeats_left() - num
//                order.getPassEnterSeats()
        ); //reduce availability by 1

        LOGGER.info("Кол-во билетов уменьшилось на num");


        String email = user.getEmail();

        final Order savedOrder = orderRepository.save(order);
        LOGGER.info("Saving the reservation:" + order);

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("prostakova1.20@gmail.com", "62esipesAnn");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("prostakova1.20@gmail.com", false));
        LOGGER.info("Inside bookRoute() email" + email);
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
//        msg.setSubject("Tutorials point email");
        msg.setContent("Билет", "text/html");
        msg.setSentDate(new Date());
        msg.setText(String.valueOf(savedOrder));

        MimeBodyPart messageBodyPart = new MimeBodyPart();

        Long orderId = order.getId();
        String name = user.getName();
        String lastname = user.getName();
        String fromA = route.getFromA();
        String toA = route.getToA();
        String dept_date = route.getDept_date();
        String dept_time = route.getDept_time();
        String arr_time = route.getArr_time();
        Date dateorder = new Date();
//        dateorder.toString();


        messageBodyPart.setContent("Здравствуйте, уважаемый пользователь! <br> Ваш заказ  № " + orderId + " успешно оформлен. ", "text/html; charset=utf-8");

//

        String text = "<br> Информация по заказу №" + orderId + ".<p> <p><table><thead><tr> <th>Имя</th><td>" + name + "</td></tr><tr><th>Фамилия</th> <td>" + lastname + "</td></tr><tr><th></th><td></td></tr><tr><th>Город отправления</th> <td>" + fromA + "</td> </tr> <tr> <th>Город назначения</th><td>" + toA + "</td></tr><tr> <th>Дата отправления</th><td>" + dept_date + "</td> </tr><tr> <th>Время отправления</th> <td>" + dept_time + "</td></tr><tr> <th>Время прибытия</th> <td>" + arr_time + "</td></tr> <tr><th></th><td></td></tr><tr><th>Время оформления заказа</th><td>" + dateorder + "</td>                       </tr>"; // строка для записи
        try (FileOutputStream fos = new FileOutputStream("src/main/resources/emailfile.html")) {
            // перевод строки в байты
            byte[] buffer = text.getBytes();

            fos.write(buffer, 0, buffer.length);
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
        System.out.println("The file has been written");


        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        MimeBodyPart attachPart = new MimeBodyPart();

        attachPart.attachFile("src/main/resources/emailfile.html"); //расположение файла билета
        multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
        Transport.send(msg);


        return savedOrder;

    }

    @Override
    public Optional<Order> findById(Long orderId) {
        return this.orderRepository.findById(orderId);
    }

//    @Override
//    public List<Order> findByUserId(long userId) {
//        return this.orderRepository.findByUserId(userId);
//    }

RouteService ser;
    public void updateRoute(RouteInfo route) {
       ser.updateRoute(route);
    }
}