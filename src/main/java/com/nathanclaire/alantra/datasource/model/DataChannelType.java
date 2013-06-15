/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.model;

import java.util.Date;
import java.util.HashSet;
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
 * DataChannelType 
 * @author Edward Banfa
 */
@Entity
@Table(name="DATA_CHANNEL_TYPE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class DataChannelType  extends BaseEntity implements java.io.Serializable {

	private DataChannelAdapter dataChannelAdapter;
	private DataChannelCategory dataChannelCategory;
    private String name;
    private String description;
	private Set<DataChannel> dataChannels = new HashSet<DataChannel>(0);

    public DataChannelType() {
    }

    public DataChannelType(DataChannelAdapter dataChannelAdapter, DataChannelCategory dataChannelCategory, String code, String name, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.name = name;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public DataChannelType(DataChannelAdapter dataChannelAdapter, DataChannelCategory dataChannelCategory, String code, String name, String description, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<DataChannel> dataChannels ) 
    {
		this.dataChannelAdapter = dataChannelAdapter;
		this.dataChannelCategory = dataChannelCategory;
		this.code = code;
		this.name = name;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
		this.dataChannels = dataChannels;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ADAPTER_ID", nullable=false)
    @JsonIgnore
    public DataChannelAdapter getDataChannelAdapter() 
    {
        return this.dataChannelAdapter;
    }
    
    public void setDataChannelAdapter(DataChannelAdapter dataChannelAdapter)
    {
        this.dataChannelAdapter = dataChannelAdapter;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CATEGORY_ID", nullable=false)
    @JsonIgnore
    public DataChannelCategory getDataChannelCategory() 
    {
        return this.dataChannelCategory;
    }
    
    public void setDataChannelCategory(DataChannelCategory dataChannelCategory)
    {
        this.dataChannelCategory = dataChannelCategory;
    }
		
    @Column(name="NAME" , nullable=false, length=75)
    public String getName() 
    {
        return this.name;
    }
    
    public void setName(String name) 
    {
        this.name = name;
    }
		
    @Column(name="DESCRIPTION" , unique=true, length=150)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="dataChannelType")
    @JsonIgnore
    public Set<DataChannel> getDataChannels() 
    {
        return this.dataChannels;
    }
    
    public void setDataChannels(Set<DataChannel> dataChannels) 
    {
        this.dataChannels = dataChannels;
    }			


}


