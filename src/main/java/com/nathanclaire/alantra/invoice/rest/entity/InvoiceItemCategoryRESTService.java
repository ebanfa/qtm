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
import com.nathanclaire.alantra.invoice.model.InvoiceItemCategory;
import com.nathanclaire.alantra.invoice.request.InvoiceItemCategoryRequest;
import com.nathanclaire.alantra.invoice.service.entity.InvoiceItemCategoryService;

/**
 * @author administrator
 *
 */
@Path("/invoiceitemcategory")
@Stateless
public class InvoiceItemCategoryRESTService extends BaseEntityRESTService<InvoiceItemCategory, InvoiceItemCategoryRequest> 
{
	@Inject
	InvoiceItemCategoryService invoiceItemCategoryService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<InvoiceItemCategory> getAll(MultivaluedMap<String, String> queryParameters) {
		return invoiceItemCategoryService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected InvoiceItemCategory getSingleInstance(Integer id) {
		return invoiceItemCategoryService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return invoiceItemCategoryService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(InvoiceItemCategoryRequest request) {
		invoiceItemCategoryService.create(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(InvoiceItemCategoryRequest request) {
		invoiceItemCategoryService.update(request);
		return null;
	}
	
}
