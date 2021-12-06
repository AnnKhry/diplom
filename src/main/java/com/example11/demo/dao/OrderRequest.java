package com.example11.demo.dao;

public class OrderRequest {
    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    private Long routeId;
    private Long userId;
    private int passEnterSeats;
//    private String userEmail;
//
    public int getPassEnterSeats() {
        return passEnterSeats;
    }

    public void setPassEnterSeats(int passEnterSeats) {
        this.passEnterSeats = passEnterSeats;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

//    public String getUserEmail() {
//        return userEmail;
//    }
//
//    public void setUserEmail(String userEmail) {
//        this.userEmail = userEmail;
//    }



    //    private String orderFromA;
//    private String orderToA;
//    private String orderDept_date;
//    private String orderDept_time;
//    private String orderArr_time;
//    private Long userId;
//    private String orderUsername;
//    private String orderName;


//    private String orderLastName;
//    private String orderEmail;

//
//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {

    @Override
    public String toString() {
        return "OrderRequest{" +
                "routeId=" + routeId +
                ", userId=" + userId +
                ", passEnterSeats=" + passEnterSeats +
                '}';
    }

}
