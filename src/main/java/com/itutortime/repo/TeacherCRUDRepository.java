package com.itutortime.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itutortime.model.Announcements;
import com.itutortime.model.CenterReg;
import com.itutortime.model.TeacherReg;

@Repository
public interface TeacherCRUDRepository extends  CrudRepository<TeacherReg, Integer> {	
	
}