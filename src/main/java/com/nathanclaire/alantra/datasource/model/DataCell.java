/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.model;

import java.util.Date;

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
 * CellData 
 * @author Edward Banfa
 */
@Entity
@Table(name="DATA_CELL"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class DataCell  extends BaseEntity implements java.io.Serializable {

	private DataRow dataRow;
	private DataField dataField;
    private String name;
    private String description;
    private String cellData;
    private char importStatusFg;

    public DataCell() {
    }

    public DataCell(DataRow dataRow, DataField dataField, String code, String name, String cellData, char importStatusFg, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.name = name;
		this.cellData = cellData;
		this.importStatusFg = importStatusFg;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public DataCell(DataRow dataRow, DataField dataField, String code, String name, String description, String cellData, char importStatusFg, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
		this.dataRow = dataRow;
		this.dataField = dataField;
		this.code = code;
		this.name = name;
		this.description = description;
		this.cellData = cellData;
		this.importStatusFg = importStatusFg;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ROW_DATA_ID", nullable=false)
    @JsonIgnore
    public DataRow getDataRow() 
    {
        return this.dataRow;
    }
    
    public void setDataRow(DataRow dataRow)
    {
        this.dataRow = dataRow;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DATA_FIELD_ID", nullable=false)
    @JsonIgnore
    public DataField getDataField() 
    {
        return this.dataField;
    }
    
    public void setDataField(DataField dataField)
    {
        this.dataField = dataField;
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
		
    @Column(name="CELL_DATA" , nullable=false)
    public String getCellData() 
    {
        return this.cellData;
    }
    
    public void setCellData(String cellData) 
    {
        this.cellData = cellData;
    }
		
    @Column(name="IMPORT_STATUS_FG" , nullable=false, length=1)
    public char getImportStatusFg() 
    {
        return this.importStatusFg;
    }
    
    public void setImportStatusFg(char importStatusFg) 
    {
        this.importStatusFg = importStatusFg;
    }


}


