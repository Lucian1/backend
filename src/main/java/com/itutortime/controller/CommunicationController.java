package com.itutortime.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itutortime.model.Communication;
import com.itutortime.repo.CommunicationCRUDRepository;
import com.itutortime.repo.CommunicationRepository;

@RestController
// @CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin
@RequestMapping("/itutortime/api")
public class CommunicationController extends BaseController {
	@Autowired
	CommunicationCRUDRepository communictionCRUDRepo;
	
	@Autowired
	CommunicationRepository communictionRepo;

	@PostMapping("/messageSave.php")
	@ResponseBody
	public String save(@RequestBody Communication communication) {
		communication.setTime(new Timestamp(System.currentTimeMillis()));
		communictionCRUDRepo.save(communication);
		return "success";
	}

 	
	/**
	 * 

select * from communication where (fromType="Parent" and fromId='6') or 
(toType="Parent" and toId="6"
)

	 * @param id
	 * @param type
	 * @return
	 */

	@PostMapping("/fromMessages.php")
	@ResponseBody
	public List getFromEmails(@RequestBody Communication communication) {
		return sorted(communictionRepo.getCommunicationsFrom(communication.getFromid(), communication.getFromtype()));
	}
	

	
	@PostMapping("/fromToMessages.php")
	@ResponseBody
	public List getFromToEmails(@RequestBody Communication communication) {
		String fromtype = communication.getFromtype();
		int fromid = communication.getFromid();
		String totype = communication.getTotype();
		int toid = communication.getToid();
		
		List<Map> l = getMessages(fromtype, fromid, totype, toid);		
		List<Map> l2 = getMessages(totype, toid, fromtype, fromid);
		
		List merged = new ArrayList(l);
		merged.addAll(l2);
		
		merged=sorted(merged);
		return merged;
	}


	private List<Map> getMessages(String fromtype, int fromid, String totype,
			int toid) {
		List<Map> l = (communictionRepo.getCommunications(fromid, fromtype,toid, totype));		
		String fromName=communictionRepo.getName(fromtype, fromid);
		String toName=communictionRepo.getName(totype, toid);		
		addName(l, fromName, toName);
		return l;
	}


	private void addName(List<Map> l, String fromName, String toName) {
		for (Map m:l) {
			m.put("fromName",  fromName);
			m.put("toName",  toName);
			m.remove("Name");
		}
	}
	
	@PostMapping("/toMessages.php")
	@ResponseBody
	public List getToEmails(@RequestBody Communication communication) {
		return sorted(communictionRepo.getCommunicationsTo(communication.getToid(), communication.getTotype()));
	}
	
	@PostMapping("/toTeacherMessages.php")
	@ResponseBody
	public List getToTeacherEmails(@RequestBody Communication communication) {
		return sorted(communictionRepo.getCommunicationsToTeacher(communication.getToid()));
	}
	

	@PostMapping("/fromTeacherMessages.php")
	@ResponseBody
	public List getFromTeacherEmails(@RequestBody Communication communication) {
		return sorted(communictionRepo.getCommunicationsFromTeacher(communication.getFromid()));
	}
	
	public List sorted(List<Map> l) {
		Comparator<Map> c=new Comparator<Map>() {

			@Override
			public int compare(Map o1, Map o2) {
				return (o1.get("time").toString().compareTo(o2.get("time").toString()));
				
			}


			
		};
		
		Collections.sort(l, c);
		return l;
	}
}
