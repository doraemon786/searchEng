package com.nsdl.searchEng.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nsdl.searchEng.model.University;

@Repository
public interface SearchDao extends JpaRepository<University, Integer> {

	
	/*
	 * custom query is used to support req of search by ajax call with data in
	 * ascending order
	 */	@Query("SELECT u FROM University u where lower(u.universityName) like lower(concat(:universityName,'%')) order by u.universityName ASC")
	List<University> findAllByUniversityName(@Param("universityName") String universityName);

}