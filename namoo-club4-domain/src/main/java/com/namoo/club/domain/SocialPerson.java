package com.namoo.club.domain;


public class SocialPerson {

	private String name;
	private String email;
	private String password;

	public SocialPerson(){
		//
	}

	/**
	 *
	 * @param name
	 * @param email
	 * @param password
	 */
	public SocialPerson(String name, String email, String password){
		//
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

}