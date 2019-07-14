package com.itutortime.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itutortime.model.Announcements;


@Repository
public interface AnnoucementCRUDRepository extends  CrudRepository<Announcements, Integer> {
	
	 List<Announcements> findBySenderId(int Id);
}