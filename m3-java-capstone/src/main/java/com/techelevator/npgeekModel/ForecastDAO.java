package com.techelevator.npgeekModel;

import java.util.List;

import org.springframework.ui.ModelMap;

public interface ForecastDAO {

	public List<Forecast> getFiveDayForecast(String parkcode, ModelMap model);
	
}
