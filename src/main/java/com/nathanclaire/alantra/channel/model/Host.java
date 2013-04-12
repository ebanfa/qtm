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
 * Host 
 * @author Edward Banfa
 */
@Entity
@Table(name="HOST"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Host  extends BaseEntity implements java.io.Serializable {

	private ServiceProtocolAdapter serviceProtocolAdapter;
	private HostType hostType;
    private String name;
    private String description;
    private int portNo;
    private String ipAddress;
	private Set<ServicePeer> servicePeers = new HashSet<ServicePeer>(0);

    public Host() {
    }

    public Host(ServiceProtocolAdapter serviceProtocolAdapter, HostType hostType, String code, String name, int portNo, String ipAddress, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.portNo = portNo;
		this.ipAddress = ipAddress;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public Host(ServiceProtocolAdapter serviceProtocolAdapter, HostType hostType, String code, String name, String description, int portNo, String ipAddress, Date effectiveDt, char recSt, Set<ServicePeer> servicePeers ) 
    {
		this.serviceProtocolAdapter = serviceProtocolAdapter;
		this.hostType = hostType;
		this.code = code;
		this.name = name;
		this.description = description;
		this.portNo = portNo;
		this.ipAddress = ipAddress;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.servicePeers = servicePeers;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ADAPTER_ID", nullable=false)
    @JsonIgnore
    public ServiceProtocolAdapter getServiceProtocolAdapter() 
    {
        return this.serviceProtocolAdapter;
    }
    
    public void setServiceProtocolAdapter(ServiceProtocolAdapter serviceProtocolAdapter)
    {
        this.serviceProtocolAdapter = serviceProtocolAdapter;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="HOST_TY_ID", nullable=false)
    @JsonIgnore
    public HostType getHostType() 
    {
        return this.hostType;
    }
    
    public void setHostType(HostType hostType)
    {
        this.hostType = hostType;
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
		
    @Column(name="PORT_NO" , nullable=false)
    public int getPortNo() 
    {
        return this.portNo;
    }
    
    public void setPortNo(int portNo) 
    {
        this.portNo = portNo;
    }
		
    @Column(name="IP_ADDRESS" , nullable=false, length=15)
    public String getIpAddress() 
    {
        return this.ipAddress;
    }
    
    public void setIpAddress(String ipAddress) 
    {
        this.ipAddress = ipAddress;
    }
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="host")
    @JsonIgnore
    public Set<ServicePeer> getServicePeers() 
    {
        return this.servicePeers;
    }
    
    public void setServicePeers(Set<ServicePeer> servicePeers) 
    {
        this.servicePeers = servicePeers;
    }			


}


