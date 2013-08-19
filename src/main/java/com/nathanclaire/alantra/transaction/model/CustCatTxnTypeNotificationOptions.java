/**
 *  Alantra.
 */
package com.nathanclaire.alantra.transaction.model;

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
import com.nathanclaire.alantra.customer.model.CustomerCategory;

/**
 * CustCatTxnTypeNotificationOptions 
 * @author Edward Banfa
 */
@Entity
@Table(name="CUST_CAT_TXN_TYPE_NOTIFICATION_OPTIONS"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CustCatTxnTypeNotificationOptions  extends BaseEntity implements java.io.Serializable {
	private String description;
	private CustomerCategory customerCategory;
	private ServiceTransactionType transactionType;
	private Character emailFg;
	private BigDecimal emailAmount;
	private Character smsFg;
	private BigDecimal smsAmount;
	private Character ivrFg;
	private BigDecimal ivrAmount;

    public CustCatTxnTypeNotificationOptions() {
    }

    public CustCatTxnTypeNotificationOptions(BigDecimal emailAmount, BigDecimal smsAmount, BigDecimal ivrAmount, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.emailAmount = emailAmount;
        this.smsAmount = smsAmount;
        this.ivrAmount = ivrAmount;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public CustCatTxnTypeNotificationOptions(String description, CustomerCategory customerCategory, ServiceTransactionType transactionType, Character emailFg, BigDecimal emailAmount, Character smsFg, BigDecimal smsAmount, Character ivrFg, BigDecimal ivrAmount, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.description = description;
        this.customerCategory = customerCategory;
        this.transactionType = transactionType;
        this.emailFg = emailFg;
        this.emailAmount = emailAmount;
        this.smsFg = smsFg;
        this.smsAmount = smsAmount;
        this.ivrFg = ivrFg;
        this.ivrAmount = ivrAmount;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.versionNo = versionNo;
        this.rowTs = rowTs;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
        this.lastModifiedDt = lastModifiedDt;
        this.lastModifiedUsr = lastModifiedUsr;
    }
    
    @Column(name="DESCRIPTION", length=150)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }
	
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CUST_CAT_ID")
    @JsonIgnore
    public CustomerCategory getCustomerCategory() 
    {
        return this.customerCategory;
    }
    
    public void setCustomerCategory(CustomerCategory customerCategory)
    {
        this.customerCategory = customerCategory;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TRANSACTION_TY_ID")
    @JsonIgnore
    public ServiceTransactionType getTransactionType() 
    {
        return this.transactionType;
    }
    
    public void setTransactionType(ServiceTransactionType transactionType)
    {
        this.transactionType = transactionType;
    }
    
    @Column(name="EMAIL_FG", length=1)
    public Character getEmailFg() 
    {
        return this.emailFg;
    }
    
    public void setEmailFg(Character emailFg) 
    {
        this.emailFg = emailFg;
    }
	
    @Column(name="EMAIL_AMT", nullable=false)
    public BigDecimal getEmailAmount() 
    {
        return this.emailAmount;
    }
    
    public void setEmailAmount(BigDecimal emailAmount) 
    {
        this.emailAmount = emailAmount;
    }
	
    @Column(name="SMS_FG", length=1)
    public Character getSmsFg() 
    {
        return this.smsFg;
    }
    
    public void setSmsFg(Character smsFg) 
    {
        this.smsFg = smsFg;
    }
	
    @Column(name="SMS_AMT", nullable=false)
    public BigDecimal getSmsAmount() 
    {
        return this.smsAmount;
    }
    
    public void setSmsAmount(BigDecimal smsAmount) 
    {
        this.smsAmount = smsAmount;
    }
	
    @Column(name="IVR_FG", length=1)
    public Character getIvrFg() 
    {
        return this.ivrFg;
    }
    
    public void setIvrFg(Character ivrFg) 
    {
        this.ivrFg = ivrFg;
    }
	
    @Column(name="IVR_AMT", nullable=false)
    public BigDecimal getIvrAmount() 
    {
        return this.ivrAmount;
    }
    
    public void setIvrAmount(BigDecimal ivrAmount) 
    {
        this.ivrAmount = ivrAmount;
    }
	
    

}