package com.nathanclaire.alantra.party.service.impl;

import javax.ejb.Stateless;

import com.nathanclaire.alantra.base.service.BaseService;
import com.nathanclaire.alantra.party.model.PartyType;
import com.nathanclaire.alantra.party.service.PartyTypeService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class PartyTypeServiceImpl extends BaseService implements PartyTypeService {

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyTypeService#getOrganizationalPartyType()
	 */
	@Override
	public PartyType getOrganizationalPartyType() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyTypeService#getIndividualPartyType()
	 */
	@Override
	public PartyType getIndividualPartyType() {
		// TODO Auto-generated method stub
		return null;
	}

}
