/**
 * 
 */
package com.nathanclaire.alantra.invoice.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.invoice.model.InvoiceItemType;
import com.nathanclaire.alantra.invoice.rest.request.InvoiceItemTypeRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class InvoiceItemTypeServiceImpl extends BaseEntityServiceImpl<InvoiceItemType, InvoiceItemTypeRequest> implements InvoiceItemTypeService
{
	/**
	 * @param entityClass
	 */
	public InvoiceItemTypeServiceImpl() {
		super(InvoiceItemType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceItemType#findById(java.lang.Integer)
	 */
	@Override
	public InvoiceItemType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceItemType#findByCode(java.lang.String)
	 */
	@Override
	public InvoiceItemType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceItemType#findByName(java.lang.String)
	 */
	@Override
	public InvoiceItemType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceItemType#findAll(java.util.Map)
	 */
	@Override
	public List<InvoiceItemType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceItemType#createInvoiceItemType(com.nathanclaire.alantra.invoice.rest.request.ServiceRequest)
	 */
	@Override
	public InvoiceItemType createInstance(InvoiceItemTypeRequest invoiceItemTypeRequest) {
		return createInsance(invoiceItemTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceItemType#deleteInvoiceItemType(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceItemType#updateInvoiceItemType(com.nathanclaire.alantra.invoice.rest.request.ServiceRequest)
	 */
	@Override
	public InvoiceItemType updateInstance(InvoiceItemTypeRequest invoiceItemTypeRequest) {
		return updateInstance(invoiceItemTypeRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected InvoiceItemType loadModelFromRequest(InvoiceItemTypeRequest invoiceItemTypeRequest) 
    {
		InvoiceItemType invoiceItemType = new InvoiceItemType();
    	Integer invoiceItemTypeId = invoiceItemTypeRequest.getId();
    	// Are we editing a InvoiceItemType
    	if(invoiceItemTypeId != null) 
    	{
    		invoiceItemType = getEntityManager().find(InvoiceItemType.class, invoiceItemTypeRequest.getId());
    		invoiceItemType.setLastModifiedDt(invoiceItemTypeRequest.getLastModifiedDt());
        	invoiceItemType.setLastModifiedUsr(getCurrentUserName(invoiceItemTypeRequest));
    	}
    	else
    	{
        	invoiceItemType.setCreatedDt(getCurrentSystemDate());
        	invoiceItemType.setCreatedByUsr(getCurrentUserName(invoiceItemTypeRequest));
    	}
    	invoiceItemType.setCode(invoiceItemTypeRequest.getCode());
    	invoiceItemType.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	invoiceItemType.setName(invoiceItemTypeRequest.getName()); 
    	invoiceItemType.setDescription(invoiceItemTypeRequest.getDescription()); 
    	invoiceItemType.setCode(invoiceItemTypeRequest.getCode()); 
    	invoiceItemType.setEffectiveDt(invoiceItemTypeRequest.getEffectiveDt()); 
    	invoiceItemType.setRecSt(invoiceItemTypeRequest.getRecSt()); 
		return invoiceItemType;
	}
}
