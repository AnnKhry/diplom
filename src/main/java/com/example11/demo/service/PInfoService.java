package com.example11.demo.service;


import com.example11.demo.model.enumerations.Order;
import com.example11.demo.model.enumerations.RouteInfo;
import com.example11.demo.repository.OrderRepository;
import com.example11.demo.repository.RouteInfoRepo;
import com.example11.demo.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PInfoService {
    @Autowired
    private RouteInfoRepo repo;

    public List<RouteInfo> listAll(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }
//    @Autowired
//    private OrderRepository orderRepository;
//    public List<Order> listAllRoutesOrders(Long keyword) {
//        if (keyword != null) {
//            return orderRepository.searchOrdersRoutes(keyword);
//        }
//        return orderRepository.findAll();
//    }





    public void save(RouteInfo route)
    {
        repo.save(route);
    }
    public RouteInfo getRoute(long id)
    {
        return repo.findById(id).get();
    }
    public void delete(long id)
    {
        repo.deleteById(id);
    }

    public List<RouteInfo> listAllroutes(String keywordfrom, String keywordto) {
        if (keywordfrom != null & keywordto != null) {
            return repo.searchAllroutes(keywordfrom, keywordto);
        }
        return repo.findAll();
    }

    public List<RouteInfo> listAllVitebsk(String keywordfromV, String keywordtoV) {
        if (keywordfromV != null & keywordtoV != null) {
            return repo.searchVitebsk(keywordfromV, keywordtoV);
        }
        return repo.findAllVitebsk();

    }


    public List<RouteInfo> listAllMinsk(String keywordfrom, String keywordto) {
        if (keywordfrom != null & keywordto != null) {
            return repo.searchMinsk(keywordfrom, keywordto);
        }
        return repo.findAllMinsk();
    }

    public List<RouteInfo> listAllMogilev(String keywordfrom, String keywordto) {
        if (keywordfrom != null & keywordto != null) {
            return repo.searchMogilev(keywordfrom, keywordto);
        }
        return repo.findAllMogilev();
    }

    public List<RouteInfo> listAllBrest(String keywordfrom, String keywordto) {
        if (keywordfrom != null & keywordto != null) {
            return repo.searchBrest(keywordfrom, keywordto);
        }
        return repo.findAllBrest();
    }

    public List<RouteInfo> listAllBaranovichi(String keywordfrom, String keywordto) {
        if (keywordfrom != null & keywordto != null) {
            return repo.searchBaranovichi(keywordfrom, keywordto);
        }
        return repo.findAllBaranovichi();
    }

    public List<RouteInfo> listAllBorisov(String keywordfrom, String keywordto) {
        if (keywordfrom != null & keywordto != null) {
            return repo.searchBorisov(keywordfrom, keywordto);
        }
        return repo.findAllBorisov();
    }

    public List<RouteInfo> listAllGomel(String keywordfrom, String keywordto) {
        if (keywordfrom != null & keywordto != null) {
            return repo.searchGomel(keywordfrom, keywordto);
        }
        return repo.findAllGomel();
    }

    public List<RouteInfo> listAllGrodno(String keywordfrom, String keywordto) {
        if (keywordfrom != null & keywordto != null) {
            return repo.searchGrodno(keywordfrom, keywordto);
        }
        return repo.findAllGrodno();
    }


}