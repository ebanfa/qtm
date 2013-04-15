/**
 * 
 */
package com.nathanclaire.alantra.invoice.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.invoice.model.InvoiceType;
import com.nathanclaire.alantra.invoice.rest.request.InvoiceTypeRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class InvoiceTypeServiceImpl extends BaseEntityServiceImpl<InvoiceType, InvoiceTypeRequest> implements InvoiceTypeService
{
	/**
	 * @param entityClass
	 */
	public InvoiceTypeServiceImpl() {
		super(InvoiceType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceType#findById(java.lang.Integer)
	 */
	@Override
	public InvoiceType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceType#findByCode(java.lang.String)
	 */
	@Override
	public InvoiceType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceType#findByName(java.lang.String)
	 */
	@Override
	public InvoiceType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceType#findAll(java.util.Map)
	 */
	@Override
	public List<InvoiceType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceType#createInvoiceType(com.nathanclaire.alantra.invoice.rest.request.ServiceRequest)
	 */
	@Override
	public InvoiceType createInstance(InvoiceTypeRequest invoiceTypeRequest) {
		return createInsance(invoiceTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceType#deleteInvoiceType(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceType#updateInvoiceType(com.nathanclaire.alantra.invoice.rest.request.ServiceRequest)
	 */
	@Override
	public InvoiceType updateInstance(InvoiceTypeRequest invoiceTypeRequest) {
		return updateInstance(invoiceTypeRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected InvoiceType loadModelFromRequest(InvoiceTypeRequest invoiceTypeRequest) 
    {
		InvoiceType invoiceType = new InvoiceType();
    	Integer invoiceTypeId = invoiceTypeRequest.getId();
    	// Are we editing a InvoiceType
    	if(invoiceTypeId != null) 
    	{
    		invoiceType = getEntityManager().find(InvoiceType.class, invoiceTypeRequest.getId());
    		invoiceType.setLastModifiedDt(invoiceTypeRequest.getLastModifiedDt());
        	invoiceType.setLastModifiedUsr(getCurrentUserName(invoiceTypeRequest));
    	}
    	else
    	{
        	invoiceType.setCreatedDt(getCurrentSystemDate());
        	invoiceType.setCreatedByUsr(getCurrentUserName(invoiceTypeRequest));
    	}
    	invoiceType.setCode(invoiceTypeRequest.getCode());
    	invoiceType.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	invoiceType.setName(invoiceTypeRequest.getName()); 
    	invoiceType.setDescription(invoiceTypeRequest.getDescription()); 
    	invoiceType.setCode(invoiceTypeRequest.getCode()); 
    	invoiceType.setEffectiveDt(invoiceTypeRequest.getEffectiveDt()); 
    	invoiceType.setRecSt(invoiceTypeRequest.getRecSt()); 
		return invoiceType;
	}
}
