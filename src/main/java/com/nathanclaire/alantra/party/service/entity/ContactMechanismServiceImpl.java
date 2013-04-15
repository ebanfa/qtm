/**
 * 
 */
package com.nathanclaire.alantra.party.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.party.model.ContactMechanism;
import com.nathanclaire.alantra.party.rest.request.ContactMechanismRequest;

import com.nathanclaire.alantra.party.model.ContactMechanismType;

/**
 * @author administrator
 *
 */
@Stateless
public class ContactMechanismServiceImpl extends BaseEntityServiceImpl<ContactMechanism, ContactMechanismRequest> implements ContactMechanismService
{
	/**
	 * @param entityClass
	 */
	public ContactMechanismServiceImpl() {
		super(ContactMechanism.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ContactMechanism#findById(java.lang.Integer)
	 */
	@Override
	public ContactMechanism findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ContactMechanism#findByCode(java.lang.String)
	 */
	@Override
	public ContactMechanism findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ContactMechanism#findByName(java.lang.String)
	 */
	@Override
	public ContactMechanism findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ContactMechanism#findAll(java.util.Map)
	 */
	@Override
	public List<ContactMechanism> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ContactMechanism#createContactMechanism(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public ContactMechanism createInstance(ContactMechanismRequest contactMechanismRequest) {
		return createInsance(contactMechanismRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ContactMechanism#deleteContactMechanism(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ContactMechanism#updateContactMechanism(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public ContactMechanism updateInstance(ContactMechanismRequest contactMechanismRequest) {
		return updateInstance(contactMechanismRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected ContactMechanism loadModelFromRequest(ContactMechanismRequest contactMechanismRequest) 
    {
		ContactMechanism contactMechanism = new ContactMechanism();
    	Integer contactMechanismId = contactMechanismRequest.getId();
    	// Are we editing a ContactMechanism
    	if(contactMechanismId != null) 
    	{
    		contactMechanism = getEntityManager().find(ContactMechanism.class, contactMechanismRequest.getId());
    		contactMechanism.setLastModifiedDt(contactMechanismRequest.getLastModifiedDt());
        	contactMechanism.setLastModifiedUsr(getCurrentUserName(contactMechanismRequest));
    	}
    	else
    	{
        	contactMechanism.setCreatedDt(getCurrentSystemDate());
        	contactMechanism.setCreatedByUsr(getCurrentUserName(contactMechanismRequest));
    	}
    	contactMechanism.setCode(contactMechanismRequest.getCode());
    	contactMechanism.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (contactMechanismRequest.getContactMechanismType() != null)
    	{
    		ContactMechanismType contactMechanismType = getEntityManager().find(ContactMechanismType.class, contactMechanismRequest.getContactMechanismType());
    		contactMechanism.setContactMechanismType(contactMechanismType);
    	}
    	contactMechanism.setName(contactMechanismRequest.getName()); 
    	contactMechanism.setDescription(contactMechanismRequest.getDescription()); 
    	contactMechanism.setCode(contactMechanismRequest.getCode()); 
    	contactMechanism.setEffectiveDt(contactMechanismRequest.getEffectiveDt()); 
    	contactMechanism.setRecSt(contactMechanismRequest.getRecSt()); 
		return contactMechanism;
	}
}
