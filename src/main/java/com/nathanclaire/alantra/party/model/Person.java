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

import com.nathanclaire.alantra.base.BaseEntity;
import com.nathanclaire.alantra.base.util.DateDeserializer;
import com.nathanclaire.alantra.base.util.DateSerializer;

/**
 * Person 
 * @author Edward Banfa
 */
@Entity
@Table(name="PERSON"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Person  extends BaseEntity implements java.io.Serializable {

	private Party party;
    private String currentFNm;
    private String currentLNm;
    private String currentMNm;
    private String currentSuffix;
    private String currentPtitle;
    private String currentNNm;
    private String motherMaidenNm;
    private String gender;
    private String maritalSt;
    private String ssNo;
    private String currentPpNo;
    private Date currentPpExpDt;
    private BigDecimal weight;
    private BigDecimal height;
    private Date birthDt;
    private Integer totYrWorkExp;
    private String remarks;

    public Person() {
    }

    public Person(Party party, String code, String currentFNm, String gender, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.currentFNm = currentFNm;
		this.gender = gender;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public Person(Party party, String code, String currentFNm, String currentLNm, String currentMNm, String currentSuffix, String currentPtitle, String currentNNm, String motherMaidenNm, String gender, String maritalSt, String ssNo, String currentPpNo, Date currentPpExpDt, BigDecimal weight, BigDecimal height, Date birthDt, Integer totYrWorkExp, String remarks, Date effectiveDt, char recSt) 
    {
		this.party = party;
		this.code = code;
		this.currentFNm = currentFNm;
		this.currentLNm = currentLNm;
		this.currentMNm = currentMNm;
		this.currentSuffix = currentSuffix;
		this.currentPtitle = currentPtitle;
		this.currentNNm = currentNNm;
		this.motherMaidenNm = motherMaidenNm;
		this.gender = gender;
		this.maritalSt = maritalSt;
		this.ssNo = ssNo;
		this.currentPpNo = currentPpNo;
		this.currentPpExpDt = currentPpExpDt;
		this.weight = weight;
		this.height = height;
		this.birthDt = birthDt;
		this.totYrWorkExp = totYrWorkExp;
		this.remarks = remarks;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
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
		
    @Column(name="CURRENT_F_NM" , nullable=false, length=75)
    public String getCurrentFNm() 
    {
        return this.currentFNm;
    }
    
    public void setCurrentFNm(String currentFNm) 
    {
        this.currentFNm = currentFNm;
    }
		
    @Column(name="CURRENT_L_NM" , unique=true, length=75)
    public String getCurrentLNm() 
    {
        return this.currentLNm;
    }
    
    public void setCurrentLNm(String currentLNm) 
    {
        this.currentLNm = currentLNm;
    }
		
    @Column(name="CURRENT_M_NM" , unique=true, length=75)
    public String getCurrentMNm() 
    {
        return this.currentMNm;
    }
    
    public void setCurrentMNm(String currentMNm) 
    {
        this.currentMNm = currentMNm;
    }
		
    @Column(name="CURRENT_SUFFIX" , unique=true, length=75)
    public String getCurrentSuffix() 
    {
        return this.currentSuffix;
    }
    
    public void setCurrentSuffix(String currentSuffix) 
    {
        this.currentSuffix = currentSuffix;
    }
		
    @Column(name="CURRENT_PTITLE" , unique=true, length=75)
    public String getCurrentPtitle() 
    {
        return this.currentPtitle;
    }
    
    public void setCurrentPtitle(String currentPtitle) 
    {
        this.currentPtitle = currentPtitle;
    }
		
    @Column(name="CURRENT_N_NM" , unique=true, length=75)
    public String getCurrentNNm() 
    {
        return this.currentNNm;
    }
    
    public void setCurrentNNm(String currentNNm) 
    {
        this.currentNNm = currentNNm;
    }
		
    @Column(name="MOTHER_MAIDEN_NM" , unique=true, length=75)
    public String getMotherMaidenNm() 
    {
        return this.motherMaidenNm;
    }
    
    public void setMotherMaidenNm(String motherMaidenNm) 
    {
        this.motherMaidenNm = motherMaidenNm;
    }
		
    @Column(name="GENDER" , nullable=false, length=35)
    public String getGender() 
    {
        return this.gender;
    }
    
    public void setGender(String gender) 
    {
        this.gender = gender;
    }
		
    @Column(name="MARITAL_ST" , unique=true, length=35)
    public String getMaritalSt() 
    {
        return this.maritalSt;
    }
    
    public void setMaritalSt(String maritalSt) 
    {
        this.maritalSt = maritalSt;
    }
		
    @Column(name="SS_NO" , unique=true, length=35)
    public String getSsNo() 
    {
        return this.ssNo;
    }
    
    public void setSsNo(String ssNo) 
    {
        this.ssNo = ssNo;
    }
		
    @Column(name="CURRENT_PP_NO" , unique=true, length=35)
    public String getCurrentPpNo() 
    {
        return this.currentPpNo;
    }
    
    public void setCurrentPpNo(String currentPpNo) 
    {
        this.currentPpNo = currentPpNo;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="CURRENT_PP_EXP_DT" , length=10)
	@JsonSerialize(using = DateSerializer.class)
    public Date getCurrentPpExpDt() 
    {
        return this.currentPpExpDt;
    }
    
    @JsonDeserialize(using = DateDeserializer.class)
    public void setCurrentPpExpDt(Date currentPpExpDt) 
    {
        this.currentPpExpDt = currentPpExpDt;
    }
		
    @Column(name="WEIGHT" , unique=true)
    public BigDecimal getWeight() 
    {
        return this.weight;
    }
    
    public void setWeight(BigDecimal weight) 
    {
        this.weight = weight;
    }
		
    @Column(name="HEIGHT" , unique=true)
    public BigDecimal getHeight() 
    {
        return this.height;
    }
    
    public void setHeight(BigDecimal height) 
    {
        this.height = height;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="BIRTH_DT" , length=10)
	@JsonSerialize(using = DateSerializer.class)
    public Date getBirthDt() 
    {
        return this.birthDt;
    }
    
    @JsonDeserialize(using = DateDeserializer.class)
    public void setBirthDt(Date birthDt) 
    {
        this.birthDt = birthDt;
    }
		
    @Column(name="TOT_YR_WORK_EXP" , unique=true)
    public Integer getTotYrWorkExp() 
    {
        return this.totYrWorkExp;
    }
    
    public void setTotYrWorkExp(Integer totYrWorkExp) 
    {
        this.totYrWorkExp = totYrWorkExp;
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


