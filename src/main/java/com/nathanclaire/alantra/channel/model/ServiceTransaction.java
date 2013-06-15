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
import com.nathanclaire.alantra.customer.model.CustomerAccount;
import com.nathanclaire.alantra.datasource.model.DataChannel;

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
	private ServiceTransactionStatus serviceTransactionStatus;
	private DataChannel dataChannel;
	private ServiceTransactionType serviceTransactionType;
	private CustomerAccount customerAccount;
    private String name;
    private BigDecimal amount;
    private Date txnDate;
    private String chequeNo;
    private String cardNo;
    private String accountNm;
    private String description;
	private Set<AdvicedTransaction> advicedTransactions = new HashSet<AdvicedTransaction>(0);

    public ServiceTransaction() {
    }

    public ServiceTransaction(Currency currency, ServiceTransactionStatus serviceTransactionStatus, DataChannel dataChannel, ServiceTransactionType serviceTransactionType, CustomerAccount customerAccount, String code, String name, BigDecimal amount, Date txnDate, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.name = name;
		this.amount = amount;
		this.txnDate = txnDate;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public ServiceTransaction(Currency currency, ServiceTransactionStatus serviceTransactionStatus, DataChannel dataChannel, ServiceTransactionType serviceTransactionType, CustomerAccount customerAccount, String code, String name, BigDecimal amount, Date txnDate, String chequeNo, String cardNo, String accountNm, String description, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<AdvicedTransaction> advicedTransactions ) 
    {
		this.currency = currency;
		this.serviceTransactionStatus = serviceTransactionStatus;
		this.dataChannel = dataChannel;
		this.serviceTransactionType = serviceTransactionType;
		this.customerAccount = customerAccount;
		this.code = code;
		this.name = name;
		this.amount = amount;
		this.txnDate = txnDate;
		this.chequeNo = chequeNo;
		this.cardNo = cardNo;
		this.accountNm = accountNm;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
		this.advicedTransactions = advicedTransactions;
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
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ACCOUNT_ID", nullable=false)
    @JsonIgnore
    public CustomerAccount getCustomerAccount() 
    {
        return this.customerAccount;
    }
    
    public void setCustomerAccount(CustomerAccount customerAccount)
    {
        this.customerAccount = customerAccount;
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


