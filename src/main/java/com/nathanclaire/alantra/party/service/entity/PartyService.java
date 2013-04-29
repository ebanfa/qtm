/**
 * 
 */
package com.nathanclaire.alantra.party.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.party.model.Party;
import com.nathanclaire.alantra.party.request.OrganizationRequest;
import com.nathanclaire.alantra.party.request.PartyRequest;
import com.nathanclaire.alantra.party.request.PersonRequest;

/**
 * @author Edward Banfa
 *
 */
public interface PartyService extends BaseEntityService<Party, PartyRequest>
{
	/**
	 * @param personRequest
	 * @return
	 */
	public Party createIndividual(PersonRequest personRequest) ;	
	
	/**
	 * @param organizationRequest
	 * @return
	 */
	public Party createOrganization(OrganizationRequest organizationRequest);
}
