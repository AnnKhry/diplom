package com.example11.demo.repository;

import com.example11.demo.model.enumerations.Order;
import com.example11.demo.model.enumerations.RouteInfo;
import com.example11.demo.model.enumerations.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

//    @Query("from Order where oUser=("from User )")
//    List<Order> orderHistory(@Param("oUser") long oUser);
//    Select * from order_table where o_user_id= (SELECT id FROM users_table WHERE name Like 'Anna')


    @Query("Select p from Order p WHERE p.orderId = ?1")
    public List<Order> searchOrders(Long keyword);


    Optional<Order> findById(long orderId);
//   String  findByEEmail(String email);

    @Query("from Order p WHERE p.oUser.id = ?1")
    public List<Order> searchOrdersU(long iU);


    @Query( " from Order p WHERE p.oRoute.id = ?1")
   public List<Order> findByRouteId(long id);
    @Query( " from Order p WHERE p.orderId= ?1")
    public List<Order> findByRouteIdUser(Long id);

}
