/**
 * 
 */
package com.nathanclaire.alantra.businessdata.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.businessdata.model.GeoBoundryAssociation;
import com.nathanclaire.alantra.businessdata.request.GeoBoundryAssociationRequest;

import com.nathanclaire.alantra.businessdata.model.GeoBoundry;
import com.nathanclaire.alantra.businessdata.model.GeoBoundry;

/**
 * @author administrator
 *
 */
@Stateless
public class GeoBoundryAssociationServiceImpl extends BaseEntityServiceImpl<GeoBoundryAssociation, GeoBoundryAssociationRequest> implements GeoBoundryAssociationService
{
	/**
	 * @param entityClass
	 */
	public GeoBoundryAssociationServiceImpl() {
		super(GeoBoundryAssociation.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.GeoBoundryAssociation#findById(java.lang.Integer)
	 */
	@Override
	public GeoBoundryAssociation findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.GeoBoundryAssociation#findByCode(java.lang.String)
	 */
	@Override
	public GeoBoundryAssociation findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.GeoBoundryAssociation#findByName(java.lang.String)
	 */
	@Override
	public GeoBoundryAssociation findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.GeoBoundryAssociation#findAll(java.util.Map)
	 */
	@Override
	public List<GeoBoundryAssociation> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.GeoBoundryAssociation#createGeoBoundryAssociation(com.nathanclaire.alantra.businessdata.rest.request.ServiceRequest)
	 */
	@Override
	public GeoBoundryAssociation create(GeoBoundryAssociationRequest geoBoundryAssociationRequest) {
		return createInstance(geoBoundryAssociationRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.GeoBoundryAssociation#deleteGeoBoundryAssociation(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.GeoBoundryAssociation#updateGeoBoundryAssociation(com.nathanclaire.alantra.businessdata.rest.request.ServiceRequest)
	 */
	@Override
	public GeoBoundryAssociation update(GeoBoundryAssociationRequest geoBoundryAssociationRequest) {
		return updateInstance(geoBoundryAssociationRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected GeoBoundryAssociation loadModelFromRequest(GeoBoundryAssociationRequest geoBoundryAssociationRequest) 
    {
		GeoBoundryAssociation geoBoundryAssociation = new GeoBoundryAssociation();
    	Integer geoBoundryAssociationId = geoBoundryAssociationRequest.getId();
    	// Are we editing a GeoBoundryAssociation
    	if(geoBoundryAssociationId != null) 
    	{
    		geoBoundryAssociation = getEntityManager().find(GeoBoundryAssociation.class, geoBoundryAssociationRequest.getId());
    		geoBoundryAssociation.setLastModifiedDt(geoBoundryAssociationRequest.getLastModifiedDt());
        	geoBoundryAssociation.setLastModifiedUsr(getCurrentUserName(geoBoundryAssociationRequest));
    	}
    	else
    	{
        	geoBoundryAssociation.setCreatedDt(getCurrentSystemDate());
        	geoBoundryAssociation.setCreatedByUsr(getCurrentUserName(geoBoundryAssociationRequest));
    	}
    	geoBoundryAssociation.setCode(geoBoundryAssociationRequest.getCode());
    	geoBoundryAssociation.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (geoBoundryAssociationRequest.getGeoBoundryByToGeoId() != null)
    	{
    		GeoBoundry geoBoundryByToGeoId = getEntityManager().find(GeoBoundry.class, geoBoundryAssociationRequest.getGeoBoundryByToGeoId());
    		geoBoundryAssociation.setGeoBoundryByToGeoId(geoBoundryByToGeoId);
    	}
    	if (geoBoundryAssociationRequest.getGeoBoundryByFromGeoId() != null)
    	{
    		GeoBoundry geoBoundryByFromGeoId = getEntityManager().find(GeoBoundry.class, geoBoundryAssociationRequest.getGeoBoundryByFromGeoId());
    		geoBoundryAssociation.setGeoBoundryByFromGeoId(geoBoundryByFromGeoId);
    	}
    	geoBoundryAssociation.setDescription(geoBoundryAssociationRequest.getDescription()); 
    	geoBoundryAssociation.setCode(geoBoundryAssociationRequest.getCode()); 
    	geoBoundryAssociation.setEffectiveDt(geoBoundryAssociationRequest.getEffectiveDt()); 
    	geoBoundryAssociation.setRecSt(geoBoundryAssociationRequest.getRecSt()); 
		return geoBoundryAssociation;
	}
}
