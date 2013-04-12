/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.channel.rest.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;

/**
 * ServiceTransactionRequest 
 * @author Edward Banfa
 */
public class ServiceTransactionRequest extends BaseRequest {

    private Integer service;
    private Integer serviceTransactionType;
    private String name;
    private BigDecimal amount;
    private Date txnDate;
    private String accountNo;
    private String accountNm;
    private String description;
    private Integer id;
    private String code;

    public ServiceTransactionRequest() {
    }

    public Integer getService() {
        return this.service;
    }
    
    public void setService(Integer service) {
        this.service = service;
    }

    public Integer getServiceTransactionType() {
        return this.serviceTransactionType;
    }
    
    public void setServiceTransactionType(Integer serviceTransactionType) {
        this.serviceTransactionType = serviceTransactionType;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getTxnDate() {
        return this.txnDate;
    }
    
    public void setTxnDate(Date txnDate) {
        this.txnDate = txnDate;
    }

    public String getAccountNo() {
        return this.accountNo;
    }
    
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountNm() {
        return this.accountNm;
    }
    
    public void setAccountNm(String accountNm) {
        this.accountNm = accountNm;
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


