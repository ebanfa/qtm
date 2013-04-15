/**
 * 
 */
package com.nathanclaire.alantra.businessdata.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.businessdata.model.UomConversion;
import com.nathanclaire.alantra.businessdata.rest.request.UomConversionRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class UomConversionServiceImpl extends BaseEntityServiceImpl<UomConversion, UomConversionRequest> implements UomConversionService
{
	/**
	 * @param entityClass
	 */
	public UomConversionServiceImpl() {
		super(UomConversion.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.UomConversion#findById(java.lang.Integer)
	 */
	@Override
	public UomConversion findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.UomConversion#findByCode(java.lang.String)
	 */
	@Override
	public UomConversion findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.UomConversion#findByName(java.lang.String)
	 */
	@Override
	public UomConversion findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.UomConversion#findAll(java.util.Map)
	 */
	@Override
	public List<UomConversion> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.UomConversion#createUomConversion(com.nathanclaire.alantra.businessdata.rest.request.ServiceRequest)
	 */
	@Override
	public UomConversion createInstance(UomConversionRequest uomConversionRequest) {
		return createInsance(uomConversionRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.UomConversion#deleteUomConversion(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.UomConversion#updateUomConversion(com.nathanclaire.alantra.businessdata.rest.request.ServiceRequest)
	 */
	@Override
	public UomConversion updateInstance(UomConversionRequest uomConversionRequest) {
		return updateInstance(uomConversionRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected UomConversion loadModelFromRequest(UomConversionRequest uomConversionRequest) 
    {
		UomConversion uomConversion = new UomConversion();
    	Integer uomConversionId = uomConversionRequest.getId();
    	// Are we editing a UomConversion
    	if(uomConversionId != null) 
    	{
    		uomConversion = getEntityManager().find(UomConversion.class, uomConversionRequest.getId());
    		uomConversion.setLastModifiedDt(uomConversionRequest.getLastModifiedDt());
        	uomConversion.setLastModifiedUsr(getCurrentUserName(uomConversionRequest));
    	}
    	else
    	{
        	uomConversion.setCreatedDt(getCurrentSystemDate());
        	uomConversion.setCreatedByUsr(getCurrentUserName(uomConversionRequest));
    	}
    	uomConversion.setCode(uomConversionRequest.getCode());
    	uomConversion.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	uomConversion.setFromUomId(uomConversionRequest.getFromUomId()); 
    	uomConversion.setToUomId(uomConversionRequest.getToUomId()); 
    	uomConversion.setConversionFactor(uomConversionRequest.getConversionFactor()); 
    	uomConversion.setName(uomConversionRequest.getName()); 
    	uomConversion.setDescription(uomConversionRequest.getDescription()); 
    	uomConversion.setCode(uomConversionRequest.getCode()); 
    	uomConversion.setEffectiveDt(uomConversionRequest.getEffectiveDt()); 
    	uomConversion.setRecSt(uomConversionRequest.getRecSt()); 
		return uomConversion;
	}
}
