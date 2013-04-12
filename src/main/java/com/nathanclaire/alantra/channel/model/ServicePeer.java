/**
 *  Nathan Claire Group.
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
 * ServicePeer 
 * @author Edward Banfa
 */
@Entity
@Table(name="SERVICE_PEER"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ServicePeer  extends BaseEntity implements java.io.Serializable {

	private Service service;
	private Host host;
    private String name;
    private String description;

    public ServicePeer() {
    }

    public ServicePeer(Service service, Host host, String code, String name, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public ServicePeer(Service service, Host host, String code, String name, String description, Date effectiveDt, char recSt) 
    {
		this.service = service;
		this.host = host;
		this.code = code;
		this.name = name;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SERVICE_ID", nullable=false)
    @JsonIgnore
    public Service getService() 
    {
        return this.service;
    }
    
    public void setService(Service service)
    {
        this.service = service;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="HOST_ID", nullable=false)
    @JsonIgnore
    public Host getHost() 
    {
        return this.host;
    }
    
    public void setHost(Host host)
    {
        this.host = host;
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


}


