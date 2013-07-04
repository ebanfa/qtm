/**
 * 
 */
package com.nathanclaire.alantra.messaging.util;

/**
 * @author Edward Banfa 
 *
 */
public class Contact {
	
	private String primaryEmail;
	private String primaryPhone;
	private String secondaryEmail;
	private String secondaryPhone ;
	
	/**
	 * @param primaryEmail
	 * @param primaryPhone
	 * @param secondaryEmail
	 * @param secondaryPhone
	 */
	public Contact(String primaryEmail, String secondaryEmail, String primaryPhone, String secondaryPhone) {
		this.primaryEmail = primaryEmail;
		this.primaryPhone = primaryPhone;
		this.secondaryEmail = secondaryEmail;
		this.secondaryPhone = secondaryPhone;
	}
	/**
	 * @return the primaryEmail
	 */
	public String getPrimaryEmail() {
		return primaryEmail;
	}
	/**
	 * @param primaryEmail the primaryEmail to set
	 */
	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}
	/**
	 * @return the primaryPhone
	 */
	public String getPrimaryPhone() {
		return primaryPhone;
	}
	/**
	 * @param primaryPhone the primaryPhone to set
	 */
	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}
	/**
	 * @return the secondaryEmail
	 */
	public String getSecondaryEmail() {
		return secondaryEmail;
	}
	/**
	 * @param secondaryEmail the secondaryEmail to set
	 */
	public void setSecondaryEmail(String secondaryEmail) {
		this.secondaryEmail = secondaryEmail;
	}
	/**
	 * @return the secondaryPhone
	 */
	public String getSecondaryPhone() {
		return secondaryPhone;
	}
	/**
	 * @param secondaryPhone the secondaryPhone to set
	 */
	public void setSecondaryPhone(String secondaryPhone) {
		this.secondaryPhone = secondaryPhone;
	}

}
