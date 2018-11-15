package com.techelevator.npgeekControllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.npgeekModel.Forecast;
import com.techelevator.npgeekModel.ForecastDAO;
import com.techelevator.npgeekModel.Park;
import com.techelevator.npgeekModel.ParkDAO;

@Controller
@SessionAttributes("tempModel")
public class ParkDetailsController {

	@Autowired
	private ParkDAO dao;

	@Autowired
	private ForecastDAO forecastDao;

	ModelMap tempModel = new ModelMap("tempUnit","F");
	
	

	
	@RequestMapping(path = "/parkdetails", method = RequestMethod.GET)
	public String displayParkDetails(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//Plan A
		try {
			String parkCodeParam = request.getParameter("parkcode");
			request.setAttribute("park", getParkByCode(parkCodeParam));
			List<Forecast> forecasts = forecastDao.getFiveDayForecast(parkCodeParam, tempModel);
			request.setAttribute("forecastList", forecasts);

			
			
			return "parkdetails";
			}

			
		catch (NumberFormatException | IndexOutOfBoundsException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
	}

	@RequestMapping(path="/parkdetails", method=RequestMethod.POST)
	public String changeTempUnit(@RequestParam String tempUnit, String parkcode) {
		tempModel.addAttribute("parkcode", parkcode);
		if (tempUnit.contentEquals("F")){
			tempModel.addAttribute("tempUnit", "F");
			}
		else {tempModel.addAttribute("tempUnit", "C");
		}
		return "redirect:/parkdetails?parkcode="+parkcode;
	}
	
	
	private Park getParkByCode(String parkcode) {

		for (Park p : dao.getAllParks()) {
			if (p.getParkCode().equals(parkcode)) {
				return p;
			}
		}
		return null;
	}
}
