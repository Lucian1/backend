package com.itutortime.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itutortime.model.ParentChild;
import com.itutortime.model.ParentReg;
import com.itutortime.repo.ParentRepository;

@RestController

//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin
@RequestMapping("/itutortime/api")
public class ParentController extends BaseController {
    @Autowired
    private ParentRepository itemRepo;

    @RequestMapping("/parentLogin.php")    
    @ResponseBody
    public Map getParent(@RequestParam("user") String user,@RequestParam("pass") String pass){
    	List x = itemRepo.login(user, pass);
        return getDataMap(x);
    }



    @PostMapping("/parentLogin.php")    
    @ResponseBody
    public Map getParent(@RequestBody ParentReg user){
    	List x = itemRepo.login(user.getEmail(), user.getPassword());
        return getDataMap(x);
    }

    /** @RequestMapping("/parentReg.php")
    @ResponseBody
    public String parentReg(@RequestParam("user") String user,@RequestParam("pass") String pass){
    	return itemRepo.register(user, pass);    	
    }    **/

    @PostMapping("/parentReg.php")
    @ResponseBody
    public String parentReg(@RequestBody ParentReg parent) {
    	System.out.println(parent.getUsername()+ " " + parent.getPassword() + " " + parent.getReferreremail() +"\n\n\n");
    	return itemRepo.register(parent.getUsername(), parent.getPassword(), parent.getReferreremail());    	
    }
    
    @RequestMapping("/parentUpdate.php")
    @ResponseBody
    public String update(@RequestParam("user") String user, @RequestParam ("pass") String pass, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("zip") String zip) {
    	ParentReg p=new ParentReg();
    	p.setUsername(user);
    	p.setPassword(pass);
    	p.setName(name);
    	p.setZip(zip);
    	p.setMobile(new Long(phone));
    	p.setEmail(user);
    	return itemRepo.update(p);
    	//return itemRepo.update(user, pass, name, phone, zip);
    }
    
    @PostMapping("/parentUpdate.php")
    @ResponseBody
    public String update(@RequestBody ParentReg parent) {
    	System.out.println("update here\n\n\n");
    	//return itemRepo.update(parent.getUser(), parent.getPass(), parent.getName(), parent.getPhone(), parent.getZip());
    	return itemRepo.update(parent);
    }
    
    @PostMapping("/parentAdd.php")
    @ResponseBody
    public String addChild(@RequestBody ParentChild child) {
    	//return itemRepo.update(parent.getUser(), parent.getPass(), parent.getName(), parent.getPhone(), parent.getZip());
    	return itemRepo.add(child);
    }
    
    @PostMapping("/addCenterReview.php")
    @ResponseBody
    public String addReview(@RequestBody ParentChild child) {
    	return "";
    	//return itemRepo.addReview(child);
    }
}
