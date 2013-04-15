/**
 * 
 */
package com.nathanclaire.alantra.party.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.party.model.PartyContactMechanismPurpose;
import com.nathanclaire.alantra.party.rest.request.PartyContactMechanismPurposeRequest;

import com.nathanclaire.alantra.party.model.ContactMechanismPurposeType;
import com.nathanclaire.alantra.party.model.ContactMechanism;

/**
 * @author administrator
 *
 */
@Stateless
public class PartyContactMechanismPurposeServiceImpl extends BaseEntityServiceImpl<PartyContactMechanismPurpose, PartyContactMechanismPurposeRequest> implements PartyContactMechanismPurposeService
{
	/**
	 * @param entityClass
	 */
	public PartyContactMechanismPurposeServiceImpl() {
		super(PartyContactMechanismPurpose.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyContactMechanismPurpose#findById(java.lang.Integer)
	 */
	@Override
	public PartyContactMechanismPurpose findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyContactMechanismPurpose#findByCode(java.lang.String)
	 */
	@Override
	public PartyContactMechanismPurpose findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyContactMechanismPurpose#findByName(java.lang.String)
	 */
	@Override
	public PartyContactMechanismPurpose findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyContactMechanismPurpose#findAll(java.util.Map)
	 */
	@Override
	public List<PartyContactMechanismPurpose> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyContactMechanismPurpose#createPartyContactMechanismPurpose(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public PartyContactMechanismPurpose createInstance(PartyContactMechanismPurposeRequest partyContactMechanismPurposeRequest) {
		return createInsance(partyContactMechanismPurposeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyContactMechanismPurpose#deletePartyContactMechanismPurpose(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyContactMechanismPurpose#updatePartyContactMechanismPurpose(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public PartyContactMechanismPurpose updateInstance(PartyContactMechanismPurposeRequest partyContactMechanismPurposeRequest) {
		return updateInstance(partyContactMechanismPurposeRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected PartyContactMechanismPurpose loadModelFromRequest(PartyContactMechanismPurposeRequest partyContactMechanismPurposeRequest) 
    {
		PartyContactMechanismPurpose partyContactMechanismPurpose = new PartyContactMechanismPurpose();
    	Integer partyContactMechanismPurposeId = partyContactMechanismPurposeRequest.getId();
    	// Are we editing a PartyContactMechanismPurpose
    	if(partyContactMechanismPurposeId != null) 
    	{
    		partyContactMechanismPurpose = getEntityManager().find(PartyContactMechanismPurpose.class, partyContactMechanismPurposeRequest.getId());
    		partyContactMechanismPurpose.setLastModifiedDt(partyContactMechanismPurposeRequest.getLastModifiedDt());
        	partyContactMechanismPurpose.setLastModifiedUsr(getCurrentUserName(partyContactMechanismPurposeRequest));
    	}
    	else
    	{
        	partyContactMechanismPurpose.setCreatedDt(getCurrentSystemDate());
        	partyContactMechanismPurpose.setCreatedByUsr(getCurrentUserName(partyContactMechanismPurposeRequest));
    	}
    	partyContactMechanismPurpose.setCode(partyContactMechanismPurposeRequest.getCode());
    	partyContactMechanismPurpose.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (partyContactMechanismPurposeRequest.getContactMechanismPurposeType() != null)
    	{
    		ContactMechanismPurposeType contactMechanismPurposeType = getEntityManager().find(ContactMechanismPurposeType.class, partyContactMechanismPurposeRequest.getContactMechanismPurposeType());
    		partyContactMechanismPurpose.setContactMechanismPurposeType(contactMechanismPurposeType);
    	}
    	if (partyContactMechanismPurposeRequest.getContactMechanism() != null)
    	{
    		ContactMechanism contactMechanism = getEntityManager().find(ContactMechanism.class, partyContactMechanismPurposeRequest.getContactMechanism());
    		partyContactMechanismPurpose.setContactMechanism(contactMechanism);
    	}
    	partyContactMechanismPurpose.setFromDt(partyContactMechanismPurposeRequest.getFromDt()); 
    	partyContactMechanismPurpose.setToDt(partyContactMechanismPurposeRequest.getToDt()); 
    	partyContactMechanismPurpose.setName(partyContactMechanismPurposeRequest.getName()); 
    	partyContactMechanismPurpose.setDescription(partyContactMechanismPurposeRequest.getDescription()); 
    	partyContactMechanismPurpose.setCode(partyContactMechanismPurposeRequest.getCode()); 
    	partyContactMechanismPurpose.setEffectiveDt(partyContactMechanismPurposeRequest.getEffectiveDt()); 
    	partyContactMechanismPurpose.setRecSt(partyContactMechanismPurposeRequest.getRecSt()); 
		return partyContactMechanismPurpose;
	}
}
