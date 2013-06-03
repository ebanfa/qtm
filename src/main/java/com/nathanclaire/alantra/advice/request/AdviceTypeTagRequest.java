/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.advice.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * AdviceTypeTagRequest 
 * @author Edward Banfa
 */
public class AdviceTypeTagRequest extends BaseRequest {

    private Integer adviceTypeId;
    private String adviceTypeText;
    private String name;
    private String description;
    private String adviceTyTagVal;
    private char isRegexFg;
    private Integer id;
    private String code;

    public AdviceTypeTagRequest() {
    }

    public Integer getAdviceTypeId() {
        return this.adviceTypeId;
    }
    
    public void setAdviceTypeId(Integer adviceTypeId) {
        this.adviceTypeId = adviceTypeId;
    }

    public String getAdviceTypeText() {
        return this.adviceTypeText;
    }
    
    public void setAdviceTypeText(String adviceTypeText) {
        this.adviceTypeText = adviceTypeText;
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

    public String getAdviceTyTagVal() {
        return this.adviceTyTagVal;
    }
    
    public void setAdviceTyTagVal(String adviceTyTagVal) {
        this.adviceTyTagVal = adviceTyTagVal;
    }

    public char getIsRegexFg() {
        return this.isRegexFg;
    }
    
    public void setIsRegexFg(char isRegexFg) {
        this.isRegexFg = isRegexFg;
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


