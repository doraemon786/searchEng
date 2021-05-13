package com.nsdl.searchEng.utility;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.nsdl.searchEng.model.University;


@Component
public class Utility {

	/*
	 * this is a utility class it will read the json file to populate the data for
	 * db insertion from controller
	 */
	
	public List<University> InsertCountry() {
		List<University> list = new ArrayList<University>();
		University university = null;
		JSONParser parser = new JSONParser();
		try {

			Object obj = parser.parse(new FileReader("src//main//resources//brand.json"));
			// A JSON object. Key value pairs are unordered. JSONObject supports
			// java.util.Map interface.
			JSONArray jsonArray = (JSONArray) obj;

			for (Object o : jsonArray) {
				JSONObject person = (JSONObject) o;

				String name = (String) person.get("University");
				Long id = (Long) person.get("id");
				university = new University(id, name);
				list.add(university);
				System.out.println(university);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

}
