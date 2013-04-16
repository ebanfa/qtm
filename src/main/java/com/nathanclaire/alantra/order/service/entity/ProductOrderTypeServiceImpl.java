/**
 * 
 */
package com.nathanclaire.alantra.order.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.order.model.ProductOrderType;
import com.nathanclaire.alantra.order.request.ProductOrderTypeRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class ProductOrderTypeServiceImpl extends BaseEntityServiceImpl<ProductOrderType, ProductOrderTypeRequest> implements ProductOrderTypeService
{
	/**
	 * @param entityClass
	 */
	public ProductOrderTypeServiceImpl() {
		super(ProductOrderType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.order.service.ProductOrderType#findById(java.lang.Integer)
	 */
	@Override
	public ProductOrderType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.order.service.ProductOrderType#findByCode(java.lang.String)
	 */
	@Override
	public ProductOrderType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.order.service.ProductOrderType#findByName(java.lang.String)
	 */
	@Override
	public ProductOrderType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.order.service.ProductOrderType#findAll(java.util.Map)
	 */
	@Override
	public List<ProductOrderType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.order.service.ProductOrderType#createProductOrderType(com.nathanclaire.alantra.order.rest.request.ServiceRequest)
	 */
	@Override
	public ProductOrderType create(ProductOrderTypeRequest productOrderTypeRequest) {
		return createInstance(productOrderTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.order.service.ProductOrderType#deleteProductOrderType(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.order.service.ProductOrderType#updateProductOrderType(com.nathanclaire.alantra.order.rest.request.ServiceRequest)
	 */
	@Override
	public ProductOrderType update(ProductOrderTypeRequest productOrderTypeRequest) {
		return updateInstance(productOrderTypeRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected ProductOrderType loadModelFromRequest(ProductOrderTypeRequest productOrderTypeRequest) 
    {
		ProductOrderType productOrderType = new ProductOrderType();
    	Integer productOrderTypeId = productOrderTypeRequest.getId();
    	// Are we editing a ProductOrderType
    	if(productOrderTypeId != null) 
    	{
    		productOrderType = getEntityManager().find(ProductOrderType.class, productOrderTypeRequest.getId());
    		productOrderType.setLastModifiedDt(productOrderTypeRequest.getLastModifiedDt());
        	productOrderType.setLastModifiedUsr(getCurrentUserName(productOrderTypeRequest));
    	}
    	else
    	{
        	productOrderType.setCreatedDt(getCurrentSystemDate());
        	productOrderType.setCreatedByUsr(getCurrentUserName(productOrderTypeRequest));
    	}
    	productOrderType.setCode(productOrderTypeRequest.getCode());
    	productOrderType.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	productOrderType.setName(productOrderTypeRequest.getName()); 
    	productOrderType.setDescription(productOrderTypeRequest.getDescription()); 
    	productOrderType.setCode(productOrderTypeRequest.getCode()); 
    	productOrderType.setEffectiveDt(productOrderTypeRequest.getEffectiveDt()); 
    	productOrderType.setRecSt(productOrderTypeRequest.getRecSt()); 
		return productOrderType;
	}
}
