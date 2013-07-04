/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.customer.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * CustomerTypeResponse 
 * @author Edward Banfa
 */
public class CustomerTypeResponse extends BaseResponse {

    private Integer customerCategoryId;
    private String customerCategoryText;

    public CustomerTypeResponse() {
    }

    public Integer getCustomerCategoryId() {
        return this.customerCategoryId;
    }
    
    public void setCustomerCategoryId(Integer customerCategoryId) {
        this.customerCategoryId = customerCategoryId;
    }

    public String getCustomerCategoryText() {
        return this.customerCategoryText;
    }
    
    public void setCustomerCategoryText(String customerCategoryText) {
        this.customerCategoryText = customerCategoryText;
    }


}


