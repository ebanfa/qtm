/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.advice.rest.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;

/**
 * AdviceTypeRequest 
 * @author Edward Banfa
 */
public class AdviceTypeRequest extends BaseRequest {

    private String name;
    private String description;
    private Character reqFeedback;
    private String feedbackMsg;
    private Integer id;
    private String code;

    public AdviceTypeRequest() {
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


