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
public class CenterController extends BaseController {
	
	@Autowired
	CenterRepository centerRepo;

	@PostMapping("/centerLogin.php")
	@ResponseBody
	public Map getCenter(@RequestBody CenterReg user) {
		return getDataMap(centerRepo.login(user.getUsername(), user.getPassword()));
	}


	@PostMapping("/centerReg.php")
	@ResponseBody
	public String centerReg(@RequestBody CenterReg center) {
		return centerRepo.register(center);
	}

	@RequestMapping("/centerList.php")
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


	@PostMapping(value = "/allTeachers")
	@ResponseBody
	public List allTeachers(@RequestBody CenterReg user) {
		// 
		return centerRepo.allTeachers(user.getId());
	}
	
	@PostMapping(value = "/allApprovedTeachers")
	@ResponseBody
	public List allApprovedTeachers(@RequestBody CenterReg user) {
		// 
		return centerRepo.allApprovedTeachers(user.getId());
	}
	
	@PostMapping(value = "/allPendingTeachers")
	@ResponseBody
	public List allPendingTeachers(@RequestBody CenterReg user) {
		// 
		return centerRepo.allPendingTeachers(user.getId());
	}

	
}
