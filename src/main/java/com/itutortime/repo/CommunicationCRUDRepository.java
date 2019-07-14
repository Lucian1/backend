package com.itutortime.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itutortime.model.CenterReg;
import com.itutortime.model.Communication;

@Repository
public interface CommunicationCRUDRepository extends  CrudRepository<Communication, Integer> {
	
	
}