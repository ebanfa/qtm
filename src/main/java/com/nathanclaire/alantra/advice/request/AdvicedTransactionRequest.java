/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.advice.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * AdvicedTransactionRequest 
 * @author Edward Banfa
 */
public class AdvicedTransactionRequest extends BaseRequest {

    private Integer adviceId;
    private String adviceText;
    private Integer serviceTransactionId;
    private String serviceTransactionText;
    private String name;
    private String description;
    private Integer id;
    private String code;

    public AdvicedTransactionRequest() {
    }

    public Integer getAdviceId() {
        return this.adviceId;
    }
    
    public void setAdviceId(Integer adviceId) {
        this.adviceId = adviceId;
    }

    public String getAdviceText() {
        return this.adviceText;
    }
    
    public void setAdviceText(String adviceText) {
        this.adviceText = adviceText;
    }

    public Integer getServiceTransactionId() {
        return this.serviceTransactionId;
    }
    
    public void setServiceTransactionId(Integer serviceTransactionId) {
        this.serviceTransactionId = serviceTransactionId;
    }

    public String getServiceTransactionText() {
        return this.serviceTransactionText;
    }
    
    public void setServiceTransactionText(String serviceTransactionText) {
        this.serviceTransactionText = serviceTransactionText;
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


