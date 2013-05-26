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
 * Service 
 * @author Edward Banfa
 */
@Entity
@Table(name="SERVICE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Service  extends BaseEntity implements java.io.Serializable {

	private ServiceType serviceType;
	private ServiceProtocolAdapter serviceProtocolAdapter;
	private ServiceMode serviceMode;
	private ServiceCategory serviceCategory;
    private String name;
    private String description;
    private int portNo;
    private String ipAddress;
	private Set<ServiceTransaction> serviceTransactions = new HashSet<ServiceTransaction>(0);
	private Set<ServicePeer> servicePeers = new HashSet<ServicePeer>(0);

    public Service() {
    }

    public Service(ServiceType serviceType, ServiceProtocolAdapter serviceProtocolAdapter, ServiceMode serviceMode, ServiceCategory serviceCategory, String name, int portNo, String ipAddress, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.name = name;
		this.portNo = portNo;
		this.ipAddress = ipAddress;
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public Service(ServiceType serviceType, ServiceProtocolAdapter serviceProtocolAdapter, ServiceMode serviceMode, ServiceCategory serviceCategory, String name, String description, int portNo, String ipAddress, Set<ServiceTransaction> serviceTransactions, Set<ServicePeer> servicePeers, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
		this.serviceType = serviceType;
		this.serviceProtocolAdapter = serviceProtocolAdapter;
		this.serviceMode = serviceMode;
		this.serviceCategory = serviceCategory;
		this.name = name;
		this.description = description;
		this.portNo = portNo;
		this.ipAddress = ipAddress;
		this.serviceTransactions = serviceTransactions;
		this.servicePeers = servicePeers;
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SERVICE_TY_ID", nullable=false)
    @JsonIgnore
    public ServiceType getServiceType() 
    {
        return this.serviceType;
    }
    
    public void setServiceType(ServiceType serviceType)
    {
        this.serviceType = serviceType;
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
    @JoinColumn(name="MODE_ID", nullable=false)
    @JsonIgnore
    public ServiceMode getServiceMode() 
    {
        return this.serviceMode;
    }
    
    public void setServiceMode(ServiceMode serviceMode)
    {
        this.serviceMode = serviceMode;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SERVICE_CATEGORY_ID", nullable=false)
    @JsonIgnore
    public ServiceCategory getServiceCategory() 
    {
        return this.serviceCategory;
    }
    
    public void setServiceCategory(ServiceCategory serviceCategory)
    {
        this.serviceCategory = serviceCategory;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="service")
    @JsonIgnore
    public Set<ServiceTransaction> getServiceTransactions() 
    {
        return this.serviceTransactions;
    }
    
    public void setServiceTransactions(Set<ServiceTransaction> serviceTransactions) 
    {
        this.serviceTransactions = serviceTransactions;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="service")
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


