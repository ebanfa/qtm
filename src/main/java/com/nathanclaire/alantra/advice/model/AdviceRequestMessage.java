/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.advice.model;

import java.math.BigDecimal;
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
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.datasource.model.DataChannel;

/**
 * AdviceRequestMessage 
 * @author Edward Banfa
 */
@Entity
@Table(name="ADVICE_REQUEST_MESSAGE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class AdviceRequestMessage  extends BaseEntity implements java.io.Serializable {

	private Customer customer;
	private DataChannel dataChannel;
	private AdviceRequestMessageStatus adviceRequestMessageStatus;
    private String sourceAddress;
    private String name;
    private BigDecimal amount;
    private String accountNo;
    private String chequeNo;
    private String cardNo;
    private String currencyCd;
    private String adviceTyTxt;
    private String adviceTxt;
    private String description;
	private Set<Advice> advices = new HashSet<Advice>(0);

    public AdviceRequestMessage() {
    }

    public AdviceRequestMessage(Customer customer, DataChannel dataChannel, AdviceRequestMessageStatus adviceRequestMessageStatus, String sourceAddress, String code, String name, BigDecimal amount, String accountNo, String currencyCd, String adviceTyTxt, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.sourceAddress = sourceAddress;
		this.code = code;
		this.name = name;
		this.amount = amount;
		this.accountNo = accountNo;
		this.currencyCd = currencyCd;
		this.adviceTyTxt = adviceTyTxt;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public AdviceRequestMessage(Customer customer, DataChannel dataChannel, AdviceRequestMessageStatus adviceRequestMessageStatus, String sourceAddress, String code, String name, BigDecimal amount, String accountNo, String chequeNo, String cardNo, String currencyCd, String adviceTyTxt, String adviceTxt, String description, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<Advice> advices ) 
    {
		this.customer = customer;
		this.dataChannel = dataChannel;
		this.adviceRequestMessageStatus = adviceRequestMessageStatus;
		this.sourceAddress = sourceAddress;
		this.code = code;
		this.name = name;
		this.amount = amount;
		this.accountNo = accountNo;
		this.chequeNo = chequeNo;
		this.cardNo = cardNo;
		this.currencyCd = currencyCd;
		this.adviceTyTxt = adviceTyTxt;
		this.adviceTxt = adviceTxt;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
		this.advices = advices;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CUSTOMER_ID", nullable=false)
    @JsonIgnore
    public Customer getCustomer() 
    {
        return this.customer;
    }
    
    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CHANNEL_ID", nullable=false)
    @JsonIgnore
    public DataChannel getDataChannel() 
    {
        return this.dataChannel;
    }
    
    public void setDataChannel(DataChannel dataChannel)
    {
        this.dataChannel = dataChannel;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="STATUS_ID", nullable=false)
    @JsonIgnore
    public AdviceRequestMessageStatus getAdviceRequestMessageStatus() 
    {
        return this.adviceRequestMessageStatus;
    }
    
    public void setAdviceRequestMessageStatus(AdviceRequestMessageStatus adviceRequestMessageStatus)
    {
        this.adviceRequestMessageStatus = adviceRequestMessageStatus;
    }
		
    @Column(name="SOURCE_ADDRESS" , nullable=false, length=75)
    public String getSourceAddress() 
    {
        return this.sourceAddress;
    }
    
    public void setSourceAddress(String sourceAddress) 
    {
        this.sourceAddress = sourceAddress;
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
		
    @Column(name="AMOUNT" , nullable=false)
    public BigDecimal getAmount() 
    {
        return this.amount;
    }
    
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }
		
    @Column(name="ACCOUNT_NO" , nullable=false, length=35)
    public String getAccountNo() 
    {
        return this.accountNo;
    }
    
    public void setAccountNo(String accountNo) 
    {
        this.accountNo = accountNo;
    }
		
    @Column(name="CHEQUE_NO" , unique=true, length=35)
    public String getChequeNo() 
    {
        return this.chequeNo;
    }
    
    public void setChequeNo(String chequeNo) 
    {
        this.chequeNo = chequeNo;
    }
		
    @Column(name="CARD_NO" , unique=true, length=15)
    public String getCardNo() 
    {
        return this.cardNo;
    }
    
    public void setCardNo(String cardNo) 
    {
        this.cardNo = cardNo;
    }
		
    @Column(name="CURRENCY_CD" , nullable=false, length=15)
    public String getCurrencyCd() 
    {
        return this.currencyCd;
    }
    
    public void setCurrencyCd(String currencyCd) 
    {
        this.currencyCd = currencyCd;
    }
		
    @Column(name="ADVICE_TY_TXT" , nullable=false, length=75)
    public String getAdviceTyTxt() 
    {
        return this.adviceTyTxt;
    }
    
    public void setAdviceTyTxt(String adviceTyTxt) 
    {
        this.adviceTyTxt = adviceTyTxt;
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
		
    @Column(name="DESCRIPTION" , unique=true, length=150)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="adviceRequestMessage")
    @JsonIgnore
    public Set<Advice> getAdvices() 
    {
        return this.advices;
    }
    
    public void setAdvices(Set<Advice> advices) 
    {
        this.advices = advices;
    }			


}


