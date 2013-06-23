/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.customer.model;

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
 * CustomerLimit 
 * @author Edward Banfa
 */
@Entity
@Table(name="CUSTOMER_LIMIT"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CustomerLimit  extends BaseEntity implements java.io.Serializable {

	private Customer customer;
	private LimitType limitType;
    private String name;
    private BigDecimal amount;
    private Date startDt;
    private Date endDt;
    private String description;

    public CustomerLimit() {
    }

    public CustomerLimit(Customer customer, LimitType limitType, String code, String name, BigDecimal amount, Date startDt, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.name = name;
		this.amount = amount;
		this.startDt = startDt;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public CustomerLimit(Customer customer, LimitType limitType, String code, String name, BigDecimal amount, Date startDt, Date endDt, String description, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
		this.customer = customer;
		this.limitType = limitType;
		this.code = code;
		this.name = name;
		this.amount = amount;
		this.startDt = startDt;
		this.endDt = endDt;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CUST_ID", nullable=false)
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
    @JoinColumn(name="LIMIT_TY_ID", nullable=false)
    @JsonIgnore
    public LimitType getLimitType() 
    {
        return this.limitType;
    }
    
    public void setLimitType(LimitType limitType)
    {
        this.limitType = limitType;
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
    @Column(name="START_DT" , nullable=false, length=10)
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
		
    @Column(name="DESCRIPTION" , unique=true, length=200)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }


}


