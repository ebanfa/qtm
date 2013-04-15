/**
 * 
 */
package com.nathanclaire.alantra.businessdata.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.businessdata.model.GeoBoundaryType;
import com.nathanclaire.alantra.businessdata.rest.request.GeoBoundaryTypeRequest;

import com.nathanclaire.alantra.businessdata.model.GeoBoundaryType;

/**
 * @author administrator
 *
 */
@Stateless
public class GeoBoundaryTypeServiceImpl extends BaseEntityServiceImpl<GeoBoundaryType, GeoBoundaryTypeRequest> implements GeoBoundaryTypeService
{
	/**
	 * @param entityClass
	 */
	public GeoBoundaryTypeServiceImpl() {
		super(GeoBoundaryType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.GeoBoundaryType#findById(java.lang.Integer)
	 */
	@Override
	public GeoBoundaryType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.GeoBoundaryType#findByCode(java.lang.String)
	 */
	@Override
	public GeoBoundaryType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.GeoBoundaryType#findByName(java.lang.String)
	 */
	@Override
	public GeoBoundaryType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.GeoBoundaryType#findAll(java.util.Map)
	 */
	@Override
	public List<GeoBoundaryType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.GeoBoundaryType#createGeoBoundaryType(com.nathanclaire.alantra.businessdata.rest.request.ServiceRequest)
	 */
	@Override
	public GeoBoundaryType createInstance(GeoBoundaryTypeRequest geoBoundaryTypeRequest) {
		return createInsance(geoBoundaryTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.GeoBoundaryType#deleteGeoBoundaryType(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.GeoBoundaryType#updateGeoBoundaryType(com.nathanclaire.alantra.businessdata.rest.request.ServiceRequest)
	 */
	@Override
	public GeoBoundaryType updateInstance(GeoBoundaryTypeRequest geoBoundaryTypeRequest) {
		return updateInstance(geoBoundaryTypeRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected GeoBoundaryType loadModelFromRequest(GeoBoundaryTypeRequest geoBoundaryTypeRequest) 
    {
		GeoBoundaryType geoBoundaryType = new GeoBoundaryType();
    	Integer geoBoundaryTypeId = geoBoundaryTypeRequest.getId();
    	// Are we editing a GeoBoundaryType
    	if(geoBoundaryTypeId != null) 
    	{
    		geoBoundaryType = getEntityManager().find(GeoBoundaryType.class, geoBoundaryTypeRequest.getId());
    		geoBoundaryType.setLastModifiedDt(geoBoundaryTypeRequest.getLastModifiedDt());
        	geoBoundaryType.setLastModifiedUsr(getCurrentUserName(geoBoundaryTypeRequest));
    	}
    	else
    	{
        	geoBoundaryType.setCreatedDt(getCurrentSystemDate());
        	geoBoundaryType.setCreatedByUsr(getCurrentUserName(geoBoundaryTypeRequest));
    	}
    	geoBoundaryType.setCode(geoBoundaryTypeRequest.getCode());
    	geoBoundaryType.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (geoBoundaryTypeRequest.getGeoBoundaryType() != null)
    	{
    		GeoBoundaryType parentGeoBoundaryType = getEntityManager().find(GeoBoundaryType.class, geoBoundaryTypeRequest.getGeoBoundaryType());
    		geoBoundaryType.setGeoBoundaryType(parentGeoBoundaryType);
    	}
    	geoBoundaryType.setName(geoBoundaryTypeRequest.getName()); 
    	geoBoundaryType.setDescription(geoBoundaryTypeRequest.getDescription()); 
    	geoBoundaryType.setCode(geoBoundaryTypeRequest.getCode()); 
    	geoBoundaryType.setEffectiveDt(geoBoundaryTypeRequest.getEffectiveDt()); 
    	geoBoundaryType.setRecSt(geoBoundaryTypeRequest.getRecSt()); 
		return geoBoundaryType;
	}
}
