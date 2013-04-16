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

    private Integer party;
    private Integer adviceStatus;
    private Integer communicationEvent;
    private Integer adviceType;
    private String name;
    private String description;
    private String adviceTxt;
    private String accountNo;
    private Integer id;
    private String code;

    public AdviceRequest() {
    }

    public Integer getParty() {
        return this.party;
    }
    
    public void setParty(Integer party) {
        this.party = party;
    }

    public Integer getAdviceStatus() {
        return this.adviceStatus;
    }
    
    public void setAdviceStatus(Integer adviceStatus) {
        this.adviceStatus = adviceStatus;
    }

    public Integer getCommunicationEvent() {
        return this.communicationEvent;
    }
    
    public void setCommunicationEvent(Integer communicationEvent) {
        this.communicationEvent = communicationEvent;
    }

    public Integer getAdviceType() {
        return this.adviceType;
    }
    
    public void setAdviceType(Integer adviceType) {
        this.adviceType = adviceType;
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

    public String getAccountNo() {
        return this.accountNo;
    }
    
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
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


