package com.example11.demo.repository;


import com.example11.demo.model.enumerations.RouteInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SearchRepo extends JpaRepository<RouteInfo, Long> {


    @Query("from RouteInfo WHERE fromA=:fromA and toA=:toA and dept_date=:dept_date and seats_left>0 and  dept_date>=CURRENT_DATE and dept_time>CURRENT_TIME")
    List<RouteInfo> findRoutes(@Param("fromA") String fromA, @Param("toA") String toA,
                               @Param("dept_date") String dept_date);


    @Query("from RouteInfo WHERE fromA like 'Витебск' or toA like 'Витебск' and seats_left>0 and  dept_date>=CURRENT_DATE and dept_time>CURRENT_TIME")
    List<RouteInfo> findRoutesVitebsk();
}