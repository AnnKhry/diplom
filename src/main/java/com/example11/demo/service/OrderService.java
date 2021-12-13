package com.example11.demo.service;



import com.example11.demo.dao.OrderRequest;
import com.example11.demo.model.enumerations.Order;
import com.example11.demo.model.enumerations.User;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public interface OrderService {
    public Object bookRoute(OrderRequest orderRequest) throws IOException, MessagingException;


//    public List orderUsers(ResultSet result) throws IOException, MessagingException, SQLException;
Optional<Order> findByIdd(long orderId);


    Optional<Order> findById(Long orderId);

//    List<Order> findByUserId(long oUser);
public List<Order> listAllOrdersU(Long iU);
//    public List<Order> listAllOrdersRoutes(Long id);
    void setPassEnterSeats(int num);




}
