package com.nsdl.searchEng.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.nsdl.searchEng.model.University;


public interface SearchService {
	
	public void saveCountry(List<University> country);

	public List<University> findAllByUniversityName(String name);

	public List<University> getAll();

	public Page<University> getAllUniversity(Integer pageNo, Integer pageSize);

}
