/**
 * 
 */
package com.nathanclaire.alantra.channel.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.channel.model.HostType;
import com.nathanclaire.alantra.channel.request.HostTypeRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class HostTypeServiceImpl extends BaseEntityServiceImpl<HostType, HostTypeRequest> implements HostTypeService
{
	/**
	 * @param entityClass
	 */
	public HostTypeServiceImpl() {
		super(HostType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.HostType#findById(java.lang.Integer)
	 */
	@Override
	public HostType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.HostType#findByCode(java.lang.String)
	 */
	@Override
	public HostType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.HostType#findByName(java.lang.String)
	 */
	@Override
	public HostType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.HostType#findAll(java.util.Map)
	 */
	@Override
	public List<HostType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.HostType#createHostType(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public HostType create(HostTypeRequest hostTypeRequest) {
		return createInstance(hostTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.HostType#deleteHostType(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.HostType#updateHostType(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public HostType update(HostTypeRequest hostTypeRequest) {
		return updateInstance(hostTypeRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected HostType loadModelFromRequest(HostTypeRequest hostTypeRequest) 
    {
		HostType hostType = new HostType();
    	Integer hostTypeId = hostTypeRequest.getId();
    	// Are we editing a HostType
    	if(hostTypeId != null) 
    	{
    		hostType = getEntityManager().find(HostType.class, hostTypeRequest.getId());
    		hostType.setLastModifiedDt(hostTypeRequest.getLastModifiedDt());
        	hostType.setLastModifiedUsr(getCurrentUserName(hostTypeRequest));
    	}
    	else
    	{
        	hostType.setCreatedDt(getCurrentSystemDate());
        	hostType.setCreatedByUsr(getCurrentUserName(hostTypeRequest));
    	}
    	hostType.setCode(hostTypeRequest.getCode());
    	hostType.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	hostType.setName(hostTypeRequest.getName()); 
    	hostType.setDescription(hostTypeRequest.getDescription()); 
    	hostType.setCode(hostTypeRequest.getCode()); 
    	hostType.setEffectiveDt(hostTypeRequest.getEffectiveDt()); 
    	hostType.setRecSt(hostTypeRequest.getRecSt()); 
		return hostType;
	}
}
