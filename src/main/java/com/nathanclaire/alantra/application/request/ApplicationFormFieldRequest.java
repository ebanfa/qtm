/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.application.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * ApplicationFormFieldRequest 
 * @author Edward Banfa
 */
public class ApplicationFormFieldRequest extends BaseRequest {

    private Integer id;
    private Integer applicationFormFieldType;
    private Integer applicationForm;
    private Integer applicationEntityField;
    private String code;
    private String name;
    private String description;
    private char primarykeyFg;
    private char requiredFg;
    private Character relatedFg;
    private Integer size;
    private Integer maxDigits;
    private Integer decimalPrecision;
    private int sequenceNo;

    public ApplicationFormFieldRequest() {
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApplicationFormFieldType() {
        return this.applicationFormFieldType;
    }
    
    public void setApplicationFormFieldType(Integer applicationFormFieldType) {
        this.applicationFormFieldType = applicationFormFieldType;
    }

    public Integer getApplicationForm() {
        return this.applicationForm;
    }
    
    public void setApplicationForm(Integer applicationForm) {
        this.applicationForm = applicationForm;
    }

    public Integer getApplicationEntityField() {
        return this.applicationEntityField;
    }
    
    public void setApplicationEntityField(Integer applicationEntityField) {
        this.applicationEntityField = applicationEntityField;
    }

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
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


