/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * DataFieldRequest 
 * @author Edward Banfa
 */
public class DataFieldRequest extends BaseRequest {

    private Integer dataTransformerId;
    private String dataTransformerText;
    private Integer dataStructureId;
    private String dataStructureText;
    private Integer dataFieldTypeId;
    private String dataFieldTypeText;
    private String name;
    private String description;
    private String targetEntityCd;
    private String targetEntityField;
    private String relTargetEntityCd;
    private String fieldFormat;
    private int seqNo;
    private Integer id;
    private String code;

    public DataFieldRequest() {
    }

    public Integer getDataTransformerId() {
        return this.dataTransformerId;
    }
    
    public void setDataTransformerId(Integer dataTransformerId) {
        this.dataTransformerId = dataTransformerId;
    }

    public String getDataTransformerText() {
        return this.dataTransformerText;
    }
    
    public void setDataTransformerText(String dataTransformerText) {
        this.dataTransformerText = dataTransformerText;
    }

    public Integer getDataStructureId() {
        return this.dataStructureId;
    }
    
    public void setDataStructureId(Integer dataStructureId) {
        this.dataStructureId = dataStructureId;
    }

    public String getDataStructureText() {
        return this.dataStructureText;
    }
    
    public void setDataStructureText(String dataStructureText) {
        this.dataStructureText = dataStructureText;
    }

    public Integer getDataFieldTypeId() {
        return this.dataFieldTypeId;
    }
    
    public void setDataFieldTypeId(Integer dataFieldTypeId) {
        this.dataFieldTypeId = dataFieldTypeId;
    }

    public String getDataFieldTypeText() {
        return this.dataFieldTypeText;
    }
    
    public void setDataFieldTypeText(String dataFieldTypeText) {
        this.dataFieldTypeText = dataFieldTypeText;
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

    public String getTargetEntityCd() {
        return this.targetEntityCd;
    }
    
    public void setTargetEntityCd(String targetEntityCd) {
        this.targetEntityCd = targetEntityCd;
    }

    public String getTargetEntityField() {
        return this.targetEntityField;
    }
    
    public void setTargetEntityField(String targetEntityField) {
        this.targetEntityField = targetEntityField;
    }

    public String getRelTargetEntityCd() {
        return this.relTargetEntityCd;
    }
    
    public void setRelTargetEntityCd(String relTargetEntityCd) {
        this.relTargetEntityCd = relTargetEntityCd;
    }

    public String getFieldFormat() {
        return this.fieldFormat;
    }
    
    public void setFieldFormat(String fieldFormat) {
        this.fieldFormat = fieldFormat;
    }

    public int getSeqNo() {
        return this.seqNo;
    }
    
    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
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


