/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.advice.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * AdviceTypeRequest 
 * @author Edward Banfa
 */
public class AdviceTypeRequest extends BaseRequest {

    private Integer serviceTransactionTypeId;
    private String serviceTransactionTypeText;
    private String name;
    private String description;
    private Character reqFeedback;
    private String feedbackMsg;
    private Integer id;
    private String code;

    public AdviceTypeRequest() {
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

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
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

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }


}


