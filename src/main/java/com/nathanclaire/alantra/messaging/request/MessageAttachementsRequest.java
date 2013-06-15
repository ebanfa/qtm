/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.messaging.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * MessageAttachementsRequest 
 * @author Edward Banfa
 */
public class MessageAttachementsRequest extends BaseRequest {

    private Integer messageId;
    private String messageText;
    private String dataTy;
    private String dataUrl;
    private Integer id;
    private String code;

    public MessageAttachementsRequest() {
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

    public String getDataTy() {
        return this.dataTy;
    }
    
    public void setDataTy(String dataTy) {
        this.dataTy = dataTy;
    }

    public String getDataUrl() {
        return this.dataUrl;
    }
    
    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
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


