package com.example11.demo.repository;

import com.example11.demo.model.enumerations.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Object> {
}
