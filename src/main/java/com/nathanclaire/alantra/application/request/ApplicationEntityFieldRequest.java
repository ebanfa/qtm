/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.application.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * ApplicationEntityFieldRequest 
 * @author Edward Banfa
 */
public class ApplicationEntityFieldRequest extends BaseRequest {

    private Integer id;
    private Integer applicationEntityFieldType;
    private Integer applicationEntityByRelatedEntityId;
    private Integer applicationEntityByEntityId;
    private String code;
    private String name;
    private String description;
    private char primarykeyFg;
    private String storage;
    private char requiredFg;
    private Character uniqueFg;
    private Character relatedFg;
    private Integer size;
    private Integer maxDigits;
    private Integer decimalPrecision;
    private int sequenceNo;

    public ApplicationEntityFieldRequest() {
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApplicationEntityFieldType() {
        return this.applicationEntityFieldType;
    }
    
    public void setApplicationEntityFieldType(Integer applicationEntityFieldType) {
        this.applicationEntityFieldType = applicationEntityFieldType;
    }

    public Integer getApplicationEntityByRelatedEntityId() {
        return this.applicationEntityByRelatedEntityId;
    }
    
    public void setApplicationEntityByRelatedEntityId(Integer applicationEntityByRelatedEntityId) {
        this.applicationEntityByRelatedEntityId = applicationEntityByRelatedEntityId;
    }

    public Integer getApplicationEntityByEntityId() {
        return this.applicationEntityByEntityId;
    }
    
    public void setApplicationEntityByEntityId(Integer applicationEntityByEntityId) {
        this.applicationEntityByEntityId = applicationEntityByEntityId;
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

    public String getStorage() {
        return this.storage;
    }
    
    public void setStorage(String storage) {
        this.storage = storage;
    }

    public char getRequiredFg() {
        return this.requiredFg;
    }
    
    public void setRequiredFg(char requiredFg) {
        this.requiredFg = requiredFg;
    }

    public Character getUniqueFg() {
        return this.uniqueFg;
    }
    
    public void setUniqueFg(Character uniqueFg) {
        this.uniqueFg = uniqueFg;
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


