/**
 * 
 */
package com.nathanclaire.alantra.party.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.party.model.ContactMechanismType;
import com.nathanclaire.alantra.party.request.ContactMechanismTypeRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class ContactMechanismTypeServiceImpl extends BaseEntityServiceImpl<ContactMechanismType, ContactMechanismTypeRequest> implements ContactMechanismTypeService
{
	/**
	 * @param entityClass
	 */
	public ContactMechanismTypeServiceImpl() {
		super(ContactMechanismType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ContactMechanismType#findById(java.lang.Integer)
	 */
	@Override
	public ContactMechanismType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ContactMechanismType#findByCode(java.lang.String)
	 */
	@Override
	public ContactMechanismType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ContactMechanismType#findByName(java.lang.String)
	 */
	@Override
	public ContactMechanismType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ContactMechanismType#findAll(java.util.Map)
	 */
	@Override
	public List<ContactMechanismType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ContactMechanismType#createContactMechanismType(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public ContactMechanismType create(ContactMechanismTypeRequest contactMechanismTypeRequest) {
		return createInstance(contactMechanismTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ContactMechanismType#deleteContactMechanismType(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ContactMechanismType#updateContactMechanismType(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public ContactMechanismType update(ContactMechanismTypeRequest contactMechanismTypeRequest) {
		return updateInstance(contactMechanismTypeRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected ContactMechanismType loadModelFromRequest(ContactMechanismTypeRequest contactMechanismTypeRequest) 
    {
		ContactMechanismType contactMechanismType = new ContactMechanismType();
    	Integer contactMechanismTypeId = contactMechanismTypeRequest.getId();
    	// Are we editing a ContactMechanismType
    	if(contactMechanismTypeId != null) 
    	{
    		contactMechanismType = getEntityManager().find(ContactMechanismType.class, contactMechanismTypeRequest.getId());
    		contactMechanismType.setLastModifiedDt(contactMechanismTypeRequest.getLastModifiedDt());
        	contactMechanismType.setLastModifiedUsr(getCurrentUserName(contactMechanismTypeRequest));
    	}
    	else
    	{
        	contactMechanismType.setCreatedDt(getCurrentSystemDate());
        	contactMechanismType.setCreatedByUsr(getCurrentUserName(contactMechanismTypeRequest));
    	}
    	contactMechanismType.setCode(contactMechanismTypeRequest.getCode());
    	contactMechanismType.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	contactMechanismType.setName(contactMechanismTypeRequest.getName()); 
    	contactMechanismType.setDescription(contactMechanismTypeRequest.getDescription()); 
    	contactMechanismType.setCode(contactMechanismTypeRequest.getCode()); 
    	contactMechanismType.setEffectiveDt(contactMechanismTypeRequest.getEffectiveDt()); 
    	contactMechanismType.setRecSt(contactMechanismTypeRequest.getRecSt()); 
		return contactMechanismType;
	}
}
