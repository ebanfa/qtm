/**
 * 
 */
package com.nathanclaire.alantra.party.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.party.model.ElectronicAddress;
import com.nathanclaire.alantra.party.rest.request.ElectronicAddressRequest;

import com.nathanclaire.alantra.party.model.ContactMechanism;
import com.nathanclaire.alantra.party.rest.request.ContactMechanismRequest;

/**
 * @author administrator
 *
 */
@Stateless
public class ElectronicAddressServiceImpl extends BaseEntityServiceImpl<ElectronicAddress> implements ElectronicAddressService
{
	/**
	 * @param entityClass
	 */
	public ElectronicAddressServiceImpl() {
		super(ElectronicAddress.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ElectronicAddress#findById(java.lang.Integer)
	 */
	@Override
	public ElectronicAddress findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ElectronicAddress#findByCode(java.lang.String)
	 */
	@Override
	public ElectronicAddress findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ElectronicAddress#findByName(java.lang.String)
	 */
	@Override
	public ElectronicAddress findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ElectronicAddress#findAll(java.util.Map)
	 */
	@Override
	public List<ElectronicAddress> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ElectronicAddress#createElectronicAddress(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public ElectronicAddress createInstance(BaseRequest electronicAddressRequest) {
		return createInsance(electronicAddressRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ElectronicAddress#deleteElectronicAddress(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.ElectronicAddress#updateElectronicAddress(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public ElectronicAddress updateInstance(BaseRequest electronicAddressRequest) {
		return updateInstance(electronicAddressRequest);
	}
	
	/**
     * @param request
     * @return
     */
    protected ElectronicAddress loadModelFromRequest(BaseRequest request) 
    {
    	ElectronicAddressRequest electronicAddressRequest = (ElectronicAddressRequest) request;
		ElectronicAddress electronicAddress = new ElectronicAddress();
    	Integer electronicAddressId = electronicAddressRequest.getId();
    	// Are we editing a ElectronicAddress
    	if(electronicAddressId != null) 
    	{
    		electronicAddress = getEntityManager().find(ElectronicAddress.class, electronicAddressRequest.getId());
    		electronicAddress.setLastModifiedDt(electronicAddressRequest.getLastModifiedDt());
        	electronicAddress.setLastModifiedUsr(getCurrentUserName(electronicAddressRequest));
    	}
    	else
    	{
        	electronicAddress.setCreatedDt(getCurrentSystemDate());
        	electronicAddress.setCreatedByUsr(getCurrentUserName(electronicAddressRequest));
    	}
    	electronicAddress.setCode(electronicAddressRequest.getCode());
    	electronicAddress.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (electronicAddressRequest.getContactMechanism() != null)
    	{
    		ContactMechanism contactMechanism = getEntityManager().find(ContactMechanism.class, electronicAddressRequest.getContactMechanism());
    		electronicAddress.setContactMechanism(contactMechanism);
    	}
    	electronicAddress.setElecAddrTxt(electronicAddressRequest.getElecAddrTxt()); 
    	electronicAddress.setDescription(electronicAddressRequest.getDescription()); 
    	electronicAddress.setCode(electronicAddressRequest.getCode()); 
    	electronicAddress.setEffectiveDt(electronicAddressRequest.getEffectiveDt()); 
    	electronicAddress.setRecSt(electronicAddressRequest.getRecSt()); 
		return electronicAddress;
	}
}
