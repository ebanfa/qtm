/**
 * 
 */
package com.nathanclaire.alantra.invoice.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.invoice.model.Invoice;
import com.nathanclaire.alantra.invoice.request.InvoiceRequest;

import com.nathanclaire.alantra.invoice.model.InvoiceType;
import com.nathanclaire.alantra.party.model.Party;
import com.nathanclaire.alantra.party.model.ContactMechanism;
import com.nathanclaire.alantra.party.model.Party;
import com.nathanclaire.alantra.invoice.model.InvoiceTerm;

/**
 * @author administrator
 *
 */
@Stateless
public class InvoiceServiceImpl extends BaseEntityServiceImpl<Invoice, InvoiceRequest> implements InvoiceService
{
	/**
	 * @param entityClass
	 */
	public InvoiceServiceImpl() {
		super(Invoice.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.Invoice#findById(java.lang.Integer)
	 */
	@Override
	public Invoice findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.Invoice#findByCode(java.lang.String)
	 */
	@Override
	public Invoice findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.Invoice#findByName(java.lang.String)
	 */
	@Override
	public Invoice findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.Invoice#findAll(java.util.Map)
	 */
	@Override
	public List<Invoice> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.Invoice#createInvoice(com.nathanclaire.alantra.invoice.rest.request.ServiceRequest)
	 */
	@Override
	public Invoice create(InvoiceRequest invoiceRequest) {
		return createInstance(invoiceRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.Invoice#deleteInvoice(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.Invoice#updateInvoice(com.nathanclaire.alantra.invoice.rest.request.ServiceRequest)
	 */
	@Override
	public Invoice update(InvoiceRequest invoiceRequest) {
		return updateInstance(invoiceRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected Invoice loadModelFromRequest(InvoiceRequest invoiceRequest) 
    {
		Invoice invoice = new Invoice();
    	Integer invoiceId = invoiceRequest.getId();
    	// Are we editing a Invoice
    	if(invoiceId != null) 
    	{
    		invoice = getEntityManager().find(Invoice.class, invoiceRequest.getId());
    		invoice.setLastModifiedDt(invoiceRequest.getLastModifiedDt());
        	invoice.setLastModifiedUsr(getCurrentUserName(invoiceRequest));
    	}
    	else
    	{
        	invoice.setCreatedDt(getCurrentSystemDate());
        	invoice.setCreatedByUsr(getCurrentUserName(invoiceRequest));
    	}
    	invoice.setCode(invoiceRequest.getCode());
    	invoice.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (invoiceRequest.getInvoiceType() != null)
    	{
    		InvoiceType invoiceType = getEntityManager().find(InvoiceType.class, invoiceRequest.getInvoiceType());
    		invoice.setInvoiceType(invoiceType);
    	}
    	if (invoiceRequest.getPartyByToPartyId() != null)
    	{
    		Party partyByToPartyId = getEntityManager().find(Party.class, invoiceRequest.getPartyByToPartyId());
    		invoice.setPartyByToPartyId(partyByToPartyId);
    	}
    	if (invoiceRequest.getContactMechanism() != null)
    	{
    		ContactMechanism contactMechanism = getEntityManager().find(ContactMechanism.class, invoiceRequest.getContactMechanism());
    		invoice.setContactMechanism(contactMechanism);
    	}
    	if (invoiceRequest.getPartyByFromPartyId() != null)
    	{
    		Party partyByFromPartyId = getEntityManager().find(Party.class, invoiceRequest.getPartyByFromPartyId());
    		invoice.setPartyByFromPartyId(partyByFromPartyId);
    	}
    	if (invoiceRequest.getInvoiceTerm() != null)
    	{
    		InvoiceTerm invoiceTerm = getEntityManager().find(InvoiceTerm.class, invoiceRequest.getInvoiceTerm());
    		invoice.setInvoiceTerm(invoiceTerm);
    	}
    	invoice.setMessage(invoiceRequest.getMessage()); 
    	invoice.setDescription(invoiceRequest.getDescription()); 
    	invoice.setInvoiceDt(invoiceRequest.getInvoiceDt()); 
    	invoice.setCode(invoiceRequest.getCode()); 
    	invoice.setEffectiveDt(invoiceRequest.getEffectiveDt()); 
    	invoice.setRecSt(invoiceRequest.getRecSt()); 
		return invoice;
	}
}
