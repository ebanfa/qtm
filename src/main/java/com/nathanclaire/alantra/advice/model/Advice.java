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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.base.util.DateDeserializer;
import com.nathanclaire.alantra.base.util.DateSerializer;
import com.nathanclaire.alantra.businessdata.model.Currency;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.model.CustomerAccount;
import com.nathanclaire.alantra.messaging.model.Message;

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

	private Customer customer;
	private Message message;
	private Currency currency;
	private AdviceStatus adviceStatus;
	private AdviceClassification adviceClassification;
	private CustomerAccount customerAccount;
	private AdviceType adviceType;
    private String name;
    private String description;
    private String adviceTxt;
    private String cardNo;
    private String chequeNo;
    private String accountNm;
    private BigDecimal amount;
    private Date startDt;
    private Date endDt;
    private int maxMatches;
    private int matchCount;
	private Set<AdvicedTransaction> advicedTransactions = new HashSet<AdvicedTransaction>(0);

    public Advice() {
    }

    public Advice(Customer customer, Message message, Currency currency, AdviceStatus adviceStatus, AdviceClassification adviceClassification, AdviceType adviceType, String code, String name, BigDecimal amount, int maxMatches, int matchCount, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.name = name;
		this.amount = amount;
		this.maxMatches = maxMatches;
		this.matchCount = matchCount;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public Advice(Customer customer, Message message, Currency currency, AdviceStatus adviceStatus, AdviceClassification adviceClassification, CustomerAccount customerAccount, AdviceType adviceType, String code, String name, String description, String adviceTxt, String cardNo, String chequeNo, String accountNm, BigDecimal amount, Date startDt, Date endDt, int maxMatches, int matchCount, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<AdvicedTransaction> advicedTransactions ) 
    {
		this.customer = customer;
		this.message = message;
		this.currency = currency;
		this.adviceStatus = adviceStatus;
		this.adviceClassification = adviceClassification;
		this.customerAccount = customerAccount;
		this.adviceType = adviceType;
		this.code = code;
		this.name = name;
		this.description = description;
		this.adviceTxt = adviceTxt;
		this.cardNo = cardNo;
		this.chequeNo = chequeNo;
		this.accountNm = accountNm;
		this.amount = amount;
		this.startDt = startDt;
		this.endDt = endDt;
		this.maxMatches = maxMatches;
		this.matchCount = matchCount;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
		this.advicedTransactions = advicedTransactions;
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
    @JoinColumn(name="MESSAGE_ID", nullable=false)
    @JsonIgnore
    public Message getMessage() 
    {
        return this.message;
    }
    
    public void setMessage(Message message)
    {
        this.message = message;
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
    @JoinColumn(name="ADVICE_CLASS_ID", nullable=false)
    @JsonIgnore
    public AdviceClassification getAdviceClassification() 
    {
        return this.adviceClassification;
    }
    
    public void setAdviceClassification(AdviceClassification adviceClassification)
    {
        this.adviceClassification = adviceClassification;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ACCOUNT_ID")
    @JsonIgnore
    public CustomerAccount getCustomerAccount() 
    {
        return this.customerAccount;
    }
    
    public void setCustomerAccount(CustomerAccount customerAccount)
    {
        this.customerAccount = customerAccount;
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
		
    @Column(name="CARD_NO" , unique=true, length=35)
    public String getCardNo() 
    {
        return this.cardNo;
    }
    
    public void setCardNo(String cardNo) 
    {
        this.cardNo = cardNo;
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
		
    @Column(name="ACCOUNT_NM" , unique=true, length=75)
    public String getAccountNm() 
    {
        return this.accountNm;
    }
    
    public void setAccountNm(String accountNm) 
    {
        this.accountNm = accountNm;
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
    @Column(name="START_DT" , length=10)
	@JsonSerialize(using = DateSerializer.class)
    public Date getStartDt() 
    {
        return this.startDt;
    }
    
    @JsonDeserialize(using = DateDeserializer.class)
    public void setStartDt(Date startDt) 
    {
        this.startDt = startDt;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="END_DT" , length=10)
	@JsonSerialize(using = DateSerializer.class)
    public Date getEndDt() 
    {
        return this.endDt;
    }
    
    @JsonDeserialize(using = DateDeserializer.class)
    public void setEndDt(Date endDt) 
    {
        this.endDt = endDt;
    }
		
    @Column(name="MAX_MATCHES" , nullable=false)
    public int getMaxMatches() 
    {
        return this.maxMatches;
    }
    
    public void setMaxMatches(int maxMatches) 
    {
        this.maxMatches = maxMatches;
    }
		
    @Column(name="MATCH_COUNT" , nullable=false)
    public int getMatchCount() 
    {
        return this.matchCount;
    }
    
    public void setMatchCount(int matchCount) 
    {
        this.matchCount = matchCount;
    }
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="advice")
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


