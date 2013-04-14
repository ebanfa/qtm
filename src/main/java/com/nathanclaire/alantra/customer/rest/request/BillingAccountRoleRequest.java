/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.customer.rest.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.customer.model.BillingAccountRole;

/**
 * BillingAccountRoleRequest 
 * @author Edward Banfa
 */
public class BillingAccountRoleRequest extends BaseRequest {

    private Integer billingAccountRoleType;
    private Integer party;
    private Integer billingAccount;
    private String name;
    private String description;
    private Date fromDt;
    private Date thruDt;
    private Integer id;
    private String code;

    public BillingAccountRoleRequest() {
    }

    public Integer getBillingAccountRoleType() {
        return this.billingAccountRoleType;
    }
    
    public void setBillingAccountRoleType(Integer billingAccountRoleType) {
        this.billingAccountRoleType = billingAccountRoleType;
    }

    public Integer getParty() {
        return this.party;
    }
    
    public void setParty(Integer party) {
        this.party = party;
    }

    public Integer getBillingAccount() {
        return this.billingAccount;
    }
    
    public void setBillingAccount(Integer billingAccount) {
        this.billingAccount = billingAccount;
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

    public Date getFromDt() {
        return this.fromDt;
    }
    
    public void setFromDt(Date fromDt) {
        this.fromDt = fromDt;
    }

    public Date getThruDt() {
        return this.thruDt;
    }
    
    public void setThruDt(Date thruDt) {
        this.thruDt = thruDt;
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


