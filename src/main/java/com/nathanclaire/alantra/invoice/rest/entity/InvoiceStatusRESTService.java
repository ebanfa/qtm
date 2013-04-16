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
import com.nathanclaire.alantra.invoice.model.InvoiceStatus;
import com.nathanclaire.alantra.invoice.request.InvoiceStatusRequest;
import com.nathanclaire.alantra.invoice.service.entity.InvoiceStatusService;

/**
 * @author administrator
 *
 */
@Path("/invoicestatus")
@Stateless
public class InvoiceStatusRESTService extends BaseEntityRESTService<InvoiceStatus, InvoiceStatusRequest> 
{
	@Inject
	InvoiceStatusService invoiceStatusService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<InvoiceStatus> getAll(MultivaluedMap<String, String> queryParameters) {
		return invoiceStatusService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected InvoiceStatus getSingleInstance(Integer id) {
		return invoiceStatusService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return invoiceStatusService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(InvoiceStatusRequest request) {
		invoiceStatusService.create(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(InvoiceStatusRequest request) {
		invoiceStatusService.update(request);
		return null;
	}
	
}
