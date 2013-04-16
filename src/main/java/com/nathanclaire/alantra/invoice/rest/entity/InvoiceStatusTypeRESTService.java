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
import com.nathanclaire.alantra.invoice.model.InvoiceStatusType;
import com.nathanclaire.alantra.invoice.request.InvoiceStatusTypeRequest;
import com.nathanclaire.alantra.invoice.service.entity.InvoiceStatusTypeService;

/**
 * @author administrator
 *
 */
@Path("/invoicestatustype")
@Stateless
public class InvoiceStatusTypeRESTService extends BaseEntityRESTService<InvoiceStatusType, InvoiceStatusTypeRequest> 
{
	@Inject
	InvoiceStatusTypeService invoiceStatusTypeService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<InvoiceStatusType> getAll(MultivaluedMap<String, String> queryParameters) {
		return invoiceStatusTypeService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected InvoiceStatusType getSingleInstance(Integer id) {
		return invoiceStatusTypeService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return invoiceStatusTypeService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(InvoiceStatusTypeRequest request) {
		invoiceStatusTypeService.create(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(InvoiceStatusTypeRequest request) {
		invoiceStatusTypeService.update(request);
		return null;
	}
	
}
