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

    private Integer applicationEntityFieldTypeId;
    private String applicationEntityFieldTypeText;
    private Integer applicationRelatedEntityId;
    private String applicationRelatedEntityText;
    private Integer applicationEntityId;
    private String applicationEntityText;
    private String name;
    private String description;
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
    private Integer id;
    private String code;

    public ApplicationEntityFieldRequest() {
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


