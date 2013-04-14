/**
 * 
 */
package com.nathanclaire.alantra.party.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.party.model.TelecommunicationsNumber;
import com.nathanclaire.alantra.party.rest.request.TelecommunicationsNumberRequest;

import com.nathanclaire.alantra.party.model.ContactMechanism;
import com.nathanclaire.alantra.party.rest.request.ContactMechanismRequest;

/**
 * @author administrator
 *
 */
@Stateless
public class TelecommunicationsNumberServiceImpl extends BaseEntityServiceImpl<TelecommunicationsNumber> implements TelecommunicationsNumberService
{
	/**
	 * @param entityClass
	 */
	public TelecommunicationsNumberServiceImpl() {
		super(TelecommunicationsNumber.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.TelecommunicationsNumber#findById(java.lang.Integer)
	 */
	@Override
	public TelecommunicationsNumber findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.TelecommunicationsNumber#findByCode(java.lang.String)
	 */
	@Override
	public TelecommunicationsNumber findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.TelecommunicationsNumber#findByName(java.lang.String)
	 */
	@Override
	public TelecommunicationsNumber findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.TelecommunicationsNumber#findAll(java.util.Map)
	 */
	@Override
	public List<TelecommunicationsNumber> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.TelecommunicationsNumber#createTelecommunicationsNumber(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public TelecommunicationsNumber createInstance(BaseRequest telecommunicationsNumberRequest) {
		return createInsance(telecommunicationsNumberRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.TelecommunicationsNumber#deleteTelecommunicationsNumber(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.TelecommunicationsNumber#updateTelecommunicationsNumber(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public TelecommunicationsNumber updateInstance(BaseRequest telecommunicationsNumberRequest) {
		return updateInstance(telecommunicationsNumberRequest);
	}
	
	/**
     * @param request
     * @return
     */
    protected TelecommunicationsNumber loadModelFromRequest(BaseRequest request) 
    {
    	TelecommunicationsNumberRequest telecommunicationsNumberRequest = (TelecommunicationsNumberRequest) request;
		TelecommunicationsNumber telecommunicationsNumber = new TelecommunicationsNumber();
    	Integer telecommunicationsNumberId = telecommunicationsNumberRequest.getId();
    	// Are we editing a TelecommunicationsNumber
    	if(telecommunicationsNumberId != null) 
    	{
    		telecommunicationsNumber = getEntityManager().find(TelecommunicationsNumber.class, telecommunicationsNumberRequest.getId());
    		telecommunicationsNumber.setLastModifiedDt(telecommunicationsNumberRequest.getLastModifiedDt());
        	telecommunicationsNumber.setLastModifiedUsr(getCurrentUserName(telecommunicationsNumberRequest));
    	}
    	else
    	{
        	telecommunicationsNumber.setCreatedDt(getCurrentSystemDate());
        	telecommunicationsNumber.setCreatedByUsr(getCurrentUserName(telecommunicationsNumberRequest));
    	}
    	telecommunicationsNumber.setCode(telecommunicationsNumberRequest.getCode());
    	telecommunicationsNumber.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (telecommunicationsNumberRequest.getContactMechanism() != null)
    	{
    		ContactMechanism contactMechanism = getEntityManager().find(ContactMechanism.class, telecommunicationsNumberRequest.getContactMechanism());
    		telecommunicationsNumber.setContactMechanism(contactMechanism);
    	}
    	telecommunicationsNumber.setAreaCode(telecommunicationsNumberRequest.getAreaCode()); 
    	telecommunicationsNumber.setContactNo(telecommunicationsNumberRequest.getContactNo()); 
    	telecommunicationsNumber.setCountryCd(telecommunicationsNumberRequest.getCountryCd()); 
    	telecommunicationsNumber.setDescription(telecommunicationsNumberRequest.getDescription()); 
    	telecommunicationsNumber.setCode(telecommunicationsNumberRequest.getCode()); 
    	telecommunicationsNumber.setEffectiveDt(telecommunicationsNumberRequest.getEffectiveDt()); 
    	telecommunicationsNumber.setRecSt(telecommunicationsNumberRequest.getRecSt()); 
		return telecommunicationsNumber;
	}
}
