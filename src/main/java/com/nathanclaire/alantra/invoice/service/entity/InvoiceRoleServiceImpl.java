/**
 * 
 */
package com.nathanclaire.alantra.invoice.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.invoice.model.InvoiceRole;
import com.nathanclaire.alantra.invoice.rest.request.InvoiceRoleRequest;

import com.nathanclaire.alantra.invoice.model.InvoiceRoleType;
import com.nathanclaire.alantra.party.model.Party;
import com.nathanclaire.alantra.invoice.model.Invoice;

/**
 * @author administrator
 *
 */
@Stateless
public class InvoiceRoleServiceImpl extends BaseEntityServiceImpl<InvoiceRole, InvoiceRoleRequest> implements InvoiceRoleService
{
	/**
	 * @param entityClass
	 */
	public InvoiceRoleServiceImpl() {
		super(InvoiceRole.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceRole#findById(java.lang.Integer)
	 */
	@Override
	public InvoiceRole findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceRole#findByCode(java.lang.String)
	 */
	@Override
	public InvoiceRole findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceRole#findByName(java.lang.String)
	 */
	@Override
	public InvoiceRole findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceRole#findAll(java.util.Map)
	 */
	@Override
	public List<InvoiceRole> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceRole#createInvoiceRole(com.nathanclaire.alantra.invoice.rest.request.ServiceRequest)
	 */
	@Override
	public InvoiceRole createInstance(InvoiceRoleRequest invoiceRoleRequest) {
		return createInsance(invoiceRoleRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceRole#deleteInvoiceRole(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceRole#updateInvoiceRole(com.nathanclaire.alantra.invoice.rest.request.ServiceRequest)
	 */
	@Override
	public InvoiceRole updateInstance(InvoiceRoleRequest invoiceRoleRequest) {
		return updateInstance(invoiceRoleRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected InvoiceRole loadModelFromRequest(InvoiceRoleRequest invoiceRoleRequest) 
    {
		InvoiceRole invoiceRole = new InvoiceRole();
    	Integer invoiceRoleId = invoiceRoleRequest.getId();
    	// Are we editing a InvoiceRole
    	if(invoiceRoleId != null) 
    	{
    		invoiceRole = getEntityManager().find(InvoiceRole.class, invoiceRoleRequest.getId());
    		invoiceRole.setLastModifiedDt(invoiceRoleRequest.getLastModifiedDt());
        	invoiceRole.setLastModifiedUsr(getCurrentUserName(invoiceRoleRequest));
    	}
    	else
    	{
        	invoiceRole.setCreatedDt(getCurrentSystemDate());
        	invoiceRole.setCreatedByUsr(getCurrentUserName(invoiceRoleRequest));
    	}
    	invoiceRole.setCode(invoiceRoleRequest.getCode());
    	invoiceRole.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (invoiceRoleRequest.getInvoiceRoleType() != null)
    	{
    		InvoiceRoleType invoiceRoleType = getEntityManager().find(InvoiceRoleType.class, invoiceRoleRequest.getInvoiceRoleType());
    		invoiceRole.setInvoiceRoleType(invoiceRoleType);
    	}
    	if (invoiceRoleRequest.getParty() != null)
    	{
    		Party party = getEntityManager().find(Party.class, invoiceRoleRequest.getParty());
    		invoiceRole.setParty(party);
    	}
    	if (invoiceRoleRequest.getInvoice() != null)
    	{
    		Invoice invoice = getEntityManager().find(Invoice.class, invoiceRoleRequest.getInvoice());
    		invoiceRole.setInvoice(invoice);
    	}
    	invoiceRole.setDescription(invoiceRoleRequest.getDescription()); 
    	invoiceRole.setPercentage(invoiceRoleRequest.getPercentage()); 
    	invoiceRole.setCode(invoiceRoleRequest.getCode()); 
    	invoiceRole.setEffectiveDt(invoiceRoleRequest.getEffectiveDt()); 
    	invoiceRole.setRecSt(invoiceRoleRequest.getRecSt()); 
		return invoiceRole;
	}
}
