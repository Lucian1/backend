package com.itutortime.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itutortime.model.CenterReg;
import com.itutortime.model.ParentReg;
import com.itutortime.model.Ratings;
import com.itutortime.model.TeacherReg;
import com.itutortime.repo.CenterRepository;
import com.itutortime.repo.TeacherCRUDRepository;
import com.itutortime.repo.TeacherRepository;

@RestController
// @CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin
@RequestMapping("/itutortime/api")
public class TeacherController extends BaseController {
	@Autowired
	TeacherRepository teacherRepo;

	@PostMapping("/teacherLogin.php")
	@ResponseBody
	public Map getTeacher(@RequestBody TeacherReg user) {
		return getDataMap(teacherRepo.login(user.getUsername(), user.getPassword()));
	}


	@PostMapping("/teacherReg.php")
	@ResponseBody
	public String teacherReg(@RequestBody TeacherReg center) {
		return teacherRepo.register(center);
	}

	@PostMapping(value = "/approveTeacher.php")
	@ResponseBody
	public String approve(@RequestBody TeacherReg teacher) {
		// 
		return teacherRepo.approve(teacher.getId());
	}

	@PostMapping(value = "/authorizeTeacher.php")
	@ResponseBody
	public String authorize(@RequestBody TeacherReg teacher) {
		// 
		return teacherRepo.authorize(teacher.getId());
	}
	

	@PostMapping(value = "/getTeacherStatus.php")
	@ResponseBody
	public String getStatus(@RequestBody TeacherReg teacher) {
		// 
		return teacherRepo.getStatus(teacher.getId());
	}
	
/**	@RequestMapping("/centerList.php")
	@ResponseBody
	public Map getAllItems() {
		Map m = new HashMap();
		m.put("data", centerRepo.getAllItems());
		return m;
	}

	@RequestMapping(value = "/centerList.php/{Id}")
	@ResponseBody
	public Object getItem(@PathVariable("Id") int itemId) {
		return centerRepo.getItem(itemId);
	}

	@RequestMapping(value = "/centerList.php/reviews/{Id}")
	@ResponseBody
	public List getReview(@PathVariable("Id") int itemId) {
		return centerRepo.getReviews(itemId);
	}

	@PostMapping(value = "/centerList.php/addReview/{Id}")
	@ResponseBody
	public String addReview(@PathVariable("Id") Long id,
			@RequestBody Ratings ratings) {
		ratings.setRateeid(id);
		ratings.setRateetype("Center");
		centerRepo.addReview(ratings);
		return "review added successfully";
	}
**/
}
