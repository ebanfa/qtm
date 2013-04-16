/**
 * 
 */
package com.nathanclaire.alantra.base.rest;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.nathanclaire.alantra.advice.model.AdviceType;

/**
 * <p>
 *   A number of RESTful services implement GET operations on a particular type of entity. For
 *   observing the DRY principle, the generic operations are implemented in the <code>BaseEntityRESTService</code>
 *   class, and the other services can inherit from here.
 * </p>
 *
 * <p>
 *    Subclasses will declare a base path using the JAX-RS {@link Path} annotation, for example:
 * </p>
 *
 * <pre>
 * <code>
 * &#064;Path("/widgets")
 * public class WidgetService extends BaseEntityRESTService<Widget> {
 * ...
 * }
 * </code>
 * </pre>
 *
 * <p>
 *   will support the following methods:
 * </p>
 *
 * <pre>
 * <code>
 *   GET /widgets
 *   GET /widgets/:id
 *   GET /widgets/count
 * </code>
 * </pre>
 *
 *  <p>
 *     Subclasses may specify various criteria for filtering entities when retrieving a list of them, by supporting
 *     custom query parameters. Pagination is supported by default through the query parameters <code>first</code>
 *     and <code>maxResults</code>.
 * </p>
 *
 * <p>
 *     The class is abstract because it is not intended to be used directly, but subclassed by actual JAX-RS
 *     endpoints.
 * </p>
 *
 */
public abstract class BaseEntityRESTService<T,V> {

    /**
     * Default constructor
     */
    public BaseEntityRESTService() {}
    
    /**
     * <p>
     *   A method for retrieving all entities of a given type. Supports the query parameters <code>first</code>
     *   and <code>maxResults</code> for pagination.
     * </p>
     *
     * @param uriInfo application and request context information (see {@see UriInfo} class information for more details)
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<T> getAll(@Context UriInfo uriInfo) 
    {
        return getAll(uriInfo.getQueryParameters());
    }

    /**
     * <p>
     *     A method for retrieving individual entity instances.
     * </p>
     * @param id entity id
     * @return
     */
    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public T findById(@PathParam("id") Integer id) 
    {
    	
    	AdviceType instance = (AdviceType) getSingleInstance(id);
    	//System.out.println(">>>>>>>>>>>>>>>Returning single instance: " + instance.getLastModifiedUsr());
        return getSingleInstance(id);
    }

    /**
     * <p>
     *   A method for counting all entities of a given type
     * </p>
     *
     * @param uriInfo application and request context information (see {@see UriInfo} class information for more details)
     * @return
     */
    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Long> getCount(@Context UriInfo uriInfo) {
    	return getInstanceCount(uriInfo.getQueryParameters());
    }
    
    /**
     * <p>  Create a Host. Data is contained in the HostRequest object </p>
     * @param HostRequest
     * @return
     */
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link HostRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createHost(V request) {
        return createInstance(request);
    }
    
    /**
     * <p>  Edit a Host. Data is contained in the HostRequest object </p>
     * @param request
     * @return
     */
    @PUT 
    @Path("/{id:[0-9][0-9]*}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(V request) 
    {
    	return editInstance(request);
    }
    
    /**
     * @param queryParameters
     * @return
     */
    protected abstract List<T> getAll(MultivaluedMap<String, String> queryParameters);
    
    /**
     * @param queryParameters
     * @return
     */
    protected abstract T getSingleInstance(Integer id);
    
    /**
     * @param request
     * @return
     */
    protected abstract Response createInstance(V request);
    
    /**
     * @param request
     * @return
     */
    protected abstract Response editInstance(V request);
    
    /**
     * @param queryParameters
     * @return
     */
    protected abstract Map<String, Long> getInstanceCount(MultivaluedMap<String, String> queryParameters);

}
