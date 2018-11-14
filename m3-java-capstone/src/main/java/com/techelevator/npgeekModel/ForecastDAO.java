package com.techelevator.npgeekModel;

import java.util.List;

public interface ForecastDAO {

	public List<Forecast> getFiveDayForecast(String parkcode);
	
}
