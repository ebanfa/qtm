/**
 * 
 */
package com.nathanclaire.alantra.base.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationActivityService;
import com.nathanclaire.alantra.application.service.entity.ApplicationRelatedActivityService;
import com.nathanclaire.alantra.base.response.BaseActivityResponse;
import com.nathanclaire.alantra.base.response.EditActivityResponse;
import com.nathanclaire.alantra.base.response.ListActivityResponse;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.response.RelatedActivityResponse;

/**
 * <p>
 *   A number of RESTful services implement GET operations on a particular type of entity. For
 *   observing the DRY principle, the generic operations are implemented in the <code>BaseActivityRESTService</code>
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
 * public class WidgetService extends BaseActivityRESTService<Widget> {
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
public abstract class BaseActivityRESTService<T,V> {
	
	@Inject
	ApplicationRelatedActivityService applicationRelatedActivityService;
	
	@Inject
	ApplicationActivityService applicationActivityService;

    /**
     * Default constructor
     */
    public BaseActivityRESTService() {}
    
    /**
     * <p>
     *     A method for retrieving the list activity of an entity.
     * </p>
     * @param id entity id
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ListActivityResponse<T> getListActivity(@Context UriInfo uriInfo) 
    {
    	ListActivityResponse<T> response = null;
    	MultivaluedMap<String, String> queryParameters = uriInfo.getPathParameters();
    	ApplicationActivityResponse activity = 
    			applicationActivityService.convertModelToResponse(applicationActivityService.findByCode(getListActivityCode()));
    	if(activity == null) return response;
    	return populateListActivityResponse(activity, prepareListActivityResponse(activity, queryParameters), queryParameters);
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
    public EditActivityResponse<T> getSingleInstance(@PathParam("id") Integer id) {
    	EditActivityResponse<T> response = null;
    	ApplicationActivityResponse activity = 
    			applicationActivityService.convertModelToResponse(applicationActivityService.findByCode(getEditActivityCode()));
    	if(activity == null) return response;
        return populateEditActivityResponse(id, activity, prepareEditActivityResponse(activity));
    }
    
    /**
     * <p>  Edit a Host. Data is contained in the HostRequest object </p>
     * @param request
     * @return
     */
    @PUT 
    @Path("/{id:[0-9][0-9]*}")
    @Consumes(MediaType.APPLICATION_JSON)
    public EditActivityResponse<T> edit(V request) 
    {
    	return this.saveEditedEntityInstance(request);
    }

    /**
     * @param uriInfo
     * @return
     */
    @GET
    @Path("/relatedlistitems")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, List<ListItemResponse>> getRelatedEntitiesAsListItems(@Context UriInfo uriInfo)
    {
    	return this.prepareRelatedEntitiesListItems(uriInfo.getQueryParameters());
    }
    /**
     * Prepare the list activity response object that will be returned
     * @param activity
     * @param queryParameters
     * @return
     */
    protected ListActivityResponse<T> prepareListActivityResponse(
    		ApplicationActivityResponse activity, MultivaluedMap<String, String> queryParameters)
    {
    	ListActivityResponse<T> response = new ListActivityResponse<T>();
    	prepareActivityResponse(response, activity);
    	return response;
    }
    
    /**
     * Prepare the edit activity response object.
     * @param activity
     * @return
     */
    protected EditActivityResponse<T> prepareEditActivityResponse(ApplicationActivityResponse activity)
    {
		EditActivityResponse<T> response = new EditActivityResponse<T>();
		prepareActivityResponse(response, activity);
    	return response;
    }

    /**
     * Load all the related activities for the given activity. The related activities will each be converted
     * to ActivityResponse objects.
     * @param activity
     * @return
     */
    protected List<RelatedActivityResponse> getRelatedActivities(ApplicationActivityResponse activity)
    {
    	//return applicationRelatedActivityService.getRelatedActivities(activity.getCode());
    	return null;
    }
    
    /**
     * Utility method to prepare an activity response object.
     * @param response
     * @param activity
     */
    private void prepareActivityResponse(BaseActivityResponse response, ApplicationActivityResponse activity)
    {
    	response.setId(activity.getId());
    	response.setName(activity.getName());
    	response.setCode(activity.getCode());
    	response.setActivityUrl(activity.getActivityUrl());
    	response.setRelatedActivities(getRelatedActivities(activity));
    }
    
    /**
     * Populate the list activity with data.
     * @param activity
     * @param response
     * @param queryParameters
     * @return
     */
    protected abstract ListActivityResponse<T> populateListActivityResponse(ApplicationActivityResponse activity, 
    		ListActivityResponse<T> response, MultivaluedMap<String, String> queryParameters );
    
    /**
     * NOTE: Make this method abstract!!!
     * @param id
     * @return
     */
    protected EditActivityResponse<T> populateEditActivityResponse(Integer id,	ApplicationActivityResponse activity,
			EditActivityResponse<T> response){
    	return null;
    }
    
    /**
     * NOTE: Make this method abstract
     * @param entityInstance
     * @return
     */
    protected EditActivityResponse<T> saveEditedEntityInstance(V entityInstance)
    {
    	return null;
    }
    
    protected Map<String, List<ListItemResponse>> prepareRelatedEntitiesListItems(MultivaluedMap<String, String> multivaluedMap)
    {
    	return null;
    }
    
    /**
     * Get the activity code for the list activity
     * @return
     */
    protected abstract String getListActivityCode();
    
    /**
     * Get the activity code for the edit activity
     * @return
     */
    protected abstract String getEditActivityCode();
    
    /**
     * @param parameterName
     * @param queryParameters
     * @return
     */
    protected String extractParameter(String parameterName, MultivaluedMap<String, String> queryParameters)
    {
    	if (queryParameters.containsKey(parameterName)) 
    	{
            return queryParameters.getFirst(parameterName);
    	}
		return "";
	}
    
}
