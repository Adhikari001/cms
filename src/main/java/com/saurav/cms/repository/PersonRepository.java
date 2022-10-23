package com.saurav.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saurav.cms.entity.person.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
    
}