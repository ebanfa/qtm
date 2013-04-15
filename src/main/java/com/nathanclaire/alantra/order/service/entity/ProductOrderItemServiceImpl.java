/**
 * 
 */
package com.nathanclaire.alantra.order.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.order.model.ProductOrderItem;
import com.nathanclaire.alantra.order.rest.request.ProductOrderItemRequest;

import com.nathanclaire.alantra.order.model.ProductOrderItemType;
import com.nathanclaire.alantra.order.model.ProductOrder;
import com.nathanclaire.alantra.product.model.Product;
import com.nathanclaire.alantra.product.model.ProductFeature;

/**
 * @author administrator
 *
 */
@Stateless
public class ProductOrderItemServiceImpl extends BaseEntityServiceImpl<ProductOrderItem, ProductOrderItemRequest> implements ProductOrderItemService
{
	/**
	 * @param entityClass
	 */
	public ProductOrderItemServiceImpl() {
		super(ProductOrderItem.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.order.service.ProductOrderItem#findById(java.lang.Integer)
	 */
	@Override
	public ProductOrderItem findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.order.service.ProductOrderItem#findByCode(java.lang.String)
	 */
	@Override
	public ProductOrderItem findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.order.service.ProductOrderItem#findByName(java.lang.String)
	 */
	@Override
	public ProductOrderItem findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.order.service.ProductOrderItem#findAll(java.util.Map)
	 */
	@Override
	public List<ProductOrderItem> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.order.service.ProductOrderItem#createProductOrderItem(com.nathanclaire.alantra.order.rest.request.ServiceRequest)
	 */
	@Override
	public ProductOrderItem createInstance(ProductOrderItemRequest productOrderItemRequest) {
		return createInsance(productOrderItemRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.order.service.ProductOrderItem#deleteProductOrderItem(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.order.service.ProductOrderItem#updateProductOrderItem(com.nathanclaire.alantra.order.rest.request.ServiceRequest)
	 */
	@Override
	public ProductOrderItem updateInstance(ProductOrderItemRequest productOrderItemRequest) {
		return updateInstance(productOrderItemRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected ProductOrderItem loadModelFromRequest(ProductOrderItemRequest productOrderItemRequest) 
    {
		ProductOrderItem productOrderItem = new ProductOrderItem();
    	Integer productOrderItemId = productOrderItemRequest.getId();
    	// Are we editing a ProductOrderItem
    	if(productOrderItemId != null) 
    	{
    		productOrderItem = getEntityManager().find(ProductOrderItem.class, productOrderItemRequest.getId());
    		productOrderItem.setLastModifiedDt(productOrderItemRequest.getLastModifiedDt());
        	productOrderItem.setLastModifiedUsr(getCurrentUserName(productOrderItemRequest));
    	}
    	else
    	{
        	productOrderItem.setCreatedDt(getCurrentSystemDate());
        	productOrderItem.setCreatedByUsr(getCurrentUserName(productOrderItemRequest));
    	}
    	productOrderItem.setCode(productOrderItemRequest.getCode());
    	productOrderItem.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (productOrderItemRequest.getProductOrderItemType() != null)
    	{
    		ProductOrderItemType productOrderItemType = getEntityManager().find(ProductOrderItemType.class, productOrderItemRequest.getProductOrderItemType());
    		productOrderItem.setProductOrderItemType(productOrderItemType);
    	}
    	if (productOrderItemRequest.getProductOrder() != null)
    	{
    		ProductOrder productOrder = getEntityManager().find(ProductOrder.class, productOrderItemRequest.getProductOrder());
    		productOrderItem.setProductOrder(productOrder);
    	}
    	if (productOrderItemRequest.getProduct() != null)
    	{
    		Product product = getEntityManager().find(Product.class, productOrderItemRequest.getProduct());
    		productOrderItem.setProduct(product);
    	}
    	if (productOrderItemRequest.getProductFeature() != null)
    	{
    		ProductFeature productFeature = getEntityManager().find(ProductFeature.class, productOrderItemRequest.getProductFeature());
    		productOrderItem.setProductFeature(productFeature);
    	}
    	productOrderItem.setName(productOrderItemRequest.getName()); 
    	productOrderItem.setDescription(productOrderItemRequest.getDescription()); 
    	productOrderItem.setQuantity(productOrderItemRequest.getQuantity()); 
    	productOrderItem.setUnitPrice(productOrderItemRequest.getUnitPrice()); 
    	productOrderItem.setExpectDeliveryDt(productOrderItemRequest.getExpectDeliveryDt()); 
    	productOrderItem.setInstructions(productOrderItemRequest.getInstructions()); 
    	productOrderItem.setRemarks(productOrderItemRequest.getRemarks()); 
    	productOrderItem.setCode(productOrderItemRequest.getCode()); 
    	productOrderItem.setEffectiveDt(productOrderItemRequest.getEffectiveDt()); 
    	productOrderItem.setRecSt(productOrderItemRequest.getRecSt()); 
		return productOrderItem;
	}
}
