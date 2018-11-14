package com.techelevator.npgeekModel;

import java.util.List;
import java.util.Map;

public interface SurveyDAO {
	public List<FavPark> listOfFavoriteParks();
	
	public void save(Survey survey) ;

}
