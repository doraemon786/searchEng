package com.nsdl.searchEng.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nsdl.searchEng.model.University;
import com.nsdl.searchEng.service.SearchService;
import com.nsdl.searchEng.utility.Utility;

@Controller
public class SearchController {
	@Autowired
	private SearchService searchService;

	@Autowired
	private Utility util;

	
	public final Integer pageSize=10;
	public final Integer pageNo=0;
	
	/* this method will insert the 
	 *json data in db for application to use */
	@RequestMapping("/")
	public String saveUniversity(Model model) {
		List<University> universityList = util.InsertCountry();
		
		searchService.saveCountry(universityList);
		model.addAttribute("currentPage", 0);
		return "index";
	}

	
	/*
	 * this method will initiate by 
	 * ajax caall when use type a word in search panel
	 */
	@RequestMapping(value = "/getUniversity", method = RequestMethod.GET)
	public @ResponseBody List<String> getMachedNames(@RequestParam("term") String name) {
		List<University> matchName = searchService.findAllByUniversityName(name);
		List<String> result = matchName.stream().limit(10).map(n -> n.getUniversityName()).collect(Collectors.toList());
		System.out.println("Test result" + result);
		return result;
	}
	
	/* method to test the
	 *  data the data insertion in db */

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public @ResponseBody List<University> getAll() {
		List<University> allUniv = searchService.getAll();
		System.out.println(allUniv);
		return allUniv;
	}

	/*
	 * method to support pagination
	 *  will be called on next page or page number click
	 */
	@GetMapping("/getUnivList/{pageNo}")
	public String getAllUniversity(
			@PathVariable String pageNo, Model model) {
		
		Page<University> page = searchService.getAllUniversity(Integer.valueOf(pageNo), pageSize);
		List<University> listUniveristy = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listUniveristy", listUniveristy);
		return "index";
	}
	
	/* this method will be
	 *  called on form submit
	 *  it populate the data to support the pagination */
	
	@RequestMapping({"getUnivList/showPaginationDemo","showPaginationDemo"})
	public String getAllUniversity(Model model) {
		
		Page<University> page = searchService.getAllUniversity(pageNo, pageSize);
		List<University> listUniveristy = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listUniveristy", listUniveristy);
		return "index";
	}

}
