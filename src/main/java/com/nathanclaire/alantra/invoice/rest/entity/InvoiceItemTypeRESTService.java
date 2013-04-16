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
import com.nathanclaire.alantra.invoice.model.InvoiceItemType;
import com.nathanclaire.alantra.invoice.request.InvoiceItemTypeRequest;
import com.nathanclaire.alantra.invoice.service.entity.InvoiceItemTypeService;

/**
 * @author administrator
 *
 */
@Path("/invoiceitemtype")
@Stateless
public class InvoiceItemTypeRESTService extends BaseEntityRESTService<InvoiceItemType, InvoiceItemTypeRequest> 
{
	@Inject
	InvoiceItemTypeService invoiceItemTypeService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<InvoiceItemType> getAll(MultivaluedMap<String, String> queryParameters) {
		return invoiceItemTypeService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected InvoiceItemType getSingleInstance(Integer id) {
		return invoiceItemTypeService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return invoiceItemTypeService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(InvoiceItemTypeRequest request) {
		invoiceItemTypeService.create(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(InvoiceItemTypeRequest request) {
		invoiceItemTypeService.update(request);
		return null;
	}
	
}
