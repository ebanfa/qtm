/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.advice.model;

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
 * AdviceChannel 
 * @author Edward Banfa
 */
@Entity
@Table(name="ADVICE_CHANNEL"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class AdviceChannel  extends BaseEntity implements java.io.Serializable {

	private AdviceChannelType adviceChannelType;
    private String ipAddr;
    private int portNo;
    private String username;
    private String password;
    private String description;

    public AdviceChannel() {
    }

    public AdviceChannel(AdviceChannelType adviceChannelType, String ipAddr, int portNo, String username, String password, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.ipAddr = ipAddr;
		this.portNo = portNo;
		this.username = username;
		this.password = password;
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public AdviceChannel(AdviceChannelType adviceChannelType, String ipAddr, int portNo, String username, String password, String description, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
		this.adviceChannelType = adviceChannelType;
		this.ipAddr = ipAddr;
		this.portNo = portNo;
		this.username = username;
		this.password = password;
		this.description = description;
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ADVICE_CHL_TY_ID", nullable=false)
    @JsonIgnore
    public AdviceChannelType getAdviceChannelType() 
    {
        return this.adviceChannelType;
    }
    
    public void setAdviceChannelType(AdviceChannelType adviceChannelType)
    {
        this.adviceChannelType = adviceChannelType;
    }
		
    @Column(name="IP_ADDR" , nullable=false, length=75)
    public String getIpAddr() 
    {
        return this.ipAddr;
    }
    
    public void setIpAddr(String ipAddr) 
    {
        this.ipAddr = ipAddr;
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
		
    @Column(name="USERNAME" , nullable=false, length=35)
    public String getUsername() 
    {
        return this.username;
    }
    
    public void setUsername(String username) 
    {
        this.username = username;
    }
		
    @Column(name="PASSWORD" , nullable=false, length=35)
    public String getPassword() 
    {
        return this.password;
    }
    
    public void setPassword(String password) 
    {
        this.password = password;
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


