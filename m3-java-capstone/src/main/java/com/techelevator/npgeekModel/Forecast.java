package com.techelevator.npgeekModel;



public class Forecast {
	private String		parkCode;
	private int			day; //days 1-5
	private Double			low;
	private Double			high;
	private String		forecast;
	private String		tempMessage = "";
	private String		weatherMessage;
	private String		imgName;
	
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	
	public Double getLow() {
		return low;
	}
	public void setLow(Double low) {
		this.low = low;
	}
	public Double getHigh() {
		return high;
	}
	public void setHigh(Double high) {
		this.high = high; 
	}
	public String getForecast() {
		return forecast;
	}
	public void setForecast(String forecast) {
		this.forecast = forecast;
		setImgName();
		setWeatherMessage();
	}
	

	public String getWeatherMessage() {
		return weatherMessage;
	}

	public String getTempMessage() {
		return tempMessage;
	}
	
	public void setTempMessage(String tempMessage) {
		this.tempMessage+= tempMessage;
	}
	
	public void setWeatherMessage() {
		if (forecast.contains("snow")) {
			weatherMessage = "Don't forget the snowshoes! \n";
		}
		if (forecast.contains("rain")) {
			weatherMessage = "Don't forget your raingear and waterproof shoes! \n";
		}
		if (forecast.contains("thunderstorms")) {
			weatherMessage = "In the event of thunderstorm, seek shelter and avoid hiking on exposed ridges. \n";
		}
		if (forecast.contains("sunny")) {
			weatherMessage = "Don't forget your sunblock!";
			
		} else {weatherMessage = "";
		}
		
		
	}
	
	public String getImgName() {
		return imgName;
	}
	
	private void setImgName() {
		if (forecast.contains("partly")) {
			imgName = "partlyCloudy.png";
		}
		else {
			imgName = forecast + ".png";
		}
	}

}
