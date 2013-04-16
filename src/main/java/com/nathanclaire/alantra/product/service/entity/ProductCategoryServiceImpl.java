/**
 * 
 */
package com.nathanclaire.alantra.product.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.product.model.ProductCategory;
import com.nathanclaire.alantra.product.request.ProductCategoryRequest;

import com.nathanclaire.alantra.product.model.ProductCategoryType;

/**
 * @author administrator
 *
 */
@Stateless
public class ProductCategoryServiceImpl extends BaseEntityServiceImpl<ProductCategory, ProductCategoryRequest> implements ProductCategoryService
{
	/**
	 * @param entityClass
	 */
	public ProductCategoryServiceImpl() {
		super(ProductCategory.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductCategory#findById(java.lang.Integer)
	 */
	@Override
	public ProductCategory findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductCategory#findByCode(java.lang.String)
	 */
	@Override
	public ProductCategory findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductCategory#findByName(java.lang.String)
	 */
	@Override
	public ProductCategory findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductCategory#findAll(java.util.Map)
	 */
	@Override
	public List<ProductCategory> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductCategory#createProductCategory(com.nathanclaire.alantra.product.rest.request.ServiceRequest)
	 */
	@Override
	public ProductCategory create(ProductCategoryRequest productCategoryRequest) {
		return createInstance(productCategoryRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductCategory#deleteProductCategory(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductCategory#updateProductCategory(com.nathanclaire.alantra.product.rest.request.ServiceRequest)
	 */
	@Override
	public ProductCategory update(ProductCategoryRequest productCategoryRequest) {
		return updateInstance(productCategoryRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected ProductCategory loadModelFromRequest(ProductCategoryRequest productCategoryRequest) 
    {
		ProductCategory productCategory = new ProductCategory();
    	Integer productCategoryId = productCategoryRequest.getId();
    	// Are we editing a ProductCategory
    	if(productCategoryId != null) 
    	{
    		productCategory = getEntityManager().find(ProductCategory.class, productCategoryRequest.getId());
    		productCategory.setLastModifiedDt(productCategoryRequest.getLastModifiedDt());
        	productCategory.setLastModifiedUsr(getCurrentUserName(productCategoryRequest));
    	}
    	else
    	{
        	productCategory.setCreatedDt(getCurrentSystemDate());
        	productCategory.setCreatedByUsr(getCurrentUserName(productCategoryRequest));
    	}
    	productCategory.setCode(productCategoryRequest.getCode());
    	productCategory.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (productCategoryRequest.getProductCategoryType() != null)
    	{
    		ProductCategoryType productCategoryType = getEntityManager().find(ProductCategoryType.class, productCategoryRequest.getProductCategoryType());
    		productCategory.setProductCategoryType(productCategoryType);
    	}
    	productCategory.setName(productCategoryRequest.getName()); 
    	productCategory.setDescription(productCategoryRequest.getDescription()); 
    	productCategory.setCode(productCategoryRequest.getCode()); 
    	productCategory.setEffectiveDt(productCategoryRequest.getEffectiveDt()); 
    	productCategory.setRecSt(productCategoryRequest.getRecSt()); 
		return productCategory;
	}
}
