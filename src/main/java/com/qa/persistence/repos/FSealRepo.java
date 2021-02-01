package com.qa.persistence.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.persistence.domain.FSealDomain;

@Repository
public interface FSealRepo extends JpaRepository<FSealDomain, Long>{
	
	List<FSealDomain> findByWeight(Double weight);

}
