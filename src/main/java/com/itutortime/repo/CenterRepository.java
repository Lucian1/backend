package com.itutortime.repo;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.stereotype.Repository;

import com.itutortime.model.CenterReg;
import com.itutortime.model.Ratings;

@Repository
public class CenterRepository extends GenRepository {
	
	@Autowired
	RatingsCRUDRepository ratingsRepository;
	
	@Autowired
	CenterCRUDRepository centerRepository;

    /*Getting a specific item by item id from table*/
    public List getReviews(int itemId){
        String query = this.getSqlValue(".selectReviews");
        return template.query(query,new Object[]{itemId},r);
    }

    public void addReview(Ratings r2) {    
    	Long rateeId = r2.getRateeid();
    	Long raterId = r2.getRaterid();
    	String rateeType = r2.getRateetype();
    	
    	String query = this.getSqlValue(".deleteReviews");
    	template.update(query, new Object[]{raterId, rateeId, rateeType});
    	
    	ratingsRepository.save(r2);	
    }

	public String register(CenterReg center) {
		String user = center.getUsername();
		String sql = "select * from center_reg where userName='"+user+"'";
		CenterCRUDRepository rep = centerRepository;
		
		return save(center, user, sql, rep);
	}

	protected String save(CenterReg center, String user, String sql,
			CenterCRUDRepository rep) {
		List<Map<String, Object>> res = template.query(sql, r);
		if (res != null && res.size() >0)
			return "User " + user + " exists";
		else {		
			return save(center, rep, center.getEmail());
		}
	}


	public List allTeachers(int centerId) {
		String sql="select * from teacher_reg where centerId='"+centerId+"'";
		return template.query(sql, r);		
	}

	public List allApprovedTeachers(int centerId) {
		String sql="select * from teacher_reg where centerId='"+centerId+"' and status='Approved'";
		return template.query(sql, r);	
	}

	public List allPendingTeachers(int centerId) {
		String sql="select * from teacher_reg where centerId='"+centerId+"' and status='Pending'";
		return template.query(sql, r);
		
	}

}
