package com.saurav.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saurav.cms.entity.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long>{
}