/**
 *  Alantra.
 */
package com.nathanclaire.alantra.channel.request;


import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * AdviceRequest 
 * @author Edward Banfa
 */
public class ChannelRequest extends BaseRequest {
    private String name;
    private String description;
    private Integer channelTypeId;
    private String channelTypeText;
    private Integer channelStatusId;
    private String channelStatusText;
    private Integer dataChannelId;
    private String dataChannelText;
    private Integer channelPipelineId;
    private String channelPipelineText;

    public ChannelRequest() {
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
	public Integer getChannelTypeId() {
        return this.channelTypeId;
    }
    
    public void setChannelTypeId(Integer channelTypeId) {
        this.channelTypeId = channelTypeId;
    }

    public String getChannelTypeText() {
        return this.channelTypeText;
    }
    
    public void setChannelTypeText(String channelTypeText) {
        this.channelTypeText = channelTypeText;
    }
	public Integer getChannelStatusId() {
        return this.channelStatusId;
    }
    
    public void setChannelStatusId(Integer channelStatusId) {
        this.channelStatusId = channelStatusId;
    }

    public String getChannelStatusText() {
        return this.channelStatusText;
    }
    
    public void setChannelStatusText(String channelStatusText) {
        this.channelStatusText = channelStatusText;
    }
	public Integer getDataChannelId() {
        return this.dataChannelId;
    }
    
    public void setDataChannelId(Integer dataChannelId) {
        this.dataChannelId = dataChannelId;
    }

    public String getDataChannelText() {
        return this.dataChannelText;
    }
    
    public void setDataChannelText(String dataChannelText) {
        this.dataChannelText = dataChannelText;
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


