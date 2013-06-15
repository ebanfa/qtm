/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * DataFieldResponse 
 * @author Edward Banfa
 */
public class DataFieldResponse extends BaseResponse {

    private Integer dataTransformerId;
    private String dataTransformerText;
    private Integer dataStructureId;
    private String dataStructureText;
    private Integer dataFieldTypeId;
    private String dataFieldTypeText;
    private String targetEntityCd;
    private String targetEntityField;
    private String relTargetEntityCd;
    private String fieldFormat;
    private int seqNo;

    public DataFieldResponse() {
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


}


