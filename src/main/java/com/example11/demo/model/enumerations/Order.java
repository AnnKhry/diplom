package com.example11.demo.model.enumerations;

import lombok.Data;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Data
@Entity
@Transactional
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @OneToOne
    private User oUser;
    @OneToOne
    private RouteInfo oRoute;
    private LocalDateTime dateCreatedOrder;
    private int passEnterSeats;

//    @OneToOne
//    private   User  oEmail;

//    public User getoEmail() {
//        return oEmail;
//    }
//
//    public void setoEmail(User oEmail) {
//        this.oEmail = oEmail;
//    }



    public Long getId() {
        return orderId;
    }

//    public void setId(Long orderId) {
//        orderId = orderId;
//    }

    public User getoUser() {
        return oUser;
    }



    public LocalDateTime getDateCreatedOrder() {
        return dateCreatedOrder;
    }

    public void setDateCreatedOrder(LocalDateTime dateCreatedOrder) {
        this.dateCreatedOrder = dateCreatedOrder;
    }



    public Order() {
    }

    public int getPassEnterSeats() {
        return passEnterSeats;
    }

    public void setPassEnterSeats(int passEnterSeats) {
        this.passEnterSeats = passEnterSeats;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setoUser(User oUser) {
        this.oUser = oUser;
    }

    public RouteInfo getoRoute() {
        return oRoute;
    }

    public void setoRoute(RouteInfo oRoute) {
        this.oRoute = oRoute;
    }



    public Order(Long orderId, User oUser, RouteInfo oRoute, LocalDateTime dateCreatedOrder,
int passEnterSeats
    ) {
        this.orderId = orderId;
        this.oUser = oUser;
        this.oRoute = oRoute;
        this.dateCreatedOrder = dateCreatedOrder;
        this.passEnterSeats = passEnterSeats;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", oUser=" + oUser +
                ", oRoute=" + oRoute +
                ", dateCreatedOrder=" + dateCreatedOrder +
                ", passEnterSeats=" + passEnterSeats +
//                ", oEmail=" + oEmail +
                '}';
    }


}
