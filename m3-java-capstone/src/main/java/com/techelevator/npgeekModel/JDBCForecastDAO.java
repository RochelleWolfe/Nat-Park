package com.techelevator.npgeekModel;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


@Component
public class JDBCForecastDAO implements ForecastDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCForecastDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Forecast> getFiveDayForecast(String parkcode){
		List<Forecast> allForecasts = new ArrayList<>();
		String sqlSelectAllForecasts = "SELECT * " + 
										"FROM weather " + 
										"WHERE parkcode = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllForecasts, parkcode);
		while(results.next()) {
			allForecasts.add(mapRowToForecast(results));
		}
		return allForecasts;
	}

	private Forecast mapRowToForecast(SqlRowSet row) {
		Forecast forecast = new Forecast();
		forecast.setParkCode(row.getString("parkcode"));
		forecast.setDay(row.getInt("fivedayforecastvalue"));
		forecast.setLowF(row.getInt("low"));
		forecast.setHighF(row.getInt("high"));
		forecast.setForecast(row.getString("forecast"));
		
		return forecast;
	}
	
}