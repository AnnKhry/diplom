package com.example11.demo.model.enumerations;



import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Data
@Entity
public class Ticket {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreated;

    @ManyToOne
    private User user;


    @ManyToMany
    private List<Order> orders;


    public Ticket(User user) {
        this.dateCreated = LocalDateTime.now();
        this.user = user;

        this.orders = new ArrayList<>();

    }

    public Ticket() {
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public void removeOrder(Order order) {
        this.orders.remove(order);
    }


}


