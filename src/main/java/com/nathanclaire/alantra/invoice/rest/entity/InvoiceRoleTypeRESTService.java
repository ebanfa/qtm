/**
 * 
 */
package com.nathanclaire.alantra.invoice.rest.entity;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.nathanclaire.alantra.base.rest.BaseEntityRESTService;
import com.nathanclaire.alantra.invoice.model.InvoiceRoleType;
import com.nathanclaire.alantra.invoice.request.InvoiceRoleTypeRequest;
import com.nathanclaire.alantra.invoice.service.entity.InvoiceRoleTypeService;

/**
 * @author administrator
 *
 */
@Path("/invoiceroletype")
@Stateless
public class InvoiceRoleTypeRESTService extends BaseEntityRESTService<InvoiceRoleType, InvoiceRoleTypeRequest> 
{
	@Inject
	InvoiceRoleTypeService invoiceRoleTypeService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<InvoiceRoleType> getAll(MultivaluedMap<String, String> queryParameters) {
		return invoiceRoleTypeService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected InvoiceRoleType getSingleInstance(Integer id) {
		return invoiceRoleTypeService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return invoiceRoleTypeService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(InvoiceRoleTypeRequest request) {
		invoiceRoleTypeService.create(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(InvoiceRoleTypeRequest request) {
		invoiceRoleTypeService.update(request);
		return null;
	}
	
}
