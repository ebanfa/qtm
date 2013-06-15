/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.advice.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * AdviceRequest 
 * @author Edward Banfa
 */
public class AdviceRequest extends BaseRequest {

    private Integer customerId;
    private String customerText;
    private Integer currencyId;
    private String currencyText;
    private Integer adviceRequestMessageId;
    private String adviceRequestMessageText;
    private Integer adviceStatusId;
    private String adviceStatusText;
    private Integer adviceClassificationId;
    private String adviceClassificationText;
    private Integer customerAccountId;
    private String customerAccountText;
    private Integer adviceTypeId;
    private String adviceTypeText;
    private String name;
    private String description;
    private String adviceTxt;
    private String cardNo;
    private String chequeNo;
    private String accountNm;
    private BigDecimal amount;
    private Date startDt;
    private Date endDt;
    private int maxMatches;
    private int matchCount;
    private Integer id;
    private String code;

    public AdviceRequest() {
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

    public Integer getCurrencyId() {
        return this.currencyId;
    }
    
    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyText() {
        return this.currencyText;
    }
    
    public void setCurrencyText(String currencyText) {
        this.currencyText = currencyText;
    }

    public Integer getAdviceRequestMessageId() {
        return this.adviceRequestMessageId;
    }
    
    public void setAdviceRequestMessageId(Integer adviceRequestMessageId) {
        this.adviceRequestMessageId = adviceRequestMessageId;
    }

    public String getAdviceRequestMessageText() {
        return this.adviceRequestMessageText;
    }
    
    public void setAdviceRequestMessageText(String adviceRequestMessageText) {
        this.adviceRequestMessageText = adviceRequestMessageText;
    }

    public Integer getAdviceStatusId() {
        return this.adviceStatusId;
    }
    
    public void setAdviceStatusId(Integer adviceStatusId) {
        this.adviceStatusId = adviceStatusId;
    }

    public String getAdviceStatusText() {
        return this.adviceStatusText;
    }
    
    public void setAdviceStatusText(String adviceStatusText) {
        this.adviceStatusText = adviceStatusText;
    }

    public Integer getAdviceClassificationId() {
        return this.adviceClassificationId;
    }
    
    public void setAdviceClassificationId(Integer adviceClassificationId) {
        this.adviceClassificationId = adviceClassificationId;
    }

    public String getAdviceClassificationText() {
        return this.adviceClassificationText;
    }
    
    public void setAdviceClassificationText(String adviceClassificationText) {
        this.adviceClassificationText = adviceClassificationText;
    }

    public Integer getCustomerAccountId() {
        return this.customerAccountId;
    }
    
    public void setCustomerAccountId(Integer customerAccountId) {
        this.customerAccountId = customerAccountId;
    }

    public String getCustomerAccountText() {
        return this.customerAccountText;
    }
    
    public void setCustomerAccountText(String customerAccountText) {
        this.customerAccountText = customerAccountText;
    }

    public Integer getAdviceTypeId() {
        return this.adviceTypeId;
    }
    
    public void setAdviceTypeId(Integer adviceTypeId) {
        this.adviceTypeId = adviceTypeId;
    }

    public String getAdviceTypeText() {
        return this.adviceTypeText;
    }
    
    public void setAdviceTypeText(String adviceTypeText) {
        this.adviceTypeText = adviceTypeText;
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

    public String getAdviceTxt() {
        return this.adviceTxt;
    }
    
    public void setAdviceTxt(String adviceTxt) {
        this.adviceTxt = adviceTxt;
    }

    public String getCardNo() {
        return this.cardNo;
    }
    
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getChequeNo() {
        return this.chequeNo;
    }
    
    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public String getAccountNm() {
        return this.accountNm;
    }
    
    public void setAccountNm(String accountNm) {
        this.accountNm = accountNm;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getStartDt() {
        return this.startDt;
    }
    
    public void setStartDt(Date startDt) {
        this.startDt = startDt;
    }

    public Date getEndDt() {
        return this.endDt;
    }
    
    public void setEndDt(Date endDt) {
        this.endDt = endDt;
    }

    public int getMaxMatches() {
        return this.maxMatches;
    }
    
    public void setMaxMatches(int maxMatches) {
        this.maxMatches = maxMatches;
    }

    public int getMatchCount() {
        return this.matchCount;
    }
    
    public void setMatchCount(int matchCount) {
        this.matchCount = matchCount;
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


