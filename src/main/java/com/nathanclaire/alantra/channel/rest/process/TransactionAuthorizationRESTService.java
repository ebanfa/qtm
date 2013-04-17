/**
 * 
 */
package com.nathanclaire.alantra.channel.rest.process;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.nathanclaire.alantra.base.service.process.BaseBusinessService;
import com.nathanclaire.alantra.channel.request.ServiceTransactionRequest;
import com.nathanclaire.alantra.channel.service.entity.ServiceService;
import com.nathanclaire.alantra.channel.service.entity.ServiceTransactionService;

/**
 * @author Edward Banfa 
 *
 */
@Path("/authorize")
@Stateless
public class TransactionAuthorizationRESTService extends BaseBusinessService 
{
	/**
	 * 
	 */
	@Inject
	ServiceTransactionService serviceTransactionService;
	/**
	 * 
	 */
	@Inject
	ServiceService serviceService;
	
    /**
     * @param transactionRequest
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	public ServiceTransactionRequest authorize(ServiceTransactionRequest transactionRequest)
	{
    	serviceTransactionService.create(transactionRequest);
    	transactionRequest.setReturnCode(1);
		return transactionRequest;
	}
}
