package com.example11.demo.repository;

import com.example11.demo.model.enumerations.RouteInfo;
import com.example11.demo.model.enumerations.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface RouteRepository extends JpaRepository<RouteInfo,Long>, PagingAndSortingRepository<RouteInfo,Long> {




	@Query("from RouteInfo WHERE fromA=:fromA and toA=:toA and dept_date=:dept_date")
	List<RouteInfo> findRoutes(@Param("fromA") String fromA, @Param("toA") String toA,
							   @Param("dept_date") String dept_date);

	Optional<RouteInfo> findById(Long routeId);
}
