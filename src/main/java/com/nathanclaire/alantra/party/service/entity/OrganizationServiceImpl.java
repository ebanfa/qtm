/**
 * 
 */
package com.nathanclaire.alantra.party.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.party.model.Organization;
import com.nathanclaire.alantra.party.rest.request.OrganizationRequest;

import com.nathanclaire.alantra.party.model.Party;

/**
 * @author administrator
 *
 */
@Stateless
public class OrganizationServiceImpl extends BaseEntityServiceImpl<Organization, OrganizationRequest> implements OrganizationService
{
	/**
	 * @param entityClass
	 */
	public OrganizationServiceImpl() {
		super(Organization.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.Organization#findById(java.lang.Integer)
	 */
	@Override
	public Organization findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.Organization#findByCode(java.lang.String)
	 */
	@Override
	public Organization findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.Organization#findByName(java.lang.String)
	 */
	@Override
	public Organization findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.Organization#findAll(java.util.Map)
	 */
	@Override
	public List<Organization> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.Organization#createOrganization(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public Organization createInstance(OrganizationRequest organizationRequest) {
		return createInsance(organizationRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.Organization#deleteOrganization(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.Organization#updateOrganization(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public Organization updateInstance(OrganizationRequest organizationRequest) {
		return updateInstance(organizationRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected Organization loadModelFromRequest(OrganizationRequest organizationRequest) 
    {
		Organization organization = new Organization();
    	Integer organizationId = organizationRequest.getId();
    	// Are we editing a Organization
    	if(organizationId != null) 
    	{
    		organization = getEntityManager().find(Organization.class, organizationRequest.getId());
    		organization.setLastModifiedDt(organizationRequest.getLastModifiedDt());
        	organization.setLastModifiedUsr(getCurrentUserName(organizationRequest));
    	}
    	else
    	{
        	organization.setCreatedDt(getCurrentSystemDate());
        	organization.setCreatedByUsr(getCurrentUserName(organizationRequest));
    	}
    	organization.setCode(organizationRequest.getCode());
    	organization.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (organizationRequest.getParty() != null)
    	{
    		Party party = getEntityManager().find(Party.class, organizationRequest.getParty());
    		organization.setParty(party);
    	}
    	organization.setTaxIdNo(organizationRequest.getTaxIdNo()); 
    	organization.setCode(organizationRequest.getCode()); 
    	organization.setEffectiveDt(organizationRequest.getEffectiveDt()); 
    	organization.setRecSt(organizationRequest.getRecSt()); 
		return organization;
	}
}
