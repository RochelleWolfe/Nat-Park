package com.techelevator.npgeekModel;

import javax.validation.constraints.AssertTrue;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class Survey {
	
	private int		id; 
	private String	parkCode;
	
	@NotBlank(message="Your Email is required.")
	@Email(message="A valid Email is required")
	private String	email;
	
	@NotBlank(message="Your Email is required.")
	@Email(message="A valid Email is required")
	private String	confirmEmail;
	
	@NotBlank(message="Your state of residence is required.")
	private String	state;  //Wishlist: drop down with all 50 states
	
	private	String	activityLevel;
	
	@SuppressWarnings("unused")
	@AssertTrue(message="Emails must match")
	public boolean isEmailMatching() {
		if (email != null) {
			return email.equals(confirmEmail);
		}
		return false;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getActivityLevel() {
		return activityLevel;
	}
	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}
	public String getConfirmEmail() {
		return confirmEmail;
	}
	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}

}
