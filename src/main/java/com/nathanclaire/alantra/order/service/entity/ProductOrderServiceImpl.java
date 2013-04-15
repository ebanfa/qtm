/**
 * 
 */
package com.nathanclaire.alantra.order.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.order.model.ProductOrder;
import com.nathanclaire.alantra.order.rest.request.ProductOrderRequest;

import com.nathanclaire.alantra.order.model.ProductOrderType;
import com.nathanclaire.alantra.party.model.Party;
import com.nathanclaire.alantra.party.model.Party;

/**
 * @author administrator
 *
 */
@Stateless
public class ProductOrderServiceImpl extends BaseEntityServiceImpl<ProductOrder, ProductOrderRequest> implements ProductOrderService
{
	/**
	 * @param entityClass
	 */
	public ProductOrderServiceImpl() {
		super(ProductOrder.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.order.service.ProductOrder#findById(java.lang.Integer)
	 */
	@Override
	public ProductOrder findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.order.service.ProductOrder#findByCode(java.lang.String)
	 */
	@Override
	public ProductOrder findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.order.service.ProductOrder#findByName(java.lang.String)
	 */
	@Override
	public ProductOrder findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.order.service.ProductOrder#findAll(java.util.Map)
	 */
	@Override
	public List<ProductOrder> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.order.service.ProductOrder#createProductOrder(com.nathanclaire.alantra.order.rest.request.ServiceRequest)
	 */
	@Override
	public ProductOrder createInstance(ProductOrderRequest productOrderRequest) {
		return createInsance(productOrderRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.order.service.ProductOrder#deleteProductOrder(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.order.service.ProductOrder#updateProductOrder(com.nathanclaire.alantra.order.rest.request.ServiceRequest)
	 */
	@Override
	public ProductOrder updateInstance(ProductOrderRequest productOrderRequest) {
		return updateInstance(productOrderRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected ProductOrder loadModelFromRequest(ProductOrderRequest productOrderRequest) 
    {
		ProductOrder productOrder = new ProductOrder();
    	Integer productOrderId = productOrderRequest.getId();
    	// Are we editing a ProductOrder
    	if(productOrderId != null) 
    	{
    		productOrder = getEntityManager().find(ProductOrder.class, productOrderRequest.getId());
    		productOrder.setLastModifiedDt(productOrderRequest.getLastModifiedDt());
        	productOrder.setLastModifiedUsr(getCurrentUserName(productOrderRequest));
    	}
    	else
    	{
        	productOrder.setCreatedDt(getCurrentSystemDate());
        	productOrder.setCreatedByUsr(getCurrentUserName(productOrderRequest));
    	}
    	productOrder.setCode(productOrderRequest.getCode());
    	productOrder.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (productOrderRequest.getProductOrderType() != null)
    	{
    		ProductOrderType productOrderType = getEntityManager().find(ProductOrderType.class, productOrderRequest.getProductOrderType());
    		productOrder.setProductOrderType(productOrderType);
    	}
    	productOrder.setName(productOrderRequest.getName()); 
    	productOrder.setDescription(productOrderRequest.getDescription()); 
    	productOrder.setOrderDt(productOrderRequest.getOrderDt()); 
    	if (productOrderRequest.getFromParty() != null)
    	{
    		Party fromParty = getEntityManager().find(Party.class, productOrderRequest.getFromParty());
    		productOrder.setFromParty(fromParty);
    	}
    	if (productOrderRequest.getToParty() != null)
    	{
    		Party toParty = getEntityManager().find(Party.class, productOrderRequest.getToParty());
    		productOrder.setToParty(toParty);
    	}
    	productOrder.setAmount(productOrderRequest.getAmount()); 
    	productOrder.setCode(productOrderRequest.getCode()); 
    	productOrder.setEffectiveDt(productOrderRequest.getEffectiveDt()); 
    	productOrder.setRecSt(productOrderRequest.getRecSt()); 
		return productOrder;
	}
}
