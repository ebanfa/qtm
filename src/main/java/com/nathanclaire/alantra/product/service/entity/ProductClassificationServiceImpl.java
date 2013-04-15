/**
 * 
 */
package com.nathanclaire.alantra.product.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.product.model.ProductClassification;
import com.nathanclaire.alantra.product.rest.request.ProductClassificationRequest;

import com.nathanclaire.alantra.product.model.ProductCategory;
import com.nathanclaire.alantra.product.model.Product;

/**
 * @author administrator
 *
 */
@Stateless
public class ProductClassificationServiceImpl extends BaseEntityServiceImpl<ProductClassification, ProductClassificationRequest> implements ProductClassificationService
{
	/**
	 * @param entityClass
	 */
	public ProductClassificationServiceImpl() {
		super(ProductClassification.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductClassification#findById(java.lang.Integer)
	 */
	@Override
	public ProductClassification findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductClassification#findByCode(java.lang.String)
	 */
	@Override
	public ProductClassification findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductClassification#findByName(java.lang.String)
	 */
	@Override
	public ProductClassification findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductClassification#findAll(java.util.Map)
	 */
	@Override
	public List<ProductClassification> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductClassification#createProductClassification(com.nathanclaire.alantra.product.rest.request.ServiceRequest)
	 */
	@Override
	public ProductClassification createInstance(ProductClassificationRequest productClassificationRequest) {
		return createInsance(productClassificationRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductClassification#deleteProductClassification(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductClassification#updateProductClassification(com.nathanclaire.alantra.product.rest.request.ServiceRequest)
	 */
	@Override
	public ProductClassification updateInstance(ProductClassificationRequest productClassificationRequest) {
		return updateInstance(productClassificationRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected ProductClassification loadModelFromRequest(ProductClassificationRequest productClassificationRequest) 
    {
		ProductClassification productClassification = new ProductClassification();
    	Integer productClassificationId = productClassificationRequest.getId();
    	// Are we editing a ProductClassification
    	if(productClassificationId != null) 
    	{
    		productClassification = getEntityManager().find(ProductClassification.class, productClassificationRequest.getId());
    		productClassification.setLastModifiedDt(productClassificationRequest.getLastModifiedDt());
        	productClassification.setLastModifiedUsr(getCurrentUserName(productClassificationRequest));
    	}
    	else
    	{
        	productClassification.setCreatedDt(getCurrentSystemDate());
        	productClassification.setCreatedByUsr(getCurrentUserName(productClassificationRequest));
    	}
    	productClassification.setCode(productClassificationRequest.getCode());
    	productClassification.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (productClassificationRequest.getProductCategory() != null)
    	{
    		ProductCategory productCategory = getEntityManager().find(ProductCategory.class, productClassificationRequest.getProductCategory());
    		productClassification.setProductCategory(productCategory);
    	}
    	if (productClassificationRequest.getProduct() != null)
    	{
    		Product product = getEntityManager().find(Product.class, productClassificationRequest.getProduct());
    		productClassification.setProduct(product);
    	}
    	productClassification.setName(productClassificationRequest.getName()); 
    	productClassification.setDescription(productClassificationRequest.getDescription()); 
    	productClassification.setRemarks(productClassificationRequest.getRemarks()); 
    	productClassification.setFromDt(productClassificationRequest.getFromDt()); 
    	productClassification.setToDt(productClassificationRequest.getToDt()); 
    	productClassification.setPrimaryFg(productClassificationRequest.getPrimaryFg()); 
    	productClassification.setCode(productClassificationRequest.getCode()); 
    	productClassification.setEffectiveDt(productClassificationRequest.getEffectiveDt()); 
    	productClassification.setRecSt(productClassificationRequest.getRecSt()); 
		return productClassification;
	}
}
