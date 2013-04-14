/**
 * 
 */
package com.nathanclaire.alantra.product.rest;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.nathanclaire.alantra.base.rest.BaseEntityRESTService;
import com.nathanclaire.alantra.product.model.Product;
import com.nathanclaire.alantra.product.model.ProductComponent;
import com.nathanclaire.alantra.product.rest.request.ProductComponentRequest;
import com.nathanclaire.alantra.product.rest.request.ProductRequest;

/**
 * @author administrator
 *
 */
@Path("/productcomponent")
@Stateless
public class ProductComponentService extends BaseEntityRESTService<ProductComponent> 
{
	/**
	 * @param entityClass
	 */
	public ProductComponentService() {
		super(ProductComponent.class);
	}

    /**
     * <p>
     *     Subclasses may choose to expand the set of supported query parameters (for adding more filtering
     *     criteria) by overriding this method.
     * </p>
     * @param queryParameters - the HTTP query parameters received by the endpoint
     * @param criteriaBuilder - @{link CriteriaBuilder} used by the invoker
     * @param root  @{link Root} used by the invoker
     * @return a list of {@link Predicate}s that will added as query parameters
     */
    protected Predicate[] extractPredicates(MultivaluedMap<String, String> queryParameters,
            CriteriaBuilder criteriaBuilder, Root<ProductComponent> root) 
    {
    	List<Predicate> predicates = new ArrayList<Predicate>() ;
        if (queryParameters.containsKey(CODE_CRITERIA)) {
             String code = queryParameters.getFirst(CODE_CRITERIA) + "%";
            //predicates.add(criteriaBuilder.equal(root.get(CODE_CRITERIA), code));
            predicates.add(criteriaBuilder.like(root.<String>get(CODE_CRITERIA), code));
        }
        return predicates.toArray(new Predicate[]{});
	}
    
    /**
     * <p>
     *   Create a ProductComponent. Data is contained in the ProductComponentRequest object
     * </p>
     * @param ProductComponentRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProductComponent(ProductComponentRequest request) {
        try 
        {
        	ProductComponent productComponent = this.loadModelFromRequest(request);
        	entityManager.persist(productComponent);
            return null;
        } 
        catch (ConstraintViolationException e) 
        {
            // A WebApplicationException can wrap a response
            // Throwing the exception causes an automatic rollback
            throw new WebApplicationException(e);
        } catch (Exception e) {
            // Finally, handle 
            throw new WebApplicationException(e);
        }
    }
    
    /**
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    @PUT 
    @Path("/{id:[0-9][0-9]*}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editProductComponent(ProductComponentRequest request) 
    {
    	try 
        {
    		this.loadModelFromRequest(request);
            return null;
        } 
        catch (ConstraintViolationException e) 
        {
            // A WebApplicationException can wrap a response
            // Throwing the exception causes an automatic rollback
            throw new WebApplicationException(e);
        } catch (Exception e) {
            // Finally, handle 
            throw new WebApplicationException(e);
        }
    }
    
    /**
     * @param request
     * @return
     */
    private ProductComponent loadModelFromRequest(ProductComponentRequest request) 
    {
		ProductComponent productComponent = new ProductComponent();
    	Integer productComponentId = request.getId();
    	// Are we editing a ProductComponent
    	if(productComponentId != null) 
    	{
    		productComponent = getEntityManager().find(ProductComponent.class, request.getId());
    		productComponent.setLastModifiedDt(request.getLastModifiedDt());
        	productComponent.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	productComponent.setCreatedDt(getCurrentDate());
        	productComponent.setCreatedByUsr(getCurrentUserName(request));
    	}
    	productComponent.setCode(request.getCode());
    	productComponent.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getProductByInProdId() != null)
    	{
    		Product productByInProdId = entityManager.find(Product.class, request.getProductByInProdId());
    		productComponent.setProductByInProdId(productByInProdId);
    	}
    	if (request.getProductByForProdId() != null)
    	{
    		Product productByForProdId = entityManager.find(Product.class, request.getProductByForProdId());
    		productComponent.setProductByForProdId(productByForProdId);
    	}
    	productComponent.setLocationId(request.getLocationId()); 
    	productComponent.setCode(request.getCode()); 
    	productComponent.setDescription(request.getDescription()); 
    	productComponent.setInstruction(request.getInstruction()); 
    	productComponent.setRemarks(request.getRemarks()); 
    	productComponent.setQuantitiyUsed(request.getQuantitiyUsed()); 
    	productComponent.setFromDt(request.getFromDt()); 
    	productComponent.setToDt(request.getToDt()); 
    	productComponent.setEffectiveDt(request.getEffectiveDt()); 
    	productComponent.setRecSt(request.getRecSt()); 
		return productComponent;
	}
}
