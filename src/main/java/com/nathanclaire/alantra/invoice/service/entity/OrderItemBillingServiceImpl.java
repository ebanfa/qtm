/**
 * 
 */
package com.nathanclaire.alantra.invoice.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.invoice.model.OrderItemBilling;
import com.nathanclaire.alantra.invoice.rest.request.OrderItemBillingRequest;

import com.nathanclaire.alantra.invoice.model.InvoiceItem;
import com.nathanclaire.alantra.order.model.ProductOrderItem;

/**
 * @author administrator
 *
 */
@Stateless
public class OrderItemBillingServiceImpl extends BaseEntityServiceImpl<OrderItemBilling, OrderItemBillingRequest> implements OrderItemBillingService
{
	/**
	 * @param entityClass
	 */
	public OrderItemBillingServiceImpl() {
		super(OrderItemBilling.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.OrderItemBilling#findById(java.lang.Integer)
	 */
	@Override
	public OrderItemBilling findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.OrderItemBilling#findByCode(java.lang.String)
	 */
	@Override
	public OrderItemBilling findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.OrderItemBilling#findByName(java.lang.String)
	 */
	@Override
	public OrderItemBilling findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.OrderItemBilling#findAll(java.util.Map)
	 */
	@Override
	public List<OrderItemBilling> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.OrderItemBilling#createOrderItemBilling(com.nathanclaire.alantra.invoice.rest.request.ServiceRequest)
	 */
	@Override
	public OrderItemBilling createInstance(OrderItemBillingRequest orderItemBillingRequest) {
		return createInsance(orderItemBillingRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.OrderItemBilling#deleteOrderItemBilling(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.invoice.service.OrderItemBilling#updateOrderItemBilling(com.nathanclaire.alantra.invoice.rest.request.ServiceRequest)
	 */
	@Override
	public OrderItemBilling updateInstance(OrderItemBillingRequest orderItemBillingRequest) {
		return updateInstance(orderItemBillingRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected OrderItemBilling loadModelFromRequest(OrderItemBillingRequest orderItemBillingRequest) 
    {
		OrderItemBilling orderItemBilling = new OrderItemBilling();
    	Integer orderItemBillingId = orderItemBillingRequest.getId();
    	// Are we editing a OrderItemBilling
    	if(orderItemBillingId != null) 
    	{
    		orderItemBilling = getEntityManager().find(OrderItemBilling.class, orderItemBillingRequest.getId());
    		orderItemBilling.setLastModifiedDt(orderItemBillingRequest.getLastModifiedDt());
        	orderItemBilling.setLastModifiedUsr(getCurrentUserName(orderItemBillingRequest));
    	}
    	else
    	{
        	orderItemBilling.setCreatedDt(getCurrentSystemDate());
        	orderItemBilling.setCreatedByUsr(getCurrentUserName(orderItemBillingRequest));
    	}
    	orderItemBilling.setCode(orderItemBillingRequest.getCode());
    	orderItemBilling.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (orderItemBillingRequest.getInvoiceItem() != null)
    	{
    		InvoiceItem invoiceItem = getEntityManager().find(InvoiceItem.class, orderItemBillingRequest.getInvoiceItem());
    		orderItemBilling.setInvoiceItem(invoiceItem);
    	}
    	if (orderItemBillingRequest.getProductOrderItem() != null)
    	{
    		ProductOrderItem productOrderItem = getEntityManager().find(ProductOrderItem.class, orderItemBillingRequest.getProductOrderItem());
    		orderItemBilling.setProductOrderItem(productOrderItem);
    	}
    	orderItemBilling.setQuantity(orderItemBillingRequest.getQuantity()); 
    	orderItemBilling.setAmount(orderItemBillingRequest.getAmount()); 
    	orderItemBilling.setDescription(orderItemBillingRequest.getDescription()); 
    	orderItemBilling.setCode(orderItemBillingRequest.getCode()); 
    	orderItemBilling.setEffectiveDt(orderItemBillingRequest.getEffectiveDt()); 
    	orderItemBilling.setRecSt(orderItemBillingRequest.getRecSt()); 
		return orderItemBilling;
	}
}
