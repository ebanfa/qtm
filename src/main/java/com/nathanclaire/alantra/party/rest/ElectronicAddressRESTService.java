/**
 * 
 */
package com.nathanclaire.alantra.party.rest;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.nathanclaire.alantra.base.rest.BaseEntityRESTService;
import com.nathanclaire.alantra.party.model.ElectronicAddress;
import com.nathanclaire.alantra.party.rest.request.ElectronicAddressRequest;
import com.nathanclaire.alantra.party.service.entity.ElectronicAddressService;

/**
 * @author administrator
 *
 */
@Path("/electronicaddress")
@Stateless
public class ElectronicAddressRESTService extends BaseEntityRESTService<ElectronicAddress, ElectronicAddressRequest> 
{
	@Inject
	ElectronicAddressService electronicAddressService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<ElectronicAddress> getAll(MultivaluedMap<String, String> queryParameters) {
		return electronicAddressService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected ElectronicAddress getSingleInstance(Integer id) {
		return electronicAddressService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return electronicAddressService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(ElectronicAddressRequest request) {
		electronicAddressService.createInstance(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(ElectronicAddressRequest request) {
		electronicAddressService.updateInstance(request);
		return null;
	}
	
}
