/**
 * 
 */
package com.nathanclaire.alantra.advice.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.advice.model.Advice;
import com.nathanclaire.alantra.base.service.BaseEntityService;
import com.nathanclaire.alantra.channel.rest.request.ServiceTransactionRequest;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class TransactionAuthorizerServiceImpl extends BaseEntityService<Advice> implements TransactionAuthorizerService {

	@Inject
	MultivaluedMap<String, String> queryParameters;
	private Logger logger = LoggerFactory.getLogger(TransactionAuthorizerServiceImpl.class);
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.TransactionAuthorizerService#isAuthorized(com.nathanclaire.alantra.advice.rest.request.TransactionRequest)
	 */
	@Override
	public boolean isAuthorized(ServiceTransactionRequest transactionRequest) {
		// TODO Auto-generated method stub
		queryParameters.add("", "");
		logger.debug("Processing authorization request for account: {}", transactionRequest.getAccountNo());
		return false;
	}

}
