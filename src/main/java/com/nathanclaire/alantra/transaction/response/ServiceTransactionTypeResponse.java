/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.transaction.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * ServiceTransactionTypeResponse 
 * @author Edward Banfa
 */
public class ServiceTransactionTypeResponse extends BaseResponse {

    private Integer serviceTransactionCategoryId;
    private String serviceTransactionCategoryText;

    public ServiceTransactionTypeResponse() {
    }

    public Integer getServiceTransactionCategoryId() {
        return this.serviceTransactionCategoryId;
    }
    
    public void setServiceTransactionCategoryId(Integer serviceTransactionCategoryId) {
        this.serviceTransactionCategoryId = serviceTransactionCategoryId;
    }

    public String getServiceTransactionCategoryText() {
        return this.serviceTransactionCategoryText;
    }
    
    public void setServiceTransactionCategoryText(String serviceTransactionCategoryText) {
        this.serviceTransactionCategoryText = serviceTransactionCategoryText;
    }


}


