/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.invoice.model;

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

import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.base.util.DateDeserializer;
import com.nathanclaire.alantra.base.util.DateSerializer;
import com.nathanclaire.alantra.party.model.ContactMechanism;
import com.nathanclaire.alantra.party.model.Party;

/**
 * Invoice 
 * @author Edward Banfa
 */
@Entity
@Table(name="INVOICE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Invoice  extends BaseEntity implements java.io.Serializable {

	private InvoiceType invoiceType;
	private Party partyByToPartyId;
	private ContactMechanism contactMechanism;
	private Party partyByFromPartyId;
	private InvoiceTerm invoiceTerm;
    private String message;
    private String description;
    private Date invoiceDt;
	private Set<InvoiceStatus> invoiceStatuses = new HashSet<InvoiceStatus>(0);
	private Set<InvoiceRole> invoiceRoles = new HashSet<InvoiceRole>(0);
	private Set<InvoiceItem> invoiceItems = new HashSet<InvoiceItem>(0);

    public Invoice() {
    }

    public Invoice(InvoiceType invoiceType, Party partyByToPartyId, ContactMechanism contactMechanism, Party partyByFromPartyId, String code, String message, Date invoiceDt, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.message = message;
		this.invoiceDt = invoiceDt;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public Invoice(InvoiceType invoiceType, Party partyByToPartyId, ContactMechanism contactMechanism, Party partyByFromPartyId, InvoiceTerm invoiceTerm, String code, String message, String description, Date invoiceDt, Date effectiveDt, char recSt, Set<InvoiceStatus> invoiceStatuses, Set<InvoiceRole> invoiceRoles, Set<InvoiceItem> invoiceItems ) 
    {
		this.invoiceType = invoiceType;
		this.partyByToPartyId = partyByToPartyId;
		this.contactMechanism = contactMechanism;
		this.partyByFromPartyId = partyByFromPartyId;
		this.invoiceTerm = invoiceTerm;
		this.code = code;
		this.message = message;
		this.description = description;
		this.invoiceDt = invoiceDt;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.invoiceStatuses = invoiceStatuses;
		this.invoiceRoles = invoiceRoles;
		this.invoiceItems = invoiceItems;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="INVOICE_TY_ID", nullable=false)
    @JsonIgnore
    public InvoiceType getInvoiceType() 
    {
        return this.invoiceType;
    }
    
    public void setInvoiceType(InvoiceType invoiceType)
    {
        this.invoiceType = invoiceType;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TO_PARTY_ID", nullable=false)
    @JsonIgnore
    public Party getPartyByToPartyId() 
    {
        return this.partyByToPartyId;
    }
    
    public void setPartyByToPartyId(Party partyByToPartyId)
    {
        this.partyByToPartyId = partyByToPartyId;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CONT_MECH_ID", nullable=false)
    @JsonIgnore
    public ContactMechanism getContactMechanism() 
    {
        return this.contactMechanism;
    }
    
    public void setContactMechanism(ContactMechanism contactMechanism)
    {
        this.contactMechanism = contactMechanism;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FROM_PARTY_ID", nullable=false)
    @JsonIgnore
    public Party getPartyByFromPartyId() 
    {
        return this.partyByFromPartyId;
    }
    
    public void setPartyByFromPartyId(Party partyByFromPartyId)
    {
        this.partyByFromPartyId = partyByFromPartyId;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="INVOICE_TERM_ID")
    @JsonIgnore
    public InvoiceTerm getInvoiceTerm() 
    {
        return this.invoiceTerm;
    }
    
    public void setInvoiceTerm(InvoiceTerm invoiceTerm)
    {
        this.invoiceTerm = invoiceTerm;
    }
		
    @Column(name="MESSAGE" , nullable=false)
    public String getMessage() 
    {
        return this.message;
    }
    
    public void setMessage(String message) 
    {
        this.message = message;
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
    @Temporal(TemporalType.DATE)
    @Column(name="INVOICE_DT" , nullable=false, length=10)
	@JsonSerialize(using = DateSerializer.class)
    public Date getInvoiceDt() 
    {
        return this.invoiceDt;
    }
    
    @JsonDeserialize(using = DateDeserializer.class)
    public void setInvoiceDt(Date invoiceDt) 
    {
        this.invoiceDt = invoiceDt;
    }
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="invoice")
    @JsonIgnore
    public Set<InvoiceStatus> getInvoiceStatuses() 
    {
        return this.invoiceStatuses;
    }
    
    public void setInvoiceStatuses(Set<InvoiceStatus> invoiceStatuses) 
    {
        this.invoiceStatuses = invoiceStatuses;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="invoice")
    @JsonIgnore
    public Set<InvoiceRole> getInvoiceRoles() 
    {
        return this.invoiceRoles;
    }
    
    public void setInvoiceRoles(Set<InvoiceRole> invoiceRoles) 
    {
        this.invoiceRoles = invoiceRoles;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="invoice")
    @JsonIgnore
    public Set<InvoiceItem> getInvoiceItems() 
    {
        return this.invoiceItems;
    }
    
    public void setInvoiceItems(Set<InvoiceItem> invoiceItems) 
    {
        this.invoiceItems = invoiceItems;
    }			


}


