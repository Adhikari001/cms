package com.saurav.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saurav.cms.entity.person.MyUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Long>{

    @Query(value = "select person from MyUser person where person.email = :email")
    Optional<MyUser> findPersonByEmail(String email);

    MyUser findByUsername(String username);

    @Query(value = "select person from MyUser person where person.email = :email")
    MyUser findByEmail(String email);
}
