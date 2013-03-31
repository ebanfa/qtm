/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.model.party;

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

import com.nathanclaire.alantra.model.BaseEntity;
import com.nathanclaire.alantra.model.customer.BillingAccountRole;
import com.nathanclaire.alantra.model.customer.Customer;
import com.nathanclaire.alantra.model.customer.CustomerBlacklist;
import com.nathanclaire.alantra.model.invoice.Invoice;
import com.nathanclaire.alantra.model.invoice.InvoiceRole;
import com.nathanclaire.alantra.model.payments.Payment;
import com.nathanclaire.alantra.model.payments.PaymentMethodTypeProvider;
import com.nathanclaire.alantra.util.DateDeserializer;
import com.nathanclaire.alantra.util.DateSerializer;

/**
 * Party 
 * @author Edward Banfa
 */
@Entity
@Table(name="PARTY"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Party  extends BaseEntity implements java.io.Serializable {

	private PartyType partyType;
    private String name;
    private String description;
	private Set<Customer> customers = new HashSet<Customer>(0);
	private Set<PartyClassification> partyClassifications = new HashSet<PartyClassification>(0);
	private Set<PartyRole> partyRoles = new HashSet<PartyRole>(0);
	private Set<Person> persons = new HashSet<Person>(0);
	private Set<CaseRole> caseRoles = new HashSet<CaseRole>(0);
	private Set<PaymentMethodTypeProvider> paymentMethodTypeProviders = new HashSet<PaymentMethodTypeProvider>(0);
	private Set<PartyContactMechanism> partyContactMechanisms = new HashSet<PartyContactMechanism>(0);
	private Set<BillingAccountRole> billingAccountRoles = new HashSet<BillingAccountRole>(0);
	private Set<Organization> organizations = new HashSet<Organization>(0);
	private Set<InvoiceRole> invoiceRoles = new HashSet<InvoiceRole>(0);
	private Set<Payment> paymentsForToPartyId = new HashSet<Payment>(0);
	private Set<Payment> paymentsForFromPartyId = new HashSet<Payment>(0);
	private Set<Invoice> invoicesForFromPartyId = new HashSet<Invoice>(0);
	private Set<Invoice> invoicesForToPartyId = new HashSet<Invoice>(0);
	private Set<CustomerBlacklist> customerBlacklists = new HashSet<CustomerBlacklist>(0);

    public Party() {
    }

    public Party(PartyType partyType, String code, String name, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public Party(PartyType partyType, String code, String name, String description, Date effectiveDt, char recSt, Set<Customer> customers, Set<PartyClassification> partyClassifications, Set<PartyRole> partyRoles, Set<Person> persons, Set<CaseRole> caseRoles, Set<PaymentMethodTypeProvider> paymentMethodTypeProviders, Set<PartyContactMechanism> partyContactMechanisms, Set<BillingAccountRole> billingAccountRoles, Set<Organization> organizations, Set<InvoiceRole> invoiceRoles, Set<Payment> paymentsForToPartyId, Set<Payment> paymentsForFromPartyId, Set<Invoice> invoicesForFromPartyId, Set<Invoice> invoicesForToPartyId, Set<CustomerBlacklist> customerBlacklists ) 
    {
		this.partyType = partyType;
		this.code = code;
		this.name = name;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.customers = customers;
		this.partyClassifications = partyClassifications;
		this.partyRoles = partyRoles;
		this.persons = persons;
		this.caseRoles = caseRoles;
		this.paymentMethodTypeProviders = paymentMethodTypeProviders;
		this.partyContactMechanisms = partyContactMechanisms;
		this.billingAccountRoles = billingAccountRoles;
		this.organizations = organizations;
		this.invoiceRoles = invoiceRoles;
		this.paymentsForToPartyId = paymentsForToPartyId;
		this.paymentsForFromPartyId = paymentsForFromPartyId;
		this.invoicesForFromPartyId = invoicesForFromPartyId;
		this.invoicesForToPartyId = invoicesForToPartyId;
		this.customerBlacklists = customerBlacklists;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARTY_TY_ID", nullable=false)
    @JsonIgnore
    public PartyType getPartyType() 
    {
        return this.partyType;
    }
    
    public void setPartyType(PartyType partyType)
    {
        this.partyType = partyType;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<Customer> getCustomers() 
    {
        return this.customers;
    }
    
    public void setCustomers(Set<Customer> customers) 
    {
        this.customers = customers;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<PartyClassification> getPartyClassifications() 
    {
        return this.partyClassifications;
    }
    
    public void setPartyClassifications(Set<PartyClassification> partyClassifications) 
    {
        this.partyClassifications = partyClassifications;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<PartyRole> getPartyRoles() 
    {
        return this.partyRoles;
    }
    
    public void setPartyRoles(Set<PartyRole> partyRoles) 
    {
        this.partyRoles = partyRoles;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<Person> getPersons() 
    {
        return this.persons;
    }
    
    public void setPersons(Set<Person> persons) 
    {
        this.persons = persons;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<CaseRole> getCaseRoles() 
    {
        return this.caseRoles;
    }
    
    public void setCaseRoles(Set<CaseRole> caseRoles) 
    {
        this.caseRoles = caseRoles;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<PaymentMethodTypeProvider> getPaymentMethodTypeProviders() 
    {
        return this.paymentMethodTypeProviders;
    }
    
    public void setPaymentMethodTypeProviders(Set<PaymentMethodTypeProvider> paymentMethodTypeProviders) 
    {
        this.paymentMethodTypeProviders = paymentMethodTypeProviders;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<PartyContactMechanism> getPartyContactMechanisms() 
    {
        return this.partyContactMechanisms;
    }
    
    public void setPartyContactMechanisms(Set<PartyContactMechanism> partyContactMechanisms) 
    {
        this.partyContactMechanisms = partyContactMechanisms;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<BillingAccountRole> getBillingAccountRoles() 
    {
        return this.billingAccountRoles;
    }
    
    public void setBillingAccountRoles(Set<BillingAccountRole> billingAccountRoles) 
    {
        this.billingAccountRoles = billingAccountRoles;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<Organization> getOrganizations() 
    {
        return this.organizations;
    }
    
    public void setOrganizations(Set<Organization> organizations) 
    {
        this.organizations = organizations;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<InvoiceRole> getInvoiceRoles() 
    {
        return this.invoiceRoles;
    }
    
    public void setInvoiceRoles(Set<InvoiceRole> invoiceRoles) 
    {
        this.invoiceRoles = invoiceRoles;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyByToPartyId")
    @JsonIgnore
    public Set<Payment> getPaymentsForToPartyId() 
    {
        return this.paymentsForToPartyId;
    }
    
    public void setPaymentsForToPartyId(Set<Payment> paymentsForToPartyId) 
    {
        this.paymentsForToPartyId = paymentsForToPartyId;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyByFromPartyId")
    @JsonIgnore
    public Set<Payment> getPaymentsForFromPartyId() 
    {
        return this.paymentsForFromPartyId;
    }
    
    public void setPaymentsForFromPartyId(Set<Payment> paymentsForFromPartyId) 
    {
        this.paymentsForFromPartyId = paymentsForFromPartyId;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyByFromPartyId")
    @JsonIgnore
    public Set<Invoice> getInvoicesForFromPartyId() 
    {
        return this.invoicesForFromPartyId;
    }
    
    public void setInvoicesForFromPartyId(Set<Invoice> invoicesForFromPartyId) 
    {
        this.invoicesForFromPartyId = invoicesForFromPartyId;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyByToPartyId")
    @JsonIgnore
    public Set<Invoice> getInvoicesForToPartyId() 
    {
        return this.invoicesForToPartyId;
    }
    
    public void setInvoicesForToPartyId(Set<Invoice> invoicesForToPartyId) 
    {
        this.invoicesForToPartyId = invoicesForToPartyId;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<CustomerBlacklist> getCustomerBlacklists() 
    {
        return this.customerBlacklists;
    }
    
    public void setCustomerBlacklists(Set<CustomerBlacklist> customerBlacklists) 
    {
        this.customerBlacklists = customerBlacklists;
    }			


}


