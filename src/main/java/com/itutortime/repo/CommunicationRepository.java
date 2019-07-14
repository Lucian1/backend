package com.itutortime.repo;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CommunicationRepository extends GenRepository {
	ColumnMapRowMapper r=new ColumnMapRowMapper() {
		public Map<String, Object> mapRow(ResultSet rs, int rowNum) {
			Map m=new HashMap();
			try {				
				ResultSetMetaData md=rs.getMetaData();
				int n=md.getColumnCount();
				 {

					for (int i=1; i<=n; i++) {
						Object key=md.getColumnName(i);
						Object value=rs.getObject(i);
						if (value != null && value.getClass().toString().trim().endsWith("java.sql.Timestamp")) {
							value=value.toString();
						} 
						m.put(key, value);
					}
					//rs.getObject(columnIndex);
					
				}
				System.out.println("m + " +m);
				return m;
				//return super.mapRow(rs, rowNum);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		};
	};
	
    /*Getting a specific item by item id from table*/
	


	/**select * from communication where fromType=? and fromId=? **/
    public List getCommunicationsFrom(int fromId, String fromType){
        String query = this.getSqlValue(".fromSql");
        return template.query(query,new Object[]{fromId, fromType},r);
    }


    public String getName(String type, int id) {
		String sql="select Name from "+type+"_reg where Id="+id;
		System.out.println(sql + " is sql");
		return template.queryForObject(sql, String.class);
	}
	
	/**select * from communication where toType=? and toId=? **/
    public List getCommunicationsTo(int toId, String toType){
    	String query = this.getSqlValue(".toSql");
        return template.query(query,new Object[]{toId, toType,toId, toType,toId, toType},r);
    }

    /**
     * select * from communication where toType='Teacher' and toId in (
select id from teacher_reg where centerId='6')
     */
    public List getCommunicationsToTeacher(int centerId){
    	String query = this.getSqlValue(".toTeacherSql");
        return template.query(query,new Object[]{centerId},r);
    }
    
    /** select * from communication where fromType='Teacher' and fromId in (
    		select id from teacher_reg where centerId='6') **/
    public List getCommunicationsFromTeacher(int centerId){
    	String query = this.getSqlValue(".fromTeacherSql");
        return template.query(query,new Object[]{centerId},r);
    }

	public List getCommunications(int fromid, String fromtype, int toid,
			String totype) {
		String query = this.getSqlValue(".fromToSql");
	    List<Map<String, Object>> l = template.query(query,new Object[]{fromid,fromtype,toid, totype,fromid,fromtype,toid, totype,fromid,fromtype,toid, totype},r);
	    if (l.size() >=1)
	    	System.out.println(l.get(0).get("time").getClass());
	    return l;
	}

}
