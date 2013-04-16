/**
 * 
 */
package com.nathanclaire.alantra.party.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.party.model.RoleType;
import com.nathanclaire.alantra.party.request.RoleTypeRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class RoleTypeServiceImpl extends BaseEntityServiceImpl<RoleType, RoleTypeRequest> implements RoleTypeService
{
	/**
	 * @param entityClass
	 */
	public RoleTypeServiceImpl() {
		super(RoleType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.RoleType#findById(java.lang.Integer)
	 */
	@Override
	public RoleType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.RoleType#findByCode(java.lang.String)
	 */
	@Override
	public RoleType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.RoleType#findByName(java.lang.String)
	 */
	@Override
	public RoleType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.RoleType#findAll(java.util.Map)
	 */
	@Override
	public List<RoleType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.RoleType#createRoleType(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public RoleType create(RoleTypeRequest roleTypeRequest) {
		return createInstance(roleTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.RoleType#deleteRoleType(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.RoleType#updateRoleType(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public RoleType update(RoleTypeRequest roleTypeRequest) {
		return updateInstance(roleTypeRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected RoleType loadModelFromRequest(RoleTypeRequest roleTypeRequest) 
    {
		RoleType roleType = new RoleType();
    	Integer roleTypeId = roleTypeRequest.getId();
    	// Are we editing a RoleType
    	if(roleTypeId != null) 
    	{
    		roleType = getEntityManager().find(RoleType.class, roleTypeRequest.getId());
    		roleType.setLastModifiedDt(roleTypeRequest.getLastModifiedDt());
        	roleType.setLastModifiedUsr(getCurrentUserName(roleTypeRequest));
    	}
    	else
    	{
        	roleType.setCreatedDt(getCurrentSystemDate());
        	roleType.setCreatedByUsr(getCurrentUserName(roleTypeRequest));
    	}
    	roleType.setCode(roleTypeRequest.getCode());
    	roleType.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	roleType.setName(roleTypeRequest.getName()); 
    	roleType.setDescription(roleTypeRequest.getDescription()); 
    	roleType.setCode(roleTypeRequest.getCode()); 
    	roleType.setEffectiveDt(roleTypeRequest.getEffectiveDt()); 
    	roleType.setRecSt(roleTypeRequest.getRecSt()); 
		return roleType;
	}
}
