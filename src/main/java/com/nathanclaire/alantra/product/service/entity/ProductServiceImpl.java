/**
 * 
 */
package com.nathanclaire.alantra.product.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.product.model.Product;
import com.nathanclaire.alantra.product.rest.request.ProductRequest;

import com.nathanclaire.alantra.product.model.ProductType;

/**
 * @author administrator
 *
 */
@Stateless
public class ProductServiceImpl extends BaseEntityServiceImpl<Product, ProductRequest> implements ProductService
{
	/**
	 * @param entityClass
	 */
	public ProductServiceImpl() {
		super(Product.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.Product#findById(java.lang.Integer)
	 */
	@Override
	public Product findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.Product#findByCode(java.lang.String)
	 */
	@Override
	public Product findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.Product#findByName(java.lang.String)
	 */
	@Override
	public Product findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.Product#findAll(java.util.Map)
	 */
	@Override
	public List<Product> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.Product#createProduct(com.nathanclaire.alantra.product.rest.request.ServiceRequest)
	 */
	@Override
	public Product createInstance(ProductRequest productRequest) {
		return createInsance(productRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.Product#deleteProduct(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.Product#updateProduct(com.nathanclaire.alantra.product.rest.request.ServiceRequest)
	 */
	@Override
	public Product updateInstance(ProductRequest productRequest) {
		return updateInstance(productRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected Product loadModelFromRequest(ProductRequest productRequest) 
    {
		Product product = new Product();
    	Integer productId = productRequest.getId();
    	// Are we editing a Product
    	if(productId != null) 
    	{
    		product = getEntityManager().find(Product.class, productRequest.getId());
    		product.setLastModifiedDt(productRequest.getLastModifiedDt());
        	product.setLastModifiedUsr(getCurrentUserName(productRequest));
    	}
    	else
    	{
        	product.setCreatedDt(getCurrentSystemDate());
        	product.setCreatedByUsr(getCurrentUserName(productRequest));
    	}
    	product.setCode(productRequest.getCode());
    	product.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (productRequest.getProductType() != null)
    	{
    		ProductType productType = getEntityManager().find(ProductType.class, productRequest.getProductType());
    		product.setProductType(productType);
    	}
    	product.setName(productRequest.getName()); 
    	product.setDescription(productRequest.getDescription()); 
    	product.setIntroductionDt(productRequest.getIntroductionDt()); 
    	product.setDiscountinuedDt(productRequest.getDiscountinuedDt()); 
    	product.setCode(productRequest.getCode()); 
    	product.setEffectiveDt(productRequest.getEffectiveDt()); 
    	product.setRecSt(productRequest.getRecSt()); 
		return product;
	}
}
