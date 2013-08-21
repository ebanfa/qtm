/**
 *  Alantra.
 */
package com.nathanclaire.alantra.messaging.request;


import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * AdviceRequest 
 * @author Edward Banfa
 */
public class MessageAttachmentRequest extends BaseRequest {
    private Integer messageId;
    private String messageText;
    private String dataTy;
    private String dataUrl;

    public MessageAttachmentRequest() {
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
}


