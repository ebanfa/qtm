/**
 * 
 */
package com.nathanclaire.alantra.businessdata.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.businessdata.model.Uom;
import com.nathanclaire.alantra.businessdata.rest.request.UomRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class UomServiceImpl extends BaseEntityServiceImpl<Uom, UomRequest> implements UomService
{
	/**
	 * @param entityClass
	 */
	public UomServiceImpl() {
		super(Uom.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.Uom#findById(java.lang.Integer)
	 */
	@Override
	public Uom findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.Uom#findByCode(java.lang.String)
	 */
	@Override
	public Uom findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.Uom#findByName(java.lang.String)
	 */
	@Override
	public Uom findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.Uom#findAll(java.util.Map)
	 */
	@Override
	public List<Uom> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.Uom#createUom(com.nathanclaire.alantra.businessdata.rest.request.ServiceRequest)
	 */
	@Override
	public Uom createInstance(UomRequest uomRequest) {
		return createInsance(uomRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.Uom#deleteUom(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.Uom#updateUom(com.nathanclaire.alantra.businessdata.rest.request.ServiceRequest)
	 */
	@Override
	public Uom updateInstance(UomRequest uomRequest) {
		return updateInstance(uomRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected Uom loadModelFromRequest(UomRequest uomRequest) 
    {
		Uom uom = new Uom();
    	Integer uomId = uomRequest.getId();
    	// Are we editing a Uom
    	if(uomId != null) 
    	{
    		uom = getEntityManager().find(Uom.class, uomRequest.getId());
    		uom.setLastModifiedDt(uomRequest.getLastModifiedDt());
        	uom.setLastModifiedUsr(getCurrentUserName(uomRequest));
    	}
    	else
    	{
        	uom.setCreatedDt(getCurrentSystemDate());
        	uom.setCreatedByUsr(getCurrentUserName(uomRequest));
    	}
    	uom.setCode(uomRequest.getCode());
    	uom.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	uom.setName(uomRequest.getName()); 
    	uom.setDescription(uomRequest.getDescription()); 
    	uom.setCode(uomRequest.getCode()); 
    	uom.setEffectiveDt(uomRequest.getEffectiveDt()); 
    	uom.setRecSt(uomRequest.getRecSt()); 
		return uom;
	}
}
