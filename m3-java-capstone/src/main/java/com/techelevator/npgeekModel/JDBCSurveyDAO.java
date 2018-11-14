package com.techelevator.npgeekModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCSurveyDAO implements SurveyDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCSurveyDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<FavPark> listOfFavoriteParks() {
		List<FavPark> listOfFavoriteParks= new ArrayList<>();
		FavPark fp = new FavPark();
		String sqlGetFavorites = "SELECT parkname, COUNT(surveyid) AS surveys " + 
				"FROM park " + 
				"INNER JOIN " + 
				"survey_result " + 
				"ON park.parkcode = survey_result.parkcode " + 
				"GROUP BY parkname " + 
				"ORDER BY surveys DESC;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetFavorites);
		while(results.next()) {
			fp.setParkname(results.getString("parkname"));
			fp.setNumSurveys(results.getInt("surveys"));
			listOfFavoriteParks.add(fp);
		}
		return listOfFavoriteParks;
	}

	public List<Survey> getAllSurveys() {
		List<Survey> allSurveys = new ArrayList<>();
		String sqlSelectAllSurveys = "SELECT * FROM survey_result;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllSurveys);
		while(results.next()) {
			allSurveys.add(mapRowToSurvey(results));
		}
		return allSurveys;
	}
	
	private Survey mapRowToSurvey(SqlRowSet row) {
		Survey survey = new Survey();
		survey.setId(row.getInt("surveyid"));
		survey.setParkCode(row.getString("parkcode"));
		survey.setEmail(row.getString("emailaddress"));
		survey.setState(row.getString("state"));
		survey.setActivityLevel(row.getString("activitylevel"));
		
		return survey;
	}
	
	public void save(Survey survey) {
		int id = getNextId();
		String sqlInsertSurvey = "INSERT INTO survey_result (surveyid, parkcode, emailaddress, state, activitylevel) " + 
								"VALUES (?, ?, ?, ?, ?);";
		jdbcTemplate.update(sqlInsertSurvey, id, survey.getParkCode(), survey.getEmail(), survey.getState(), survey.getActivityLevel());
		survey.setId(id);
	}
	
	private int getNextId() {
		String sqlSelectNextId = "SELECT NEXTVAL('seq_surveyId')";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectNextId);
		Integer id = null;
		if(results.next()) {
			id = results.getInt(1);
		} else {
			throw new RuntimeException("Something strange happened, unable to select next forum post id from sequence");
		}
		return id;
	}
	
	
}
