/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.messaging.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * MessageApplicationTagResponse 
 * @author Edward Banfa
 */
public class MessageApplicationTagResponse extends BaseResponse {

    private Integer messageApplicationId;
    private String messageApplicationText;
    private String tagVal;
    private char isRegexFg;

    public MessageApplicationTagResponse() {
    }

    public Integer getMessageApplicationId() {
        return this.messageApplicationId;
    }
    
    public void setMessageApplicationId(Integer messageApplicationId) {
        this.messageApplicationId = messageApplicationId;
    }

    public String getMessageApplicationText() {
        return this.messageApplicationText;
    }
    
    public void setMessageApplicationText(String messageApplicationText) {
        this.messageApplicationText = messageApplicationText;
    }

    public String getTagVal() {
        return this.tagVal;
    }
    
    public void setTagVal(String tagVal) {
        this.tagVal = tagVal;
    }

    public char getIsRegexFg() {
        return this.isRegexFg;
    }
    
    public void setIsRegexFg(char isRegexFg) {
        this.isRegexFg = isRegexFg;
    }


}


