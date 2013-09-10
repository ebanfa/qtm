/**
 *  Alantra.
 */
package com.nathanclaire.alantra.channel.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.datasource.model.DataChannel;

/**
 * Channel 
 * @author Edward Banfa
 */
@Entity
@Table(name="CHANNEL"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Channel  extends BaseEntity implements java.io.Serializable {
	private String name;
	private String description;
	private ChannelType channelType;
	private ChannelStatus channelStatus;
	private DataChannel dataChannel;
	private ChannelPipeline pipeline;
	private Character autoStartFg;

    public Channel() {
    }

    public Channel(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public Channel(String name, String description, ChannelType channelType, ChannelStatus channelStatus, DataChannel dataChannel, ChannelPipeline channelPipeline, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.name = name;
        this.description = description;
        this.channelType = channelType;
        this.channelStatus = channelStatus;
        this.dataChannel = dataChannel;
        this.pipeline = channelPipeline;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.versionNo = versionNo;
        this.rowTs = rowTs;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
        this.lastModifiedDt = lastModifiedDt;
        this.lastModifiedUsr = lastModifiedUsr;
    }
    
    @Column(name="NAME", nullable=false, length=150)
    public String getName() 
    {
        return this.name;
    }
    
    public void setName(String name) 
    {
        this.name = name;
    }
	
    @Column(name="DESCRIPTION", length=150)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }
	
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CHANNEL_TY_ID", nullable=false)
    @JsonIgnore
    public ChannelType getChannelType() 
    {
        return this.channelType;
    }
    
    public void setChannelType(ChannelType channelType)
    {
        this.channelType = channelType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="STATUS_ID", nullable=false)
    @JsonIgnore
    public ChannelStatus getChannelStatus() 
    {
        return this.channelStatus;
    }
    
    public void setChannelStatus(ChannelStatus channelStatus)
    {
        this.channelStatus = channelStatus;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DATA_CHANNEL_ID", nullable=false)
    @JsonIgnore
    public DataChannel getDataChannel() 
    {
        return this.dataChannel;
    }
    
    public void setDataChannel(DataChannel dataChannel)
    {
        this.dataChannel = dataChannel;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PIPELINE_ID", nullable=false)
    @JsonIgnore
    public ChannelPipeline getPipeline() 
    {
        return this.pipeline;
    }
    
    public void setPipeline(ChannelPipeline channelPipeline)
    {
        this.pipeline = channelPipeline;
    }

	/**
	 * @return the autoStartFg
	 */
    @Column(name="AUTO_START_FG", nullable=false, length=1)
	public Character getAutoStartFg() {
		return autoStartFg;
	}

	/**
	 * @param autoStartFg the autoStartFg to set
	 */
	public void setAutoStartFg(Character autoStartFg) {
		this.autoStartFg = autoStartFg;
	}
    
    

}
