package com.itutortime.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

//@CrossOrigin(origins = "http://localhost:4200")
//@RequestMapping("/itutortime/api")
public class BaseController {
	protected Map getDataMap(List x) {
		Map m=new HashMap();
        if (x != null && x.size()>0)
        	m.put("data",x);
        return m;
	}

}
