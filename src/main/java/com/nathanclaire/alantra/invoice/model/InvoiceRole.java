/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.invoice.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
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
import com.nathanclaire.alantra.party.model.Party;

/**
 * InvoiceRole 
 * @author Edward Banfa
 */
@Entity
@Table(name="INVOICE_ROLE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class InvoiceRole  extends BaseEntity implements java.io.Serializable {

	private InvoiceRoleType invoiceRoleType;
	private Party party;
	private Invoice invoice;
    private String description;
    private Integer percentage;

    public InvoiceRole() {
    }

    public InvoiceRole(InvoiceRoleType invoiceRoleType, Party party, Invoice invoice, String code, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public InvoiceRole(InvoiceRoleType invoiceRoleType, Party party, Invoice invoice, String code, String description, Integer percentage, Date effectiveDt, char recSt) 
    {
		this.invoiceRoleType = invoiceRoleType;
		this.party = party;
		this.invoice = invoice;
		this.code = code;
		this.description = description;
		this.percentage = percentage;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="INVOICE_ROLE_TY_ID", nullable=false)
    @JsonIgnore
    public InvoiceRoleType getInvoiceRoleType() 
    {
        return this.invoiceRoleType;
    }
    
    public void setInvoiceRoleType(InvoiceRoleType invoiceRoleType)
    {
        this.invoiceRoleType = invoiceRoleType;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARTY_ID", nullable=false)
    @JsonIgnore
    public Party getParty() 
    {
        return this.party;
    }
    
    public void setParty(Party party)
    {
        this.party = party;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="INVOICE_ID", nullable=false)
    @JsonIgnore
    public Invoice getInvoice() 
    {
        return this.invoice;
    }
    
    public void setInvoice(Invoice invoice)
    {
        this.invoice = invoice;
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
		
    @Column(name="PERCENTAGE" , unique=true)
    public Integer getPercentage() 
    {
        return this.percentage;
    }
    
    public void setPercentage(Integer percentage) 
    {
        this.percentage = percentage;
    }


}


