/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.channel.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.base.model.BaseEntity;

/**
 * ServiceProtocolAdapter 
 * @author Edward Banfa
 */
@Entity
@Table(name="SERVICE_PROTOCOL_ADAPTER"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ServiceProtocolAdapter  extends BaseEntity implements java.io.Serializable {

    private String name;
    private String description;
    private String className;
	private Set<Host> hosts = new HashSet<Host>(0);
	private Set<Service> services = new HashSet<Service>(0);

    public ServiceProtocolAdapter() {
    }

    public ServiceProtocolAdapter(String code, String name, String className, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.className = className;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public ServiceProtocolAdapter(String code, String name, String description, String className, Date effectiveDt, char recSt, Set<Host> hosts, Set<Service> services ) 
    {
		this.code = code;
		this.name = name;
		this.description = description;
		this.className = className;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.hosts = hosts;
		this.services = services;
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
		
    @Column(name="CLASS_NAME" , nullable=false, length=75)
    public String getClassName() 
    {
        return this.className;
    }
    
    public void setClassName(String className) 
    {
        this.className = className;
    }
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="serviceProtocolAdapter")
    @JsonIgnore
    public Set<Host> getHosts() 
    {
        return this.hosts;
    }
    
    public void setHosts(Set<Host> hosts) 
    {
        this.hosts = hosts;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="serviceProtocolAdapter")
    @JsonIgnore
    public Set<Service> getServices() 
    {
        return this.services;
    }
    
    public void setServices(Set<Service> services) 
    {
        this.services = services;
    }			


}


