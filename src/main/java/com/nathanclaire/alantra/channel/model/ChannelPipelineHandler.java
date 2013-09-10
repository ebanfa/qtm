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

/**
 * ChannelPipelineHandler 
 * @author Edward Banfa
 */
@Entity
@Table(name="CHANNEL_PIPELINE_HANDLER"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ChannelPipelineHandler  extends BaseEntity implements java.io.Serializable {
	private String name;
	private String description;
	private Integer sequenceNo;
	private ChannelHandler channelHandler;
	private ChannelPipeline channelPipeline;

    public ChannelPipelineHandler() {
    }

    public ChannelPipelineHandler(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public ChannelPipelineHandler(String name, String description, ChannelHandler channelHandler, ChannelPipeline channelPipeline, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.name = name;
        this.description = description;
        this.channelHandler = channelHandler;
        this.channelPipeline = channelPipeline;
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
    @JoinColumn(name="HANDLER_ID")
    @JsonIgnore
    public ChannelHandler getChannelHandler() 
    {
        return this.channelHandler;
    }
    
    public void setChannelHandler(ChannelHandler channelHandler)
    {
        this.channelHandler = channelHandler;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PIPELINE_ID")
    @JsonIgnore
    public ChannelPipeline getChannelPipeline() 
    {
        return this.channelPipeline;
    }
    
    public void setChannelPipeline(ChannelPipeline channelPipeline)
    {
        this.channelPipeline = channelPipeline;
    }

	/**
	 * @return the sequenceNo
	 */
    @Column(name="SEQUENCE_NO")
	public Integer getSequenceNo() {
		return sequenceNo;
	}

	/**
	 * @param sequenceNo the sequenceNo to set
	 */
	public void setSequenceNo(Integer sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
    
    

}
