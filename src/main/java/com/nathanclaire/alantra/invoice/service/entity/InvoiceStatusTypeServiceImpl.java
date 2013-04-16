/**
 * 
 */
package com.nathanclaire.alantra.invoice.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.invoice.model.InvoiceStatusType;
import com.nathanclaire.alantra.invoice.request.InvoiceStatusTypeRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class InvoiceStatusTypeServiceImpl extends BaseEntityServiceImpl<InvoiceStatusType, InvoiceStatusTypeRequest> implements InvoiceStatusTypeService
{
	/**
	 * @param entityClass
	 */
	public InvoiceStatusTypeServiceImpl() {
		super(InvoiceStatusType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceStatusType#findById(java.lang.Integer)
	 */
	@Override
	public InvoiceStatusType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceStatusType#findByCode(java.lang.String)
	 */
	@Override
	public InvoiceStatusType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceStatusType#findByName(java.lang.String)
	 */
	@Override
	public InvoiceStatusType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceStatusType#findAll(java.util.Map)
	 */
	@Override
	public List<InvoiceStatusType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceStatusType#createInvoiceStatusType(com.nathanclaire.alantra.invoice.rest.request.ServiceRequest)
	 */
	@Override
	public InvoiceStatusType create(InvoiceStatusTypeRequest invoiceStatusTypeRequest) {
		return createInstance(invoiceStatusTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceStatusType#deleteInvoiceStatusType(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceStatusType#updateInvoiceStatusType(com.nathanclaire.alantra.invoice.rest.request.ServiceRequest)
	 */
	@Override
	public InvoiceStatusType update(InvoiceStatusTypeRequest invoiceStatusTypeRequest) {
		return updateInstance(invoiceStatusTypeRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected InvoiceStatusType loadModelFromRequest(InvoiceStatusTypeRequest invoiceStatusTypeRequest) 
    {
		InvoiceStatusType invoiceStatusType = new InvoiceStatusType();
    	Integer invoiceStatusTypeId = invoiceStatusTypeRequest.getId();
    	// Are we editing a InvoiceStatusType
    	if(invoiceStatusTypeId != null) 
    	{
    		invoiceStatusType = getEntityManager().find(InvoiceStatusType.class, invoiceStatusTypeRequest.getId());
    		invoiceStatusType.setLastModifiedDt(invoiceStatusTypeRequest.getLastModifiedDt());
        	invoiceStatusType.setLastModifiedUsr(getCurrentUserName(invoiceStatusTypeRequest));
    	}
    	else
    	{
        	invoiceStatusType.setCreatedDt(getCurrentSystemDate());
        	invoiceStatusType.setCreatedByUsr(getCurrentUserName(invoiceStatusTypeRequest));
    	}
    	invoiceStatusType.setCode(invoiceStatusTypeRequest.getCode());
    	invoiceStatusType.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	invoiceStatusType.setName(invoiceStatusTypeRequest.getName()); 
    	invoiceStatusType.setDescription(invoiceStatusTypeRequest.getDescription()); 
    	invoiceStatusType.setCode(invoiceStatusTypeRequest.getCode()); 
    	invoiceStatusType.setEffectiveDt(invoiceStatusTypeRequest.getEffectiveDt()); 
    	invoiceStatusType.setRecSt(invoiceStatusTypeRequest.getRecSt()); 
		return invoiceStatusType;
	}
}
