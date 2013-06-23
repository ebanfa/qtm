/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.notification.model;

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
 * TemplateType 
 * @author Edward Banfa
 */
@Entity
@Table(name="TEMPLATE_TYPE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class TemplateType  extends BaseEntity implements java.io.Serializable {

	private TemplateCategory templateCategory;
    private String name;
    private String description;
	private Set<Template> templates = new HashSet<Template>(0);
	private Set<TemplateTypeTag> templateTypeTags = new HashSet<TemplateTypeTag>(0);

    public TemplateType() {
    }

    public TemplateType(TemplateCategory templateCategory, String code, String name, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.name = name;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public TemplateType(TemplateCategory templateCategory, String code, String name, String description, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<Template> templates, Set<TemplateTypeTag> templateTypeTags ) 
    {
		this.templateCategory = templateCategory;
		this.code = code;
		this.name = name;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
		this.templates = templates;
		this.templateTypeTags = templateTypeTags;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TEMPLATE_CAT_ID", nullable=false)
    @JsonIgnore
    public TemplateCategory getTemplateCategory() 
    {
        return this.templateCategory;
    }
    
    public void setTemplateCategory(TemplateCategory templateCategory)
    {
        this.templateCategory = templateCategory;
    }
		
    @Column(name="NAME" , nullable=false, length=150)
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="templateType")
    @JsonIgnore
    public Set<Template> getTemplates() 
    {
        return this.templates;
    }
    
    public void setTemplates(Set<Template> templates) 
    {
        this.templates = templates;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="templateType")
    @JsonIgnore
    public Set<TemplateTypeTag> getTemplateTypeTags() 
    {
        return this.templateTypeTags;
    }
    
    public void setTemplateTypeTags(Set<TemplateTypeTag> templateTypeTags) 
    {
        this.templateTypeTags = templateTypeTags;
    }			


}


