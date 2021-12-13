package com.example11.demo.repository;

import com.example11.demo.model.enumerations.Order;
import com.example11.demo.model.enumerations.RouteInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RouteInfoRepo extends JpaRepository<RouteInfo, Long> {
    @Query("Select p from RouteInfo p WHERE p.fromA like %?1% and  dept_date>=CURRENT_DATE and dept_time>CURRENT_TIME")
    public List<RouteInfo> search(String keyword);





//    @Query("Select p from RouteInfo p WHERE p.fromA like %?1% and p.toA like %?2% ")
//    public List<RouteInfo> searchVitebsk(String keywordfrom, String keywordto);


    @Query("Select p from RouteInfo p WHERE p.fromA like %?1% and p.toA like %?2% and  dept_date>=CURRENT_DATE and dept_time>CURRENT_TIME")
    public List<RouteInfo> searchAllroutes(String keywordfrom, String keywordto);


    @Query("Select p from RouteInfo p WHERE  p.fromA like %?1% and p.toA like %?2% and  dept_date>=CURRENT_DATE and dept_time>CURRENT_TIME")
    public List<RouteInfo> searchVitebsk(String keywordfromV, String keywordtoV);
    @Query("Select p from RouteInfo p WHERE p.fromA like 'Витебск' or p.toA like 'Витебск' and  dept_date>=CURRENT_DATE and dept_time>CURRENT_TIME")
    public List<RouteInfo> findAllVitebsk();

    @Query("Select p from RouteInfo p WHERE  p.fromA like %?1% and p.toA like %?2% and  dept_date>=CURRENT_DATE and dept_time>CURRENT_TIME ")
    public List<RouteInfo> searchBrest(String keywordfrom, String keywordto);
    @Query("Select p from RouteInfo p WHERE p.fromA like 'Брест' or p.toA like 'Брест' and  dept_date>=CURRENT_DATE and dept_time>CURRENT_TIME")
    public List<RouteInfo> findAllBrest();

    @Query("Select p from RouteInfo p WHERE  p.fromA like %?1% and p.toA like %?2% and  dept_date>=CURRENT_DATE and dept_time>CURRENT_TIME")
    public List<RouteInfo> searchMogilev(String keywordfrom, String keywordto);
    @Query("Select p from RouteInfo p WHERE p.fromA like 'Могилев' or p.toA like 'Могилев' and  dept_date>=CURRENT_DATE and dept_time>CURRENT_TIME")
    public List<RouteInfo> findAllMogilev();

    @Query("Select p from RouteInfo p WHERE  p.fromA like %?1% and p.toA like %?2% and  dept_date>=CURRENT_DATE and dept_time>CURRENT_TIME")
    public List<RouteInfo> searchBorisov(String keywordfrom, String keywordto);
    @Query("Select p from RouteInfo p WHERE p.fromA like 'Борисов' or p.toA like 'Борисов' and  dept_date>=CURRENT_DATE and dept_time>CURRENT_TIME")
    public List<RouteInfo> findAllBorisov();

    @Query("Select p from RouteInfo p WHERE p.fromA like %?1% and p.toA like %?2% and  dept_date>=CURRENT_DATE and dept_time>CURRENT_TIME")
    public List<RouteInfo> searchBaranovichi(String keywordfrom, String keywordto);
    @Query("Select p from RouteInfo p WHERE p.fromA like 'Барановичи' or p.toA like 'Барановичи' and  dept_date>=CURRENT_DATE and dept_time>CURRENT_TIME")
    public List<RouteInfo> findAllBaranovichi();

    @Query("Select p from RouteInfo p WHERE  p.fromA like %?1% and p.toA like %?2% and  dept_date>=CURRENT_DATE and dept_time>CURRENT_TIME")
    public List<RouteInfo> searchGomel(String keywordfrom, String keywordto);
    @Query("Select p from RouteInfo p WHERE p.fromA like 'Гомель' or p.toA like 'Гомель' and  dept_date>=CURRENT_DATE and dept_time>CURRENT_TIMEE")
    public List<RouteInfo> findAllGomel();

    @Query("Select p from RouteInfo p WHERE  p.fromA like %?1% and p.toA like %?2% and  dept_date>=CURRENT_DATE and dept_time>CURRENT_TIME")
    public List<RouteInfo> searchGrodno(String keywordfrom, String keywordto);
    @Query("Select p from RouteInfo p WHERE p.fromA like 'Гродно' or p.toA like 'Гродно' and  dept_date>=CURRENT_DATE and dept_time>CURRENT_TIME")
    public List<RouteInfo> findAllGrodno();

    @Query("Select p from RouteInfo p WHERE  p.fromA like %?1% and p.toA like %?2% and  dept_date>=CURRENT_DATE and dept_time>CURRENT_TIME")
    public List<RouteInfo> searchMinsk(String keywordfrom, String keywordto);
    @Query("Select p from RouteInfo p WHERE p.fromA like 'Минск' or p.toA like 'Минск' and  dept_date>=CURRENT_DATE and dept_time>CURRENT_TIME")
    public List<RouteInfo> findAllMinsk();





}
