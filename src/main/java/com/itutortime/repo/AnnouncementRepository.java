package com.itutortime.repo;


import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class AnnouncementRepository extends GenRepository {
	
    /** select * from communication where fromType='Teacher' and fromId in (
    		select id from teacher_reg where centerId='6') **/
    public List getAnnoucements(int parentId){
    	String query = this.getSqlValue(".getAnnouncements");
        return template.query(query,new Object[]{parentId},r);
    }

}
