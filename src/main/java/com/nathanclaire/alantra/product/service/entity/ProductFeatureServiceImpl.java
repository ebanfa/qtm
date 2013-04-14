/**
 * 
 */
package com.nathanclaire.alantra.product.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.product.model.ProductFeature;
import com.nathanclaire.alantra.product.rest.request.ProductFeatureRequest;

import com.nathanclaire.alantra.product.model.ProductFeatureType;
import com.nathanclaire.alantra.product.rest.request.ProductFeatureTypeRequest;
import com.nathanclaire.alantra.product.model.ProductFeatureCategory;
import com.nathanclaire.alantra.product.rest.request.ProductFeatureCategoryRequest;

/**
 * @author administrator
 *
 */
@Stateless
public class ProductFeatureServiceImpl extends BaseEntityServiceImpl<ProductFeature> implements ProductFeatureService
{
	/**
	 * @param entityClass
	 */
	public ProductFeatureServiceImpl() {
		super(ProductFeature.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeature#findById(java.lang.Integer)
	 */
	@Override
	public ProductFeature findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeature#findByCode(java.lang.String)
	 */
	@Override
	public ProductFeature findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeature#findByName(java.lang.String)
	 */
	@Override
	public ProductFeature findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeature#findAll(java.util.Map)
	 */
	@Override
	public List<ProductFeature> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeature#createProductFeature(com.nathanclaire.alantra.product.rest.request.ServiceRequest)
	 */
	@Override
	public ProductFeature createInstance(BaseRequest productFeatureRequest) {
		return createInsance(productFeatureRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeature#deleteProductFeature(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeature#updateProductFeature(com.nathanclaire.alantra.product.rest.request.ServiceRequest)
	 */
	@Override
	public ProductFeature updateInstance(BaseRequest productFeatureRequest) {
		return updateInstance(productFeatureRequest);
	}
	
	/**
     * @param request
     * @return
     */
    protected ProductFeature loadModelFromRequest(BaseRequest request) 
    {
    	ProductFeatureRequest productFeatureRequest = (ProductFeatureRequest) request;
		ProductFeature productFeature = new ProductFeature();
    	Integer productFeatureId = productFeatureRequest.getId();
    	// Are we editing a ProductFeature
    	if(productFeatureId != null) 
    	{
    		productFeature = getEntityManager().find(ProductFeature.class, productFeatureRequest.getId());
    		productFeature.setLastModifiedDt(productFeatureRequest.getLastModifiedDt());
        	productFeature.setLastModifiedUsr(getCurrentUserName(productFeatureRequest));
    	}
    	else
    	{
        	productFeature.setCreatedDt(getCurrentSystemDate());
        	productFeature.setCreatedByUsr(getCurrentUserName(productFeatureRequest));
    	}
    	productFeature.setCode(productFeatureRequest.getCode());
    	productFeature.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (productFeatureRequest.getProductFeatureType() != null)
    	{
    		ProductFeatureType productFeatureType = getEntityManager().find(ProductFeatureType.class, productFeatureRequest.getProductFeatureType());
    		productFeature.setProductFeatureType(productFeatureType);
    	}
    	if (productFeatureRequest.getProductFeatureCategory() != null)
    	{
    		ProductFeatureCategory productFeatureCategory = getEntityManager().find(ProductFeatureCategory.class, productFeatureRequest.getProductFeatureCategory());
    		productFeature.setProductFeatureCategory(productFeatureCategory);
    	}
    	productFeature.setName(productFeatureRequest.getName()); 
    	productFeature.setDescription(productFeatureRequest.getDescription()); 
    	productFeature.setCode(productFeatureRequest.getCode()); 
    	productFeature.setEffectiveDt(productFeatureRequest.getEffectiveDt()); 
    	productFeature.setRecSt(productFeatureRequest.getRecSt()); 
		return productFeature;
	}
}
