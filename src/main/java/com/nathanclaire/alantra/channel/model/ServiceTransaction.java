/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.channel.model;


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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.advice.model.AdvicedTransaction;
import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.base.util.DateDeserializer;
import com.nathanclaire.alantra.base.util.DateSerializer;
import com.nathanclaire.alantra.businessdata.model.Currency;

/**
 * ServiceTransaction 
 * @author Edward Banfa
 */
@Entity
@Table(name="SERVICE_TRANSACTION"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ServiceTransaction  extends BaseEntity implements java.io.Serializable {

    private Currency currency;
	private Service service;
	private ServiceTransactionStatus serviceTransactionStatus;
	private ServiceTransactionType serviceTransactionType;
    private String name;
    private BigDecimal amount;
    private Date txnDate;
    private String accountNo;
    private String chequeNo;
    private String cardNo;
    private String accountNm;
    private String description;
	private Set<AdvicedTransaction> advicedTransactions = new HashSet<AdvicedTransaction>(0);

    public ServiceTransaction() {
    }

    public ServiceTransaction(Currency currency, Service service, ServiceTransactionStatus serviceTransactionStatus, ServiceTransactionType serviceTransactionType, String name, BigDecimal amount, Date txnDate, String accountNo, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.currency = currency;
		this.name = name;
		this.amount = amount;
		this.txnDate = txnDate;
		this.accountNo = accountNo;
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public ServiceTransaction(Currency currency, Service service, ServiceTransactionStatus serviceTransactionStatus, ServiceTransactionType serviceTransactionType, String name, BigDecimal amount, Date txnDate, String accountNo, String chequeNo, String cardNo, String accountNm, String description, Set<AdvicedTransaction> advicedTransactions, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
		this.currency = currency;
		this.service = service;
		this.serviceTransactionStatus = serviceTransactionStatus;
		this.serviceTransactionType = serviceTransactionType;
		this.name = name;
		this.amount = amount;
		this.txnDate = txnDate;
		this.accountNo = accountNo;
		this.chequeNo = chequeNo;
		this.cardNo = cardNo;
		this.accountNm = accountNm;
		this.description = description;
		this.advicedTransactions = advicedTransactions;
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CURRENCY_ID", nullable=false)
    @JsonIgnore
    public Currency getCurrency() 
    {
        return this.currency;
    }
    
    public void setCurrency(Currency currency) 
    {
        this.currency = currency;
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
    @JoinColumn(name="TXN_STATUS_ID", nullable=false)
    @JsonIgnore
    public ServiceTransactionStatus getServiceTransactionStatus() 
    {
        return this.serviceTransactionStatus;
    }
    
    public void setServiceTransactionStatus(ServiceTransactionStatus serviceTransactionStatus)
    {
        this.serviceTransactionStatus = serviceTransactionStatus;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TXN_TY_ID", nullable=false)
    @JsonIgnore
    public ServiceTransactionType getServiceTransactionType() 
    {
        return this.serviceTransactionType;
    }
    
    public void setServiceTransactionType(ServiceTransactionType serviceTransactionType)
    {
        this.serviceTransactionType = serviceTransactionType;
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
    @Temporal(TemporalType.DATE)
    @Column(name="TXN_DATE" , nullable=false, length=10)
	@JsonSerialize(using = DateSerializer.class)
    public Date getTxnDate() 
    {
        return this.txnDate;
    }
    
    @JsonDeserialize(using = DateDeserializer.class)
    public void setTxnDate(Date txnDate) 
    {
        this.txnDate = txnDate;
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
		
    @Column(name="CHEQUE_NO" , unique=true, length=15)
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
		
    @Column(name="ACCOUNT_NM" , unique=true, length=75)
    public String getAccountNm() 
    {
        return this.accountNm;
    }
    
    public void setAccountNm(String accountNm) 
    {
        this.accountNm = accountNm;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="serviceTransaction")
    @JsonIgnore
    public Set<AdvicedTransaction> getAdvicedTransactions() 
    {
        return this.advicedTransactions;
    }
    
    public void setAdvicedTransactions(Set<AdvicedTransaction> advicedTransactions) 
    {
        this.advicedTransactions = advicedTransactions;
    }			


}


