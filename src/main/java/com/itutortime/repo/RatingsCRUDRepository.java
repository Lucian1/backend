package com.itutortime.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itutortime.model.Ratings;

@Repository
public interface RatingsCRUDRepository extends  CrudRepository<Ratings, Integer> {
	
	
}