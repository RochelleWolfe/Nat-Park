package com.techelevator.npgeekModel;

public class Forecast {
	private String		parkCode;
	private int			day; //days 1-5
	private int			lowF;
	private int			highF;
	private String		forecast;
	private double		lowC;
	private double		highC;
	private String		tempMessage;
	private String		weatherMessage;
	private String		imgName;
	
	public double getLowC() {
		return lowC;
	}
	public void setLowC() {
		lowC= (lowF-32)/1.8;
	}
	public double getHighC() {
		return highC;
	}
	public void setHighC() {
		highC= (highF-32)/1.8;
	}
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
	public int getLowF() {
		return lowF;
	}
	public void setLowF(int lowF) {
		this.lowF = lowF;
		setTempMessage();
		setLowC();
	}
	public int getHighF() {
		return highF;
	}
	public void setHighF(int highF) {
		this.highF = highF;
		setTempMessage();
		setHighC();
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
	
	public void setTempMessage() {
		if (highF>75) {
			tempMessage+= "Be sure to pack an extra gallon of water. \n";
		}
		if (lowF<20) {
			tempMessage+= "Warning: prepare for cold temperatures! \n";
		}
		if ((highF-lowF)>20) {
			tempMessage+= "Be sure to wear breathable layers. \n";
		}
		else {
			tempMessage = "";
		}
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
