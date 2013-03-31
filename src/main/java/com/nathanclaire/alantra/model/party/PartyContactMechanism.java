/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.model.party;

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

import com.nathanclaire.alantra.model.BaseEntity;
import com.nathanclaire.alantra.util.DateDeserializer;
import com.nathanclaire.alantra.util.DateSerializer;

/**
 * PartyContactMechanism 
 * @author Edward Banfa
 */
@Entity
@Table(name="PARTY_CONTACT_MECHANISM"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PartyContactMechanism  extends BaseEntity implements java.io.Serializable {

	private ContactMechanism contactMechanism;
	private Party party;
    private Date fromDt;
    private Date toDt;
    private String description;
    private char noSolicitationFg;
    private String extension;
    private String remarks;

    public PartyContactMechanism() {
    }

    public PartyContactMechanism(ContactMechanism contactMechanism, Party party, String code, Date fromDt, Date toDt, char noSolicitationFg, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.fromDt = fromDt;
		this.toDt = toDt;
		this.noSolicitationFg = noSolicitationFg;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public PartyContactMechanism(ContactMechanism contactMechanism, Party party, String code, Date fromDt, Date toDt, String description, char noSolicitationFg, Date effectiveDt, String extension, String remarks, char recSt) 
    {
		this.contactMechanism = contactMechanism;
		this.party = party;
		this.code = code;
		this.fromDt = fromDt;
		this.toDt = toDt;
		this.description = description;
		this.noSolicitationFg = noSolicitationFg;
		this.effectiveDt = effectiveDt;
		this.extension = extension;
		this.remarks = remarks;
		this.recSt = recSt;
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
    @Temporal(TemporalType.DATE)
    @Column(name="FROM_DT" , nullable=false, length=10)
	@JsonSerialize(using = DateSerializer.class)
    public Date getFromDt() 
    {
        return this.fromDt;
    }
    
    @JsonDeserialize(using = DateDeserializer.class)
    public void setFromDt(Date fromDt) 
    {
        this.fromDt = fromDt;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="TO_DT" , nullable=false, length=10)
	@JsonSerialize(using = DateSerializer.class)
    public Date getToDt() 
    {
        return this.toDt;
    }
    
    @JsonDeserialize(using = DateDeserializer.class)
    public void setToDt(Date toDt) 
    {
        this.toDt = toDt;
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
		
    @Column(name="NO_SOLICITATION_FG" , nullable=false, length=1)
    public char getNoSolicitationFg() 
    {
        return this.noSolicitationFg;
    }
    
    public void setNoSolicitationFg(char noSolicitationFg) 
    {
        this.noSolicitationFg = noSolicitationFg;
    }
		
    @Column(name="EXTENSION" , unique=true, length=35)
    public String getExtension() 
    {
        return this.extension;
    }
    
    public void setExtension(String extension) 
    {
        this.extension = extension;
    }
		
    @Column(name="REMARKS" , unique=true)
    public String getRemarks() 
    {
        return this.remarks;
    }
    
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }


}


