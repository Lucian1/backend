package com.itutortime.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.hibernate.annotations.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itutortime.model.Announcements;
import com.itutortime.model.Communication;
import com.itutortime.model.GroupMembership;
import com.itutortime.model.ParentReg;
import com.itutortime.repo.AnnoucementCRUDRepository;
import com.itutortime.repo.AnnouncementRepository;
import com.itutortime.repo.CommunicationCRUDRepository;
import com.itutortime.repo.CommunicationRepository;
import com.itutortime.repo.GroupMembershipCRUDRepository;

@RestController
// @CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin
@RequestMapping("/itutortime/api")
public class AnnouncementsController extends BaseController {
	@Autowired
	AnnoucementCRUDRepository announcementsCommunictionCRUDRepo;
	
	@Autowired
	AnnouncementRepository announcementsRepository;
	
	@Autowired
	GroupMembershipCRUDRepository groupMembershipCRUDRepository;
	
	@PostMapping("/announcementsSave.php")
	@ResponseBody
	public String save(@RequestBody Announcements announcements) {
		announcements.setTime(new Timestamp(System.currentTimeMillis()));
		announcementsCommunictionCRUDRepo.save(announcements);
		return "success";	
	}
	

	@PostMapping("/findAllAnnouncements.php")
	@ResponseBody
	public List find(@RequestBody Announcements announcements) {
		return announcementsCommunictionCRUDRepo.findBySenderId(announcements.getSenderid());
			
	}
	

	@PostMapping("/findAnnouncements.php")
	@ResponseBody
	public List find(@RequestBody ParentReg parent) {
		return announcementsRepository.getAnnoucements(parent.getId());
			
	}
	


	@PostMapping("/addMembership.php")
	@ResponseBody
	public String save(@RequestBody GroupMembership membership) {
		groupMembershipCRUDRepository.save(membership);
		return "success";
			
	}
	
	
	
}
