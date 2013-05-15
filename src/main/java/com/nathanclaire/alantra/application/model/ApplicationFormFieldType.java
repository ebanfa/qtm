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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.base.model.BaseEntity;

/**
 * ApplicationFormFieldType 
 * @author Edward Banfa
 */
@Entity
@Table(name="APPLICATION_FORM_FIELD_TYPE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ApplicationFormFieldType  extends BaseEntity implements java.io.Serializable {

    private String name;
    private String description;
	private Set<ApplicationFormField> applicationFormFields = new HashSet<ApplicationFormField>(0);

    public ApplicationFormFieldType() {
    }

    public ApplicationFormFieldType(String code, String name, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public ApplicationFormFieldType(String code, String name, String description, Date effectiveDt, char recSt, Set<ApplicationFormField> applicationFormFields ) 
    {
		this.code = code;
		this.name = name;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.applicationFormFields = applicationFormFields;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="applicationFormFieldType")
    @JsonIgnore
    public Set<ApplicationFormField> getApplicationFormFields() 
    {
        return this.applicationFormFields;
    }
    
    public void setApplicationFormFields(Set<ApplicationFormField> applicationFormFields) 
    {
        this.applicationFormFields = applicationFormFields;
    }			


}


