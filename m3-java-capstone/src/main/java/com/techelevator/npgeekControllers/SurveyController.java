package com.techelevator.npgeekControllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.npgeekModel.ParkDAO;
import com.techelevator.npgeekModel.Survey;
import com.techelevator.npgeekModel.SurveyDAO;

@Controller
public class SurveyController {

	@Autowired
	ParkDAO dao;
	
	@Autowired
	SurveyDAO sDao;
	
	@RequestMapping(path="/survey", method=RequestMethod.GET)
	public String displayFavParkForm(HttpServletRequest request, Model modelHolder) {
		request.setAttribute("parkList", dao.getAllParks());
		if( ! modelHolder.containsAttribute("Survey")) {
			modelHolder.addAttribute("Survey", new Survey());
		}
		return "survey";
	}
	
	@RequestMapping(path="/favparks", method=RequestMethod.POST)
	public String submitFavParkForm(@Valid @ModelAttribute("Survey") Survey registrationFormValues,
			BindingResult result, RedirectAttributes flash, @RequestParam	String parkcode,
																			String email,
																			String state,
																			String activity
															) {
		if(result.hasErrors()) {
			flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "Survey", result);
			flash.addFlashAttribute("Survey", registrationFormValues);
			return "redirect:/survey";
		}
		
		Survey s = new Survey();
		s.setParkCode(parkcode);
		s.setEmail(email);
		s.setState(state);
		s.setActivityLevel(activity);
		sDao.save(s);
		return "favparks";
	}
	
	@RequestMapping(path="/favparks", method=RequestMethod.GET)
	public String displayFavParks(HttpServletRequest request) {
		request.setAttribute("favParksList", sDao.listOfFavoriteParks());
		return "favparks";
	}
}
