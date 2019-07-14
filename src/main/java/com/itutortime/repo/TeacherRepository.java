package com.itutortime.repo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itutortime.model.CenterReg;
import com.itutortime.model.Ratings;
import com.itutortime.model.TeacherReg;

@Repository
public class TeacherRepository extends GenRepository {
	@Autowired
	RatingsCRUDRepository ratingsRepository;

	@Autowired
	TeacherCRUDRepository teacherRepository;
	
	public String approve(int id) {
		String sql = "update teacher_reg set Status='Approved' where Id=?";
		template.update(sql, id);
		return "teacher " + id + " approved";
	}

	public String register(TeacherReg teacher) {
		String user = teacher.getUsername();
		String sql = "select * from teacher_reg where userName='"+user+"'";
		List<Map<String, Object>> res = template.query(sql, r);
		if (res != null && res.size() >0)
			return "User " + user + " exists";
		else
			return save(teacher, teacherRepository, teacher.getEmail());
	}


	public String getStatus(int id) {
		String sql = "select status from teacher_reg where Id=?";
		return template.queryForObject(sql, new Object[]{id}, String.class);
	}
	
	public String authorize(int id) {
		String sql = "update teacher_reg set Status='Authorized' where Id=?";
		template.update(sql, id);
		return "teacher " + id + " authorized";
	}
	
	

}
