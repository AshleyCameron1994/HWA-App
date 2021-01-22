package com.qa.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.persistence.domain.MSealDomain;

public class MSealRepo extends JpaRepository<MSealDomain, Long>{

}
