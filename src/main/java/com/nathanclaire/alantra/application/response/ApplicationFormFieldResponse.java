/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.application.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * ApplicationFormFieldResponse 
 * @author Edward Banfa
 */
public class ApplicationFormFieldResponse extends BaseResponse {

    private Integer applicationFormFieldTypeId;
    private Integer applicationFormId;
    private Integer applicationEntityFieldId;
    private String description;
    private char primarykeyFg;
    private char requiredFg;
    private Character relatedFg;
    private Integer size;
    private Integer maxDigits;
    private Integer decimalPrecision;
    private int sequenceNo;

    public ApplicationFormFieldResponse() {
    }

    public Integer getApplicationFormFieldTypeId() {
        return this.applicationFormFieldTypeId;
    }
    
    public void setApplicationFormFieldTypeId(Integer applicationFormFieldTypeId) {
        this.applicationFormFieldTypeId = applicationFormFieldTypeId;
    }

    public Integer getApplicationFormId() {
        return this.applicationFormId;
    }
    
    public void setApplicationFormId(Integer applicationFormId) {
        this.applicationFormId = applicationFormId;
    }

    public Integer getApplicationEntityFieldId() {
        return this.applicationEntityFieldId;
    }
    
    public void setApplicationEntityFieldId(Integer applicationEntityFieldId) {
        this.applicationEntityFieldId = applicationEntityFieldId;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public char getPrimarykeyFg() {
        return this.primarykeyFg;
    }
    
    public void setPrimarykeyFg(char primarykeyFg) {
        this.primarykeyFg = primarykeyFg;
    }

    public char getRequiredFg() {
        return this.requiredFg;
    }
    
    public void setRequiredFg(char requiredFg) {
        this.requiredFg = requiredFg;
    }

    public Character getRelatedFg() {
        return this.relatedFg;
    }
    
    public void setRelatedFg(Character relatedFg) {
        this.relatedFg = relatedFg;
    }

    public Integer getSize() {
        return this.size;
    }
    
    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getMaxDigits() {
        return this.maxDigits;
    }
    
    public void setMaxDigits(Integer maxDigits) {
        this.maxDigits = maxDigits;
    }

    public Integer getDecimalPrecision() {
        return this.decimalPrecision;
    }
    
    public void setDecimalPrecision(Integer decimalPrecision) {
        this.decimalPrecision = decimalPrecision;
    }

    public int getSequenceNo() {
        return this.sequenceNo;
    }
    
    public void setSequenceNo(int sequenceNo) {
        this.sequenceNo = sequenceNo;
    }


}


