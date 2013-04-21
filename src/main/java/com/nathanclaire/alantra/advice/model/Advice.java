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
import com.nathanclaire.alantra.channel.model.ServiceTransactionType;
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

    private String name;
    private String cardNo;
    private String amount;
    private String chequeNo;
    private String adviceTxt;
    private String accountNo;
    private String accountNm;
    private String description;
    //Related entities
	private Party party;
	private AdviceType adviceType;
	private AdviceStatus adviceStatus;
	private CommunicationEvent communicationEvent;
    private ServiceTransactionType transactionType;

    public Advice() {
    }

    public Advice(Party party, AdviceStatus adviceStatus, CommunicationEvent communicationEvent, AdviceType adviceType, String name, String accountNo, String code, Date effectiveDt, char recSt) 
    {
		this.name = name;
		this.accountNo = accountNo;
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public Advice(Party party, AdviceStatus adviceStatus, CommunicationEvent communicationEvent, AdviceType adviceType, String name, String description, String adviceTxt, String accountNo, String code, Date effectiveDt, char recSt) 
    {
		this.party = party;
		this.adviceStatus = adviceStatus;
		this.communicationEvent = communicationEvent;
		this.adviceType = adviceType;
		this.name = name;
		this.description = description;
		this.adviceTxt = adviceTxt;
		this.accountNo = accountNo;
		this.code = code;
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

	/**
	 * @return the cardNo
	 */
    @Column(name="CARD_NO" , length=35)
	public String getCardNo() {
		return cardNo;
	}

	/**
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * @return the amount
	 */
    @Column(name="AMOUNT" , nullable=false)
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @return the accountNm
	 */
    @Column(name="ACCOUNT_NM" , nullable=false, length=75)
	public String getAccountNm() {
		return accountNm;
	}

	/**
	 * @param accountNm the accountNm to set
	 */
	public void setAccountNm(String accountNm) {
		this.accountNm = accountNm;
	}

	/**
	 * @return the transactionType
	 */
	public ServiceTransactionType getTransactionType() {
		return transactionType;
	}

	/**
	 * @param transactionType the transactionType to set
	 */
	public void setTransactionType(ServiceTransactionType transactionType) {
		this.transactionType = transactionType;
	}

	/**
	 * @return the chequeNo
	 */
    @Column(name="CHEQUE_NO", length=35)
	public String getChequeNo() {
		return chequeNo;
	}

	/**
	 * @param chequeNo the chequeNo to set
	 */
	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}


}


