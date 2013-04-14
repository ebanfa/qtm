/**
 * 
 */
package com.nathanclaire.alantra.party.rest;

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
import com.nathanclaire.alantra.party.model.EstimatedProductCost;
import com.nathanclaire.alantra.product.model.Product;
import com.nathanclaire.alantra.product.rest.request.ProductRequest;
import com.nathanclaire.alantra.product.model.CostComponentType;
import com.nathanclaire.alantra.product.rest.request.CostComponentTypeRequest;
import com.nathanclaire.alantra.product.model.ProductFeature;
import com.nathanclaire.alantra.product.rest.request.ProductFeatureRequest;
import com.nathanclaire.alantra.businessdata.model.GeoBoundry;
import com.nathanclaire.alantra.businessdata.rest.request.GeoBoundryRequest;
import com.nathanclaire.alantra.party.rest.request.EstimatedProductCostRequest;

/**
 * @author administrator
 *
 */
@Path("/estimatedproductcost")
@Stateless
public class EstimatedProductCostRESTService extends BaseEntityRESTService<EstimatedProductCost> 
{
	/**
	 * @param entityClass
	 */
	public EstimatedProductCostRESTService() {
		super(EstimatedProductCost.class);
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
            CriteriaBuilder criteriaBuilder, Root<EstimatedProductCost> root) 
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
     *   Create a EstimatedProductCost. Data is contained in the EstimatedProductCostRequest object
     * </p>
     * @param EstimatedProductCostRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEstimatedProductCost(EstimatedProductCostRequest request) {
        try 
        {
        	EstimatedProductCost estimatedProductCost = this.loadModelFromRequest(request);
        	entityManager.persist(estimatedProductCost);
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
    public Response editEstimatedProductCost(EstimatedProductCostRequest request) 
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
    private EstimatedProductCost loadModelFromRequest(EstimatedProductCostRequest request) 
    {
		EstimatedProductCost estimatedProductCost = new EstimatedProductCost();
    	Integer estimatedProductCostId = request.getId();
    	// Are we editing a EstimatedProductCost
    	if(estimatedProductCostId != null) 
    	{
    		estimatedProductCost = getEntityManager().find(EstimatedProductCost.class, request.getId());
    		estimatedProductCost.setLastModifiedDt(request.getLastModifiedDt());
        	estimatedProductCost.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	estimatedProductCost.setCreatedDt(getCurrentDate());
        	estimatedProductCost.setCreatedByUsr(getCurrentUserName(request));
    	}
    	estimatedProductCost.setCode(request.getCode());
    	estimatedProductCost.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getProduct() != null)
    	{
    		Product product = entityManager.find(Product.class, request.getProduct());
    		estimatedProductCost.setProduct(product);
    	}
    	if (request.getCostComponentType() != null)
    	{
    		CostComponentType costComponentType = entityManager.find(CostComponentType.class, request.getCostComponentType());
    		estimatedProductCost.setCostComponentType(costComponentType);
    	}
    	if (request.getProductFeature() != null)
    	{
    		ProductFeature productFeature = entityManager.find(ProductFeature.class, request.getProductFeature());
    		estimatedProductCost.setProductFeature(productFeature);
    	}
    	if (request.getGeoBoundry() != null)
    	{
    		GeoBoundry geoBoundry = entityManager.find(GeoBoundry.class, request.getGeoBoundry());
    		estimatedProductCost.setGeoBoundry(geoBoundry);
    	}
    	estimatedProductCost.setCost(request.getCost()); 
    	estimatedProductCost.setDescription(request.getDescription()); 
    	estimatedProductCost.setFromDt(request.getFromDt()); 
    	estimatedProductCost.setToDt(request.getToDt()); 
    	estimatedProductCost.setCode(request.getCode()); 
    	estimatedProductCost.setEffectiveDt(request.getEffectiveDt()); 
    	estimatedProductCost.setRecSt(request.getRecSt()); 
		return estimatedProductCost;
	}
}
