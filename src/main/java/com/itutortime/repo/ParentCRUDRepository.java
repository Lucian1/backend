package com.itutortime.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itutortime.model.ParentReg;

@Repository
public interface ParentCRUDRepository extends  CrudRepository<ParentReg, Integer> {
	
	
}