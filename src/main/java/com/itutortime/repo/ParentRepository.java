package com.itutortime.repo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itutortime.model.ParentChild;
import com.itutortime.model.ParentReg;

@Repository
public class ParentRepository extends GenRepository {
	@Autowired
	ParentCRUDRepository parentRepository;
	
	@Autowired
	ParentChildCRUDRepository parentChildRepository;
	

	private List<Map<String, Object>> getCenter(String user) {
		String sql="select * from parent_reg where Email='"+user+"'";		
		return template.query(sql, r);
	}

	public String register(String user, String pass, String referrerEmail) {
		List<Map<String, Object>> a = getUser(user);
		if (a != null && ! a.isEmpty())
			return "User  " + user + " exists";
			//return "Registration succeeded";
		
		
		ParentReg reg=new ParentReg();
		reg.setUsername(user);
		reg.setEmail(user);
		reg.setPassword(pass);
		
		String msg = "Registration success";
		
		if (referrerEmail != null) {
			List<Map<String, Object>> a2 = getUser(referrerEmail);
			if (a2==null || a2.isEmpty()) {
				msg = "referrer email does not exist";
			} else {
				reg.setReferreremail(referrerEmail);
			}
		}
		
		parentRepository.save(reg);
		
		return msg;
	}


	Long value(Long v1, Long v2) {
		if (v1 !=null )
			return v1;
		return v2;
	}
	
	String value(String v1, String v2) {
		if (v1 !=null )
			return v1;
		return v2;
	}

	public String update(ParentReg p) {
		List<Map<String, Object>> a = getUser(p.getEmail());
		
		System.out.println("here1 " + a);
		if (a != null && ! a.isEmpty()) {
			Map<String, Object> aa = a.get(0);
			System.out.println("here2 " + aa);	
			if (p.getId()==0) {
				Integer xx = (Integer) aa.get("Id");
				p.setId(xx);
			} 
			
			System.out.println(p.getPassword() + " new " +  aa.get("Password"));
			
			p.setAnswer(value(p.getAnswer(),(String) aa.get("Answer")));
			p.setMobile(value(p.getMobile(), (Long) aa.get("Mobile")));
			p.setName(value(p.getName(), (String) aa.get("Name") ));
			p.setPassword(value(p.getPassword(), (String) aa.get("Password")));
			p.setQuestion(value(p.getQuestion(), (String) aa.get("Question")));
			p.setZip(value(p.getZip(), (String) aa.get("zip")));
			p.setUsername(value(p.getUsername(), p.getEmail()));
			p.setAddress(value(p.getAddress(), (String)aa.get("address")));
			p.setCity(value(p.getCity(), (String)aa.get("city")));
			p.setState(value(p.getState(), (String)aa.get("state")));			
		}		
		
		parentRepository.save(p);
		return "Update success";		
	}
	
	public String add(ParentChild p) {
		parentChildRepository.save(p);
		return "add Child success";		
	}
}
