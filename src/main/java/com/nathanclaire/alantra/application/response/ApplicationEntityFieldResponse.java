/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.application.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * ApplicationEntityFieldResponse 
 * @author Edward Banfa
 */
public class ApplicationEntityFieldResponse extends BaseResponse {

    private Integer applicationEntityFieldTypeId;
    private String applicationEntityFieldTypeText;
    private Integer applicationRelatedEntityId;
    private String applicationRelatedEntityText;
    private Integer applicationEntityId;
    private String applicationEntityText;
    private String storage;
    private Character primarykeyFg;
    private Character requiredFg;
    private Character searchFieldFg;
    private Character uniqueFg;
    private Character relatedFg;
    private Integer size;
    private Integer maxDigits;
    private Integer decimalPrecision;
    private Integer sequenceNo;

    public ApplicationEntityFieldResponse() {
    }

    public Integer getApplicationEntityFieldTypeId() {
        return this.applicationEntityFieldTypeId;
    }
    
    public void setApplicationEntityFieldTypeId(Integer applicationEntityFieldTypeId) {
        this.applicationEntityFieldTypeId = applicationEntityFieldTypeId;
    }

    public String getApplicationEntityFieldTypeText() {
        return this.applicationEntityFieldTypeText;
    }
    
    public void setApplicationEntityFieldTypeText(String applicationEntityFieldTypeText) {
        this.applicationEntityFieldTypeText = applicationEntityFieldTypeText;
    }

    public Integer getApplicationRelatedEntityId() {
        return this.applicationRelatedEntityId;
    }
    
    public void setApplicationRelatedEntityId(Integer applicationRelatedEntityId) {
        this.applicationRelatedEntityId = applicationRelatedEntityId;
    }

    public String getApplicationRelatedEntityText() {
        return this.applicationRelatedEntityText;
    }
    
    public void setApplicationRelatedEntityText(String applicationRelatedEntityText) {
        this.applicationRelatedEntityText = applicationRelatedEntityText;
    }

    public Integer getApplicationEntityId() {
        return this.applicationEntityId;
    }
    
    public void setApplicationEntityId(Integer applicationEntityId) {
        this.applicationEntityId = applicationEntityId;
    }

    public String getApplicationEntityText() {
        return this.applicationEntityText;
    }
    
    public void setApplicationEntityText(String applicationEntityText) {
        this.applicationEntityText = applicationEntityText;
    }

    public String getStorage() {
        return this.storage;
    }
    
    public void setStorage(String storage) {
        this.storage = storage;
    }

    public Character getPrimarykeyFg() {
        return this.primarykeyFg;
    }
    
    public void setPrimarykeyFg(Character primarykeyFg) {
        this.primarykeyFg = primarykeyFg;
    }

    public Character getRequiredFg() {
        return this.requiredFg;
    }
    
    public void setRequiredFg(Character requiredFg) {
        this.requiredFg = requiredFg;
    }

    public Character getSearchFieldFg() {
        return this.searchFieldFg;
    }
    
    public void setSearchFieldFg(Character searchFieldFg) {
        this.searchFieldFg = searchFieldFg;
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

    public Integer getSequenceNo() {
        return this.sequenceNo;
    }
    
    public void setSequenceNo(Integer sequenceNo) {
        this.sequenceNo = sequenceNo;
    }


}


