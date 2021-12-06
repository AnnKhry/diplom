package com.example11.demo.service;



import com.example11.demo.dao.OrderRequest;

import javax.mail.MessagingException;
import java.io.IOException;

public interface OrderService {
    public Object bookRoute(OrderRequest orderRequest) throws IOException, MessagingException;

    void setPassEnterSeats(int num);
}
