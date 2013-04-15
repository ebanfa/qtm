/**
 * 
 */
package com.nathanclaire.alantra.businessdata.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.businessdata.model.TermType;
import com.nathanclaire.alantra.businessdata.rest.request.TermTypeRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class TermTypeServiceImpl extends BaseEntityServiceImpl<TermType, TermTypeRequest> implements TermTypeService
{
	/**
	 * @param entityClass
	 */
	public TermTypeServiceImpl() {
		super(TermType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.TermType#findById(java.lang.Integer)
	 */
	@Override
	public TermType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.TermType#findByCode(java.lang.String)
	 */
	@Override
	public TermType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.TermType#findByName(java.lang.String)
	 */
	@Override
	public TermType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.TermType#findAll(java.util.Map)
	 */
	@Override
	public List<TermType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.TermType#createTermType(com.nathanclaire.alantra.businessdata.rest.request.ServiceRequest)
	 */
	@Override
	public TermType createInstance(TermTypeRequest termTypeRequest) {
		return createInsance(termTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.TermType#deleteTermType(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.TermType#updateTermType(com.nathanclaire.alantra.businessdata.rest.request.ServiceRequest)
	 */
	@Override
	public TermType updateInstance(TermTypeRequest termTypeRequest) {
		return updateInstance(termTypeRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected TermType loadModelFromRequest(TermTypeRequest termTypeRequest) 
    {
		TermType termType = new TermType();
    	Integer termTypeId = termTypeRequest.getId();
    	// Are we editing a TermType
    	if(termTypeId != null) 
    	{
    		termType = getEntityManager().find(TermType.class, termTypeRequest.getId());
    		termType.setLastModifiedDt(termTypeRequest.getLastModifiedDt());
        	termType.setLastModifiedUsr(getCurrentUserName(termTypeRequest));
    	}
    	else
    	{
        	termType.setCreatedDt(getCurrentSystemDate());
        	termType.setCreatedByUsr(getCurrentUserName(termTypeRequest));
    	}
    	termType.setCode(termTypeRequest.getCode());
    	termType.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	termType.setName(termTypeRequest.getName()); 
    	termType.setDescription(termTypeRequest.getDescription()); 
    	termType.setCode(termTypeRequest.getCode()); 
    	termType.setEffectiveDt(termTypeRequest.getEffectiveDt()); 
    	termType.setRecSt(termTypeRequest.getRecSt()); 
		return termType;
	}
}
