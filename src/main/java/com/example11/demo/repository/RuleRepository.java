package com.example11.demo.repository;

import com.example11.demo.model.enumerations.Post;
import com.example11.demo.model.enumerations.Rule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepository extends CrudRepository<Rule,Long> {
}
