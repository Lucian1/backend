package com.itutortime.repo;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.itutortime.model.*;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface ParentChildCRUDRepository extends  CrudRepository<ParentChild, Integer> {
	
	
}