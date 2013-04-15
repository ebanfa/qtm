/**
 * 
 */
package com.nathanclaire.alantra.businessdata.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.businessdata.model.PostalAddressBoundry;
import com.nathanclaire.alantra.businessdata.rest.request.PostalAddressBoundryRequest;

import com.nathanclaire.alantra.party.model.PostalAddress;
import com.nathanclaire.alantra.businessdata.model.GeoBoundry;

/**
 * @author administrator
 *
 */
@Stateless
public class PostalAddressBoundryServiceImpl extends BaseEntityServiceImpl<PostalAddressBoundry, PostalAddressBoundryRequest> implements PostalAddressBoundryService
{
	/**
	 * @param entityClass
	 */
	public PostalAddressBoundryServiceImpl() {
		super(PostalAddressBoundry.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.PostalAddressBoundry#findById(java.lang.Integer)
	 */
	@Override
	public PostalAddressBoundry findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.PostalAddressBoundry#findByCode(java.lang.String)
	 */
	@Override
	public PostalAddressBoundry findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.PostalAddressBoundry#findByName(java.lang.String)
	 */
	@Override
	public PostalAddressBoundry findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.PostalAddressBoundry#findAll(java.util.Map)
	 */
	@Override
	public List<PostalAddressBoundry> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.PostalAddressBoundry#createPostalAddressBoundry(com.nathanclaire.alantra.businessdata.rest.request.ServiceRequest)
	 */
	@Override
	public PostalAddressBoundry createInstance(PostalAddressBoundryRequest postalAddressBoundryRequest) {
		return createInsance(postalAddressBoundryRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.PostalAddressBoundry#deletePostalAddressBoundry(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.PostalAddressBoundry#updatePostalAddressBoundry(com.nathanclaire.alantra.businessdata.rest.request.ServiceRequest)
	 */
	@Override
	public PostalAddressBoundry updateInstance(PostalAddressBoundryRequest postalAddressBoundryRequest) {
		return updateInstance(postalAddressBoundryRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected PostalAddressBoundry loadModelFromRequest(PostalAddressBoundryRequest postalAddressBoundryRequest) 
    {
		PostalAddressBoundry postalAddressBoundry = new PostalAddressBoundry();
    	Integer postalAddressBoundryId = postalAddressBoundryRequest.getId();
    	// Are we editing a PostalAddressBoundry
    	if(postalAddressBoundryId != null) 
    	{
    		postalAddressBoundry = getEntityManager().find(PostalAddressBoundry.class, postalAddressBoundryRequest.getId());
    		postalAddressBoundry.setLastModifiedDt(postalAddressBoundryRequest.getLastModifiedDt());
        	postalAddressBoundry.setLastModifiedUsr(getCurrentUserName(postalAddressBoundryRequest));
    	}
    	else
    	{
        	postalAddressBoundry.setCreatedDt(getCurrentSystemDate());
        	postalAddressBoundry.setCreatedByUsr(getCurrentUserName(postalAddressBoundryRequest));
    	}
    	postalAddressBoundry.setCode(postalAddressBoundryRequest.getCode());
    	postalAddressBoundry.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (postalAddressBoundryRequest.getPostalAddress() != null)
    	{
    		PostalAddress postalAddress = getEntityManager().find(PostalAddress.class, postalAddressBoundryRequest.getPostalAddress());
    		postalAddressBoundry.setPostalAddress(postalAddress);
    	}
    	if (postalAddressBoundryRequest.getGeoBoundry() != null)
    	{
    		GeoBoundry geoBoundry = getEntityManager().find(GeoBoundry.class, postalAddressBoundryRequest.getGeoBoundry());
    		postalAddressBoundry.setGeoBoundry(geoBoundry);
    	}
    	postalAddressBoundry.setDescription(postalAddressBoundryRequest.getDescription()); 
    	postalAddressBoundry.setCode(postalAddressBoundryRequest.getCode()); 
    	postalAddressBoundry.setEffectiveDt(postalAddressBoundryRequest.getEffectiveDt()); 
    	postalAddressBoundry.setRecSt(postalAddressBoundryRequest.getRecSt()); 
		return postalAddressBoundry;
	}
}