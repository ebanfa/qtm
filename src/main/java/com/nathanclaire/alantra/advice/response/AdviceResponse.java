/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.advice.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * AdviceResponse 
 * @author Edward Banfa
 */
public class AdviceResponse extends BaseResponse {

    private Integer customerId;
    private Integer adviceStatusId;
    private Integer adviceTypeId;
    private int commEventId;
    private String adviceTxt;
    private String cardNo;
    private String accountNo;
    private String chequeNo;
    private String accountNm;
    private BigDecimal amount;

    public AdviceResponse() {
    }

    public Integer getCustomerId() {
        return this.customerId;
    }
    
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getAdviceStatusId() {
        return this.adviceStatusId;
    }
    
    public void setAdviceStatusId(Integer adviceStatusId) {
        this.adviceStatusId = adviceStatusId;
    }

    public Integer getAdviceTypeId() {
        return this.adviceTypeId;
    }
    
    public void setAdviceTypeId(Integer adviceTypeId) {
        this.adviceTypeId = adviceTypeId;
    }

    public int getCommEventId() {
        return this.commEventId;
    }
    
    public void setCommEventId(int commEventId) {
        this.commEventId = commEventId;
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


}


