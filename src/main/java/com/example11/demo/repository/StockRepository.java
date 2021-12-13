package com.example11.demo.repository;


import com.example11.demo.model.enumerations.Stock;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository  extends CrudRepository<Stock,Long> {
}
