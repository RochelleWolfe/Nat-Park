package com.techelevator.npgeekControllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techelevator.npgeekModel.Forecast;
import com.techelevator.npgeekModel.ForecastDAO;
import com.techelevator.npgeekModel.Park;
import com.techelevator.npgeekModel.ParkDAO;

@Controller
public class ParkDetailsController {
	
	@Autowired
	private ParkDAO dao;
	
	@Autowired
	private ForecastDAO forecastDao;

	@RequestMapping(path="/parkdetails")
	public String displayParkDetails(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String parkCodeParam = request.getParameter("parkcode");
			request.setAttribute("park", getParkByCode(parkCodeParam));
			List<Forecast> forecasts = forecastDao.getFiveDayForecast(parkCodeParam);
			request.setAttribute("forecastList", forecasts);
			return "parkdetails";
		} catch (NumberFormatException | IndexOutOfBoundsException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
	}
	
	private Park getParkByCode(String parkcode) {
		
		for(Park p : dao.getAllParks()) {
			if (p.getParkCode().equals(parkcode)) {
				return p;
			}
		}
		return null;
	}
}
