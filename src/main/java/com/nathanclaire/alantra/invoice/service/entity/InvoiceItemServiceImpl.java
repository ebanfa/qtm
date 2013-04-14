/**
 * 
 */
package com.nathanclaire.alantra.invoice.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.invoice.model.InvoiceItem;
import com.nathanclaire.alantra.invoice.rest.request.InvoiceItemRequest;

import com.nathanclaire.alantra.invoice.model.InvoiceItemType;
import com.nathanclaire.alantra.invoice.rest.request.InvoiceItemTypeRequest;
import com.nathanclaire.alantra.invoice.model.InvoiceItemCategory;
import com.nathanclaire.alantra.invoice.rest.request.InvoiceItemCategoryRequest;
import com.nathanclaire.alantra.product.model.Product;
import com.nathanclaire.alantra.product.rest.request.ProductRequest;
import com.nathanclaire.alantra.product.model.ProductFeature;
import com.nathanclaire.alantra.product.rest.request.ProductFeatureRequest;
import com.nathanclaire.alantra.invoice.model.Invoice;
import com.nathanclaire.alantra.invoice.rest.request.InvoiceRequest;

/**
 * @author administrator
 *
 */
@Stateless
public class InvoiceItemServiceImpl extends BaseEntityServiceImpl<InvoiceItem> implements InvoiceItemService
{
	/**
	 * @param entityClass
	 */
	public InvoiceItemServiceImpl() {
		super(InvoiceItem.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceItem#findById(java.lang.Integer)
	 */
	@Override
	public InvoiceItem findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceItem#findByCode(java.lang.String)
	 */
	@Override
	public InvoiceItem findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceItem#findByName(java.lang.String)
	 */
	@Override
	public InvoiceItem findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceItem#findAll(java.util.Map)
	 */
	@Override
	public List<InvoiceItem> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceItem#createInvoiceItem(com.nathanclaire.alantra.invoice.rest.request.ServiceRequest)
	 */
	@Override
	public InvoiceItem createInstance(BaseRequest invoiceItemRequest) {
		return createInsance(invoiceItemRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceItem#deleteInvoiceItem(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.InvoiceItem#updateInvoiceItem(com.nathanclaire.alantra.invoice.rest.request.ServiceRequest)
	 */
	@Override
	public InvoiceItem updateInstance(BaseRequest invoiceItemRequest) {
		return updateInstance(invoiceItemRequest);
	}
	
	/**
     * @param request
     * @return
     */
    protected InvoiceItem loadModelFromRequest(BaseRequest request) 
    {
    	InvoiceItemRequest invoiceItemRequest = (InvoiceItemRequest) request;
		InvoiceItem invoiceItem = new InvoiceItem();
    	Integer invoiceItemId = invoiceItemRequest.getId();
    	// Are we editing a InvoiceItem
    	if(invoiceItemId != null) 
    	{
    		invoiceItem = getEntityManager().find(InvoiceItem.class, invoiceItemRequest.getId());
    		invoiceItem.setLastModifiedDt(invoiceItemRequest.getLastModifiedDt());
        	invoiceItem.setLastModifiedUsr(getCurrentUserName(invoiceItemRequest));
    	}
    	else
    	{
        	invoiceItem.setCreatedDt(getCurrentSystemDate());
        	invoiceItem.setCreatedByUsr(getCurrentUserName(invoiceItemRequest));
    	}
    	invoiceItem.setCode(invoiceItemRequest.getCode());
    	invoiceItem.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (invoiceItemRequest.getInvoiceItemType() != null)
    	{
    		InvoiceItemType invoiceItemType = getEntityManager().find(InvoiceItemType.class, invoiceItemRequest.getInvoiceItemType());
    		invoiceItem.setInvoiceItemType(invoiceItemType);
    	}
    	if (invoiceItemRequest.getInvoiceItemCategory() != null)
    	{
    		InvoiceItemCategory invoiceItemCategory = getEntityManager().find(InvoiceItemCategory.class, invoiceItemRequest.getInvoiceItemCategory());
    		invoiceItem.setInvoiceItemCategory(invoiceItemCategory);
    	}
    	if (invoiceItemRequest.getProduct() != null)
    	{
    		Product product = getEntityManager().find(Product.class, invoiceItemRequest.getProduct());
    		invoiceItem.setProduct(product);
    	}
    	if (invoiceItemRequest.getProductFeature() != null)
    	{
    		ProductFeature productFeature = getEntityManager().find(ProductFeature.class, invoiceItemRequest.getProductFeature());
    		invoiceItem.setProductFeature(productFeature);
    	}
    	if (invoiceItemRequest.getInvoice() != null)
    	{
    		Invoice invoice = getEntityManager().find(Invoice.class, invoiceItemRequest.getInvoice());
    		invoiceItem.setInvoice(invoice);
    	}
    	invoiceItem.setName(invoiceItemRequest.getName()); 
    	invoiceItem.setDescription(invoiceItemRequest.getDescription()); 
    	invoiceItem.setTaxableFg(invoiceItemRequest.getTaxableFg()); 
    	invoiceItem.setQuantity(invoiceItemRequest.getQuantity()); 
    	invoiceItem.setAmount(invoiceItemRequest.getAmount()); 
    	invoiceItem.setCode(invoiceItemRequest.getCode()); 
    	invoiceItem.setEffectiveDt(invoiceItemRequest.getEffectiveDt()); 
    	invoiceItem.setRecSt(invoiceItemRequest.getRecSt()); 
		return invoiceItem;
	}
}
