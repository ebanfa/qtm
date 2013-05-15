/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.application.response;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * ApplicationEntityFieldResponse 
 * @author Edward Banfa
 */
public class ApplicationEntityFieldResponse extends BaseResponse {

    private Integer applicationEntityFieldTypeId;
    private Integer applicationRelatedEntityId;
    private Integer applicationEntityId;
    private String fieldTypeCode;
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

    public ApplicationEntityFieldResponse() {
    }

    public Integer getApplicationEntityFieldTypeId() {
        return this.applicationEntityFieldTypeId;
    }
    
    public void setApplicationEntityFieldTypeId(Integer applicationEntityFieldTypeId) {
        this.applicationEntityFieldTypeId = applicationEntityFieldTypeId;
    }

    public Integer getApplicationRelatedEntityId() {
        return this.applicationRelatedEntityId;
    }
    
    public void setApplicationRelatedEntityId(Integer applicationRelatedEntityId) {
        this.applicationRelatedEntityId = applicationRelatedEntityId;
    }

    public Integer getApplicationEntityId() {
        return this.applicationEntityId;
    }
    
    public void setApplicationEntityId(Integer applicationEntityId) {
        this.applicationEntityId = applicationEntityId;
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

	/**
	 * @return the fieldTypeCode
	 */
	public String getFieldTypeCode() {
		return fieldTypeCode;
	}

	/**
	 * @param fieldTypeCode the fieldTypeCode to set
	 */
	public void setFieldTypeCode(String fieldTypeCode) {
		this.fieldTypeCode = fieldTypeCode;
	}


}


