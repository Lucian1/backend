package com.itutortime.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itutortime.model.CenterReg;

@Repository
public interface CenterCRUDRepository extends  CrudRepository<CenterReg, Integer> {
	
	
}