/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.application.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.base.model.BaseEntity;

/**
 * ApplicationForm 
 * @author Edward Banfa
 */
@Entity
@Table(name="APPLICATION_FORM"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ApplicationForm  extends BaseEntity implements java.io.Serializable {

	private ApplicationFormType applicationFormType;
	private ApplicationModule applicationModule;
	private ApplicationEntity applicationEntity;
    private String name;
    private String description;
    private String displayNm;
    private String displayNmPlural;
    private char hasTable;
    private String dbName;
	private Set<ApplicationFormField> applicationFormFields = new HashSet<ApplicationFormField>(0);
	private Set<ApplicationActivity> applicationActivities = new HashSet<ApplicationActivity>(0);

    public ApplicationForm() {
    }

    public ApplicationForm(ApplicationFormType applicationFormType, ApplicationModule applicationModule, String code, String name, String displayNm, String displayNmPlural, char hasTable, String dbName, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.displayNm = displayNm;
		this.displayNmPlural = displayNmPlural;
		this.hasTable = hasTable;
		this.dbName = dbName;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public ApplicationForm(ApplicationFormType applicationFormType, ApplicationModule applicationModule, ApplicationEntity applicationEntity, String code, String name, String description, String displayNm, String displayNmPlural, char hasTable, String dbName, Date effectiveDt, char recSt, Set<ApplicationFormField> applicationFormFields, Set<ApplicationActivity> applicationActivities ) 
    {
		this.applicationFormType = applicationFormType;
		this.applicationModule = applicationModule;
		this.applicationEntity = applicationEntity;
		this.code = code;
		this.name = name;
		this.description = description;
		this.displayNm = displayNm;
		this.displayNmPlural = displayNmPlural;
		this.hasTable = hasTable;
		this.dbName = dbName;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.applicationFormFields = applicationFormFields;
		this.applicationActivities = applicationActivities;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FORM_TY_ID", nullable=false)
    @JsonIgnore
    public ApplicationFormType getApplicationFormType() 
    {
        return this.applicationFormType;
    }
    
    public void setApplicationFormType(ApplicationFormType applicationFormType)
    {
        this.applicationFormType = applicationFormType;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MODULE_ID", nullable=false)
    @JsonIgnore
    public ApplicationModule getApplicationModule() 
    {
        return this.applicationModule;
    }
    
    public void setApplicationModule(ApplicationModule applicationModule)
    {
        this.applicationModule = applicationModule;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ENTITY_ID")
    @JsonIgnore
    public ApplicationEntity getApplicationEntity() 
    {
        return this.applicationEntity;
    }
    
    public void setApplicationEntity(ApplicationEntity applicationEntity)
    {
        this.applicationEntity = applicationEntity;
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
		
    @Column(name="DESCRIPTION" , unique=true, length=100)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }
		
    @Column(name="DISPLAY_NM" , nullable=false, length=75)
    public String getDisplayNm() 
    {
        return this.displayNm;
    }
    
    public void setDisplayNm(String displayNm) 
    {
        this.displayNm = displayNm;
    }
		
    @Column(name="DISPLAY_NM_PLURAL" , nullable=false, length=75)
    public String getDisplayNmPlural() 
    {
        return this.displayNmPlural;
    }
    
    public void setDisplayNmPlural(String displayNmPlural) 
    {
        this.displayNmPlural = displayNmPlural;
    }
		
    @Column(name="HAS_TABLE" , nullable=false, length=1)
    public char getHasTable() 
    {
        return this.hasTable;
    }
    
    public void setHasTable(char hasTable) 
    {
        this.hasTable = hasTable;
    }
		
    @Column(name="DB_NAME" , nullable=false, length=50)
    public String getDbName() 
    {
        return this.dbName;
    }
    
    public void setDbName(String dbName) 
    {
        this.dbName = dbName;
    }
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="applicationForm")
    @JsonIgnore
    public Set<ApplicationFormField> getApplicationFormFields() 
    {
        return this.applicationFormFields;
    }
    
    public void setApplicationFormFields(Set<ApplicationFormField> applicationFormFields) 
    {
        this.applicationFormFields = applicationFormFields;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="applicationForm")
    @JsonIgnore
    public Set<ApplicationActivity> getApplicationActivities() 
    {
        return this.applicationActivities;
    }
    
    public void setApplicationActivities(Set<ApplicationActivity> applicationActivities) 
    {
        this.applicationActivities = applicationActivities;
    }			


}


