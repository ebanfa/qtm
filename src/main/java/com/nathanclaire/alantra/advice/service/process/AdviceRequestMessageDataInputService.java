/**
 * 
 */
package com.nathanclaire.alantra.advice.service.process;

import com.nathanclaire.alantra.base.service.process.EntityDataInputService;

/**
 * @author Edward Banfa 
 *
 */
public interface AdviceRequestMessageDataInputService extends EntityDataInputService 
{
	public final String ADVICE_CURRENCY_NOT_FOUND = "AdviceRequestMessageDataInputService.ADVICE_CURRENCY_NOT_FOUND";
	public final String CUSTOMER_ACCOUNT_DOES_NOT_EXIST = "AdviceRequestMessageDataInputService.CUSTOMER_ACCOUNT_DOES_NOT_EXIST";
	public final String ADVICE_TYPE_NOT_FOUND = "AdviceRequestMessageDataInputService.ADVICE_TYPE_NOT_FOUND";
	public final String ADVICE_STATUS_NOT_FOUND = "AdviceRequestMessageDataInputService.ADVICE_STATUS_NOT_FOUND";
	public final String ADVICE_CHANNEL_NOT_FOUND = "AdviceRequestMessageDataInputService.ADVICE_CHANNEL_NOT_FOUND";
	public final String ADVICE_CLASSIFICATION_NOT_FOUND = "AdviceRequestMessageDataInputService.ADVICE_CLASSIFICATION_NOT_FOUND";
	public final String ADVICE_REQUEST_MESSAGE_NOT_FOUND = "AdviceRequestMessageDataInputService.ADVICE_REQUEST_MESSAGE_NOT_FOUND";
	public final String ADVICE_REQUEST_ALREADY_EXIST = "AdviceRequestMessageDataInputService.ADVICE_REQUEST_ALREADY_EXIST";

}
