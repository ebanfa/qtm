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
import com.nathanclaire.alantra.channel.model.ServiceTransaction;

/**
 * AdvicedTransaction 
 * @author Edward Banfa
 */
@Entity
@Table(name="ADVICED_TRANSACTION"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class AdvicedTransaction  extends BaseEntity implements java.io.Serializable {

	private Advice advice;
	private ServiceTransaction serviceTransaction;
    private String name;
    private String description;

    public AdvicedTransaction() {
    }

    public AdvicedTransaction(Advice advice, ServiceTransaction serviceTransaction, String code, String name, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.name = name;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public AdvicedTransaction(Advice advice, ServiceTransaction serviceTransaction, String code, String name, String description, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
		this.advice = advice;
		this.serviceTransaction = serviceTransaction;
		this.code = code;
		this.name = name;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ADVICE_ID", nullable=false)
    @JsonIgnore
    public Advice getAdvice() 
    {
        return this.advice;
    }
    
    public void setAdvice(Advice advice)
    {
        this.advice = advice;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TXN_ID", nullable=false)
    @JsonIgnore
    public ServiceTransaction getServiceTransaction() 
    {
        return this.serviceTransaction;
    }
    
    public void setServiceTransaction(ServiceTransaction serviceTransaction)
    {
        this.serviceTransaction = serviceTransaction;
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


}


