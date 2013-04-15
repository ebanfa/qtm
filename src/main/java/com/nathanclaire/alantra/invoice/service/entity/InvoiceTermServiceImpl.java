/**
 * 
 */
package com.nathanclaire.alantra.invoice.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.invoice.model.InvoiceTerm;
import com.nathanclaire.alantra.invoice.rest.request.InvoiceTermRequest;

import com.nathanclaire.alantra.businessdata.model.TermType;

/**
 * @author administrator
 *
 */
@Stateless
public class InvoiceTermServiceImpl extends BaseEntityServiceImpl<InvoiceTerm, InvoiceTermRequest> implements InvoiceTermService
{
	/**
	 * @param entityClass
	 */
	public InvoiceTermServiceImpl() {
		super(InvoiceTerm.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceTerm#findById(java.lang.Integer)
	 */
	@Override
	public InvoiceTerm findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceTerm#findByCode(java.lang.String)
	 */
	@Override
	public InvoiceTerm findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceTerm#findByName(java.lang.String)
	 */
	@Override
	public InvoiceTerm findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceTerm#findAll(java.util.Map)
	 */
	@Override
	public List<InvoiceTerm> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceTerm#createInvoiceTerm(com.nathanclaire.alantra.invoice.rest.request.ServiceRequest)
	 */
	@Override
	public InvoiceTerm createInstance(InvoiceTermRequest invoiceTermRequest) {
		return createInsance(invoiceTermRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceTerm#deleteInvoiceTerm(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceTerm#updateInvoiceTerm(com.nathanclaire.alantra.invoice.rest.request.ServiceRequest)
	 */
	@Override
	public InvoiceTerm updateInstance(InvoiceTermRequest invoiceTermRequest) {
		return updateInstance(invoiceTermRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected InvoiceTerm loadModelFromRequest(InvoiceTermRequest invoiceTermRequest) 
    {
		InvoiceTerm invoiceTerm = new InvoiceTerm();
    	Integer invoiceTermId = invoiceTermRequest.getId();
    	// Are we editing a InvoiceTerm
    	if(invoiceTermId != null) 
    	{
    		invoiceTerm = getEntityManager().find(InvoiceTerm.class, invoiceTermRequest.getId());
    		invoiceTerm.setLastModifiedDt(invoiceTermRequest.getLastModifiedDt());
        	invoiceTerm.setLastModifiedUsr(getCurrentUserName(invoiceTermRequest));
    	}
    	else
    	{
        	invoiceTerm.setCreatedDt(getCurrentSystemDate());
        	invoiceTerm.setCreatedByUsr(getCurrentUserName(invoiceTermRequest));
    	}
    	invoiceTerm.setCode(invoiceTermRequest.getCode());
    	invoiceTerm.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (invoiceTermRequest.getTermType() != null)
    	{
    		TermType termType = getEntityManager().find(TermType.class, invoiceTermRequest.getTermType());
    		invoiceTerm.setTermType(termType);
    	}
    	invoiceTerm.setTermVal(invoiceTermRequest.getTermVal()); 
    	invoiceTerm.setDescription(invoiceTermRequest.getDescription()); 
    	invoiceTerm.setCode(invoiceTermRequest.getCode()); 
    	invoiceTerm.setEffectiveDt(invoiceTermRequest.getEffectiveDt()); 
    	invoiceTerm.setRecSt(invoiceTermRequest.getRecSt()); 
		return invoiceTerm;
	}
}
