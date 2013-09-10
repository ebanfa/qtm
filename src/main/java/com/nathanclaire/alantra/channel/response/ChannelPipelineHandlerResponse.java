/**
 *  Alantra.
 */
package com.nathanclaire.alantra.channel.response;


import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * AdviceResponse 
 * @author Edward Banfa
 */
public class ChannelPipelineHandlerResponse extends BaseResponse {
    private String name;
    private String description;
    private Integer channelHandlerId;
    private String channelHandlerText;
    private Integer channelPipelineId;
    private String channelPipelineText;

    public ChannelPipelineHandlerResponse() {
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
	public Integer getChannelHandlerId() {
        return this.channelHandlerId;
    }
    
    public void setChannelHandlerId(Integer channelHandlerId) {
        this.channelHandlerId = channelHandlerId;
    }

    public String getChannelHandlerText() {
        return this.channelHandlerText;
    }
    
    public void setChannelHandlerText(String channelHandlerText) {
        this.channelHandlerText = channelHandlerText;
    }
	public Integer getChannelPipelineId() {
        return this.channelPipelineId;
    }
    
    public void setChannelPipelineId(Integer channelPipelineId) {
        this.channelPipelineId = channelPipelineId;
    }

    public String getChannelPipelineText() {
        return this.channelPipelineText;
    }
    
    public void setChannelPipelineText(String channelPipelineText) {
        this.channelPipelineText = channelPipelineText;
    }
}


