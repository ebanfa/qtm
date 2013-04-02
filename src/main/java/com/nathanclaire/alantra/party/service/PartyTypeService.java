/**
 * 
 */
package com.nathanclaire.alantra.party.service;

import com.nathanclaire.alantra.party.model.PartyType;

/**
 * @author Edward Banfa 
 *
 */
public interface PartyTypeService 
{
	/**
	 * @return
	 */
	public PartyType getOrganizationalPartyType();
	
	/**
	 * @return
	 */
	public PartyType getIndividualPartyType();

}
