/**
 * 
 */
package com.nathanclaire.alantra.invoice.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.invoice.model.InvoiceRoleType;
import com.nathanclaire.alantra.invoice.rest.request.InvoiceRoleTypeRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class InvoiceRoleTypeServiceImpl extends BaseEntityServiceImpl<InvoiceRoleType> implements InvoiceRoleTypeService
{
	/**
	 * @param entityClass
	 */
	public InvoiceRoleTypeServiceImpl() {
		super(InvoiceRoleType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceRoleType#findById(java.lang.Integer)
	 */
	@Override
	public InvoiceRoleType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceRoleType#findByCode(java.lang.String)
	 */
	@Override
	public InvoiceRoleType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceRoleType#findByName(java.lang.String)
	 */
	@Override
	public InvoiceRoleType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceRoleType#findAll(java.util.Map)
	 */
	@Override
	public List<InvoiceRoleType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceRoleType#createInvoiceRoleType(com.nathanclaire.alantra.invoice.rest.request.ServiceRequest)
	 */
	@Override
	public InvoiceRoleType createInstance(BaseRequest invoiceRoleTypeRequest) {
		return createInsance(invoiceRoleTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceRoleType#deleteInvoiceRoleType(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceRoleType#updateInvoiceRoleType(com.nathanclaire.alantra.invoice.rest.request.ServiceRequest)
	 */
	@Override
	public InvoiceRoleType updateInstance(BaseRequest invoiceRoleTypeRequest) {
		return updateInstance(invoiceRoleTypeRequest);
	}
	
	/**
     * @param request
     * @return
     */
    protected InvoiceRoleType loadModelFromRequest(BaseRequest request) 
    {
    	InvoiceRoleTypeRequest invoiceRoleTypeRequest = (InvoiceRoleTypeRequest) request;
		InvoiceRoleType invoiceRoleType = new InvoiceRoleType();
    	Integer invoiceRoleTypeId = invoiceRoleTypeRequest.getId();
    	// Are we editing a InvoiceRoleType
    	if(invoiceRoleTypeId != null) 
    	{
    		invoiceRoleType = getEntityManager().find(InvoiceRoleType.class, invoiceRoleTypeRequest.getId());
    		invoiceRoleType.setLastModifiedDt(invoiceRoleTypeRequest.getLastModifiedDt());
        	invoiceRoleType.setLastModifiedUsr(getCurrentUserName(invoiceRoleTypeRequest));
    	}
    	else
    	{
        	invoiceRoleType.setCreatedDt(getCurrentSystemDate());
        	invoiceRoleType.setCreatedByUsr(getCurrentUserName(invoiceRoleTypeRequest));
    	}
    	invoiceRoleType.setCode(invoiceRoleTypeRequest.getCode());
    	invoiceRoleType.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	invoiceRoleType.setName(invoiceRoleTypeRequest.getName()); 
    	invoiceRoleType.setDescription(invoiceRoleTypeRequest.getDescription()); 
    	invoiceRoleType.setCode(invoiceRoleTypeRequest.getCode()); 
    	invoiceRoleType.setEffectiveDt(invoiceRoleTypeRequest.getEffectiveDt()); 
    	invoiceRoleType.setRecSt(invoiceRoleTypeRequest.getRecSt()); 
		return invoiceRoleType;
	}
}
