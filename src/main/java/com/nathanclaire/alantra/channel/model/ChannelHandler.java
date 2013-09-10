/**
 *  Alantra.
 */
package com.nathanclaire.alantra.channel.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.base.model.BaseEntity;

/**
 * ChannelHandler 
 * @author Edward Banfa
 */
@Entity
@Table(name="CHANNEL_HANDLER"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ChannelHandler  extends BaseEntity implements java.io.Serializable {
	private String name;
	private String description;
	private String className;
	private ChannelHandler parentChannelHandler;
	private ChannelHandlerType channelHandlerType;
	private Set<ChannelHandler> parentChannelHandlers;
	private Set<ChannelPipelineHandler> channelPipelineHandlers;

    public ChannelHandler() {
    }

    public ChannelHandler(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public ChannelHandler(String name, String description, ChannelHandler parentChannelHandler, ChannelHandlerType channelHandlerType, Set parentChannelHandlers, Set channelPipelineHandlers, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.name = name;
        this.description = description;
        this.parentChannelHandler = parentChannelHandler;
        this.channelHandlerType = channelHandlerType;
        this.parentChannelHandlers = parentChannelHandlers;
        this.channelPipelineHandlers = channelPipelineHandlers;
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
    @JoinColumn(name="PARENT_HANDLER_ID")
    @JsonIgnore
    public ChannelHandler getParentChannelHandler() 
    {
        return this.parentChannelHandler;
    }
    
    public void setParentChannelHandler(ChannelHandler parentChannelHandler)
    {
        this.parentChannelHandler = parentChannelHandler;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="HANDLER_TY_ID")
    @JsonIgnore
    public ChannelHandlerType getChannelHandlerType() 
    {
        return this.channelHandlerType;
    }
    
    public void setChannelHandlerType(ChannelHandlerType channelHandlerType)
    {
        this.channelHandlerType = channelHandlerType;
    }
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="parentChannelHandler")
    @JsonIgnore
    public Set<ChannelHandler> getParentChannelHandlers() 
    {
        return this.parentChannelHandlers;
    }
    
    public void setParentChannelHandlers(Set<ChannelHandler> parentChannelHandlers) 
    {
        this.parentChannelHandlers = parentChannelHandlers;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="channelHandler")
    @JsonIgnore
    public Set<ChannelPipelineHandler> getChannelPipelineHandlers() 
    {
        return this.channelPipelineHandlers;
    }
    
    public void setChannelPipelineHandlers(Set<ChannelPipelineHandler> channelPipelineHandlers) 
    {
        this.channelPipelineHandlers = channelPipelineHandlers;
    }

	/**
	 * @return the className
	 */
    @Column(name="CLASS_NAME", nullable=false, length=255)
	public String getClassName() {
		return className;
	}

	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}			

}
