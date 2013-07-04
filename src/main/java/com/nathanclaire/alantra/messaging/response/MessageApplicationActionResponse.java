/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.messaging.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * MessageApplicationActionResponse 
 * @author Edward Banfa
 */
public class MessageApplicationActionResponse extends BaseResponse {

    private Integer messageApplicationId;
    private String messageApplicationText;
    private Character autoRespFg;

    public MessageApplicationActionResponse() {
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

    public Character getAutoRespFg() {
        return this.autoRespFg;
    }
    
    public void setAutoRespFg(Character autoRespFg) {
        this.autoRespFg = autoRespFg;
    }


}


