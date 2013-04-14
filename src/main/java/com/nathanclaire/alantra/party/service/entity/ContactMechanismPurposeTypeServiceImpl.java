/**
 * 
 */
package com.nathanclaire.alantra.party.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.party.model.ContactMechanismPurposeType;
import com.nathanclaire.alantra.party.rest.request.ContactMechanismPurposeTypeRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class ContactMechanismPurposeTypeServiceImpl extends BaseEntityServiceImpl<ContactMechanismPurposeType> implements ContactMechanismPurposeTypeService
{
	/**
	 * @param entityClass
	 */
	public ContactMechanismPurposeTypeServiceImpl() {
		super(ContactMechanismPurposeType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ContactMechanismPurposeType#findById(java.lang.Integer)
	 */
	@Override
	public ContactMechanismPurposeType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ContactMechanismPurposeType#findByCode(java.lang.String)
	 */
	@Override
	public ContactMechanismPurposeType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ContactMechanismPurposeType#findByName(java.lang.String)
	 */
	@Override
	public ContactMechanismPurposeType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ContactMechanismPurposeType#findAll(java.util.Map)
	 */
	@Override
	public List<ContactMechanismPurposeType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ContactMechanismPurposeType#createContactMechanismPurposeType(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public ContactMechanismPurposeType createInstance(BaseRequest contactMechanismPurposeTypeRequest) {
		return createInsance(contactMechanismPurposeTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ContactMechanismPurposeType#deleteContactMechanismPurposeType(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ContactMechanismPurposeType#updateContactMechanismPurposeType(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public ContactMechanismPurposeType updateInstance(BaseRequest contactMechanismPurposeTypeRequest) {
		return updateInstance(contactMechanismPurposeTypeRequest);
	}
	
	/**
     * @param request
     * @return
     */
    protected ContactMechanismPurposeType loadModelFromRequest(BaseRequest request) 
    {
    	ContactMechanismPurposeTypeRequest contactMechanismPurposeTypeRequest = (ContactMechanismPurposeTypeRequest) request;
		ContactMechanismPurposeType contactMechanismPurposeType = new ContactMechanismPurposeType();
    	Integer contactMechanismPurposeTypeId = contactMechanismPurposeTypeRequest.getId();
    	// Are we editing a ContactMechanismPurposeType
    	if(contactMechanismPurposeTypeId != null) 
    	{
    		contactMechanismPurposeType = getEntityManager().find(ContactMechanismPurposeType.class, contactMechanismPurposeTypeRequest.getId());
    		contactMechanismPurposeType.setLastModifiedDt(contactMechanismPurposeTypeRequest.getLastModifiedDt());
        	contactMechanismPurposeType.setLastModifiedUsr(getCurrentUserName(contactMechanismPurposeTypeRequest));
    	}
    	else
    	{
        	contactMechanismPurposeType.setCreatedDt(getCurrentSystemDate());
        	contactMechanismPurposeType.setCreatedByUsr(getCurrentUserName(contactMechanismPurposeTypeRequest));
    	}
    	contactMechanismPurposeType.setCode(contactMechanismPurposeTypeRequest.getCode());
    	contactMechanismPurposeType.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	contactMechanismPurposeType.setName(contactMechanismPurposeTypeRequest.getName()); 
    	contactMechanismPurposeType.setDescription(contactMechanismPurposeTypeRequest.getDescription()); 
    	contactMechanismPurposeType.setCode(contactMechanismPurposeTypeRequest.getCode()); 
    	contactMechanismPurposeType.setEffectiveDt(contactMechanismPurposeTypeRequest.getEffectiveDt()); 
    	contactMechanismPurposeType.setRecSt(contactMechanismPurposeTypeRequest.getRecSt()); 
		return contactMechanismPurposeType;
	}
}
