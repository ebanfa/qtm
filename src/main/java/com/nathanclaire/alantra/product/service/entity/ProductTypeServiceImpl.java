/**
 * 
 */
package com.nathanclaire.alantra.product.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.product.model.ProductType;
import com.nathanclaire.alantra.product.request.ProductTypeRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class ProductTypeServiceImpl extends BaseEntityServiceImpl<ProductType, ProductTypeRequest> implements ProductTypeService
{
	/**
	 * @param entityClass
	 */
	public ProductTypeServiceImpl() {
		super(ProductType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductType#findById(java.lang.Integer)
	 */
	@Override
	public ProductType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductType#findByCode(java.lang.String)
	 */
	@Override
	public ProductType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductType#findByName(java.lang.String)
	 */
	@Override
	public ProductType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductType#findAll(java.util.Map)
	 */
	@Override
	public List<ProductType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductType#createProductType(com.nathanclaire.alantra.product.rest.request.ServiceRequest)
	 */
	@Override
	public ProductType create(ProductTypeRequest productTypeRequest) {
		return createInstance(productTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductType#deleteProductType(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductType#updateProductType(com.nathanclaire.alantra.product.rest.request.ServiceRequest)
	 */
	@Override
	public ProductType update(ProductTypeRequest productTypeRequest) {
		return updateInstance(productTypeRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected ProductType loadModelFromRequest(ProductTypeRequest productTypeRequest) 
    {
		ProductType productType = new ProductType();
    	Integer productTypeId = productTypeRequest.getId();
    	// Are we editing a ProductType
    	if(productTypeId != null) 
    	{
    		productType = getEntityManager().find(ProductType.class, productTypeRequest.getId());
    		productType.setLastModifiedDt(productTypeRequest.getLastModifiedDt());
        	productType.setLastModifiedUsr(getCurrentUserName(productTypeRequest));
    	}
    	else
    	{
        	productType.setCreatedDt(getCurrentSystemDate());
        	productType.setCreatedByUsr(getCurrentUserName(productTypeRequest));
    	}
    	productType.setCode(productTypeRequest.getCode());
    	productType.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	productType.setNames(productTypeRequest.getNames()); 
    	productType.setDescription(productTypeRequest.getDescription()); 
    	productType.setCode(productTypeRequest.getCode()); 
    	productType.setEffectiveDt(productTypeRequest.getEffectiveDt()); 
    	productType.setRecSt(productTypeRequest.getRecSt()); 
		return productType;
	}
}
