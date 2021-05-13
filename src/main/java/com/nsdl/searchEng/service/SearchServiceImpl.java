package com.nsdl.searchEng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nsdl.searchEng.dao.SearchDao;
import com.nsdl.searchEng.model.University;

@Service
public class SearchServiceImpl implements SearchService {

	
	@Autowired
	private SearchDao   searchDao;

	@Override
	public void saveCountry(List<University> country) {
		searchDao.deleteAll();
		searchDao.saveAll(country);
		
	}

	@Override
	public List<University> findAllByUniversityName(String name) {
		return searchDao.findAllByUniversityName(name);
	}

	@Override
	public List<University> getAll() {
		
		return searchDao.findAll();
	}

	@Override
	public Page<University> getAllUniversity(Integer pageNo, Integer pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		 
        Page<University> pagedResult = searchDao.findAll(paging);
         
      return pagedResult;
	}
	
	

}
