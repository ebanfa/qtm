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

import com.nathanclaire.alantra.base.BaseEntity;
import com.nathanclaire.alantra.messaging.model.CommunicationEvent;
import com.nathanclaire.alantra.party.model.Party;

/**
 * Advice 
 * @author Edward Banfa
 */
@Entity
@Table(name="ADVICE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Advice  extends BaseEntity implements java.io.Serializable {

	private Party party;
	private AdviceStatus adviceStatus;
	private CommunicationEvent communicationEvent;
	private AdviceType adviceType;
    private String name;
    private String description;
    private String adviceTxt;
    private String accountNo;

    public Advice() {
    }

    public Advice(Party party, AdviceStatus adviceStatus, CommunicationEvent communicationEvent, AdviceType adviceType, String code, String name, String accountNo, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.accountNo = accountNo;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public Advice(Party party, AdviceStatus adviceStatus, CommunicationEvent communicationEvent, AdviceType adviceType, String code, String name, String description, String adviceTxt, String accountNo, Date effectiveDt, char recSt) 
    {
		this.party = party;
		this.adviceStatus = adviceStatus;
		this.communicationEvent = communicationEvent;
		this.adviceType = adviceType;
		this.code = code;
		this.name = name;
		this.description = description;
		this.adviceTxt = adviceTxt;
		this.accountNo = accountNo;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CUSTOMER_ID", nullable=false)
    @JsonIgnore
    public Party getParty() 
    {
        return this.party;
    }
    
    public void setParty(Party party)
    {
        this.party = party;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ADVICE_ST_ID", nullable=false)
    @JsonIgnore
    public AdviceStatus getAdviceStatus() 
    {
        return this.adviceStatus;
    }
    
    public void setAdviceStatus(AdviceStatus adviceStatus)
    {
        this.adviceStatus = adviceStatus;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="COMM_EVENT_ID", nullable=false)
    @JsonIgnore
    public CommunicationEvent getCommunicationEvent() 
    {
        return this.communicationEvent;
    }
    
    public void setCommunicationEvent(CommunicationEvent communicationEvent)
    {
        this.communicationEvent = communicationEvent;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ADVICE_TY_ID", nullable=false)
    @JsonIgnore
    public AdviceType getAdviceType() 
    {
        return this.adviceType;
    }
    
    public void setAdviceType(AdviceType adviceType)
    {
        this.adviceType = adviceType;
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
		
    @Column(name="ADVICE_TXT" , unique=true, length=500)
    public String getAdviceTxt() 
    {
        return this.adviceTxt;
    }
    
    public void setAdviceTxt(String adviceTxt) 
    {
        this.adviceTxt = adviceTxt;
    }
		
    @Column(name="ACCOUNT_NO" , nullable=false, length=75)
    public String getAccountNo() 
    {
        return this.accountNo;
    }
    
    public void setAccountNo(String accountNo) 
    {
        this.accountNo = accountNo;
    }


}


