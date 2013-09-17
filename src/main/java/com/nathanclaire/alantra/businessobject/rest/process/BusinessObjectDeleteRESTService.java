/**
 * 
 */
package com.nathanclaire.alantra.businessobject.rest.process;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.nathanclaire.alantra.businessobject.data.BusinessObjectResponse;

/**
 * @author Edward Banfa
 *
 */
public class BusinessObjectDeleteRESTService {
	/**
     * @param uriInfo
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public BusinessObjectResponse deleteBusinesObject(@Context UriInfo uriInfo)
    {
    	return new BusinessObjectResponse();
    }
}
