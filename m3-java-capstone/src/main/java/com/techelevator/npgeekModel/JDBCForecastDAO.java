package com.techelevator.npgeekModel;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

@Component
public class JDBCForecastDAO implements ForecastDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCForecastDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Forecast> getFiveDayForecast(String parkcode, ModelMap tempModel) {
		List<Forecast> allForecasts = new ArrayList<>();
		String sqlSelectAllForecasts = "SELECT * " + "FROM weather " + "WHERE parkcode = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllForecasts, parkcode);
		while (results.next()) {
			allForecasts.add(mapRowToForecast(results, tempModel));
		}
		return allForecasts;
	}

	private Forecast mapRowToForecast(SqlRowSet row, ModelMap tempModel) {
		Forecast forecast = new Forecast();
		forecast.setParkCode(row.getString("parkcode"));
		forecast.setDay(row.getInt("fivedayforecastvalue"));

		tempMessage(row, forecast);
		convertTemp(row, forecast, tempModel);
		
		forecast.setForecast(row.getString("forecast"));
		
		
		return forecast;
	}

	private void tempMessage(SqlRowSet row, Forecast forecast) {
		if (row.getInt("high")>75) {
			forecast.setTempMessage("Be sure to pack an extra gallon of water. \n");
		}
		if (row.getInt("low")<20) {
			forecast.setTempMessage("Warning: prepare for cold temperatures! \n");
		}
		if ((row.getInt("high")-row.getInt("low"))>20) {
			forecast.setTempMessage("Be sure to wear breathable layers. \n");
		}
		else {
			forecast.setTempMessage("");
		}
	}
	
	private void convertTemp(SqlRowSet row, Forecast forecast, ModelMap tempModel) {
		if (tempModel.containsValue("F")) {
			forecast.setHigh(row.getDouble("high"));
			forecast.setLow(row.getDouble("low"));
		}
		else {
			forecast.setHigh((row.getDouble("high") - 32)/1.8);
			forecast.setLow((row.getDouble("low")- 32)/1.8);
		}
	}
}