package com.techelevator.npgeekControllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.techelevator.npgeekModel.ParkDAO;

@Controller
public class HomeController {
	
	@Autowired
	private ParkDAO dao;
	
	@RequestMapping("/")
	public String displayHomePage(HttpServletRequest request) {
		request.setAttribute("parkList", dao.getAllParks());
		return "homepage";
	}
}