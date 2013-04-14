/**
 * 
 */
package com.nathanclaire.alantra.invoice.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.invoice.model.InvoiceItemCategory;
import com.nathanclaire.alantra.invoice.rest.request.InvoiceItemCategoryRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class InvoiceItemCategoryServiceImpl extends BaseEntityServiceImpl<InvoiceItemCategory> implements InvoiceItemCategoryService
{
	/**
	 * @param entityClass
	 */
	public InvoiceItemCategoryServiceImpl() {
		super(InvoiceItemCategory.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceItemCategory#findById(java.lang.Integer)
	 */
	@Override
	public InvoiceItemCategory findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceItemCategory#findByCode(java.lang.String)
	 */
	@Override
	public InvoiceItemCategory findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceItemCategory#findByName(java.lang.String)
	 */
	@Override
	public InvoiceItemCategory findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceItemCategory#findAll(java.util.Map)
	 */
	@Override
	public List<InvoiceItemCategory> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceItemCategory#createInvoiceItemCategory(com.nathanclaire.alantra.invoice.rest.request.ServiceRequest)
	 */
	@Override
	public InvoiceItemCategory createInstance(BaseRequest invoiceItemCategoryRequest) {
		return createInsance(invoiceItemCategoryRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceItemCategory#deleteInvoiceItemCategory(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceItemCategory#updateInvoiceItemCategory(com.nathanclaire.alantra.invoice.rest.request.ServiceRequest)
	 */
	@Override
	public InvoiceItemCategory updateInstance(BaseRequest invoiceItemCategoryRequest) {
		return updateInstance(invoiceItemCategoryRequest);
	}
	
	/**
     * @param request
     * @return
     */
    protected InvoiceItemCategory loadModelFromRequest(BaseRequest request) 
    {
    	InvoiceItemCategoryRequest invoiceItemCategoryRequest = (InvoiceItemCategoryRequest) request;
		InvoiceItemCategory invoiceItemCategory = new InvoiceItemCategory();
    	Integer invoiceItemCategoryId = invoiceItemCategoryRequest.getId();
    	// Are we editing a InvoiceItemCategory
    	if(invoiceItemCategoryId != null) 
    	{
    		invoiceItemCategory = getEntityManager().find(InvoiceItemCategory.class, invoiceItemCategoryRequest.getId());
    		invoiceItemCategory.setLastModifiedDt(invoiceItemCategoryRequest.getLastModifiedDt());
        	invoiceItemCategory.setLastModifiedUsr(getCurrentUserName(invoiceItemCategoryRequest));
    	}
    	else
    	{
        	invoiceItemCategory.setCreatedDt(getCurrentSystemDate());
        	invoiceItemCategory.setCreatedByUsr(getCurrentUserName(invoiceItemCategoryRequest));
    	}
    	invoiceItemCategory.setCode(invoiceItemCategoryRequest.getCode());
    	invoiceItemCategory.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	invoiceItemCategory.setName(invoiceItemCategoryRequest.getName()); 
    	invoiceItemCategory.setDescription(invoiceItemCategoryRequest.getDescription()); 
    	invoiceItemCategory.setCode(invoiceItemCategoryRequest.getCode()); 
    	invoiceItemCategory.setEffectiveDt(invoiceItemCategoryRequest.getEffectiveDt()); 
    	invoiceItemCategory.setRecSt(invoiceItemCategoryRequest.getRecSt()); 
		return invoiceItemCategory;
	}
}
