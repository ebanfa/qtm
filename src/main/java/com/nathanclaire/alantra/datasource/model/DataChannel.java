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

import com.nathanclaire.alantra.advice.model.AdviceRequestMessage;
import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.channel.model.ServiceTransaction;
import com.nathanclaire.alantra.messaging.model.Message;

/**
 * DataChannel 
 * @author Edward Banfa
 */
@Entity
@Table(name="DATA_CHANNEL"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class DataChannel  extends BaseEntity implements java.io.Serializable {

	private DataChannelStatus dataChannelStatus;
	private DataChannelType dataChannelType;
    private String name;
    private String description;
    private String ipAddr;
    private Integer portNo;
    private String url;
    private String dsDb;
    private String dsTblNm;
    private String username;
    private String password;
    private char inboundOutboundFg;
	private Set<ServiceTransaction> serviceTransactions = new HashSet<ServiceTransaction>(0);
	private Set<Data> datas = new HashSet<Data>(0);
	private Set<AdviceRequestMessage> adviceRequestMessages = new HashSet<AdviceRequestMessage>(0);
	private Set<Message> messages = new HashSet<Message>(0);

    public DataChannel() {
    }

    public DataChannel(DataChannelStatus dataChannelStatus, DataChannelType dataChannelType, String code, String name, char inboundOutboundFg, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.name = name;
		this.inboundOutboundFg = inboundOutboundFg;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public DataChannel(DataChannelStatus dataChannelStatus, DataChannelType dataChannelType, String code, String name, String description, String ipAddr, Integer portNo, String url, String dsDb, String dsTblNm, String username, String password, char inboundOutboundFg, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<ServiceTransaction> serviceTransactions, Set<Data> datas, Set<AdviceRequestMessage> adviceRequestMessages, Set<Message> messages ) 
    {
		this.dataChannelStatus = dataChannelStatus;
		this.dataChannelType = dataChannelType;
		this.code = code;
		this.name = name;
		this.description = description;
		this.ipAddr = ipAddr;
		this.portNo = portNo;
		this.url = url;
		this.dsDb = dsDb;
		this.dsTblNm = dsTblNm;
		this.username = username;
		this.password = password;
		this.inboundOutboundFg = inboundOutboundFg;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
		this.serviceTransactions = serviceTransactions;
		this.datas = datas;
		this.adviceRequestMessages = adviceRequestMessages;
		this.messages = messages;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CHANNEL_STATUS_ID", nullable=false)
    @JsonIgnore
    public DataChannelStatus getDataChannelStatus() 
    {
        return this.dataChannelStatus;
    }
    
    public void setDataChannelStatus(DataChannelStatus dataChannelStatus)
    {
        this.dataChannelStatus = dataChannelStatus;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CHANNEL_TY_ID", nullable=false)
    @JsonIgnore
    public DataChannelType getDataChannelType() 
    {
        return this.dataChannelType;
    }
    
    public void setDataChannelType(DataChannelType dataChannelType)
    {
        this.dataChannelType = dataChannelType;
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
		
    @Column(name="IP_ADDR" , unique=true, length=75)
    public String getIpAddr() 
    {
        return this.ipAddr;
    }
    
    public void setIpAddr(String ipAddr) 
    {
        this.ipAddr = ipAddr;
    }
		
    @Column(name="PORT_NO" , unique=true)
    public Integer getPortNo() 
    {
        return this.portNo;
    }
    
    public void setPortNo(Integer portNo) 
    {
        this.portNo = portNo;
    }
		
    @Column(name="URL" , unique=true, length=150)
    public String getUrl() 
    {
        return this.url;
    }
    
    public void setUrl(String url) 
    {
        this.url = url;
    }
		
    @Column(name="DS_DB" , unique=true, length=75)
    public String getDsDb() 
    {
        return this.dsDb;
    }
    
    public void setDsDb(String dsDb) 
    {
        this.dsDb = dsDb;
    }
		
    @Column(name="DS_TBL_NM" , unique=true, length=75)
    public String getDsTblNm() 
    {
        return this.dsTblNm;
    }
    
    public void setDsTblNm(String dsTblNm) 
    {
        this.dsTblNm = dsTblNm;
    }
		
    @Column(name="USERNAME" , unique=true, length=35)
    public String getUsername() 
    {
        return this.username;
    }
    
    public void setUsername(String username) 
    {
        this.username = username;
    }
		
    @Column(name="PASSWORD" , unique=true, length=35)
    public String getPassword() 
    {
        return this.password;
    }
    
    public void setPassword(String password) 
    {
        this.password = password;
    }
		
    @Column(name="INBOUND_OUTBOUND_FG" , nullable=false, length=1)
    public char getInboundOutboundFg() 
    {
        return this.inboundOutboundFg;
    }
    
    public void setInboundOutboundFg(char inboundOutboundFg) 
    {
        this.inboundOutboundFg = inboundOutboundFg;
    }
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="dataChannel")
    @JsonIgnore
    public Set<ServiceTransaction> getServiceTransactions() 
    {
        return this.serviceTransactions;
    }
    
    public void setServiceTransactions(Set<ServiceTransaction> serviceTransactions) 
    {
        this.serviceTransactions = serviceTransactions;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="dataChannel")
    @JsonIgnore
    public Set<Data> getDatas() 
    {
        return this.datas;
    }
    
    public void setDatas(Set<Data> datas) 
    {
        this.datas = datas;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="dataChannel")
    @JsonIgnore
    public Set<AdviceRequestMessage> getAdviceRequestMessages() 
    {
        return this.adviceRequestMessages;
    }
    
    public void setAdviceRequestMessages(Set<AdviceRequestMessage> adviceRequestMessages) 
    {
        this.adviceRequestMessages = adviceRequestMessages;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="dataChannel")
    @JsonIgnore
    public Set<Message> getMessages() 
    {
        return this.messages;
    }
    
    public void setMessages(Set<Message> messages) 
    {
        this.messages = messages;
    }			


}


