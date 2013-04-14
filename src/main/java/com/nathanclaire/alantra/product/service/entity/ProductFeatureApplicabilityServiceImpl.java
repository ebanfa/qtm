/**
 * 
 */
package com.nathanclaire.alantra.product.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.product.model.ProductFeatureApplicability;
import com.nathanclaire.alantra.product.rest.request.ProductFeatureApplicabilityRequest;

import com.nathanclaire.alantra.product.model.Product;
import com.nathanclaire.alantra.product.rest.request.ProductRequest;
import com.nathanclaire.alantra.product.model.ProductFeature;
import com.nathanclaire.alantra.product.rest.request.ProductFeatureRequest;
import com.nathanclaire.alantra.product.model.ProductFeatureApplicabilityType;
import com.nathanclaire.alantra.product.rest.request.ProductFeatureApplicabilityTypeRequest;

/**
 * @author administrator
 *
 */
@Stateless
public class ProductFeatureApplicabilityServiceImpl extends BaseEntityServiceImpl<ProductFeatureApplicability> implements ProductFeatureApplicabilityService
{
	/**
	 * @param entityClass
	 */
	public ProductFeatureApplicabilityServiceImpl() {
		super(ProductFeatureApplicability.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeatureApplicability#findById(java.lang.Integer)
	 */
	@Override
	public ProductFeatureApplicability findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeatureApplicability#findByCode(java.lang.String)
	 */
	@Override
	public ProductFeatureApplicability findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeatureApplicability#findByName(java.lang.String)
	 */
	@Override
	public ProductFeatureApplicability findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeatureApplicability#findAll(java.util.Map)
	 */
	@Override
	public List<ProductFeatureApplicability> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeatureApplicability#createProductFeatureApplicability(com.nathanclaire.alantra.product.rest.request.ServiceRequest)
	 */
	@Override
	public ProductFeatureApplicability createInstance(BaseRequest productFeatureApplicabilityRequest) {
		return createInsance(productFeatureApplicabilityRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeatureApplicability#deleteProductFeatureApplicability(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeatureApplicability#updateProductFeatureApplicability(com.nathanclaire.alantra.product.rest.request.ServiceRequest)
	 */
	@Override
	public ProductFeatureApplicability updateInstance(BaseRequest productFeatureApplicabilityRequest) {
		return updateInstance(productFeatureApplicabilityRequest);
	}
	
	/**
     * @param request
     * @return
     */
    protected ProductFeatureApplicability loadModelFromRequest(BaseRequest request) 
    {
    	ProductFeatureApplicabilityRequest productFeatureApplicabilityRequest = (ProductFeatureApplicabilityRequest) request;
		ProductFeatureApplicability productFeatureApplicability = new ProductFeatureApplicability();
    	Integer productFeatureApplicabilityId = productFeatureApplicabilityRequest.getId();
    	// Are we editing a ProductFeatureApplicability
    	if(productFeatureApplicabilityId != null) 
    	{
    		productFeatureApplicability = getEntityManager().find(ProductFeatureApplicability.class, productFeatureApplicabilityRequest.getId());
    		productFeatureApplicability.setLastModifiedDt(productFeatureApplicabilityRequest.getLastModifiedDt());
        	productFeatureApplicability.setLastModifiedUsr(getCurrentUserName(productFeatureApplicabilityRequest));
    	}
    	else
    	{
        	productFeatureApplicability.setCreatedDt(getCurrentSystemDate());
        	productFeatureApplicability.setCreatedByUsr(getCurrentUserName(productFeatureApplicabilityRequest));
    	}
    	productFeatureApplicability.setCode(productFeatureApplicabilityRequest.getCode());
    	productFeatureApplicability.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (productFeatureApplicabilityRequest.getProduct() != null)
    	{
    		Product product = getEntityManager().find(Product.class, productFeatureApplicabilityRequest.getProduct());
    		productFeatureApplicability.setProduct(product);
    	}
    	if (productFeatureApplicabilityRequest.getProductFeature() != null)
    	{
    		ProductFeature productFeature = getEntityManager().find(ProductFeature.class, productFeatureApplicabilityRequest.getProductFeature());
    		productFeatureApplicability.setProductFeature(productFeature);
    	}
    	if (productFeatureApplicabilityRequest.getProductFeatureApplicabilityType() != null)
    	{
    		ProductFeatureApplicabilityType productFeatureApplicabilityType = getEntityManager().find(ProductFeatureApplicabilityType.class, productFeatureApplicabilityRequest.getProductFeatureApplicabilityType());
    		productFeatureApplicability.setProductFeatureApplicabilityType(productFeatureApplicabilityType);
    	}
    	productFeatureApplicability.setName(productFeatureApplicabilityRequest.getName()); 
    	productFeatureApplicability.setDescription(productFeatureApplicabilityRequest.getDescription()); 
    	productFeatureApplicability.setFromDt(productFeatureApplicabilityRequest.getFromDt()); 
    	productFeatureApplicability.setToDt(productFeatureApplicabilityRequest.getToDt()); 
    	productFeatureApplicability.setCode(productFeatureApplicabilityRequest.getCode()); 
    	productFeatureApplicability.setEffectiveDt(productFeatureApplicabilityRequest.getEffectiveDt()); 
    	productFeatureApplicability.setRecSt(productFeatureApplicabilityRequest.getRecSt()); 
		return productFeatureApplicability;
	}
}
