package com.qa.orangehrm.util;


public class Credentials {
	
	//valid username valid password
	
	String appUsername;
	String appPassword;
	
	//Constructor
	public Credentials(String appUsername, String appPassword) {
		this.appUsername = appUsername;
		this.appPassword = appPassword;
	}

	/**
	 * @return the appUsername
	 */
	public String getAppUsername() {
		return appUsername;
	}

	/**
	 * @param appUsername the appUsername to set
	 */
	public void setAppUsername(String appUsername) {
		this.appUsername = appUsername;
	}

	/**
	 * @return the appPassword
	 */
	public String getAppPassword() {
		return appPassword;
	}

	/**
	 * @param appPassword the appPassword to set
	 */
	public void setAppPassword(String appPassword) {
		this.appPassword = appPassword;
	}
	
	
	

}
