/**
 *  Alantra.
 */
package com.nathanclaire.alantra.channel.request;


import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * AdviceRequest 
 * @author Edward Banfa
 */
public class ChannelHandlerRequest extends BaseRequest {
    private String name;
    private String description;
    private Integer parentChannelHandlerId;
    private String parentChannelHandlerText;
    private Integer channelHandlerTypeId;
    private String channelHandlerTypeText;

    public ChannelHandlerRequest() {
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
	public Integer getParentChannelHandlerId() {
        return this.parentChannelHandlerId;
    }
    
    public void setParentChannelHandlerId(Integer parentChannelHandlerId) {
        this.parentChannelHandlerId = parentChannelHandlerId;
    }

    public String getParentChannelHandlerText() {
        return this.parentChannelHandlerText;
    }
    
    public void setParentChannelHandlerText(String parentChannelHandlerText) {
        this.parentChannelHandlerText = parentChannelHandlerText;
    }
	public Integer getChannelHandlerTypeId() {
        return this.channelHandlerTypeId;
    }
    
    public void setChannelHandlerTypeId(Integer channelHandlerTypeId) {
        this.channelHandlerTypeId = channelHandlerTypeId;
    }

    public String getChannelHandlerTypeText() {
        return this.channelHandlerTypeText;
    }
    
    public void setChannelHandlerTypeText(String channelHandlerTypeText) {
        this.channelHandlerTypeText = channelHandlerTypeText;
    }
}


