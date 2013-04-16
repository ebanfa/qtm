/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.payment.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * PaymentRequest 
 * @author Edward Banfa
 */
public class PaymentRequest extends BaseRequest {

    private Integer paymentType;
    private Integer partyByToPartyId;
    private Integer partyByFromPartyId;
    private Integer paymentMethodTypeProvider;
    private Integer paymentMethodType;
    private String name;
    private String description;
    private String comment;
    private BigDecimal amount;
    private Integer id;
    private String code;

    public PaymentRequest() {
    }

    public Integer getPaymentType() {
        return this.paymentType;
    }
    
    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getPartyByToPartyId() {
        return this.partyByToPartyId;
    }
    
    public void setPartyByToPartyId(Integer partyByToPartyId) {
        this.partyByToPartyId = partyByToPartyId;
    }

    public Integer getPartyByFromPartyId() {
        return this.partyByFromPartyId;
    }
    
    public void setPartyByFromPartyId(Integer partyByFromPartyId) {
        this.partyByFromPartyId = partyByFromPartyId;
    }

    public Integer getPaymentMethodTypeProvider() {
        return this.paymentMethodTypeProvider;
    }
    
    public void setPaymentMethodTypeProvider(Integer paymentMethodTypeProvider) {
        this.paymentMethodTypeProvider = paymentMethodTypeProvider;
    }

    public Integer getPaymentMethodType() {
        return this.paymentMethodType;
    }
    
    public void setPaymentMethodType(Integer paymentMethodType) {
        this.paymentMethodType = paymentMethodType;
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

    public String getComment() {
        return this.comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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


