/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.advice.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * AdvicedTransactionResponse 
 * @author Edward Banfa
 */
public class AdvicedTransactionResponse extends BaseResponse {

    private Integer adviceId;
    private String adviceText;
    private Integer serviceTransactionId;
    private String serviceTransactionText;

    public AdvicedTransactionResponse() {
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


}


