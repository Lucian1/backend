package com.itutortime.repo;

import com.itutortime.model.ConfigProperties;
import com.itutortime.model.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public abstract class GenRepository {	
	private static final String SELECT = ".select";
	private static final String INSERT = ".insert";
	private static final String LOGIN_SELECT = ".loginSelect";

	public List login(String email, String pass) {
		return template.query(getLoginSelect(), new Object[]{email, pass}, r);
	}
	
	@Autowired
	ConfigProperties configProp;

    @Autowired
    JdbcTemplate template;

    //ColumnMapRowMapper r=new ColumnMapRowMapper();
    

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
    
    protected String getInsert() {
    	return getValue(getClassName()+INSERT);
	}

    protected String getSqlValue(String sss) {
    	return getValue(getClassName()+sss);
	}
    
	private String getValue(String sss) {
		System.out.println(sss+"\n\n");
    	return configProp.getConfigValue(sss);
	}

    protected String getSelect() {
    	return getValue(getClassName()+SELECT);
    }

    protected String getLoginSelect() {
    	return getValue(getClassName()+LOGIN_SELECT);
    }
    
	private String getClassName() {
		return this.getClass().getSimpleName();
	}
    
    /*Getting all Items from table*/
    public List getAllItems(){
		return template.query(getSelect(), r);
    }
    
    /*Getting a specific item by item id from table*/
    public Object getItem(int itemId){
        String query = this.getSqlValue(".select1");
        return template.queryForObject(query,new Object[]{itemId},r);
    }
    
    
    /*Adding an item into database table*/
    public int addItem(int id,String name,String category){
        String query = "INSERT INTO ITEM VALUES(?,?,?)";
        return template.update(query,id,name,category);
    }
    /*delete an item from database*/
    public int deleteItem(int id){
        String query = "DELETE FROM ITEM WHERE ID =?";
        return template.update(query,id);
    }
    

	protected List<Map<String, Object>> getUser(String email) {
		//String sql="select * from parent_reg where Email='"+user+"'";
		String sql="select id from parent_reg where Email='"+email + "' union select id from center_reg where Email='" + email + "' union select id from teacher_reg where Email='"+email+"'";
		System.out.println("sql is " + sql + "\n\n\n");
		return template.query(sql, r);
	}
	
	protected boolean emailAvailable(String e) {
		List<Map<String, Object>> a = getUser(e);
		return a==null || a.isEmpty();
	}


	protected <T> String save(T teacher, CrudRepository<T, Integer> repo,
			String email) {
		if (emailAvailable(email)) {

			repo.save(teacher);
			return "success";
		} else {
			return "email not available";
		}
	}
}
