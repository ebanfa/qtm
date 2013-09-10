/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.customer.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * CustomerRequest 
 * @author Edward Banfa
 */
public class CustomerRequest extends BaseRequest {

    private Integer customerClassificationId;
    private String customerClassificationText;
    private Integer customerTypeId;
    private String customerTypeText;
    private String pin;
    private String name;
    private String primaryEmail;
    private String secondaryEmail;
    private String primaryMobile;
    private String secondaryMobile;
    private Integer id;
    private String code;

    public CustomerRequest() {
    }

    /**
	 * @param customerTypeId
	 * @param customerClassificationId
	 * @param name
	 * @param code
	 * @param pin
	 * @param primaryEmail
	 * @param primaryMobile
	 */
	public CustomerRequest(Integer customerTypeId,
			Integer customerClassificationId, String name, String code,
			String pin, String primaryEmail, String primaryMobile) {
		super();
		this.customerTypeId = customerTypeId;
		this.customerClassificationId = customerClassificationId;
		this.name = name;
		this.code = code;
		this.pin = pin;
		this.primaryEmail = primaryEmail;
		this.primaryMobile = primaryMobile;
	}

	public Integer getCustomerClassificationId() {
        return this.customerClassificationId;
    }
    
    public void setCustomerClassificationId(Integer customerClassificationId) {
        this.customerClassificationId = customerClassificationId;
    }

    public String getCustomerClassificationText() {
        return this.customerClassificationText;
    }
    
    public void setCustomerClassificationText(String customerClassificationText) {
        this.customerClassificationText = customerClassificationText;
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

    public String getPin() {
        return this.pin;
    }
    
    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getPrimaryEmail() {
        return this.primaryEmail;
    }
    
    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getSecondaryEmail() {
        return this.secondaryEmail;
    }
    
    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }

    public String getPrimaryMobile() {
        return this.primaryMobile;
    }
    
    public void setPrimaryMobile(String primaryMobile) {
        this.primaryMobile = primaryMobile;
    }

    public String getSecondaryMobile() {
        return this.secondaryMobile;
    }
    
    public void setSecondaryMobile(String secondaryMobile) {
        this.secondaryMobile = secondaryMobile;
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


