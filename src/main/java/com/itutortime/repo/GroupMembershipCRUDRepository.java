package com.itutortime.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itutortime.model.GroupMembership;

@Repository
public interface GroupMembershipCRUDRepository extends  CrudRepository<GroupMembership, Integer> {
	
	
}