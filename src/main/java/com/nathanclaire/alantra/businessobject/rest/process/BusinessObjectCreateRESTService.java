/**
 * 
 */
package com.nathanclaire.alantra.businessobject.rest.process;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.nathanclaire.alantra.businessobject.data.BusinessObjectRequest;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectResponse;

/**
 * @author Edward Banfa
 *
 */
public class BusinessObjectCreateRESTService {
	

    /**
     * @param uriInfo
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public BusinessObjectResponse createBusinesObject(BusinessObjectRequest businessObjectRequest)
    {
    	// Validate the business object
    	this.validateRequest(businessObjectRequest);
    	// Convert the business object request into
    	// a business object data
    	// pass the business object data to the business 
    	// object creation service
    	return new BusinessObjectResponse();
    }

	private void validateRequest(BusinessObjectRequest businessObjectRequest) {
		// TODO Auto-generated method stub
		
	}

}
