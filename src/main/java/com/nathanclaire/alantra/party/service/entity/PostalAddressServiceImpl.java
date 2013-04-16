/**
 * 
 */
package com.nathanclaire.alantra.party.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.party.model.PostalAddress;
import com.nathanclaire.alantra.party.request.PostalAddressRequest;

import com.nathanclaire.alantra.party.model.ContactMechanism;

/**
 * @author administrator
 *
 */
@Stateless
public class PostalAddressServiceImpl extends BaseEntityServiceImpl<PostalAddress, PostalAddressRequest> implements PostalAddressService
{
	/**
	 * @param entityClass
	 */
	public PostalAddressServiceImpl() {
		super(PostalAddress.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PostalAddress#findById(java.lang.Integer)
	 */
	@Override
	public PostalAddress findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PostalAddress#findByCode(java.lang.String)
	 */
	@Override
	public PostalAddress findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PostalAddress#findByName(java.lang.String)
	 */
	@Override
	public PostalAddress findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PostalAddress#findAll(java.util.Map)
	 */
	@Override
	public List<PostalAddress> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PostalAddress#createPostalAddress(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public PostalAddress create(PostalAddressRequest postalAddressRequest) {
		return createInstance(postalAddressRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PostalAddress#deletePostalAddress(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PostalAddress#updatePostalAddress(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public PostalAddress update(PostalAddressRequest postalAddressRequest) {
		return updateInstance(postalAddressRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected PostalAddress loadModelFromRequest(PostalAddressRequest postalAddressRequest) 
    {
		PostalAddress postalAddress = new PostalAddress();
    	Integer postalAddressId = postalAddressRequest.getId();
    	// Are we editing a PostalAddress
    	if(postalAddressId != null) 
    	{
    		postalAddress = getEntityManager().find(PostalAddress.class, postalAddressRequest.getId());
    		postalAddress.setLastModifiedDt(postalAddressRequest.getLastModifiedDt());
        	postalAddress.setLastModifiedUsr(getCurrentUserName(postalAddressRequest));
    	}
    	else
    	{
        	postalAddress.setCreatedDt(getCurrentSystemDate());
        	postalAddress.setCreatedByUsr(getCurrentUserName(postalAddressRequest));
    	}
    	postalAddress.setCode(postalAddressRequest.getCode());
    	postalAddress.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (postalAddressRequest.getContactMechanism() != null)
    	{
    		ContactMechanism contactMechanism = getEntityManager().find(ContactMechanism.class, postalAddressRequest.getContactMechanism());
    		postalAddress.setContactMechanism(contactMechanism);
    	}
    	postalAddress.setAddress1(postalAddressRequest.getAddress1()); 
    	postalAddress.setAddress2(postalAddressRequest.getAddress2()); 
    	postalAddress.setDirections(postalAddressRequest.getDirections()); 
    	postalAddress.setDescription(postalAddressRequest.getDescription()); 
    	postalAddress.setCode(postalAddressRequest.getCode()); 
    	postalAddress.setEffectiveDt(postalAddressRequest.getEffectiveDt()); 
    	postalAddress.setRecSt(postalAddressRequest.getRecSt()); 
		return postalAddress;
	}
}
