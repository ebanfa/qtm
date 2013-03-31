/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.util.DateDeserializer;
import com.nathanclaire.alantra.util.DateSerializer;

/**
 * InvoiceTerm 
 * @author Edward Banfa
 */
@Entity
@Table(name="INVOICE_TERM"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class InvoiceTerm  extends BaseEntity implements java.io.Serializable {

	private TermType termType;
    private BigDecimal termVal;
    private String description;
	private Set<Invoice> invoices = new HashSet<Invoice>(0);

    public InvoiceTerm() {
    }

    public InvoiceTerm(TermType termType, String code, BigDecimal termVal, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.termVal = termVal;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public InvoiceTerm(TermType termType, String code, BigDecimal termVal, String description, Date effectiveDt, char recSt, Set<Invoice> invoices ) 
    {
		this.termType = termType;
		this.code = code;
		this.termVal = termVal;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.invoices = invoices;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TERM_TY_ID", nullable=false)
    @JsonIgnore
    public TermType getTermType() 
    {
        return this.termType;
    }
    
    public void setTermType(TermType termType)
    {
        this.termType = termType;
    }
		
    @Column(name="TERM_VAL" , nullable=false)
    public BigDecimal getTermVal() 
    {
        return this.termVal;
    }
    
    public void setTermVal(BigDecimal termVal) 
    {
        this.termVal = termVal;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="invoiceTerm")
    @JsonIgnore
    public Set<Invoice> getInvoices() 
    {
        return this.invoices;
    }
    
    public void setInvoices(Set<Invoice> invoices) 
    {
        this.invoices = invoices;
    }			


}


