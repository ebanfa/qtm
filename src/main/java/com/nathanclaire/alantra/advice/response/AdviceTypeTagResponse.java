/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.advice.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * AdviceTypeTagResponse 
 * @author Edward Banfa
 */
public class AdviceTypeTagResponse extends BaseResponse {

    private Integer adviceTypeId;
    private String adviceTypeText;
    private String adviceTyTagVal;
    private char isRegexFg;

    public AdviceTypeTagResponse() {
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


}


