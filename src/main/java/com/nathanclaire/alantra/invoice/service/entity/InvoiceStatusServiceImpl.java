/**
 * 
 */
package com.nathanclaire.alantra.invoice.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.invoice.model.InvoiceStatus;
import com.nathanclaire.alantra.invoice.rest.request.InvoiceStatusRequest;

import com.nathanclaire.alantra.invoice.model.InvoiceStatusType;
import com.nathanclaire.alantra.invoice.model.Invoice;

/**
 * @author administrator
 *
 */
@Stateless
public class InvoiceStatusServiceImpl extends BaseEntityServiceImpl<InvoiceStatus, InvoiceStatusRequest> implements InvoiceStatusService
{
	/**
	 * @param entityClass
	 */
	public InvoiceStatusServiceImpl() {
		super(InvoiceStatus.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceStatus#findById(java.lang.Integer)
	 */
	@Override
	public InvoiceStatus findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceStatus#findByCode(java.lang.String)
	 */
	@Override
	public InvoiceStatus findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceStatus#findByName(java.lang.String)
	 */
	@Override
	public InvoiceStatus findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceStatus#findAll(java.util.Map)
	 */
	@Override
	public List<InvoiceStatus> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceStatus#createInvoiceStatus(com.nathanclaire.alantra.invoice.rest.request.ServiceRequest)
	 */
	@Override
	public InvoiceStatus createInstance(InvoiceStatusRequest invoiceStatusRequest) {
		return createInsance(invoiceStatusRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceStatus#deleteInvoiceStatus(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceStatus#updateInvoiceStatus(com.nathanclaire.alantra.invoice.rest.request.ServiceRequest)
	 */
	@Override
	public InvoiceStatus updateInstance(InvoiceStatusRequest invoiceStatusRequest) {
		return updateInstance(invoiceStatusRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected InvoiceStatus loadModelFromRequest(InvoiceStatusRequest invoiceStatusRequest) 
    {
		InvoiceStatus invoiceStatus = new InvoiceStatus();
    	Integer invoiceStatusId = invoiceStatusRequest.getId();
    	// Are we editing a InvoiceStatus
    	if(invoiceStatusId != null) 
    	{
    		invoiceStatus = getEntityManager().find(InvoiceStatus.class, invoiceStatusRequest.getId());
    		invoiceStatus.setLastModifiedDt(invoiceStatusRequest.getLastModifiedDt());
        	invoiceStatus.setLastModifiedUsr(getCurrentUserName(invoiceStatusRequest));
    	}
    	else
    	{
        	invoiceStatus.setCreatedDt(getCurrentSystemDate());
        	invoiceStatus.setCreatedByUsr(getCurrentUserName(invoiceStatusRequest));
    	}
    	invoiceStatus.setCode(invoiceStatusRequest.getCode());
    	invoiceStatus.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (invoiceStatusRequest.getInvoiceStatusType() != null)
    	{
    		InvoiceStatusType invoiceStatusType = getEntityManager().find(InvoiceStatusType.class, invoiceStatusRequest.getInvoiceStatusType());
    		invoiceStatus.setInvoiceStatusType(invoiceStatusType);
    	}
    	if (invoiceStatusRequest.getInvoice() != null)
    	{
    		Invoice invoice = getEntityManager().find(Invoice.class, invoiceStatusRequest.getInvoice());
    		invoiceStatus.setInvoice(invoice);
    	}
    	invoiceStatus.setName(invoiceStatusRequest.getName()); 
    	invoiceStatus.setDescription(invoiceStatusRequest.getDescription()); 
    	invoiceStatus.setCode(invoiceStatusRequest.getCode()); 
    	invoiceStatus.setEffectiveDt(invoiceStatusRequest.getEffectiveDt()); 
    	invoiceStatus.setRecSt(invoiceStatusRequest.getRecSt()); 
		return invoiceStatus;
	}
}
