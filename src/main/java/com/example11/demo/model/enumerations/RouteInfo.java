package com.example11.demo.model.enumerations;


import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
public class RouteInfo{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;



    private String fromA;

    private String toA;

    private String dept_date;
    private String dept_time;
    private  String arr_time;
    private  int seats_left;
    private   float seat_price;
    private   String status;





    public RouteInfo(Long id, String fromA, String toA, String dept_date,String dept_time,String arr_time, int seats_left, float seat_price, String status) {

    this.id = id;
        this.fromA = fromA;
        this.toA = toA;
        this.dept_date = dept_date;
        this.dept_time = dept_time;
        this.arr_time = arr_time;
        this.seats_left = seats_left;
        this.seat_price = seat_price;
        this.status = status;

    }

    public RouteInfo() {


    }



    public String getDept_time() {
        return dept_time;
    }

    public void setDept_time(String dept_time) {
        this.dept_time = dept_time;
    }



    public String getArr_time() {
        return arr_time;
    }

    public void setArr_time(String arr_time) {
        this.arr_time = arr_time;
    }

    public int getSeats_left() {
        return seats_left;
    }

    public void setSeats_left(int seats_left) {
        this.seats_left = seats_left;
    }

    public float getSeat_price() {
        return seat_price;
    }

    public void setSeat_price(float e_seat_price) {
        this.seat_price = e_seat_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromA() {
        return fromA;
    }

    public void setFromA(String fromA) {
        this.fromA = fromA;
    }

    public String getToA() {
        return toA;
    }

    public void setToA(String toA) {
        this.toA = toA;
    }

    public String getDept_date() {
        return dept_date;
    }

    public void setDept_date(String dept_date) {
        this.dept_date = dept_date;
    }

    public void setSeatsBooking(int SeatsOut){
        this. seats_left = this. seats_left - SeatsOut;
    }

    public void setSeatsCancelBooking(int SeatsOut){
        this. seats_left = this.seats_left + SeatsOut;
    }


}



