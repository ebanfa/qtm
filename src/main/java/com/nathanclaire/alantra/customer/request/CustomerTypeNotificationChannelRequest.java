/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.customer.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * CustomerTypeNotificationChannelRequest 
 * @author Edward Banfa
 */
public class CustomerTypeNotificationChannelRequest extends BaseRequest {

    private Integer dataChannelCategoryId;
    private String dataChannelCategoryText;
    private Integer customerTypeId;
    private String customerTypeText;
    private Integer id;
    private String code;

    public CustomerTypeNotificationChannelRequest() {
    }

    public Integer getDataChannelCategoryId() {
        return this.dataChannelCategoryId;
    }
    
    public void setDataChannelCategoryId(Integer dataChannelCategoryId) {
        this.dataChannelCategoryId = dataChannelCategoryId;
    }

    public String getDataChannelCategoryText() {
        return this.dataChannelCategoryText;
    }
    
    public void setDataChannelCategoryText(String dataChannelCategoryText) {
        this.dataChannelCategoryText = dataChannelCategoryText;
    }

    public Integer getCustomerTypeId() {
        return this.customerTypeId;
    }
    
    public void setCustomerTypeId(Integer customerTypeId) {
        this.customerTypeId = customerTypeId;
    }

    public String getCustomerTypeText() {
        return this.customerTypeText;
    }
    
    public void setCustomerTypeText(String customerTypeText) {
        this.customerTypeText = customerTypeText;
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


