/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.advice.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * AdviceRequestMessageRequest 
 * @author Edward Banfa
 */
public class AdviceRequestMessageRequest extends BaseRequest {

    private Integer customerId;
    private String customerText;
    private Integer dataChannelId;
    private String dataChannelText;
    private Integer adviceRequestMessageStatusId;
    private String adviceRequestMessageStatusText;
    private String sourceAddress;
    private String name;
    private BigDecimal amount;
    private String accountNo;
    private String chequeNo;
    private String cardNo;
    private String currencyCd;
    private String description;
    private Integer id;
    private String code;

    public AdviceRequestMessageRequest() {
    }

    public Integer getCustomerId() {
        return this.customerId;
    }
    
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerText() {
        return this.customerText;
    }
    
    public void setCustomerText(String customerText) {
        this.customerText = customerText;
    }

    public Integer getDataChannelId() {
        return this.dataChannelId;
    }
    
    public void setDataChannelId(Integer dataChannelId) {
        this.dataChannelId = dataChannelId;
    }

    public String getDataChannelText() {
        return this.dataChannelText;
    }
    
    public void setDataChannelText(String dataChannelText) {
        this.dataChannelText = dataChannelText;
    }

    public Integer getAdviceRequestMessageStatusId() {
        return this.adviceRequestMessageStatusId;
    }
    
    public void setAdviceRequestMessageStatusId(Integer adviceRequestMessageStatusId) {
        this.adviceRequestMessageStatusId = adviceRequestMessageStatusId;
    }

    public String getAdviceRequestMessageStatusText() {
        return this.adviceRequestMessageStatusText;
    }
    
    public void setAdviceRequestMessageStatusText(String adviceRequestMessageStatusText) {
        this.adviceRequestMessageStatusText = adviceRequestMessageStatusText;
    }

    public String getSourceAddress() {
        return this.sourceAddress;
    }
    
    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
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

    public String getAccountNo() {
        return this.accountNo;
    }
    
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getChequeNo() {
        return this.chequeNo;
    }
    
    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public String getCardNo() {
        return this.cardNo;
    }
    
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCurrencyCd() {
        return this.currencyCd;
    }
    
    public void setCurrencyCd(String currencyCd) {
        this.currencyCd = currencyCd;
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

