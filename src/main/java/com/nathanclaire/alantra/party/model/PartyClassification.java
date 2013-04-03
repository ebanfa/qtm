/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.party.model;

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

/**
 * PartyClassification 
 * @author Edward Banfa
 */
@Entity
@Table(name="PARTY_CLASSIFICATION"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PartyClassification  extends BaseEntity implements java.io.Serializable {

	private PartyType partyType;
	private PartyClassificationType partyClassificationType;
	private Party party;
    private String name;
    private String description;
    private Date fromDt;
    private Date thruDt;

    public PartyClassification() {
    }

    public PartyClassification(PartyClassificationType partyClassificationType, String code, String name, Date fromDt, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.fromDt = fromDt;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public PartyClassification(PartyType partyType, PartyClassificationType partyClassificationType, Party party, String code, String name, String description, Date fromDt, Date thruDt, Date effectiveDt, char recSt) 
    {
		this.partyType = partyType;
		this.partyClassificationType = partyClassificationType;
		this.party = party;
		this.code = code;
		this.name = name;
		this.description = description;
		this.fromDt = fromDt;
		this.thruDt = thruDt;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARTY_TY_ID")
    @JsonIgnore
    public PartyType getPartyType() 
    {
        return this.partyType;
    }
    
    public void setPartyType(PartyType partyType)
    {
        this.partyType = partyType;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLASS_TY_ID", nullable=false)
    @JsonIgnore
    public PartyClassificationType getPartyClassificationType() 
    {
        return this.partyClassificationType;
    }
    
    public void setPartyClassificationType(PartyClassificationType partyClassificationType)
    {
        this.partyClassificationType = partyClassificationType;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARTY_ID")
    @JsonIgnore
    public Party getParty() 
    {
        return this.party;
    }
    
    public void setParty(Party party)
    {
        this.party = party;
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
    @Column(name="THRU_DT" , length=10)
	@JsonSerialize(using = DateSerializer.class)
    public Date getThruDt() 
    {
        return this.thruDt;
    }
    
    @JsonDeserialize(using = DateDeserializer.class)
    public void setThruDt(Date thruDt) 
    {
        this.thruDt = thruDt;
    }


}


