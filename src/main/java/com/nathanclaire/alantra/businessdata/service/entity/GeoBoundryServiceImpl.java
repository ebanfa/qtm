/**
 * 
 */
package com.nathanclaire.alantra.businessdata.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.businessdata.model.GeoBoundry;
import com.nathanclaire.alantra.businessdata.rest.request.GeoBoundryRequest;

import com.nathanclaire.alantra.businessdata.model.GeoBoundaryType;
import com.nathanclaire.alantra.businessdata.rest.request.GeoBoundaryTypeRequest;

/**
 * @author administrator
 *
 */
@Stateless
public class GeoBoundryServiceImpl extends BaseEntityServiceImpl<GeoBoundry> implements GeoBoundryService
{
	/**
	 * @param entityClass
	 */
	public GeoBoundryServiceImpl() {
		super(GeoBoundry.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.GeoBoundry#findById(java.lang.Integer)
	 */
	@Override
	public GeoBoundry findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.GeoBoundry#findByCode(java.lang.String)
	 */
	@Override
	public GeoBoundry findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.GeoBoundry#findByName(java.lang.String)
	 */
	@Override
	public GeoBoundry findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.GeoBoundry#findAll(java.util.Map)
	 */
	@Override
	public List<GeoBoundry> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.GeoBoundry#createGeoBoundry(com.nathanclaire.alantra.businessdata.rest.request.ServiceRequest)
	 */
	@Override
	public GeoBoundry createInstance(BaseRequest geoBoundryRequest) {
		return createInsance(geoBoundryRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.GeoBoundry#deleteGeoBoundry(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.GeoBoundry#updateGeoBoundry(com.nathanclaire.alantra.businessdata.rest.request.ServiceRequest)
	 */
	@Override
	public GeoBoundry updateInstance(BaseRequest geoBoundryRequest) {
		return updateInstance(geoBoundryRequest);
	}
	
	/**
     * @param request
     * @return
     */
    protected GeoBoundry loadModelFromRequest(BaseRequest request) 
    {
    	GeoBoundryRequest geoBoundryRequest = (GeoBoundryRequest) request;
		GeoBoundry geoBoundry = new GeoBoundry();
    	Integer geoBoundryId = geoBoundryRequest.getId();
    	// Are we editing a GeoBoundry
    	if(geoBoundryId != null) 
    	{
    		geoBoundry = getEntityManager().find(GeoBoundry.class, geoBoundryRequest.getId());
    		geoBoundry.setLastModifiedDt(geoBoundryRequest.getLastModifiedDt());
        	geoBoundry.setLastModifiedUsr(getCurrentUserName(geoBoundryRequest));
    	}
    	else
    	{
        	geoBoundry.setCreatedDt(getCurrentSystemDate());
        	geoBoundry.setCreatedByUsr(getCurrentUserName(geoBoundryRequest));
    	}
    	geoBoundry.setCode(geoBoundryRequest.getCode());
    	geoBoundry.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (geoBoundryRequest.getGeoBoundaryType() != null)
    	{
    		GeoBoundaryType geoBoundaryType = getEntityManager().find(GeoBoundaryType.class, geoBoundryRequest.getGeoBoundaryType());
    		geoBoundry.setGeoBoundaryType(geoBoundaryType);
    	}
    	geoBoundry.setName(geoBoundryRequest.getName()); 
    	geoBoundry.setAbbreviation(geoBoundryRequest.getAbbreviation()); 
    	geoBoundry.setDescription(geoBoundryRequest.getDescription()); 
    	geoBoundry.setCode(geoBoundryRequest.getCode()); 
    	geoBoundry.setEffectiveDt(geoBoundryRequest.getEffectiveDt()); 
    	geoBoundry.setRecSt(geoBoundryRequest.getRecSt()); 
		return geoBoundry;
	}
}
