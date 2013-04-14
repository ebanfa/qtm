/**
 * 
 */
package com.nathanclaire.alantra.party.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.party.model.ContactMechanismLink;
import com.nathanclaire.alantra.party.rest.request.ContactMechanismLinkRequest;

import com.nathanclaire.alantra.party.model.ContactMechanism;
import com.nathanclaire.alantra.party.rest.request.ContactMechanismRequest;
import com.nathanclaire.alantra.party.model.ContactMechanism;
import com.nathanclaire.alantra.party.rest.request.ContactMechanismRequest;

/**
 * @author administrator
 *
 */
@Stateless
public class ContactMechanismLinkServiceImpl extends BaseEntityServiceImpl<ContactMechanismLink> implements ContactMechanismLinkService
{
	/**
	 * @param entityClass
	 */
	public ContactMechanismLinkServiceImpl() {
		super(ContactMechanismLink.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ContactMechanismLink#findById(java.lang.Integer)
	 */
	@Override
	public ContactMechanismLink findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ContactMechanismLink#findByCode(java.lang.String)
	 */
	@Override
	public ContactMechanismLink findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ContactMechanismLink#findByName(java.lang.String)
	 */
	@Override
	public ContactMechanismLink findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ContactMechanismLink#findAll(java.util.Map)
	 */
	@Override
	public List<ContactMechanismLink> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ContactMechanismLink#createContactMechanismLink(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public ContactMechanismLink createInstance(BaseRequest contactMechanismLinkRequest) {
		return createInsance(contactMechanismLinkRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ContactMechanismLink#deleteContactMechanismLink(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ContactMechanismLink#updateContactMechanismLink(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public ContactMechanismLink updateInstance(BaseRequest contactMechanismLinkRequest) {
		return updateInstance(contactMechanismLinkRequest);
	}
	
	/**
     * @param request
     * @return
     */
    protected ContactMechanismLink loadModelFromRequest(BaseRequest request) 
    {
    	ContactMechanismLinkRequest contactMechanismLinkRequest = (ContactMechanismLinkRequest) request;
		ContactMechanismLink contactMechanismLink = new ContactMechanismLink();
    	Integer contactMechanismLinkId = contactMechanismLinkRequest.getId();
    	// Are we editing a ContactMechanismLink
    	if(contactMechanismLinkId != null) 
    	{
    		contactMechanismLink = getEntityManager().find(ContactMechanismLink.class, contactMechanismLinkRequest.getId());
    		contactMechanismLink.setLastModifiedDt(contactMechanismLinkRequest.getLastModifiedDt());
        	contactMechanismLink.setLastModifiedUsr(getCurrentUserName(contactMechanismLinkRequest));
    	}
    	else
    	{
        	contactMechanismLink.setCreatedDt(getCurrentSystemDate());
        	contactMechanismLink.setCreatedByUsr(getCurrentUserName(contactMechanismLinkRequest));
    	}
    	contactMechanismLink.setCode(contactMechanismLinkRequest.getCode());
    	contactMechanismLink.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (contactMechanismLinkRequest.getContactMechanismByToId() != null)
    	{
    		ContactMechanism contactMechanismByToId = getEntityManager().find(ContactMechanism.class, contactMechanismLinkRequest.getContactMechanismByToId());
    		contactMechanismLink.setContactMechanismByToId(contactMechanismByToId);
    	}
    	if (contactMechanismLinkRequest.getContactMechanismByFromId() != null)
    	{
    		ContactMechanism contactMechanismByFromId = getEntityManager().find(ContactMechanism.class, contactMechanismLinkRequest.getContactMechanismByFromId());
    		contactMechanismLink.setContactMechanismByFromId(contactMechanismByFromId);
    	}
    	contactMechanismLink.setDescription(contactMechanismLinkRequest.getDescription()); 
    	contactMechanismLink.setCode(contactMechanismLinkRequest.getCode()); 
    	contactMechanismLink.setEffectiveDt(contactMechanismLinkRequest.getEffectiveDt()); 
    	contactMechanismLink.setRecSt(contactMechanismLinkRequest.getRecSt()); 
		return contactMechanismLink;
	}
}
