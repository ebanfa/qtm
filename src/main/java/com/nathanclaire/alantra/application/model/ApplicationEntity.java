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
 * ApplicationEntity 
 * @author Edward Banfa
 */
@Entity
@Table(name="APPLICATION_ENTITY"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ApplicationEntity  extends BaseEntity implements java.io.Serializable {

	private ApplicationModule applicationModule;
    private String name;
    private String description;
    private String displayNm;
    private String displayNmPlural;
    private String entityClassNm;
    private char hasTable;
    private String dbName;
	private Set<ApplicationEntityField> applicationEntityFields = new HashSet<ApplicationEntityField>(0);
	private Set<ApplicationForm> applicationForms = new HashSet<ApplicationForm>(0);
	private Set<ApplicationEntityField> applicationRelatedEntityFields = new HashSet<ApplicationEntityField>(0);
	private Set<ApplicationActivity> applicationActivities = new HashSet<ApplicationActivity>(0);

    public ApplicationEntity() {
    }

    public ApplicationEntity(ApplicationModule applicationModule, String code, String name, String displayNm, String displayNmPlural, char hasTable, String dbName, Date effectiveDt, char recSt) 
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
    public ApplicationEntity(ApplicationModule applicationModule, String code, String name, String description, String displayNm, String displayNmPlural, char hasTable, String dbName, Date effectiveDt, char recSt, Set<ApplicationEntityField> applicationEntityFieldsForEntityId, Set<ApplicationForm> applicationForms, Set<ApplicationEntityField> applicationEntityFieldsForRelatedEntityId, Set<ApplicationActivity> applicationActivities ) 
    {
		this.applicationModule = applicationModule;
		this.code = code;
		this.name = name;
		this.description = description;
		this.displayNm = displayNm;
		this.displayNmPlural = displayNmPlural;
		this.hasTable = hasTable;
		this.dbName = dbName;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.applicationEntityFields = applicationEntityFieldsForEntityId;
		this.applicationForms = applicationForms;
		this.applicationRelatedEntityFields = applicationEntityFieldsForRelatedEntityId;
		this.applicationActivities = applicationActivities;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="applicationEntity")
    @JsonIgnore
    public Set<ApplicationEntityField> getApplicationEntityFields() 
    {
        return this.applicationEntityFields;
    }
    
    public void setApplicationEntityFields(Set<ApplicationEntityField> applicationEntityFieldsForEntityId) 
    {
        this.applicationEntityFields = applicationEntityFieldsForEntityId;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="applicationEntity")
    @JsonIgnore
    public Set<ApplicationForm> getApplicationForms() 
    {
        return this.applicationForms;
    }
    
    public void setApplicationForms(Set<ApplicationForm> applicationForms) 
    {
        this.applicationForms = applicationForms;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="applicationEntity")
    @JsonIgnore
    public Set<ApplicationEntityField> getApplicationRelatedEntityFields() 
    {
        return this.applicationRelatedEntityFields;
    }
    
    public void setApplicationRelatedEntityFields(Set<ApplicationEntityField> applicationEntityFieldsForRelatedEntityId) 
    {
        this.applicationRelatedEntityFields = applicationEntityFieldsForRelatedEntityId;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="applicationEntity")
    @JsonIgnore
    public Set<ApplicationActivity> getApplicationActivities() 
    {
        return this.applicationActivities;
    }
    
    public void setApplicationActivities(Set<ApplicationActivity> applicationActivities) 
    {
        this.applicationActivities = applicationActivities;
    }

	/**
	 * @return the entityClassNm
	 */
    @Column(name="ENTITY_CLASS_NM" , nullable=false, length=75)
	public String getEntityClassNm() {
		return entityClassNm;
	}

	/**
	 * @param entityClassNm the entityClassNm to set
	 */
	public void setEntityClassNm(String entityClassNm) {
		this.entityClassNm = entityClassNm;
	}			


}


