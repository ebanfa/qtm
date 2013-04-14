/**
 * 
 */
package com.nathanclaire.alantra.product.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.product.model.ProductComponent;
import com.nathanclaire.alantra.product.rest.request.ProductComponentRequest;

import com.nathanclaire.alantra.product.model.Product;
import com.nathanclaire.alantra.product.rest.request.ProductRequest;
import com.nathanclaire.alantra.product.model.Product;
import com.nathanclaire.alantra.product.rest.request.ProductRequest;

/**
 * @author administrator
 *
 */
@Stateless
public class ProductComponentServiceImpl extends BaseEntityServiceImpl<ProductComponent> implements ProductComponentService
{
	/**
	 * @param entityClass
	 */
	public ProductComponentServiceImpl() {
		super(ProductComponent.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductComponent#findById(java.lang.Integer)
	 */
	@Override
	public ProductComponent findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductComponent#findByCode(java.lang.String)
	 */
	@Override
	public ProductComponent findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductComponent#findByName(java.lang.String)
	 */
	@Override
	public ProductComponent findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductComponent#findAll(java.util.Map)
	 */
	@Override
	public List<ProductComponent> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductComponent#createProductComponent(com.nathanclaire.alantra.product.rest.request.ServiceRequest)
	 */
	@Override
	public ProductComponent createInstance(BaseRequest productComponentRequest) {
		return createInsance(productComponentRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductComponent#deleteProductComponent(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductComponent#updateProductComponent(com.nathanclaire.alantra.product.rest.request.ServiceRequest)
	 */
	@Override
	public ProductComponent updateInstance(BaseRequest productComponentRequest) {
		return updateInstance(productComponentRequest);
	}
	
	/**
     * @param request
     * @return
     */
    protected ProductComponent loadModelFromRequest(BaseRequest request) 
    {
    	ProductComponentRequest productComponentRequest = (ProductComponentRequest) request;
		ProductComponent productComponent = new ProductComponent();
    	Integer productComponentId = productComponentRequest.getId();
    	// Are we editing a ProductComponent
    	if(productComponentId != null) 
    	{
    		productComponent = getEntityManager().find(ProductComponent.class, productComponentRequest.getId());
    		productComponent.setLastModifiedDt(productComponentRequest.getLastModifiedDt());
        	productComponent.setLastModifiedUsr(getCurrentUserName(productComponentRequest));
    	}
    	else
    	{
        	productComponent.setCreatedDt(getCurrentSystemDate());
        	productComponent.setCreatedByUsr(getCurrentUserName(productComponentRequest));
    	}
    	productComponent.setCode(productComponentRequest.getCode());
    	productComponent.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (productComponentRequest.getProductByInProdId() != null)
    	{
    		Product productByInProdId = getEntityManager().find(Product.class, productComponentRequest.getProductByInProdId());
    		productComponent.setProductByInProdId(productByInProdId);
    	}
    	if (productComponentRequest.getProductByForProdId() != null)
    	{
    		Product productByForProdId = getEntityManager().find(Product.class, productComponentRequest.getProductByForProdId());
    		productComponent.setProductByForProdId(productByForProdId);
    	}
    	productComponent.setLocationId(productComponentRequest.getLocationId()); 
    	productComponent.setDescription(productComponentRequest.getDescription()); 
    	productComponent.setInstruction(productComponentRequest.getInstruction()); 
    	productComponent.setRemarks(productComponentRequest.getRemarks()); 
    	productComponent.setQuantitiyUsed(productComponentRequest.getQuantitiyUsed()); 
    	productComponent.setFromDt(productComponentRequest.getFromDt()); 
    	productComponent.setToDt(productComponentRequest.getToDt()); 
    	productComponent.setCode(productComponentRequest.getCode()); 
    	productComponent.setEffectiveDt(productComponentRequest.getEffectiveDt()); 
    	productComponent.setRecSt(productComponentRequest.getRecSt()); 
		return productComponent;
	}
}
