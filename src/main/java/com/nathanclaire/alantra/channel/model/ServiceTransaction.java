/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.channel.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	private Service service;
	private ServiceTransactionType serviceTransactionType;
    private String name;
    private BigDecimal amount;
    private Date txnDate;
    private String accountNo;
    private String accountNm;
    private String description;

    public ServiceTransaction() {
    }

    public ServiceTransaction(Service service, ServiceTransactionType serviceTransactionType, String code, String name, BigDecimal amount, Date txnDate, String accountNo, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.amount = amount;
		this.txnDate = txnDate;
		this.accountNo = accountNo;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public ServiceTransaction(Service service, ServiceTransactionType serviceTransactionType, String code, String name, BigDecimal amount, Date txnDate, String accountNo, String accountNm, String description, Date effectiveDt, char recSt) 
    {
		this.service = service;
		this.serviceTransactionType = serviceTransactionType;
		this.code = code;
		this.name = name;
		this.amount = amount;
		this.txnDate = txnDate;
		this.accountNo = accountNo;
		this.accountNm = accountNm;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
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


}


