/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.messaging.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * MessageAttachementsResponse 
 * @author Edward Banfa
 */
public class MessageAttachementsResponse extends BaseResponse {

    private Integer messageId;
    private String messageText;
    private byte data;
    private String dataTy;

    public MessageAttachementsResponse() {
    }

    public Integer getMessageId() {
        return this.messageId;
    }
    
    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessageText() {
        return this.messageText;
    }
    
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public byte getData() {
        return this.data;
    }
    
    public void setData(byte data) {
        this.data = data;
    }

    public String getDataTy() {
        return this.dataTy;
    }
    
    public void setDataTy(String dataTy) {
        this.dataTy = dataTy;
    }


}


