/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.advice.model;

import java.math.BigDecimal;
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
import com.nathanclaire.alantra.customer.model.Customer;

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
	private AdviceStatus adviceStatus;
	private AdviceType adviceType;
    private String name;
    private String description;
    private int commEventId;
    private String adviceTxt;
    private String cardNo;
    private String accountNo;
    private String chequeNo;
    private String accountNm;
    private BigDecimal amount;

    public Advice() {
    }

    public Advice(Customer customer, AdviceStatus adviceStatus, AdviceType adviceType, String code, String name, int commEventId, String accountNo, BigDecimal amount, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.name = name;
		this.commEventId = commEventId;
		this.accountNo = accountNo;
		this.amount = amount;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public Advice(Customer customer, AdviceStatus adviceStatus, AdviceType adviceType, String code, String name, String description, int commEventId, String adviceTxt, String cardNo, String accountNo, String chequeNo, String accountNm, BigDecimal amount, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
		this.customer = customer;
		this.adviceStatus = adviceStatus;
		this.adviceType = adviceType;
		this.code = code;
		this.name = name;
		this.description = description;
		this.commEventId = commEventId;
		this.adviceTxt = adviceTxt;
		this.cardNo = cardNo;
		this.accountNo = accountNo;
		this.chequeNo = chequeNo;
		this.accountNm = accountNm;
		this.amount = amount;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
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
		
    @Column(name="COMM_EVENT_ID" , nullable=false)
    public int getCommEventId() 
    {
        return this.commEventId;
    }
    
    public void setCommEventId(int commEventId) 
    {
        this.commEventId = commEventId;
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


}


