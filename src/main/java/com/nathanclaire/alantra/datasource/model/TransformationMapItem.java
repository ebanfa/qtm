/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.base.model.BaseEntity;

/**
 * An item in a {@link TransformationMap}.
 * 
 * TransformationMapItem 
 * 
 * @author Edward Banfa
 */
@Entity
@Table(name="TRANSFORMATION_MAP_ITEM"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class TransformationMapItem  extends BaseEntity implements java.io.Serializable {

	private String src;
	private String dst;
    private String name;
    private String description;
    private TransformationMap transformationMap;

	public TransformationMapItem() {
    }
    
    /**
	 * @return the transformationMap
	 */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TRANSFORMATION_MAP_ID", nullable=false)
    @JsonIgnore
	public TransformationMap getTransformationMap() {
		return transformationMap;
	}


	/**
	 * @param transformationMap the transformationMap to set
	 */
	public void setTransformationMap(TransformationMap transformationMap) {
		this.transformationMap = transformationMap;
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
	 * @return the src
	 */
    @Column(name="SRC" , nullable=false, length=150)
	public String getSrc() {
		return src;
	}


	/**
	 * @param src the src to set
	 */
	public void setSrc(String src) {
		this.src = src;
	}


	/**
	 * @return the dst
	 */
    @Column(name="DST" , nullable=false, length=150)
	public String getDst() {
		return dst;
	}


	/**
	 * @param dst the dst to set
	 */
	public void setDst(String dst) {
		this.dst = dst;
	}
}