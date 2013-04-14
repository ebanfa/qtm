/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.advice.rest.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.advice.model.AdviceTypeTag;

/**
 * AdviceTypeTagRequest 
 * @author Edward Banfa
 */
public class AdviceTypeTagRequest extends BaseRequest {

    private Integer adviceType;
    private String name;
    private String description;
    private String adviceTyTagVal;
    private char isRegexFg;
    private Integer id;
    private String code;

    public AdviceTypeTagRequest() {
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


