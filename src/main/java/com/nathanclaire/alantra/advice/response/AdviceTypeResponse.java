/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.advice.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * AdviceTypeResponse 
 * @author Edward Banfa
 */
public class AdviceTypeResponse extends BaseResponse {

    private Integer serviceTransactionTypeId;
    private String serviceTransactionTypeText;
    private Character reqFeedback;
    private String feedbackMsg;

    public AdviceTypeResponse() {
    }

    public Integer getServiceTransactionTypeId() {
        return this.serviceTransactionTypeId;
    }
    
    public void setServiceTransactionTypeId(Integer serviceTransactionTypeId) {
        this.serviceTransactionTypeId = serviceTransactionTypeId;
    }

    public String getServiceTransactionTypeText() {
        return this.serviceTransactionTypeText;
    }
    
    public void setServiceTransactionTypeText(String serviceTransactionTypeText) {
        this.serviceTransactionTypeText = serviceTransactionTypeText;
    }

    public Character getReqFeedback() {
        return this.reqFeedback;
    }
    
    public void setReqFeedback(Character reqFeedback) {
        this.reqFeedback = reqFeedback;
    }

    public String getFeedbackMsg() {
        return this.feedbackMsg;
    }
    
    public void setFeedbackMsg(String feedbackMsg) {
        this.feedbackMsg = feedbackMsg;
    }


}


