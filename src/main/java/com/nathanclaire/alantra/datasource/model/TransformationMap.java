/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.model;

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
 * TransformationMap 
 * 
 * @author Edward Banfa
 */
@Entity
@Table(name="TRANSFORMATION_MAP"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class TransformationMap  extends BaseEntity implements java.io.Serializable {

    private String name;
    private String description;
    private Set<DataFieldMap> dataFieldMaps = new HashSet<DataFieldMap>();
    private Set<TransformationMapItem> transformationMapItems = new HashSet<TransformationMapItem>();

	public TransformationMap() {
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


	/**
	 * @return the dataFieldMaps
	 */
    @OneToMany(fetch=FetchType.LAZY, mappedBy="transformationMap")
    @JsonIgnore
	public Set<DataFieldMap> getDataFieldMaps() {
		return dataFieldMaps;
	}


	/**
	 * @param dataFieldMaps the dataFieldMaps to set
	 */
	public void setDataFieldMaps(Set<DataFieldMap> dataFieldMaps) {
		this.dataFieldMaps = dataFieldMaps;
	}


	/**
	 * @return the transformationMapItems
	 */
    @OneToMany(fetch=FetchType.LAZY, mappedBy="transformationMap")
    @JsonIgnore
	public Set<TransformationMapItem> getTransformationMapItems() {
		return transformationMapItems;
	}


	/**
	 * @param transformationMapItems the transformationMapItems to set
	 */
	public void setTransformationMapItems(
			Set<TransformationMapItem> transformationMapItems) {
		this.transformationMapItems = transformationMapItems;
	}
}