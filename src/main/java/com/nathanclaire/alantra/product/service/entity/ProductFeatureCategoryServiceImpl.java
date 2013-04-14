/**
 * 
 */
package com.nathanclaire.alantra.product.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.product.model.ProductFeatureCategory;
import com.nathanclaire.alantra.product.rest.request.ProductFeatureCategoryRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class ProductFeatureCategoryServiceImpl extends BaseEntityServiceImpl<ProductFeatureCategory> implements ProductFeatureCategoryService
{
	/**
	 * @param entityClass
	 */
	public ProductFeatureCategoryServiceImpl() {
		super(ProductFeatureCategory.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeatureCategory#findById(java.lang.Integer)
	 */
	@Override
	public ProductFeatureCategory findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeatureCategory#findByCode(java.lang.String)
	 */
	@Override
	public ProductFeatureCategory findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeatureCategory#findByName(java.lang.String)
	 */
	@Override
	public ProductFeatureCategory findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeatureCategory#findAll(java.util.Map)
	 */
	@Override
	public List<ProductFeatureCategory> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeatureCategory#createProductFeatureCategory(com.nathanclaire.alantra.product.rest.request.ServiceRequest)
	 */
	@Override
	public ProductFeatureCategory createInstance(BaseRequest productFeatureCategoryRequest) {
		return createInsance(productFeatureCategoryRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeatureCategory#deleteProductFeatureCategory(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeatureCategory#updateProductFeatureCategory(com.nathanclaire.alantra.product.rest.request.ServiceRequest)
	 */
	@Override
	public ProductFeatureCategory updateInstance(BaseRequest productFeatureCategoryRequest) {
		return updateInstance(productFeatureCategoryRequest);
	}
	
	/**
     * @param request
     * @return
     */
    protected ProductFeatureCategory loadModelFromRequest(BaseRequest request) 
    {
    	ProductFeatureCategoryRequest productFeatureCategoryRequest = (ProductFeatureCategoryRequest) request;
		ProductFeatureCategory productFeatureCategory = new ProductFeatureCategory();
    	Integer productFeatureCategoryId = productFeatureCategoryRequest.getId();
    	// Are we editing a ProductFeatureCategory
    	if(productFeatureCategoryId != null) 
    	{
    		productFeatureCategory = getEntityManager().find(ProductFeatureCategory.class, productFeatureCategoryRequest.getId());
    		productFeatureCategory.setLastModifiedDt(productFeatureCategoryRequest.getLastModifiedDt());
        	productFeatureCategory.setLastModifiedUsr(getCurrentUserName(productFeatureCategoryRequest));
    	}
    	else
    	{
        	productFeatureCategory.setCreatedDt(getCurrentSystemDate());
        	productFeatureCategory.setCreatedByUsr(getCurrentUserName(productFeatureCategoryRequest));
    	}
    	productFeatureCategory.setCode(productFeatureCategoryRequest.getCode());
    	productFeatureCategory.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	productFeatureCategory.setName(productFeatureCategoryRequest.getName()); 
    	productFeatureCategory.setDescription(productFeatureCategoryRequest.getDescription()); 
    	productFeatureCategory.setCode(productFeatureCategoryRequest.getCode()); 
    	productFeatureCategory.setEffectiveDt(productFeatureCategoryRequest.getEffectiveDt()); 
    	productFeatureCategory.setRecSt(productFeatureCategoryRequest.getRecSt()); 
		return productFeatureCategory;
	}
}
