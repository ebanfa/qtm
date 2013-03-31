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

import com.nathanclaire.alantra.base.BaseEntity;
import com.nathanclaire.alantra.util.DateDeserializer;
import com.nathanclaire.alantra.util.DateSerializer;

/**
 * InvoiceStatus 
 * @author Edward Banfa
 */
@Entity
@Table(name="INVOICE_STATUS"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class InvoiceStatus  extends BaseEntity implements java.io.Serializable {

	private InvoiceStatusType invoiceStatusType;
	private Invoice invoice;
    private String name;
    private String description;

    public InvoiceStatus() {
    }

    public InvoiceStatus(InvoiceStatusType invoiceStatusType, Invoice invoice, String code, String name, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public InvoiceStatus(InvoiceStatusType invoiceStatusType, Invoice invoice, String code, String name, String description, Date effectiveDt, char recSt) 
    {
		this.invoiceStatusType = invoiceStatusType;
		this.invoice = invoice;
		this.code = code;
		this.name = name;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="INVOICE_ST_TY_ID", nullable=false)
    @JsonIgnore
    public InvoiceStatusType getInvoiceStatusType() 
    {
        return this.invoiceStatusType;
    }
    
    public void setInvoiceStatusType(InvoiceStatusType invoiceStatusType)
    {
        this.invoiceStatusType = invoiceStatusType;
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

